package Controller;

import java.sql.*;
import Model.User;

public class DAO {

    public User login(String NameAccount, String PasswordAccount) {
        try (Connection c = DBConnection.connect()) {
            String query = "SELECT * FROM V_Account WHERE NameAccount = ? AND PasswordAccount = ? AND AccountStatus = 0";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, NameAccount);
            ps.setString(2, PasswordAccount);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setNameAccount(rs.getString("NameAccount"));
                u.setPasswordAccount(rs.getString("PasswordAccount"));
                u.setRoleAccount(rs.getString("RoleAccount"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    }
