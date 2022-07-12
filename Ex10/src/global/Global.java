package global;

import model.Student;
import org.apache.log4j.Logger;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Global {
    public static File fileConfig = new File("app.conf");
    public static File dbConfig = new File("db.conf");
    public static volatile Student student = null;
    public static Queue<Student> studentQueue = new ConcurrentLinkedQueue<>();
    public static volatile String username = "";
    public static volatile String password = "";
    public static volatile String mySQLDriver = "";
    public static volatile String conUrl = "";
    public static volatile Connection connection = null;
    public static volatile Logger logger = Logger.getLogger(Global.class.getName());

    public static boolean auth(String _username, String _password) {
        boolean result = false;
        try {
            Scanner scanner = new Scanner(fileConfig);
            if (!scanner.hasNextLine()) {
                System.out.println("File khong co thong tin");
            } else {
                while (scanner.hasNextLine()) {
                    String[] splitted = scanner.nextLine().split("=");
                    if (splitted[0].equals("username"))
                        username = splitted[1];
                    else if (splitted[0].equals("password"))
                        password = splitted[1];
                }
                scanner.close();
            }
            if (_username.equals(username) && _password.equals(password))
                result = true;
        } catch (Exception e) {
            System.out.println("Loi doc file");
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static synchronized boolean readDbConf() {
        boolean result = false;
        try {
            Scanner scanner = new Scanner(dbConfig);
            if (!scanner.hasNextLine()) {
                System.out.println("File khong co thong tin");
            } else {
                while (scanner.hasNextLine()) {
                    String[] splitted = scanner.nextLine().split("=");
                    if (splitted[0].equals("driver"))
                        mySQLDriver = splitted[1];
                    else if (splitted[0].equals("connection"))
                        conUrl = splitted[1];
                }
                scanner.close();
                if (!mySQLDriver.equals("") || !conUrl.equals(""))
                    result = true;
            }
        } catch (Exception e) {
            System.out.println("Loi doc file");
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static synchronized Connection getMySQLConnection() {
        if (readDbConf() && !username.equals("") && !password.equals("")) {
            try {
                Class.forName(mySQLDriver);
                connection = DriverManager.getConnection(conUrl, username, password);
                return connection;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return null;
    }
}
