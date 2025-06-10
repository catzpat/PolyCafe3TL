/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.sql.*;
import java.util.*;
import Model.Account;
import Controller.DBConnection;
/**
 *
 * @author nycop
 */
public class AccountDAO {
     public void insert(Account acc) {
        String sql = "INSERT INTO Account (NameAccount, PasswordAccount, Email, UserName, Sex, RoleAccount, AccountStatus, Created_at) VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE())";
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, acc.getNameAccount());
            stmt.setString(2, acc.getPasswordAccount());
            stmt.setString(3, acc.getEmail());
            stmt.setString(4, acc.getUserName());
            stmt.setString(5, acc.getSex());
            stmt.setString(6, acc.getRoleAccount());
            stmt.setInt(7, acc.getAccountStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Account> selectAll() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Account";
        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Account acc = new Account();
                acc.setIdAccount(rs.getInt("IDAccount"));
                acc.setNameAccount(rs.getString("NameAccount"));
                acc.setPasswordAccount(rs.getString("PasswordAccount"));
                acc.setEmail(rs.getString("Email"));
                acc.setUserName(rs.getString("UserName"));
                acc.setSex(rs.getString("Sex"));
                acc.setRoleAccount(rs.getString("RoleAccount"));
                acc.setAccountStatus(rs.getInt("AccountStatus"));
                acc.setCreatedAt(rs.getString("Created_at"));
                list.add(acc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    public void update(Account acc) {
    String sql = "UPDATE Account SET NameAccount=?, PasswordAccount=?, Email=?, UserName=?, Sex=?, RoleAccount=?, AccountStatus=? WHERE IDAccount=?";
    try (Connection conn = DBConnection.connect();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, acc.getNameAccount());
        stmt.setString(2, acc.getPasswordAccount());
        stmt.setString(3, acc.getEmail());
        stmt.setString(4, acc.getUserName());
        stmt.setString(5, acc.getSex());
        stmt.setString(6, acc.getRoleAccount());
        stmt.setInt(7, acc.getAccountStatus());
        stmt.setInt(8, acc.getIdAccount());

        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}

