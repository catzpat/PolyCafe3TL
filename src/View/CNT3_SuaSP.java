package View;

import Controller.DAO;
import Model.Products;
import java.io.File;
import javax.swing.*;

public class CNT3_SuaSP extends javax.swing.JFrame {

    private String NameAccount;
    private String RoleAccount;
    private String maSPCanSua;

    public CNT3_SuaSP(String NameAccount, String RoleAccount, String maSP) {
        this.NameAccount = NameAccount;
        this.RoleAccount = RoleAccount;
        this.maSPCanSua = maSP;
        setTitle("4KL_Sửa SP");
        initComponents();
        KhoiTaoComboBox();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        HienThiSanPhamCanSua();
    }

    private void HienThiSanPhamCanSua() {
        DAO dao = new DAO();
        Products p = dao.getProductById(maSPCanSua);
        if (p != null) {
            txtMaSP.setText(p.getMaSP());
            txtTenSP.setText(p.getTenSP());
            txtGiaSP.setText(String.valueOf(p.getGia()));
            txtAnhSP.setText(p.getHinhAnh());

            for (int i = 0; i < cbxLoaiSP.getItemCount(); i++) {
                if (cbxLoaiSP.getItemAt(i).equalsIgnoreCase(p.getLoaiSP().trim())) {
                    cbxLoaiSP.setSelectedIndex(i);
                    break;
                }
            }

            String trangThaiStr = p.getTrangThai() == 0 ? "Đang bán" : "Ngừng bán";
            for (int i = 0; i < cbxTrangThai.getItemCount(); i++) {
                if (cbxTrangThai.getItemAt(i).equalsIgnoreCase(trangThaiStr)) {
                    cbxTrangThai.setSelectedIndex(i);
                    break;
                }
            }

            // Khóa các dlieu không được sửa
            txtMaSP.setEditable(false);
            txtTenSP.setEditable(false);
            txtAnhSP.setEditable(false);
            cbxLoaiSP.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm để sửa!");
            this.dispose();
        }
    }

    private void KhoiTaoComboBox() {
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
        btnSua = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(215, 204, 200));

        pnlMain.setBackground(new java.awt.Color(215, 204, 200));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SỬA SẢN PHẨM");

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

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel8.setText("Mã sản phẩm:");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(49, 49, 49)
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtAnhSP)
                                                .addComponent(txtGiaSP)
                                                .addComponent(txtTenSP)
                                                .addComponent(cbxLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(147, 147, 147))))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
                .addGap(31, 31, 31)
                .addComponent(btnSua)
                .addContainerGap(236, Short.MAX_VALUE))
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

    private void cbxLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLoaiSPActionPerformed

    }//GEN-LAST:event_cbxLoaiSPActionPerformed

    private void cbxTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTrangThaiActionPerformed

    }//GEN-LAST:event_cbxTrangThaiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            String maSP = txtMaSP.getText().trim();
            String tenSP = txtTenSP.getText().trim();
            String loaiSP = cbxLoaiSP.getSelectedItem().toString();
            String giaText = txtGiaSP.getText().trim().replace(" ", "");
            String hinhAnh = txtAnhSP.getText().trim();
            String trangThaiStr = cbxTrangThai.getSelectedItem().toString();
            int trangThai = trangThaiStr.equals("Ngừng bán") ? 1 : 0;

            // Kiểm tra rỗng giá sản phẩm
            if (giaText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập giá sản phẩm!", "Thiếu dữ liệu", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Kiểm tra giá có phải số và lớn hơn 0
            int gia;
            try {
                gia = Integer.parseInt(giaText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Giá phải là số nguyên hợp lệ!", "Lỗi định dạng", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (gia < 0) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm không hợp lệ!", "Lỗi giá", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Tạo đối tượng sản phẩm cần cập nhật
            Products p = new Products(maSP, tenSP, loaiSP, gia, hinhAnh, trangThai);
            // Gửi yêu cầu cập nhật sản phẩm vào database
            boolean success = new DAO().updateProduct(p);

            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CNT3_SuaSP("", "", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JComboBox<String> cbxLoaiSP;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextField txtAnhSP;
    private javax.swing.JTextField txtGiaSP;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
