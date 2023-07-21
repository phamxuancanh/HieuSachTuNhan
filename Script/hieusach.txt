USE master
CREATE DATABASE QUANLYHIEUSACH
GO
USE QUANLYHIEUSACH
GO 

CREATE TABLE TaiKhoan(
	[tenDangNhap] [nvarchar](6) NOT NULL,
	[matKhau] [nvarchar](20) NOT NULL,
	[maNhanVien] [nvarchar](8) NOT NULL,
	[quyen] [bit] NOT NULL,
)

CREATE TABLE NhanVien(
	[maNhanVien] [nvarchar](8) NOT NULL,
	[hoTenNhanVien] [nvarchar](50) NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[cCCD] [nvarchar](12) NOT NULL,
	[diaChi] [nvarchar](100) NOT NULL,
	[sdt] [nvarchar](10) NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[chucVu] [nvarchar](20) NOT NULL,
	[caLamViec] [nvarchar](1) NOT NULL,
	[hinhAnh] [nvarchar](100) NOT NULL,
	[OTP] [varchar](6) ,
	[hetHanOTP] [datetime] null ,
)

CREATE TABLE KhachHang(
	[maKhachHang] [nvarchar](8) NOT NULL,
	[hotenKhachHang] [nvarchar](50) NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[sdt] [nvarchar](10) NOT NULL,
	[diaChi] [nvarchar](100) NOT NULL,
)

CREATE TABLE SanPham(
	[maSanPham] [nvarchar](7) NOT NULL,
	[loaiSanPham] [nvarchar](20) NOT NULL,
	[soLuongTon] [int] NOT NULL,
	[trongLuong] [float] NOT NULL,
	[maNCC] [nvarchar](5)  NULL,
	[giaNhap] [float] NOT NULL,
	[ghiChu] [nvarchar](50) not NULL,
	[donViSanPham] [nvarchar](20) NOT NULL,
	[hinhAnh] [nvarchar](100),

	[tenSach] [nvarchar](50) ,
	[maTacGia] [nvarchar](5) ,
	[maNXB] [nvarchar](6) ,
	[namXB] [int] ,
	[soTrang] [int] ,
	[maTheLoai] [nvarchar](4) ,

	[tenVanPhongPham] [nvarchar](50) ,
	[maLoaiVanPhongPham] [nvarchar](5) ,
	[maMauSac] [nvarchar](5) ,
	[maChatLieu] [nvarchar](5) ,
	[maXuatXu] [nvarchar](5) ,
)

CREATE TABLE HoaDon(
	[maHoaDon] [nvarchar](9) NOT NULL,
	[maNhanVien] [nvarchar](8) NULL,
	[maKhachHang] [nvarchar](8) NOT NULL,
	[ngayLapHoaDon] [date] NOT NULL,
	[ghiChu] [nvarchar](10) NULL,
	[tienKhachDua] [float] NOT NULL,
	[tinhTrang] [bit] NOT NULL,
)


CREATE TABLE HoaDonDoiTra(
	[maHoaDonDoiTra] [nvarchar](11) NOT NULL,
	[maNhanVien] [nvarchar](8) NULL,
	[maKhachHang] [nvarchar](8) NOT NULL,
	[ngayLapHoaDon] [date] NOT NULL,
	[ghiChu] [nvarchar](10) NULL,
	[tienKhachDua] [float] NOT NULL,
	[maHoaDon] [nvarchar](9) NOT NULL,
	[tienPhaiTru] [float] NOT NULL
)


CREATE TABLE ChiTietHoaDonDoiTra(
	[maHoaDonDoiTra] [nvarchar](11) NOT NULL,
	[maSanPham] [nvarchar](7) NOT NULL,
	[soLuong] [int] NOT NULL,
	[donGia] [float] NOT NULL
)

CREATE TABLE NhaCungCap(
	[maNCC] [nvarchar](5) not NULL,
	[tenNCC] [nvarchar](50) not NULL,
	[diaChi] [nvarchar](100)  NULL,
	[email] [nvarchar](50)  NULL,
	[sdt] [nvarchar](20)  NULL,
)

