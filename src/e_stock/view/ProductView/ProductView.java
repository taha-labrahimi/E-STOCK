/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package e_stock.view.ProductView;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.main.Main;
import e_stock.Model.ImageRenderer;
import e_stock.Model.Product;
import e_stock.Model.Supplier;
import e_stock.RepositoryImplementation.ProductRepositoryImpl;
import e_stock.RepositoryImplementation.SupplierRepositoryImpl;
import e_stock.database.DatabaseConnector;
import e_stock.view.clientView.AddClientView;
import e_stock.view.clientView.TableGradientCell;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ilyas
 */
public class ProductView extends javax.swing.JPanel {

   private ProductRepositoryImpl productRepository;
    private SupplierRepositoryImpl supplierRepository;
    private AddProductView addProductView;
    private ModifyProductView modifyProductView;
    private DetailsProductView detailsProductView;
    private Main mainFrame;
    public ProductView(Main main) {
        initComponents();
        this.mainFrame = main;
        tableproducts.setDefaultRenderer(Object.class, new TableGradientCell());
        jPanel3.putClientProperty(FlatClientProperties.STYLE, ""+ "border:1,1,1,1,$TableHeader.bottomSeparatorColor,,10");
        tableproducts.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background");
        scroll.putClientProperty(FlatClientProperties.STYLE, ""+ "border:3,0,3,0,$Table.background,10,10");
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,""+"hoverTrackColor:null");

