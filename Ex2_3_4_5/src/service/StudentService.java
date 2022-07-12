package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface StudentService {
	
	@WebMethod
	public String getStudent(String username, String pasword, String name, Integer age, String code, String className,
			String address, Float mark);
	

}
