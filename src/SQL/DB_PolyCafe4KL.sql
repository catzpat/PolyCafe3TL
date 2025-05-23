DROP DATABASE POLYCAFE4KL
CREATE DATABASE POLYCAFE4KL
GO
USE POLYCAFE4KL
GO

CREATE TABLE Drinks_CATE
(
    Cate_ID NVARCHAR(20) PRIMARY KEY,
    Cate_Name NVARCHAR(50) NOT NULL
);
go

CREATE TABLE Drinks
(
    Drink_ID NVARCHAR(20) PRIMARY KEY,
    Drink_Name NVARCHAR(50) NOT NULL,
    Price Float NOT NULL,
    Discount FLOAT NOT NULL,
    Img nvarchar(50) NOT NULL,
    Drink_Status BIT NOT NULL,
    Cate_ID NVARCHAR(20) NOT NULL,
    FOREIGN KEY (Cate_ID) REFERENCES Drinks_CATE(Cate_ID)
    ON DELETE CASCADE 
    ON UPDATE CASCADE
);
go
CREATE TABLE Cards (
    Cards_ID INT PRIMARY KEY,
    Cards_Status INT NOT NULL
);
go
CREATE TABLE Account (
    Acc_Name Nvarchar(20) PRIMARY KEY,
    Acc_PW nvarchar(50) NOT NULL,
    Acc_Status BIT NOT NULL,
    FullName nvarchar(50) NOT NULL,
    Acc_Photo NVARCHAR(50) NOT NULL,
    Manager BIT NOT NULL
);
go
CREATE TABLE Bills (
    Bill_ID BIGINT IDENTITY(10000, 1),
    Acc_Name NVARCHAR(20) NOT NULL,
    Cards_ID INT NOT NULL,
    Checkin DATETIME NOT NULL,
    Checkout DATETIME NOT NULL,
    Bill_Status INT NOT NULL,
    PRIMARY KEY (Bill_ID),
    FOREIGN KEY (Acc_Name) REFERENCES Account(Acc_Name) ON UPDATE CASCADE,
    FOREIGN KEY (Cards_ID) REFERENCES Cards(Cards_ID) ON UPDATE CASCADE,
);
go
SELECT * FROM Bills
CREATE TABLE BillsDetails (
    BillsDetails_ID BIGINT IDENTITY(100000, 1),
    Bill_ID BIGINT NOT NULL,
    Drink_ID NVARCHAR(20) NOT NULL,
    Price Float NOT NULL,
    Discount FLOAT NOT NULL,
    Quantity INT NOT NULL,
    PRIMARY KEY (BillsDetails_ID),
    FOREIGN KEY (Bill_ID) REFERENCES Bills(Bill_ID) ON DELETE CASCADE,
    FOREIGN KEY (Drink_ID) REFERENCES Drinks(Drink_ID) ON DELETE CASCADE,
);

--------------------------------------------------------------------------------------
INSERT INTO Drinks_CATE (Cate_ID, Cate_Name)
VALUES 
('C001', N'Trà sữa'),
('C002', N'Cà phê'),
('C003', N'Nước ép'),
('C004', N'Sinh tố');
GO

INSERT INTO Drinks (Drink_ID, Drink_Name, Price, Discount, Img, Drink_Status, Cate_ID)
VALUES 
('D001', N'Trà sữa trân châu', 35000, 0.1, 'img1.jpg', 1, 'C001'),
('D002', N'Trà đào cam sả', 40000, 0.05, 'img2.jpg', 1, 'C001'),
('D003', N'Cà phê sữa đá', 25000, 0.0, 'img3.jpg', 1, 'C002'),
('D004', N'Cà phê đen', 20000, 0.0, 'img4.jpg', 1, 'C002'),
('D005', N'Nước ép cam', 30000, 0.05, 'img5.jpg', 1, 'C003'),
('D006', N'Nước ép dưa hấu', 28000, 0.05, 'img6.jpg', 1, 'C003'),
('D007', N'Sinh tố bơ', 32000, 0.1, 'img7.jpg', 1, 'C004'),
('D008', N'Sinh tố xoài', 30000, 0.1, 'img8.jpg', 1, 'C004');
GO
INSERT INTO Cards (Cards_ID, Cards_Status)
VALUES 
(123, 1),
(456, 1),
(789, 0);
GO
INSERT INTO Account (Acc_Name, Acc_PW, Acc_Status, FullName, Acc_Photo, Manager)
VALUES 
('admin01', '1234', 1, N'Văn Minh Khôi', 'admin1.jpg', 1),
('admin02', '1234', 1, N'Trương Đức Nam Khánh', 'admin2.jpg', 1),
('user01', '1234', 1, N'Nguyễn Quang Huy', 'user1.jpg', 0),
('user02', '1234', 1, N'Trần Bảo Hân', 'user2.jpg', 0);
GO
INSERT INTO Bills (Acc_Name, Cards_ID, Checkin, Checkout, Bill_Status)
VALUES 
('user01', 123, '2025-05-23 08:00', '2025-05-23 09:00', 1),
('user01', 456, '2025-05-23 09:15', '2025-05-23 09:45', 1),
('user02', 789, '2025-05-23 10:00', '2025-05-23 10:30', 1);
GO
INSERT INTO BillsDetails (Bill_ID, Drink_ID, Price, Discount, Quantity)
VALUES 
(10003, 'D001', 35000, 0.1, 2),
(10002, 'D003', 25000, 0.0, 1),
(10001, 'D005', 30000, 0.05, 1),
(10001, 'D006', 28000, 0.05, 2),
(10002, 'D007', 32000, 0.1, 1),
(10002, 'D004', 20000, 0.0, 1);
GO
