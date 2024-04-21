package com.oneshark.runner;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 程序启动之后，所有的bean都初始化完了，再去执行
 */
@Component // 注入 交给spring容器去管理
public class TestRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("测试程序初始化！！！");
    }
}
