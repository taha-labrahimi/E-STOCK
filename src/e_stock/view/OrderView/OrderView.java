/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package e_stock.view.OrderView;
import com.formdev.flatlaf.FlatClientProperties;
import com.raven.main.Main;
import e_stock.Model.Client;
import e_stock.Model.Order;
import e_stock.Model.OrderLine;
import e_stock.Model.Product;
import e_stock.RepositoryImplementation.ClientRepositoryImpl;
import e_stock.RepositoryImplementation.OrderLineRepositoryImpl;
import e_stock.RepositoryImplementation.OrderRepositoryImpl;
import e_stock.RepositoryImplementation.ProductRepositoryImpl;
import e_stock.database.DatabaseConnector;
import e_stock.view.OrderView.report.ReportManager;
import e_stock.view.clientView.TableGradientCell;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.BasicConfigurator;
public class OrderView extends javax.swing.JPanel {

     AddOrderView addOrderView;
    ModifyOrderView modifyOrderView;
    OrderRepositoryImpl orderRepositoryImpl;
    OrderLineRepositoryImpl orderLineRepositoryImpl;
    ClientRepositoryImpl clientRepositoryImpl;
    ProductRepositoryImpl productRepositoryImpl;
    Main mainFrame;
    public OrderView(Main main) {
        this.mainFrame=main;
        initComponents();
        DatabaseConnector dbConnector = new DatabaseConnector();
        orderRepositoryImpl = new OrderRepositoryImpl(dbConnector);
        orderLineRepositoryImpl = new OrderLineRepositoryImpl(dbConnector);
        clientRepositoryImpl = new ClientRepositoryImpl(dbConnector);
        productRepositoryImpl = new ProductRepositoryImpl(dbConnector);
        tableorder.setDefaultRenderer(Object.class, new TableGradientCell());
        jPanel2.putClientProperty(FlatClientProperties.STYLE, ""+ "border:1,1,1,1,$TableHeader.bottomSeparatorColor,,10");
        tableorder.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background");
        scroll.putClientProperty(FlatClientProperties.STYLE, ""+ "border:3,0,3,0,$Table.background,10,10");
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,""+"hoverTrackColor:null");

