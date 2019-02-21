package com.pang.blog.jwt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pang
 * @version V1.0
 * @ClassName: PassToken
 * @Package com.pang.blog.jwt
 * @description: 跳过验证的PassToken
 * @date 2019/2/10 22:15
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}

