package e_stock.view.clientView;

import e_stock.view.clientView.AddClientView;
import e_stock.Model.Client;
import e_stock.RepositoryImplementation.ClientRepositoryImpl;
import e_stock.database.DatabaseConnector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class ClientView extends javax.swing.JFrame {

    private ClientRepositoryImpl clientRepository;
    private AddClientView addClientView;
    private ModifyClientView modifyClientView;

    public ClientView() {
    initComponents();
    setResizable(false);
    setLocationRelativeTo(null);
    DatabaseConnector dbConnector = new DatabaseConnector();
    clientRepository = new ClientRepositoryImpl(dbConnector);
    loadClientsAndPopulateTable();

    Icon editIcon = new ImageIcon(getClass().getResource("/resources/images/icons20.png"));
    Icon deleteIcon = new ImageIcon(getClass().getResource("/resources/images/iconsdelete20.png"));

    // Assuming the column indices for "Edit" and "Delete" are correct
    TableColumnModel columnModel = tableclient.getColumnModel();
    columnModel.getColumn(columnModel.getColumnCount() - 2).setCellRenderer(new ButtonRenderer(editIcon));
    columnModel.getColumn(columnModel.getColumnCount() - 2).setCellEditor(
        new ButtonEditor(editIcon, clientRepository, modifyClientView, this));

    columnModel.getColumn(columnModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer(deleteIcon));
    columnModel.getColumn(columnModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(deleteIcon, clientRepository, modifyClientView, this));

    // Set the preferred width for the "Edit" and "Delete" columns
    int buttonWidth = new JButton(editIcon).getPreferredSize().width;
    columnModel.getColumn(columnModel.getColumnCount() - 2).setPreferredWidth(40);
    columnModel.getColumn(columnModel.getColumnCount() - 2).setMaxWidth(40);
    columnModel.getColumn(columnModel.getColumnCount() - 1).setPreferredWidth(50);
    columnModel.getColumn(columnModel.getColumnCount() - 1).setMaxWidth(50);

    tableclient.setRowHeight(40);  // Adjust row height if necessary
}


    protected void loadClientsAndPopulateTable() {
    List<Client> clients = clientRepository.findAll();
    DefaultTableModel tableModel = (DefaultTableModel) tableclient.getModel();
    String[] columnNames = {
        "Client Code", "First Name", "Last Name", "Address", "City", "Country", "Phone Number", "Edit", "Delete"
    };
    tableModel.setColumnIdentifiers(columnNames);
    tableModel.setRowCount(0); // Clear the table

    for (Client client : clients) {
        tableModel.addRow(new Object[]{
            client.getClientCode(),
            client.getFirstName(),
            client.getLastName(),
            client.getAddress(),
            client.getCity(),
            client.getCountry(),
            client.getPhoneNumber(),
            "Edit", // Instead of null, you can put placeholders or just leave it as is
            "Delete" // Same here
        });
    }

    // Set up button renderers and editors
    Icon editIcon = new ImageIcon(getClass().getResource("/resources/images/icons20.png"));
    Icon deleteIcon = new ImageIcon(getClass().getResource("/resources/images/iconsdelete20.png"));
    TableColumnModel columnModel = tableclient.getColumnModel();
    
    columnModel.getColumn(columnModel.getColumnCount() - 2).setCellRenderer(new ButtonRenderer(editIcon));
    columnModel.getColumn(columnModel.getColumnCount() - 2).setCellEditor(new ButtonEditor(editIcon, clientRepository, modifyClientView, this));

    columnModel.getColumn(columnModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer(deleteIcon));
    columnModel.getColumn(columnModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(deleteIcon, clientRepository, modifyClientView, this));
    int buttonWidth = new JButton(editIcon).getPreferredSize().width;
    columnModel.getColumn(columnModel.getColumnCount() - 2).setPreferredWidth(40);
    columnModel.getColumn(columnModel.getColumnCount() - 2).setMaxWidth(40);
    columnModel.getColumn(columnModel.getColumnCount() - 1).setPreferredWidth(50);
    columnModel.getColumn(columnModel.getColumnCount() - 1).setMaxWidth(50);
    tableclient.setRowHeight(40); // Adjust if necessary
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableclient = new javax.swing.JTable();
        addclient = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchtextfield = new javax.swing.JTextField();
        searchbtn = new javax.swing.JButton();
        printbtn = new javax.swing.JButton();

        jButton1.setText("jButton1");

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

        addclient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iconsadd30.png"))); // NOI18N
        addclient.setBorder(null);
        addclient.setContentAreaFilled(false);
        addclient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addclientActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana Pro Cond Black", 1, 48)); // NOI18N
        jLabel1.setText("Clients");

        searchtextfield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        searchtextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtextfieldActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addclient)
                            .addComponent(printbtn)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(491, 491, 491)
                        .addComponent(jLabel1)))
                .addGap(0, 5, Short.MAX_VALUE))
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
                        .addGap(67, 67, 67)
                        .addComponent(addclient)
                        .addGap(179, 179, 179)
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

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
        String searchText = searchtextfield.getText().trim();

        if (searchText.isEmpty()) {
            loadClientsAndPopulateTable();
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) tableclient.getModel();
        String[] columnNames = {"Supplier Code", "First Name", "Last Name", "Address", "City", "Country", "Phone Number"};
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.setRowCount(0);

        try {
            if (searchText.matches("\\d+")) { // If input is a number, consider it as ID
                int clientCode = Integer.parseInt(searchText);
                Client client = clientRepository.findById(clientCode);
                if (client != null) {
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
            } else { // If input is not a number, consider it as Name or Surname
                List<Client> suppliersByFirstName = clientRepository.findByFirstName(searchText);
                List<Client> suppliersByLastName = clientRepository.findByLastName(searchText);
                List<Client> combinedList = new ArrayList<>();
                combinedList.addAll(suppliersByFirstName);
                combinedList.addAll(suppliersByLastName);

                if (!combinedList.isEmpty()) {
                    for (Client client : combinedList) {
                        tableModel.addRow(new Object[]{
                            String.valueOf(client.getClientCode()),
                            client.getFirstName(),
                            client.getLastName(),
                            client.getAddress(),
                            client.getCity(),
                            client.getCountry(),
                            client.getPhoneNumber()
                        });
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Client not found", "Search", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid client code", "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchbtnActionPerformed

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed
        printTable();
    }//GEN-LAST:event_printbtnActionPerformed
    private void printTable() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");

        job.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.6, 0.6); // Adjust the scaling if needed

                // Print the table header
                JTableHeader header = tableclient.getTableHeader();
                g2.translate(0, 0);
                header.print(g2);

                // Print the table. The table is translated below the header.
                g2.translate(0, header.getHeight());
                tableclient.print(g2);

                return Printable.PAGE_EXISTS;
            }
        });

        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                // Handle the printer exceptions
            }
        }
    }

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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton printbtn;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTextField searchtextfield;
    private javax.swing.JTable tableclient;
    // End of variables declaration//GEN-END:variables
}
