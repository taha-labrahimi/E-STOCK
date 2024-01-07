/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package e_stock.view.supplierView;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.main.Main;
import e_stock.Model.Supplier;
import e_stock.Repository.SupplierRepository;
import e_stock.database.DatabaseConnector;
import e_stock.RepositoryImplementation.SupplierRepositoryImpl;
import e_stock.view.clientView.TableGradientCell;
import e_stock.view.supplierView.AddSupplierView;
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
import javax.swing.JScrollPane;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class SupplierView extends javax.swing.JPanel {

    private SupplierRepositoryImpl supplierRepository;
    private AddSupplierView addSupplierView;
    private ModifySupplierView modifySupplierView;
     private DetailsSupplierView detailsSupplierView;
    Main mainFrame;
    /**
     * Creates new form SupplierView
     */
    public SupplierView(Main main) {
        mainFrame = main;
        initComponents();
        tablesupplier.setDefaultRenderer(Object.class, new TableGradientCell());
        jPanel2.putClientProperty(FlatClientProperties.STYLE, ""+ "border:1,1,1,1,$TableHeader.bottomSeparatorColor,,10");
        tablesupplier.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background");
        scroll.putClientProperty(FlatClientProperties.STYLE, ""+ "border:3,0,3,0,$Table.background,10,10");
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,""+"hoverTrackColor:null");

        DatabaseConnector dbConnector = new DatabaseConnector();
        supplierRepository = new SupplierRepositoryImpl(dbConnector);
        loadSuupliersAndPopulateTable();
    }
    
     protected void loadSuupliersAndPopulateTable() {
        List<Supplier> suppliers = supplierRepository.findAll();
        DefaultTableModel tableModel = (DefaultTableModel) tablesupplier.getModel();
        String[] columnNames = {"Supplier Code", "First Name", "Last Name", "Address", "City", "Country", "Phone Number" ,"Edit", "Delete", "View"};
        tableModel.setColumnIdentifiers(columnNames);

        tableModel.setRowCount(0); // Clear the table before loading new data

        for (Supplier supplier : suppliers) {
            tableModel.addRow(new Object[]{
                supplier.getSupplierCode(),
                supplier.getFirstName(),
                supplier.getLastName(),
                supplier.getAddress(),
                supplier.getCity(),
                supplier.getCountry(),
                supplier.getPhoneNumber(),
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

        TableColumnModel columnModel = tablesupplier.getColumnModel();
        columnModel.getColumn(7).setCellRenderer(new ButtonRenderer(editIcon));
        columnModel.getColumn(7).setCellEditor(new ButtonEditor(editIcon, supplierRepository, modifySupplierView, this,mainFrame));

        columnModel.getColumn(8).setCellRenderer(new ButtonRenderer(deleteIcon));
        columnModel.getColumn(8).setCellEditor(new ButtonEditor(deleteIcon, supplierRepository, modifySupplierView, this,mainFrame));

        columnModel.getColumn(9).setCellRenderer(new ButtonRenderer(ViewIcon));
        columnModel.getColumn(9).setCellEditor(new ButtonEditor(ViewIcon, supplierRepository, modifySupplierView, this,mainFrame));

        int buttonWidth = new JButton(editIcon).getPreferredSize().width;
        columnModel.getColumn(7).setPreferredWidth(40);
        columnModel.getColumn(7).setMaxWidth(40);
        columnModel.getColumn(8).setPreferredWidth(50);
        columnModel.getColumn(8).setMaxWidth(50);
        columnModel.getColumn(9).setPreferredWidth(40);
        columnModel.getColumn(9).setMaxWidth(40);
        tablesupplier.setRowHeight(40);
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
                JTableHeader header = tablesupplier.getTableHeader();
                g2.translate(0, 0);
                header.print(g2);

                // Print the table. The table is translated below the header.
                g2.translate(0, header.getHeight());
                tablesupplier.print(g2);

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
        AddSupplier = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchfield = new javax.swing.JTextField();
        printbtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tablesupplier = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setMaximumSize(new java.awt.Dimension(1121, 666));
        jPanel1.setPreferredSize(new java.awt.Dimension(1794, 716));

        AddSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iconsadd30.png"))); // NOI18N
        AddSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSupplierActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setText("Suppliers");

        searchfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                searchfieldMouseReleased(evt);
            }
        });
        searchfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchfieldActionPerformed(evt);
            }
        });
        searchfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchfieldKeyReleased(evt);
            }
        });

        printbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iconsprint30.png"))); // NOI18N
        printbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtnActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.BorderLayout());

        scroll.setBorder(null);

        tablesupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scroll.setViewportView(tablesupplier);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(360, 360, 360))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(searchfield, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addComponent(printbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(printbtn)
                            .addComponent(AddSupplier))
                        .addGap(18, 18, 18)
                        .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchfield, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(188, 188, 188))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1135, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AddSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSupplierActionPerformed
        mainFrame.showForm(new AddSupplierView(mainFrame));
    }//GEN-LAST:event_AddSupplierActionPerformed

    private void searchfieldMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchfieldMouseReleased

    }//GEN-LAST:event_searchfieldMouseReleased

    private void searchfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchfieldActionPerformed

    }//GEN-LAST:event_searchfieldActionPerformed

    private void searchfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchfieldKeyReleased
        // TODO add your handling code here:
        // TODO add your handling code here:
        String searchText = searchfield.getText().trim().toLowerCase();

        DefaultTableModel tableModel = (DefaultTableModel) tablesupplier.getModel();
        String[] columnNames = {"Supplier Code", "First Name", "Last Name", "Address", "City", "Country", "Phone Number", "Edit", "Delete", "View"};
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.setRowCount(0);
        List<Supplier> allSuppliers = supplierRepository.findAll();

        for (Supplier supplier : allSuppliers) {
            // Modify this condition based on how you want to match the search text
            if (supplier.getFirstName().toLowerCase().startsWith(searchText) || supplier.getLastName().toLowerCase().startsWith(searchText)) {
                tableModel.addRow(new Object[]{
                    supplier.getSupplierCode(),
                    supplier.getFirstName(),
                    supplier.getLastName(),
                    supplier.getAddress(),
                    supplier.getCity(),
                    supplier.getCountry(),
                    supplier.getPhoneNumber(),
                    "Edit",
                    "Delete",
                    "View"
                });
            }
        }
    }//GEN-LAST:event_searchfieldKeyReleased

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed
        // TODO add your handling code here:
        printTable();
    }//GEN-LAST:event_printbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton printbtn;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField searchfield;
    private javax.swing.JTable tablesupplier;
    // End of variables declaration//GEN-END:variables
}
