package com.plugin.users.utils;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.springframework.util.StringUtils;

import java.io.File;
 
/**
 * @author 向振华
 * @date 2021/07/09 10:47
 */
@Slf4j
public class FileZipUtils {
 
    /**
     * 压缩
     *
     * @param sourceFile 压缩源文件，会在源文件所在目录下新建一个zip文件夹存放压缩后的文件
     * @param password   密码
     */
    public static void compress(File sourceFile, String password) {
        File dir = new File(sourceFile.getParent() + File.separator + "zip");
        dir.mkdir();
 
        // 文件名
        String fileName = sourceFile.getName();
        // 文件真实名（不含扩展名）
        String realName = fileName.substring(0, fileName.lastIndexOf("."));
        String targetPathname = dir.getAbsolutePath() + File.separator + realName + ".zip";
        File targetFile = new File(targetPathname);
 
        ZipParameters zipParameters = new ZipParameters();
        ZipFile zipFile = new ZipFile(targetFile);
        // 是否加密
        if (!StringUtils.isEmpty(password)) {
            zipParameters.setEncryptFiles(true);
            zipParameters.setEncryptionMethod(EncryptionMethod.AES);
            zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
            zipFile.setPassword(password.toCharArray());
        }
        try {
            zipFile.addFile(sourceFile, zipParameters);
        } catch (Exception e) {
            log.error("压缩文件异常：", e);
        }
    }
 
    /**
     * 解压
     *
     * @param sourceFile 解压源文件，会在源文件所在目录下新建一个unzip文件夹存放解压后的文件
     * @param password   密码
     */
    public static void uncompress(File sourceFile, String password) {
        // 文件夹路径
        String parent = sourceFile.getParent();
        String targetPath = parent + File.separator + "unzip";
        File dir = new File(targetPath);
        dir.mkdir();

        ZipFile zipFile = new ZipFile(sourceFile);
        try {
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password.toCharArray());
            }
            zipFile.extractAll(targetPath);
        } catch (ZipException e) {
            log.error("解压缩文件异常：", e);
        }
    }

    public static void main(String[] args) {
        // 压缩
        compress(new File("C:\\KeyMaker.jar"), "123");

        // 解压
        uncompress(new File("C:\\zip\\KeyMaker.zip"), "123");
    }
}