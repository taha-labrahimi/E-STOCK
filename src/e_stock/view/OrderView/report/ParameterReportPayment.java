/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e_stock.view.OrderView.report;

import java.io.InputStream;
import java.util.List;

/**
 *
 * @author tahav
 */
public class ParameterReportPayment {
        public ParameterReportPayment(String client, String total, InputStream qrcode, List<FieldReportPayment> fields) {
        this.client = client;
        this.total = total;
        this.qrcode = qrcode;
        this.fields = fields;
    }

    public ParameterReportPayment() {
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public InputStream getQrcode() {
        return qrcode;
    }

    public void setQrcode(InputStream qrcode) {
        this.qrcode = qrcode;
    }

    public List<FieldReportPayment> getFields() {
        return fields;
    }

    public void setFields(List<FieldReportPayment> fields) {
        this.fields = fields;
    }

    

    private String client;
    private String total;
    private InputStream qrcode;
    private List<FieldReportPayment> fields;
}
