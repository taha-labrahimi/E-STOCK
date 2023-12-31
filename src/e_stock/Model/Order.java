package e_stock.Model;
import java.util.Date;

public class Order {
    private int orderId;
    private Date orderDate;
    private int clientCode;

   
    public Order() {
    }

    public Order(int orderId, Date orderDate, int clientCode) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.clientCode = clientCode;
    }

    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getClientCode() {
        return clientCode;
    }

    public void setClientCode(int clientCode) {
        this.clientCode = clientCode;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", clientCode=" + clientCode +
                '}';
    }
}