CREATE TABLE ChiTietHoaDon(
	[maHoaDon] [nvarchar](9) NOT NULL,
	[maSanPham] [nvarchar](7) NOT NULL,
	[soLuong] [int] NOT NULL,
	[donGia] [float] NOT NULL
)

CREATE TABLE XuatXu(
	[maXuatXu] [nvarchar](5) NOT NULL,
	[tenXuatXu] [nvarchar](30) NOT NULL,
)

CREATE TABLE TheLoaiSach(
	[maTheLoai] [nvarchar](4) NOT NULL,
	[tenTheLoai] [nvarchar](50) NOT NULL,
)

CREATE TABLE TacGia(
	[maTacGia] [nvarchar](5) NOT NULL,
	[tenTacGia] [nvarchar](50) NOT NULL,
)

CREATE TABLE SachLoi(
	[maSanPham] [nvarchar](7) NOT NULL,
	[loiSanPham] [nvarchar](20) NOT NULL,
	[soLuongLoi] [int] NOT NULL
)
CREATE TABLE LoaiVanPhongPham(
	[maLoaiVanPhongPham] [nvarchar](5) NOT NULL,
	[tenTheLoai] [nvarchar](50) NOT NULL,
)

CREATE TABLE MauSac(
	[maMauSac] [nvarchar](5) NOT NULL,
	[tenMau] [nvarchar](30) NOT NULL,
)

CREATE TABLE NhaXuatBan(
	[maNXB] [nvarchar](6) NOT NULL,
	[tenNXB] [nvarchar](30) NOT NULL,
)


CREATE TABLE ChatLieu(
	[maChatLieu] [nvarchar](5) NOT NULL,
	[tenChatLieu] [nvarchar](30) NOT NULL,
)


--khoachinh
ALTER TABLE NhanVien
ADD PRIMARY KEY(maNhanVien)

ALTER TABLE ChiTietHoaDon
ADD CONSTRAINT maSP_maHD PRIMARY KEY(maSanPham, maHoaDon)

ALTER TABLE ChiTietHoaDonDoiTra
ADD CONSTRAINT maSP_maHDDT PRIMARY KEY(maSanPham, maHoaDonDoiTra)

ALTER TABLE SachLoi
ADD CONSTRAINT maSP_loiSP PRIMARY KEY(maSanPham, loiSanPham)

ALTER TABLE TaiKhoan
ADD PRIMARY KEY([tenDangNhap])

ALTER TABLE NhaCungCap
ADD PRIMARY KEY(maNCC)

ALTER TABLE HoaDon
ADD PRIMARY KEY(maHoaDon)

ALTER TABLE HoaDonDoiTra
ADD PRIMARY KEY(maHoaDonDoiTra)

ALTER TABLE KhachHang
ADD PRIMARY KEY(maKhachHang)

ALTER TABLE SanPham
ADD PRIMARY KEY(maSanPham)

ALTER TABLE XuatXu
ADD PRIMARY KEY(maXuatXu)

ALTER TABLE TheLoaiSach
ADD PRIMARY KEY(maTheLoai)

ALTER TABLE TacGia
ADD PRIMARY KEY(maTacGia)

ALTER TABLE MauSac
ADD PRIMARY KEY(maMauSac)

ALTER TABLE NhaXuatBan
ADD PRIMARY KEY(maNXB)

ALTER TABLE LoaiVanPhongPham
ADD PRIMARY KEY(maLoaiVanPhongPham)

ALTER TABLE ChatLieu
ADD PRIMARY KEY (maChatLieu)

--khoa ngoai
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_cthd_hd] FOREIGN KEY([maHoaDon])
REFERENCES [dbo].[HoaDon] ([maHoaDon])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_cthd_hd]
GO

ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_cthd_sp] FOREIGN KEY([maSanPham])
REFERENCES [dbo].[SanPham] ([maSanPham])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_cthd_sp]
GO

ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_hd_kh] FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_hd_kh]
GO

ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_hd_nv] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_hd_nv]
GO


ALTER TABLE [dbo].[ChiTietHoaDonDoiTra]  WITH CHECK ADD  CONSTRAINT [FK_cthddt_hddt] FOREIGN KEY([maHoaDonDoiTra])
REFERENCES [dbo].[HoaDonDoiTra] ([maHoaDonDoiTra])
GO
ALTER TABLE [dbo].[ChiTietHoaDonDoiTra] CHECK CONSTRAINT [FK_cthddt_hddt]
GO

