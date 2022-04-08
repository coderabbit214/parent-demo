package com.coderabbit.utildemo.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
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

    /**
     * 打包文件夹
     *
     * @param sourceDir 原文件夹
     * @param zipFile   目标 zip 文件
     * @throws IOException IO异常
     */
    public static void packFolder(File sourceDir, File zipFile) throws IOException {
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(zipFile.toPath()))) {
            Path pp = sourceDir.toPath();
            Files.walk(pp)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    /**
     * 往指定目录解压
     *
     * @param is
     * @param targetDir
     * @throws IOException
     */
    public static void unzip(InputStream is, Path targetDir) throws IOException {
        targetDir = targetDir.toAbsolutePath();
        try (ZipInputStream zipIn = new ZipInputStream(is)) {
            for (ZipEntry ze; (ze = zipIn.getNextEntry()) != null; ) {
                Path resolvedPath = targetDir.resolve(ze.getName()).normalize();
                if (!resolvedPath.startsWith(targetDir)) {
                    throw new RuntimeException("Entry with an illegal path: "
                            + ze.getName());
                }
                if (ze.isDirectory()) {
                    Files.createDirectories(resolvedPath);
                } else {
                    Files.createDirectories(resolvedPath.getParent());
                    Files.copy(zipIn, resolvedPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

    public static void main(String[] args) {
        
    }
}
