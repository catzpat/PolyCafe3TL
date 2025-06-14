package View;

import Controller.DAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class A_TAB4_QLHD extends javax.swing.JFrame {

    // Biến toàn cục
    private String NameAccount;
    private String RoleAccount;
    private DAO dao = new DAO();

    // Hàm khởi tạo
    public A_TAB4_QLHD(String NameAccount, String RoleAccount) {
        this.NameAccount = NameAccount;
        this.RoleAccount = RoleAccount;
        initComponents();
        InitUI();
        LoadAllHoaDon();
        GanSuKienBangHoaDon();
        jLabel6.setText("2");
    }

    // Thiết lập giao diện ban đầu
    public void InitUI() {
        setTitle("4KL_Quản Lý Hóa Đơn");
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/USER_IMG/default.jpg");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        txtImg.setText("");
        txtImg.setIcon(new ImageIcon(scaledImg));
        txtUserName.setText(NameAccount);

        // Hiển thị số liệu thống kê tổng quan
        LoadThongTinTongQuan();
        lblTHD.setText("TỔNG SỐ ĐƠN");
        lblHDC.setText("HÓA ĐƠN CHỜ");
        lblDTT.setText("HÓA ĐƠN ĐÃ THANH TOÁN");

        tblHD.setDefaultEditor(Object.class, null);
        
        
    }

    // Hiển thị số liệu ở 3 panel: tổng hóa đơn, đang chờ, đã thanh toán
    private void LoadThongTinTongQuan() {
        int tongSoDon = dao.demTongSoDonHang();
        int tongHoaDonCho = dao.demHoaDonCho();
        int tongHoaDonThanhToan = dao.demHoaDonDaThanhToan();

        jLabel3.setText(String.valueOf(tongSoDon));
        jLabel4.setText(String.valueOf(tongHoaDonCho));
        lblDTT.setText(String.valueOf(tongHoaDonThanhToan));
        
    }

    // Load toàn bộ danh sách hóa đơn ban đầu
    private void LoadAllHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tblHD.getModel();
        model.setRowCount(0);

        List<Object[]> ds = dao.getAllHoaDon();

        for (Object[] row : ds) {
            model.addRow(row);
        }
    }

    // Tìm hóa đơn theo mã nhập vào
    private void LoadHoaDonTheoMa(String maHD) {
        DefaultTableModel model = (DefaultTableModel) tblHD.getModel();
        model.setRowCount(0);

        List<Object[]> ds = dao.selectHoaDonByMa(maHD);

        for (Object[] row : ds) {
            model.addRow(row);
        }

        if (ds.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn với mã: " + maHD);
        }
    }

    // Lọc hóa đơn theo trạng thái, hình thức thanh toán và ngày
    private void LocHoaDon() {
        String trangThai = cbxTTHD.getSelectedItem().toString();
        String hinhThuc = cbxHTTT.getSelectedItem().toString();
        java.util.Date utilDate = jdcNgay.getDate();
        java.sql.Date ngayChon = (utilDate != null) ? new java.sql.Date(utilDate.getTime()) : null;

        DefaultTableModel model = (DefaultTableModel) tblHD.getModel();
        model.setRowCount(0);

        List<Object[]> ds = dao.locHoaDon(trangThai, hinhThuc, ngayChon);

        for (Object[] row : ds) {
            model.addRow(row);
        }

        if (ds.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có hóa đơn nào khớp với điều kiện lọc");
        }
    }

    // Click chuột vào bảng hóa đơn
    private void GanSuKienBangHoaDon() {
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = tblHD.getSelectedRow();
                    if (row >= 0) {
                        String maHD = tblHD.getValueAt(row, 0).toString();
                        new CN_T4_HDCT(maHD).setVisible(true);
                    }
                }
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
        txtUserName = new javax.swing.JLabel();
        txtImg = new javax.swing.JLabel();
        pnlHD = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlTSDH = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblTHD = new javax.swing.JLabel();
        lblTongSoHD = new javax.swing.JLabel();
        pnlDHC = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblHDC = new javax.swing.JLabel();
        pnlDTT = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblDTT = new javax.swing.JLabel();
        tblQLHD = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        btnTimSP = new javax.swing.JButton();
        txtTimSP = new javax.swing.JTextField();
        jdcNgay = new com.toedter.calendar.JDateChooser();
        cbxTTHD = new javax.swing.JComboBox<>();
        cbxHTTT = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(223, 255, 214));
        pnlMain.setMaximumSize(new java.awt.Dimension(1485, 780));
        pnlMain.setMinimumSize(new java.awt.Dimension(1485, 780));
        pnlMain.setRequestFocusEnabled(false);
        pnlMain.setVerifyInputWhenFocusTarget(false);

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

        txtUserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtUserName.setText("jLabel1");

        txtImg.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtImg.setText("img");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCNLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtImg, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        pnlCNLayout.setVerticalGroup(
            pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCNLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImg, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName))
                .addGap(26, 26, 26)
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

        pnlHD.setBackground(new java.awt.Color(223, 255, 214));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH HÓA ĐƠN");

        pnlTSDH.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("100");

        lblTHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTHD.setText("[TỔNG SỐ HD]");

        javax.swing.GroupLayout pnlTSDHLayout = new javax.swing.GroupLayout(pnlTSDH);
        pnlTSDH.setLayout(pnlTSDHLayout);
        pnlTSDHLayout.setHorizontalGroup(
            pnlTSDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTSDHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblTHD, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
        );
        pnlTSDHLayout.setVerticalGroup(
            pnlTSDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTSDHLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTHD)
                .addGap(12, 12, 12))
        );

        lblTongSoHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongSoHD.setText("[TỔNG SỐ ĐƠN]");

        pnlDHC.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("2");

        lblHDC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHDC.setText("[HDC]");

        javax.swing.GroupLayout pnlDHCLayout = new javax.swing.GroupLayout(pnlDHC);
        pnlDHC.setLayout(pnlDHCLayout);
        pnlDHCLayout.setHorizontalGroup(
            pnlDHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
            .addComponent(lblHDC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDHCLayout.setVerticalGroup(
            pnlDHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDHCLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHDC)
                .addGap(12, 12, 12))
        );

        pnlDTT.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("100");

        lblDTT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDTT.setText("[DTT]");

        javax.swing.GroupLayout pnlDTTLayout = new javax.swing.GroupLayout(pnlDTT);
        pnlDTT.setLayout(pnlDTTLayout);
        pnlDTTLayout.setHorizontalGroup(
            pnlDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDTT, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDTTLayout.setVerticalGroup(
            pnlDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDTTLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDTT)
                .addGap(12, 12, 12))
        );

        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ HÓA ĐƠN", "NGÀY LẬP", "TỔNG TIỀN", "TRẠNG THÁI", "THANH TOÁN"
            }
        ));
        tblQLHD.setViewportView(tblHD);

        btnTimSP.setText("Tìm");
        btnTimSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSPActionPerformed(evt);
            }
        });

        cbxTTHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hóa đơn chờ", "Đã thanh toán" }));
        cbxTTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTTHDActionPerformed(evt);
            }
        });

        cbxHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Tiền mặt", "Chuyển khoản" }));
        cbxHTTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxHTTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHDLayout = new javax.swing.GroupLayout(pnlHD);
        pnlHD.setLayout(pnlHDLayout);
        pnlHDLayout.setHorizontalGroup(
            pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblQLHD, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlHDLayout.createSequentialGroup()
                .addGroup(pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHDLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlHDLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jdcNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(cbxTTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(cbxHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHDLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlTSDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlDHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlDTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(304, 304, 304))
            .addGroup(pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlHDLayout.createSequentialGroup()
                    .addGap(532, 532, 532)
                    .addComponent(lblTongSoHD, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addGap(532, 532, 532)))
        );
        pnlHDLayout.setVerticalGroup(
            pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHDLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGroup(pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHDLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(pnlTSDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHDLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlDHC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlDTT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimSP))
                    .addComponent(jdcNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxTTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(tblQLHD, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
            .addGroup(pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlHDLayout.createSequentialGroup()
                    .addGap(382, 382, 382)
                    .addComponent(lblTongSoHD)
                    .addContainerGap(382, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSPActionPerformed
        String maHD = txtTimSP.getText().trim();
        if (maHD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn cần tìm!");
            return;
        }
        LoadHoaDonTheoMa(maHD);
    }//GEN-LAST:event_btnTimSPActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        new A_TAB1_TrangChu(NameAccount, RoleAccount).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        new A_TAB2_Order(NameAccount, RoleAccount).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnQLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLSPActionPerformed
        new A_TAB3_QLSP(NameAccount, RoleAccount).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQLSPActionPerformed

    private void btnQLHD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLHD1ActionPerformed
        new A_TAB5_QLNV(NameAccount, RoleAccount).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQLHD1ActionPerformed

    private void btnTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKActionPerformed
        new A_TAB6_ThongKe(NameAccount, RoleAccount).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTKActionPerformed

    private void cbxTTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTTHDActionPerformed
        LocHoaDon();
    }//GEN-LAST:event_cbxTTHDActionPerformed

    private void cbxHTTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxHTTTActionPerformed
        LocHoaDon();
    }//GEN-LAST:event_cbxHTTTActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new A_TAB4_QLHD("", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnQLHD;
    private javax.swing.JButton btnQLHD1;
    private javax.swing.JButton btnQLSP;
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnTimSP;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JComboBox<String> cbxHTTT;
    private javax.swing.JComboBox<String> cbxTTHD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private com.toedter.calendar.JDateChooser jdcNgay;
    private javax.swing.JLabel lblDTT;
    private javax.swing.JLabel lblHDC;
    private javax.swing.JLabel lblTHD;
    private javax.swing.JLabel lblTongSoHD;
    private javax.swing.JPanel pnlCN;
    private javax.swing.JPanel pnlDHC;
    private javax.swing.JPanel pnlDTT;
    private javax.swing.JPanel pnlHD;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTSDH;
    private javax.swing.JTable tblHD;
    private javax.swing.JScrollPane tblQLHD;
    private javax.swing.JLabel txtImg;
    private javax.swing.JTextField txtTimSP;
    private javax.swing.JLabel txtUserName;
    // End of variables declaration//GEN-END:variables
}
