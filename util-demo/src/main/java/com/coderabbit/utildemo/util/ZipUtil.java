package com.coderabbit.utildemo.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: Mr_J
 * @Date: 2021/12/21 5:09 下午
 */
public class ZipUtil {
    public static void zipMultipleFiles(FileOutputStream outputStream, File... files) throws IOException {
        //创建zip输出流
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        //遍历文件加入到zip文件流中
        for (File file : files) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zipOutputStream.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length =fileInputStream.read(bytes)) >= 0) {
                zipOutputStream.write(bytes,0,length);
            }
            fileInputStream.close();
        }
        zipOutputStream.close();
//      未关闭outputStream，外部关闭
    }

    public static void main(String[] args) {
        
    }
}
