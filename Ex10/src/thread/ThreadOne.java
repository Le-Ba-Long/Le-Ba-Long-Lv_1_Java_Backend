package thread;

import global.Global;
import model.Student;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import static global.Global.connection;
import static global.Global.student;

public class ThreadOne implements Runnable {
	public static volatile Logger logger = Logger.getLogger(ThreadOne.class.getName());

	private static synchronized void addToQueue() {
		if (Global.student != null) {
			Global.studentQueue.add(Global.student);
			logger.debug("Thread 1 đã add student có code " + student.getCode() + " vào queue");
			Global.student = null;
		}
	}

	private static synchronized void insertToDB(Student student) {
		PreparedStatement preparedStatement = null;
		if (connection == null)
			Global.getMySQLConnection();
		if (connection != null) {
			try {
				String sql = "insert into student(code,name,age,classname,address,mark) values(?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, student.getCode());
				preparedStatement.setString(2, student.getName());
				preparedStatement.setInt(3, student.getAge());
				preparedStatement.setString(4, student.getClassName());
				preparedStatement.setString(5, student.getAddress());
				preparedStatement.setDouble(6, student.getMark());
				preparedStatement.execute();
				if (preparedStatement.getUpdateCount() > 0) {
					logger.debug("Đã thêm student có code " + student.getCode() + " vào db");
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

	@Override
	public void run() {
		logger.debug("Thread 1 started");
		do {
			addToQueue();
			Student student = Global.studentQueue.poll();
			if (student != null)
				insertToDB(student);
		} while (true);
	}
}
