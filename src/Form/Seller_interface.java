package Form;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Seller_interface extends javax.swing.JFrame {

    String userName = "";

    public Seller_interface(String userName) {

        initComponents();
        setTitle("4KL_Seller");
        this.userName = userName;
        txtHello.setText(userName);
        setLocationRelativeTo(null);
//        ImageIcon icon = new ImageIcon("src/USER_IMG/admin1.jpg");
//        ImageIcon icon = new ImageIcon("src/USER_IMG/" + img);
//        Image image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // điều chỉnh kích thước nếu cần
//        txtImg.setIcon(new ImageIcon(image));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtHello = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtHello.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtHello.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(840, Short.MAX_VALUE)
                .addComponent(txtHello, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtHello)
                .addContainerGap(545, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Seller_interface("").setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel txtHello;
    // End of variables declaration//GEN-END:variables
}
