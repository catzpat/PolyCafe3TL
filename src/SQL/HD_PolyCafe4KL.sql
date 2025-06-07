-- DROP TABLE HoaDon

USE PolyCafe4KL
GO

-- Bảng hóa đơn chờ: Lưu HD chưa thanh toán
CREATE TABLE HoaDonCho (
    MaHD VARCHAR(20) PRIMARY KEY,
    ThoiGian TIME NOT NULL,
    NhanVien NVARCHAR(50) NOT NULL
);
GO

-- Bảng hóa đơn hoàn tất: Lưu HD đã thanh toán
CREATE TABLE HoaDon (
    MaHD VARCHAR(20) PRIMARY KEY,
    NgayLap DATETIME NOT NULL,
    NhanVien NVARCHAR(50),
    TongTien INT,
    GiamGia INT,
    ThanhToan INT,
    TienMat INT,
    TienTraLai INT
);
GO

-- Bảng chi tiết hóa đơn: Lưu từng SP trong HD
CREATE TABLE ChiTietHoaDon (
    MaHD VARCHAR(20),
    TenSP NVARCHAR(100),
    DonGia INT,
    SoLuong INT,
    ThanhTien INT,
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD)
);
GO


--
GO
CREATE VIEW v_HoaDonCho AS
SELECT MaHD, ThoiGian, NhanVien
FROM HoaDonCho;

GO
CREATE VIEW v_HoaDonTongHop AS
SELECT MaHD, NgayLap, NhanVien, TongTien, GiamGia, ThanhToan, TienMat, TienTraLai
FROM HoaDon;
GO

GO
CREATE VIEW v_ChiTietHoaDonFull AS
SELECT 
    hd.MaHD,
    hd.NgayLap,
    hd.NhanVien,
    ct.TenSP,
    ct.DonGia,
    ct.SoLuong,
    ct.ThanhTien
FROM HoaDon hd
JOIN ChiTietHoaDon ct ON hd.MaHD = ct.MaHD;
GO

--
SELECT * FROM v_ChiTietHoaDonFull
--
SELECT * FROM v_HoaDonCho
--
SELECT * FROM v_HoaDonTongHop