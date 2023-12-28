package e_stock.Model;

public class Product {
    private Integer productCode;
    private String productName;
    private float productUnitPrice;
    private byte[] image;
    
    public Product(Integer productCode, String productName, float productUnitPrice, byte[] image) {
        this.productCode = productCode;
        this.productName = productName;
        this.productUnitPrice = productUnitPrice;
        this.image = image;
    }
    public Product(String productName, float productUnitPrice, byte[] image) {
        this.productName = productName;
        this.productUnitPrice = productUnitPrice;
        this.image = image;
    }
    
    // Getters et Setters
    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(float productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}