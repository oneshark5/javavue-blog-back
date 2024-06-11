package com.oneshark;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.oneshark.mapper")
@EnableScheduling // 启动定时任务
@EnableSwagger2 // 启用Swagger2
public class OneSharkBlogApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(OneSharkBlogApplication.class, args);
            System.out.println("启动成功！"); // 后面换log打印日志
            System.out.println("测试git提交");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
