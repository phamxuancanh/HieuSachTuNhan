package entity;

public class TheLoaiVanPhongPham {
	private String maLoai;
	private String tenLoai;

	public TheLoaiVanPhongPham() {
		super();

	}

	public TheLoaiVanPhongPham(String maLoai) {
		super();
		this.maLoai = maLoai;

	}

	public TheLoaiVanPhongPham(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	@Override
	public String toString() {
		return "TheLoaiVanPhongPham [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}

}
