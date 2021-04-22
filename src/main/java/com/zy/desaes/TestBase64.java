package com.zy.desaes;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class TestBase64 {
    public static void main(String[] args) {
        //1  表示一个字节，不够三个字节
        // MQ==:需要注意，在使用base64进行编码的时候，如果字节不够三个字节，需要使用=进行补齐
        System.out.println(Base64.encode("1".getBytes()));
        //如果是两个字节，就补齐一个=号
        System.out.println(Base64.encode("12".getBytes()));
        //三个字节，则不用补=
        System.out.println(Base64.encode("123".getBytes()));

        //早安:是6个字节 6 * 8 = 48位，刚好可以被6整除，所以没有=号
        System.out.println(Base64.encode("早安".getBytes()));
    }
}
