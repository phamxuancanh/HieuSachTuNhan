package entity;

public class VanPhongPham extends SanPham {
	private String tenVanPhongPham;
	private TheLoaiVanPhongPham loaiVanPhongPham;
	private MauSac mauSac;
	private ChatLieu chatLieu;
	private XuatXu xuatXu;

	public VanPhongPham() {
		super();

	}

	public VanPhongPham(String maSanPham) {
		super(maSanPham);

	}

	public VanPhongPham(String maSanPham, String loaiSanPham, int soLuongTon, double trongLuong, NhaCungCap nhaCungCap,
			long giaNhap, String ghiChu, String donViSanPham, String hinhAnh, String tenVanPhongPham,
			TheLoaiVanPhongPham loaiVanPhongPham, MauSac mauSac, ChatLieu chatLieu, XuatXu xuatXu) {
		super(maSanPham, loaiSanPham, soLuongTon, trongLuong, nhaCungCap, giaNhap, ghiChu, donViSanPham, hinhAnh);
		this.tenVanPhongPham = tenVanPhongPham;
		this.loaiVanPhongPham = loaiVanPhongPham;
		this.mauSac = mauSac;
		this.chatLieu = chatLieu;
		this.xuatXu = xuatXu;
	}

	public String getTenVanPhongPham() {
		return tenVanPhongPham;
	}

	public void setTenVanPhongPham(String tenVanPhongPham) {
		this.tenVanPhongPham = tenVanPhongPham;
	}

	public TheLoaiVanPhongPham getLoaiVanPhongPham() {
		return loaiVanPhongPham;
	}

	public void setLoaiVanPhongPham(TheLoaiVanPhongPham loaiVanPhongPham) {
		this.loaiVanPhongPham = loaiVanPhongPham;
	}

	public MauSac getMauSac() {
		return mauSac;
	}

	public void setMauSac(MauSac mauSac) {
		this.mauSac = mauSac;
	}

	public ChatLieu getChatLieu() {
		return chatLieu;
	}

	public void setChatLieu(ChatLieu chatLieu) {
		this.chatLieu = chatLieu;
	}

	public XuatXu getXuatXu() {
		return xuatXu;
	}

	public void setXuatXu(XuatXu xuatXu) {
		this.xuatXu = xuatXu;
	}

	@Override
	public String toString() {
		return "VanPhongPham [tenVanPhongPham=" + tenVanPhongPham + ", loaiVanPhongPham=" + loaiVanPhongPham
				+ ", mauSac=" + mauSac + ", chatLieu=" + chatLieu + ", xuatXu=" + xuatXu + "]";
	}

}
