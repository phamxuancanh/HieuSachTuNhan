package entity;

public class SachLoi {
	private Sach sach;
	private String loiSanPham;
	private int soLuong;

	public SachLoi() {
		super();

	}

	public SachLoi(Sach sach, String loiSanPham, int soLuong) {
		super();
		this.sach = sach;
		this.loiSanPham = loiSanPham;
		this.soLuong = soLuong;
	}

	public Sach getSach() {
		return sach;
	}

	public void setSach(Sach sach) {
		this.sach = sach;
	}

	public String getLoiSanPham() {
		return loiSanPham;
	}

	public void setLoiSanPham(String loiSanPham) {
		this.loiSanPham = loiSanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "SachLoi [sach=" + sach + ", loiSanPham=" + loiSanPham + ", soLuong=" + soLuong + "]";
	}

}
