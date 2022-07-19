package global;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import enumrank.Rank;
import model.Student;

public class Global {
	private static final Scanner sc = new Scanner(System.in);
	public static volatile Logger logger = Logger.getLogger(Global.class.getName());
	private boolean isName = false;
	private boolean isPoint = false;
	private boolean isEmail = false;
	private final double POINT_MIN = 0;
	private final double POINT_MAX = 10;

	private static Pattern pattern;
	private static Matcher matcher;
	// public static final String EMAIL_REGEX =
	// "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
	private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

	private static final List<Student> listStudent = new ArrayList<>();
	static {
		
		//Khởi Tạo Dữ Liệu Cho listStudent
		listStudent.add(new Student("Le Ba Long", 10, "longkubi2k@gmail.com"));
		listStudent.add(new Student("Cao Hai Nam", 7.8, "namkuteo@gmail.com"));
		listStudent.add(new Student("Mai Thanh Trung", 6.2, "trungkhoangsan@gmail.com"));
		listStudent.add(new Student("Hoang Duc Canh", 1, "canhdaukhi@gmail.com"));
		listStudent.add(new Student("Trinh Van Hiep", 2, "hiepgasiudent@gmail.com"));
		listStudent.add(new Student("Le Ba Tho", 9, "thomoc@gmail.com"));
		listStudent.add(new Student("Nguyen Chi Thanh", 8.5, "thanhmoclop@gmail.com"));
		listStudent.add(new Student("Pham Van Dong", 7.7, "donggucong@gmail.com"));
		listStudent.add(new Student("Nguyen Duy Anh", 3, "chuabaxuanhinh@gmail.com"));
		listStudent.add(new Student("Mai Thanh Trung", 6.8, "trungdaumo@gmail.com"));
		listStudent.add(new Student("Mai Trung Duc", 4.5, "duclgbt@gmail.com"));
	}

	// Y1 Nhập Danh Sách Student
	public void inputStudent(String code) {
		String name, email;
		float point;
		name = checkName();
		point = checkPoint("Mời Bạn Nhập Điểm: ");
		email = checkEmail();
		if (code != "") {
			listStudent.set(Integer.parseInt(code), new Student(name, point, email));
		} else {
			listStudent.add(new Student(name, point, email));
		}

	}

	// Y2 In Danh Sách Student
	public void outputStudent() {
		if (listStudent.isEmpty()) {
			System.out.println("Danh Sách Rỗng");
		} else {
			for (Student student : listStudent) {

				System.out.printf("\nCode:%s ,Name: %s ,Point: %.1f ,Email: %s ,Rank: %s ", student.getCode(),
						student.getName(), student.getPoint(), student.getEmail(), setRank(student.getPoint()));

			}
		}

	}

	// Y3 Tìm Kiếm Thông Tin Student Theo Khoảng Điểm Nhập Vào
	public void findByAboutPoint() {
		if (listStudent.isEmpty()) {
			System.out.println("Danh Sách Rỗng");

		} else {
			double pointStar = checkPoint("Mời Bạn Nhập Khoảng Điểm Bắt Đầu: ");
			double pointEnd = checkPoint("Mời Bạn Nhập Khoảng Điểm Kết Thúc: ");
			System.out.println("Thông Tin Sinh Viên Nằm Trong Khoảng (" + pointStar + "->" + pointEnd + ") Là: ");
			for (Student student : listStudent) {
				if (student.getPoint() >= pointStar && student.getPoint() <= pointEnd) {
					printStudent(student);
				}

			}
		}

	}

	// Y4 Tìm Kiếm Student Theo Xếp Loại Học Lực
	public void findByStudentRank() {
		if (listStudent.isEmpty()) {
			System.out.println("Danh Sách Rỗng");

		} else {
			System.out.println("Mời Bạn Nhập Học Lực Của Học Viên Cần Tìm Kiếm: ");
			String rank = sc.nextLine();
			for (Student student : listStudent) {
				if (setRank(student.getPoint()).equalsIgnoreCase(rank)) {
					printStudent(student);

				}

			}
		}

	}

	// Y5 Tìm Kiếm Mã SV Và Cập Nhập Lại Thông Tin Sinh Viên
	public void findCodeAndUpdateStudent() {
		System.out.println("Mời Bạn Nhập Code Cua Student Cần Tìm : ");
		String code = sc.nextLine();
		int codeUpdate = 0;
		System.out.println("Thông Tin Sinh Viên Có Mã Code Là : " + code);
		for (int i = 0; i < listStudent.size(); i++) {
			if (listStudent.get(i).getCode().equalsIgnoreCase(code)) {
				printStudent(listStudent.get(i));
				codeUpdate = i;
			}

		}
		System.out.println();
		inputStudent(String.valueOf(codeUpdate));

	}

	// Y6 Sắp Xếp Student Theo Điểm
	public void sortStudentByPoint(String mes) {
		if (listStudent.isEmpty()) {
			System.out.println("Danh Sách Rỗng");
		} else {
			Collections.sort(listStudent);
			System.out.println(mes);
			// outputStudent();
			for (int i = 0; i < listStudent.size(); i++) {
				printStudent(listStudent.get(i));
			}
		}

	}

	// Y7 Xuất Danh Sách 5 Student Có Điểm Cao Nhất
	public void outputStudentLimitFiveMaxPoint() {
		if (listStudent.isEmpty()) {
			System.out.println("Danh Sách Rỗng");
		} else {
			Collections.sort(listStudent);
			System.out.println("Danh Sach 5 Hoc Vien Co Diem Cao Nhat La: ");
			// outputStudent();
			for (int i = 0; i <5; i++) {
				printStudent(listStudent.get(i));
			}
		}

	}