ALTER TABLE [dbo].[ChiTietHoaDonDoiTra]  WITH CHECK ADD  CONSTRAINT [FK_cthddt_sp] FOREIGN KEY([maSanPham])
REFERENCES [dbo].[SanPham] ([maSanPham])
GO
ALTER TABLE [dbo].[ChiTietHoaDonDoiTra] CHECK CONSTRAINT [FK_cthddt_sp]
GO

ALTER TABLE [dbo].[HoaDonDoiTra]  WITH CHECK ADD  CONSTRAINT [FK_hddt_kh] FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[HoaDonDoiTra] CHECK CONSTRAINT [FK_hddt_kh]
GO

ALTER TABLE [dbo].[HoaDonDoiTra]  WITH CHECK ADD  CONSTRAINT [FK_hddt_nv] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[HoaDonDoiTra] CHECK CONSTRAINT [FK_hddt_nv]
GO





ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_s_nxb] FOREIGN KEY([maNXB])
REFERENCES [dbo].[NhaXuatBan] ([maNXB])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_s_nxb]
GO



ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_s_tg] FOREIGN KEY([maTacGia])
REFERENCES [dbo].[TacGia] ([maTacGia])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_s_tg]
GO

ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_s_tls] FOREIGN KEY([maTheLoai])
REFERENCES [dbo].[TheLoaiSach] ([maTheLoai])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_s_tls]
GO

ALTER TABLE [dbo].[SachLoi]  WITH CHECK ADD  CONSTRAINT [FK_sl_s] FOREIGN KEY([maSanPham])
REFERENCES [dbo].[SanPham] ([maSanPham])
GO
ALTER TABLE [dbo].[SachLoi] CHECK CONSTRAINT [FK_sl_s]
GO

ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_sp_ncc] FOREIGN KEY([maNCC])
REFERENCES [dbo].[NhaCungCap] ([maNCC])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_sp_ncc]
GO

ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_taikhoan_nv] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_taikhoan_nv]
GO

ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_vpp_cl] FOREIGN KEY([maChatLieu])
REFERENCES [dbo].[ChatLieu] ([maChatLieu])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_vpp_cl]
GO

ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_vpp_ms] FOREIGN KEY([maMauSac])
REFERENCES [dbo].[MauSac] ([maMauSac])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_vpp_ms]
GO


ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_vpp_tlvpp] FOREIGN KEY([maLoaiVanPhongPham])
REFERENCES [dbo].[LoaiVanPhongPham] ([maLoaiVanPhongPham])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_vpp_tlvpp]
GO

ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_vpp_xx] FOREIGN KEY([maXuatXu])
REFERENCES [dbo].[XuatXu] ([maXuatXu])
GO
ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_vpp_xx]
GO




USE QUANLYHIEUSACH
GO
INSERT [dbo].[ChatLieu] ([maChatLieu], [tenChatLieu]) VALUES ('CL001', N'Nhựa')
INSERT [dbo].[ChatLieu] ([maChatLieu], [tenChatLieu]) VALUES ('CL002', N'Giấy')
INSERT [dbo].[ChatLieu] ([maChatLieu], [tenChatLieu]) VALUES ('CL003', N'Tẩy')
INSERT [dbo].[ChatLieu] ([maChatLieu], [tenChatLieu]) VALUES ('CL004', N'Màu')
INSERT [dbo].[ChatLieu] ([maChatLieu], [tenChatLieu]) VALUES ('CL005', N'Vải')

