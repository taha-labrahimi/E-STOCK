package e_stock.Model;

public class Product {
    private Integer productCode;
    private String productName;
    private float productUnitPrice;
    private byte[] image;
    private Integer QteStock;
    private Integer supplierCode;

    public void setSupplierCode(Integer supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getSupplierCode() {
        return supplierCode;
    }

    public void setQteStock(Integer QteStock) {
        this.QteStock = QteStock;
    }

    public Integer getQteStock() {
        return QteStock;
    }
    
    public Product(Integer productCode, String productName, int QteStock,float productUnitPrice, byte[] image,int supplier) {
        this.productCode = productCode;
        this.productName = productName;
        this.QteStock = QteStock;
        this.productUnitPrice = productUnitPrice;
        this.image = image;
        this.supplierCode=supplier;
    }
    public Product(String productName, int QteStock, float productUnitPrice, byte[] image,int supplier) {
        this.productName = productName;
        this.QteStock = QteStock;
        this.productUnitPrice = productUnitPrice;
        this.image = image;
        this.supplierCode=supplier;
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
