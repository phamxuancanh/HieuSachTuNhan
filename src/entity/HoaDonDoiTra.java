package entity;

import java.time.LocalDate;

public class HoaDonDoiTra {
	private String maHoaDonDoiTra;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private LocalDate ngayLapHoaDon;
	private String ghiChu;
	private double tienKhachDua;
	private HoaDon hoaDon;
	private double tienPhaiTru;

	public String getMaHoaDonDoiTra() {
		return maHoaDonDoiTra;
	}

	public void setMaHoaDonDoiTra(String maHoaDonDoiTra) {
		this.maHoaDonDoiTra = maHoaDonDoiTra;
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

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public double getTienPhaiTru() {
		return tienPhaiTru;
	}

	public void setTienPhaiTru(double tienPhaiTru) {
		this.tienPhaiTru = tienPhaiTru;
	}

	@Override
	public String toString() {
		return "HoaDonDoiTra [maHoaDonDoiTra=" + maHoaDonDoiTra + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang
				+ ", ngayLapHoaDon=" + ngayLapHoaDon + ", ghiChu=" + ghiChu + ", tienKhachDua=" + tienKhachDua
				+ ", hoaDon=" + hoaDon + ", tienPhaiTru=" + tienPhaiTru + "]";
	}

	public HoaDonDoiTra() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDonDoiTra(String maHoaDonDoiTra, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayLapHoaDon,
			String ghiChu, double tienKhachDua, HoaDon hoaDon, double tienPhaiTru) {
		super();
		this.maHoaDonDoiTra = maHoaDonDoiTra;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.ghiChu = ghiChu;
		this.tienKhachDua = tienKhachDua;
		this.hoaDon = hoaDon;
		this.tienPhaiTru = tienPhaiTru;
	}

}
