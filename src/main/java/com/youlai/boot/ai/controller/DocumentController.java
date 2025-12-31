package com.youlai.boot.ai.controller;

import com.youlai.boot.ai.service.DocumentService;
import com.youlai.boot.core.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auth chengmingchao
 * @date 2025/12/31 10:16
 * @description
 */
@RestController
@RequestMapping("/doc")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload")
    public Result<Void> upload(MultipartFile file) {
        documentService.upload(file);
        return Result.success();
    }
}
