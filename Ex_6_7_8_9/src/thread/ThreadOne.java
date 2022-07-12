package thread;

import java.util.Scanner;

import org.apache.log4j.Logger;
import global.Global;
import model.Student;

public class ThreadOne extends Thread {
	public static volatile Logger logger = Logger.getLogger(ThreadOne.class.getName());
	public static final Scanner sc = new Scanner(System.in);

	public ThreadOne() {}
	Student student;

	public ThreadOne(Student student) {
		this.student = student;
	}

	@Override
	public void run() {
		Global.queue.add(student);
		logger.info("Them Student Vao Quee");
		Global.insertToDB(Global.queue.poll());
		logger.info("Ghi Student Tu Quee Vao DB");
	}
}
