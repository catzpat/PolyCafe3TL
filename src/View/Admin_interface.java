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
        initUI();

    }

    public void initUI() {
        setTitle("4KL_Admin");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // set width = max
        setLocationRelativeTo(null);
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
            "Quản Lý HD",
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
//        topLeft.setBackground(Color.GRAY);
        JPanel topRight = new JPanel();
//        topRight.setBackground(Color.ORANGE);

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
//        ------------------- chia trong top left

//        ------------------- Bottom Container
        JPanel bottomContainer = new JPanel(new GridBagLayout());
        JPanel bottomLeft = new JPanel();
//        bottomLeft.setBackground(Color.cyan);
        JPanel bottomRight = new JPanel();
//        bottomRight.setBackground(Color.GREEN);

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

// Đổi layout topLeft thành BorderLayout
        topLeft.setLayout(new BorderLayout());

// Tạo panel bọc mainText để thêm padding nếu muốn
        JPanel panelTitle = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); // canh trái, padding 10,5
        panelTitle.add(mainText);
//        panelTitle.setBackground(Color.GRAY); // giữ màu nền

// Thêm panelTitle lên topLeft phía trên
        topLeft.add(panelTitle, BorderLayout.NORTH);

        Object[][] data = {
            {"Sản phẩm A", 2, "100.000"},
            {"Sản phẩm B", 1, "150.000"},
            {"Sản phẩm C", 5, "50.000"}
        };
// Tên cột
        String[] columnNames = {"Tên Món", "Số Lượng", "Giá"};

// Tạo bảng
        JTable table = new JTable(data, columnNames);
        table.setEnabled(false);

// Bọc bảng trong JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

// Đặt scrollPane vào trung tâm (dưới mainText)
        topLeft.add(scrollPane, BorderLayout.CENTER);
// ------------------------------------------------------------------
        // Danh sách hóa đơn chờ
        Label mainText2 = new Label("Danh sách hóa đơn chờ");
        mainText2.setFont(new Font("Segoe UI", Font.BOLD, 20));

// Đổi layout topLeft thành BorderLayout
        topRight.setLayout(new BorderLayout());

// Tạo panel bọc mainText để thêm padding nếu muốn
        JPanel panelTitle2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); // canh trái, padding 10,5
        panelTitle2.add(mainText2);
//        panelTitle2.setBackground(Color.YELLOW); // giữ màu nền

// Thêm panelTitle lên topLeft phía trên
        topRight.add(panelTitle2, BorderLayout.NORTH);

        Object[][] data2 = {
            {1, "HD001", "Chờ"},
            {2, "HD002", "Chờ"},
            {3, "HD003", "Chờ"}
        };
// Tên cột
        String[] columnNames2 = {"STT", "Mã Hóa Đơn", "Trạng Thái"};

// Tạo bảng
        JTable table2 = new JTable(data2, columnNames2);
        table2.setEnabled(false);

// Bọc bảng trong JScrollPane
        JScrollPane scrollPane2 = new JScrollPane(table2);

// Đặt scrollPane vào trung tâm (dưới mainText)
        topRight.add(scrollPane2, BorderLayout.CENTER);
//   --------------------------------------------------------------
        Label mainText3 = new Label("Danh sách sản phẩm");
        mainText3.setFont(new Font("Segoe UI", Font.BOLD, 20));

// Panel chứa tiêu đề
        JPanel panelTitle3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
//        panelTitle3.setBackground(Color.cyan); // cùng màu với bottomLeft
        panelTitle3.add(mainText3);

// Panel danh sách sản phẩm (2 cột)
        JPanel productListPanel = new JPanel(new GridLayout(0, 2, 10, 10));
//        productListPanel.setBackground(Color.cyan);

