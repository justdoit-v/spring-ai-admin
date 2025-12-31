package com.youlai.boot.ai.service;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

    void upload(MultipartFile file);
}
