package jdbcPractice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
    static {
        // Step 1. load and register the Driver //from jdbc 4.x version loading is
        // automated , codes for loading need to be written
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("jdbc driver loaded successfully...");
    }

    private JdbcUtil() {

    }

    public static Connection getJdbcConnection() throws SQLException, FileNotFoundException, IOException {
        // get connection data from properties file
        FileInputStream fis = new FileInputStream("./jdbcPractice/application.properties");
        Properties properties = new Properties();
        properties.load(fis);

        // Step 2. Establish the connection
        String url = properties.getProperty("url");// if same jdk and databse is in the same machine then
        // host need not to be written. if db is stored in
        // default port, port no need not to be written
        String uName = properties.getProperty("userName");
        String pass = properties.getProperty("password");
        Connection connection = DriverManager.getConnection(url, uName, pass);
        System.out.println("connection established successfully...");
        return connection;
    }

    public static void cleanUp(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
