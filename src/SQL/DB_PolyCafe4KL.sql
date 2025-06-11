CREATE DATABASE PolyCafe4KL
GO
USE PolyCafe4KL
GO

CREATE TABLE Account
(
    IDAccount INT PRIMARY KEY,
    NameAccount VARCHAR(50) NOT NULL UNIQUE,
    PasswordAccount VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL UNIQUE,
    UserName NVARCHAR(50) NOT NULL,
    Sex NVARCHAR(3) NOT NULL,
    RoleAccount VARCHAR(50) DEFAULT 'User',
    -- 0: True (Tài Khoản Còn Hoạt Động) 
    -- 1: False (Tài Khoản Ngưng Hoạt Động)
    AccountStatus BIT DEFAULT 0,
    Created_at DATETIME DEFAULT GETDATE()
);
GO

CREATE PROC SP_AddAccount
    @IDAccount INT,
    @NameAccount VARCHAR(50),
    @PasswordAccount VARCHAR(50),
    @Email VARCHAR(50),
    @UserName NVARCHAR(50),
    @Sex NVARCHAR(3),
    @RoleAccount VARCHAR(50)
AS
IF (@IDAccount IS NULL OR @NameAccount IS NULL OR @PasswordAccount IS NULL OR @Email IS NULL OR @UserName IS NULL OR @RoleAccount IS NULL)
    PRINT N'VUI LÒNG NHẬP ĐỦ THÔNG TIN'
ELSE IF EXISTS (SELECT *
FROM Account
WHERE IDAccount = @IDAccount)
    PRINT N'MÃ NGƯỜI DÙNG ĐÃ TỒN TẠI'
ELSE IF EXISTS (SELECT *
FROM Account
WHERE NameAccount = @NameAccount)
    PRINT N'TÊN TÀI KHOẢN ĐÃ TỒN TẠI'
    ELSE IF EXISTS (SELECT *
FROM Account
WHERE Email = @Email)
    PRINT N'EMAIL ĐÃ ĐƯỢC SỬ DỤNG'
ELSE
BEGIN
    INSERT INTO Account
        ( IDAccount, NameAccount, PasswordAccount, Email,
        UserName, Sex, RoleAccount)
    VALUES
        (@IDAccount, @NameAccount, @PasswordAccount, @Email, @UserName, @Sex, @RoleAccount)
END
GO
EXEC SP_AddAccount 1, 'mkhoixyz', '1234', 'khoivmth05018@gmail.com', N'Văn Minh Khôi', N'Nam', 'Admin';
EXEC SP_AddAccount 2, 'catzpat', '1234', 'catzpat123@gmail.com', N'Trương Đức Nam Khánh', N'Nam', 'Admin';
EXEC SP_AddAccount 3, 'nqh1089', '1234', 'huynqth05211@gmail.com', N'Nguyễn Quang Huy', N'Nam', 'User';
EXEC SP_AddAccount 4, 'bnah07', '1234', 'han07092008@gmail.com', N'Trần Bảo Hân', N'Nữ', 'User';
SELECT *
FROM Account
GO

CREATE VIEW V_Account
AS
    SELECT
        IDAccount,
        NameAccount,
        PasswordAccount,
        Email,
        UserName,
        Sex,
        RoleAccount,
        AccountStatus,
        FORMAT(Created_at, 'dd/MM/yyyy HH:mm:ss') AS Created_at
    FROM Account;
GO
SELECT *
FROM V_Account

-- Test truy vấn Login
-- SELECT * FROM V_Account WHERE NameAccount = 'mkhoixyz' AND PasswordAccount = '1234' AND AccountStatus = 0;

-- Bảng hóa đơn chờ: Lưu HD chưa thanh toán
CREATE TABLE HoaDonCho
(
    MaHD VARCHAR(20) PRIMARY KEY,
    ThoiGian TIME NOT NULL,
    NhanVien NVARCHAR(50) NOT NULL
);
GO

-- Bảng hóa đơn hoàn tất: Lưu HD đã thanh toán
CREATE TABLE HoaDon
(
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
CREATE TABLE ChiTietHoaDon
(
    MaHD VARCHAR(20),
    TenSP NVARCHAR(100),
    DonGia INT,
    SoLuong INT,
    ThanhTien INT,
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD)
);
GO

CREATE VIEW V_HoaDonCho
AS
    SELECT MaHD, ThoiGian, NhanVien
    FROM HoaDonCho;
GO

CREATE VIEW V_HoaDonTongHop
AS
    SELECT MaHD, NgayLap, NhanVien, TongTien, GiamGia, ThanhToan, TienMat, TienTraLai
    FROM HoaDon;
GO

CREATE VIEW V_ChiTietHoaDonFull
AS
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

CREATE TABLE Products
(
    MaSP VARCHAR(10) PRIMARY KEY,
    TenSP NVARCHAR(100) NOT NULL,
    LoaiSP NVARCHAR(50),
    Gia INT,
    HinhAnh VARCHAR(255),
    TrangThaiBan BIT DEFAULT 0
    -- 0: Còn bán, 1: Ngừng bán
);
GO


INSERT INTO Products
VALUES
    -- Trà sữa
    ('TS01', N'Trà sữa truyền thống', N'Trà sữa', 30000, 'TS_Thuong.jpg', 0),
    ('TS02', N'Trà sữa trân châu đường đen', N'Trà sữa', 32000, 'TS_TCDD.jpg', 0),
    ('TS03', N'Trà sữa nướng', N'Trà sữa', 35000, 'TS_Nuong.jpg', 0),
    ('TS04', N'Trà sữa matcha', N'Trà sữa', 34000, 'TS_Matcha.jpg', 0),

    -- Cà phê
    ('CF01', N'Cà phê sữa', N'Cà phê', 35000, 'CF_Sua.jpg', 0),
    ('CF02', N'Cà phê đen', N'Cà phê', 20000, 'CF_Den.jpg', 0),
    ('CF03', N'Bạc xỉu', N'Cà phê', 32000, 'CF_BacXiu.jpg', 0),
    ('CF04', N'Cà phê muối', N'Cà phê', 37000, 'CF_Muoi.jpg', 0),

    -- Nước ép
    ('NE01', N'Nước ép cam', N'Nước ép', 28000, 'NE_Cam.jpg', 0),
    ('NE02', N'Nước ép dưa hấu', N'Nước ép', 22000, 'NE_DuaHau.jpg', 0),
    ('NE03', N'Nước ép ổi', N'Nước ép', 25000, 'NE_Oi.jpg', 0),
    ('NE04', N'Nước ép táo', N'Nước ép', 27000, 'NE_Tao.jpg', 0),

    -- Sinh tố
    ('ST01', N'Sinh tố bơ', N'Sinh tố', 28000, 'ST_Bo.jpg', 0),
    ('ST02', N'Sinh tố xoài', N'Sinh tố', 28000, 'ST_Xoai.jpg', 0),
    ('ST03', N'Sinh tố dâu', N'Sinh tố', 29000, 'ST_Dau.jpg', 0),
    ('ST04', N'Sinh tố mãng cầu', N'Sinh tố', 30000, 'ST_MangCau.jpg', 0);
GO

SELECT *
FROM Account