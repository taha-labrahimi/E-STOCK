/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package e_stock.view;
import java.sql.*;
import e_stock.Model.User;
import e_stock.database.DatabaseConnector;
import e_stock.view.OrderView.OrderView;
import e_stock.view.ProductView.AddProductView;
import e_stock.view.ProductView.ProductView;
import e_stock.view.clientView.ClientView;
import e_stock.view.supplierView.SupplierView;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class HOME extends javax.swing.JFrame {
    
    private User user;
    private OrderView orderView;
    private ProductView productView;
    private ClientView clientView;
    private SupplierView supplierView;
    private UserProfil userProfil;
    DatabaseConnector dbConnector = new DatabaseConnector();
    public HOME() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
    }
   public void setLoggedInUser(User user) {
    this.user = user;
    welcomeLabel.setText("Hello, " + user.getUsername());
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
            imageIcon = new ImageIcon(userImage.getScaledInstance(userimage.getWidth(), userimage.getHeight(), Image.SCALE_SMOOTH));
            adminicon.setVisible(false);
        } else {
//            // Utiliser une image intégrée comme image par défaut
//            try {
//                BufferedImage defaultImage = ImageIO.read(getClass().getResource("/resources/images/home/Admin Settings Male.png"));
//                imageIcon = new ImageIcon(defaultImage.getScaledInstance(userimage.getWidth(), userimage.getHeight(), Image.SCALE_SMOOTH));
//            } catch (Exception e) {
//                e.printStackTrace();
//                return; // Arrêter l'exécution si l'image par défaut ne peut pas être chargée
//            }
        }

        userimage.setIcon(imageIcon);
    } catch (Exception e) {
        e.printStackTrace();
    }
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        userimage = new javax.swing.JLabel();
        Profilbtn = new javax.swing.JButton();
        welcomeLabel = new javax.swing.JLabel();
        adminicon = new javax.swing.JLabel();
        Clientsbtn = new javax.swing.JButton();
        Fournisseurbtn = new javax.swing.JButton();
        Productsbtn = new javax.swing.JButton();
        Usersbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(244, 172, 74));

        userimage.setBackground(new java.awt.Color(255, 255, 255));
        userimage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Profilbtn.setText("Profil");
        Profilbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfilbtnActionPerformed(evt);
            }
        });

        welcomeLabel.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        adminicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/home/Admin Settings Male.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(welcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Profilbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(userimage, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 8, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(adminicon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(userimage, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Profilbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adminicon)
                .addGap(18, 18, 18)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(299, 299, 299))
        );

        Clientsbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ClientsIcon.png"))); // NOI18N
        Clientsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientsbtnActionPerformed(evt);
            }
        });

        Fournisseurbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/FournisseurIcon.png"))); // NOI18N
        Fournisseurbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FournisseurbtnActionPerformed(evt);
            }
        });

        Productsbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/ProductsIcon.png"))); // NOI18N
        Productsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductsbtnActionPerformed(evt);
            }
        });

        Usersbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/UsersIcon.png"))); // NOI18N
        Usersbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsersbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(Productsbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Fournisseurbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Usersbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(Clientsbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(526, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(Clientsbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Productsbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fournisseurbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111)
                .addComponent(Usersbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
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

    private void ClientsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientsbtnActionPerformed
          if (clientView == null) {
            clientView = new ClientView();
        }
        this.setVisible(false);
        clientView.setVisible(true);      // TODO add your handling code here:
    }//GEN-LAST:event_ClientsbtnActionPerformed

    private void ProductsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductsbtnActionPerformed
        if (productView == null) {
            productView = new ProductView();
        }
        this.setVisible(false);
        productView.setVisible(true);
    }//GEN-LAST:event_ProductsbtnActionPerformed

    private void FournisseurbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FournisseurbtnActionPerformed
        if (supplierView == null) {
            supplierView = new SupplierView();
        }
        this.setVisible(false);
        supplierView.setVisible(true);
    }//GEN-LAST:event_FournisseurbtnActionPerformed

    private void ProfilbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfilbtnActionPerformed
        // TODO add your handling code here:
        if (user != null) {
            // Display the user profile view with JTable
            if (userProfil == null) {
                userProfil = new UserProfil();
            }
            userProfil.displayUserData(user);
            this.setVisible(false);
            userProfil.setVisible(true);
        } else {
            // User not logged in, handle accordingly
            JOptionPane.showMessageDialog(this, "User not logged in!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ProfilbtnActionPerformed

    private void UsersbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsersbtnActionPerformed
        if (orderView == null) {
            orderView = new OrderView();
        }
        this.setVisible(false);
        orderView.setVisible(true);
    }//GEN-LAST:event_UsersbtnActionPerformed

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
            java.util.logging.Logger.getLogger(HOME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HOME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HOME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HOME.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HOME().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Clientsbtn;
    private javax.swing.JButton Fournisseurbtn;
    private javax.swing.JButton Productsbtn;
    private javax.swing.JButton Profilbtn;
    private javax.swing.JButton Usersbtn;
    private javax.swing.JLabel adminicon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel userimage;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
