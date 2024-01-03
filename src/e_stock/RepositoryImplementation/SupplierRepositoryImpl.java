package e_stock.RepositoryImplementation;

import e_stock.Repository.SupplierRepository;
import e_stock.Model.Supplier;
import e_stock.database.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author douae
 */
public class SupplierRepositoryImpl implements SupplierRepository{
    private DatabaseConnector dbConnector;

    public SupplierRepositoryImpl(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

   public Supplier findById(int supplierCode) {
    String sql = "SELECT * FROM suppliers WHERE supplierCode = ?";
    try (Connection conn = dbConnector.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, supplierCode);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Supplier supplier = new Supplier(
                rs.getInt("supplierCode"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("address"),
                rs.getString("city"),
                rs.getString("country"),
                rs.getString("phoneNumber")
            );
            return supplier;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(SupplierRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
    
   
    @Override
     public List<Supplier> findAll() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers";
        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                suppliers.add(new Supplier(
                    rs.getInt("supplierCode"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("country"),
                    rs.getString("phoneNumber")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suppliers;
    }
     @Override
    public void save(Supplier supplier) {
        String sql = "INSERT INTO suppliers (firstName, lastName, address, city, country, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, supplier.getFirstName());
            pstmt.setString(2, supplier.getLastName());
            pstmt.setString(3, supplier.getAddress());
            pstmt.setString(4, supplier.getCity());
            pstmt.setString(5, supplier.getCountry());
            pstmt.setString(6, supplier.getPhoneNumber());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Supplier supplier) {
        String sql = "UPDATE suppliers SET firstName = ?, lastName = ?, address = ?, city = ?, country = ?, phoneNumber = ? WHERE supplierCode = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, supplier.getFirstName());
            pstmt.setString(2, supplier.getLastName());
            pstmt.setString(3, supplier.getAddress());
            pstmt.setString(4, supplier.getCity());
            pstmt.setString(5, supplier.getCountry());
            pstmt.setString(6, supplier.getPhoneNumber());
            pstmt.setInt(7, supplier.getSupplierCode());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int supplierCode) {
        String sql = "DELETE FROM suppliers WHERE supplierCode = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, supplierCode);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public List<Supplier> findByFirstName(String firstName) {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers WHERE firstName = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, firstName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                suppliers.add(new Supplier(
                    rs.getInt("supplierCode"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("country"),
                    rs.getString("phoneNumber")
                ));
            
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return suppliers;
    }
      @Override
    public List<Supplier> findByLastName(String lastName) {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM suppliers WHERE lastName = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, lastName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                suppliers.add(new Supplier(
                    rs.getInt("supplierCode"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("country"),
                    rs.getString("phoneNumber")
                ));
            
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return suppliers;
    }




    

}
