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
SELECT * FROM Account
GO

CREATE VIEW V_Account AS
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
SELECT * FROM V_Account

-- Test truy vấn Login
SELECT * FROM V_Account WHERE NameAccount = 'mkhoixyz' AND PasswordAccount = '1234' AND AccountStatus = 0;