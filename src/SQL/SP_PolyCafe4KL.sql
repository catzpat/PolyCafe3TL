CREATE TABLE Products (
    MaSP VARCHAR(10) PRIMARY KEY,
    TenSP NVARCHAR(100) NOT NULL,
    LoaiSP NVARCHAR(50),
    Gia INT,
    HinhAnh VARCHAR(255)
);
GO


INSERT INTO Products VALUES 
-- Trà sữa
('TS01', N'Trà sữa truyền thống', N'Trà sữa', 30000, 'TS_Thuong.jpg'),
('TS02', N'Trà sữa trân châu đường đen', N'Trà sữa', 32000, 'TS_TCDD.jpg'),
('TS03', N'Trà sữa nướng', N'Trà sữa', 35000, 'TS_Nuong.jpg'),
('TS04', N'Trà sữa matcha', N'Trà sữa', 34000, 'TS_Matcha.jpg'),

-- Cà phê
('CF01', N'Cà phê sữa', N'Cà phê', 35000, 'CF_Sua.jpg'),
('CF02', N'Cà phê đen', N'Cà phê', 20000, 'CF_Den.jpg'),
('CF03', N'Bạc xỉu', N'Cà phê', 32000, 'CF_BacXiu.jpg'),
('CF04', N'Cà phê muối', N'Cà phê', 37000, 'CF_Muoi.jpg'),

-- Nước ép
('NE01', N'Nước ép cam', N'Nước ép', 28000, 'NE_Cam.jpg'),
('NE02', N'Nước ép dưa hấu', N'Nước ép', 22000, 'NE_DuaHau.jpg'),
('NE03', N'Nước ép ổi', N'Nước ép', 25000, 'NE_Oi.jpg'),
('NE04', N'Nước ép táo', N'Nước ép', 27000, 'NE_Tao.jpg'),

-- Sinh tố
('ST01', N'Sinh tố bơ', N'Sinh tố', 28000, 'ST_Bo.jpg'),
('ST02', N'Sinh tố xoài', N'Sinh tố', 28000, 'ST_Xoai.jpg'),
('ST03', N'Sinh tố dâu', N'Sinh tố', 29000, 'ST_Dau.jpg'),
('ST04', N'Sinh tố mãng cầu', N'Sinh tố', 30000, 'ST_MangCau.jpg');
GO
SELECT * FROM Products