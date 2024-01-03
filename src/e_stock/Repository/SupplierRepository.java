package e_stock.Repository;

import e_stock.Model.Supplier;
import java.util.List;

public interface SupplierRepository {
    Supplier findById(int supplierCode);
    List<Supplier> findAll();
    List<Supplier> findByFirstName(String firstName);
    List<Supplier> findByLastName(String lastName);
    //List<Supplier> findByLastName(String lastName);
    void save(Supplier supplier);
    void update(Supplier supplier);
    void delete(int supplierCode);
    
}
