package View;

import Controller.DAO;
import Model.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Admin_interface extends javax.swing.JFrame {

    public String NameAccount = "";

    public Admin_interface(String NameAccount) {
        this.NameAccount = NameAccount;
        initComponents();
        initLayout();
        setTitle("4KL_Admin");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // set width = max
        setLocationRelativeTo(null);

    }

    public void initLayout() {
        // Set layout chính của frame thành BorderLayout
        getContentPane().setLayout(new BorderLayout());

        // Tạo contaner (Panel) Slidebar 
        JPanel panelSidebar = new JPanel();
        panelSidebar.setBackground(Color.WHITE);
        // Tạo đối tượng Dimension (Kích thước) cho panelSlidebar
        panelSidebar.setPreferredSize(new Dimension(250, getHeight())); // chiều rộng 250px, chiều cao = chiều cao của chương trinh
        panelSidebar.setLayout(new BorderLayout());  
        
        // Thêm panelSidebar vào bên trái (WEST)
        getContentPane().add(panelSidebar, BorderLayout.WEST);
//          ---------------- Panel in top (Account Inf) ----------------
        JPanel panelUserInfo = new JPanel();
        panelUserInfo.setBackground(Color.WHITE);
        panelUserInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // căn lề trái + padding

        // Avatar
        ImageIcon avatarIcon = new ImageIcon("src/USER_IMG/default.jpg");
        // Scale image vừa với JLabel (40x40)
        Image scaledImage = avatarIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel lblAvatar = new JLabel(new ImageIcon(scaledImage));

        // Tên user
        JLabel txtUserName = new JLabel(NameAccount);
        txtUserName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        // set Tên cho user
        txtUserName.setText(NameAccount);

        // Thêm avatar và tên vào panelUserInfo
        panelUserInfo.add(lblAvatar);
        panelUserInfo.add(txtUserName);

        // Tạo đường kẻ đen (separator)
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setPreferredSize(new Dimension(5, 5));


        // ---------------- Thêm vào panelSlidebar ----------------
        panelSidebar.add(panelUserInfo, BorderLayout.NORTH); // top
        panelSidebar.add(separator, BorderLayout.CENTER); // separator ở dưới panelUserInfo

        // ---------------- Thêm panelSidebar vào frame ----------------
        getContentPane().add(panelSidebar, BorderLayout.WEST);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 907, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );

        pack();
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
            java.util.logging.Logger.getLogger(Admin_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_interface("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
