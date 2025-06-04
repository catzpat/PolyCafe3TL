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

        // Tạo container (Panel) Sidebar
        JPanel panelSidebar = new JPanel();
        panelSidebar.setBackground(Color.WHITE);
        panelSidebar.setPreferredSize(new Dimension(250, getHeight()));
        panelSidebar.setLayout(new BorderLayout());

        // Panel chứa User Info
        JPanel panelUserInfo = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelUserInfo.setBackground(Color.WHITE);

        // Avatar
        ImageIcon avatarIcon = new ImageIcon("src/USER_IMG/default.jpg");
        Image scaledImage = avatarIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel lblAvatar = new JLabel(new ImageIcon(scaledImage));

        // Tên user
        JLabel txtUserName = new JLabel(NameAccount);
        txtUserName.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panelUserInfo.add(lblAvatar);
        panelUserInfo.add(txtUserName);

        // Separator
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);

        // Panel chứa các nút Menu
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(6, 1, 0, 10));
        panelMenu.setBackground(Color.WHITE);
        panelMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonNames = {
            "Trang chủ",
            "Bán hàng",
            "Quản Lý SP",
            "Quản Lý DH",
            "Quản Lý NV",
            "Thống Kê"
        };

        for (String name : buttonNames) {
            JButton button = new JButton(name);
            button.setFocusPainted(false); // Bỏ khoanh vùng chữ
            button.setBackground(Color.decode("#eeeeee"));
            button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            button.setPreferredSize(new Dimension(80, 60)); // tăng chiều cao btn\
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(70, 130, 180));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.WHITE);
                }
            });
            panelMenu.add(button);
        }
        // Tạo 1 container chứa 3 phần trên
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        panelContainer.setBackground(Color.WHITE);

        panelContainer.add(panelUserInfo);
        panelContainer.add(separator);
        panelContainer.add(panelMenu);

        // Thêm panelContainer vào panelSidebar
        panelSidebar.add(panelContainer, BorderLayout.NORTH);

        // Thêm panelSidebar vào frame
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
