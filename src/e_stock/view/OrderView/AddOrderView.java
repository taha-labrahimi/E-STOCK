/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package e_stock.view.OrderView;

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
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AddOrderView extends javax.swing.JPanel {

    OrderView orderView;
    private ClientRepositoryImpl clientRepositoryImpl;
    private ProductRepositoryImpl productRepositoryImpl;
    private DefaultTableModel tableModel;
    OrderRepositoryImpl orderRepositoryImpl;
    OrderLineRepositoryImpl orderLineRepositoryImpl;
    private int orderid;
    private int currentOrderId = -1;

    Main main;

    public AddOrderView(Main main) {
        this.main = main;
        initComponents();
        orderView = new OrderView(main);
        DatabaseConnector dbConnector = new DatabaseConnector();
        clientRepositoryImpl = new ClientRepositoryImpl(dbConnector);
        productRepositoryImpl = new ProductRepositoryImpl(dbConnector);
        orderLineRepositoryImpl = new OrderLineRepositoryImpl(dbConnector);
        orderRepositoryImpl = new OrderRepositoryImpl(dbConnector);
        populateClientComboBox();
        populateProductComboBox();
    }

    private void populateClientComboBox() {
        List<Client> clients = clientRepositoryImpl.findAll();

        for (Client client : clients) {
            String clientName = client.getFirstName() + " " + client.getLastName();
            clientcombobox.addItem(clientName);
        }
    }

    private void populateProductComboBox() {
        List<Product> products = productRepositoryImpl.findAll();

        for (Product product : products) {
            String productName = product.getProductName();
            productcombobox.addItem(productName);
        }
    }

    private int getClientCodeByName(String clientName) {
        List<Client> clients = clientRepositoryImpl.findAll();
        for (Client client : clients) {
            String fullName = client.getFirstName() + " " + client.getLastName();
            if (fullName.equals(clientName)) {
                return client.getClientCode();
            }
        }
        return -1;
    }

    private int getProductCodeByName(String productName) {
        List<Product> products = productRepositoryImpl.findAll();
        for (Product product : products) {
            if (product.getProductName().equals(productName)) {
                return product.getProductCode();
            }
        }
        return -1;
    }

    private int createNewOrder(int clientCode) {
        Order newOrder = new Order();
        newOrder.setClientCode(clientCode);
        newOrder.setOrderDate(new java.sql.Date(System.currentTimeMillis())); // Set current date as order date

        // Save and get generated order ID
        int orderId = orderRepositoryImpl.save(newOrder);
        newOrder.setOrderId(orderId); // Set the retrieved order ID back to the Order object

        System.out.println(newOrder.getOrderId()); // This should now print the correct ID
        return orderId;
    }

    private void addOrderLine(int orderId, int productCode, int quantity) {
        OrderLine orderLine = new OrderLine();
        orderLine.setOrderId(orderId);
        orderLine.setProductCode(productCode);
        orderLine.setQuantityOrdered(quantity);
        orderLineRepositoryImpl.save(orderLine);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addorderbtn = new javax.swing.JButton();
        clientcombobox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        productcombobox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        addorderbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_add.png"))); // NOI18N
        addorderbtn.setText("ADD");
        addorderbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addorderbtnActionPerformed(evt);
            }
        });

        clientcombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientcomboboxActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana Pro Semibold", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/AddClient_icon.png"))); // NOI18N
        jLabel3.setText("Client :");

        productcombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productcomboboxActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana Pro Semibold", 1, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/icons8-money-64.png"))); // NOI18N
        jLabel4.setText("Total items :");

        jLabel5.setFont(new java.awt.Font("Verdana Pro Semibold", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/icons8-product-64.png"))); // NOI18N
        jLabel5.setText("Product :");

        jLabel1.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/icons8-add-shopping-cart-64.png"))); // NOI18N
        jLabel1.setText("Add Order");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(399, 399, 399)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(clientcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(productcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(335, 335, 335)
                        .addComponent(addorderbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(85, 85, 85))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(productcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(129, 129, 129)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117)
                .addComponent(addorderbtn)
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addorderbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addorderbtnActionPerformed
        try {
            String selectedProductName = productcombobox.getSelectedItem().toString();
            int productCode = getProductCodeByName(selectedProductName);
            int quantity = (Integer) jSpinner1.getValue();
            Product product = productRepositoryImpl.findById(productCode);
            if (product != null && product.getQteStock() >= quantity) {
                if (currentOrderId == -1) {
                    
                    String selectedClientName = clientcombobox.getSelectedItem().toString();
                    int clientCode = getClientCodeByName(selectedClientName);
                    currentOrderId = createNewOrder(clientCode);
                }

                addOrderLine(currentOrderId, productCode, quantity);
                productRepositoryImpl.updateqte(productCode, quantity);
                JOptionPane.showMessageDialog(this, "Produit ajouté à la commande.", "Succès", JOptionPane.INFORMATION_MESSAGE);

                int response = JOptionPane.showConfirmDialog(this, "Voulez-vous ajouter un autre produit à cette commande ?", "Confirmer l'ajout du produit", JOptionPane.YES_NO_OPTION);
                if (response != JOptionPane.YES_OPTION) {
                    currentOrderId = -1;
                    this.main.showForm(new OrderView(this.main));
                } 
            } else {
                JOptionPane.showMessageDialog(this, "Quantité demandée non disponible en stock.", "Avertissement", JOptionPane.WARNING_MESSAGE);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du produit à la commande : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_addorderbtnActionPerformed

    private void clientcomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientcomboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientcomboboxActionPerformed

    private void productcomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productcomboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productcomboboxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addorderbtn;
    private javax.swing.JComboBox<String> clientcombobox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JComboBox<String> productcombobox;
    // End of variables declaration//GEN-END:variables
}
