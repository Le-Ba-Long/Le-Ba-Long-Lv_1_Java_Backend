package service;

import javax.jws.WebMethod;

public interface ClassWebservice {

	@WebMethod
	public String addClass(String username, String password, String name, String code);
}
