package e_stock.Repository;

import e_stock.Model.Product;
import java.util.List;

public interface ProductRepository {
    Product findById(int productCode);
    List<Product> findAll();
    void save(Product product);
    void update(Product product);
    void delete(int productCode);
}
