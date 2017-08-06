package com.earthlyfish.jpa.pojo;


import javax.persistence.*;

@Entity
@Table(name="t_student")
public class Student {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private int age;
	
	private String address;

	public Student(String name, int age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}
	
}