// Danh sách sản phẩm mẫu
        String[] productNames = {"Trà sữa chân châu hoàn gia", "Nước Cam Ép", "Caffe", "Capuchino", "Nước Cam Ép 2.0", "Nước Ép Dưa Hấu"};
        String[] productPrices = {"100.000 đ", "200.000 đ", "150.000 đ", "80.000 đ", "120.000 đ", "97.000 đ"};

        for (int i = 0; i < productNames.length; i++) {
            JPanel productPanel = new JPanel(new BorderLayout(10, 10));
            productPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            productPanel.setBackground(Color.WHITE);

            // Ảnh sản phẩm
            String imagePath = "src/STOCK_img/img" + (i + 1) + ".jpg";
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            productPanel.setLayout(new BorderLayout(10, 0));
            JLabel lblImage = new JLabel(new ImageIcon(img));
            productPanel.add(lblImage, BorderLayout.WEST);
            lblImage.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

            // Bên phải thông tin
            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.setBackground(Color.WHITE);

            // Tên và giá
            JPanel namePricePanel = new JPanel();
            namePricePanel.setLayout(new BoxLayout(namePricePanel, BoxLayout.Y_AXIS));
            namePricePanel.setBackground(Color.WHITE);
            JLabel lblName = new JLabel(productNames[i]);
            lblName.setFont(new Font("Segoe UI", Font.BOLD, 18));
            JLabel lblPrice = new JLabel(productPrices[i]);
            lblPrice.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            namePricePanel.add(lblName);
            namePricePanel.add(lblPrice);
            rightPanel.add(namePricePanel, BorderLayout.NORTH);

            // Nút + và - số lượng
            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            quantityPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            quantityPanel.setBackground(Color.WHITE);
            JButton btnMinus = new JButton("-");
            JButton btnPlus = new JButton("+");
            JLabel lblQuantity = new JLabel("0");
            lblQuantity.setPreferredSize(new Dimension(20, 20));
            lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);

            btnMinus.addActionListener(e -> {
                int current = Integer.parseInt(lblQuantity.getText());
                if (current > 0) {
                    lblQuantity.setText(String.valueOf(current - 1));
                }
            });
            btnPlus.addActionListener(e -> {
                int current = Integer.parseInt(lblQuantity.getText());
                lblQuantity.setText(String.valueOf(current + 1));
            });

            quantityPanel.add(btnMinus);
            quantityPanel.add(lblQuantity);
            quantityPanel.add(btnPlus);
            rightPanel.add(quantityPanel, BorderLayout.SOUTH);

            productPanel.add(rightPanel, BorderLayout.CENTER);

            productListPanel.add(productPanel);
        }

        // Gói gọn lại phần danh sách sản phẩm trong JScrollPane để cuộn
        JScrollPane scrollPaneProducts = new JScrollPane(productListPanel);
        scrollPaneProducts.setPreferredSize(new Dimension(0, 300)); // giới hạn chiều cao
        scrollPaneProducts.setBorder(null);

        // Panel gói gọn tiêu đề và danh sách
        JPanel productWrapper = new JPanel(new BorderLayout());
        productWrapper.setBackground(Color.cyan);
        productWrapper.add(panelTitle3, BorderLayout.NORTH);
        productWrapper.add(scrollPaneProducts, BorderLayout.CENTER);

        // Thêm vào bottomLeft
        bottomLeft.setLayout(new BorderLayout());
        bottomLeft.add(productWrapper, BorderLayout.CENTER);
// ------------------------------------------------------------------
        // Layout dọc cho bottomRight
        bottomRight.setLayout(new BoxLayout(bottomRight, BoxLayout.Y_AXIS));
        bottomRight.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // padding đều các phía

        // Tên khách
        bottomRight.add(new JLabel("Tên khách:"));
        bottomRight.add(Box.createVerticalStrut(5));
        bottomRight.add(new JTextField());

        // SDT
        bottomRight.add(Box.createVerticalStrut(10));
        bottomRight.add(new JLabel("SDT:"));
        bottomRight.add(Box.createVerticalStrut(5));
        bottomRight.add(new JTextField());

        // Mã giảm giá
        bottomRight.add(Box.createVerticalStrut(10));
        bottomRight.add(new JLabel("Mã giảm giá:"));
        bottomRight.add(Box.createVerticalStrut(5));
        bottomRight.add(new JTextField());
        // Tổng tiền
        bottomRight.add(Box.createVerticalStrut(10));
        bottomRight.add(new JLabel("Tổng tiền:"));
        bottomRight.add(Box.createVerticalStrut(5));
        bottomRight.add(new JTextField());

        // Nút "Tổng tiền"
        bottomRight.add(Box.createVerticalStrut(20));
        JButton btnTinhTong = new JButton("Tổng tiền");  
        btnTinhTong.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottomRight.add(btnTinhTong);

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
