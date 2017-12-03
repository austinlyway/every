package com.lyways.framework.security.encryption;

import java.security.MessageDigest;

/**
 * message-digest algorithm 5 （信息-摘要算法）缩写，广泛用于加密和解密技术;
 * 常用于文件校验。校验？不管文件多大，经过MD5后都能生成唯一的MD5值
 * @author austin
 * @createDate 2017/11/29.
 */
public class MD5 {

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * @param source    原字符串
     * @param encoding  指定编码类型
     * @param uppercase 是否转为大写字符串
     */
    public static String encode(String source, String encoding, boolean uppercase) {
        String result = null;
        try {
            result = source;
            // 获得MD5摘要对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组更新摘要信息
            messageDigest.update(result.getBytes(encoding));
            // messageDigest.digest()获得16位长度
            // result = parseByte2HexStr(messageDigest.digest());
//            result = byteArrayToHexString(messageDigest.digest());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return uppercase ? result.toUpperCase() : result;
    }


    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i], true));
        }
        return resultSb.toString();
    }

    public static String byteArrayToHexStringLittleEnding(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i], false));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b, boolean bigEnding) {
        int n = b;
        if (n < 0){
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return (bigEnding)?(hexDigits[d1] + hexDigits[d2]):(hexDigits[d2] + hexDigits[d1]);
    }
}
