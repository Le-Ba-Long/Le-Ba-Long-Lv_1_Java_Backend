package model;

public class Student implements Comparable<Student> {

	private String code;
	private String name;
	private double point;
	private String email;
	private static int count = 1;

	public Student() {
	}

	public Student(String name, double point, String email) {
		this.code = "DTC" + String.valueOf(count);
		this.name = name;
		this.point = point;
		this.email = email;
		count++;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	@Override
//	public int compareTo(Student o) {
//
//		return this.code.compareToIgnoreCase(o.code);
//
//	}

	@Override
	public int compareTo(Student o) {
		if (point == o.point)
			return 0;
		else if (point < o.point) {
			return 1;
		} else {
			return -1;
		}
	}

}