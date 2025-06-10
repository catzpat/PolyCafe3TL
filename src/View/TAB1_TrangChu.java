package View;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TAB1_TrangChu extends javax.swing.JFrame {

    private String nameAccount;
    private String roleAccount;

    public TAB1_TrangChu(String nameAccount, String roleAccount) {
        this.nameAccount = nameAccount;
        this.roleAccount = roleAccount;

        initComponents();
        setLogo();
        setTime();
        setAccountInfo();
        addLabelEvents();
    }

    private void setLogo() {
        setImageToLabel(lblFPS, "src/STOCK_IMG/Logo_FPS.png");
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
        lblTen.setText("Xin chào, " + nameAccount);
        lblVT.setText(roleAccount);
        lblBQ.setText("© PolyCafe 4KL");
    }

    private void addLabelEvents() {
        makeLabelInteractive(lblDMK, () -> {
            // TODO: Mở giao diện đổi mật khẩu
            System.out.println("Đổi mật khẩu được bấm");
        });

        makeLabelInteractive(lblDX, () -> {
            // Đăng xuất: quay về LoginForm và đóng form hiện tại
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn đăng xuất?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
                new LoginForm().setVisible(true); // mở lại form đăng nhập
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
        lblTrangTri = new javax.swing.JLabel();
        lblTrangTri1 = new javax.swing.JLabel();
        lblPMQL = new javax.swing.JLabel();
        lblPolycafe = new javax.swing.JLabel();
        lblTG = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        lblVT = new javax.swing.JLabel();
        lblDX = new javax.swing.JLabel();
        lblDMK = new javax.swing.JLabel();
        lblBQ = new javax.swing.JLabel();
        lblFPS = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(223, 255, 214));
        pnlMain.setMaximumSize(new java.awt.Dimension(1485, 780));
        pnlMain.setMinimumSize(new java.awt.Dimension(1485, 780));

        pnlCN.setBackground(new java.awt.Color(215, 204, 200));

        btnTrangChu.setText("Trang Chủ");

        btnOrder.setText("Order");

        btnQLSP.setText("Quản Lý Sản Phẩm");

        btnQLHD.setText("Quản Lý Hóa Đơn");

        btnTK.setText("Thống Kê");

        btnQLHD1.setText("Quản Lý Nhân Viên");

        javax.swing.GroupLayout pnlCNLayout = new javax.swing.GroupLayout(pnlCN);
        pnlCN.setLayout(pnlCNLayout);
        pnlCNLayout.setHorizontalGroup(
            pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCNLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQLSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQLHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQLHD1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlCNLayout.setVerticalGroup(
            pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCNLayout.createSequentialGroup()
                .addGap(106, 106, 106)
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
                .addContainerGap(320, Short.MAX_VALUE))
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

        lblDX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDX.setText("[Đăng xuất]");

        lblDMK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDMK.setText("[Đổi mật khẩu]");

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
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTrangTri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTrangTri1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPMQL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPolycafe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblVT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblBQ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDMK, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDX, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(544, 544, 544))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(510, 510, 510)
                        .addComponent(lblFPS, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(514, Short.MAX_VALUE))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(lblFPS, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTrangTri)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTrangTri1)
                .addGap(51, 51, 51)
                .addComponent(lblPMQL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPolycafe)
                .addGap(18, 18, 18)
                .addComponent(lblTG)
                .addGap(40, 40, 40)
                .addComponent(lblTen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVT)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDX)
                    .addComponent(lblDMK))
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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new TAB1_TrangChu("admin", "Quản trị viên").setVisible(true);
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
    private javax.swing.JLabel lblDMK;
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
    // End of variables declaration//GEN-END:variables
}
