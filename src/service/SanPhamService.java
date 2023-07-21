package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.NhanVien;
import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;

public interface SanPhamService {

	public boolean xoaSanPham(String maSP);

	public int getSoLuongSachLoi() throws SQLException;

	public int getSoLuongVPPTon() throws SQLException;

	public int getSoLuongSachTon() throws SQLException;

	public List<SanPham> getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc);

	public SanPham timSanPhamTheoMa(String maSP) throws SQLException;

	public int getSoLuongBanCuaSanPhamChayNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException;

	public Sach getSachTheoMaSP(String maSP) throws SQLException;

	public VanPhongPham getVPPTheoMaSP(String maSP) throws SQLException;

	public ArrayList<Sach> getListSach(String maSach, String tenSP, String maTheLoai, Long giaTu, Long giaDen,
			String maTacGia, String maNXB, String maNCC, boolean hetHang) throws Exception;

	public ArrayList<VanPhongPham> getListVanPhongPham(String maVPP, String tenVPP, String theLoaiVPP, Long giaTu,
			Long giaDen, String maChatLieu, String maXuatXu, String maNCC, boolean hetHang) throws Exception;

	public String getMaSPMax() throws SQLException;

	public Sach timSanPhamTheoMaSach(String maSach) throws Exception;

	public VanPhongPham timSanPhamTheoMaVPP(String maVPP) throws Exception;

	public boolean capNhatSanPham(String maSP, SanPham temp) throws Exception;

	public boolean themSanPham(SanPham sanPham) throws Exception;

	public List<Sach> getAllSach() throws Exception;

//	String ma, String ten, String theLoai, long giaTu, long giaDen, String tacGia, String nhaXB, String nhaCC, boolean hetHang
	public Sach getSachTheoTen(String ten);

	public int capNhatSoLuongSanPham(SanPham sanPham);

	public List<VanPhongPham> getAllVPP();

	public VanPhongPham getVPPTheoTen(String ten);

	public SanPham getSanPhamTheoMa(String masp) throws SQLException;
	
	public SanPham timSanPhamTheoMa1(String maSP);

}
