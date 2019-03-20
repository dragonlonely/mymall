package com.dragon.xiaomi.utils;


/**
 * Created by jackiechan on 10/16/18/6:59 PM
 *
 * @Author jackiechan
 */
public class StringUtil {

    public static boolean isEmpty(String source) {
        return source == null || "".equalsIgnoreCase(source);
    }

    public static boolean isEqualsIgnoreCase(String s1, String s2) {

        return !isEmpty(s1) && s1.equalsIgnoreCase(s2);
    }

    /**
     * 随机生成盐(字符串)
     */
    public static String getRandomString(int length){
        StringBuffer stringBuffer=new StringBuffer();
        int count=0;
        while(count<=length-1){
            int t= (int) (Math.random()*123);
            if((t>=48 & t<=57) | (t>=65 & t<=90) | (t>=97 & t<=122)){
                stringBuffer.append((char) t);
                count++;
            }
        }
        return stringBuffer.toString();
    }
}
