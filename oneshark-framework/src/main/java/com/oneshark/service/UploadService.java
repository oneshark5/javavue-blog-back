package com.oneshark.service;

import com.oneshark.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

// 因为前台后台都会涉及一些上传操作，所以定义在framework中
public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
