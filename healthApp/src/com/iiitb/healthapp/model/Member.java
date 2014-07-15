package com.iiitb.healthapp.model;

public class Member {

	private int mid;
	private String name;
	private String age;
	private String sex;
	private String weight;
	private String height;
	private String blood;
	
	
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	
	
	public Member(int mid, String name, String age, String sex, String weight,
			String height, String blood) {
		super();
		this.mid = mid;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.weight = weight;
		this.height = height;
		this.blood = blood;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
	
}
