package Form;

import CafeDAO.DAO;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Seller_interface extends javax.swing.JFrame {

    private String NameAccount = "";

    public Seller_interface(String NameAccount) {

        this.NameAccount = NameAccount;
        initComponents();
        setTitle("4KL_Seller");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // set width = max
        setLocationRelativeTo(null);
        txtUserName.setText("Hello, " + NameAccount);
//        ---------------------------------------------------------------------
        DAO dao = new DAO();
        
//        ImageIcon icon = new ImageIcon("src/USER_IMG/admin1.jpg");
//        ImageIcon icon = new ImageIcon("src/USER_IMG/" + img);
//        Image image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // điều chỉnh kích thước nếu cần
//        txtImg.setIcon(new ImageIcon(image));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUserName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtUserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtUserName.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 775, Short.MAX_VALUE)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUserName)
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
    private javax.swing.JLabel txtUserName;
    // End of variables declaration//GEN-END:variables
}
