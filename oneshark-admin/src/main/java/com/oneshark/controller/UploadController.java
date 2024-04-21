package com.oneshark.controller;

import com.oneshark.domain.ResponseResult;
import com.oneshark.service.impl.OssUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @version 1.0
 * @Author bobo
 * @Date 2024/2/18 16:33
 * @注释
 */
@RestController
public class UploadController {
    @Autowired
    private OssUploadService uploadService;
    @PostMapping("/upload")
    public ResponseResult uploadImg (@RequestParam("img")MultipartFile multipartFile) {
        try {
            return uploadService.uploadImg(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传上传失败");
        }
    }
}
