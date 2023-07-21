package entity;

public class Sach extends SanPham {
	private String tenSach;
	private TacGia tacGia;
	private NhaXuatBan nhaXuatBan;
	private int namXuatBan;
	private int soTrang;
	private TheLoaiSach theLoaiSach;

	public Sach() {
		super();
	}

	public Sach(String maSanPham) {
		super(maSanPham);
	}

	public Sach(String maSanPham, String loaiSanPham, int soLuongTon, double trongLuong, NhaCungCap nhaCungCap,
			long giaNhap, String ghiChu, String donViSanPham, String hinhAnh, String tenSach, TacGia tacGia,
			NhaXuatBan nhaXuatBan, int namXuatBan, int soTrang, TheLoaiSach theLoaiSach) {
		super(maSanPham, loaiSanPham, soLuongTon, trongLuong, nhaCungCap, giaNhap, ghiChu, donViSanPham, hinhAnh);
		this.tenSach = tenSach;
		this.tacGia = tacGia;
		this.nhaXuatBan = nhaXuatBan;
		this.namXuatBan = namXuatBan;
		this.soTrang = soTrang;
		this.theLoaiSach = theLoaiSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public TacGia getTacGia() {
		return tacGia;
	}

	public void setTacGia(TacGia tacGia) {
		this.tacGia = tacGia;
	}

	public NhaXuatBan getNhaXuatBan() {
		return nhaXuatBan;
	}

	public void setNhaXuatBan(NhaXuatBan nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}

	public int getNamXuatBan() {
		return namXuatBan;
	}

	public void setNamXuatBan(int namXuatBan) {
		this.namXuatBan = namXuatBan;
	}

	public int getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}

	public TheLoaiSach getTheLoaiSach() {
		return theLoaiSach;
	}

	public void setTheLoaiSach(TheLoaiSach theLoaiSach) {
		this.theLoaiSach = theLoaiSach;
	}

	@Override
	public String toString() {
		return "Sach [tenSach=" + tenSach + ", tacGia=" + tacGia + ", nhaXuatBan=" + nhaXuatBan + ", namXuatBan="
				+ namXuatBan + ", soTrang=" + soTrang + ", theLoaiSach=" + theLoaiSach + "]";
	}

}
