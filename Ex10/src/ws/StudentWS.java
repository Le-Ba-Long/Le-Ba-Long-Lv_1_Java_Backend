package ws;

import global.Global;
import model.Student;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebService
public class StudentWS {
	public static volatile Logger logger = Logger.getLogger(StudentWS .class.getName());
    public String addStudentReal(@WebParam(name = "username") String username,
                                 @WebParam(name = "password") String password,
                                 @WebParam(name = "name") String name,
                                 @WebParam(name = "age") Integer age,
                                 @WebParam(name = "code") String code,
                                 @WebParam(name = "className") String className,
                                 @WebParam(name = "address") String address,
                                 @WebParam(name = "mark") Double mark) {
        String result = "That bai";
        if (!Global.auth(username, password)) {
            result = "Username/password khong hop le";
            logger.info("Username/password khong hop le");
        } else {
            if (!checkExistedClass(className)) {
                String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ngoclm.com/\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <ser:addClass>\n" +
                        "         <username>" + username + "</username>\n" +
                        "         <password>" + password + "</password>\n" +
                        "         <name>" + className + "</name>\n" +
                        "         <code>?</code>\n" +
                        "      </ser:addClass>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>";

                if (callSoapService(xml).equals("0")) {
                    Global.student = new Student(code,name,  age, className, address, mark);
                    result = "Thanh cong";
                    logger.info("Thanh Cong");
                } else {
                    result = "Them student that bai do class khong ton tai";
                    logger.info("Them student that bai do class khong ton tai");
                }
            } else {
                Global.student = new Student(code,name,  age, className, address, mark);
                result = "Thanh cong";
                logger.info("Thanh Cong");
            }
        }
        return result;
    }

    private static boolean checkExistedClass(String className) {
        boolean result = false;
        try {
            if (className != null) {
                Global.connection = Global.getMySQLConnection();
                String sql = "SELECT * FROM class WHERE name=(?);";
                PreparedStatement preparedStatement = Global.connection.prepareStatement(sql);
                preparedStatement.setString(1, className);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                    result = true;
                else
                    result = false;
            }
        } catch (SQLException e) {
            Global.logger.error(e);
        }
        return result;
    }

    private static String callSoapService(String soapRequest) {
        try {
            String url = "http://localhost:8080/classwebservice?wsdl";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(soapRequest);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();
            System.out.println(responseStatus);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String finalvalue = response.toString();
            finalvalue = finalvalue.substring(finalvalue.indexOf("<return>") + 8, finalvalue.indexOf("</return>"));
            System.out.println(finalvalue);
            return finalvalue;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
