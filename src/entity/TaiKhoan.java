package entity;

public class TaiKhoan {
	private String tenDangNhap;
	private String matKhau;
	private NhanVien nhanVien;
	private boolean quyen;

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String tenDangNhap) {
		super();
		this.tenDangNhap = tenDangNhap;
	}

	public TaiKhoan(String tenDangNhap, String matKhau, NhanVien nhanVien, boolean quyen) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
		this.quyen = quyen;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public boolean isQuyen() {
		return quyen;
	}

	public void setQuyen(boolean quyen) {
		this.quyen = quyen;
	}

	@Override
	public String toString() {
		return "TaiKhoan [tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + ", nhanVien=" + nhanVien + ", quyen="
				+ quyen + "]";
	}

}
