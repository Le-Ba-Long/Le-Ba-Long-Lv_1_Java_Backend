package service;

import javax.jws.WebMethod;


public interface StudentWS {
	
	@WebMethod
	public String getStudent(String username, String pasword, String name, Integer age, String code, String className,
			String address, Float mark);
	

}
