package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuConnectionMaker implements ConnectionMaker {

    @Value("${db.classname}")
    private String className;
    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(className);
        return DriverManager.getConnection(url, user, password);
    }
}
