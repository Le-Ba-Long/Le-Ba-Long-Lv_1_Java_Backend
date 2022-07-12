package main;

import java.io.FileInputStream;
import java.util.Properties;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import serviceImpl.ClassWebserviceImpl;
public class Ex9 {
	 public static volatile Logger logger = Logger.getLogger(Ex9.class.getName());
		public static void main(String[] args) {
			try {
				Properties props = new Properties();
				props.load(new FileInputStream("./log4j/log4j.properties"));
				PropertyConfigurator.configure(props);
				logger.info("Main started");
				Endpoint.publish("http://localhost:8080/classwebservice", new ClassWebserviceImpl());

			} catch (Exception e) {
				logger.error(e);
			}

		}
}
