package com.youlai.boot.ai.utils;

import com.alibaba.cloud.ai.transformer.splitter.SentenceSplitter;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.milvus.MilvusVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auth chengmingchao
 * @date 2025/12/31 13:05
 * @description 文件处理工具
 */
@Component
public class DocumentUtil {

    @Autowired
    private MilvusVectorStore vectorStore;

    /**
     * 处理并存储一个通用文档（Word, Excel, PPT, 纯文本等）
     * 内部方法：核心处理流程（分块 -> 存储）
     * @param fileResource 文件资源
     */
    public void processAndStoreDocuments(Resource fileResource) {
        TikaDocumentReader tikaReader = new TikaDocumentReader(fileResource);
        List<Document> documents = tikaReader.get();
        // 1. 分块：将大文档拆分成适合Embedding的小块
        TokenTextSplitter textSplitter = new TokenTextSplitter();
        List<Document> splitDocuments = textSplitter.apply(documents);

        // 2. （可选）为每个分块添加元数据，便于后续检索过滤
        for (Document doc : splitDocuments) {
            doc.getMetadata().put("source", fileResource.getFilename());
            doc.getMetadata().put("createdTime", System.currentTimeMillis());
        }
        // 3. 向量化并存储：调用VectorStore，内部会自动使用配置的Ollama Embedding模型生成向量，并存入Milvus
        vectorStore.add(splitDocuments);
    }
}
