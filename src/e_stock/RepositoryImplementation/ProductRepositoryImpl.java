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
                rs.getInt("QteStock"),
                rs.getFloat("productUnitPrice"),
                rs.getBytes("image"),
                rs.getInt("supplierCode")
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
                rs.getInt("QteStock"),
                rs.getFloat("productUnitPrice"),
                rs.getBytes("image"),
                rs.getInt("supplierCode")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public int getquantity(int productCode) {
        String sql = "SELECT QteStock FROM OrderLines ord,products p WHERE p.ProductCode=ord.productCode and productCode = ?";
        int quantity = 0;
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setInt(1, productCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            quantity = rs.getInt("QteStock"); 
        }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(OrderLineRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        return quantity;
    }
    @Override
    public void save(Product product) {
        String sql = "INSERT INTO products (ProductName, ProductUnitPrice, image,QteStock,supplierCode) VALUES (?, ?, ?,?,?)";
        try (Connection conn = dbConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductName());
            pstmt.setFloat(2, product.getProductUnitPrice());
            pstmt.setBytes(3, product.getImage());
            pstmt.setInt(4, product.getQteStock());
            pstmt.setInt(5, product.getSupplierCode());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE products SET ProductName = ?, ProductUnitPrice = ?, image = ? ,QteStock = ?,supplierCode=? WHERE ProductCode = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, product.getProductName());
            pstmt.setFloat(2, product.getProductUnitPrice());
            pstmt.setBytes(3, product.getImage());
            pstmt.setInt(4, product.getQteStock());
            pstmt.setInt(5, product.getSupplierCode());
            pstmt.setInt(6, product.getProductCode());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateqte(int codeProd,int qte) {
        String sql = "UPDATE products SET QteStock = QteStock-? WHERE ProductCode = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, qte);
            pstmt.setInt(2, codeProd);


            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateqte2(int codeProd,int qte) {
        String sql = "UPDATE products SET QteStock = QteStock+? WHERE ProductCode = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, qte);
            pstmt.setInt(2, codeProd);


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
    public boolean hassupplier(int supplierCode) {
        String sql = "SELECT COUNT(*) FROM products WHERE supplierCode = ?";
        try (Connection conn = dbConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, supplierCode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Optionally handle the exception more gracefully
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}

