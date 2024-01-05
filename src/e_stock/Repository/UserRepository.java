package e_stock.Repository;

import e_stock.Model.Product;
import e_stock.Model.User;

public interface UserRepository {
    User login(String username, String password);
    void update(User user);
}
