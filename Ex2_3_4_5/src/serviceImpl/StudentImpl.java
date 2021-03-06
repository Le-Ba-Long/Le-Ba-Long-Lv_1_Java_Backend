package serviceImpl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import global.Global;
import model.Student;
import service.StudentService;
import thread.ThreadOne;

@WebService
public class StudentImpl implements StudentService {
	private static final Logger LOGGER = Logger.getLogger(StudentImpl.class.getName());

	@Override
	@WebMethod
	public String getStudent(@WebParam(name = "username") String username, @WebParam(name = "password") String password,
			@WebParam(name = "name") String name, @WebParam(name = "age") Integer age,
			@WebParam(name = "code") String code, @WebParam(name = "className") String className,
			@WebParam(name = "address") String address, @WebParam(name = "mark") Float mark) {
		if (!Global.checkLogin(username, password)) {
			LOGGER.error("Username/password khong dung");
			return "Username/password khong dung";
		} else {

			if (name == null || age == null || code == null || className == null || address == null || mark == null) {
				LOGGER.info("Them student vao queue that bai");
				return "That bai";
			} else {
				Student student = new Student(code, name, age, className, address, mark);
				ThreadOne threadOne = new ThreadOne(student);
				threadOne.start();
				LOGGER.info("Da them student vao queue");
				return "Thanh cong";

			}
		}
	}
}
