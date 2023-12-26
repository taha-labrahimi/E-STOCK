package e_stock.view;
import e_stock.Model.Client;
import e_stock.RepositoryImplementation.ClientRepositoryImpl;
import e_stock.database.DatabaseConnector;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClientView extends javax.swing.JFrame {
    private ClientRepositoryImpl clientRepository;
    private AddClientView addClientView;
    private ModifyClientView modifyClientView;
    public ClientView() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null); 
         // Initialize the database connection and repository
        DatabaseConnector dbConnector = new DatabaseConnector();
        clientRepository = new ClientRepositoryImpl(dbConnector);
        loadClientsAndPopulateTable();
        
    }
    
    protected void loadClientsAndPopulateTable() {
    List<Client> clients = clientRepository.findAll();
    DefaultTableModel tableModel = (DefaultTableModel) tableclient.getModel();
    String[] columnNames = {"Client Code", "First Name", "Last Name", "Address", "City", "Country", "Phone Number"};
    tableModel.setColumnIdentifiers(columnNames);

    tableModel.setRowCount(0); // Clear the table before loading new data
    
    for (Client client : clients) {
        tableModel.addRow(new Object[]{
            client.getClientCode(), 
            client.getFirstName(), 
            client.getLastName(),
            client.getAddress(),
            client.getCity(),
            client.getCountry(),
            client.getPhoneNumber()
        });
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableclient = new javax.swing.JTable();
        addclient = new javax.swing.JButton();
        modifyclient = new javax.swing.JButton();
        deleteclient = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchtextfield = new javax.swing.JTextField();
        searchbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tableclient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableclient);

        addclient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/iconsadd30.png"))); // NOI18N
        addclient.setBorder(null);
        addclient.setContentAreaFilled(false);
        addclient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addclientActionPerformed(evt);
            }
        });

        modifyclient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/iconsedit30.png"))); // NOI18N
        modifyclient.setBorder(null);
        modifyclient.setContentAreaFilled(false);
        modifyclient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modifyclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyclientActionPerformed(evt);
            }
        });

        deleteclient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/iconsdelete30.png"))); // NOI18N
        deleteclient.setBorder(null);
        deleteclient.setContentAreaFilled(false);
        deleteclient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteclientActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana Pro Cond Black", 1, 48)); // NOI18N
        jLabel1.setText("CLIENTS");

        searchtextfield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        searchtextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtextfieldActionPerformed(evt);
            }
        });

        searchbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/iconssearch.png"))); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addclient)
                            .addComponent(modifyclient)
                            .addComponent(deleteclient)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(484, 484, 484)
                        .addComponent(jLabel1)))
                .addGap(0, 35, Short.MAX_VALUE))
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
                        .addGap(58, 58, 58)
                        .addComponent(addclient)
                        .addGap(39, 39, 39)
                        .addComponent(modifyclient)
                        .addGap(45, 45, 45)
                        .addComponent(deleteclient)))
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

    private void searchtextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtextfieldActionPerformed

    private void addclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addclientActionPerformed
         if (addClientView == null) {
        addClientView = new AddClientView(); 
    }
        this.setVisible(false);
        addClientView.setVisible(true);
    }//GEN-LAST:event_addclientActionPerformed

    private void modifyclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyclientActionPerformed
        int selectedRowIndex = tableclient.getSelectedRow();
    if (selectedRowIndex != -1) {
        if (modifyClientView == null) {
            modifyClientView = new ModifyClientView();
        }
        modifyClientView.setClientcode(tableclient.getValueAt(selectedRowIndex, 0).toString());
        modifyClientView.setFirstname(tableclient.getValueAt(selectedRowIndex, 1).toString());
        modifyClientView.setLastname(tableclient.getValueAt(selectedRowIndex, 2).toString());
        modifyClientView.setAdresse(tableclient.getValueAt(selectedRowIndex, 3).toString());
        modifyClientView.setCity(tableclient.getValueAt(selectedRowIndex, 4).toString());
        modifyClientView.setCountry(tableclient.getValueAt(selectedRowIndex, 5).toString());
        modifyClientView.setPhonenumber(tableclient.getValueAt(selectedRowIndex, 6).toString());
        this.setVisible(false);
        modifyClientView.setVisible(true);
    } else {
       JOptionPane.showMessageDialog(null, "You should select a row!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_modifyclientActionPerformed

    private void deleteclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteclientActionPerformed
        int selectedRowIndex = tableclient.getSelectedRow();
        if (selectedRowIndex != -1) {
            int deletedclientcode = Integer.parseInt(tableclient.getValueAt(selectedRowIndex, 0).toString());
            clientRepository.delete(deletedclientcode);
            JOptionPane.showMessageDialog(this, "Client deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            
            this.loadClientsAndPopulateTable();
        }
        else {
       JOptionPane.showMessageDialog(null, "You should select a row!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_deleteclientActionPerformed

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
         if (searchtextfield.getText().trim().isEmpty()) {
        loadClientsAndPopulateTable();
        return;
    }

    try {
        int clientCode = Integer.parseInt(searchtextfield.getText().trim());
        Client client = clientRepository.findById(clientCode);
        if (client != null) {
            DefaultTableModel tableModel = (DefaultTableModel) tableclient.getModel();
            String[] columnNames = {"Client Code", "First Name", "Last Name", "Address", "City", "Country", "Phone Number"};
            tableModel.setColumnIdentifiers(columnNames);
            tableModel.setRowCount(0);
            tableModel.addRow(new Object[]{
                String.valueOf(client.getClientCode()),
                client.getFirstName(),
                client.getLastName(),
                client.getAddress(),
                client.getCity(),
                client.getCountry(),
                client.getPhoneNumber()
            });
        } else {
            JOptionPane.showMessageDialog(this, "Client not found", "Search", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter a valid client code", "Search Error", JOptionPane.ERROR_MESSAGE);
    }     
    }//GEN-LAST:event_searchbtnActionPerformed

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
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addclient;
    private javax.swing.JButton deleteclient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifyclient;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTextField searchtextfield;
    private javax.swing.JTable tableclient;
    // End of variables declaration//GEN-END:variables
}
