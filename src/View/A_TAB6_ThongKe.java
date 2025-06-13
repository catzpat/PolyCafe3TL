package View;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import Controller.DAO;
import java.awt.Color;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class A_TAB6_ThongKe extends javax.swing.JFrame {

    private String NameAccount = "";
    private String RoleAccount;
    private DAO dao = new DAO();

    private String formatTien(int soTien) {
        DecimalFormatSymbols s = new DecimalFormatSymbols();
        s.setGroupingSeparator(' ');
        return new DecimalFormat("#,###", s).format(soTien); // Định dạng tiền VD: "20 000"
    }

    public A_TAB6_ThongKe(String NameAccount, String RoleAccount) {
        this.NameAccount = NameAccount;
        this.RoleAccount = RoleAccount;
        initComponents();
        initUI();
        loadThongTinThongKe();
    }

    public void initUI() {
        setTitle("4KL_Thống Kê");
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("src/USER_IMG/default.jpg");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        txtImg.setText("");
        txtImg.setIcon(new ImageIcon(scaledImg));
        txtUserName.setText(NameAccount);
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

    private void loadThongTinThongKe() {
        // Doanh thu
        int doanhThu = dao.getTongDoanhThu();
        int tongSP = dao.getTongSanPhamBan();
        int tongHD = dao.getTongSoHoaDon();

        lblDT.setText("TỔNG DOANH THU: " + formatTien(doanhThu));
        lblSanPham.setText("TỔNG SẢN PHẨM: " + tongSP);
        lblHoaDon.setText("TỔNG HÓA ĐƠN: " + tongHD);

        // Hóa đơn
        int tongDon = dao.demTongSoDonHang();
        int donCho = dao.demHoaDonCho();
        int daThanhToan = dao.demHoaDonDaThanhToan();

        lblHD.setText("TỔNG ĐƠN: " + tongDon);
        lblCho.setText("HÓA ĐƠN CHỜ: " + donCho);
        lblThanhToan.setText("ĐÃ THANH TOÁN: " + daThanhToan);

        // Bảng sản phẩm bán chạy
        List<Object[]> list = dao.thongKeSanPham();
        DefaultTableModel model = (DefaultTableModel) tblSP.getModel();
        model.setRowCount(0);

        for (Object[] row : list) {
            String maSP = (String) row[0];
            String tenSP = (String) row[1];
            String loai = (String) row[2];
            int gia = (int) row[3];
            String tenAnh = (String) row[4];
            int trangThai = Integer.parseInt(row[5].toString());
            int soLuong = (int) row[6];

            // Load ảnh từ thư mục
            String imagePath = "src/STOCK_IMG/" + tenAnh;
            File imgFile = new File(imagePath);
            ImageIcon icon;
            if (imgFile.exists()) {
                Image img = new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icon = new ImageIcon(img);
            } else {
                icon = new ImageIcon(); // Ảnh trống nếu không tồn tại
            }

            // Format giá tiền & trạng thái
            String giaFormatted = formatTien(gia); // Dinh dạng tiền ("20 000")
            String trangThaiText = (trangThai == 0) ? "Đang bán" : "Ngừng bán";

            model.addRow(new Object[]{
                maSP, icon, tenSP, loai, giaFormatted, trangThaiText, soLuong
            });
        }

        // Set renderer ảnh
        tblSP.setRowHeight(55);
        tblSP.getColumnModel().getColumn(1).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    return new javax.swing.JLabel((ImageIcon) value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
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
        pnl_DT = new javax.swing.JPanel();
        pnlDoanhThu = new javax.swing.JPanel();
        lblDT = new javax.swing.JLabel();
        pnlDH = new javax.swing.JPanel();
        lblSanPham = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlTSP = new javax.swing.JPanel();
        lblHoaDon = new javax.swing.JLabel();
        cbxThoiGian = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        pnl_HD = new javax.swing.JPanel();
        pnlTongHD = new javax.swing.JPanel();
        lblHD = new javax.swing.JLabel();
        pnlHD = new javax.swing.JPanel();
        lblCho = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlTBD = new javax.swing.JPanel();
        lblThanhToan = new javax.swing.JLabel();
        cbxThoiGian1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(223, 255, 214));
        pnlMain.setMaximumSize(new java.awt.Dimension(1065, 780));
        pnlMain.setMinimumSize(new java.awt.Dimension(1065, 780));

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
                .addGap(30, 30, 30)
                .addGroup(pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImg, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName))
                .addGap(29, 29, 29)
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

        pnlDoanhThu.setBackground(new java.awt.Color(255, 248, 225));

        lblDT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDT.setText("[TỔNG DOANH THU]");

        javax.swing.GroupLayout pnlDoanhThuLayout = new javax.swing.GroupLayout(pnlDoanhThu);
        pnlDoanhThu.setLayout(pnlDoanhThuLayout);
        pnlDoanhThuLayout.setHorizontalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDT, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDoanhThuLayout.setVerticalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(lblDT)
                .addGap(27, 27, 27))
        );

        pnlDH.setBackground(new java.awt.Color(255, 248, 225));

        lblSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSanPham.setText("[TỔNG SỐ SP]");

        javax.swing.GroupLayout pnlDHLayout = new javax.swing.GroupLayout(pnlDH);
        pnlDH.setLayout(pnlDHLayout);
        pnlDHLayout.setHorizontalGroup(
            pnlDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDHLayout.setVerticalGroup(
            pnlDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDHLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSanPham)
                .addGap(34, 34, 34))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("DOANH THU");

        pnlTSP.setBackground(new java.awt.Color(255, 248, 225));

        lblHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoaDon.setText("[HD]");

        javax.swing.GroupLayout pnlTSPLayout = new javax.swing.GroupLayout(pnlTSP);
        pnlTSP.setLayout(pnlTSPLayout);
        pnlTSPLayout.setHorizontalGroup(
            pnlTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTSPLayout.setVerticalGroup(
            pnlTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTSPLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblHoaDon)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        cbxThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày", "Tuần", "Tháng" }));
        cbxThoiGian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxThoiGianActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_DTLayout = new javax.swing.GroupLayout(pnl_DT);
        pnl_DT.setLayout(pnl_DTLayout);
        pnl_DTLayout.setHorizontalGroup(
            pnl_DTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_DTLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(pnl_DTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_DTLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_DTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pnlDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_DTLayout.createSequentialGroup()
                            .addComponent(pnlDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pnlTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        pnl_DTLayout.setVerticalGroup(
            pnl_DTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_DTLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnl_DTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_DTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THỐNG KÊ");

        pnlTongHD.setBackground(new java.awt.Color(255, 248, 225));

        lblHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHD.setText("[TỔNG SỐ HD]");

        javax.swing.GroupLayout pnlTongHDLayout = new javax.swing.GroupLayout(pnlTongHD);
        pnlTongHD.setLayout(pnlTongHDLayout);
        pnlTongHDLayout.setHorizontalGroup(
            pnlTongHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongHDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHD, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTongHDLayout.setVerticalGroup(
            pnlTongHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongHDLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblHD)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pnlHD.setBackground(new java.awt.Color(255, 248, 225));

        lblCho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCho.setText("[CHỜ]");

        javax.swing.GroupLayout pnlHDLayout = new javax.swing.GroupLayout(pnlHD);
        pnlHD.setLayout(pnlHDLayout);
        pnlHDLayout.setHorizontalGroup(
            pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCho, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHDLayout.setVerticalGroup(
            pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHDLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblCho)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("HÓA ĐƠN");

        pnlTBD.setBackground(new java.awt.Color(255, 248, 225));

        lblThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThanhToan.setText("[ĐÃ THANH TOÁN]");

        javax.swing.GroupLayout pnlTBDLayout = new javax.swing.GroupLayout(pnlTBD);
        pnlTBD.setLayout(pnlTBDLayout);
        pnlTBDLayout.setHorizontalGroup(
            pnlTBDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTBDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTBDLayout.setVerticalGroup(
            pnlTBDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTBDLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblThanhToan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbxThoiGian1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày", "Tuần", "Tháng" }));
        cbxThoiGian1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxThoiGian1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_HDLayout = new javax.swing.GroupLayout(pnl_HD);
        pnl_HD.setLayout(pnl_HDLayout);
        pnl_HDLayout.setHorizontalGroup(
            pnl_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_HDLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(pnl_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_HDLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxThoiGian1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_HDLayout.createSequentialGroup()
                        .addComponent(pnlHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlTBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        pnl_HDLayout.setVerticalGroup(
            pnl_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_HDLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnl_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxThoiGian1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_HDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Ảnh SP", "Tên SP", "Loại SP", "Giá", "Trạng thái", "Số lượng bán"
            }
        ));
        jScrollPane1.setViewportView(tblSP);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SẢN PHẨM");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addComponent(pnl_DT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnl_HD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnl_DT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_HD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnQLHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLHDActionPerformed
        new A_TAB4_QLHD(NameAccount, RoleAccount).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQLHDActionPerformed

    private void btnQLHD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLHD1ActionPerformed
        new A_TAB5_QLNV(NameAccount, RoleAccount).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQLHD1ActionPerformed

    private void cbxThoiGianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxThoiGianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxThoiGianActionPerformed

    private void cbxThoiGian1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxThoiGian1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxThoiGian1ActionPerformed

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
            java.util.logging.Logger.getLogger(A_TAB6_ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(A_TAB6_ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(A_TAB6_ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(A_TAB6_ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new A_TAB6_ThongKe("", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnQLHD;
    private javax.swing.JButton btnQLHD1;
    private javax.swing.JButton btnQLSP;
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JComboBox<String> cbxThoiGian;
    private javax.swing.JComboBox<String> cbxThoiGian1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblCho;
    private javax.swing.JLabel lblDT;
    private javax.swing.JLabel lblHD;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JPanel pnlCN;
    private javax.swing.JPanel pnlDH;
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlHD;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTBD;
    private javax.swing.JPanel pnlTSP;
    private javax.swing.JPanel pnlTongHD;
    private javax.swing.JPanel pnl_DT;
    private javax.swing.JPanel pnl_HD;
    private javax.swing.JTable tblSP;
    private javax.swing.JLabel txtImg;
    private javax.swing.JLabel txtUserName;
    // End of variables declaration//GEN-END:variables
}
