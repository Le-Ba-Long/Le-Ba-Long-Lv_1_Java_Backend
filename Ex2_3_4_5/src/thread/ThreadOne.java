package thread;

import java.util.Scanner;

import org.apache.log4j.Logger;

import file.Xfile;
import global.Global;
import model.Student;

public class ThreadOne extends Thread {
	private static volatile Logger LOGGER = Logger.getLogger(ThreadOne.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	public ThreadOne() {
	}

	// ý 1 EX3
	Student student;

	public ThreadOne(Student student) {
		this.student = student;
	}

	@Override
	public void run() {
		Global.queue.add(student);
		LOGGER.info("Them Student Vao Quee");
		// 6 là số lượng các thuộc tính được truyền vào contructor
		Xfile.writeFile(Global.queue.poll(), 6);
		LOGGER.info("Ghi Student Tu Quee Vao File");
	}
	// end EX3
}
