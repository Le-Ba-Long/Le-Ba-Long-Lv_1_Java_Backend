package model;

public class Student {
	private String name;
	private String code;
	private Integer age;
	private String className;
	private String address;
	private Double mark;

	public Student() {
	}

	public Student(String code, String name, Integer age, String className, String address, Double mark) {
		this.name = name;
		this.code = code;
		this.age = age;
		this.className = className;
		this.address = address;
		this.mark = mark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getMark() {
		return mark;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}
}
