package com.youlai.boot.rag;

import com.youlai.boot.ai.utils.DocumentUtil;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.milvus.MilvusVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.util.List;

import static com.google.api.ResourceProto.resource;

/**
 * @auth chengmingchao
 * @date 2025/12/31 15:25
 * @description
 */
@SpringBootTest
public class DocumentTest {

    @Autowired
    private DocumentUtil documentUtil;
    @Autowired
    private MilvusVectorStore vectorStore;

    @Test
    public void testStore() throws MalformedURLException {
        Resource resource = new FileSystemResource("/Users/cmc/Desktop/锐锢商城供应链主数据管理平台软件-软著申请2025/锐锢商城供应链主数据管理平台软件V1.0_使用手册.doc");
        documentUtil.processAndStoreDocuments(resource);
    }

    @Test
    public void testQuery() {
        SearchRequest searchRequest = SearchRequest.builder().topK(3).query("提前推单操作").build();
        List<Document> documentList = vectorStore.similaritySearch(searchRequest);
        System.out.println(documentList);
    }
}
