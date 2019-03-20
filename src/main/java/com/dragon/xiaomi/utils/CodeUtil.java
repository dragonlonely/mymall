package com.dragon.xiaomi.utils;
/**
 * @author Administrator
 * @Date 2018/11/1 22:33
 * @Classname CodeUtil
 */
public class CodeUtil {

    public static String sms() {
        int n = 0;
        while (n < 100000) {
            n = (int) (Math.random() * 1000000);
        }
        return n+"";
    }

    public static void main(String[] args) {
        String sms = CodeUtil.sms();
        System.out.println(sms);

    }

}
