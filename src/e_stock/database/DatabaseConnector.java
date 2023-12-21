package e_stock.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {
    public Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Xe", "ADMIN", "ADMIN");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
