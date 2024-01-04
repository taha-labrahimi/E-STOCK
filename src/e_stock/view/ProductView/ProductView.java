package e_stock.view.ProductView;

import e_stock.view.ProductView.AddProductView;
import e_stock.Model.Product;
import e_stock.Model.ImageRenderer;
import e_stock.RepositoryImplementation.ProductRepositoryImpl;
import e_stock.database.DatabaseConnector;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ProductView extends javax.swing.JFrame {

    private ProductRepositoryImpl productRepository;
    private AddProductView addProductView;
    private ModifyProductView modifyProductView;
    private DetailsProductView detailsProductView;

    public ProductView() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        // Initialize the database connection and repository
        DatabaseConnector dbConnector = new DatabaseConnector();
        productRepository = new ProductRepositoryImpl(dbConnector);
        loadProductAndPopulateTable();
    }

    protected void loadProductAndPopulateTable() {
        List<Product> products = productRepository.findAll();
        DefaultTableModel tableModel = (DefaultTableModel) tableproducts.getModel();
        String[] columnNames = {"Product Code", "Product Name", "QteStock","Product Unit Price", "Image"};
        tableModel.setColumnIdentifiers(columnNames);
        tableproducts.setRowHeight(60);
        tableModel.setRowCount(0); // Clear the table before loading new data

        for (Product product : products) {
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
                image
            });
        }
        tableproducts.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());
        
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableproducts = new javax.swing.JTable();
        addProduct = new javax.swing.JButton();
        modifyProduct = new javax.swing.JButton();
        deleteProduct = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchtextfield = new javax.swing.JTextField();
        searchbtn = new javax.swing.JButton();
        printbtn = new javax.swing.JButton();
        detailsbtn = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
        jScrollPane1.setViewportView(tableproducts);

        addProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iconsadd30.png"))); // NOI18N
        addProduct.setBorder(null);
        addProduct.setContentAreaFilled(false);
        addProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });

        modifyProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iconsedit30.png"))); // NOI18N
        modifyProduct.setBorder(null);
        modifyProduct.setContentAreaFilled(false);
        modifyProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modifyProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyProductActionPerformed(evt);
            }
        });

        deleteProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iconsdelete30.png"))); // NOI18N
        deleteProduct.setBorder(null);
        deleteProduct.setContentAreaFilled(false);
        deleteProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductActionPerformed(evt);
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

        detailsbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/detailsicon.png"))); // NOI18N
        detailsbtn.setBorder(null);
        detailsbtn.setContentAreaFilled(false);
        detailsbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        detailsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsbtnActionPerformed(evt);
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
                        .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addProduct)
                            .addComponent(modifyProduct)
                            .addComponent(deleteProduct)
                            .addComponent(printbtn)
                            .addComponent(detailsbtn))
                        .addContainerGap(48, Short.MAX_VALUE))))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchbtn)
                            .addComponent(searchtextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(addProduct)
                        .addGap(32, 32, 32)
                        .addComponent(modifyProduct)
                        .addGap(48, 48, 48)
                        .addComponent(deleteProduct)
                        .addGap(43, 43, 43)
                        .addComponent(printbtn)
                        .addGap(46, 46, 46)
                        .addComponent(detailsbtn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        if (addProductView == null) {
            addProductView = new AddProductView();
        }
        this.setVisible(false);
        addProductView.setVisible(true);
    }//GEN-LAST:event_addProductActionPerformed

    private void modifyProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyProductActionPerformed
        int selectedRowIndex = tableproducts.getSelectedRow();
        if (selectedRowIndex != -1) {
            if (modifyProductView == null) {
                modifyProductView = new ModifyProductView();
                
            }else{
            modifyProductView.resetForm();
            }
            
            modifyProductView.setProductcode(tableproducts.getValueAt(selectedRowIndex, 0).toString());
            modifyProductView.setProductname(tableproducts.getValueAt(selectedRowIndex, 1).toString());
            modifyProductView.setQteTextField(tableproducts.getValueAt(selectedRowIndex, 2).toString());
            modifyProductView.setPrice(tableproducts.getValueAt(selectedRowIndex, 3).toString());
            ImageIcon imageicon = (ImageIcon) tableproducts.getValueAt(selectedRowIndex, 4);
            modifyProductView.setImage(imageicon);
            this.setVisible(false);
            modifyProductView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "You should select a row!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_modifyProductActionPerformed

    private void deleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProductActionPerformed
        int selectedRowIndex = tableproducts.getSelectedRow();
        if (selectedRowIndex != -1) {
            int deletedproductcode = Integer.parseInt(tableproducts.getValueAt(selectedRowIndex, 0).toString());
            productRepository.delete(deletedproductcode);
            JOptionPane.showMessageDialog(this, "Product deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

            this.loadProductAndPopulateTable();
        } else {
            JOptionPane.showMessageDialog(null, "You should select a row!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteProductActionPerformed

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
        if (searchtextfield.getText().trim().isEmpty()) {
            loadProductAndPopulateTable();
            return;
        }

        try {
            int productCode = Integer.parseInt(searchtextfield.getText().trim());
            Product product = productRepository.findById(productCode);
            if (product != null) {
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
                DefaultTableModel tableModel = (DefaultTableModel) tableproducts.getModel();
                String[] columnNames = {"Product Code", "Product Name", "QteStock","Product Unit Price", "Image"};
                tableModel.setColumnIdentifiers(columnNames);
                tableModel.setRowCount(0);
                tableModel.addRow(new Object[]{
                    String.valueOf(product.getProductCode()),
                    product.getProductName(),
                    product.getQteStock(),
                    product.getProductUnitPrice(),
                    image
                });
            } else {
                JOptionPane.showMessageDialog(this, "Product not found", "Search", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Product code", "Search Error", JOptionPane.ERROR_MESSAGE);
        }
        tableproducts.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());
    }//GEN-LAST:event_searchbtnActionPerformed

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed
        printTable();
    }//GEN-LAST:event_printbtnActionPerformed
    
    private void detailsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsbtnActionPerformed
        int selectedRowIndex = tableproducts.getSelectedRow();
        if (selectedRowIndex != -1) {
            if (detailsProductView == null) {
                detailsProductView = new DetailsProductView();
            }
            
            detailsProductView.setProductcode(tableproducts.getValueAt(selectedRowIndex, 0).toString());
            detailsProductView.setProductname(tableproducts.getValueAt(selectedRowIndex, 1).toString());
            detailsProductView.setQteTextField(tableproducts.getValueAt(selectedRowIndex, 2).toString());
            detailsProductView.setPrice(tableproducts.getValueAt(selectedRowIndex, 3).toString());
            ImageIcon imageicon = (ImageIcon) tableproducts.getValueAt(selectedRowIndex, 4);
            detailsProductView.setImage(imageicon);
            this.setVisible(false);
            detailsProductView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "You should select a row!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_detailsbtnActionPerformed
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
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProduct;
    private javax.swing.JButton deleteProduct;
    private javax.swing.JButton detailsbtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifyProduct;
    private javax.swing.JButton printbtn;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTextField searchtextfield;
    private javax.swing.JTable tableproducts;
    // End of variables declaration//GEN-END:variables
}
