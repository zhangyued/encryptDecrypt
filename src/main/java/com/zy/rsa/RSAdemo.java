package com.zy.rsa;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密
 */
public class RSAdemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, Base64DecodingException, IOException, InvalidKeySpecException {
        //定义原文
        String inputStr = "早安";
        //创建密钥对
        String algorithm = "RSA";
        //创建保存秘钥
        generateKeyToFile(algorithm, "a.pub", "a.pri");

        //读取私钥
        PrivateKey privateKey = getPrivateKey(algorithm,"a.pri");

        //读取公钥
        PublicKey publicKey = getPublicKey(algorithm,"a.pub");

        String encryptedStr = encryptRSA(algorithm, privateKey, inputStr);
        System.out.println("加密后：" + encryptedStr);

        String decryptedStr = decryptRSA(algorithm, publicKey, encryptedStr);
        System.out.println("解密后：" + decryptedStr);

    }

    /**
     * 读取公钥
     * @param algorithm
     * @param publicKeyPath
     * @return
     */
    static PublicKey getPublicKey(String algorithm, String publicKeyPath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, Base64DecodingException {
        String privateKeyStr = FileUtils.readFileToString(new File(publicKeyPath), Charset.defaultCharset());
        //创建key工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        //创建私钥key规则
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(privateKeyStr));
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    /**
     * 读取私钥
     * @param algorithm
     * @param privateKeyPath
     * @return
     */
    static PrivateKey getPrivateKey(String algorithm, String privateKeyPath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, Base64DecodingException {
        String privateKeyStr = FileUtils.readFileToString(new File(privateKeyPath), Charset.defaultCharset());
        //创建key工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        //创建私钥key规则
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyStr));
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * 保存公钥私钥，把公钥和私钥保存到根目录
     * @param algorithm 算法
     * @param publicPath 公钥路径
     * @param privatePath 私钥路径
     */
    private static void generateKeyToFile(String algorithm, String publicPath, String privatePath) throws NoSuchAlgorithmException, IOException {
        //KeyPairGenerator:密钥对生成器对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //生成公钥
        PublicKey aPublic = keyPair.getPublic();
        //生成私钥
        PrivateKey aPrivate = keyPair.getPrivate();
        //生成公钥字节数组
        byte[] aPublicEncoded = aPublic.getEncoded();
        //生成私钥字节数组
        byte[] aPrivateEncoded = aPrivate.getEncoded();

        //使用base64进行编码
        String encodePub = Base64.encode(aPublicEncoded);
        String encodePri = Base64.encode(aPrivateEncoded);

        //把公钥私钥保存到根目录
        FileUtils.writeStringToFile(new File(publicPath),encodePub, Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(privatePath),encodePri, Charset.forName("UTF-8"));
    }

    /**
     * RSA加密
     * @param algorithm
     * @param aPrivate
     * @param inputStr
     * @return
     */
    public static String encryptRSA(String algorithm , Key aPrivate, String inputStr) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        //创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        //对加密进行初始化
        //第一个参数：加密模式
        //第二个参数：想使用公钥还是私钥加密
        //使用私钥加密
        cipher.init(Cipher.ENCRYPT_MODE,aPrivate);
        //使用私钥进行加密
        byte[] bytes = cipher.doFinal(inputStr.getBytes());
        return Base64.encode(bytes);
    }

    /**
     * RSA解密
     * @param algorithm
     * @param aPublic
     * @param encryptedStr
     * @return
     */
    public static String decryptRSA(String algorithm , Key aPublic, String encryptedStr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, Base64DecodingException {
        //创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        //私钥解密
        cipher.init(Cipher.DECRYPT_MODE,aPublic);
        //转码
        byte[] decode = Base64.decode(encryptedStr);
        return new String(cipher.doFinal(decode));
    }

}
