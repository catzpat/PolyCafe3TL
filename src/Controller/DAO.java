package Controller;

import java.sql.*;
import Model.User;
import Model.Products;
import java.util.ArrayList;
import java.util.List;

public class DAO {

// Đăng nhập
    public User login(String NameAccount, String PasswordAccount) {
        try (Connection c = DBConnection.connect()) {
            String query = "SELECT * FROM V_Account WHERE NameAccount = ? AND PasswordAccount = ?";
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

// Lấy thông tin user theo NameAccount
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

// Lấy tất cả sản phẩm
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

// Thêm sản phẩm mới
    public boolean insertProduct(Products p) {
        String sql = "INSERT INTO Products (MaSP, TenSP, LoaiSP, Gia, HinhAnh, TrangThaiBan) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection c = DBConnection.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getMaSP());
            ps.setNString(2, p.getTenSP());
            ps.setNString(3, p.getLoaiSP());
            ps.setInt(4, p.getGia());
            ps.setString(5, p.getHinhAnh());
            ps.setInt(6, p.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

// Cập nhật sản phẩm
    public boolean updateProduct(Products p) {
        String sql = "UPDATE Products SET TenSP = ?, LoaiSP = ?, Gia = ?, HinhAnh = ?, TrangThaiBan = ? WHERE MaSP = ?";
        try (Connection c = DBConnection.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setNString(1, p.getTenSP());
            ps.setNString(2, p.getLoaiSP());
            ps.setInt(3, p.getGia());
            ps.setString(4, p.getHinhAnh());
            ps.setInt(5, p.getTrangThai());
            ps.setString(6, p.getMaSP());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

// Ẩn sản phẩm (TrangThaiBan = 1)
    public boolean hideProduct(String maSP) {
        String sql = "UPDATE Products SET TrangThaiBan = 1 WHERE MaSP = ?";
        try (Connection c = DBConnection.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, maSP);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

// Xóa sản phẩm khỏi DB (chỉ dùng khi phát triển)
    public boolean deleteProduct(String maSP) {
        String sql = "DELETE FROM Products WHERE MaSP = ?";
        try (Connection c = DBConnection.connect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, maSP);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

// Tạo mã sp theo loại - ThemSP - CNT3
    public int TaoMaSPTheoLoai(String tienTo) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Products WHERE MaSP LIKE ?";
        try (Connection conn = DBConnection.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tienTo + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

// Truy vấn sản phẩm theo mã - SuaSP - CNT3
    public Products getProductById(String maSP) {
        String sql = "SELECT * FROM Products WHERE MaSP = ?";
        try (Connection conn = DBConnection.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Products(
                        rs.getString("MaSP"),
                        rs.getString("TenSP"),
                        rs.getString("LoaiSP"),
                        rs.getInt("Gia"),
                        rs.getString("HinhAnh"),
                        rs.getInt("TrangThaiBan")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

// Hóa đơn
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
                ps.setString(2, row[0].toString());
                ps.setInt(3, parseTien(row[1].toString()));
                ps.setInt(4, Integer.parseInt(row[2].toString()));
                ps.setInt(5, parseTien(row[3].toString()));
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
