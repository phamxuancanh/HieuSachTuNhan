package entity;

public class XuatXu {
	private String maXuatXu;
	private String tenXuatXu;

	public XuatXu() {
		super();

	}

	public XuatXu(String maXuatXu) {
		super();
		this.maXuatXu = maXuatXu;

	}

	public XuatXu(String maXuatXu, String tenXuatXu) {
		super();
		this.maXuatXu = maXuatXu;
		this.tenXuatXu = tenXuatXu;
	}

	public String getMaXuatXu() {
		return maXuatXu;
	}

	public void setMaXuatXu(String maXuatXu) {
		this.maXuatXu = maXuatXu;
	}

	public String getTenXuatXu() {
		return tenXuatXu;
	}

	public void setTenXuatXu(String tenXuatXu) {
		this.tenXuatXu = tenXuatXu;
	}

	@Override
	public String toString() {
		return "XuatXu [maXuatXu=" + maXuatXu + ", tenXuatXu=" + tenXuatXu + "]";
	}

}
