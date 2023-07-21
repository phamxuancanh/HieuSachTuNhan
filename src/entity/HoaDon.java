package entity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import dao.HoaDonDao;

public class HoaDon {
	private String maHoaDon;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private LocalDate ngayLapHoaDon;
	private String ghiChu;
	private double tienKhachDua;
	private boolean tinhTrang;

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public LocalDate getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(LocalDate ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public double getTienKhachDua() {
		return tienKhachDua;
	}

	public void setTienKhachDua(double tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}

	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public static String auto_ID() {
		HoaDonDao hoadon_dao = new HoaDonDao();
		String idPrefix = "HD";
		LocalDate myObj = LocalDate.now();
		String ngayHD = String.valueOf(myObj.getDayOfMonth());
		int length = 0;
		try {
			length = hoadon_dao.getDSHoaDon().size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String finalId = idPrefix + ngayHD + String.format("%05d", length + 1);
		return finalId;
	}

	public static String auto_Date() {
		LocalDate myObj = LocalDate.now();
		String ngay = String.valueOf(myObj.getDayOfMonth());
		String thang = String.valueOf(myObj.getMonthValue());
		String nam = String.valueOf(myObj.getYear());
		String finalDate = ngay + "-" + thang + "-" + nam;
		return finalDate;
	}

	public HoaDon(String maHoaDon, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayLapHoaDon, String ghiChu,
			double tienKhachDua, boolean tinhTrang) {
		super();
		this.maHoaDon = maHoaDon;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.ghiChu = ghiChu;
		this.tienKhachDua = tienKhachDua;
		this.tinhTrang = tinhTrang;
	}

	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang
				+ ", ngayLapHoaDon=" + ngayLapHoaDon + ", ghiChu=" + ghiChu + ", tienKhachDua=" + tienKhachDua
				+ ", tinhTrang=" + tinhTrang + "]";
	}

}