	// Y8 Tính Điểm Trung Bình Của Tất Cả Student
	public double calculatePointAVG() {
		float avgListStudent = 0f;
		if (listStudent.isEmpty()) {
			System.out.println("Danh Sách Rỗng");
		} else {
			float avgPoint = 0;
			for (Student student : listStudent) {
				avgPoint += student.getPoint();

			}

			avgListStudent = avgPoint / listStudent.size();
			System.out.printf("Điểm Trung Bình Của Tất Cả Sinh Viên Là: %.2f ", avgListStudent);
		}

		return avgListStudent;

	}

	// Y9 Xuất Danh Sách Student Có Điểm Trên Điểm Trung Bình
	public void outputStudentOnPointAVG() {
		if (listStudent.isEmpty()) {
			System.out.println("Danh Sách Rỗng");

		} else {
			System.out.println("Danh Sách Sinh Viên Trên Điểm Trung Bình Của Lớp Là: ");
			for (Student student : listStudent) {
				if (student.getPoint() > calculatePointAVG()) {
					printStudent(student);
				}
			}
		}
	}

	// Y10 Tổng Hợp Student Theo Xếp Loại Học Lực
	public void syntheticStudentGroupbyRank() {
		int countKem = 0;
		int countYeu = 0;
		int countTrungBinh = 0;
		int countKha = 0;
		int countGioi = 0;
		int countXuatSac = 0;
		// String kem =Rank.KEM.getRank();

		for (int i = 0; i < listStudent.size(); i++) {
			
			switch (setRank(listStudent.get(i).getPoint())) {
			case "KEM":
				countKem++;
				break;
			case "YEU":
				countYeu++;
				break;
			case "TRUNG BINH":
				countTrungBinh++;
				break;
			case "KHA":
				countKha++;
				break;
			case "GIOI":
				countGioi++;
				break;
			case "XUAT SAC":
				countXuatSac++;
				break;
			}

		}

		System.out.println("Tổng Số Sinh Viên Xếp Loại Kém La: " + countKem 
				+ "\nTổng Số Sinh Viên Xếp Loại Yếu Là: "+ countYeu 
				+"\nTổng Số Sinh Viên Xếp Loại Trung Bình Là: " + countTrungBinh
				+ "\nTổng Số Sinh Viên Xếp Loại Khá Là: " + countKha 
				+ "\nTổng Số Sinh Viên Xếp Loại Giỏi Là: "+ countGioi 
				+ "\nTổng Số Sinh Viên Xếp Loại Xuất Sắc Là: " + countXuatSac);
	}

	public String setRank(double point) {
		if (point < 3) {
			return Rank.KEM.getRank();
		} else if (point >= 3 && point < 5) {
			return Rank.YEU.getRank();
		} else if (point >= 5 && point < 6.5) {
			return Rank.TRUNG_BINH.getRank();
		} else if (point >= 6.5 && point < 7.5) {
			return Rank.KHA.getRank();
		} else if (point >= 7.5 && point < 9) {
			return Rank.GIOI.getRank();
		} else {
			return Rank.XUAT_SAC.getRank();
		}

//		String rank = (point < 3) ? Rank.KEM.getRank()
//				: (point >= 3 && point < 5) ? Rank.YEU.getRank()
//				: (point >= 5 && point < 6.5) ? Rank.TRUNG_BINH.getRank()
//				: (point >= 6.5 && point < 7.5) ? Rank.KHA.getRank()
//				: (point >= 7.5 && point < 9) ? Rank.GIOI.getRank()
//				: Rank.XUAT_SAC.getRank();
//		return rank;
	}

	public void printStudent(Student student) {
		System.out.printf("\nCode:%s ,Name: %s ,Point: %.1f ,Email: %s ,Rank: %s ", student.getCode(),
				student.getName(), student.getPoint(), student.getEmail(), setRank(student.getPoint()));
	}

	public String checkName() {
		String name;
		do {

			System.out.println("Moi Ban Nhap Ho Ten: ");
			name = sc.nextLine();
			if (name.length() == 0) {
				System.out.println("Ban Chua Nhap Ten Moi Ban Nhap Lai.");
				isName = true;
			} else {
				isName = false;
			}

		} while (isName);
		return name;
	}

	public float checkPoint(String mes) {
		float point = 0;
		do {
			isPoint = false;
			try {
				System.out.println(mes);
				point = sc.nextFloat();
				sc.nextLine();
				if (point < POINT_MIN || point > POINT_MAX) {
					System.out.println("Mời Bạn Nhập Lại Điểm Trong Khoảng(0 -> 10) ");
					isPoint = true;
				}

			} catch (InputMismatchException e) {
				System.out.println("Ban Đã Nhập Sai Định Dạng Dữ Liệu");
				isPoint = true;
				sc.nextLine();
			}
		} while (isPoint);
		return point;
	}

	public String checkEmail() {
		String email;
		do {
			System.out.println("Mời Bạn Nhập Email: ");
			email = sc.nextLine();
			if (email.length() == 0) {
				System.out.println("Bạn Chưa Nhập Email Mời Bạn Nhập Lại.");
				isEmail = true;
			} else if (!isValidEmail(email)) {
				System.out.println("Bạn Chưa Nhập Đúng Định Dạng Email.");
				isEmail = true;
			} else {
				isEmail = false;
			}

		} while (isEmail);
		return email;
	}

	public boolean isValidEmail(String regex) {
		return regex.matches(EMAIL_REGEX);
	}
}