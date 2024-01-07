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
import static e_stock.view.ProductView.ModifyProductView.imageContentStatic;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class ModifyProductView extends javax.swing.JPanel {

    private ProductRepositoryImpl productRepository;
    private ProductView productView;
    private SupplierRepositoryImpl supplierRepository;
    Main main;
    private LOGIN loginView;
    public ModifyProductView(Main main) {
        this.main = main;
        initComponents();
        productView = new ProductView(main);
        NumberFormatter formatterfloat = createNumberFormatterFloat();
        NumberFormatter formatterint= createNumberFormatterInt();
        ProductPrice.setFormatterFactory(new DefaultFormatterFactory(formatterfloat));
        QteTextField.setFormatterFactory(new DefaultFormatterFactory(formatterint));
        for (MouseWheelListener listener : ProductPrice.getMouseWheelListeners()) {
        ProductPrice.removeMouseWheelListener(listener);
        }
        ProductPrice.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            SwingUtilities.invokeLater(() -> {
                ProductPrice.setCaretPosition(ProductPrice.getText().length());
            });
        }
    });

        QteTextField.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            SwingUtilities.invokeLater(() -> {
                QteTextField.setCaretPosition(QteTextField.getText().length());
            });
        }
    });

        productView = new ProductView(main);
        
        
        DatabaseConnector dbConnector = new DatabaseConnector();
        productRepository = new ProductRepositoryImpl(dbConnector);
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
    public void setProductcode(String productcode) {
        this.ProductCode.setText(productcode);
    }

    public String getSupplierCombo() {
        return SupplierCombo.toString();
    }

    public void setSupplierCombo(String Suppliercombo) {
        for (int i = 0; i < SupplierCombo.getItemCount(); i++) {
        if (SupplierCombo.getItemAt(i).equals(Suppliercombo)) {
            SupplierCombo.setSelectedIndex(i);
            break;
        }
    }
    }
    
    public JTextField getProductcode() {
        return ProductCode;
    }
    public JTextField getProductname() {
        return ProductName;
    }

    public void setProductname(String name) {
        this.ProductName.setText(name);
    }

    public JTextField getPrice() {
        return ProductPrice;
    }

    public void setPrice(String price) {
        float priceValue = Float.parseFloat(price);
        ProductPrice.setValue(priceValue);
    }
    
    public JLabel getImage() {
        return ImageLabel;
    }
    public JTextField getQteTextField() {
        return QteTextField;
    }

    public void setQteTextField(String QteTextField) {
        this.QteTextField.setText(QteTextField);
    }

    public void setImage(ImageIcon img) {
        this.ImageLabel.setIcon(img);
    }
    // </editor-fold>                        

    public void resetForm() {
    ProductCode.setText(""); // Clear product code field or set to default value
    ProductName.setText(""); // Clear product name field
    ProductPrice.setText(""); // Clear product price field
    QteTextField.setText(""); // Clear product price field
    ImageLabel.setIcon(null); // Reset image label
    imageContentStatic = null;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ProductName = new javax.swing.JTextField();
        modifyProductbtn = new javax.swing.JButton();
        AddImageBtn = new javax.swing.JButton();
        ImageLabel = new javax.swing.JLabel();
        ProductCode = new javax.swing.JTextField();
        SupplierCombo = new javax.swing.JComboBox<>();
        ProductPrice = new javax.swing.JFormattedTextField();
        QteTextField = new javax.swing.JFormattedTextField();
        aaa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        ProductName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductNameActionPerformed(evt);
            }
        });

        modifyProductbtn.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        modifyProductbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_add.png"))); // NOI18N
        modifyProductbtn.setText("MODIFY");
        modifyProductbtn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 102, 255)));
        modifyProductbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyProductbtnActionPerformed(evt);
            }
        });

        AddImageBtn.setText("Choose Image");
        AddImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddImageBtnActionPerformed(evt);
            }
        });

        ProductCode.setEditable(false);

        aaa.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        aaa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_addimage.png"))); // NOI18N
        aaa.setText("Image : ");

        jLabel2.setFont(new java.awt.Font("Arial", 3, 48)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_addProd.png"))); // NOI18N
        jLabel2.setText("MODIFY PRODUCT");

        jLabel3.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_prodname.png"))); // NOI18N
        jLabel3.setText("Product Name : ");

        jlabel.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        jlabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_price.png"))); // NOI18N
        jlabel.setText("Price :");

        jLabel4.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/icons8-fournisseur-64.png"))); // NOI18N
        jLabel4.setText("Supplier Name : ");

        jlabel1.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        jlabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ic_quantity.png"))); // NOI18N
        jlabel1.setText("Qte en Stock :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SupplierCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(aaa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(AddImageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlabel1)
                        .addGap(18, 18, 18)
                        .addComponent(QteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(ProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(92, 92, 92))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(185, 185, 185)
                                .addComponent(ProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(268, 268, 268))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(modifyProductbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(378, 378, 378))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabel)
                            .addComponent(ProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabel1)
                            .addComponent(QteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(SupplierCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(aaa)
                                .addGap(37, 37, 37)
                                .addComponent(AddImageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(modifyProductbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AddImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddImageBtnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {

                imageContentStatic = Files.readAllBytes(selectedFile.toPath());
                ImageIcon imageIcon = new ImageIcon(imageContentStatic);
                Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageLabel.setIcon(new ImageIcon(image));

            } catch (IOException ex) {
                ex.printStackTrace(); // Gérer l'exception ici
            }
        }
    }//GEN-LAST:event_AddImageBtnActionPerformed

    private void modifyProductbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyProductbtnActionPerformed
        try {

            byte[] imageBytes = null;

            if (ImageLabel.getIcon() != null) {
                ImageIcon imageIconDeLabel = (ImageIcon) ImageLabel.getIcon();
                BufferedImage bufferedImage = new BufferedImage(
                    imageIconDeLabel.getIconWidth(),
                    imageIconDeLabel.getIconHeight(),
                    BufferedImage.TYPE_INT_RGB);
                bufferedImage.createGraphics().drawImage(
                    imageIconDeLabel.getImage(),
                    0,
                    0,
                    null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);
                imageBytes = baos.toByteArray();
            }

            int productCode = Integer.parseInt(ProductCode.getText());

            float productPriceUnit = ((Number)ProductPrice.getValue()).floatValue();
            String productName = ProductName.getText();
            int qte = ((Number)QteTextField.getValue()).intValue();
            String selectedSupplierName = SupplierCombo.getSelectedItem().toString();
            int supplierCode = getSupplierCodeByName(selectedSupplierName);
            Product product = new Product(productCode,productName,qte,productPriceUnit,imageBytes,supplierCode);
            productRepository.update(product);

            JOptionPane.showMessageDialog(this, "product modified successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            if (productView != null) {
                productView = new ProductView(main);
            }
            this.main.showForm(productView);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Product code must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating product: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_modifyProductbtnActionPerformed

    private void ProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductNameActionPerformed
        this.ProductName.setText(null);
    }//GEN-LAST:event_ProductNameActionPerformed
public static byte[] imageContentStatic = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddImageBtn;
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JTextField ProductCode;
    private javax.swing.JTextField ProductName;
    private javax.swing.JFormattedTextField ProductPrice;
    private javax.swing.JFormattedTextField QteTextField;
    private javax.swing.JComboBox<String> SupplierCombo;
    private javax.swing.JLabel aaa;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel jlabel1;
    private javax.swing.JButton modifyProductbtn;
    // End of variables declaration//GEN-END:variables
}