        loadOrdersAndPopulateTable();
    }

    protected void loadOrdersAndPopulateTable() {
        try{
            BasicConfigurator.configure();
            ReportManager.getInstance().compileReport();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        try {
            List<Order> orders = orderRepositoryImpl.findAllWithOrderLines();
            DefaultTableModel tableModel = (DefaultTableModel) tableorder.getModel();
            String[] columnNames = {"Order ID", "Order Date", "Client Name", "Product Name","price", "Total Items", "Total Amount","Edit", "Delete", "View"
            };
            tableModel.setColumnIdentifiers(columnNames);
            tableModel.setRowCount(0);

            for (Order order : orders) {
                Client client = clientRepositoryImpl.findById(order.getClientCode());
                String clientName = (client != null) ? client.getFirstName() + " " + client.getLastName() : "Unknown Client";

                for (OrderLine line : order.getOrderLines()) {
                    Product product = productRepositoryImpl.findById(line.getProductCode());
                    String productName = (product != null) ? product.getProductName() : "Unknown Product";

                    int totalItems = line.getQuantityOrdered();
                    double totalAmount =  Double.parseDouble(String.format("%.2f", line.getTotalPrice()));
                    double price = line.getPrice();
                    Object[] row = new Object[]{
                        order.getOrderId(),
                        order.getOrderDate(),
                        clientName,
                        productName,
                        price,
                        totalItems,
                        totalAmount
                    };
                    tableModel.addRow(row);
                }
                setUpTableButtons();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        private void setUpTableButtons() {
        Icon editIcon = new ImageIcon(getClass().getResource("/resources/images/icons20.png"));
        Icon deleteIcon = new ImageIcon(getClass().getResource("/resources/images/iconsdelete20.png"));
        Icon ViewIcon = new ImageIcon(getClass().getResource("/resources/images/iconsview20.png"));

        TableColumnModel columnModel = tableorder.getColumnModel();
        columnModel.getColumn(7).setCellRenderer(new e_stock.view.OrderView.ButtonRenderer(editIcon));
        columnModel.getColumn(7).setCellEditor(new e_stock.view.OrderView.ButtonEditor(editIcon, orderRepositoryImpl,orderLineRepositoryImpl, modifyOrderView, this,mainFrame));

        columnModel.getColumn(8).setCellRenderer(new e_stock.view.OrderView.ButtonRenderer(deleteIcon));
        columnModel.getColumn(8).setCellEditor(new e_stock.view.OrderView.ButtonEditor(deleteIcon, orderRepositoryImpl,orderLineRepositoryImpl, modifyOrderView, this,mainFrame));

        columnModel.getColumn(9).setCellRenderer(new e_stock.view.OrderView.ButtonRenderer(ViewIcon));
        columnModel.getColumn(9).setCellEditor(new e_stock.view.OrderView.ButtonEditor(ViewIcon, orderRepositoryImpl,orderLineRepositoryImpl, modifyOrderView, this,mainFrame));

        int buttonWidth = new JButton(editIcon).getPreferredSize().width;
        columnModel.getColumn(7).setPreferredWidth(40);
        columnModel.getColumn(7).setMaxWidth(40);
        columnModel.getColumn(8).setPreferredWidth(50);
        columnModel.getColumn(8).setMaxWidth(50);
        columnModel.getColumn(9).setPreferredWidth(40);
        columnModel.getColumn(9).setMaxWidth(40);
        tableorder.setRowHeight(40);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        printbtn = new javax.swing.JButton();
        addorder = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchtextfield = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tableorder = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        printbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/2.png"))); // NOI18N
        printbtn.setBorder(null);
        printbtn.setContentAreaFilled(false);
        printbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtnActionPerformed(evt);
            }
        });

        addorder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/1.png"))); // NOI18N
        addorder.setBorder(null);
        addorder.setContentAreaFilled(false);
        addorder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addorderActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel1.setText("ORDERS");

        searchtextfield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        searchtextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtextfieldActionPerformed(evt);
            }
        });
        searchtextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchtextfieldKeyReleased(evt);
            }
        });

        jPanel2.setLayout(new java.awt.BorderLayout());

        tableorder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableorder.setFocusable(false);
        tableorder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableorderMouseClicked(evt);
            }
        });
        scroll.setViewportView(tableorder);

        jPanel2.add(scroll, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(592, 592, 592)
                        .addComponent(addorder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(printbtn))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 957, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(460, 460, 460)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(printbtn)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(addorder))))
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed

    }//GEN-LAST:event_printbtnActionPerformed

    private void tableorderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableorderMouseClicked

    }//GEN-LAST:event_tableorderMouseClicked

    private void addorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addorderActionPerformed
        mainFrame.showForm(new AddOrderView(mainFrame));
    }//GEN-LAST:event_addorderActionPerformed

    private void searchtextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtextfieldActionPerformed

    private void searchtextfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtextfieldKeyReleased
        String searchText = searchtextfield.getText().trim().toLowerCase();
        List<Order> orders = null;
         try {
             orders = orderRepositoryImpl.findAllWithOrderLines();
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(OrderView.class.getName()).log(Level.SEVERE, null, ex);
         }

        DefaultTableModel tableModel = (DefaultTableModel) tableorder.getModel();
        String[] columnNames = {"Order ID", "Order Date", "Client Name", "Product Name","price", "Total Items", "Total Amount","Edit", "Delete", "View"};
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.setRowCount(0);

        for (Order order : orders) {

                Client client = clientRepositoryImpl.findById(order.getClientCode());
                String clientName = (client != null) ? client.getFirstName() + " " + client.getLastName() : "Unknown Client";
                    if (clientName.toLowerCase().startsWith(searchText)) {

                for (OrderLine line : order.getOrderLines()) {
                    Product product = productRepositoryImpl.findById(line.getProductCode());
                    String productName = (product != null) ? product.getProductName() : "Unknown Product";

                    int totalItems = line.getQuantityOrdered();
                    double totalAmount = line.getTotalPrice();
                    double price = line.getPrice();
                    Object[] row = new Object[]{
                        order.getOrderId(),
                        order.getOrderDate(),
                        clientName,
                        productName,
                        price,
                        totalItems,
                        totalAmount
                    };
                    tableModel.addRow(row);
                }
                    }
        setUpTableButtons();
    }//GEN-LAST:event_searchtextfieldKeyReleased

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addorder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton printbtn;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField searchtextfield;
    private javax.swing.JTable tableorder;
    // End of variables declaration//GEN-END:variables
}
