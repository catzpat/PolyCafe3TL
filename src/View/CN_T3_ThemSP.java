package View;

import Controller.DAO;
import Model.Products;
import java.io.File;
import javax.swing.*;

public class CN_T3_ThemSP extends javax.swing.JFrame {

    private String NameAccount;
    private String RoleAccount;
    private String MaSPCanSua; // Mã SP cần sửa

    public CN_T3_ThemSP(String NameAccount, String RoleAccount) {
        this(NameAccount, RoleAccount, null);
    }

    public CN_T3_ThemSP(String NameAccount, String RoleAccount, String maSP) {
        this.NameAccount = NameAccount;
        this.RoleAccount = RoleAccount;
        this.MaSPCanSua = maSP;
        setTitle("4KL_Thêm SP");
        initComponents();
        initComboBoxes();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        if (MaSPCanSua != null) {
            loadSanPhamCanSua();
        }
    }

    // Tạo mã SP
    private String LayMaSPTuLoai(String loai) {
        return switch (loai) {
            case "Trà sữa" ->
                "TS";
            case "Cà phê" ->
                "CF";
            case "Nước ép" ->
                "NE";
            case "Sinh tố" ->
                "ST";
            default ->
                throw new IllegalArgumentException("Loại sản phẩm không hợp lệ: " + loai);
        };
    }

    // Load sản phẩm cần sửa
    private void loadSanPhamCanSua() {
        DAO dao = new DAO();
        Products p = dao.getProductById(MaSPCanSua);
        if (p != null) {
            txtTenSP.setText(p.getTenSP());
            txtGiaSP.setText(String.valueOf(p.getGia()));
            txtAnhSP.setText(p.getHinhAnh());
            cbxLoaiSP.setSelectedItem(p.getLoaiSP());
            cbxTrangThai.setSelectedItem(p.getTrangThai() == 0 ? "Đang bán" : "Ngừng bán");
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm để sửa!");
            this.dispose();
        }
    }

    // Tạo mã mới
    private String TaoMaSanPham(String prefix) {
        DAO dao = new DAO();
        int dem = dao.TaoMaSPTheoLoai(prefix);
        return String.format("%s%02d", prefix, dem + 1);
    }

    // Tự sinh combo box
    private void initComboBoxes() {
        cbxLoaiSP.setModel(new DefaultComboBoxModel<>(new String[]{"Trà sữa", "Cà phê", "Nước ép", "Sinh tố"}));
        cbxTrangThai.setModel(new DefaultComboBoxModel<>(new String[]{"Đang bán", "Ngừng bán"}));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        txtGiaSP = new javax.swing.JTextField();
        txtAnhSP = new javax.swing.JTextField();
        cbxLoaiSP = new javax.swing.JComboBox<>();
        cbxTrangThai = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(215, 204, 200));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÊM SẢN PHẨM");

        jLabel3.setText("Tên sản phẩm:");

        jLabel4.setText("Tên file ảnh:");

        jLabel5.setText("Loại sản phẩm:");

        jLabel6.setText("Trạng thái:");

        jLabel7.setText("Giá sản phẩm:");

        cbxLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trà sữa", "Cà phê", "Nước ép", "Sinh tố" }));
        cbxLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLoaiSPActionPerformed(evt);
            }
        });

        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang bán", "Ngừng bán" }));
        cbxTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTrangThaiActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addGap(0, 45, Short.MAX_VALUE)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAnhSP)
                            .addComponent(txtGiaSP)
                            .addComponent(txtTenSP)
                            .addComponent(cbxLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbxLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(btnThem)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTrangThaiActionPerformed

    }//GEN-LAST:event_cbxTrangThaiActionPerformed

    private void cbxLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLoaiSPActionPerformed

    }//GEN-LAST:event_cbxLoaiSPActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            String tenSP = txtTenSP.getText().trim();
            String loaiSP = cbxLoaiSP.getSelectedItem().toString();
            String giaText = txtGiaSP.getText().trim().replace(" ", "");
            String hinhAnh = txtAnhSP.getText().trim();
            String trangThaiStr = cbxTrangThai.getSelectedItem().toString();
            int trangThai = trangThaiStr.equals("Ngừng bán") ? 1 : 0;

            // Tự tạo mã sản phẩm từ loại
            String prefix = LayMaSPTuLoai(loaiSP);
            String maSP = TaoMaSanPham(prefix);

            // Kiểm tra rỗng
            if (tenSP.isEmpty() || hinhAnh.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Thiếu dữ liệu", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Kiểm tra phần mở rộng hợp lệ
            if (!(hinhAnh.endsWith(".jpg") || hinhAnh.endsWith(".png") || hinhAnh.endsWith(".jpeg"))) {
                JOptionPane.showMessageDialog(this, "Tên ảnh phải có phần mở rộng .jpg, .png hoặc .jpeg!", "Lỗi định dạng ảnh", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Kiểm tra tồn tại ảnh
            File imgFile = new File("src/STOCK_IMG/" + hinhAnh);
            if (!imgFile.exists()) {
                JOptionPane.showMessageDialog(this, "Ảnh không tồn tại trong thư mục STOCK_IMG!", "Lỗi ảnh", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int gia = Integer.parseInt(giaText);
            if (gia < 0) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm không hợp lệ!", "Lỗi giá", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Thêm sản phẩm
            Products p = new Products(maSP, tenSP, loaiSP, gia, hinhAnh, trangThai);
            DAO dao = new DAO();
            boolean success = dao.insertProduct(p);

            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Không thể thêm sản phẩm! Mã SP có thể đã tồn tại.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá sản phẩm phải là số hợp lệ!", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CN_T3_ThemSP("", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cbxLoaiSP;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextField txtAnhSP;
    private javax.swing.JTextField txtGiaSP;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