GO
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH110001', N'Nguyễn Van An', 'TRUE', '0337098734', N'Hóc Môn')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH120002', N'Nguyễn Thị Khánh', 'FALSE', '0914712039', N'Gò Vấp')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH120003', N'Lê Thị Hồng', 'FALSE', '0961410277', N'Bình Thạnh')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH140004', N'Bùi Ngọc Dươnng', 'FALSE', '0366365911', N'Gò Vấp')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH140005', N'Ðinh Huy Tùng', 'TRUE','0909393964', N'Gò Vấp')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH150006', N'Phạm Ngọc Minh', 'TRUE','0366387511', N'Bình Chánh')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH150007', N'Ðinh Thị Tôn', 'FALSE', '0909354542', N'Quận 7')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH150008', N'Phạm Hoàng Sơn', 'TRUE', '0323575911', N'Quận 4')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH170009', N'Lê Thị Linh', 'FALSE', '0909393543', N'Quận 2')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH180010', N'Bùi Thị Nhật Vy', 'FALSE', '0366355478', N'Quận 1')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH180013', N'Nguyễn Tấn Vũ', 'TRUE', '0155858651', N'Hóc Môn')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH180014', N'Nguyễn Anh Tuấn', 'TRUE', '015584351', N'Gò Vấp')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH190011', N'Nguyễn Phạm Công Nguyên', 'TRUE', '0909411616', N'Quận 12')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH190014', N'Nguyễn Tuấn Dũng', 'TRUE', '0944861616', N'Quận 12')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH200012', N'Trần Thanh Phát','TRUE', '0916161515', N'Thủ Đức')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH200015', N'Phan Nguyễn Trung Thành', 'TRUE', '0955551616', N'Quận 12')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH200016', N'Nguyễn Việt Hoàng', 'TRUE', '0955448816', N'Quận 7')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH200017', N'Trần Minh Trí', 'TRUE', '0955551616', N'Quận 7')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH200018', N'Vũ Thái Dương', 'TRUE', '0944558484',N'Gò Vấp')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH200019', N'Nguyễn Huyền Nhi', 'FALSE', '096868686', N'Tân Bình')
INSERT [dbo].[KhachHang] ([maKhachHang], [hotenKhachHang], [gioiTinh], [sdt], [diaChi]) VALUES ('KH200021', N'Nguyễn Thái Cường', 'TRUE', '093525164', N'Quận 12')
GO
INSERT [dbo].[LoaiVanPhongPham] ([maLoaiVanPhongPham], [tenTheLoai]) VALUES ('TL001', N'Dụng cụ học sinh')
INSERT [dbo].[LoaiVanPhongPham] ([maLoaiVanPhongPham], [tenTheLoai]) VALUES ('TL002', N'Thiết bị văn phòng')
INSERT [dbo].[LoaiVanPhongPham] ([maLoaiVanPhongPham], [tenTheLoai]) VALUES ('TL003', N'Thiết bị trường học')
GO
INSERT [dbo].[MauSac] ([maMauSac], [tenMau]) VALUES ('MS001',N'Ðen')
INSERT [dbo].[MauSac] ([maMauSac], [tenMau]) VALUES ('MS002', N'Ðỏ')
INSERT [dbo].[MauSac] ([maMauSac], [tenMau]) VALUES ('MS003', N'Trắng')
INSERT [dbo].[MauSac] ([maMauSac], [tenMau]) VALUES ('MS004', N'Xanh')
INSERT [dbo].[MauSac] ([maMauSac], [tenMau]) VALUES ('MS005', N'Hồng')

GO
INSERT [dbo].[TheLoaiSach] ([maTheLoai], [tenTheLoai]) VALUES ('L001', N'SGK')
INSERT [dbo].[TheLoaiSach] ([maTheLoai], [tenTheLoai]) VALUES ('L002', N'Truyện')
INSERT [dbo].[TheLoaiSach] ([maTheLoai], [tenTheLoai]) VALUES ('L003', N'Tiểu thuyết')
INSERT [dbo].[TheLoaiSach] ([maTheLoai], [tenTheLoai]) VALUES ('L004', N'Kĩ năng sống')
INSERT [dbo].[TheLoaiSach] ([maTheLoai], [tenTheLoai]) VALUES ('L005', N'Kinh doanh')
INSERT [dbo].[TheLoaiSach] ([maTheLoai], [tenTheLoai]) VALUES ('L006', N'Thiếu nhi')

