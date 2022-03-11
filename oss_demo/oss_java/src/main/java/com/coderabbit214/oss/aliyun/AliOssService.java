package com.coderabbit214.oss.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.coderabbit214.oss.FormData;
import com.coderabbit214.oss.OssConstantPropertiesUtils;
import com.coderabbit214.oss.OssInterface;
import com.coderabbit214.oss.OssRequestData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * @author Mr_J
 */
@Service
public class AliOssService implements OssInterface {

    String endpoint = AliyunConstantPropertiesUtils.END_POINT;
    String accessKeyId = AliyunConstantPropertiesUtils.KEY_ID;
    String accessKeySecret = AliyunConstantPropertiesUtils.KEY_SECRET;
    String bucketName = AliyunConstantPropertiesUtils.BUCKET_NAME;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file, String objectName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            //第一个参数 Bucket名称
            //第二个参数 文件路径和文件名称
            //第三个参数 文件输入流
            ossClient.putObject(bucketName, objectName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //上传之后的文件路径返回
            //需要手动拼接
            return "https://" + bucketName + "." + endpoint + "/" + objectName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断文件是否存在
     */
    @Override
    public boolean existFile(String objectName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        boolean found = ossClient.doesObjectExist(bucketName, objectName);
        ossClient.shutdown();
        return found;
    }

    /**
     * 签名
     */
    @Override
    public OssRequestData getPolicy(String dir,String fileName){
        fileName = UUID.randomUUID()+"_"+fileName;
        String host = "https://" + bucketName + "." + endpoint;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        OssRequestData ossRequestData =null;
        try {
            long expireEndTime = System.currentTimeMillis() + OssConstantPropertiesUtils.UPDATE_EXPIRY_TIME * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir+fileName);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);
            FormData formData = new FormData(accessKeyId,encodedPolicy,postSignature,dir,dir+fileName,fileName,String.valueOf(expireEndTime / 1000));
            ossRequestData = new OssRequestData(host,"file",true,"post",formData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return ossRequestData;
    }

    /**
     * 获取签名授权访问
     * @param objectName
     * @return
     */
    @Override
    public String getExpiration(String objectName){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        Date expiration = new Date(System.currentTimeMillis() + OssConstantPropertiesUtils.DOWNLOAD_EXPIRY_TIME * 1000);
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        ossClient.shutdown();
        return url.toString();
    }

}
