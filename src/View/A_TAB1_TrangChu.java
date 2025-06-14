package View;

import Controller.DAO;
import Model.User;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class A_TAB1_TrangChu extends javax.swing.JFrame {

    private String NameAccount;
    private String RoleAccount;

    public A_TAB1_TrangChu(String NameAccount, String RoleAccount) {
        this.NameAccount = NameAccount;
        this.RoleAccount = RoleAccount;
        setTitle("4KL_Trang Chủ");
        initComponents();
        setLogo();
        setTime();
        setAccountInfo();
        addLabelEvents();
        JButton[] btn = {
            btnTrangChu, btnOrder, btnQLSP, btnQLHD, btnQLHD1, btnTK
        };

        for (JButton jbtn : btn) {
            jbtn.setFocusPainted(false);
            jbtn.setBackground(Color.WHITE);
            jbtn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    jbtn.setBackground(new Color(230, 230, 250));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    jbtn.setBackground(Color.WHITE);
                }
            });
        }

    }

    private void setLogo() {
        setImageToLabel(lblFPS, "src/STOCK_IMG/Logo_FPS.png");
        ImageIcon icon = new ImageIcon("src/USER_IMG/default.jpg");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        txtImg.setText("");
        txtImg.setIcon(new ImageIcon(scaledImg));
        txtUserName.setText(NameAccount);
    }

    private void setImageToLabel(JLabel label, String path) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(
                label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH
        );
        label.setIcon(new ImageIcon(img));
        label.setText(""); // Ẩn chữ "jLabel1"
    }

    private void setTime() {
        Timer timer = new Timer(1000, e -> {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MM-yyyy - HH:mm:ss");
            lblTG.setText(sdf.format(now));
        });
        timer.start();
    }

    private void setAccountInfo() {
        lblTen.setText("Xin chào, " + NameAccount);
        lblVT.setText(RoleAccount);
        lblBQ.setText("© PolyCafe 4KL");
    }

    private void addLabelEvents() {
        makeLabelInteractive(lblDX, () -> {
            // Đăng xuất: quay về LoginForm và đóng form hiện tại
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn đăng xuất?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
                new A1_LoginForm().setVisible(true); // mở lại form đăng nhập
            }
        });
    }

    private void makeLabelInteractive(JLabel label, Runnable onClick) {
        label.setText("<html><u>" + label.getText() + "</u></html>");
        label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick.run();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlCN = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        btnQLSP = new javax.swing.JButton();
        btnQLHD = new javax.swing.JButton();
        btnTK = new javax.swing.JButton();
        btnQLHD1 = new javax.swing.JButton();
        lblDX = new javax.swing.JLabel();
        txtUserName = new javax.swing.JLabel();
        txtImg = new javax.swing.JLabel();
        lblTrangTri = new javax.swing.JLabel();
        lblTrangTri1 = new javax.swing.JLabel();
        lblPMQL = new javax.swing.JLabel();
        lblPolycafe = new javax.swing.JLabel();
        lblTG = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        lblVT = new javax.swing.JLabel();
        lblBQ = new javax.swing.JLabel();
        lblFPS = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(223, 255, 214));
        pnlMain.setMaximumSize(new java.awt.Dimension(1485, 780));
        pnlMain.setMinimumSize(new java.awt.Dimension(1485, 780));

        pnlCN.setBackground(new java.awt.Color(215, 204, 200));

        btnTrangChu.setText("Trang Chủ");
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        btnOrder.setText("Order");
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        btnQLSP.setText("Quản Lý Sản Phẩm");
        btnQLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLSPActionPerformed(evt);
            }
        });

        btnQLHD.setText("Quản Lý Hóa Đơn");
        btnQLHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLHDActionPerformed(evt);
            }
        });

        btnTK.setText("Thống Kê");
        btnTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKActionPerformed(evt);
            }
        });

        btnQLHD1.setText("Quản Lý Nhân Viên");
        btnQLHD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLHD1ActionPerformed(evt);
            }
        });

        lblDX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDX.setText("[Đăng xuất]");

        txtUserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtUserName.setText("jLabel1");

        txtImg.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtImg.setText("img");

        javax.swing.GroupLayout pnlCNLayout = new javax.swing.GroupLayout(pnlCN);
        pnlCN.setLayout(pnlCNLayout);
        pnlCNLayout.setHorizontalGroup(
            pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCNLayout.createSequentialGroup()
                .addGroup(pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCNLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQLSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQLHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQLHD1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)))
                    .addGroup(pnlCNLayout.createSequentialGroup()
                        .addGroup(pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCNLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(lblDX, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCNLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(txtImg, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlCNLayout.setVerticalGroup(
            pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCNLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImg, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName))
                .addGap(34, 34, 34)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQLHD1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTK, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(lblDX)
                .addGap(16, 16, 16))
        );

        lblTrangTri.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrangTri.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrangTri.setText("------------------------ ✦✦✦ ------------------------");

        lblTrangTri1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrangTri1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrangTri1.setText(" --------------- ✦✦ --------------- ");

        lblPMQL.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPMQL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPMQL.setText("PHẦN MỀM QUẢN LÝ");

        lblPolycafe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPolycafe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPolycafe.setText("POLYCAFE");

        lblTG.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTG.setText("[Thứ, ngày - giờ]");

        lblTen.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTen.setText("[Xin chao, NameAccount]");

        lblVT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblVT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVT.setText("[Nhân Viên/Admin]");

        lblBQ.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBQ.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBQ.setText("[Bản quyền (POLYCAFE_4KL)]");

        lblFPS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFPS.setText("jLabel1");
        lblFPS.setPreferredSize(new java.awt.Dimension(120, 50));

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(499, 499, 499)
                        .addComponent(lblFPS, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(519, 519, 519))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBQ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTrangTri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTrangTri1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPMQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPolycafe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblVT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(lblFPS, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTrangTri)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTrangTri1)
                .addGap(51, 51, 51)
                .addComponent(lblPMQL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPolycafe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTG)
                .addGap(40, 40, 40)
                .addComponent(lblTen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBQ)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        DAO dao = new DAO();
        User user = dao.getUserByNameAccount(NameAccount);
        if (user != null) {
            new A_TAB2_Order(NameAccount, user.getRoleAccount()).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Hãy đăng nhập để sử dụng chức năng này!", "Waring", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnQLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLSPActionPerformed
        DAO dao = new DAO();
        User user = dao.getUserByNameAccount(NameAccount);
        if (user != null && "Admin".equalsIgnoreCase(user.getRoleAccount())) {
            new A_TAB3_QLSP(NameAccount, user.getRoleAccount()).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Chức năng này chỉ dành cho Admin!", "Waring", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnQLSPActionPerformed

    private void btnQLHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLHDActionPerformed
        DAO dao = new DAO();
        User user = dao.getUserByNameAccount(NameAccount);
        if (user != null && "Admin".equalsIgnoreCase(user.getRoleAccount())) {
            new A_TAB4_QLHD(NameAccount, user.getRoleAccount()).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Chức năng này chỉ dành cho Admin!", "Waring", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnQLHDActionPerformed

    private void btnQLHD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLHD1ActionPerformed
        DAO dao = new DAO();
        User user = dao.getUserByNameAccount(NameAccount);
        if (user != null && "Admin".equalsIgnoreCase(user.getRoleAccount())) {
            new A_TAB5_QLNV(NameAccount, user.getRoleAccount()).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Chức năng này chỉ dành cho Admin!", "Waring", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnQLHD1ActionPerformed

    private void btnTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKActionPerformed
        DAO dao = new DAO();
        User user = dao.getUserByNameAccount(NameAccount);
        if (user != null && "Admin".equalsIgnoreCase(user.getRoleAccount())) {
            new A_TAB6_ThongKe(NameAccount, user.getRoleAccount()).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Chức năng này chỉ dành cho Admin!", "Waring", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnTKActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new A_TAB1_TrangChu("Null", "???").setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnQLHD;
    private javax.swing.JButton btnQLHD1;
    private javax.swing.JButton btnQLSP;
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JLabel lblBQ;
    private javax.swing.JLabel lblDX;
    private javax.swing.JLabel lblFPS;
    private javax.swing.JLabel lblPMQL;
    private javax.swing.JLabel lblPolycafe;
    private javax.swing.JLabel lblTG;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblTrangTri;
    private javax.swing.JLabel lblTrangTri1;
    private javax.swing.JLabel lblVT;
    private javax.swing.JPanel pnlCN;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JLabel txtImg;
    private javax.swing.JLabel txtUserName;
    // End of variables declaration//GEN-END:variables
}
