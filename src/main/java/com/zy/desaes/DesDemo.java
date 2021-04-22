package com.zy.desaes;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
 * 对称加密
 */
public class DesDemo {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, Base64DecodingException {
        //原文
        String inputStr = "早安";
        //定义key
        //如果使用des进行加密，秘钥必须是8个字节
        String key = "123456qw";
        //算法
        String transformation = "DES";
        //加密类型
        String algorithm = "DES";
        String encryptDES = encryptDES(inputStr, key, transformation, algorithm);
        System.out.println("加密:" + encryptDES);

        String decryptDES = decryptDES(encryptDES, key, transformation, algorithm);
        System.out.println("解密:" + decryptDES);
    }

    /**
     * 解密
     * @param encryptDES
     * @param key
     * @param transformation
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static String decryptDES(String encryptDES, String key, String transformation, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, Base64DecodingException {
        //创建解密对象
        Cipher cipher = Cipher.getInstance(transformation);
        //创建解密规则
        //第一个参数表示key的字节
        //第二个参数表示解密类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),algorithm);
        //进行解密初始化
        //第一个参数表示模式，加密模式和解密模式
        //第二个参数，加密规则
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        //调用解密方法
        //传入密文
        byte[] bytes = cipher.doFinal(Base64.decode(encryptDES));
        //创建base64对象,进行编码
        return new String(bytes);
    }

    /**
     * 加密
     * @param inputStr
     * @param key
     * @param transformation
     * @param algorithm
     * @return。
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static String encryptDES(String inputStr, String key, String transformation, String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        //创建加密规则
        //第一个参数表示key的字节
        //第二个参数表示加密类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),algorithm);
        //进行加密初始化
        //第一个参数表示模式，加密模式和解密模式
        //第二个参数，加密规则
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        //调用加密方法
        //参数表示原文的字节数组
        byte[] bytes = cipher.doFinal(inputStr.getBytes());
//        for (byte aByte : bytes) {
//            System.out.println(aByte);
//        }
//        //打印密文
//        //如果直接打印会出现乱码,是因为在编码表上找不到对应的字符
//        System.out.println(new String(bytes));

        //创建base64对象,进行编码
        return Base64.encode(bytes);
    }
}
