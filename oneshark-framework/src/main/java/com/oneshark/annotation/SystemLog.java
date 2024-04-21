package com.oneshark.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 注解保持在哪个阶段
@Target({ElementType.METHOD}) // 所定义的注解可以加上哪些东西上面 ---这里是在Controller这个方法上面，说明该方法是受AOP切面增强
public @interface SystemLog {
    // 添加属性，可以指定名字
    String businessName();
}
