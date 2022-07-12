package endpoint;

import global.Global;
import org.apache.log4j.PropertyConfigurator;
import thread.ThreadOne;
import ws.StudentWS;

import javax.xml.ws.Endpoint;
import java.io.FileInputStream;
import java.util.Properties;

public class StudentWSPublisher {
    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("./log4j/log4j.properties"));
            PropertyConfigurator.configure(props);
            Global.logger.debug("Main started");
            Thread threadOne = new Thread(new ThreadOne());
            threadOne.start();
            Endpoint.publish(
                    "http://localhost:8080/studentWS",
                    new StudentWS());
        } catch (Exception e) {
            Global.logger.debug(e);
        }
    }
}
