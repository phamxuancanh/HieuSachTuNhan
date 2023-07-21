package entity;

public class ChiTietHoaDon {
	private HoaDon hoaDon;
	private SanPham sanPham;
	private int soLuong;
	private long donGia;

	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(HoaDon hoaDon, SanPham sanPham, int soLuong, long donGia) {
		super();
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public long tinhThanhTien() {
		return soLuong * donGia;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
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

	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", sanPham=" + sanPham + ", soLuong=" + soLuong + ", donGia="
				+ donGia + "]";
	}

}
