package e_stock.RepositoryImplementation;

import java.sql.*;
import e_stock.Model.Order;
import e_stock.Model.OrderLine;
import e_stock.Repository.OrderRepository;
import e_stock.database.DatabaseConnector;
import java.util.ArrayList;
import java.util.HashMap;
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
   public List<Order> findAllWithOrderLines() throws ClassNotFoundException {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT o.*, ol.productCode, ol.quantityOrdered, p.productUnitPrice " +
                 "FROM Orders o " +
                 "LEFT JOIN OrderLines ol ON o.orderId = ol.orderId " +
                 "LEFT JOIN Products p ON ol.productCode = p.productCode";
    try (Connection conn = dbConnector.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        HashMap<Integer, Order> orderMap = new HashMap<>();

        while (rs.next()) {
            int orderId = rs.getInt("orderId");
            Order order = orderMap.getOrDefault(orderId, new Order(orderId, rs.getDate("orderDate"), rs.getInt("clientCode")));
            orderMap.putIfAbsent(orderId, order);

            int productCode = rs.getInt("productCode");
            if (!rs.wasNull()) {
                double price = rs.getDouble("productUnitPrice");
                int quantity = rs.getInt("quantityOrdered");
                OrderLine orderLine = new OrderLine(orderId, productCode, quantity, price);
                order.addOrderLine(orderLine);
            }
        }

        orders.addAll(orderMap.values());
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return orders;
}

public int save(Order order) {
    String sql = "INSERT INTO Orders (orderDate, clientCode) VALUES (?, ?)";
    int orderId = 0;

    try (Connection conn = dbConnector.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        pstmt.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
        pstmt.setInt(2, order.getClientCode());
        pstmt.executeUpdate();

        // Retrieve the generated key (order ID)
        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating order failed, no ID obtained.");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(OrderRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
    }

    return orderId;
}




    @Override
    public void update(Order order) {
        String sql = "UPDATE Orders SET clientCode = ? WHERE orderId = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, order.getClientCode());
            pstmt.setInt(2, order.getOrderId());
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

