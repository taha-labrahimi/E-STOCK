package e_stock.view.ProductView;


import e_stock.Model.Product;
import e_stock.Model.Supplier;
import e_stock.RepositoryImplementation.ProductRepositoryImpl;
import e_stock.RepositoryImplementation.SupplierRepositoryImpl;
import e_stock.database.DatabaseConnector;
import e_stock.view.LOGIN;
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

public class ModifyProductView extends javax.swing.JFrame {

    private ProductRepositoryImpl productRepository;
    private ProductView productView;
    private SupplierRepositoryImpl supplierRepository;

    private LOGIN loginView;
    public ModifyProductView() {
        initComponents();
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

        productView = new ProductView();
        setResizable(false);
        setLocationRelativeTo(null);
        
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
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ProductName = new javax.swing.JTextField();
        aaa = new javax.swing.JLabel();
        jlabel = new javax.swing.JLabel();
        modifyProductbtn = new javax.swing.JButton();
        AddImageBtn = new javax.swing.JButton();
        ImageLabel = new javax.swing.JLabel();
        jlabel1 = new javax.swing.JLabel();
        exitbtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        ProductCode = new javax.swing.JTextField();
        SupplierCombo = new javax.swing.JComboBox<>();
        ProductPrice = new javax.swing.JFormattedTextField();
        QteTextField = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        deconnecte = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        UserLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Verdana Pro Cond Black", 1, 48)); // NOI18N
        jLabel1.setText("CLIENTS");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/prodname.png"))); // NOI18N
        jLabel3.setText("Product Name : ");

        ProductName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ProductName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductNameActionPerformed(evt);
            }
        });

        aaa.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        aaa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ADDIMAGE.png"))); // NOI18N
        aaa.setText("Image : ");

        jlabel.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        jlabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/price.png"))); // NOI18N
        jlabel.setText("Price :");

        modifyProductbtn.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        modifyProductbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/add.png"))); // NOI18N
        modifyProductbtn.setText("MODIFY");
        modifyProductbtn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 102, 255)));
        modifyProductbtn.setContentAreaFilled(false);
        modifyProductbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyProductbtnActionPerformed(evt);
            }
        });

        AddImageBtn.setText("Choose Image");
        AddImageBtn.setContentAreaFilled(false);
        AddImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddImageBtnActionPerformed(evt);
            }
        });

        jlabel1.setFont(new java.awt.Font("Verdana Pro Semibold", 3, 18)); // NOI18N
        jlabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/quantity.png"))); // NOI18N
        jlabel1.setText("Qte en Stock :");

        exitbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/annuler.png"))); // NOI18N
        exitbtn.setContentAreaFilled(false);
        exitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitbtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 2, 48)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ajouterproduit.png"))); // NOI18N
        jLabel2.setText(" MODIFY PRODUCT");

        ProductCode.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(aaa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(exitbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddImageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(126, 126, 126)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(ProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jlabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(QteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(modifyProductbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(313, 313, 313))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(SupplierCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(370, 370, 370))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProductCode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SupplierCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlabel)
                            .addComponent(ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddImageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aaa)
                            .addComponent(jlabel1)
                            .addComponent(QteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(ImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(modifyProductbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(ProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 179, 65));
        jPanel2.setForeground(new java.awt.Color(153, 102, 255));

        deconnecte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/icons8-déconnecté-52.png"))); // NOI18N
        deconnecte.setContentAreaFilled(false);
        deconnecte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deconnecteActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        UserLabel.setBackground(new java.awt.Color(255, 255, 255));
        UserLabel.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        UserLabel.setText("Hello Ilyas");
        UserLabel.setBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 1, true));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(UserLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(UserLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel4.setBackground(java.awt.Color.white);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/icons8-utilisateur-96 (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(deconnecte, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(207, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(286, 286, 286)
                .addComponent(deconnecte, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            productView.loadProductAndPopulateTable();
            productView.setVisible(true);
        }
        this.setVisible(false);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Product code must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error updating product: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_modifyProductbtnActionPerformed
public static byte[] imageContentStatic = null;
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

    private void exitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitbtnActionPerformed
        if (productView == null) {
            productView = new ProductView();
        }
        this.setVisible(false);
        productView.setVisible(true);
    }//GEN-LAST:event_exitbtnActionPerformed

    private void ProductNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductNameActionPerformed
        this.ProductName.setText(null);
    }//GEN-LAST:event_ProductNameActionPerformed
        
    private void deconnecteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deconnecteActionPerformed
        if (loginView == null) {
            loginView = new LOGIN();
        }
        this.setVisible(false);
        loginView.setVisible(true);
    }//GEN-LAST:event_deconnecteActionPerformed

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
            java.util.logging.Logger.getLogger(ModifyProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifyProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifyProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifyProductView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifyProductView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddImageBtn;
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JTextField ProductCode;
    private javax.swing.JTextField ProductName;
    private javax.swing.JFormattedTextField ProductPrice;
    private javax.swing.JFormattedTextField QteTextField;
    private javax.swing.JComboBox<String> SupplierCombo;
    private javax.swing.JLabel UserLabel;
    private javax.swing.JLabel aaa;
    private javax.swing.JButton deconnecte;
    private javax.swing.JButton exitbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jlabel;
    private javax.swing.JLabel jlabel1;
    private javax.swing.JButton modifyProductbtn;
    // End of variables declaration//GEN-END:variables

    public void setUserLabel(String UserLabel) {
        this.UserLabel.setText(UserLabel);
    }
}
