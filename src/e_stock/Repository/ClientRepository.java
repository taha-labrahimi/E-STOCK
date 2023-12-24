package e_stock.Repository;

import e_stock.Model.Client;
import java.util.List;

public interface ClientRepository {
    Client findById(String clientCode);
    List<Client> findAll();
    void save(Client client);
    void update(Client client);
    void delete(String clientCode);
}
