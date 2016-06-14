package com.csdn.jsonParse;

import java.util.List;

public class Person {

	private String name; // 姓名
	private int age; // 年龄
	private List<String> phones; // 电话号码

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	

	public Person() {
		super();
	}

	public Person(String name, int age, List<String> phones) {
		super();
		this.name = name;
		this.age = age;
		this.phones = phones;
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

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", phones=" + phones
				+ "]";
	}
	
}
