package com.dragon.xiaomi.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname LogAnnotation
 * @Description TODO
 * @Date 2019/1/7 19:29
 * @Created by Administrator
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {

    //别名
    String opertionName(); //操作名称 比如 登录
    String operationType(); //操作类型 比如 login

}