        DatabaseConnector dbConnector = new DatabaseConnector();
        productRepository = new ProductRepositoryImpl(dbConnector);
        supplierRepository = new SupplierRepositoryImpl(dbConnector);
        loadProductAndPopulateTable();
         setUpTableButtons();
    }
    private void setUpTableButtons() {
    Icon editIcon = new ImageIcon(getClass().getResource("/resources/images/icons20.png"));
        Icon deleteIcon = new ImageIcon(getClass().getResource("/resources/images/iconsdelete20.png"));
        Icon ViewIcon = new ImageIcon(getClass().getResource("/resources/images/iconsview20.png"));

    // Assuming the column indices for "Edit" and "Delete" are correct
    TableColumnModel columnModel = tableproducts.getColumnModel();
    columnModel.getColumn(6).setCellRenderer(new ButtonRenderer(editIcon));
    columnModel.getColumn(6).setCellEditor(new ButtonEditor(editIcon, productRepository, modifyProductView, this,mainFrame));
    
    columnModel.getColumn(7).setCellRenderer(new ButtonRenderer(deleteIcon));
    columnModel.getColumn(7).setCellEditor(new ButtonEditor(deleteIcon, productRepository, modifyProductView, this,mainFrame));
    
    columnModel.getColumn(8).setCellRenderer(new ButtonRenderer(ViewIcon));
    columnModel.getColumn(8).setCellEditor(new ButtonEditor(ViewIcon, productRepository, modifyProductView, this,mainFrame));

    // Set the preferred width for the "Edit" and "Delete" columns
    int buttonWidth = new JButton(editIcon).getPreferredSize().width;
    columnModel.getColumn(6).setPreferredWidth(40);
    columnModel.getColumn(6).setMaxWidth(40);
    columnModel.getColumn(7).setPreferredWidth(50);
    columnModel.getColumn(7).setMaxWidth(50);
    columnModel.getColumn(8).setPreferredWidth(40);
    columnModel.getColumn(8).setMaxWidth(40);

    tableproducts.setRowHeight(90);
    }
    protected void loadProductAndPopulateTable() {
        List<Product> products = productRepository.findAll();
        DefaultTableModel tableModel = (DefaultTableModel) tableproducts.getModel();
        String[] columnNames = {"Product Code", "Product Name", "QteStock","Product Unit Price", "Supplier","Image","Edit", "Delete","View"};
        tableModel.setColumnIdentifiers(columnNames);
        tableproducts.setRowHeight(90);
        tableModel.setRowCount(0); // Clear the table before loading new data

        for (Product product : products) {
            Supplier supplier = supplierRepository.findById(product.getSupplierCode());
            String supplierName = (supplier != null) ? supplier.getFirstName() + " " + supplier.getLastName() : "Unknown Supplier";
            byte[] imageBytes = product.getImage();
            ImageIcon image = null;
        if (imageBytes != null) {
            BufferedImage img = null;
                try {
                    img = ImageIO.read(new ByteArrayInputStream(imageBytes));
                } catch (IOException ex) {
                    Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
                }
            Image scaledImg = img.getScaledInstance(90, 110, Image.SCALE_SMOOTH);
            image = new ImageIcon(scaledImg);
        }
            
            tableModel.addRow(new Object[]{
                product.getProductCode(),
                product.getProductName(),
                product.getQteStock(),
                product.getProductUnitPrice(),
                supplierName,
                image,
                "Edit", 
                "Delete",
                "View"
            });
        }
        tableproducts.getColumnModel().getColumn(5).setCellRenderer(new ImageRenderer());
        setUpTableButtons();
        
        
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
                JTableHeader header = tableproducts.getTableHeader();
                g2.translate(0, 0);
                header.print(g2);

                // Print the table. The table is translated below the header.
                g2.translate(0, header.getHeight());
                tableproducts.print(g2);

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
        addProduct = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchtextfield = new javax.swing.JTextField();
        searchbtn = new javax.swing.JButton();
        printbtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tableproducts = new javax.swing.JTable();

        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        addProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iconsadd30.png"))); // NOI18N
        addProduct.setBorder(null);
        addProduct.setContentAreaFilled(false);
        addProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana Pro Cond Black", 1, 48)); // NOI18N
        jLabel1.setText("PRODUCTS");

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

        jPanel3.setLayout(new java.awt.BorderLayout());

        tableproducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scroll.setViewportView(tableproducts);

        jPanel3.add(scroll, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(addProduct)
                .addGap(18, 18, 18)
                .addComponent(printbtn)
                .addGap(644, 644, 644))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 955, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchbtn)
                    .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addProduct)
                    .addComponent(printbtn))
                .addGap(28, 28, 28)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(439, 439, 439))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 78, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        mainFrame.showForm(new AddProductView(mainFrame));
    }//GEN-LAST:event_addProductActionPerformed

    private void searchtextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtextfieldActionPerformed

    private void searchtextfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtextfieldKeyReleased
        String searchText = searchtextfield.getText().trim().toLowerCase();
        if (searchtextfield.getText().trim().isEmpty()) {
            loadProductAndPopulateTable();
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) tableproducts.getModel();
        String[] columnNames = {"Product Code", "Product Name", "QteStock","Product Unit Price", "Supplier","Image","Edit", "Delete","View"};
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.setRowCount(0);
        List<Product> allProducts = productRepository.findAll();

        for (Product product : allProducts) {
            if (product.getProductName().toLowerCase().startsWith(searchText)) {
                Supplier supplier = supplierRepository.findById(product.getSupplierCode());
                String supplierName = (supplier != null) ? supplier.getFirstName() + " " + supplier.getLastName() : "Unknown Supplier";
                byte[] imageBytes = product.getImage();
                ImageIcon image = null;
                if (imageBytes != null) {
                    BufferedImage img = null;
                    try {
                        img = ImageIO.read(new ByteArrayInputStream(imageBytes));
                    } catch (IOException ex) {
                        Logger.getLogger(ProductView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Image scaledImg = img.getScaledInstance(90, 110, Image.SCALE_SMOOTH);
                    image = new ImageIcon(scaledImg);
                }
                tableModel.addRow(new Object[]{
                    String.valueOf(product.getProductCode()),
                    product.getProductName(),
                    product.getQteStock(),
                    product.getProductUnitPrice(),
                    supplierName,
                    image,
                    "Edit",
                    "Delete",
                    "View"
                });

            }
        }
        setUpTableButtons();

        tableproducts.setRowHeight(70);
        tableproducts.getColumnModel().getColumn(5).setCellRenderer(new ImageRenderer());
    }//GEN-LAST:event_searchtextfieldKeyReleased

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed

    }//GEN-LAST:event_searchbtnActionPerformed

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed
        printTable();
    }//GEN-LAST:event_printbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton printbtn;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTextField searchtextfield;
    private javax.swing.JTable tableproducts;
    // End of variables declaration//GEN-END:variables
}
