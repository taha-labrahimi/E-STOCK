package e_stock.database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class TestDatabaseConnection {
    public static void main(String[] args) throws SQLException {
        DatabaseConnector dbConnector = new DatabaseConnector();
        try {
            Connection connection = dbConnector.getConnection();
            if (connection != null) {
                System.out.println("Connection to the database was successful!");
                
                // Optional: Execute a test query
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT 1");
                if (rs.next()) {
                    System.out.println("Test query executed successfully.");
                }

                // Close the connection
                connection.close();
            } else {
                System.out.println("Failed to make connection to the database.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL JDBC Driver not found.");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
