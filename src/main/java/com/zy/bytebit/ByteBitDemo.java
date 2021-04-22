package com.zy.bytebit;

import java.io.UnsupportedEncodingException;

public class ByteBitDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //根据编码格式不一样，对应的字节也不一样
        //如果是utf-8格式一个中文对应三个字节
        //如果是gbk格式一个中文对应两个字节
        //String s = "早";

        //如果是英文，和编码格式无关
        String s = "A";
        byte[] bytes = s.getBytes("GBK");
        for (byte aByte : bytes) {
            System.out.println(aByte);
            String s1 = Integer.toBinaryString(aByte);
            System.out.println(s1);
        }
    }
}
