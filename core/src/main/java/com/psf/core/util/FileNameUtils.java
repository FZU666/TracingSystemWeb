package com.psf.core.util;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public class FileNameUtils {

    /**
     * 获取文件后缀
     */
    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     *
     * @param fileOriginName 源文件名
     */
    public static String getFileName(String fileOriginName) {
        return UUIDUtils.getUUID() + FileNameUtils.getSuffix(fileOriginName);
    }
}
