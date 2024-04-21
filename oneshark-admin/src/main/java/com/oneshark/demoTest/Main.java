package com.oneshark.demoTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Alice", 25, 1));
        persons.add(new Person("Bob", 30,2));
        persons.add(new Person("Charlie", 22,3));

        // 筛选
        // 使用 Stream 筛选并映射到 PersonVo 对象列表
        List<PersonVo> personVos = persons.stream()
                .map(person -> new PersonVo(person.getName(), person.getAge()))
                .collect(Collectors.toList());

        // 输出结果
        personVos.forEach(personVo -> System.out.println("Name: " + personVo.getName() + ", Age: " + personVo.getAge()));
        System.out.println(personVos.getClass().getName());
    }
}

class Person {
    private String name;
    private int age;
    private int id;

    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }
}

class PersonVo {
    private String name;
    private int age;

    public PersonVo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
