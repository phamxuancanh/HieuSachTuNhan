package entity;

public class ChiTietHoaDonDoiTra {
	private HoaDonDoiTra hoaDonDoiTra;
	private SanPham sanPham;
	private int soLuong;
	private long donGia;

	public HoaDonDoiTra getHoaDonDoiTra() {
		return hoaDonDoiTra;
	}

	public void setHoaDonDoiTra(HoaDonDoiTra hoaDonDoiTra) {
		this.hoaDonDoiTra = hoaDonDoiTra;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public long getDonGia() {
		return donGia;
	}

	public void setDonGia(long donGia) {
		this.donGia = donGia;
	}

	public ChiTietHoaDonDoiTra(HoaDonDoiTra hoaDonDoiTra, SanPham sanPham, int soLuong, long donGia) {
		super();
		this.hoaDonDoiTra = hoaDonDoiTra;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public ChiTietHoaDonDoiTra() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ChiTietHoaDonDoiTra [hoaDon=" + hoaDonDoiTra + ", sanPham=" + sanPham + ", soLuong=" + soLuong
				+ ", donGia=" + donGia + "]";
	}

}
