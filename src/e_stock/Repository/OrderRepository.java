package e_stock.Repository;

import e_stock.Model.Order;
import java.util.List;

public interface OrderRepository {
    Order findById(int orderId);
    List<Order> findAll();
    int save(Order order);
    void update(Order order);
    void delete(int orderId);
}
