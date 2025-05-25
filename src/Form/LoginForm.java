package Form;

import CafeDAO.DAO;
import Model.User;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class LoginForm extends javax.swing.JFrame {

    public LoginForm() {
        initComponents();
        initLayout();
        setLocationRelativeTo(null);
    }

    public void initLayout() {
        txtRegister.setText("<html><u>Register</u></html>");
        txtForgot.setText("<html><u>Forgot password</u></html>");
        mainPanel.setBackground(new Color(20, 20, 20));
        btnLogin.setBackground(new Color(40, 40, 40));
        btnLogin.setForeground(Color.WHITE);
        txtName.setBackground(white);
//        ----------------------------------------------------------------------
        // Set hover cho btnLogin
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new Color(70, 130, 180));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(new Color(40, 40, 40));
            }
        });
//        ----------------------------------------------------------------------
        // Set hover cho txtReg
        txtRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtRegister.setForeground(new Color(70, 130, 180));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtRegister.setForeground(Color.WHITE);
            }
        });

        txtForgot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtForgot.setForeground(new Color(70, 130, 180));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtForgot.setForeground(Color.WHITE);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        JLabel5 = new javax.swing.JLabel();
        txtPW = new javax.swing.JTextField();
        txtRegister = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        JLabel6 = new javax.swing.JLabel();
        txtForgot = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(0, 0, 0));
        mainPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mainPanel.setForeground(new java.awt.Color(12, 12, 12));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOGIN ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        JLabel5.setBackground(new java.awt.Color(51, 51, 255));
        JLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        JLabel5.setForeground(new java.awt.Color(255, 255, 255));
        JLabel5.setText("Don't have an account?");

        txtRegister.setBackground(new java.awt.Color(51, 51, 255));
        txtRegister.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtRegister.setForeground(new java.awt.Color(255, 255, 255));
        txtRegister.setText("Register");
        txtRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRegisterMouseClicked(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        JLabel6.setBackground(new java.awt.Color(51, 51, 255));
        JLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        JLabel6.setForeground(new java.awt.Color(255, 255, 255));
        JLabel6.setText("Forgot password?");

        txtForgot.setBackground(new java.awt.Color(51, 51, 255));
        txtForgot.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtForgot.setForeground(new java.awt.Color(255, 255, 255));
        txtForgot.setText("Forgot password");
        txtForgot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtForgotMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(JLabel6)
                        .addGap(33, 33, 33)
                        .addComponent(txtForgot))
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                            .addComponent(JLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtName)
                        .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPW, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 83, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtPW, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLabel6)
                    .addComponent(txtForgot))
                .addGap(18, 18, 18)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLabel5)
                    .addComponent(txtRegister))
                .addContainerGap(173, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String userName = txtName.getText().trim();
        String passWord = txtPW.getText().trim();

        txtName.setBackground(white);
        txtPW.setBackground(white);

        if (userName.isEmpty()) {
            txtName.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Tên không được để trống", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (passWord.isEmpty()) {
            txtPW.setBackground(Color.red);
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userName.equals("admin1") && passWord.equals("1234")) {
            new Admin_interface(userName, "admin1.jpg").setVisible(true);
            this.dispose();
        } else if (userName.equals("admin2") && passWord.equals("1234")) {
            new Admin_interface(userName, "admin2.png").setVisible(true);
            this.dispose();
        } else if (userName.equals("user1") && passWord.equals("1234")) {
            new Seller_interface(userName, "user1.png").setVisible(true);
        } else if (userName.equals("user2") && passWord.equals("1234")) {
            new Seller_interface(userName, "user2.png").setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Tài khoản không hợp lệ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRegisterMouseClicked
        RegisterForm reg = new RegisterForm();
        reg.setVisible(true);
        this.dispose(); // Xóa form cũ
    }//GEN-LAST:event_txtRegisterMouseClicked

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed

    }//GEN-LAST:event_txtNameActionPerformed

    private void txtForgotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtForgotMouseClicked
        ForgotPasswordForm forgot = new ForgotPasswordForm();
        forgot.setVisible(true);
        this.dispose(); // Xóa form cũ
    }//GEN-LAST:event_txtForgotMouseClicked

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabel5;
    private javax.swing.JLabel JLabel6;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel txtForgot;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPW;
    private javax.swing.JLabel txtRegister;
    // End of variables declaration//GEN-END:variables
}
