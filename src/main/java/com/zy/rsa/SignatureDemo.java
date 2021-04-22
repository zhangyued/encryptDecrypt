package com.zy.rsa;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class SignatureDemo {
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException, Base64DecodingException, IOException, SignatureException, InvalidKeyException {
        String inputStr = "123";
        String algorithm = "RSA";
        //读取私钥
        PrivateKey privateKey = RSAdemo.getPrivateKey(algorithm,"a.pri");
        //读取公钥
        PublicKey publicKey = RSAdemo.getPublicKey(algorithm,"a.pub");

        //获取数字签名
        String signaturedData = getSignature(inputStr,"sha256withrsa",privateKey);
        System.out.println(signaturedData);

        //校验签名
        boolean flag = verifySignature(inputStr,"sha256withrsa",publicKey,signaturedData);
        System.out.println(flag);
    }

    /**
     * 校验签名
     * @param inputStr 原文
     * @param algorithm 算法
     * @param publicKey 公钥key
     * @param signaturedData 签名密文
     * @return
     */
    private static boolean verifySignature(String inputStr, String algorithm, PublicKey publicKey, String signaturedData) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, Base64DecodingException {
        //获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        //初始化校验
        signature.initVerify(publicKey);
        //传入原文
        signature.update(inputStr.getBytes());
        //校验数据
        return signature.verify(Base64.decode(signaturedData));
    }

    /**
     * 生成数字签名
     * @param inputStr 原文
     * @param algorithm 算法
     * @param privateKey 私钥key
     * @return
     */
    private static String getSignature(String inputStr, String algorithm, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        //获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        //初始化签名
        signature.initSign(privateKey);
        //传入原文
        signature.update(inputStr.getBytes());
        //开始签名
        byte[] sign = signature.sign();
        return Base64.encode(sign);
    }

}
