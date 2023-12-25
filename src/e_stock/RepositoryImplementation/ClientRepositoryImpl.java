package e_stock.RepositoryImplementation;

import e_stock.Model.Client;
import e_stock.Repository.ClientRepository;
import e_stock.database.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientRepositoryImpl implements ClientRepository {
    private DatabaseConnector dbConnector; 

    public ClientRepositoryImpl(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public Client findById(String clientCode) {
        String sql = "SELECT * FROM clients WHERE clientCode = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, clientCode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Client(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("country"),
                    rs.getString("phoneNumber")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                clients.add(new Client(
                    rs.getInt("clientCode"),
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
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;
    }

    @Override
    public void save(Client client) {
        String sql = "INSERT INTO clients (firstName, lastName, address, city, country, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client.getFirstName());
            pstmt.setString(2, client.getLastName());
            pstmt.setString(3, client.getAddress());
            pstmt.setString(4, client.getCity());
            pstmt.setString(5, client.getCountry());
            pstmt.setString(6, client.getPhoneNumber());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Client client) {
        String sql = "UPDATE clients SET firstName = ?, lastName = ?, address = ?, city = ?, country = ?, phoneNumber = ? WHERE clientCode = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, client.getFirstName());
            pstmt.setString(2, client.getLastName());
            pstmt.setString(3, client.getAddress());
            pstmt.setString(4, client.getCity());
            pstmt.setString(5, client.getCountry());
            pstmt.setString(6, client.getPhoneNumber());
            pstmt.setInt(7, client.getClientCode());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int clientCode) {
        String sql = "DELETE FROM clients WHERE clientCode = ?";
        try (Connection conn = dbConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, clientCode);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
