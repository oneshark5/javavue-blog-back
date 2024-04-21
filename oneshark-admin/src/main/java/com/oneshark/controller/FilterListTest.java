package com.oneshark.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
/**
 * @Description 按条件筛选出List集合中的数据
 * @author 刘鹏博
 * @version v1.
 * @date 2018年8月23日
 */
public class FilterListTest {
 
	/**
	 * @Description 创建四个学生对象并设值，保存在list集合中，模拟数据
	 * @author 刘鹏博
	 * @return List<Student>
	 */
	public static List<Student> stuList() {
		List<Student> stuList = new ArrayList<Student>();
		Student stu1 = new Student();
		stu1.setStuNum(20180823);
		stu1.setName("张三");
		stu1.setAge(20);
		stuList.add(stu1);
 
		Student stu2 = new Student();
		stu2.setStuNum(20180824);
		stu2.setName("李四");
		stu2.setAge(21);
		stuList.add(stu2);
 
		Student stu3 = new Student();
		stu3.setStuNum(20180825);
		stu3.setName("王二");
		stu3.setAge(21);
		stuList.add(stu3);
 
		Student stu4 = new Student();
		stu4.setStuNum(20180826);
		stu4.setName("张五");
		stu4.setAge(22);
		stuList.add(stu4);
		return stuList;
	}
	
	public static void main(String[] args) {
		List<Student> stuList = stuList();
		System.out.println("所创建的集合为：" + stuList);

		// 需要筛选的条件：从stuList中筛选出年龄为21和22的学生
		List<Integer> ageList = new ArrayList<Integer>();
		ageList.add(21);
		ageList.add(22);
 
		// JDK1.8提供了lambda表达式， 可以从stuList中过滤出符合条件的结果。
		// 定义结果集
		List<Student> result = null;
		result = stuList.stream()
				.filter((Student s) -> ageList.contains(s.getAge()))
				.collect(Collectors.toList());
 
		// 打印原有stuList集合中的数据
		System.out.println("原有stuList集合中的数据");
		stuList.forEach((Student s) -> System.out.println(s.getName() + "--->" + s.getAge()));
 
		// 打印过滤筛选后的result结果
		System.out.println("过滤筛选后的result结果");
		result.forEach((Student s) -> System.out.println(s.getName() + "--->" + s.getAge()));

		// 测试筛选集合
		List<Student> res = null;
		// res = stuList.stream()
		// 		.map(stu -> new FilterStu(stu.getName(), stu.getAge()))
		// 		.map(s -> s.getName(),)
		// 		.collect(Collectors.toList());
	}
}