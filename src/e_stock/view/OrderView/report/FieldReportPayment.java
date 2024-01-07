/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e_stock.view.OrderView.report;

public class FieldReportPayment {

    public FieldReportPayment(String pname, int qty, double price, double total) {
        this.pname = pname;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }

    public FieldReportPayment() {
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    
    

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

   

    private String pname;
    private int qty;
    private double price;
    private double total;
}
