package global;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import org.apache.log4j.Logger;

import file.Xfile;
import model.Student;

public class Global {
	private static final Scanner sc = new Scanner(System.in);
    private static File fileConfig = new File("app.conf");
    private static volatile String username = "";
    private static volatile String password = "";
    private static final Logger LOGGER = Logger.getLogger(Global.class.getName());
	private static final List<Student> listStudent = new ArrayList<>();
	public static final Queue<Student> queue = new PriorityQueue<>();
	static {
		// Khởi Tạo Dữ Liệu Cho listStudent 
		listStudent.add(new Student("DTC1", "Le Ba Long", 10, "Lớp CNTT K17G", "Hà Nội"));
		listStudent.add(new Student("DTC2", "Trịnh Văn Hiệp", 5, "Lớp CNTT K17A", "Hà Nội"));
		listStudent.add(new Student("DTC3", "Cao Hải Nam", 7, "Lớp CNTT K17H", "Hà Nội"));
		listStudent.add(new Student("DTC4", "Nguyễn Văn Nam", 2, "Lớp CNTT K17B", "Hà Nội"));
		listStudent.add(new Student("DTC5", "Hoàng Đức Cảnh", 4, "Lớp CNTT K17L", "Hà Nội"));
		listStudent.add(new Student("DTC6", "Nguyễn Chí Thanh", 6, "Lớp CNTT K17I", "Hà Nội"));
		listStudent.add(new Student("DTC7", "Nguyễn Văn Huy", 8, "Lớp CNTT K17K", "Hà Nội"));
		listStudent.add(new Student("DTC8", "Phạm Văn Đông", 1, "Lớp CNTT K17N", "Hà Nội"));
		listStudent.add(new Student("DTC9", "Mai Thành Trung", 5, "Lớp CNTT K17M", "Hà Nội"));
		listStudent.add(new Student("DTC10", "Nguyễn Minh Đức", 3, "Lớp CNTT K17C", "Hà Nội"));
		listStudent.add(new Student("DTC11", "Lê Bá Thọ", 9, "Lớp CNTT K17U", "Hà Nội"));
		
	}

	public static synchronized Student addStudent(int sl) {
		//5 là số lượng  tham số được truyền vào  trong contructor
		if(sl == 5) {
			Student student = null;
			System.out.println("Mời Bạn Nhập Mã Sinh Viên : ");
			String code = sc.nextLine();
			System.out.println("Mời Bạn Nhập Tên Sinh Viên : ");
			String name = sc.nextLine();
			System.out.println("Mời Bạn Nhập Tuổi : ");
			int age = sc.nextInt();
			sc.nextLine();
			System.out.println("Mời Bạn Nhập Tên Lớp Học : ");
			String className = sc.nextLine();
			System.out.println("Mời Bạn Nhập Địa Chỉ : ");
			String address = sc.nextLine();
			if (code != "" && name != "" && className != "" && address != "") {
				  student = new Student(code, name, age, className, address);
				LOGGER.info("Lưu Thành Công");
			} else {
				LOGGER.info("Lưu Thất Bại");

			}
			return  student;
		}
		else {
			Student student = null;
			System.out.println("Mời Bạn Nhập Mã Sinh Viên : ");
			String code = sc.nextLine();
			System.out.println("Mời Bạn Nhập Tên Sinh Viên : ");
			String name = sc.nextLine();
			System.out.println("Mời Bạn Nhập Tuổi : ");
			Integer age = sc.nextInt();
			sc.nextLine();
			System.out.println("Mời Bạn Nhập Tên Lớp Học : ");
			String className = sc.nextLine();
			System.out.println("Mời Bạn Nhập Địa Chỉ : ");
			String address = sc.nextLine();
			System.out.println("Mời Bạn Nhập Điểm : ");
			Float mark = sc.nextFloat();
			if (code != "" && name != "" && className != "" && address != ""&& String.valueOf(age) != "" && String.valueOf(mark)!= "") {
				  student = new Student(code, name, age, className, address,mark);
					LOGGER.info("Lưu Thành Công");
			} else {
				LOGGER.info("Lưu Thất Bại");
			}
			return  student;
		}

	}

	public static void showListStudents(int sl) {
		// sl=5 là số lượng tham số được  truyền vào trong Contructor
		if(sl==5) {
			System.out.printf("%-20s%-20s%-10s%-20s%-20s\n",
					"Mã Sinh Viên",
					"Họ Tên",
					"Tuổi",
					"Lớp Học",
					"Địa Chỉ");
			for (Student students : Xfile.readFile(sl)) {
				System.out.printf("%-20s%-20s%-10d%-20s%-20s",
						students.getCode(),
						students.getName(),
						students.getAge(),
						students.getClassName(),
						students.getAddress() 
						+ "\n");
			}
		}
		else {
			System.out.printf("%-20s%-20s%-10s%-20s%-20s%-10s\n",
					"Mã Sinh Viên",
					"Họ Tên",
					"Tuổi",
					"Lớp Học",
					"Địa Chỉ",
					"Điểm");
			for (Student students : Xfile.readFile(sl)) {
				System.out.printf("%-20s%-20s%-10s%-20s%-20s%-10s\n",
						students.getCode(),
						students.getName(),
						students.getAge(),
						students.getClassName(),
						students.getAddress() ,
						String.valueOf(students.getMark())
						+ "\n");
			}
		}

	}
	
	public static boolean checkLogin(String user , String pass) {
		 try {
	            Scanner scanner = new Scanner(fileConfig);
	            if (!scanner.hasNextLine()) {
	                LOGGER.info("File khong co thong tin");
	            } else {
	                while (scanner.hasNextLine()) {
	                    String[] splitted = scanner.nextLine().split("=");
	                    if (splitted[0].equals("username"))
	                        username = splitted[1];
	                    else if (splitted[0].equals("password"))
	                        password = splitted[1];
	                }
	                scanner.close();
	            }
	            if (user.equals(username) && pass.equals(password))
	                return true;
	        } catch (FileNotFoundException e) {        
	            LOGGER.error("Loi doc file"+e.getMessage());
	        }
	        return false;
		
	}
	

	
}
