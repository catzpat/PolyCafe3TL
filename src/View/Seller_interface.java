package View;

import Controller.DAO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Seller_interface extends javax.swing.JFrame {

    private String NameAccount = "";
    private List<Object[]> danhSachTam = new ArrayList<>();
    private int soHoaDon = 1;

    private String formatTien(int soTien) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("#,###", symbols);
        return df.format(soTien);
    }

    public Seller_interface(String NameAccount) {
        this.NameAccount = NameAccount;
        initComponents();
        initUI();
        btnMoi.doClick();
        setTitle("4KL_Seller");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // set width = max
        setLocationRelativeTo(null);
        txtUserName.setText("Xin Chao, " + NameAccount);
//        ---------------------------------------------------------------------
        DAO dao = new DAO();

        //Xóa dòng rỗng ở bảng khi bắt đầu chạy
        DefaultTableModel model = (DefaultTableModel) tblTTHD.getModel();
        model.setRowCount(0);
        DefaultTableModel modelHDC = (DefaultTableModel) tblHDC.getModel();
        modelHDC.setRowCount(0);

        // Set mặc định jTextField về 0
        jTextField1.setText("0");
        jTextField2.setText("0");
        jTextField3.setText("0");
        jTextField4.setText("");
        jTextField5.setText("0");

    }

    public void initUI() {

        JButton[] buttons = {btnTrangChu, btnOrder, btnQLSP, btnQLHD, btnQLNV, btnTK, jButton1, jButton2, jButton3, jButton4, jButton5, jButton6,
            jButton7, jButton8, jButton9, jButton10, jButton11, jButton12};

        // Duyệt qua từng nút và thiết lập focusPainted
        for (JButton btn : buttons) {
            btn.setFocusPainted(false);
            btn.setBackground(Color.decode("#eeeeee"));
//            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }
        btnOrder.setBackground(Color.decode("#cceeff"));
        String[] tenSP = {
            "Trà sữa", "Trà DCS", "Cà phê Sữa", "Cà Phê", "Nước ép Cam", "Nước ép dưa hấu"
        };
        String[] giaSP = {
            "30000", "25000", "20000", "35000", "22000", "28000"
        };
        String[] anhSP = {
            "src/STOCK_IMG/img1.jpg",
            "src/STOCK_IMG/img2.jpg",
            "src/STOCK_IMG/img3.jpg",
            "src/STOCK_IMG/img4.jpg",
            "src/STOCK_IMG/img5.jpg",
            "src/STOCK_IMG/img6.jpg"
        };

        JPanel[] pnlImgs = {pnlImg1, pnlImg2, pnlImg3, pnlImg4, pnlImg5, pnlImg6};
        JLabel[] lblTenSPs = {lblTenSP1, lblTenSP2, lblTenSP3, lblTenSP4, lblTenSP5, lblTenSP6};
        JLabel[] lblGias = {lblGiaSP1, lblGiaSP2, lblGiaSP3, lblGiaSP4, lblGiaSP5, lblGiaSP6};
        JLabel[] lblSLs = {lblSLSP1, lblSLSP2, lblSLSP3, lblSLSP4, lblSLSP5, lblSLSP6};
        JButton[] btnMinus = {jButton1, jButton3, jButton5, jButton7, jButton9, jButton11};
        JButton[] btnPlus = {jButton2, jButton4, jButton6, jButton8, jButton10, jButton12};

        for (int i = 0; i < 6; i++) {
            ImageIcon icon = new ImageIcon(anhSP[i]);
            Image scaled = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel lblImg = new JLabel(new ImageIcon(scaled));
            lblImg.setHorizontalAlignment(SwingConstants.CENTER);

            pnlImgs[i].setLayout(new BorderLayout());
            pnlImgs[i].add(lblImg, BorderLayout.CENTER);
            pnlImgs[i].revalidate();
            pnlImgs[i].repaint();

            lblTenSPs[i].setText(tenSP[i]);
            lblGias[i].setText(formatTien(Integer.parseInt(giaSP[i])) + " đ");
            lblSLs[i].setText("0");

            int index = i;
            Color originalColor = pnlImgs[i].getBackground();
            pnlImgs[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    pnlImgs[index].setBackground(Color.LIGHT_GRAY);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    pnlImgs[index].setBackground(originalColor);
                }
            });

            btnMinus[i].addActionListener(e -> {
                int current = Integer.parseInt(lblSLs[index].getText());
                if (current > 0) {
                    int newQty = current - 1;
                    lblSLs[index].setText(String.valueOf(newQty));
                    capNhatBangTTHD(tenSP[index], Integer.parseInt(giaSP[index]), newQty);
                }
            });

            btnPlus[i].addActionListener(e -> {
                int current = Integer.parseInt(lblSLs[index].getText());
                int newQty = current + 1;
                lblSLs[index].setText(String.valueOf(newQty));
                capNhatBangTTHD(tenSP[index], Integer.parseInt(giaSP[index]), newQty);
            });
        }

        tblHDC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tblHDC.getSelectedRow();
                if (row >= 0 && row < danhSachTam.size()) {
                    Object[] item = danhSachTam.get(row);
                    Object[][] duLieu = (Object[][]) item[0];
                    int tienMat = (int) item[1];

                    // reset bảng TTHD
                    DefaultTableModel modelTTHD = (DefaultTableModel) tblTTHD.getModel();
                    modelTTHD.setRowCount(0);

                    // reset số lượng sản phẩm
                    JLabel[] lblSLs = {
                        lblSLSP1, lblSLSP2, lblSLSP3,
                        lblSLSP4, lblSLSP5, lblSLSP6
                    };
                    for (JLabel lbl : lblSLs) {
                        lbl.setText("0");
                    }

                    String[] danhSachSP = {
                        "Trà sữa", "Trà DCS", "Cà phê Sữa", "Cà Phê", "Nước ép Cam", "Nước ép dưa hấu"
                    };

                    // load lại hóa đơn
                    for (Object[] dong : duLieu) {
                        modelTTHD.addRow(dong);
                        String tenSP = dong[0].toString();
                        int soLuong = Integer.parseInt(dong[2].toString());

                        for (int i = 0; i < danhSachSP.length; i++) {
                            if (danhSachSP[i].equals(tenSP)) {
                                lblSLs[i].setText(String.valueOf(soLuong));
                                break;
                            }
                        }
                    }

                    // gán lại tiền mặt
                    jTextField4.setText(String.valueOf(tienMat));
                    capNhatTienTraLai();
                    tinhTongTien();

                    // cập nhật lại thời gian và mã hóa đơn mới
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
                    String thoiGian = LocalDateTime.now().format(formatter);
                    lblNgayGio.setText(thoiGian);
                    lblMaHoaDon.setText("HD" + soHoaDon++);

                    // xóa khỏi hàng chờ
                    DefaultTableModel modelHDC = (DefaultTableModel) tblHDC.getModel();
                    modelHDC.removeRow(row);
                    danhSachTam.remove(row);
                }
            }
        });

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

    // ========================================
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
        jTextField2.setText("0");              // Tổng giảm (mặc định)
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCN = new javax.swing.JPanel();
        txtUserName = new javax.swing.JLabel();
        btnTrangChu = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        btnQLSP = new javax.swing.JButton();
        btnQLHD = new javax.swing.JButton();
        btnQLNV = new javax.swing.JButton();
        btnTK = new javax.swing.JButton();
        pnlMain = new javax.swing.JPanel();
        pnlDSSP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlSP = new javax.swing.JPanel();
        pnlSP1 = new javax.swing.JPanel();
        pnlImg1 = new javax.swing.JPanel();
        lblTenSP1 = new javax.swing.JLabel();
        lblGiaSP1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblSLSP1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        pnlSP2 = new javax.swing.JPanel();
        pnlImg2 = new javax.swing.JPanel();
        lblTenSP2 = new javax.swing.JLabel();
        lblGiaSP2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        lblSLSP2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        pnlSP4 = new javax.swing.JPanel();
        pnlImg4 = new javax.swing.JPanel();
        lblTenSP4 = new javax.swing.JLabel();
        lblGiaSP4 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        lblSLSP4 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        pnlSP3 = new javax.swing.JPanel();
        pnlImg3 = new javax.swing.JPanel();
        lblTenSP3 = new javax.swing.JLabel();
        lblGiaSP3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        lblSLSP3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        pnlSP5 = new javax.swing.JPanel();
        pnlImg5 = new javax.swing.JPanel();
        lblTenSP5 = new javax.swing.JLabel();
        lblGiaSP5 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        lblSLSP5 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        pnlSP6 = new javax.swing.JPanel();
        pnlImg6 = new javax.swing.JPanel();
        lblTenSP6 = new javax.swing.JLabel();
        lblGiaSP6 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        lblSLSP6 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        pnlSP7 = new javax.swing.JPanel();
        pnlImg7 = new javax.swing.JPanel();
        lblTenSP7 = new javax.swing.JLabel();
        lblGiaSP7 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        lblSLSP7 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnthanhToan = new javax.swing.JButton();
        btnIn = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCho = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        pnlHDC = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDC = new javax.swing.JTable();
        pnlTTHD = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblNgayGio = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlCN.setBackground(new java.awt.Color(255, 204, 204));

        txtUserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtUserName.setText("jLabel1");

        btnTrangChu.setText("Trang Chủ");

        btnOrder.setText("Order");

        btnQLSP.setText("Quản Lý Sản Phẩm");

        btnQLHD.setText("Quản Lý Hóa Đơn");

        btnQLNV.setText("Quản Lý Nhân Viên");

        btnTK.setText("Thống Kê");

        javax.swing.GroupLayout pnlCNLayout = new javax.swing.GroupLayout(pnlCN);
        pnlCN.setLayout(pnlCNLayout);
        pnlCNLayout.setHorizontalGroup(
            pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCNLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(pnlCNLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQLSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQLNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQLHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlCNLayout.setVerticalGroup(
            pnlCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCNLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtUserName)
                .addGap(67, 67, 67)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQLHD, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnQLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTK, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMain.setBackground(new java.awt.Color(204, 204, 255));
        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDSSP.setBackground(new java.awt.Color(255, 153, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH SẢN PHẨM");

        pnlSP1.setBackground(new java.awt.Color(204, 255, 204));

        pnlImg1.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout pnlImg1Layout = new javax.swing.GroupLayout(pnlImg1);
        pnlImg1.setLayout(pnlImg1Layout);
        pnlImg1Layout.setHorizontalGroup(
            pnlImg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        pnlImg1Layout.setVerticalGroup(
            pnlImg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblTenSP1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenSP1.setText("1");

        lblGiaSP1.setText("jLabel6");

        jButton1.setText("-");

        lblSLSP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSLSP1.setText("0");

        jButton2.setText("+");

        javax.swing.GroupLayout pnlSP1Layout = new javax.swing.GroupLayout(pnlSP1);
        pnlSP1.setLayout(pnlSP1Layout);
        pnlSP1Layout.setHorizontalGroup(
            pnlSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSP1Layout.createSequentialGroup()
                .addComponent(pnlImg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSP1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSLSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSP1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTenSP1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(lblGiaSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSP1Layout.setVerticalGroup(
            pnlSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSP1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTenSP1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGiaSP1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(lblSLSP1)
                    .addComponent(jButton2))
                .addContainerGap())
            .addComponent(pnlImg1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlSP2.setBackground(new java.awt.Color(204, 255, 204));

        pnlImg2.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout pnlImg2Layout = new javax.swing.GroupLayout(pnlImg2);
        pnlImg2.setLayout(pnlImg2Layout);
        pnlImg2Layout.setHorizontalGroup(
            pnlImg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        pnlImg2Layout.setVerticalGroup(
            pnlImg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblTenSP2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenSP2.setText("2");

        lblGiaSP2.setText("jLabel6");

        jButton3.setText("-");

        lblSLSP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSLSP2.setText("0");

        jButton4.setText("+");

        javax.swing.GroupLayout pnlSP2Layout = new javax.swing.GroupLayout(pnlSP2);
        pnlSP2.setLayout(pnlSP2Layout);
        pnlSP2Layout.setHorizontalGroup(
            pnlSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSP2Layout.createSequentialGroup()
                .addComponent(pnlImg2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSP2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSLSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSP2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTenSP2, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(lblGiaSP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSP2Layout.setVerticalGroup(
            pnlSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlImg2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlSP2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTenSP2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGiaSP2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(lblSLSP2)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pnlSP4.setBackground(new java.awt.Color(204, 255, 204));

        pnlImg4.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout pnlImg4Layout = new javax.swing.GroupLayout(pnlImg4);
        pnlImg4.setLayout(pnlImg4Layout);
        pnlImg4Layout.setHorizontalGroup(
            pnlImg4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        pnlImg4Layout.setVerticalGroup(
            pnlImg4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblTenSP4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenSP4.setText("4");

        lblGiaSP4.setText("jLabel6");

        jButton7.setText("-");

        lblSLSP4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSLSP4.setText("0");

        jButton8.setText("+");

        javax.swing.GroupLayout pnlSP4Layout = new javax.swing.GroupLayout(pnlSP4);
        pnlSP4.setLayout(pnlSP4Layout);
        pnlSP4Layout.setHorizontalGroup(
            pnlSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSP4Layout.createSequentialGroup()
                .addComponent(pnlImg4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSP4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSLSP4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSP4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTenSP4, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(lblGiaSP4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSP4Layout.setVerticalGroup(
            pnlSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlImg4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlSP4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTenSP4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGiaSP4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(lblSLSP4)
                    .addComponent(jButton8))
                .addContainerGap())
        );

        pnlSP3.setBackground(new java.awt.Color(204, 255, 204));

        pnlImg3.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout pnlImg3Layout = new javax.swing.GroupLayout(pnlImg3);
        pnlImg3.setLayout(pnlImg3Layout);
        pnlImg3Layout.setHorizontalGroup(
            pnlImg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        pnlImg3Layout.setVerticalGroup(
            pnlImg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblTenSP3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenSP3.setText("3");

        lblGiaSP3.setText("jLabel6");

        jButton5.setText("-");

        lblSLSP3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSLSP3.setText("0");

        jButton6.setText("+");

        javax.swing.GroupLayout pnlSP3Layout = new javax.swing.GroupLayout(pnlSP3);
        pnlSP3.setLayout(pnlSP3Layout);
        pnlSP3Layout.setHorizontalGroup(
            pnlSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSP3Layout.createSequentialGroup()
                .addComponent(pnlImg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSP3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSLSP3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSP3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTenSP3, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(lblGiaSP3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSP3Layout.setVerticalGroup(
            pnlSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlImg3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlSP3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTenSP3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGiaSP3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(lblSLSP3)
                    .addComponent(jButton6))
                .addContainerGap())
        );

        pnlSP5.setBackground(new java.awt.Color(204, 255, 204));

        pnlImg5.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout pnlImg5Layout = new javax.swing.GroupLayout(pnlImg5);
        pnlImg5.setLayout(pnlImg5Layout);
        pnlImg5Layout.setHorizontalGroup(
            pnlImg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        pnlImg5Layout.setVerticalGroup(
            pnlImg5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblTenSP5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenSP5.setText("5");

        lblGiaSP5.setText("jLabel6");

        jButton9.setText("-");

        lblSLSP5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSLSP5.setText("0");

        jButton10.setText("+");

        javax.swing.GroupLayout pnlSP5Layout = new javax.swing.GroupLayout(pnlSP5);
        pnlSP5.setLayout(pnlSP5Layout);
        pnlSP5Layout.setHorizontalGroup(
            pnlSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSP5Layout.createSequentialGroup()
                .addComponent(pnlImg5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSP5Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSLSP5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSP5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTenSP5, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(lblGiaSP5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSP5Layout.setVerticalGroup(
            pnlSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlImg5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlSP5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTenSP5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGiaSP5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSP5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(lblSLSP5)
                    .addComponent(jButton10))
                .addContainerGap())
        );

        pnlSP6.setBackground(new java.awt.Color(204, 255, 204));

        pnlImg6.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout pnlImg6Layout = new javax.swing.GroupLayout(pnlImg6);
        pnlImg6.setLayout(pnlImg6Layout);
        pnlImg6Layout.setHorizontalGroup(
            pnlImg6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        pnlImg6Layout.setVerticalGroup(
            pnlImg6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblTenSP6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenSP6.setText("6");

        lblGiaSP6.setText("jLabel6");

        jButton11.setText("-");

        lblSLSP6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSLSP6.setText("0");

        jButton12.setText("+");

        pnlSP7.setBackground(new java.awt.Color(204, 255, 204));

        pnlImg7.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout pnlImg7Layout = new javax.swing.GroupLayout(pnlImg7);
        pnlImg7.setLayout(pnlImg7Layout);
        pnlImg7Layout.setHorizontalGroup(
            pnlImg7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        pnlImg7Layout.setVerticalGroup(
            pnlImg7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblTenSP7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenSP7.setText("Trà sữa matcha");

        lblGiaSP7.setText("jLabel6");

        jButton13.setText("-");

        lblSLSP7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSLSP7.setText("0");

        jButton14.setText("+");

        javax.swing.GroupLayout pnlSP7Layout = new javax.swing.GroupLayout(pnlSP7);
        pnlSP7.setLayout(pnlSP7Layout);
        pnlSP7Layout.setHorizontalGroup(
            pnlSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSP7Layout.createSequentialGroup()
                .addComponent(pnlImg7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSP7Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSLSP7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSP7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTenSP7, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(lblGiaSP7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSP7Layout.setVerticalGroup(
            pnlSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlImg7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlSP7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTenSP7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGiaSP7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSP7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(lblSLSP7)
                    .addComponent(jButton14))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlSP6Layout = new javax.swing.GroupLayout(pnlSP6);
        pnlSP6.setLayout(pnlSP6Layout);
        pnlSP6Layout.setHorizontalGroup(
            pnlSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSP6Layout.createSequentialGroup()
                .addComponent(pnlImg6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSP6Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSLSP6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSP6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTenSP6, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(lblGiaSP6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSP6Layout.setVerticalGroup(
            pnlSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlImg6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlSP6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTenSP6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGiaSP6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSP6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(lblSLSP6)
                    .addComponent(jButton12))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlSPLayout = new javax.swing.GroupLayout(pnlSP);
        pnlSP.setLayout(pnlSPLayout);
        pnlSPLayout.setHorizontalGroup(
            pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSPLayout.createSequentialGroup()
                        .addComponent(pnlSP5, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlSP6, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSPLayout.createSequentialGroup()
                        .addComponent(pnlSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSPLayout.createSequentialGroup()
                        .addComponent(pnlSP3, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlSP4, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(837, Short.MAX_VALUE))
        );
        pnlSPLayout.setVerticalGroup(
            pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlSP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlSP6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(417, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(pnlSP);

        btnthanhToan.setText("Thanh Toán");
        btnthanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthanhToanActionPerformed(evt);
            }
        });

        btnIn.setText("In Hóa Đơn");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCho, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnthanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIn, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCho, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDSSPLayout = new javax.swing.GroupLayout(pnlDSSP);
        pnlDSSP.setLayout(pnlDSSPLayout);
        pnlDSSPLayout.setHorizontalGroup(
            pnlDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDSSPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDSSPLayout.setVerticalGroup(
            pnlDSSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDSSPLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMain.add(pnlDSSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 790));

        pnlHDC.setBackground(new java.awt.Color(255, 255, 153));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHDCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHDCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlHDCLayout.setVerticalGroup(
            pnlHDCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHDCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMain.add(pnlHDC, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 450, -1));

        pnlTTHD.setBackground(new java.awt.Color(255, 255, 204));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("THÔNG TIN HÓA ĐƠN");

        lblNgayGio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayGio.setText("[Ngày và giờ]");

        lblMaHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaHoaDon.setText("[Mã Hóa Đơn]");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("                           NV: [Ma_NV]");

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

        javax.swing.GroupLayout pnlTTHDLayout = new javax.swing.GroupLayout(pnlTTHD);
        pnlTTHD.setLayout(pnlTTHDLayout);
        pnlTTHDLayout.setHorizontalGroup(
            pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(lblNgayGio, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTTHDLayout.createSequentialGroup()
                        .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlTTHDLayout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        pnlTTHDLayout.setVerticalGroup(
            pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTHDLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNgayGio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaHoaDon)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(46, 46, 46))
        );

        pnlMain.add(pnlTTHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 191, 450, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // reset các ô tổng tiền
        jTextField1.setText("0");
        jTextField2.setText("0");
        jTextField3.setText("0");
        jTextField4.setText("");
        jTextField5.setText("0");

        // reset số lượng các sản phẩm về 0
        JLabel[] lblSLs = {
            lblSLSP1, lblSLSP2, lblSLSP3,
            lblSLSP4, lblSLSP5, lblSLSP6
        };
        for (JLabel lbl : lblSLs) {
            lbl.setText("0");
        }

        // cập nhật ngày giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        String thoiGian = LocalDateTime.now().format(formatter);
        lblNgayGio.setText(thoiGian);
        lblNgayGio.revalidate();
        lblNgayGio.repaint();

        // tạo mã hóa đơn mới
        String maHD = "HD" + soHoaDon;
        lblMaHoaDon.setText(maHD);
        lblMaHoaDon.revalidate();
        lblMaHoaDon.repaint();

        // tăng biến đếm hóa đơn sau khi tạo
        soHoaDon++;
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChoActionPerformed
        DefaultTableModel modelTTHD = (DefaultTableModel) tblTTHD.getModel();
        if (modelTTHD.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong hóa đơn!");
            return;
        }

        // Lưu dữ liệu bảng TTHD
        Object[][] duLieu = new Object[modelTTHD.getRowCount()][4];
        for (int i = 0; i < modelTTHD.getRowCount(); i++) {
            for (int j = 0; j < 4; j++) {
                duLieu[i][j] = modelTTHD.getValueAt(i, j);
            }
        }

        // Lưu tiền mặt
        int tienMat = 0;
        try {
            tienMat = Integer.parseInt(jTextField4.getText().replace(" ", ""));
        } catch (NumberFormatException e) {
            tienMat = 0;
        }

        // Thêm vào danh sách tạm
        danhSachTam.add(new Object[]{duLieu, tienMat});

        // Tạo mã hóa đơn và thêm vào bảng HDC
        String maHD = "HD" + soHoaDon++;
        String thoiGian = java.time.LocalTime.now().withNano(0).toString();
        String nhanVien = NameAccount;

        DefaultTableModel modelHDC = (DefaultTableModel) tblHDC.getModel();
        modelHDC.addRow(new Object[]{maHD, thoiGian, nhanVien});

        // Thông báo
        JOptionPane.showMessageDialog(this, "Đã chuyển hóa đơn vào hàng chờ!");

        // --- RESET HÓA ĐƠN HIỆN TẠI (tương tự nút "Mới") ---
        // Xóa bảng TTHD
        modelTTHD.setRowCount(0);

        // Reset số lượng sản phẩm về 0
        JLabel[] lblSLs = {
            lblSLSP1, lblSLSP2, lblSLSP3,
            lblSLSP4, lblSLSP5, lblSLSP6
        };
        for (JLabel lbl : lblSLs) {
            lbl.setText("0");
        }

        // Reset các ô tổng tiền
        jTextField1.setText("0"); // tổng tiền sp
        jTextField2.setText("0"); // giảm giá
        jTextField3.setText("0"); // tổng thanh toán
        jTextField4.setText("");  // tiền mặt
        jTextField5.setText("0"); // trả lại

        // Cập nhật ngày giờ mới
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        String thoiGianMoi = LocalDateTime.now().format(formatter);
        lblNgayGio.setText(thoiGianMoi);

        // Gán mã hóa đơn mới
        lblMaHoaDon.setText("HD" + soHoaDon);
    }//GEN-LAST:event_btnChoActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tblTTHD.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblTTHD.getModel();

        String[] danhSachSP = {
            "Trà sữa", "Trà DCS", "Cà phê", "Cà Phê Sữa", "Nước ép dưa hấu", "Nước ép cam"
        };
        JLabel[] lblSLs = {
            lblSLSP1, lblSLSP2, lblSLSP3,
            lblSLSP4, lblSLSP5, lblSLSP6
        };

        if (row >= 0) {
            // lấy tên sản phẩm từ dòng được chọn
            String tenSP = model.getValueAt(row, 0).toString();

            // tìm và reset số lượng tương ứng
            for (int i = 0; i < danhSachSP.length; i++) {
                if (danhSachSP[i].equals(tenSP)) {
                    lblSLs[i].setText("0");
                    break;
                }
            }

            // xóa dòng được chọn
            model.removeRow(row);
        } else {
            // không chọn dòng → xóa toàn bộ hóa đơn
            model.setRowCount(0);

            // reset toàn bộ số lượng
            for (JLabel lbl : lblSLs) {
                jTextField4.setText("");
                lbl.setText("0");
            }
        }

        tinhTongTien();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnthanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthanhToanActionPerformed
        String tongTienStr = jTextField1.getText().replace(" ", "");
        if (Integer.parseInt(tongTienStr) == 0) {
            JOptionPane.showMessageDialog(this, "Hóa đơn đang trống!");
            return;
        }

        int thanhToan = Integer.parseInt(jTextField3.getText().replace(" ", ""));
        int tienMat = 0;

        try {
            String tienMatStr = jTextField4.getText().replace(" ", "").trim();
            if (!tienMatStr.isEmpty()) {
                tienMat = Integer.parseInt(tienMatStr);
                if (tienMat < thanhToan) {
                    JOptionPane.showMessageDialog(this, "Tiền mặt không đủ để thanh toán.");
                    return;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tiền mặt không hợp lệ!");
            return;
        }

        JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
        btnMoiActionPerformed(evt); // reset sau thanh toán
    }//GEN-LAST:event_btnthanhToanActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        String tongTienStr = jTextField1.getText().replace(" ", "");
        if (Integer.parseInt(tongTienStr) == 0) {
            JOptionPane.showMessageDialog(this, "Hóa đơn đang trống!");
            return;
        }

        JOptionPane.showMessageDialog(this, "Đã in hóa đơn!");
        btnMoi.doClick(); // reset sau khi in
    }//GEN-LAST:event_btnInActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Seller_interface("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCho;
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnQLHD;
    private javax.swing.JButton btnQLNV;
    private javax.swing.JButton btnQLSP;
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnthanhToan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel lblGiaSP1;
    private javax.swing.JLabel lblGiaSP2;
    private javax.swing.JLabel lblGiaSP3;
    private javax.swing.JLabel lblGiaSP4;
    private javax.swing.JLabel lblGiaSP5;
    private javax.swing.JLabel lblGiaSP6;
    private javax.swing.JLabel lblGiaSP7;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblNgayGio;
    private javax.swing.JLabel lblSLSP1;
    private javax.swing.JLabel lblSLSP2;
    private javax.swing.JLabel lblSLSP3;
    private javax.swing.JLabel lblSLSP4;
    private javax.swing.JLabel lblSLSP5;
    private javax.swing.JLabel lblSLSP6;
    private javax.swing.JLabel lblSLSP7;
    private javax.swing.JLabel lblTenSP1;
    private javax.swing.JLabel lblTenSP2;
    private javax.swing.JLabel lblTenSP3;
    private javax.swing.JLabel lblTenSP4;
    private javax.swing.JLabel lblTenSP5;
    private javax.swing.JLabel lblTenSP6;
    private javax.swing.JLabel lblTenSP7;
    private javax.swing.JPanel pnlCN;
    private javax.swing.JPanel pnlDSSP;
    private javax.swing.JPanel pnlHDC;
    private javax.swing.JPanel pnlImg1;
    private javax.swing.JPanel pnlImg2;
    private javax.swing.JPanel pnlImg3;
    private javax.swing.JPanel pnlImg4;
    private javax.swing.JPanel pnlImg5;
    private javax.swing.JPanel pnlImg6;
    private javax.swing.JPanel pnlImg7;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlSP;
    private javax.swing.JPanel pnlSP1;
    private javax.swing.JPanel pnlSP2;
    private javax.swing.JPanel pnlSP3;
    private javax.swing.JPanel pnlSP4;
    private javax.swing.JPanel pnlSP5;
    private javax.swing.JPanel pnlSP6;
    private javax.swing.JPanel pnlSP7;
    private javax.swing.JPanel pnlTTHD;
    private javax.swing.JTable tblHDC;
    private javax.swing.JTable tblTTHD;
    private javax.swing.JLabel txtUserName;
    // End of variables declaration//GEN-END:variables
}