GO
INSERT [dbo].[XuatXu] ([maXuatXu], [tenXuatXu]) VALUES ('XX001', N'Việt Nam')
INSERT [dbo].[XuatXu] ([maXuatXu], [tenXuatXu]) VALUES ('XX002', N'Hoa Kì')
INSERT [dbo].[XuatXu] ([maXuatXu], [tenXuatXu]) VALUES ('XX003', N'Trung Quốc')
GO
INSERT [dbo].[NhaCungCap] ([maNCC], [tenNCC], [diaChi], [email], [sdt]) VALUES ('NCC01', N'Công Ty TNHH SX Và TM Khang Thịnh', N'K14 Đường Ngọc Hồi, Thanh Trì, Hà Nội', 'infokhangthinh@gmail.com', '02438235235')
INSERT [dbo].[NhaCungCap] ([maNCC], [tenNCC], [diaChi], [email], [sdt]) VALUES ('NCC02', N'Công Ty TCPTM & XNK Việt Hồng Chinh', N'Ðông Lường, TP Ðông Hà,  Quảng Trị', 'viethongchinh@gmail.com', '02333571777')
INSERT [dbo].[NhaCungCap] ([maNCC], [tenNCC], [diaChi], [email], [sdt]) VALUES ('NCC03', N'Công Ty TNHH Hướng Tuyết', N'Tiểu khu Phú Mỹ,Thị trấn Phú Xuyên, Huyện Phú Xuyên, Hà Nội', 'tnhhhuongtuyet@gmail.com', '0500462073')
INSERT [dbo].[NhaCungCap] ([maNCC], [tenNCC], [diaChi], [email], [sdt]) VALUES ('NCC04', N'Công Ty TNHH Hoa Bình Minh', N'Lô 8A, Đường Đồng Khởi, P.Tân Hiệp, Tp.Biên Hòa, Ðồng Nai', 'dhp_honda@gmail.com', '0613675522')
INSERT [dbo].[NhaCungCap] ([maNCC], [tenNCC], [diaChi], [email], [sdt]) VALUES ('NCC05', N'Công Ty TNHH thương mại và dịch vụ Hiệp Gia Phát', N' 76 Ðiện Biên Phủ, P. Chính Gian, Q. Thanh Khê, Tp. Ðà Nẵng', 'hiepgiaphat2012@gmail.com', '05113649993')
GO
INSERT [dbo].[NhanVien]  VALUES ('NV140403', N'Trần Thị Minh Huyền', '2002-06-06', '02065120462', N'Hải Quy, Hải Lăng,  Quảng Trị', '0961754711', 0, 'ttminhuyen1111@gmail.com', N'Nhân Viên', '1', 'nvhuyen.jpg', null, null)
INSERT [dbo].[NhanVien] VALUES ('NV150404', N'Lê Thanh Hải', '2002-08-08', '0201207662', N'Thủy Lâm, Ðông Anh, Hà Nội', '0961728722', 1, '1128.infor@gmail.com', N'Nhân Viên', '2', 'nvhai.jpg', null, null)
INSERT [dbo].[NhanVien]  VALUES ('QL130402', N'Trần Khánh Linh','2002-12-12', '02012320462', N'Xã Xuân Minh, Thọ Xuân, Thanh Hóa', '0967628711', 1, 'Ktpmlinh2002@gmail.com', N' Quản lý', '2', 'nvlinh.jpg', null, null)
INSERT [dbo].[NhanVien] VALUES ('QL220101', N'Phạm Xuân Cảnh', '2002-11-09', '02120120462', N'Hồ Ðắc Kiện, Châu Thành, Sóc Trăng', '0969828711', 1, 'pxcpaze@gmail.com', N' Quản lý', '1', 'nvcanh.jpg', null, null)
GO
INSERT [dbo].[NhaXuatBan] ([maNXB], [tenNXB]) VALUES ('NXB001', N'Kim Ðồng')
INSERT [dbo].[NhaXuatBan] ([maNXB], [tenNXB]) VALUES ('NXB002', N'Giáo dục')
INSERT [dbo].[NhaXuatBan] ([maNXB], [tenNXB]) VALUES ('NXB003', N'Dân Trí')



