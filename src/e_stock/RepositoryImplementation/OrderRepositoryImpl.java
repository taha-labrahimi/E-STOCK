package e_stock.RepositoryImplementation;

import java.sql.*;
import e_stock.Model.Order;
import e_stock.Repository.OrderRepository;
import e_stock.database.DatabaseConnector;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderRepositoryImpl implements OrderRepository {
    private DatabaseConnector dbConnector;

    public OrderRepositoryImpl(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public Order findById(int orderId) {
        String sql = "SELECT * FROM Orders WHERE orderId = ?";
        try (Connection conn =  dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Order(
                    rs.getInt("orderId"),
                    rs.getDate("orderDate"),
                    rs.getInt("clientCode")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                orders.add(new Order(
                    rs.getInt("orderId"),
                    rs.getDate("orderDate"),
                    rs.getInt("clientCode")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    @Override
    public void save(Order order) {
        String sql = "INSERT INTO Orders (orderDate, clientCode) VALUES (?, ?)";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
            pstmt.setInt(2, order.getClientCode());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Order order) {
        String sql = "UPDATE Orders SET orderDate = ?, clientCode = ? WHERE orderId = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
            pstmt.setInt(2, order.getClientCode());
            pstmt.setInt(3, order.getOrderId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int orderId) {
        String sql = "DELETE FROM Orders WHERE orderId = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

