/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package e_stock.view.supplierView;
import com.raven.main.Main;
import e_stock.Model.Supplier;
import e_stock.RepositoryImplementation.SupplierRepositoryImpl;
import e_stock.database.DatabaseConnector;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class ModifySupplierView extends javax.swing.JPanel {

    private SupplierRepositoryImpl supplierrepository;
    private SupplierView supplierview;
    
    Main main;
    public ModifySupplierView(Main main) {
        this.main=main;
        initComponents();
        supplierview = new SupplierView(main);
        DatabaseConnector dbConnector = new DatabaseConnector();
        supplierrepository = new SupplierRepositoryImpl(dbConnector);
    }

        public void setSuppliercode(String suppliercode) {
        this.suppliercode.setText(suppliercode);
    }
    
    public JTextField getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse.setText(adresse);
    }

    public JTextField getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city.setText(city);
    }

    public JTextField getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country.setText(country);
    }

    public JTextField getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname.setText(firstname);
    }

    public JTextField getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.setText(lastname);
    }

    public JTextField getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber.setText(phonenumber);
    } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        firstname = new javax.swing.JTextField();
        aaa = new javax.swing.JLabel();
        lastname = new javax.swing.JTextField();
        ddd = new javax.swing.JLabel();
        adresse = new javax.swing.JTextField();
        city = new javax.swing.JTextField();
        jlabel = new javax.swing.JLabel();
        country = new javax.swing.JTextField();
        phonenumber = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        suppliercode = new javax.swing.JTextField();
        modifysupplierbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setFont(new java.awt.Font("Verdana Pro Cond Black", 1, 48)); // NOI18N
        jLabel2.setText("MODIFY SUPPLIER");

        jLabel3.setFont(new java.awt.Font("Verdana Pro Semibold", 1, 14)); // NOI18N
        jLabel3.setText("First Name : ");

        firstname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        aaa.setFont(new java.awt.Font("Verdana Pro Semibold", 1, 14)); // NOI18N
        aaa.setText("Last Name : ");

        lastname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ddd.setFont(new java.awt.Font("Verdana Pro Semibold", 1, 14)); // NOI18N
        ddd.setText("Adresse : ");
        ddd.setToolTipText("");

        adresse.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        city.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jlabel.setFont(new java.awt.Font("Verdana Pro Semibold", 1, 14)); // NOI18N
        jlabel.setText("City : ");

        country.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        phonenumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Verdana Pro Semibold", 1, 14)); // NOI18N
        jLabel8.setText("Phone Number :");

        suppliercode.setEditable(false);
        suppliercode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppliercodeActionPerformed(evt);
            }
        });

        modifysupplierbtn.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        modifysupplierbtn.setText("Modify");
        modifysupplierbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifysupplierbtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setText("Country :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(aaa)
                                    .addComponent(ddd))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modifysupplierbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(248, 248, 248)))
                .addGap(73, 73, 73))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(261, 261, 261))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(suppliercode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(469, 469, 469))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(suppliercode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aaa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ddd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(modifysupplierbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void modifysupplierbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifysupplierbtnActionPerformed
        String phoneNumber = phonenumber.getText();
        try {
            if (phoneNumber.matches("\\d{10}")) {
                int supplierCode = Integer.parseInt(suppliercode.getText());
                Supplier supplier = new Supplier(supplierCode, firstname.getText(), lastname.getText(), adresse.getText(), city.getText(), country.getText(), phonenumber.getText());
                supplierrepository.update(supplier);
                JOptionPane.showMessageDialog(this, "Supplier modified successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                if (supplierview != null) {
                    supplierview = new SupplierView(main);
                }
                this.main.showForm(supplierview);
                phonenumber.setBackground(Color.WHITE);
            }
            else {
                JOptionPane.showMessageDialog(this, "please add a valid number Exp\"06 XX XX XX XX\"", "validation Error", JOptionPane.ERROR_MESSAGE);
                phonenumber.setBackground(Color.PINK);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Supplier code must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating Supplier: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_modifysupplierbtnActionPerformed

    private void suppliercodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppliercodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_suppliercodeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aaa;
    private javax.swing.JTextField adresse;
    private javax.swing.JTextField city;
    private javax.swing.JTextField country;
    private javax.swing.JLabel ddd;
    private javax.swing.JTextField firstname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlabel;
    private javax.swing.JTextField lastname;
    private javax.swing.JButton modifysupplierbtn;
    private javax.swing.JTextField phonenumber;
    private javax.swing.JTextField suppliercode;
    // End of variables declaration//GEN-END:variables
}
