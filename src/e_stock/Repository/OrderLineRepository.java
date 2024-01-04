package e_stock.Repository;

import e_stock.Model.OrderLine;
import java.util.List;

public interface OrderLineRepository {
    OrderLine findById(int orderId, int productCode);
    List<OrderLine> findAll();
    void save(OrderLine orderLine);
    void update(OrderLine orderLine);
    void delete(int orderId, int productCode);
}
