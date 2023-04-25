package singleton.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * In this example, the DatabaseConnection class has a private constructor to prevent an instance of the class
 * from being created from outside of it. It also has a static getInstance() method which is used to get the
 * only instance of the class. This method is synchronized to ensure that only one instance is created in a
 * multi-threaded environment.
 *
 * This approach ensures that there is only one database connection throughout the program, which prevents
 * concurrency issues and ensures more efficient resource usage.
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection conn;

    private DatabaseConnection() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydatabase",
                    "username",
                    "password"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}
