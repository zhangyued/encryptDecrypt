package com.zy.digest;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要算法防止篡改,常见加密算法：md5，sha1，sha256，sha512
 */
public class DigestDemo1 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //创建原文
        String inputStr = "aa";
        //算法
        String algorithm = "MD5";
        String md5 = getDigest(inputStr, algorithm);
        System.out.println("MD5:" + md5);

        String sha1 = getDigest(inputStr, "SHA-1");
        System.out.println("SHA-1:" + sha1);

        String sha256 = getDigest(inputStr, "SHA-256");
        System.out.println("SHA-256:" + sha256);

        String sha512 = getDigest(inputStr, "SHA-512");
        System.out.println("SHA-512:" + sha512);


        /**
         * MD5:4124bc0a9335c27f086f24ba207a4912
         * SHA-1:e0c9035898dd52fc65c41454cec9c4d2611bfb37
         * SHA-256:961b6dd3ede3cb8ecbaacbd68de040cd78eb2ed5889130cceb4c49268ea4d506
         */
    }

    /**
     * 获取数字摘要
     * @param inputStr
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String getDigest(String inputStr, String algorithm) throws NoSuchAlgorithmException {
        //创建消息摘要对象
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        byte[] bytes = messageDigest.digest(inputStr.getBytes());
        return toHex(bytes);
    }

    private static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            //把密文转成16进制
            String s = Integer.toHexString(aByte & 0xff);
            //判断密文的长度是1，需要在高位补0
            if(s.length() == 1){
                s = "0" + s;
            }
            sb.append(s);
        }
        //System.out.println(sb.toString());
        //使用base64转码
        //System.out.println(Base64.encode(bytes));
        return sb.toString();
    }
}
