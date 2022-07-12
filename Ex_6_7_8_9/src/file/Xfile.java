package file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import model.Student;

public class Xfile {

	public static Logger logger = Logger.getLogger(Xfile.class.getName());

	public synchronized static List<Student> readFile() {
		String log4jConfPath = "../Ex_6_7_8_9/log4j/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		List<Student> studentsList = new ArrayList<>();
		FileInputStream fis = null;
		InputStreamReader reader = null;
		BufferedReader bufferedReader = null;
		try {
			fis = new FileInputStream("students.txt");
			reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
			bufferedReader = new BufferedReader(reader);

			String line;
			System.out.println("Danh Sách Sinh Viên  Đọc Từ File Là: ");
			while ((line = bufferedReader.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				Student student = new Student();
				student.parse(line);
				studentsList.add(student);
				// System.out.println(line);
			}
			logger.info("Doc Thong Tin Tu File Thanh Cong");

		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return studentsList;

	}

	public synchronized static void writeFile(Student student) {
		FileOutputStream fos = null;
		String line;
		if (student != null) {
			try {
				/*
				 * fos = new FileOutputStream("students.txt",true); k dung true thi se k ghi de
				 * duoc du lieu len file
				 */
				fos = new FileOutputStream("students.txt", true);
				// Lay Du Lieu
				line = student.saveGetFileLine();
				// Chuyen String to Byte[]
				byte[] dataStudent = line.getBytes(StandardCharsets.UTF_8);
				fos.write(dataStudent);
				System.out.println("Ghi File Thành công");
				logger.info("Ghi File Thanh Cong");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			} catch (FileNotFoundException e) {
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						logger.error(e);
					}
				}
			}

		} else {
			System.out.println("Ghi Thất Bại");
			logger.info("Ghi File That Bai");
		}
	}

}