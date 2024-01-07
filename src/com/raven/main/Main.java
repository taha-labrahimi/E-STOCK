package com.raven.main;

import com.raven.event.EventMenu;
import com.raven.form.Form;
import com.raven.form.Form_1;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import e_stock.view.OrderView.OrderView;
import e_stock.view.ProductView.ProductView;
import e_stock.view.clientView.ClientView;
import e_stock.view.supplierView.SupplierView;
import java.awt.Color;
import java.awt.Component;
import e_stock.Model.Supplier;
import java.sql.*;
import e_stock.Model.User;
import e_stock.database.DatabaseConnector;
import e_stock.view.LOGIN;
import e_stock.view.OrderView.OrderView;
import e_stock.view.ProductView.AddProductView;
import e_stock.view.ProductView.ProductView;
import e_stock.view.UserProfil;
import static e_stock.view.UserProfil.userstatic;
import e_stock.view.clientView.ClientView;
import e_stock.view.supplierView.SupplierView;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Main extends javax.swing.JFrame {

    private User user;
    private OrderView orderView;
    private ProductView productView;
    private LOGIN login;
    private ClientView clientView;
    private SupplierView supplierView;
    private UserProfil userProfil;
    DatabaseConnector dbConnector = new DatabaseConnector();

    public Main() {
        initComponents();
        FlatLaf.registerCustomDefaultsSource("e_stock.view.clientView");
        FlatMacDarkLaf.setup();
        userProfil = new UserProfil(this);
        setBackground(new Color(0, 0, 0, 0));
        EventMenu event = new EventMenu() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    showForm(new Form_1());
                } else if (index == 8) {
                    System.out.println("Logout");
                    }  
                else if (index == 1) {
                    showForm(new ClientView(Main.this));
                }    
                else if (index == 2) {
                    showForm(new ProductView(Main.this));
                } 
                else if (index == 3) {
                    showForm(new SupplierView(Main.this));
                } 
                else if (index == 4) {
                    showForm(new OrderView(Main.this));
                } 
                else if (index == 5) {
                    setLoggedInUser(userstatic);
                    userProfil.displayUserData(userstatic);
                    showForm(userProfil);
                } 
                else {
                    showForm(new Form(index));
                }
            }
        };
        menu1.initMenu(event);
        showForm(new Form_1());
    }
    public void setLoggedInUser(User user) {
    this.user = user;
    menu1.setUsername(user.getUsername());
    String sql = "SELECT image FROM users WHERE userID = ?";
    
    try (Connection conn = dbConnector.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, user.getUserID()); // utilisez l'ID de l'utilisateur
        ResultSet rs = pstmt.executeQuery();

        ImageIcon imageIcon = null;
        if (rs.next() && rs.getBlob("image") != null) {
            Blob blob = rs.getBlob("image");
            InputStream inputStream = blob.getBinaryStream();
            BufferedImage userImage = ImageIO.read(inputStream);
            imageIcon = new ImageIcon(userImage.getScaledInstance(menu1.imageAvatar1.getWidth(), menu1.imageAvatar1.getHeight(), Image.SCALE_SMOOTH));
        } else {
            imageIcon = new ImageIcon(getClass().getResource("/resources/images/home/Admin Settings Male.png"));
        }

        menu1.imageAvatar1.setIcon(imageIcon);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.revalidate();
        body.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new com.raven.swing.RoundPanel();
        header2 = new com.raven.component.Header();
        menu1 = new com.raven.component.Menu();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        roundPanel1.setBackground(new java.awt.Color(25, 25, 25));

        body.setOpaque(false);
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, 1371, Short.MAX_VALUE)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel body;
    private com.raven.component.Header header2;
    private com.raven.component.Menu menu1;
    private com.raven.swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
