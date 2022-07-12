package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ClassWebservice {

	@WebMethod
	public String addClass(String username, String password, String name, String code);
}
