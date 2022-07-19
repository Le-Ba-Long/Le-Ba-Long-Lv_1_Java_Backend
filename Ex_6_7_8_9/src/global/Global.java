package global;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	private static File dbConfig = new File("db.conf");
	private static volatile String username = "";
	private static volatile String password = "";
	private static volatile String mySQLDriver = "";
	private static volatile String conUrl = "";
	private static volatile Connection connection = null;
	private static volatile Logger logger = Logger.getLogger(Global.class.getName());
	private static List<Student> listStudent = new ArrayList<>();
	public static Queue<Student> queue = new PriorityQueue<>();

	public static synchronized Student addStudent() {
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
		System.out.println("Mời Bạn Nhập Điểm : ");
		Float mark = sc.nextFloat();
		if (code != "" && name != "" && className != "" && address != "" && String.valueOf(age) != ""
				&& String.valueOf(mark) != "") {
			student = new Student(code, name, age, className, address, mark);
			System.out.println("Lưu Thành Công");
		} else {
			System.out.println("Lưu Thất Bại");

		}
		return student;

	}

	public static void showListStudents() {
		for (Student students : Xfile.readFile()) {
			System.out.printf("%s, %s, %d, %s, %s, %f", students.getCode(), students.getName(), students.getAge(),
					students.getClassName(), students.getAddress(), students.getMark() + "\n");
		}

	}

	public static boolean checkLogin(String user, String pass) {
		try {
			Scanner scanner = new Scanner(fileConfig);
			if (!scanner.hasNextLine()) {
				logger.info("File khong co thong tin");
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
		} catch (Exception e) {
			logger.info("Loi doc file" + e.getMessage());
		}
		return false;

	}

	public static synchronized boolean readDbConf() {
		boolean result = false;
		try {
			Scanner scanner = new Scanner(dbConfig);
			if (!scanner.hasNextLine()) {
				logger.info("File khong co thong tin");
			} else {
				while (scanner.hasNextLine()) {
					String[] splitted = scanner.nextLine().split("=");
					if (splitted[0].equals("driver"))
						mySQLDriver = splitted[1];
					else if (splitted[0].equals("connection"))
						conUrl = splitted[1] + "?autoRenconnect=true&&useSSL=false";
				}
				scanner.close();
				if (!mySQLDriver.equals("") || !conUrl.equals(""))
					result = true;
			}
		} catch (Exception e) {
			logger.info("Loi doc file" + e.getMessage());
		}
		return result;
	}

	public static synchronized Connection getMySQLConnection() {
		if (readDbConf() && !username.equals("") && !password.equals("")) {
			try {
				Class.forName(mySQLDriver);
				connection = DriverManager.getConnection(conUrl, username, password);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return connection;
	}

	public static synchronized void insertToDB(Student student) {
		PreparedStatement preparedStatement = null;
		connection = getMySQLConnection();
		if (connection != null) {
			try {
				String sql = "insert into student(code,name,age,classname,address,mark) values(?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, student.getCode());
				preparedStatement.setString(2, student.getName());
				preparedStatement.setInt(3, student.getAge());
				preparedStatement.setString(4, student.getClassName());
				preparedStatement.setString(5, student.getAddress());
				preparedStatement.setFloat(6, student.getMark());
				preparedStatement.execute();
				if (preparedStatement.getUpdateCount() > 0) {
					logger.debug("Đã thêm student vào db");
				} else
					logger.error("Thêm student vào db thất bại");
			} catch (SQLException throwables) {
				logger.error("Không thêm được student vào db", throwables);
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
					if (connection != null)
						connection.close();
				} catch (SQLException throwables) {
					logger.error(throwables);
				}
			}
		}
	}

	public static synchronized void insertClassToDB(model.Class c) {
		PreparedStatement preparedStatement = null;
		connection = getMySQLConnection();
		if (connection != null) {
			try {
				String sql = "insert into class(name,code) values(?,?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, c.getName());
				preparedStatement.setString(2, c.getCode());
				preparedStatement.execute();
				if (preparedStatement.getUpdateCount() > 0) {
					logger.info("Da Them Class Vao db");
				} else
					logger.info("Thêm Class vào db thất bại");
			} catch (SQLException throwables) {
				logger.error("Không thêm được Class vào db", throwables);
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
					if (connection != null)
						connection.close();
				} catch (SQLException throwables) {
					logger.error(throwables);
				}
			}
		}
	}
}
