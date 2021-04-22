package com.zy.kaiser;

/**
 * 凯撒加密-位移法
 */
public class KaiserDemo {
    public static void main(String[] args) {
        //定义原文
        String oldMsg = "Hello World";
        //秘钥
        int key = 3;
        //凯撒加密
        String encryptStr = encryptKaiser(oldMsg, key);
        System.out.println("密文:" + encryptStr);
        String descryptStr = descryptKaiser(key, encryptStr);
        System.out.println("明文:" + descryptStr);
    }

    /**
     * 解密-秘钥破解
     * @param key
     * @param encryptStr
     */
    public static String descryptKaiser(int key, String encryptStr) {
        char[] chars = encryptStr.toCharArray();
        char [] newMsg = new char[chars.length];
        for (int i = 0 ; i < chars.length;i++){
            newMsg[i] = (char)((int)chars[i] - key);
        }
        return new String(newMsg);
    }

    /**
     * 加密
     * @param oldMsg
     * @param key
     * @return
     */
    public static String encryptKaiser(String oldMsg, int key) {
        char[] chars = oldMsg.toCharArray();
        //存放密文
        char [] newMsg = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            newMsg[i] = (char)((int)chars[i] + key);
        }
        return new String(newMsg);
    }
}
