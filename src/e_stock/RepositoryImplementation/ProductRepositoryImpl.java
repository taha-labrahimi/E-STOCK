package e_stock.RepositoryImplementation;

import e_stock.Model.Product;
import e_stock.Repository.ProductRepository;
import e_stock.database.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductRepositoryImpl implements ProductRepository{
    private DatabaseConnector dbConnector;
  
    public ProductRepositoryImpl(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }
    
    public Product findById(int productCode) {
    String sql = "SELECT * FROM products WHERE ProductCode = ?";
    try (Connection conn = dbConnector.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, productCode);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Product product = new Product(
                rs.getInt("productCode"),
                rs.getString("productName"),
                rs.getFloat("productUnitPrice"),
                rs.getBytes("image")
            );
            return product;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("productCode"),
                rs.getString("productName"),
                rs.getFloat("productUnitPrice"),
                rs.getBytes("image")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO products (ProductName, ProductUnitPrice, image) VALUES (?, ?, ?)";
        try (Connection conn = dbConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setFloat(2, product.getProductUnitPrice());
            pstmt.setBytes(3, product.getImage());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE products SET ProductName = ?, ProductUnitPrice = ?, image = ? WHERE ProductCode = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, product.getProductName());
            pstmt.setFloat(2, product.getProductUnitPrice());
            pstmt.setBytes(3, product.getImage());
            pstmt.setInt(4, product.getProductCode());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int ProductCode) {
        String sql = "DELETE FROM products WHERE ProductCode = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, ProductCode);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

