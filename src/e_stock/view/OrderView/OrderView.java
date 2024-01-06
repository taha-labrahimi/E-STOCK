package e_stock.view.OrderView;

import e_stock.Model.Client;
import e_stock.Model.Order;
import e_stock.Model.OrderLine;
import e_stock.Model.Product;
import e_stock.RepositoryImplementation.ClientRepositoryImpl;
import e_stock.RepositoryImplementation.OrderLineRepositoryImpl;
import e_stock.RepositoryImplementation.OrderRepositoryImpl;
import e_stock.RepositoryImplementation.ProductRepositoryImpl;
import e_stock.database.DatabaseConnector;
import e_stock.view.ProductView.ProductView;
import e_stock.view.clientView.ClientView;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class OrderView extends javax.swing.JFrame {

    ProductView productView;
    ClientView clientView;
    AddOrderView addOrderView;
    ModifyOrderView modifyOrderView;
    OrderRepositoryImpl orderRepositoryImpl;
    OrderLineRepositoryImpl orderLineRepositoryImpl;
    ClientRepositoryImpl clientRepositoryImpl;
    ProductRepositoryImpl productRepositoryImpl;

    public OrderView() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        DatabaseConnector dbConnector = new DatabaseConnector();
        orderRepositoryImpl = new OrderRepositoryImpl(dbConnector);
        orderLineRepositoryImpl = new OrderLineRepositoryImpl(dbConnector);
        clientRepositoryImpl = new ClientRepositoryImpl(dbConnector);
        productRepositoryImpl = new ProductRepositoryImpl(dbConnector);
        loadOrdersAndPopulateTable();
    }

    protected void loadOrdersAndPopulateTable() {
        try {
            List<Order> orders = orderRepositoryImpl.findAllWithOrderLines();
            DefaultTableModel tableModel = (DefaultTableModel) tableorder.getModel();
            String[] columnNames = {"Order ID", "Order Date", "Client Name", "Product Name", "Total Items", "Total Amount","Edit", "Delete", "View"
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
                    double totalAmount = line.getTotalPrice();

                    Object[] row = new Object[]{
                        order.getOrderId(),
                        order.getOrderDate(),
                        clientName,
                        productName,
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
        columnModel.getColumn(6).setCellRenderer(new e_stock.view.OrderView.ButtonRenderer(editIcon));
        columnModel.getColumn(6).setCellEditor(new e_stock.view.OrderView.ButtonEditor(editIcon, orderRepositoryImpl,orderLineRepositoryImpl, modifyOrderView, this));

        columnModel.getColumn(7).setCellRenderer(new e_stock.view.OrderView.ButtonRenderer(deleteIcon));
        columnModel.getColumn(7).setCellEditor(new e_stock.view.OrderView.ButtonEditor(deleteIcon, orderRepositoryImpl,orderLineRepositoryImpl, modifyOrderView, this));

        columnModel.getColumn(8).setCellRenderer(new e_stock.view.OrderView.ButtonRenderer(ViewIcon));
        columnModel.getColumn(8).setCellEditor(new e_stock.view.OrderView.ButtonEditor(ViewIcon, orderRepositoryImpl,orderLineRepositoryImpl, modifyOrderView, this));

        int buttonWidth = new JButton(editIcon).getPreferredSize().width;
        columnModel.getColumn(6).setPreferredWidth(40);
        columnModel.getColumn(6).setMaxWidth(40);
        columnModel.getColumn(7).setPreferredWidth(50);
        columnModel.getColumn(7).setMaxWidth(50);
        columnModel.getColumn(8).setPreferredWidth(40);
        columnModel.getColumn(8).setMaxWidth(40);
        tableorder.setRowHeight(40);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        searchbtn = new javax.swing.JButton();
        printbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableorder = new javax.swing.JTable();
        addorder = new javax.swing.JButton();
        modifyorder = new javax.swing.JButton();
        deleteorder = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchtextfield = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        searchbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iconssearch.png"))); // NOI18N
        searchbtn.setToolTipText("");
        searchbtn.setBorder(null);
        searchbtn.setBorderPainted(false);
        searchbtn.setContentAreaFilled(false);
        searchbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });

        printbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iconsprint30.png"))); // NOI18N
        printbtn.setBorder(null);
        printbtn.setContentAreaFilled(false);
        printbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtnActionPerformed(evt);
            }
        });

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
        tableorder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableorderMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableorder);

        addorder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iconsadd30.png"))); // NOI18N
        addorder.setBorder(null);
        addorder.setContentAreaFilled(false);
        addorder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addorderActionPerformed(evt);
            }
        });

        modifyorder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iconsedit30.png"))); // NOI18N
        modifyorder.setBorder(null);
        modifyorder.setContentAreaFilled(false);
        modifyorder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modifyorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyorderActionPerformed(evt);
            }
        });

        deleteorder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iconsdelete30.png"))); // NOI18N
        deleteorder.setBorder(null);
        deleteorder.setContentAreaFilled(false);
        deleteorder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteorderActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana Pro Cond Black", 1, 48)); // NOI18N
        jLabel1.setText("Orders");

        searchtextfield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        searchtextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtextfieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(addorder)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteorder)
                            .addComponent(modifyorder)
                            .addComponent(printbtn))
                        .addContainerGap(12, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(484, 484, 484)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchbtn)
                    .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(addorder)
                        .addGap(37, 37, 37)
                        .addComponent(modifyorder)
                        .addGap(44, 44, 44)
                        .addComponent(deleteorder)
                        .addGap(35, 35, 35)
                        .addComponent(printbtn)))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed

    }//GEN-LAST:event_searchbtnActionPerformed

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed
    }//GEN-LAST:event_printbtnActionPerformed

    private void addorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addorderActionPerformed
        if (addOrderView == null) {
            addOrderView = new AddOrderView();
        }
        this.setVisible(false);
        addOrderView.setVisible(true);
    }//GEN-LAST:event_addorderActionPerformed

    private void modifyorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyorderActionPerformed
        int selectedRow = tableorder.getSelectedRow();
        if (modifyOrderView == null) {
            modifyOrderView = new ModifyOrderView();
        }
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tableorder.getModel();
            modifyOrderView.setOrderid((int) model.getValueAt(selectedRow, 0));
            String clientName = model.getValueAt(selectedRow, 2).toString(); // Assuming client name is in the 3rd column
            String productName = model.getValueAt(selectedRow, 3).toString(); // Assuming product name is in the 4th column
            int quantity = Integer.parseInt(model.getValueAt(selectedRow, 4).toString()); // Assuming quantity is in the 5th column

            modifyOrderView.setSelectedOrderDetails(clientName, productName, quantity);
            modifyOrderView.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_modifyorderActionPerformed

    private void deleteorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteorderActionPerformed

    }//GEN-LAST:event_deleteorderActionPerformed

    private void searchtextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtextfieldActionPerformed

    private void tableorderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableorderMouseClicked

    }//GEN-LAST:event_tableorderMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addorder;
    private javax.swing.JButton deleteorder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifyorder;
    private javax.swing.JButton printbtn;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTextField searchtextfield;
    private javax.swing.JTable tableorder;
    // End of variables declaration//GEN-END:variables
}
