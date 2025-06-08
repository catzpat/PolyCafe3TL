package View;

import Controller.DAO;
import Model.Products;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAB2_Order extends javax.swing.JFrame {

    /* ==================== FIELD ==================== */
    private String NameAccount = "";
    private List<Object[]> danhSachTam = new ArrayList<>();
    private int soHoaDon = 1;
    private List<Products> danhSachSanPham = new ArrayList<>();


    /* ==================== FORMAT TIỀN ==================== */
    private String formatTien(int soTien) {
        DecimalFormatSymbols s = new DecimalFormatSymbols();
        s.setGroupingSeparator(' ');
        return new DecimalFormat("#,###", s).format(soTien); // Định dạng tiền VD: "20 000"
    }

    public TAB2_Order(String NameAccount) {
        this.NameAccount = NameAccount;
        initComponents();
        initUI();                // Load SP từ DB
        btnMoi.doClick();        // Reset hóa đơn
        setTitle("4KL_Seller");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        txtUserName.setText("Xin chào, " + NameAccount);
        txtMaNV.setText("NVBH: " + NameAccount);

        /* Xoá dòng trống mặc định trong 2 bảng */
        ((DefaultTableModel) tblTTHD.getModel()).setRowCount(0);
        ((DefaultTableModel) tblHDC.getModel()).setRowCount(0);

        /* Mặc định các ô tổng tiền = 0 */
        jTextField1.setText("0");
        jTextField2.setText("0");
        jTextField3.setText("0");
        jTextField4.setText("");
        jTextField5.setText("0");
    }

    /* ==================== LOAD SẢN PHẨM TỪ DB ==================== */
    private void initUI() {
        DAO dao = new DAO();
        danhSachSanPham = dao.getAllProducts();

        pnlSP.setLayout(new GridLayout(0, 2, 10, 10));
        hienThiSanPham(danhSachSanPham);

        /* ==== xử lý click bảng HĐ chờ → load lại ==== */
        tblHDC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xuLyLoadHoaDonCho();
            }
        });

        /* ==== tự tính tiền trả lại khi nhập tiền mặt ==== */
        jTextField4.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                capNhatTienTraLai();
            }

            public void removeUpdate(DocumentEvent e) {
                capNhatTienTraLai();
            }

            public void changedUpdate(DocumentEvent e) {
                capNhatTienTraLai();
            }
        });
    }

    private void hienThiSanPham(List<Products> ds) {
        pnlSP.removeAll();
        for (Products sp : ds) {
            JPanel card = taoCardSanPham(sp);
            pnlSP.add(card);
        }
        pnlSP.revalidate();
        pnlSP.repaint();
    }

    /* ======= CARD SẢN PHẨM (ảnh + tên + giá + +/-) ======= */
    private JPanel taoCardSanPham(Products sp) {
        // === Card tổng ===
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setPreferredSize(new Dimension(280, 130));
        card.setBackground(new Color(240, 240, 240));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Ảnh sản phẩm
        JLabel lblImg = new JLabel();
        lblImg.setHorizontalAlignment(SwingConstants.CENTER);
        lblImg.setVerticalAlignment(SwingConstants.CENTER);
        lblImg.setPreferredSize(new Dimension(120, 120));

        try {
            ImageIcon icon = new ImageIcon("src/STOCK_IMG/" + sp.getHinhAnh());
            Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblImg.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblImg.setText("No image");
            lblImg.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            lblImg.setForeground(Color.GRAY);
        }

        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.setPreferredSize(new Dimension(130, 130));
        imgPanel.setBackground(new Color(240, 240, 240));
        imgPanel.add(lblImg, BorderLayout.CENTER);
        card.add(imgPanel, BorderLayout.WEST);

        // Thông tin sản phẩm
        JLabel lblTen = new JLabel(sp.getTenSP());
        lblTen.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTen.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblGia = new JLabel(formatTien(sp.getGia()) + "");
        lblGia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblGia.setForeground(Color.DARK_GRAY);
        lblGia.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSL = new JLabel("0");
        lblSL.setPreferredSize(new Dimension(30, 25));
        lblSL.setHorizontalAlignment(SwingConstants.CENTER);
        lblSL.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSL.setName("lblSoLuong"); // để reset số lượng khi xóa

        JButton btnMinus = new JButton("-");
        JButton btnPlus = new JButton("+");

        // Xử lý nút cộng trừ
        btnPlus.addActionListener(e -> {
            int sl = Integer.parseInt(lblSL.getText()) + 1;
            lblSL.setText(String.valueOf(sl));
            capNhatBangTTHD(sp.getTenSP(), sp.getGia(), sl);
        });

        btnMinus.addActionListener(e -> {
            int sl = Integer.parseInt(lblSL.getText());
            if (sl > 0) {
                sl--;
                lblSL.setText(String.valueOf(sl));
                capNhatBangTTHD(sp.getTenSP(), sp.getGia(), sl);
            }
        });

        // Panel số lượng và nút
        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        qtyPanel.setBackground(new Color(240, 240, 240));
        qtyPanel.add(btnMinus);
        qtyPanel.add(lblSL);
        qtyPanel.add(btnPlus);
        qtyPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Gộp tất cả thông tin lại
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(240, 240, 240));

        // Thêm căn giữa dọc
        infoPanel.add(Box.createVerticalGlue()); // đẩy xuống

        infoPanel.add(lblTen);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lblGia);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(qtyPanel);

        infoPanel.add(Box.createVerticalGlue()); // giữ giữa

        card.add(infoPanel, BorderLayout.CENTER);

        return card;
    }

    private void xuLyLoadHoaDonCho() {
        int row = tblHDC.getSelectedRow();
        if (row < 0 || row >= danhSachTam.size()) {
            return;
        }

        Object[] hdTam = danhSachTam.get(row);
        Object[][] data = (Object[][]) hdTam[0];
        int tienMat = (int) hdTam[1];

        // Reset bảng TTHD & số lượng SP
        DefaultTableModel model = (DefaultTableModel) tblTTHD.getModel();
        model.setRowCount(0);
        // reset label số lượng nếu bạn có nhiều SP ⇒ tự xử lý ...

        for (Object[] d : data) {
            model.addRow(d);
        }

        jTextField4.setText(String.valueOf(tienMat));
        capNhatTienTraLai();
        tinhTongTien();

        lblMaHoaDon.setText("HD" + soHoaDon++);
        lblNgayGio.setText(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));

        // Xoá khỏi hàng bảng chờ
        ((DefaultTableModel) tblHDC.getModel()).removeRow(row);
        danhSachTam.remove(row);
    }

    /* ======================================== */
    private void capNhatBangTTHD(String tenSP, int donGia, int soLuongMoi) {
        DefaultTableModel model = (DefaultTableModel) tblTTHD.getModel();
        boolean daCo = false;

        for (int i = 0; i < model.getRowCount(); i++) {
            if (tenSP.equals(model.getValueAt(i, 0))) {
                daCo = true;

                if (soLuongMoi == 0) {
                    model.removeRow(i);
                } else {
                    model.setValueAt(formatTien(donGia), i, 1); // đơn giá
                    model.setValueAt(soLuongMoi, i, 2);         // số lượng
                    model.setValueAt(formatTien(donGia * soLuongMoi), i, 3); // thành tiền
                }
                break;
            }
        }

        if (!daCo && soLuongMoi > 0) {
            model.addRow(new Object[]{
                tenSP,
                formatTien(donGia),
                soLuongMoi,
                formatTien(donGia * soLuongMoi)
            });
        }

        tinhTongTien();
    }

    private void tinhTongTien() {
        DefaultTableModel model = (DefaultTableModel) tblTTHD.getModel();
        int tong = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            String tienStr = model.getValueAt(i, 3).toString().replace(" ", "");
            tong += Integer.parseInt(tienStr);
        }

        jTextField1.setText(formatTien(tong)); // Tổng tiền sản phẩm
        jTextField2.setText("0");              // Tổng giảm (mặc định 0)
        jTextField3.setText(formatTien(tong)); // Tổng thanh toán
        capNhatTienTraLai(); // tính lại tiền trả lại nếu có nhập tiền mặt
    }

    private void capNhatTienTraLai() {
        try {
            String thanhToanStr = jTextField3.getText().replace(" ", "");
            String tienMatStr = jTextField4.getText().replace(" ", "");

            int thanhToan = Integer.parseInt(thanhToanStr);
            int tienMat = tienMatStr.isEmpty() ? 0 : Integer.parseInt(tienMatStr);

            int traLai = tienMat >= thanhToan ? tienMat - thanhToan : 0;
            jTextField5.setText(formatTien(traLai));
        } catch (NumberFormatException e) {
            jTextField5.setText("0");
        }
    }

    private int parseTien(String txt) {
        try {
            return Integer.parseInt(txt.replace("đ", "").replace(" ", "").trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private void resetSoLuongTatCaSanPham() {
        for (Component comp : pnlSP.getComponents()) {
            if (comp instanceof JPanel cardPanel) {
                Component[] cardChildren = cardPanel.getComponents();
                for (Component cardChild : cardChildren) {
                    if (cardChild instanceof JPanel infoPanel) {
                        Component[] infoChildren = infoPanel.getComponents();
                        for (Component infoChild : infoChildren) {
                            if (infoChild instanceof JPanel qtyPanel) {
                                Component[] qtyChildren = qtyPanel.getComponents();
                                for (Component qtyChild : qtyChildren) {
                                    if (qtyChild instanceof JLabel lbl
                                            && "lblSoLuong".equals(lbl.getName())) {
                                        lbl.setText("0");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        pnlMain = new javax.swing.JPanel();
        pnlCN = new javax.swing.JPanel();
        txtUserName = new javax.swing.JLabel();
        btnTrangChu = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        btnQLSP = new javax.swing.JButton();
        btnQLHD = new javax.swing.JButton();
        btnTK = new javax.swing.JButton();
        btnQLHD1 = new javax.swing.JButton();
        pnlHD = new javax.swing.JPanel();
        pnlHDC = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDC = new javax.swing.JTable();
        pnlTTHD = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblNgayGio = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTTHD = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JLabel();
        pnlDSSP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlChucNang = new javax.swing.JPanel();
        btnIn = new javax.swing.JButton();
        btnthanhToan = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCho = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlSP = new javax.swing.JPanel();
        cbxLoaiSP = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(223, 255, 214));
        pnlMain.setMaximumSize(new java.awt.Dimension(1485, 780));
        pnlMain.setMinimumSize(new java.awt.Dimension(1485, 780));
        pnlMain.setPreferredSize(new java.awt.Dimension(1485, 780));

        pnlCN.setBackground(new java.awt.Color(215, 204, 200));

        txtUserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtUserName.setText("jLabel1");

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCNLayout.createSequentialGroup()
                        .addGap(0, 36, Short.MAX_VALUE)
                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnQLHD1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlCNLayout.setVerticalGroup(
            pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCNLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(txtUserName)
                .addGap(61, 61, 61)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlHD.setBackground(new java.awt.Color(255, 248, 225));

        pnlHDC.setBackground(new java.awt.Color(255, 248, 225));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("HÓA ĐƠN CHỜ");

        tblHDC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã HĐ", "Thời Gian", "NV"
            }
        ));
        jScrollPane1.setViewportView(tblHDC);

        javax.swing.GroupLayout pnlHDCLayout = new javax.swing.GroupLayout(pnlHDC);
        pnlHDC.setLayout(pnlHDCLayout);
        pnlHDCLayout.setHorizontalGroup(
            pnlHDCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHDCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );
        pnlHDCLayout.setVerticalGroup(
            pnlHDCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHDCLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTTHD.setBackground(new java.awt.Color(255, 248, 225));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("THÔNG TIN HÓA ĐƠN");

        lblNgayGio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayGio.setText("[Ngày và giờ]");

        lblMaHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaHoaDon.setText("[Mã Hóa Đơn]");

        tblTTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, "", null},
                {null, null, "", null},
                {null, null, "", null},
                {null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ));
        jScrollPane3.setViewportView(tblTTHD);

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText("jTextField1");

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText("jTextField2");

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setText("jTextField3");

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("NV Nhập");

        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField5.setText("jTextField5");

        jLabel17.setText("Tổng tiền thanh toán:");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("-----------------------------------------------------------");

        jLabel19.setText("Tổng tiền giảm:");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("-----------------------------------------------------------");

        jLabel21.setText("Tiền mặt:");

        jLabel22.setText("  Tổng tiền sản phẩm");

        jLabel23.setText("Tiền trả lại:");

        txtMaNV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtMaNV.setText("NVBH");

        javax.swing.GroupLayout pnlTTHDLayout = new javax.swing.GroupLayout(pnlTTHD);
        pnlTTHD.setLayout(pnlTTHDLayout);
        pnlTTHDLayout.setHorizontalGroup(
            pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTTHDLayout.createSequentialGroup()
                        .addComponent(lblNgayGio, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTTHDLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlTTHDLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTTHDLayout.createSequentialGroup()
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(pnlTTHDLayout.createSequentialGroup()
                                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))
                    .addGroup(pnlTTHDLayout.createSequentialGroup()
                        .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(pnlTTHDLayout.createSequentialGroup()
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        pnlTTHDLayout.setVerticalGroup(
            pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTTHDLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNgayGio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaHoaDon)
                    .addComponent(txtMaNV))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout pnlHDLayout = new javax.swing.GroupLayout(pnlHD);
        pnlHD.setLayout(pnlHDLayout);
        pnlHDLayout.setHorizontalGroup(
            pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTTHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlHDC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlHDLayout.setVerticalGroup(
            pnlHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHDLayout.createSequentialGroup()
                .addComponent(pnlHDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(65, 65, 65))
        );

        pnlDSSP.setBackground(new java.awt.Color(223, 255, 214));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH SẢN PHẨM");

        pnlChucNang.setBackground(new java.awt.Color(223, 255, 214));

        btnIn.setText("In Hóa Đơn");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        btnthanhToan.setText("Thanh Toán");
        btnthanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthanhToanActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnCho.setText("Chờ");
        btnCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChoActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlChucNangLayout = new javax.swing.GroupLayout(pnlChucNang);
        pnlChucNang.setLayout(pnlChucNangLayout);
        pnlChucNangLayout.setHorizontalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChucNangLayout.createSequentialGroup()
                .addContainerGap(191, Short.MAX_VALUE)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCho, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnthanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        pnlChucNangLayout.setVerticalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCho, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pnlSP.setBackground(new java.awt.Color(223, 255, 214));

        javax.swing.GroupLayout pnlSPLayout = new javax.swing.GroupLayout(pnlSP);
        pnlSP.setLayout(pnlSPLayout);
        pnlSPLayout.setHorizontalGroup(
            pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
        );
        pnlSPLayout.setVerticalGroup(
            pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(pnlSP);

        cbxLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Trà sữa", "Cà phê", "Nước ép", "Sinh tố" }));
        cbxLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLoaiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDSSPLayout = new javax.swing.GroupLayout(pnlDSSP);
        pnlDSSP.setLayout(pnlDSSPLayout);
        pnlDSSPLayout.setHorizontalGroup(
            pnlDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDSSPLayout.createSequentialGroup()
                .addGroup(pnlDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlChucNang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlDSSPLayout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDSSPLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cbxLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        pnlDSSPLayout.setVerticalGroup(
            pnlDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDSSPLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cbxLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDSSP, javax.swing.GroupLayout.PREFERRED_SIZE, 833, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlCN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlDSSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58))
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

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        String tongTienStr = jTextField1.getText().replace(" ", "");
        if (Integer.parseInt(tongTienStr) == 0) {
            JOptionPane.showMessageDialog(this, "Hóa đơn đang trống!");
            return;
        }

        JOptionPane.showMessageDialog(this, "Đã in hóa đơn!");
        btnMoi.doClick(); // reset sau khi in
        resetSoLuongTatCaSanPham();

    }//GEN-LAST:event_btnInActionPerformed

    private void btnthanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthanhToanActionPerformed
        // KT HD rỗng
        int tongTienSP = parseTien(jTextField1.getText());
        if (tongTienSP == 0) {
            JOptionPane.showMessageDialog(this, "Hóa đơn đang trống!");
            return;
        }

        // 2. Kiểm tra tiền mặt
        int thanhToan = parseTien(jTextField3.getText());
        int tienMat = parseTien(jTextField4.getText());
        if (tienMat < thanhToan) {
            JOptionPane.showMessageDialog(this, "Tiền mặt không đủ để thanh toán.");
            return;
        }

        // 3. Build dữ liệu
        String maHD = lblMaHoaDon.getText();
        int giamGia = parseTien(jTextField2.getText());
        int tienTraLai = parseTien(jTextField5.getText());

        List<Object[]> chiTiet = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) tblTTHD.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            chiTiet.add(new Object[]{
                model.getValueAt(i, 0), // Tên SP
                model.getValueAt(i, 1), // Đơn giá (chuỗi có format)
                model.getValueAt(i, 2), // Số lượng
                model.getValueAt(i, 3) // Thành tiền
            });
        }

        // 4. Ghi DB
        DAO dao = new DAO();
        boolean okHD = dao.insertHoaDon(maHD, NameAccount, tongTienSP,
                giamGia, thanhToan, tienMat, tienTraLai);
        boolean okCT = dao.insertChiTietHoaDon(maHD, chiTiet);

        if (okHD && okCT) {
            JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
            btnMoi.doClick();                // Reset giao diện
            resetSoLuongTatCaSanPham();

        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi khi lưu DB, vui lòng kiểm tra log!");
        }
    }//GEN-LAST:event_btnthanhToanActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
// Nếu bảng tblTTHD và bảng HĐ chờ đều đang rỗng thì không cho xóa
        if (tblTTHD.getRowCount() == 0 && tblHDC.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có hóa đơn nào để xóa!");
            return;
        }

        // Xóa bảng tblTTHD
        DefaultTableModel model = (DefaultTableModel) tblTTHD.getModel();
        model.setRowCount(0);

        // Reset số lượng tất cả sản phẩm về 0
        resetSoLuongTatCaSanPham();

        // Reset lại các field liên quan
        jTextField1.setText("0"); // Tổng tiền SP
        jTextField2.setText("0"); // Giảm giá
        jTextField3.setText("0"); // Thanh toán
        jTextField4.setText("");  // Tiền khách đưa
        jTextField5.setText("0"); // Tiền trả lại

        // Reset mã hóa đơn và thời gian
        if (soHoaDon > 999) {
            soHoaDon = 1;
        }
        lblMaHoaDon.setText("HD" + soHoaDon++);
        lblNgayGio.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChoActionPerformed
        DefaultTableModel modelTTHD = (DefaultTableModel) tblTTHD.getModel();
        if (modelTTHD.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong hóa đơn!");
            return;
        }

        // Lưu dữ liệu bảng TTHD tạm vào danhSachTam
        Object[][] data = new Object[modelTTHD.getRowCount()][4];
        for (int i = 0; i < modelTTHD.getRowCount(); i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = modelTTHD.getValueAt(i, j);
            }
        }

        int tienMat = parseTien(jTextField4.getText());
        danhSachTam.add(new Object[]{data, tienMat});

        // Tạo HĐ chờ
        String maHD = "HD" + soHoaDon++;
        String thoiGian = java.time.LocalTime.now().withNano(0).toString();
        ((DefaultTableModel) tblHDC.getModel()).addRow(new Object[]{maHD, thoiGian, NameAccount});

        // Lưu HĐC vào DB (bảng HoaDonCho) ====
        DAO dao = new DAO();
        dao.insertHoaDonCho(maHD, thoiGian, NameAccount);   // bạn thêm hàm này trong DAO

        JOptionPane.showMessageDialog(this, "Đã chuyển hóa đơn vào hàng chờ!");
        resetSoLuongTatCaSanPham();
        btnMoi.doClick();      // Reset để tạo hóa đơn mới
    }//GEN-LAST:event_btnChoActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        if (tblTTHD.getRowCount() == 0 && tblHDC.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Không có hóa đơn nào để làm mới!");
            return;
        }

        // Reset các ô tổng tiền
        jTextField1.setText("0");
        jTextField2.setText("0");
        jTextField3.setText("0");
        jTextField4.setText("");
        jTextField5.setText("0");

        // Reset bảng sản phẩm đã chọn
        DefaultTableModel model = (DefaultTableModel) tblTTHD.getModel();
        model.setRowCount(0);

        // Reset số lượng sản phẩm
        resetSoLuongTatCaSanPham();

        // Cập nhật ngày giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        String thoiGian = LocalDateTime.now().format(formatter);
        lblNgayGio.setText(thoiGian);

        // Tạo mã hóa đơn mới
        lblMaHoaDon.setText("HD" + soHoaDon++);
    }//GEN-LAST:event_btnMoiActionPerformed

    private void cbxLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLoaiSPActionPerformed
        String loai = cbxLoaiSP.getSelectedItem().toString();
        if (loai.equals("Tất cả")) {
            hienThiSanPham(danhSachSanPham);
        } else {
            List<Products> loc = new ArrayList<>();
            for (Products sp : danhSachSanPham) {
                if (sp.getLoaiSP().equalsIgnoreCase(loai)) {
                    loc.add(sp);
                }
            }
            hienThiSanPham(loc);
        }
    }//GEN-LAST:event_cbxLoaiSPActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TAB2_Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TAB2_Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TAB2_Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TAB2_Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>D
        //</editor-fold>
        //</editor-fold>D

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TAB2_Order("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCho;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnQLHD;
    private javax.swing.JButton btnQLHD1;
    private javax.swing.JButton btnQLSP;
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnthanhToan;
    private javax.swing.JComboBox<String> cbxLoaiSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblNgayGio;
    private javax.swing.JPanel pnlCN;
    private javax.swing.JPanel pnlChucNang;
    private javax.swing.JPanel pnlDSSP;
    private javax.swing.JPanel pnlHD;
    private javax.swing.JPanel pnlHDC;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSP;
    private javax.swing.JPanel pnlTTHD;
    private javax.swing.JTable tblHDC;
    private javax.swing.JTable tblTTHD;
    private javax.swing.JLabel txtMaNV;
    private javax.swing.JLabel txtUserName;
    // End of variables declaration//GEN-END:variables
}
