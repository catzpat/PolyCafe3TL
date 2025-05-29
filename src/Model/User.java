package Model;

import java.time.LocalDateTime;

public class User {

    private int idAccount;
    private String nameAccount;
    private String passwordAccount;
    private String email;
    private String userName;
    private String sex;
    private String roleAccount;
    private boolean accountStatus;
    private LocalDateTime createdAt;
    private String img;

    public User() {
    }

    public User(int idAccount, String nameAccount, String passwordAccount, String email, String userName, String sex, String roleAccount, boolean accountStatus, LocalDateTime createdAt, String img) {
        this.idAccount = idAccount;
        this.nameAccount = nameAccount;
        this.passwordAccount = passwordAccount;
        this.email = email;
        this.userName = userName;
        this.sex = sex;
        this.roleAccount = roleAccount;
        this.accountStatus = accountStatus;
        this.createdAt = createdAt;
        this.img = img;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public String getPasswordAccount() {
        return passwordAccount;
    }

    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoleAccount() {
        return roleAccount;
    }

    public void setRoleAccount(String roleAccount) {
        this.roleAccount = roleAccount;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "User{" + "idAccount=" + idAccount + ", nameAccount=" + nameAccount + ", passwordAccount=" + passwordAccount + ", email=" + email + ", userName=" + userName + ", sex=" + sex + ", roleAccount=" + roleAccount + ", accountStatus=" + accountStatus + ", createdAt=" + createdAt + ", img=" + img + '}';
    }

}
