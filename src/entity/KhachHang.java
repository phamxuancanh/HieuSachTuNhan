package entity;

import java.sql.SQLException;
import java.time.LocalDate;

import dao.KhachHangDao;

public class KhachHang {
	private String maKhachHang;
	private String hoTenKhachHang;
	private String sDT;
	private boolean gioiTinh;

	private String diaChi;

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getHoTenKhachHang() {
		return hoTenKhachHang;
	}

	public void setHoTenKhachHang(String hoTenKhachHang) {
		this.hoTenKhachHang = hoTenKhachHang;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getsDT() {
		return sDT;
	}

	public void setsDT(String sDT) {
		this.sDT = sDT;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public static String auto_ID() {
		KhachHangDao khachhang_dao = new KhachHangDao();
		String idPrefix = "KH";
		LocalDate myObj = LocalDate.now();
		String ngayMaKH = String.valueOf(myObj.getDayOfMonth());
		int length = 0;
		try {
			length = khachhang_dao.getDSKhachHang().size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String finalId = idPrefix + ngayMaKH + String.format("%04d", length + 1);
		return finalId;
	}

	public KhachHang(String maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}

	public KhachHang(String maKhachHang, String hoTenKhachHang, String sDT, boolean gioiTinh, String diaChi) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoTenKhachHang = hoTenKhachHang;
		this.gioiTinh = gioiTinh;
		this.sDT = sDT;
		this.diaChi = diaChi;
	}

	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", hoTenKhachHang=" + hoTenKhachHang + ", sDT=" + sDT
				+ ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + "]";
	}

}
