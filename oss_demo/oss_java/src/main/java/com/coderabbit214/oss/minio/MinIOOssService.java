package com.coderabbit214.oss.minio;

import com.coderabbit214.oss.FormData;
import com.coderabbit214.oss.OssConstantPropertiesUtils;
import com.coderabbit214.oss.OssInterface;
import com.coderabbit214.oss.OssRequestData;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author Mr_J
 */
@Service
public class MinIOOssService implements OssInterface {

    String endpoint = MinIOConstantPropertiesUtils.END_POINT;
    String accessKeyId = MinIOConstantPropertiesUtils.KEY_ID;
    String accessKeySecret = MinIOConstantPropertiesUtils.KEY_SECRET;
    String bucketName = MinIOConstantPropertiesUtils.BUCKET_NAME;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file, String objectName) {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKeyId, accessKeySecret)
                .build();

        InputStream in = null;
        try {
            in = file.getInputStream();
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName)
                    .stream(in, file.getSize(), -1).contentType(file.getContentType()).build());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件异常");
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
            throw new RuntimeException("上传异常");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return endpoint + "/" + objectName;
    }

    /**
     * 判断文件是否存在
     */
    @Override
    public boolean existFile(String objectName) {
        return false;
    }

    /**
     * 上传签名
     */
    @Override
    public OssRequestData getPolicy(String dir, String fileName) {
        fileName = UUID.randomUUID() + "_" + fileName;
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKeyId, accessKeySecret)
                .build();
        String product = null;
        OssRequestData ossRequestData =null;
        try {
            product = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(bucketName)
                            .object(dir + fileName)
                            .expiry(OssConstantPropertiesUtils.UPDATE_EXPIRY_TIME)
                            .build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            e.printStackTrace();
            throw new RuntimeException("获取签名异常");
        }
        FormData formData = new FormData(accessKeyId,null,null,dir,dir+fileName,fileName,null);
        ossRequestData = new OssRequestData(product,"data",false,"put",formData);
        return ossRequestData;
    }

    /**
     * 查看签名
     */
    @Override
    public String getExpiration(String objectName) {

        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKeyId, accessKeySecret)
                .build();
        String presignedObjectUrl = null;
        try {
            presignedObjectUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(OssConstantPropertiesUtils.DOWNLOAD_EXPIRY_TIME)
                            .build()
            );
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            e.printStackTrace();
            throw new RuntimeException("获取签名异常");
        }
        return presignedObjectUrl;
    }
}
