/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package e_stock.view.clientView;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.raven.main.Main;
import e_stock.view.clientView.AddClientView;
import e_stock.Model.Client;
import e_stock.RepositoryImplementation.ClientRepositoryImpl;
import e_stock.database.DatabaseConnector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ilyas
 */
public class ClientView extends javax.swing.JPanel {

    private ClientRepositoryImpl clientRepository;
    private AddClientView addClientView;
    private ModifyClientView modifyClientView;
    private DetailsClientView detailsClientView;
    private Main mainFrame;

    public ClientView(Main main) {
        initComponents();
        setOpaque(false);
        this.mainFrame = main;
        
        tableclient.setDefaultRenderer(Object.class, new TableGradientCell());
        jPanel2.putClientProperty(FlatClientProperties.STYLE, ""+ "border:1,1,1,1,$TableHeader.bottomSeparatorColor,,10");
        tableclient.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background");
        scroll.putClientProperty(FlatClientProperties.STYLE, ""+ "border:3,0,3,0,$Table.background,10,10");
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,""+"hoverTrackColor:null");
        DatabaseConnector dbConnector = new DatabaseConnector();
        clientRepository = new ClientRepositoryImpl(dbConnector);

        loadClientsAndPopulateTable();
    }

    protected void loadClientsAndPopulateTable() {
        List<Client> clients = clientRepository.findAll();
        DefaultTableModel tableModel = (DefaultTableModel) tableclient.getModel();
        String[] columnNames = {
            "Client Code", "First Name", "Last Name", "Address", "City", "Country", "Phone Number", "Edit", "Delete", "View"
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
                "Edit",
                "Delete",
                "View"
            });
        }

        setUpTableButtons();
    }

    private void setUpTableButtons() {
        Icon editIcon = new ImageIcon(getClass().getResource("/resources/images/icons20.png"));
        Icon deleteIcon = new ImageIcon(getClass().getResource("/resources/images/iconsdelete20.png"));
        Icon ViewIcon = new ImageIcon(getClass().getResource("/resources/images/iconsview20.png"));

        TableColumnModel columnModel = tableclient.getColumnModel();
        columnModel.getColumn(7).setCellRenderer(new ButtonRenderer(editIcon));
        columnModel.getColumn(7).setCellEditor(new ButtonEditor(editIcon, clientRepository, modifyClientView, this,mainFrame));

        columnModel.getColumn(8).setCellRenderer(new ButtonRenderer(deleteIcon));
        columnModel.getColumn(8).setCellEditor(new ButtonEditor(deleteIcon, clientRepository, modifyClientView, this,mainFrame));

        columnModel.getColumn(9).setCellRenderer(new ButtonRenderer(ViewIcon));
        columnModel.getColumn(9).setCellEditor(new ButtonEditor(ViewIcon, clientRepository, modifyClientView, this,mainFrame));

        int buttonWidth = new JButton(editIcon).getPreferredSize().width;
        columnModel.getColumn(7).setPreferredWidth(40);
        columnModel.getColumn(7).setMaxWidth(40);
        columnModel.getColumn(8).setPreferredWidth(50);
        columnModel.getColumn(8).setMaxWidth(50);
        columnModel.getColumn(9).setPreferredWidth(40);
        columnModel.getColumn(9).setMaxWidth(40);
        tableclient.setRowHeight(40);
    }

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addclient = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchtextfield = new javax.swing.JTextField();
        printbtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tableclient = new javax.swing.JTable();

        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        addclient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/1.png"))); // NOI18N
        addclient.setBorder(null);
        addclient.setContentAreaFilled(false);
        addclient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addclientActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel1.setText("CLIENTS");

        searchtextfield.setFont(new java.awt.Font("/resources/fonts/PoppinsBlack.otf", java.awt.Font.PLAIN, 18)
        );
        searchtextfield.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(0, 10, 0, 0))
        );
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

        printbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/2.png"))); // NOI18N
        printbtn.setBorder(null);
        printbtn.setContentAreaFilled(false);
        printbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtnActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.BorderLayout());

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
        tableclient.setFocusable(false);
        scroll.setViewportView(tableclient);

        jPanel2.add(scroll, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addclient)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(printbtn))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(439, 439, 439)
                        .addComponent(jLabel1)))
                .addGap(0, 86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addclient)
                    .addComponent(printbtn)
                    .addComponent(searchtextfield, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addclientActionPerformed
         mainFrame.showForm(new AddClientView(mainFrame));
    }//GEN-LAST:event_addclientActionPerformed

    private void searchtextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtextfieldActionPerformed

    private void searchtextfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtextfieldKeyReleased
        String searchText = searchtextfield.getText().trim().toLowerCase();

        DefaultTableModel tableModel = (DefaultTableModel) tableclient.getModel();
        String[] columnNames = {"Supplier Code", "First Name", "Last Name", "Address", "City", "Country", "Phone Number", "Edit", "Delete", "View"};
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.setRowCount(0);
        List<Client> allClients = clientRepository.findAll();

        for (Client client : allClients) {
            // Modify this condition based on how you want to match the search text
            if (client.getFirstName().toLowerCase().startsWith(searchText) || client.getLastName().toLowerCase().startsWith(searchText)) {
                tableModel.addRow(new Object[]{
                    client.getClientCode(),
                    client.getFirstName(),
                    client.getLastName(),
                    client.getAddress(),
                    client.getCity(),
                    client.getCountry(),
                    client.getPhoneNumber(),
                    "Edit",
                    "Delete",
                    "View"
                });
            }
        }
        setUpTableButtons();
    }//GEN-LAST:event_searchtextfieldKeyReleased

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed
        printTable();
    }//GEN-LAST:event_printbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addclient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton printbtn;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField searchtextfield;
    private javax.swing.JTable tableclient;
    // End of variables declaration//GEN-END:variables
}
