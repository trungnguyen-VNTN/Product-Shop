package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;

public class ConnectDB {

    private String hostName;
    private String instance;
    private String port;
    private String dbName;
    private String user;
    private String pass;

    public ConnectDB() {
        this.hostName = "localhost";
        this.instance = "";
        this.port = "1433";
        this.dbName = "ProductIntro";
        this.user = "sa";
        this.pass = "123456";
    }

    public ConnectDB(ServletContext sc) {
        this.hostName = sc.getInitParameter("hostAddress");
        this.instance = sc.getInitParameter("instance");
        this.dbName = sc.getInitParameter("bdName");
        this.port = sc.getInitParameter("dbPort");
        this.user = sc.getInitParameter("userName");
        this.pass = sc.getInitParameter("userPass");
    }

    public String getURLString() {
        String fm = "jdbc:sqlserver://%s\\%s:%s;databaseName=%s;user=%s;password=%s;";
        return String.format(fm, this.hostName, this.instance.trim(),
                this.port, this.dbName, this.user, this.pass);
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(getURLString());
    }
}