GO
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG001', N'Nguyễn Nhật Ánh')
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG002', N'Robin Sharma')
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG003', N'Nguyễn Văn Thiện')
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG004', N'Reberacca Weerasekera')
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG005', N'Trương Ðông Triết')
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG006', N'Kent Keith')
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG007', N'John Vu')
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG008', N'The Windy')
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG009', N'Nguyễn Thanh Hương ')
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG012', N'Lý Quang Diệu')
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG013', N'Eric Rickstad')
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG014', N'Actéon Ariane')
INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES ('TG015', N'Kateo Hearn')
go
INSERT [dbo].[SanPham] VALUES ('SP00001', N'Sách', 100, 0.5, 'NCC01', 20000, N'Không', N'Cuốn', 'sanpham1.jpg', N'Làm bạn với bầu trời', 'TG001', 'NXB001', 1990, 100, 'L003', null, null, null, null, null)
INSERT [dbo].[SanPham]  VALUES ('SP00002', N'Sách', 53, 0.5, 'NCC02', 23000, N'Không', N'Cuốn', 'sanpham2.jpg', N'Vị tu sĩ bán chiếc ferrari', 'TG002', 'NXB003', 2000, 100, 'L003', null, null, null, null, null)
INSERT [dbo].[SanPham] VALUES ('SP00003', N'Văn phòng phẩm', 60, 0.5, 'NCC01', 3000, N'Không', N'Cái', 'sanpham3.jpg', null, null, null, null, null, null, N'Bút bi', 'TL001', 'MS001', 'CL001', 'XX001')
INSERT [dbo].[SanPham] VALUES ('SP00004', N'Văn phòng phẩm', 54, 0.5, 'NCC01', 4000, N'Không', N'Cái', 'sanpham4.jpg', null, null, null, null, null, null, N'Cục tẩy', 'TL001', 'MS002', 'CL003', 'XX001')
INSERT [dbo].[SanPham] VALUES ('SP00005', N'Sách', 32, 0.5, 'NCC02', 15000, N'Không', N'Cuốn', 'sanpham5.jpg', N'Chơi Trò Ðồng Xanh', 'TG003', 'NXB003', 2011, 100, 'L003', null, null, null, null, null)
INSERT [dbo].[SanPham]  VALUES ('SP00006', N'Sách', 65, 0.5, 'NCC03', 20000, N'Không', N'Cuốn', 'sanpham6.jpg', N'Sách Letsgo 1', 'TG004', 'NXB002', 2012, 100, 'L001', null, null, null, null, null)
INSERT [dbo].[SanPham]  VALUES ('SP00007', N'Sách', 43, 0.5, 'NCC05', 27000, N'Không', N'Cuốn', 'sanpham7.jpg', N'Bậc thầy kinh doanh', 'TG005', 'NXB003', 2011, 129, 'L005', null, null, null, null, null)
INSERT [dbo].[SanPham]  VALUES ('SP00008', N'Văn phòng phẩm', 32, 0.5, 'NCC05', 5000, N'Không', N'Cái', 'sanpham8.jpg', null, null, null, null, null, null, N'Gọt bút chì', 'TL001', 'MS003', 'CL001', 'XX001')
INSERT [dbo].[SanPham]  VALUES ('SP00009', N'Văn phòng phẩm', 65, 0.5, 'NCC05', 10000, N'Không', N'Cái', 'sanpham9.jpg', null, null, null, null, null, null, N'Bút chì ngòi', 'TL001', 'MS001', 'CL001', 'XX001')
INSERT [dbo].[SanPham] VALUES ('SP00010', N'Sách', 54, 0.5, 'NCC02', 15000, N'Không', N'Cuốn', 'sanpham10.jpg', N'Nghịch lý cuộc sống', 'TG006', 'NXB003', 2022, 100, 'L004', null, null, null, null, null)
INSERT [dbo].[SanPham] VALUES ('SP00011', N'Sách', 54, 0.5, 'NCC03', 2000, N'Không', N'Cuốn', 'sanpham11.jpg', N'Muôn kiếp nhân sinh', 'TG007', 'NXB003', 2009, 143, 'L004', null, null, null, null, null)
INSERT [dbo].[SanPham]  VALUES ('SP00012', N'Sách', 76, 0.5, 'NCC03', 27000, N'Không', N'Cuốn', 'sanpham12.jpg', N'Từ điển Anh-Việt', 'TG008', 'NXB002', 2004, 930, 'L004', null, null, null, null, null)
INSERT [dbo].[SanPham] VALUES ('SP00013', N'Văn phòng phẩm', 87, 0.5, 'NCC01', 15000, N'Không', N'Cái', 'sanpham13.jpg', null, null, null, null, null, null,N'Compa', 'TL002', 'MS004', 'CL001', 'XX001')
INSERT [dbo].[SanPham]  VALUES ('SP00014', N'Văn phòng phẩm', 43, 0.5, 'NCC01', 10000, N'Không', N'Cái', 'sanpham14.jpg', null, null, null, null, null, null, N'Nhãn vở', 'TL001', 'MS005', 'CL002', 'XX001')
INSERT [dbo].[SanPham]  VALUES ('SP00015', N'Sách', 43, 0.5, 'NCC04', 150000, N'Không', N'Cuốn', 'sanpham15.jpg', N'Tư duy sâu', 'TG009', 'NXB003', 2000, 132, 'L004', null, null, null, null, null)
INSERT [dbo].[SanPham]  VALUES ('SP00016', N'Sách', 65, 0.5, 'NCC04', 20000, N'Không', N'Cuốn', 'sanpham16.jpg', N'Bé làm họa sĩ', NULL, 'NXB002', 2007, 154, 'L006', null, null, null, null, null)
INSERT [dbo].[SanPham]  VALUES ('SP00017', N'Sách', 43, 0.5, 'NCC01', 27000, N'Không', N'Cuốn', 'sanpham17.jpg', N'Bé tập tô', NULL, 'NXB002', 1999, 100, 'L006', null, null, null, null, null)
INSERT [dbo].[SanPham]  VALUES ('SP00018', N'Văn phòng phẩm', 76, 0.5, 'NCC02', 15000, N'Không', N'Cái', 'sanpham18.jpg', null, null, null, null, null, null, N'Bao bì', 'TL001', 'MS005', 'CL002', 'XX001')
INSERT [dbo].[SanPham]  VALUES ('SP00019', N'Văn phòng phẩm', 65, 0.5, 'NCC02', 20000, N'Không', N'Hộp', 'sanpham19.jpg',  null, null, null, null, null, null,N'Bút chì màu', 'TL003', 'MS004', 'CL004', 'XX001')
INSERT [dbo].[SanPham] VALUES ('SP00020', N'Sách', 54, 0.5, 'NCC02', 15000, N'Không', N'Cuốn', 'sanpham20.jpg', N'Bộ sách giáo khoa lớp 6', NULL, 'NXB002', 2009, 129, 'L001', null, null, null, null, null)
INSERT [dbo].[SanPham] VALUES ('SP00021', N'Sách', 87, 0.5, 'NCC02', 20000, N'Không', N'Cuốn', 'sanpham21.jpg', N'Sách toán nâng cao lớp 7', NULL, 'NXB002', 2012, 129, 'L001', null, null, null, null, null)
INSERT [dbo].[SanPham] VALUES ('SP00022', N'Sách', 54, 0.5, 'NCC03', 27000, N'Không', N'Cuốn', 'sanpham22.jpg', N'Sách văn mẫu tiểu học', NULL, 'NXB002', 2013, 200, 'L001', null, null, null, null, null)
INSERT [dbo].[SanPham] VALUES ('SP00023', N'Văn phòng phẩm', 43, 0.5, 'NCC02', 150000, N'Không', N'Cái', 'sanpham23.jpg', null, null, null, null, null, null, N'Cặp', 'TL003', 'MS003', 'CL005', 'XX001')
INSERT [dbo].[SanPham]  VALUES ('SP00024', N'Văn phòng phẩm', 76, 0.5, 'NCC03', 10000, N'Không', N'Cái', 'sanpham24.jpg', null, null, null, null, null, null, N'Khăn quàng', 'TL001', 'MS002', 'CL005', 'XX001')
INSERT [dbo].[SanPham]  VALUES ('SP00025', N'Sách', 12, 0.5, 'NCC04', 15000, N'Không', N'Cuốn', 'sanpham25.jpg', N'Bí quyết hóa rồng', 'TG012', 'NXB003', 2014, 132, 'L004', null, null, null, null, null)
INSERT [dbo].[SanPham]VALUES ('SP00026', N'Sách', 45, 0.5, 'NCC04', 25000, N'Không', N'Cuốn', 'sanpham26.jpg', N'Câm lặng', 'TG013', 'NXB003', 2015, 203, 'L002', null, null, null, null, null)
INSERT [dbo].[SanPham] VALUES ('SP00027', N'Sách', 54, 0.5, 'NCC05', 27000, N'Không', N'Cuốn', 'sanpham27.jpg', N'Thần thoại Hy Lạp', 'TG014', 'NXB003', 2017, 400, 'L002', null, null, null, null, null)
INSERT [dbo].[SanPham]  VALUES ('SP00028', N'Văn phòng phẩm', 20, 0.5, 'NCC01', 350000, N'Không', N'Cái', 'sanpham28.jpg', null, null, null, null, null, null, N'Máy tính 520ES', 'TL002', 'MS001', 'CL001', 'XX003')
INSERT [dbo].[SanPham] VALUES ('SP00029', N'Văn phòng phẩm', 30, 0.5, 'NCC04', 520000, N'Không', N'Cái', 'sanpham29.jpg', null, null, null, null, null, null, N'Máy tính 570VN', 'TL002', 'MS002', 'CL001', 'XX003')
INSERT [dbo].[SanPham]  VALUES ('SP00030', N'Sách', 40, 0.5, 'NCC01', 50000, N'Không', N'Cuốn', 'sanpham30.jpg', N'Pegasus', 'TG015', 'NXB003', 2011, 300, 'L002', null, null, null, null, null)


