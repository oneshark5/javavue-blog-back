package com.oneshark.controller;
 
public class Student {
	private Integer stuNum; // 学号
	private String name; // 姓名
	private Integer age; // 年龄


 
	public Integer getStuNum() {
		return stuNum;
	}
 
	public void setStuNum(Integer stuNum) {
		this.stuNum = stuNum;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public Integer getAge() {
		return age;
	}
 
	public void setAge(Integer age) {
		this.age = age;
	}
 
	@Override
	public String toString() {
		return "Student [stuNum=" + stuNum + ", name=" + name + ", age=" + age + "]";
	}
	
}