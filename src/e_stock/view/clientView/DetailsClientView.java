/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package e_stock.view.clientView;

import com.raven.main.Main;

/**
 *
 * @author ilyas
 */
public class DetailsClientView extends javax.swing.JPanel {
    private Main main;
     private ClientView clientView;
    public DetailsClientView(Main main) {
        this.main = main;
        setOpaque(false);
         clientView = new ClientView(main);
        initComponents();
        
    }

    public void setAdresse(String adresse) {
        this.adresse.setText(adresse);
    }

    public void setCity(String  city) {
        this.city.setText(city);
    }

    public void setCountry(String country) {
        this.country.setText(country);
    }

    public void setFirstnam(String firstnam) {
        this.firstnam.setText(firstnam);
    }

    public void setLastname(String lastname) {
        this.lastname.setText(lastname);
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber.setText(phonenumber);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lastname = new javax.swing.JLabel();
        adresse = new javax.swing.JLabel();
        city = new javax.swing.JLabel();
        firstnam = new javax.swing.JLabel();
        country = new javax.swing.JLabel();
        phonenumber = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        lastname.setBackground(new java.awt.Color(204, 204, 204));
        lastname.setFont(new java.awt.Font("/resources/fonts/PoppinsBlack.otf", java.awt.Font.PLAIN, 18)
        );
        lastname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lastname.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        adresse.setBackground(new java.awt.Color(204, 204, 204));
        adresse.setFont(new java.awt.Font("/resources/fonts/PoppinsBlack.otf", java.awt.Font.PLAIN, 18)
        );
        adresse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adresse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        city.setBackground(new java.awt.Color(204, 204, 204));
        city.setFont(new java.awt.Font("/resources/fonts/PoppinsBlack.otf", java.awt.Font.PLAIN, 18)
        );
        city.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        city.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        firstnam.setBackground(new java.awt.Color(204, 204, 204));
        firstnam.setFont(new java.awt.Font("/resources/fonts/PoppinsBlack.otf", java.awt.Font.PLAIN, 18)
        );
        firstnam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstnam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        country.setBackground(new java.awt.Color(204, 204, 204));
        country.setFont(new java.awt.Font("/resources/fonts/PoppinsBlack.otf", java.awt.Font.PLAIN, 18)
        );
        country.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        country.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        phonenumber.setBackground(new java.awt.Color(204, 204, 204));
        phonenumber.setFont(new java.awt.Font("/resources/fonts/PoppinsBlack.otf", java.awt.Font.PLAIN, 18)
        );
        phonenumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        phonenumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(139, 139, 139))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(firstnam, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 466, Short.MAX_VALUE)
                        .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(firstnam, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)
                        .addComponent(adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                        .addComponent(phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adresse;
    private javax.swing.JLabel city;
    private javax.swing.JLabel country;
    private javax.swing.JLabel firstnam;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lastname;
    private javax.swing.JLabel phonenumber;
    // End of variables declaration//GEN-END:variables
}
