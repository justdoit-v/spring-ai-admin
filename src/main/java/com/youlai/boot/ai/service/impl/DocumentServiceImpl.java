package com.youlai.boot.ai.service.impl;

import com.youlai.boot.ai.service.DocumentService;
import com.youlai.boot.ai.utils.DocumentUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auth chengmingchao
 * @date 2025/12/31 13:08
 * @description
 */
@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentUtil documentUtil;

    @Override
    public void upload(MultipartFile file) {
        documentUtil.processAndStoreDocuments(file.getResource());
    }
}
