/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e_stock.view.OrderView.report;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;



public class ReportManager {

    private static ReportManager instance;

    private JasperReport reportPay;
    private JasperReport reportInvoice;

    public static ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    private ReportManager() {
    }

    public void compileReport() throws JRException {
        reportPay = JasperCompileManager.compileReport(getClass().getResourceAsStream("/e_stock/view/OrderView/report/report1.jrxml"));
        //reportInvoice = JasperCompileManager.compileReport(getClass().getResourceAsStream("/print/report_invoice.jrxml"));
    }

    public void printReportPayment(ParameterReportPayment data) throws JRException {
        Map para = new HashMap();
        para.put("client", data.getClient());
        para.put("total", data.getTotal());
        para.put("qrcode", data.getQrcode());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(reportPay, para, dataSource);
        view(print);
    }

//    public void printReportInvoice(ParameterReportInvoice data) throws JRException {
//        Map para = new HashMap();
//        para.put("totalQty", data.getTotalQty());
//        para.put("totalAmount", data.getTotalAmount());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
//        JasperPrint print = JasperFillManager.fillReport(reportInvoice, para, dataSource);
//        view(print);
//    }

    private void view(JasperPrint print) throws JRException {
        JasperViewer.viewReport(print, false);
    }
}
