package e_stock.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {
    public Connection getConnection() throws ClassNotFoundException {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create connection URL for MySQL
            // Note: Change "mydatabase" to your actual database name, and adjust the user and password
            String url = "jdbc:mysql://localhost:3306/gestiondestock?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "admin"; // or your MySQL username
            String password = "admin"; // or your MySQL password

            // Establish connection to MySQL
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}