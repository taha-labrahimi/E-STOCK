package e_stock.Model;

public class OrderLine {
    private int orderId;
    private int productCode;
    private int quantityOrdered;
    private double price; 

    public OrderLine(int orderId, int productCode, int quantityOrdered) {
        this.orderId = orderId;
        this.productCode = productCode;
        this.quantityOrdered = quantityOrdered;
    }

    public OrderLine(int orderId, int productCode, int quantityOrdered, double price) {
        this.orderId = orderId;
        this.productCode = productCode;
        this.quantityOrdered = quantityOrdered;
        this.price = price;
    }

    public OrderLine() {
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Additional method to get the total price for this order line
    public double getTotalPrice() {
        return this.quantityOrdered * this.price;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "orderId=" + orderId +
                ", productCode=" + productCode +
                ", quantityOrdered=" + quantityOrdered +
                ", price=" + price +
                '}';
    }
}
