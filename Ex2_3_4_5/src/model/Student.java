package model;

public class Student {

	private String code;
	private String name;
	private int age;
	private String className;
	private String address;
	private float mark;

	public Student() {
	}

	public Student(String code, String name, int age, String className, String address) {
		this.code = code;
		this.name = name;
		this.age = age;
		this.className = className;
		this.address = address;
	}

	public Student(String code, String name, int age, String className, String address, float mark) {
		this.code = code;
		this.name = name;
		this.age = age;
		this.className = className;
		this.address = address;
		this.mark = mark;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
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

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public void parse(String line,int sl) {
		if(sl==5) {
			String[] param = line.split(", ");
			try {
				code = param[0];
				name = param[1];
				age = Integer.parseInt(param[2]);
				className = param[3];
				address = param[4];
			} catch (ArrayIndexOutOfBoundsException ex) {

			}
		}else {
			String[] param = line.split(", ");
			try {
				code = param[0];
				name = param[1];
				age = Integer.parseInt(param[2]);
				className = param[3];
				address = param[4];
				mark = Float.parseFloat(param[5]);
			} catch (ArrayIndexOutOfBoundsException ex) {

			}
		}

	}

	public String saveGetFileLine(int sl) {
		if (sl == 5) {
			return code + ", " + name + ", " + age + ", " + className + ", " + address +"\n";
		}
		else {
			return code + ", " + name + ", " + age + ", " + className + ", " + address + ", " + mark + "\n";
		}

		
	}

}
