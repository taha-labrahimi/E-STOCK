/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package e_stock.view.ProductView;

import com.raven.main.Main;
import e_stock.Model.Product;
import e_stock.Model.Supplier;
import e_stock.RepositoryImplementation.ProductRepositoryImpl;
import e_stock.RepositoryImplementation.SupplierRepositoryImpl;
import e_stock.database.DatabaseConnector;
import e_stock.view.LOGIN;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class AddProductView extends javax.swing.JPanel {

    private ProductRepositoryImpl productRepository;
    private SupplierRepositoryImpl supplierRepository;
    private ProductView productView;
    private LOGIN loginView;
    private Main main;
    public AddProductView(Main main) {
        this.main = main;
        setOpaque(false);
        initComponents();
        NumberFormatter formatterfloat = createNumberFormatterFloat();
        NumberFormatter formatterint= createNumberFormatterInt();
        ProductPrice.setFormatterFactory(new DefaultFormatterFactory(formatterfloat));
        QteTextField.setFormatterFactory(new DefaultFormatterFactory(formatterint));
        for (MouseWheelListener listener : ProductPrice.getMouseWheelListeners()) {
        ProductPrice.removeMouseWheelListener(listener);
        }
        productView = new ProductView(main);
        DatabaseConnector dbConnector = new DatabaseConnector();
        productRepository = new ProductRepositoryImpl(dbConnector);
        supplierRepository= new SupplierRepositoryImpl(dbConnector);
        populateProductComboBox();
        
    }
    private NumberFormatter createNumberFormatterFloat() {
    NumberFormat format = NumberFormat.getNumberInstance(Locale.ENGLISH); // Utilise le point comme séparateur décimal
    format.setMinimumFractionDigits(1); // Définir le nombre minimum de chiffres après la virgule
    format.setMaximumFractionDigits(2); // Définir le nombre maximum de chiffres après la virgule
    
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Float.class); // Utilisez Float.class pour les nombres avec des décimales
    formatter.setMinimum(0.0f); // Valeur minimale
    formatter.setMaximum(Float.MAX_VALUE); // Valeur maximale
    formatter.setAllowsInvalid(false); // N'autorise pas les entrées invalides
    formatter.setCommitsOnValidEdit(true); // Valide automatiquement les changements
    formatter.setOverwriteMode(true);
    return formatter;
}
    private NumberFormatter createNumberFormatterInt() {
    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class); // Utilisez Float.class si vous attendez des valeurs décimales
    formatter.setMinimum(0);
    formatter.setMaximum(Integer.MAX_VALUE);
    formatter.setAllowsInvalid(false);
    formatter.setCommitsOnValidEdit(true);
    return formatter;
}
    private void populateProductComboBox() {
        List<Supplier> suppliers = supplierRepository.findAll();

        for (Supplier supplier : suppliers) {
            String supplierName = supplier.getFirstName()+" "+supplier.getLastName();
            SupplierCombo.addItem(supplierName);
        }
    }   
    private int getSupplierCodeByName(String supplierName) {
    List<Supplier> suppliers = supplierRepository.findAll();
    for (Supplier supplier : suppliers) {
        String fullName =supplier.getFirstName()+" "+supplier.getLastName();
        if (fullName.equals(supplierName)) {
            return supplier.getSupplierCode();
        }
    }
    return -1;
}
    public JTextField getProductname() {
        return ProductName;
    }

    public void setProductname(String name) {
        this.ProductName.setText(name);
    }

    public JTextField getQteTextField() {
        return QteTextField;
    }

    public void setQteTextField(String QteTextField) {
        this.QteTextField.setText(QteTextField);
    }
    
    
    public JTextField getPrice() {
        return ProductPrice;
    }

    public void setPrice(String price) {
        this.ProductPrice.setText(price);
    }
   


    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ProductName = new javax.swing.JTextField();
        aaa = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        addclientbtn = new javax.swing.JButton();
        AddImageBtn = new javax.swing.JButton();
        ImageLabel = new javax.swing.JLabel();
        jlabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        SupplierCombo = new javax.swing.JComboBox<>();
        ProductPrice = new javax.swing.JFormattedTextField();
        QteTextField = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_prodname.png"))); // NOI18N
        jLabel3.setText("Product Name : ");

        ProductName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductNameActionPerformed(evt);
            }
        });

        aaa.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        aaa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_addimage.png"))); // NOI18N
        aaa.setText("Image : ");

        jlabel.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        jlabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_price.png"))); // NOI18N
        jlabel.setText("Price :");

        addclientbtn.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        addclientbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_add.png"))); // NOI18N
        addclientbtn.setText("ADD");
        addclientbtn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 102, 255)));
        addclientbtn.setOpaque(false);
        addclientbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addclientbtnActionPerformed(evt);
            }
        });

        AddImageBtn.setText("Choose Image");
        AddImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddImageBtnActionPerformed(evt);
            }
        });

        jlabel1.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        jlabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_quantity.png"))); // NOI18N
        jlabel1.setText("Qte en Stock :");

        jLabel2.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_addProd.png"))); // NOI18N
        jLabel2.setText("  ADD PRODUCT");

        jLabel4.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/icons8-fournisseur-64.png"))); // NOI18N
        jLabel4.setText("Supplier Name : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(AddImageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(42, 42, 42)
                                        .addComponent(SupplierCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(aaa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel3)
                                .addGap(51, 51, 51)
                                .addComponent(ProductName)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlabel1)
                                .addGap(25, 25, 25))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 108, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addclientbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(384, 384, 384))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SupplierCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jlabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(aaa)
                                .addGap(33, 33, 33)
                                .addComponent(AddImageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabel1)
                            .addComponent(QteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(182, 182, 182)))
                .addComponent(addclientbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductNameActionPerformed
        this.ProductName.setText(null);
    }//GEN-LAST:event_ProductNameActionPerformed

    private void addclientbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addclientbtnActionPerformed
        try {
            // Create client and save
            String selectedClientName = SupplierCombo.getSelectedItem().toString();
            int supplierCode = getSupplierCodeByName(selectedClientName);//

            float productPriceUnit = ((Number)ProductPrice.getValue()).floatValue();
            int qte = ((Number)QteTextField.getValue()).intValue();
            Product product = new Product(ProductName.getText(),qte,productPriceUnit,imageContentStatic2,supplierCode);
            productRepository.save(product);
            // Provide feedback
            JOptionPane.showMessageDialog(this, "Product added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Refresh client list in ClientView (if necessary)
            if (productView != null) {
                productView.loadProductAndPopulateTable();
            }

            // Hide this view and show the client list
            if (productView != null) {
                    productView = new ProductView(main);
                }
                this.main.showForm(productView);
        } catch (Exception e) {
            // Handle exception and provide feedback
            JOptionPane.showMessageDialog(this, "Error adding Product: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_addclientbtnActionPerformed
public static byte[] imageContentStatic2 = null;
    private void AddImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddImageBtnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {

                imageContentStatic2 = Files.readAllBytes(selectedFile.toPath());
                ImageIcon imageIcon = new ImageIcon(imageContentStatic2);
                Image image = imageIcon.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
                ImageLabel.setIcon(new ImageIcon(image));

            } catch (IOException ex) {
                ex.printStackTrace(); // Gérer l'exception ici
            }
        }
    }//GEN-LAST:event_AddImageBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddImageBtn;
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JTextField ProductName;
    private javax.swing.JFormattedTextField ProductPrice;
    private javax.swing.JFormattedTextField QteTextField;
    private javax.swing.JComboBox<String> SupplierCombo;
    private javax.swing.JLabel aaa;
    private javax.swing.JButton addclientbtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel jlabel1;
    // End of variables declaration//GEN-END:variables
}
