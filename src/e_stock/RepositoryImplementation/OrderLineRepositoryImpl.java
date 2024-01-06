package e_stock.RepositoryImplementation;
import e_stock.Model.Order;
import java.sql.*;
import e_stock.Model.OrderLine;
import e_stock.Repository.OrderLineRepository;
import e_stock.database.DatabaseConnector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderLineRepositoryImpl implements OrderLineRepository{
    
     private DatabaseConnector dbConnector;

    public OrderLineRepositoryImpl(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }
     
   @Override
    public OrderLine findById(int orderId, int productCode) {
        String sql = "SELECT * FROM OrderLines WHERE orderId = ? AND productCode = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, productCode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new OrderLine(
                    rs.getInt("orderId"),
                    rs.getInt("productCode"),
                    rs.getInt("quantityOrdered")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(OrderLineRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }

    @Override
    public List<OrderLine> findAll() {
        List<OrderLine> orderLines = new ArrayList<>();
        String sql = "SELECT * FROM OrderLines";
        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                orderLines.add(new OrderLine(
                    rs.getInt("orderId"),
                    rs.getInt("productCode"),
                    rs.getInt("quantityOrdered")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(OrderLineRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        return orderLines;
    }
    
    @Override
    public void save(OrderLine orderLine) {
        String sql = "INSERT INTO OrderLines (orderId, productCode, quantityOrdered) VALUES (?, ?, ?)";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, orderLine.getOrderId());
            pstmt.setInt(2, orderLine.getProductCode());
            pstmt.setInt(3, orderLine.getQuantityOrdered());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(OrderLineRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void update(OrderLine orderLine) {
        String sql = "UPDATE OrderLines SET quantityOrdered = ? WHERE orderId = ? AND productCode = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, orderLine.getQuantityOrdered());
            pstmt.setInt(2, orderLine.getOrderId());
            pstmt.setInt(3, orderLine.getProductCode());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(OrderLineRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    public void delete(int orderId) {
        String sql = "DELETE FROM OrderLines WHERE orderId = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(OrderLineRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
