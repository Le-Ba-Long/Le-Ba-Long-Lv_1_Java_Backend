package thread;

import java.util.Scanner;

import org.apache.log4j.Logger;

import file.Xfile;
import global.Global;
import model.Student;

public class ThreadOne extends Thread {
	public static volatile Logger logger = Logger.getLogger(ThreadOne.class.getName());
	public static final Scanner sc = new Scanner(System.in);

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
		logger.info("Them Student Vao Quee");
		Xfile.writeFile(Global.queue.poll(),6);
		logger.info("Ghi Student Tu Quee Vao File");
	}
    //end EX3
}
