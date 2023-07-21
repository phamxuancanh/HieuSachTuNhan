package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.KhachHang;

public interface KhachHangService {
	public List<KhachHang> getKhachHangMuaNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc);

	public KhachHang timKhachHangTheoMa(String maKH) throws SQLException;

	public int themKhachHang(KhachHang kh) throws SQLException;

	public List<KhachHang> getDSKhachHang() throws SQLException;

	public int capNhatKhachHang(KhachHang kh) throws SQLException;

	public double getTongTienCuaKhachHangTop1(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException;

	public List<KhachHang> getTop10KHThanThiet(LocalDate ngayBatDau, LocalDate ngayKetThuc);

	public int getSoLuongHoaDonCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH)
			throws SQLException;

	public double getTongTienCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH)
			throws SQLException;

	public ArrayList<KhachHang> timKhachHangTheoSDT(String maKH) throws SQLException;

	public ArrayList<KhachHang> timKhachHangTheoTen(String tenKH) throws SQLException;

	public ArrayList<KhachHang> getListKhachHangByNameAndSDT(String maNhanVien, String tenNhanVien);

	public KhachHang timKhachHangBangSDT(String sdt) throws SQLException;
	
}
