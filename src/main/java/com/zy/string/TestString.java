package com.zy.string;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * toString() 和 new String()区别
 */
public class TestString {
    public static void main(String[] args) throws Base64DecodingException {
        //表示base64密文
        String str = "5pep5a6J";

        //使用base64解码
        String rlt1 = new String(Base64.decode(str));
        String rlt2 = Base64.decode(str).toString();

        System.out.println(rlt1);
        System.out.println(rlt2);

        //如果在使用编码，进行加密和解密的时候，需要使用new String这种方式

        //str.toString()方法这个方法实际调用的是object里面的toString()方法，返回实际是哈希值

        //new String(),根据参数，参数是一个字节数组，使用java虚拟机默认的编码格式，把这个字节数组
        //进行decode，找到对应的字符，如果虚拟机的编码格式是ISO-8859-1，会去找ASCII里面的编码进行参照
        //找到对应的字符


        //new String()一般进行转码的时候，使用new String()
        //做对象打印的时候，或者想得到地址的时候，使用toString()
    }
}

