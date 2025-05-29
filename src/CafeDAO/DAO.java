package CafeDAO;

import java.sql.*;
import Model.User;

public class DAO {

    public User login(String NameAccount, String PasswordAccount) {
        try (Connection c = DBConnection.connect()) {
            String query = "SELECT * FROM Account WHERE NameAccount = ? AND PasswordAccount = ? AND AccountStatus = 0";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setString(1, NameAccount);
            stmt.setString(2, PasswordAccount);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setNameAccount(rs.getString("NameAccount"));
                u.setPasswordAccount(rs.getString("PasswordAccount"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    
    public String getPhoto(String img) {
        return img;
    }
}
