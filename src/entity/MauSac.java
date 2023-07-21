package entity;

public class MauSac {
	private String maMau;
	private String tenMau;

	public MauSac() {
		super();
	}

	public MauSac(String maMau) {
		super();
		this.maMau = maMau;

	}

	public MauSac(String maMau, String tenMau) {
		super();
		this.maMau = maMau;
		this.tenMau = tenMau;
	}

	public String getMaMau() {
		return maMau;
	}

	public void setMaMau(String maMau) {
		this.maMau = maMau;
	}

	public String getTenMau() {
		return tenMau;
	}

	public void setTenMau(String tenMau) {
		this.tenMau = tenMau;
	}

	@Override
	public String toString() {
		return "MauSac [maMau=" + maMau + ", tenMau=" + tenMau + "]";
	}

}
