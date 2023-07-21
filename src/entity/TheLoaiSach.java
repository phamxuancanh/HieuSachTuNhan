package entity;

public class TheLoaiSach {
	private String maLoai;
	private String tenLoai;

	public TheLoaiSach() {
		super();

	}

	public TheLoaiSach(String maLoai) {
		super();
		this.maLoai = maLoai;

	}

	public TheLoaiSach(String maLoai, String tenLoai) {
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
		return "TheLoaiSach [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}

}
