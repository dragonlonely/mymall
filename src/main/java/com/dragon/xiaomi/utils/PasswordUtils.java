package com.dragon.xiaomi.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author Administrator
 * @Date 2018/12/22 9:37
 * @Classname PasswordUtils
 */
public class PasswordUtils {

    /**
     *
     * @param source
     * @param salt
     * @param times
     * @return
     */
    public static String getPasswordMD5(String source, String salt, int times){
        ByteSource byteSource=ByteSource.Util.bytes(salt);
        return new SimpleHash("MD5",source,byteSource,times).toString();
    }


}
