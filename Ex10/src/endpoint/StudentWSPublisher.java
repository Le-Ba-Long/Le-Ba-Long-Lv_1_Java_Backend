package endpoint;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import thread.ThreadOne;
import ws.StudentWS;

import javax.xml.ws.Endpoint;
import java.io.FileInputStream;
import java.util.Properties;

public class StudentWSPublisher {

	public static void main(String[] args) {
		final String ENDPONT_STUNDENT_SERVICE = "http://localhost:8080/studentWS";
		final Logger logger = Logger.getLogger(StudentWSPublisher.class.getName());

		try {
			Properties props = new Properties();
			props.load(new FileInputStream("./log4j/log4j.properties"));
			PropertyConfigurator.configure(props);
			logger.debug("Main started");
			Thread threadOne = new Thread(new ThreadOne());
			threadOne.start();
			Endpoint.publish(ENDPONT_STUNDENT_SERVICE, new StudentWS());
		} catch (Exception e) {
			logger.debug(e);
		}
	}
}
