package e_stock.Repository;

import e_stock.Model.Client;
import java.util.List;

public interface ClientRepository {
    Client findById(int clientCode);
    List<Client> findByFirstName(String firstName);
    List<Client> findByLastName(String lastName);
    List<Client> findAll();
    void save(Client client);
    void update(Client client);
    void delete(int clientCode);
}