GO
INSERT [dbo].[SachLoi] ([maSanPham], [loiSanPham], [soLuongLoi]) VALUES ('SP00001', N'Rách bìa', 3)
INSERT [dbo].[SachLoi] ([maSanPham], [loiSanPham], [soLuongLoi]) VALUES ('SP00001', N'Thiếu trang', 2)
INSERT [dbo].[SachLoi] ([maSanPham], [loiSanPham], [soLuongLoi]) VALUES ('SP00005', N'Thiếu trang', 4)
GO
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNhanVien], [quyen]) VALUES ('TK001', '123456', 'QL220101', 1)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNhanVien], [quyen]) VALUES ('TK002', '123456', 'QL130402', 1)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNhanVien], [quyen]) VALUES ('TK003', '123456', 'NV140403', 0)
INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNhanVien], [quyen]) VALUES ('TK004', '123456', 'NV150404', 0)
GO
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngayLapHoaDon], [ghiChu],[tienKhachDua], [tinhTrang]) VALUES ('HD1300001', 'QL220101', 'KH110001', '2022-10-30', N'Không', 500000, 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngayLapHoaDon], [ghiChu],  [tienKhachDua], [tinhTrang]) VALUES ('HD1400002', 'QL130402', 'KH120002','2022-10-28', N'Không',  300000, 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngayLapHoaDon], [ghiChu], [tienKhachDua], [tinhTrang]) VALUES ('HD1400003', 'NV140403', 'KH120002', '2022-10-29', N'Không', 400000, 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [maNhanVien], [maKhachHang], [ngayLapHoaDon], [ghiChu],  [tienKhachDua], [tinhTrang]) VALUES ('HD1500004', 'NV150404', 'KH140004', '2022-09-18', N'Không',  300000, 1)
GO
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [donGia]) VALUES ('HD1300001', 'SP00001', 2, 50000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [donGia]) VALUES ('HD1300001', 'SP00002', 3, 75000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [donGia]) VALUES ('HD1400002', 'SP00003', 2, 25000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [donGia]) VALUES ('HD1400002', 'SP00004', 2, 20000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [donGia]) VALUES ('HD1400003', 'SP00002', 2, 200000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maSanPham], [soLuong], [donGia]) VALUES ('HD1500004', 'SP00001', 2, 150000)



INSERT [dbo].[TaiKhoan] ([tenDangNhap], [matKhau], [maNhanVien], [quyen]) VALUES ('1', '1', 'QL220101', 1)
go

create function getNameProduct
(
	@tenSach nvarchar(255), 
	@tenvpp nvarchar(255)
)
returns nvarchar(255)
as
begin
	if @tenSach like null
	begin
		return @tenvpp
	end
	return @tenSach
end
go