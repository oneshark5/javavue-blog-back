package com.oneshark;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author bobo
 */
@SpringBootApplication
@MapperScan("com.oneshark.mapper")
public class BlogAdminApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(BlogAdminApplication.class, args);
            System.out.println("博客后台管理系统启动成功！！！");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}