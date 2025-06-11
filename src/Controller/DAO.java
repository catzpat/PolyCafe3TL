package Controller;

import java.sql.*;
import Model.User;
import Model.Products;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    public User login(String NameAccount, String PasswordAccount) {
        try (Connection c = DBConnection.connect()) {
            String query = "SELECT * FROM V_Account WHERE NameAccount = ? AND PasswordAccount = ? ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, NameAccount);
            ps.setString(2, PasswordAccount);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setNameAccount(rs.getString("NameAccount"));
                u.setPasswordAccount(rs.getString("PasswordAccount"));
                u.setRoleAccount(rs.getString("RoleAccount"));
                u.setAccountStatus(rs.getBoolean("AccountStatus"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByNameAccount(String NameAccount) {
        User user = null;
        String sql = "SELECT * FROM V_Account WHERE NameAccount = ?";
        try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, NameAccount);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setIdAccount(rs.getInt("IDAccount"));
                user.setNameAccount(rs.getString("NameAccount"));
                user.setPasswordAccount(rs.getString("PasswordAccount"));
                user.setEmail(rs.getString("Email"));
                user.setUserName(rs.getString("UserName"));
                user.setSex(rs.getString("Sex"));
                user.setRoleAccount(rs.getString("RoleAccount"));
                user.setAccountStatus(rs.getBoolean("AccountStatus"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Products> getAllProducts() {
        List<Products> list = new ArrayList<>();
        String sql = "SELECT * FROM Products";

        try (Connection conn = DBConnection.connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Products p = new Products(
                        rs.getString("MaSP"),
                        rs.getNString("TenSP"),
                        rs.getNString("LoaiSP"),
                        rs.getInt("Gia"),
                        rs.getString("HinhAnh"),
                        rs.getInt("TrangThaiBan")
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertHoaDon(String maHD, String nhanVien, int tongTien, int giamGia, int thanhToan, int tienMat, int tienTraLai) {
        String sql = "INSERT INTO HoaDon (MaHD, NgayLap, NhanVien, TongTien, GiamGia, ThanhToan, TienMat, TienTraLai) "
                + "VALUES (?, GETDATE(), ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maHD);
            ps.setString(2, nhanVien);
            ps.setInt(3, tongTien);
            ps.setInt(4, giamGia);
            ps.setInt(5, thanhToan);
            ps.setInt(6, tienMat);
            ps.setInt(7, tienTraLai);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertChiTietHoaDon(String maHD, List<Object[]> chiTietList) {
        String sql = "INSERT INTO ChiTietHoaDon (MaHD, TenSP, DonGia, SoLuong, ThanhTien) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            for (Object[] row : chiTietList) {
                ps.setString(1, maHD);
                ps.setString(2, row[0].toString()); // TenSP
                ps.setInt(3, parseTien(row[1].toString())); // DonGia
                ps.setInt(4, Integer.parseInt(row[2].toString())); // SoLuong
                ps.setInt(5, parseTien(row[3].toString())); // ThanhTien
                ps.addBatch();
            }
            ps.executeBatch();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private int parseTien(String tien) {
        try {
            return Integer.parseInt(tien.replace(" ", "").replace("đ", "").trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean insertHoaDonCho(String maHD, String thoiGian, String nhanVien) {
        String sql = "INSERT INTO HoaDonCho (MaHD, ThoiGian, NhanVien) VALUES (?,?,?)";
        try (Connection c = DBConnection.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, maHD);
            ps.setString(2, thoiGian);
            ps.setString(3, nhanVien);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
