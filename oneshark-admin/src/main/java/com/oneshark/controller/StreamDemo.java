package com.oneshark.controller;

import java.util.ArrayList;

public class StreamDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("张五");
        list.add("张三三");

        //把所有“张”开头的元素存储到一个新的集合
        ArrayList<String> zhangList = new ArrayList<>();

        for (String s :
                list) {
            if (s.startsWith("张")){
                zhangList.add(s);
            }
        }
        System.out.println(zhangList);

        //把“张”开头的集合中的长度为3的元素存储到一个新的集合
        ArrayList<String> threeList = new ArrayList<>();

        for (String s :
                zhangList) {
            if (s.length()==3){
                threeList.add(s);
            }
        }
        System.out.println(threeList);

        //遍历集合
        for (String s :
                threeList) {
            System.out.println(s);
        }

        System.out.println("------------Stream流------------");

        //Stream流改进
        list.stream()
                .filter(s -> s.startsWith("张"))
                .filter(s -> s.length()==3)
                .forEach(s -> System.out.println(s));
        System.out.println("测试获取某几个元素");

        // 转换成集合1

    }
}
