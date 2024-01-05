package e_stock.RepositoryImplementation;

import e_stock.Model.User;
import e_stock.Repository.UserRepository;
import e_stock.database.DatabaseConnector;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepositoryImpl implements UserRepository {
    private DatabaseConnector dbConnector;

    public UserRepositoryImpl(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public User login(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?"; // Password should be hashed

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Here you'd hash the password before comparing

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("userID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("userType"),
                        rs.getBytes("image"),
                        rs.getString("email"),
                        rs.getString("firstName"),
                        rs.getString("lastName")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    public void update(User user) {
        String sql = "UPDATE users SET username = ?, password = ? ,userType = ?,image=?,email=?,firstName=?,lastName=? WHERE userID = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getUserType());
            pstmt.setBytes(4, user.getImage());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getFirstName());
            pstmt.setString(7, user.getLastName());
            pstmt.setInt(8, user.getUserID());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

