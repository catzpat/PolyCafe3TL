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

        JPanel panelSidebar = new JPanel(new BorderLayout());
        panelSidebar.setBackground(Color.WHITE);
        panelSidebar.setPreferredSize(new Dimension(250, getHeight()));

        // User Info Panel
        JPanel panelUserInfo = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelUserInfo.setBackground(Color.WHITE);

        ImageIcon avatarIcon = new ImageIcon("src/USER_IMG/default.jpg");
        Image scaledImage = avatarIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel lblAvatar = new JLabel(new ImageIcon(scaledImage));

        JLabel txtUserName = new JLabel(NameAccount);
        txtUserName.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panelUserInfo.add(lblAvatar);
        panelUserInfo.add(txtUserName);

        // Separator
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);

        // Menu Buttons
        JPanel panelMenu = new JPanel(new GridLayout(6, 1, 0, 10));
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
            button.setFocusPainted(false);
            button.setBackground(Color.decode("#eeeeee"));
            button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            button.setPreferredSize(new Dimension(80, 60));

            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(70, 130, 180));
                    button.setForeground(Color.WHITE);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.WHITE);
                    button.setForeground(Color.BLACK);
                }
            });

            panelMenu.add(button);
        }

        // Sidebar container
        JPanel SlidebarContainer = new JPanel();
        SlidebarContainer.setLayout(new BoxLayout(SlidebarContainer, BoxLayout.Y_AXIS));
        SlidebarContainer.setBackground(Color.WHITE);
        SlidebarContainer.add(panelUserInfo);
        SlidebarContainer.add(separator);
        SlidebarContainer.add(panelMenu);

        panelSidebar.add(SlidebarContainer, BorderLayout.NORTH);
        getContentPane().add(panelSidebar, BorderLayout.WEST);

        // Main content
        JPanel mainContainer = new JPanel(new GridLayout(2, 2));

        JPanel topContainer = new JPanel(new GridBagLayout());
        JPanel topLeft = new JPanel();
        topLeft.setBackground(Color.GRAY);
        JPanel topRight = new JPanel();
        topRight.setBackground(Color.ORANGE);

        GridBagConstraints gbcTop = new GridBagConstraints();
        gbcTop.fill = GridBagConstraints.BOTH;
        gbcTop.gridy = 0;
        gbcTop.weighty = 1;

        // Left (60%)
        gbcTop.gridx = 0;
        gbcTop.weightx = 0.6;
        topContainer.add(topLeft, gbcTop);

        // Right (40%)
        gbcTop.gridx = 1;
        gbcTop.weightx = 0.4;
        topContainer.add(topRight, gbcTop);

// --------------------------Bottom Container
        JPanel bottomContainer = new JPanel(new GridBagLayout());
        JPanel bottomLeft = new JPanel();
        bottomLeft.setBackground(Color.cyan);
        JPanel bottomRight = new JPanel();
        bottomRight.setBackground(Color.GREEN);

        GridBagConstraints gbcBottom = new GridBagConstraints();
        gbcBottom.fill = GridBagConstraints.BOTH;
        gbcBottom.gridy = 0;
        gbcBottom.weighty = 1;

        // Left (70%)
        gbcBottom.gridx = 0;
        gbcBottom.weightx = 0.7;
        bottomContainer.add(bottomLeft, gbcBottom);

        // Right (30%)
        gbcBottom.gridx = 1;
        gbcBottom.weightx = 0.3;
        bottomContainer.add(bottomRight, gbcBottom);

// ------------------------------------------------------------------
// Bảng thông tin hóa đơn
        Label mainText = new Label("Thông tin hóa đơn");
        mainText.setFont(new Font("Segoe UI", Font.BOLD, 20));
        topLeft.setLayout(new BoxLayout(topLeft, BoxLayout.Y_AXIS)); // sắp xếp dọc
        topLeft.add(mainText);
        Object[][] data = {
            {"Sản phẩm A", "100.000", 2},
            {"Sản phẩm B", "150.000", 1},
            {"Sản phẩm C", "50.000", 5}
        };
        // Tên cột
        String[] columnNames = {"Tên", "Giá", "Số lượng"};

        // Tạo bảng
        JTable table = new JTable(data, columnNames);

        // Bọc bảng trong JScrollPane để có thể cuộn nếu dữ liệu dài
        JScrollPane scrollPane = new JScrollPane(table);

        // Set kích thước hoặc layout cho scrollPane nếu cần
        scrollPane.setPreferredSize(new Dimension(100, 50)); // bạn có thể điều chỉnh

        topLeft.add(scrollPane);
// ------------------------------------------------------------------
        mainContainer.add(topContainer);
        mainContainer.add(bottomContainer);

        getContentPane().add(mainContainer, BorderLayout.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
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
