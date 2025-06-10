/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nycop
 */
public class Account {
    private int idAccount;
    private String nameAccount;
    private String passwordAccount;
    private String email;
    private String userName;
    private String sex;
    private String roleAccount;
    private int accountStatus;
    private String createdAt; // hoặc dùng java.sql.Timestamp

    // Getter & Setter
    public int getIdAccount() { return idAccount; }
    public void setIdAccount(int idAccount) { this.idAccount = idAccount; }

    public String getNameAccount() { return nameAccount; }
    public void setNameAccount(String nameAccount) { this.nameAccount = nameAccount; }

    public String getPasswordAccount() { return passwordAccount; }
    public void setPasswordAccount(String passwordAccount) { this.passwordAccount = passwordAccount; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    public String getRoleAccount() { return roleAccount; }
    public void setRoleAccount(String roleAccount) { this.roleAccount = roleAccount; }

    public int getAccountStatus() { return accountStatus; }
    public void setAccountStatus(int accountStatus) { this.accountStatus = accountStatus; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

}
