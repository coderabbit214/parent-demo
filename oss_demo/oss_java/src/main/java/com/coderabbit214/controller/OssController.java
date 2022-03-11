package com.coderabbit214.controller;

import com.coderabbit214.oss.OssConstantPropertiesUtils;
import com.coderabbit214.oss.OssFactory;
import com.coderabbit214.oss.OssInterface;
import com.coderabbit214.oss.OssRequestData;
import io.minio.errors.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Mr_J
 * @Date: 2022/3/10 10:30 上午
 */
@RestController
@CrossOrigin
public class OssController {

    @Autowired
    private OssFactory ossFactory;

    @Operation(summary = "上传文件")
    @PostMapping(value = "uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestPart MultipartFile file){
        OssInterface ossService = ossFactory.getOssService();
        return ossService.uploadFile(file, OssConstantPropertiesUtils.DIR+UUID.randomUUID()+file.getOriginalFilename());
    }

    /**
     *
     * @return
     */
    @Operation(summary = "获取上传签名")
    @GetMapping(value = "getPolicy")
    public OssRequestData getPolicy(String fileName){
        OssInterface ossService = ossFactory.getOssService();
        return ossService.getPolicy(OssConstantPropertiesUtils.DIR,fileName);
    }

    /**
     * 可以添加redis对相同文件的请求进行缓存
     * @param objectName
     * @return
     */
    @Operation(summary = "获取查看签名")
    @GetMapping(value = "getExpiration")
    public String getExpiration(String objectName){
        OssInterface ossService = ossFactory.getOssService();
        return ossService.getExpiration(objectName);
    }
}
