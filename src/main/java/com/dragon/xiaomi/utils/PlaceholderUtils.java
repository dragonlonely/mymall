package com.dragon.xiaomi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @Date 2019/1/19 11:06
 * @Classname PlaceholderUtils
 */
public class PlaceholderUtils {
    private static final Logger logger = LoggerFactory.getLogger(PlaceholderUtils.class);

    /**
     * 占位符前缀: "${"
     */
    public static final String PLACEHOLDER_PREFIX = "${";
    /**
     * 占位符的后缀: "}"
     */
    public static final String PLACEHOLDER_SUFFIX = "}";

    public static String resolvePlaceholders(String text, Map<String, String> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            return text;
        }
        StringBuffer buf = new StringBuffer(text);
        int startIndex = buf.indexOf(PLACEHOLDER_PREFIX);
        while (startIndex != -1) {
            int endIndex = buf.indexOf(PLACEHOLDER_SUFFIX, startIndex + PLACEHOLDER_PREFIX.length());
            if (endIndex != -1) {
                String placeholder = buf.substring(startIndex + PLACEHOLDER_PREFIX.length(), endIndex);
                int nextIndex = endIndex + PLACEHOLDER_SUFFIX.length();
                try {
                    String propVal = parameter.get(placeholder);
                    if (propVal != null) {
                        buf.replace(startIndex, endIndex + PLACEHOLDER_SUFFIX.length(), propVal);
                        nextIndex = startIndex + propVal.length();
                    } else {
                        logger.warn("Could not resolve placeholder '" + placeholder + "' in [" + text + "] ");
                    }
                } catch (Exception ex) {
                    logger.warn("Could not resolve placeholder '" + placeholder + "' in [" + text + "]: " + ex);
                }
                startIndex = buf.indexOf(PLACEHOLDER_PREFIX, nextIndex);
            } else {
                startIndex = -1;
            }
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        String aa= "亲爱的${name},您的验证码为${code},两分钟有效!";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name","小强");
        map.put("code","123456");
        System.out.println(PlaceholderUtils.resolvePlaceholders(aa, map));
    }
}
