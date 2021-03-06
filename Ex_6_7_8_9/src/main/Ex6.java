package main;

import java.io.FileInputStream;
import java.util.Properties;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import serviceImpl.StudentImpl;

public class Ex6 {
	public static volatile Logger logger = Logger.getLogger(Ex6.class.getName());
	private static final String ENDPONT_STUNDENT_SERVICE = "http://localhost:8080/studentWS/addStudentFake";

	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("./log4j/log4j.properties"));
			PropertyConfigurator.configure(props);
			logger.info("Main started");
			Endpoint.publish(ENDPONT_STUNDENT_SERVICE, new StudentImpl());

		} catch (Exception e) {
			logger.error(e);
		}

	}
}
