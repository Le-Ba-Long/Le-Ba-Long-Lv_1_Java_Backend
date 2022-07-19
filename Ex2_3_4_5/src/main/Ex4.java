package main;

import java.io.FileInputStream;
import java.util.Properties;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import serviceImpl.StudentImpl;

public class Ex4 {
	private static final Logger LOGGER = Logger.getLogger(Ex4.class.getName());
	private static final String ENDPONT_STUNDENT_SERVICE = "http://localhost:8080/studentservice/login";

	public static void main(String[] args) {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("./log4j/log4j.properties"));
			PropertyConfigurator.configure(props);
			LOGGER.info("Main started");
			Endpoint.publish(Ex4.ENDPONT_STUNDENT_SERVICE, new StudentImpl());

		} catch (Exception e) {
			LOGGER.error(e);
		}

	}
}
