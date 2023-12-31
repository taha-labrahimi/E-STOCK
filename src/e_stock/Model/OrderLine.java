package e_stock.Model;
public class OrderLine {
    private int orderId;
    private int productCode;
    private int quantityOrdered;

    public OrderLine() {
    }

    public OrderLine( int orderId,int productCode, int quantityOrdered) {
        this.orderId = orderId;
        this.productCode = productCode;
        this.quantityOrdered = quantityOrdered;
    }



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

    @Override
    public String toString() {
        return "OrderLine{" +
                ", orderId=" + orderId +
                ", productCode=" + productCode +
                ", quantityOrdered=" + quantityOrdered +
                '}';
    }
}

