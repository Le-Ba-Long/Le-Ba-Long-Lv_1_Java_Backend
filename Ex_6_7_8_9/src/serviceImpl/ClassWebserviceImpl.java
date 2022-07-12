package serviceImpl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import global.Global;
import model.Class;
import service.ClassWebservice;

@WebService
public class ClassWebserviceImpl implements ClassWebservice {
	public static volatile Logger logger = Logger.getLogger(ClassWebserviceImpl.class.getName());

	@SuppressWarnings("unused")
	@WebMethod
	@Override
	public String addClass(@WebParam(name = "username") String username, @WebParam(name = "password") String password,
			@WebParam(name = "name") String name, @WebParam(name = "code") String code) {
		if (!Global.checkLogin(username, password)) {
			logger.info("Username/password Không Hợp Lệ");
			return "Username/Password Không Hợp Lệ";
		} else {
			//model.Class c = new Class(name, code);
			if (!name.equals("") && !code.equals("")) {
				Global.insertClassToDB(new Class(name, code));
				logger.info("Da them class vao db");
				return "0";
			} else {
				logger.error("Them class vao db that bai");
				return "1";
			}

		}

	}
}