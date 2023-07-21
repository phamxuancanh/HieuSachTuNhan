package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.ChatLieu;
import entity.MauSac;
import entity.NhaCungCap;
import entity.NhaXuatBan;
import entity.Sach;
import entity.SachLoi;
import entity.SanPham;
import entity.TacGia;
import entity.TheLoaiSach;
import entity.TheLoaiVanPhongPham;
import entity.VanPhongPham;
import entity.XuatXu;

public class SanPhamDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;
	private ChatLieuDao chatLieuDao;
	private XuatXuDao xuatXuDao;
	private TheLoaiDao theloaiDao;
	private TacGiaDao tacgiaDao;

	public SanPhamDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	/**
	 * Hàm lấy danh sách tất cả sách
	 * 
	 * @return ArrayList Sách
	 * @throws Exception
	 */

	public SanPham timSanPhamTheoMa(String maSP) throws SQLException {
		SanPham sp = new SanPham();
		String query = "Select * from SanPham where maSanPham=?";
		ps = con.prepareStatement(query);
		ps.setString(1, maSP);
		rs = ps.executeQuery();
		while (rs.next()) {

			String maSanPham = rs.getString("maSanPham");
			String loaiSanPham = rs.getString("loaiSanPham");
			int soLuongTon = rs.getInt("soLuongTon");
			double trongLuong = rs.getDouble("trongLuong");

			NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
			long giaNhap = rs.getLong("giaNhap");
			String ghiChu = rs.getString("ghiChu");
			String donVi = rs.getString("soLuongTon");
			String hinhAnh = rs.getString("hinhAnh");
			sp = new SanPham(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donVi, hinhAnh);

			return sp;
		}
		return null;
	}

	public Sach getSachTheoMaSP(String maSP) throws SQLException {
		Sach s = new Sach();
		String query = "Select * from SanPham where maSanPham=?";
		ps = con.prepareStatement(query);
		ps.setString(1, maSP);
		rs = ps.executeQuery();
		while (rs.next()) {

			String maSanPham = rs.getString("maSanPham");
			String loaiSanPham = rs.getString("loaiSanPham");
			int soLuongTon = rs.getInt("soLuongTon");
			double trongLuong = rs.getDouble("trongLuong");

			NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
			long giaNhap = rs.getLong("giaNhap");
			String ghiChu = rs.getString("ghiChu");
			String donVi = rs.getString("soLuongTon");
			String hinhAnh = rs.getString("hinhAnh");
			String tenSach = rs.getString("tenSach");
			TacGia tacGia = new TacGia(rs.getString("maTacGia"));
			NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("maNXB"));
			int namXuatBan = rs.getInt("namXB");
			int soTrang = rs.getInt("soTrang");
			TheLoaiSach theLoaiSach = new TheLoaiSach(rs.getString("maTheLoai"));
			s = new Sach(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donVi, hinhAnh, tenSach,
					tacGia, nhaXuatBan, namXuatBan, soTrang, theLoaiSach);
			return s;
		}
		return null;
	}

	public String getLoaiSanPhamTheoMa() throws SQLException {
		String query = "Select loaiSanPham from SanPham where maSanPham = ?";
		ps = con.prepareStatement(query);
		ps.executeQuery();
		return null;
	}

	public SanPham getSanPhamTheoMa(String masp) throws SQLException {
		String query = "Select * from SanPham where maSanPham=?";
		ps = con.prepareStatement(query);
		ps.setString(1, masp);
		rs = ps.executeQuery();
		while (rs.next()) {
			SanPham sp = new SanPham(rs.getString("maSanPham"));
			return sp;
		}
		return null;
	}

	public VanPhongPham getVPPTheoMaSP(String maSP) throws SQLException {
		VanPhongPham vpp = new VanPhongPham();
		String query = "Select * from SanPham where maSanPham=?";
		ps = con.prepareStatement(query);
		ps.setString(1, maSP);
		rs = ps.executeQuery();
		while (rs.next()) {
			String maSanPham = rs.getString("maSanPham");
			String loaiSanPham = rs.getString("loaiSanPham");
			int soLuongTon = rs.getInt("soLuongTon");
			double trongLuong = rs.getDouble("trongLuong");
			NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
			long giaNhap = rs.getLong("giaNhap");
			String ghiChu = rs.getString("ghiChu");
			String donVi = rs.getString("soLuongTon");
			String hinhAnh = rs.getString("hinhAnh");
			String tenVanPhongPham = rs.getString("tenVanPhongPham");
			TheLoaiVanPhongPham loaiVanPhongPham = new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"));
			MauSac mauSac = new MauSac(rs.getString("maMauSac"));
			ChatLieu chatLieu = new ChatLieu(rs.getString("maChatLieu"));
			XuatXu xuatXu = new XuatXu(rs.getString("maXuatXu"));
			TheLoaiSach theLoaiSach = new TheLoaiSach(rs.getString("maTheLoai"));
			vpp = new VanPhongPham(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donVi, hinhAnh,
					tenVanPhongPham, loaiVanPhongPham, mauSac, chatLieu, xuatXu);
			return vpp;
		}
		return null;
	}

	public ArrayList<Sach> getListSach(String maSach, String tenSP, String maTheLoai, Long giaTu, Long giaDen,
			String maTacGia, String maNXB, String maNCC, boolean hetHang) throws Exception {

		ArrayList<Sach> listSach = new ArrayList<>();
		query = "SELECT SanPham.maSanPham, SanPham.soLuongTon,SanPham.loaiSanPham, NhaCungCap.maNCC, NhaCungCap.tenNCC, SanPham.giaNhap, SanPham.ghiChu, SanPham.trongLuong, SanPham.donViSanPham, SanPham.hinhAnh, SanPham.tenSach, TacGia.maTacGia, \r\n"
				+ "                  TacGia.tenTacGia, NhaXuatBan.maNXB, NhaXuatBan.tenNXB, SanPham.namXB, SanPham.soTrang, TheLoaiSach.maTheLoai, TheLoaiSach.tenTheLoai\r\n"
				+ "FROM     SanPham INNER JOIN\r\n"
				+ "                  NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC INNER JOIN\r\n"
				+ "                  NhaXuatBan ON SanPham.maNXB = NhaXuatBan.maNXB INNER JOIN\r\n"
				+ "                  TacGia ON SanPham.maTacGia = TacGia.maTacGia INNER JOIN\r\n"
				+ "                  TheLoaiSach ON SanPham.maTheLoai = TheLoaiSach.maTheLoai"
				+ " where maSanPham like '%" + maSach + "%' and tenSach like N'%" + tenSP
				+ "%' and SanPham.maTheLoai like '%" + maTheLoai + "%' \r\n"
				+ "	and SanPham.giaNhap > ? and SanPham.giaNhap < ? and SanPham.maTacGia like '%" + maTacGia + "%' \r\n"
				+ "	and SanPham.maNXB like '%" + maNXB + "%' and SanPham.maNCC like '%" + maNCC + "%'";
		if (hetHang) {
			query = query + " and soLuongTon = 0";
		}
		ps = con.prepareStatement(query);
		ps.setLong(1, giaTu);
		ps.setLong(2, giaDen);
		rs = ps.executeQuery();
		while (rs.next()) {
			Sach s = new Sach(rs.getString("maSanPham"), rs.getString("loaiSanPham"), rs.getInt("soLuongTon"),
					rs.getDouble("trongLuong"), new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")),
					rs.getLong("giaNhap"), rs.getString("ghiChu"), rs.getString("donViSanPham"),
					rs.getString("hinhAnh"), rs.getString("tenSach"),
					new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")),
					new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB")), rs.getInt("namXB"),
					rs.getInt("soTrang"), new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai")));
			listSach.add(s);
		}
		return listSach;
	}

	public ArrayList<VanPhongPham> getListVanPhongPham(String maVPP, String tenVPP, String theLoaiVPP, Long giaTu,
			Long giaDen, String maChatLieu, String maXuatXu, String maNCC, boolean hetHang) throws Exception {
		ArrayList<VanPhongPham> list = new ArrayList<>();
		query = "SELECT SanPham.maSanPham, SanPham.loaiSanPham, SanPham.soLuongTon, SanPham.trongLuong, "
				+ "	NhaCungCap.maNCC, NhaCungCap.tenNCC, SanPham.giaNhap, SanPham.ghiChu, SanPham.donViSanPham, SanPham.hinhAnh, "
				+ "	SanPham.tenVanPhongPham, LoaiVanPhongPham.maLoaiVanPhongPham, "
				+ " LoaiVanPhongPham.tenTheLoai, MauSac.maMauSac, MauSac.tenMau, ChatLieu.maChatLieu, ChatLieu.tenChatLieu, XuatXu.maXuatXu, XuatXu.tenXuatXu "
				+ "	FROM SanPham INNER JOIN"
				+ "	LoaiVanPhongPham ON SanPham.maLoaiVanPhongPham = LoaiVanPhongPham.maLoaiVanPhongPham INNER JOIN "
				+ "	ChatLieu ON SanPham.maChatLieu = ChatLieu.maChatLieu INNER JOIN "
				+ " MauSac ON SanPham.maMauSac = MauSac.maMauSac INNER JOIN "
				+ "	 XuatXu ON SanPham.maXuatXu = XuatXu.maXuatXu INNER JOIN "
				+ "	NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC\r\n" + "	where maSanPham like '%" + maVPP
				+ "%' and tenVanPhongPham like N'%" + tenVPP + "%' and SanPham.maLoaiVanPhongPham like '%" + theLoaiVPP
				+ "%' \r\n" + "	and SanPham.giaNhap > ? and SanPham.giaNhap < ? and SanPham.maChatLieu like '%"
				+ maChatLieu + "%' \r\n" + "	and SanPham.maXuatXu like '%" + maXuatXu
				+ "%' and SanPham.maNCC like '%" + maNCC + "%' ";
		if (hetHang) {
			query = query + " and soLuongTon = 0";
		}
		ps = con.prepareStatement(query);
		ps.setLong(1, giaTu);
		ps.setLong(2, giaDen);
		rs = ps.executeQuery();
		while (rs.next()) {
			VanPhongPham vanPhongPham = new VanPhongPham(rs.getString("maSanPham"), rs.getString("loaiSanPham"),
					rs.getInt("soLuongTon"), rs.getDouble("trongLuong"),
					new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")), rs.getLong("giaNhap"),
					rs.getString("ghiChu"), rs.getString("donViSanPham"), rs.getString("hinhAnh"),
					rs.getString("tenVanPhongPham"),
					new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai")),
					new MauSac(rs.getString("maMauSac"), rs.getString("tenMau")),
					new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu")),
					new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu")));
			list.add(vanPhongPham);
		}
		return list;
	}

	public boolean themSanPham(SanPham sanPham) throws Exception {
		Sach s = new Sach();
		VanPhongPham v = new VanPhongPham();
		if (sanPham instanceof Sach) {
			s = (Sach) sanPham;
			query = "INSERT [dbo].[SanPham] VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?," + "?, ?, ?, ?, ?, "
					+ "?, ?, null, null, null, null, null)";
			ps = con.prepareStatement(query);
			ps.setString(1, s.getMaSanPham());
			ps.setString(2, s.getLoaiSanPham());
			ps.setInt(3, s.getSoLuongTon());
			ps.setDouble(4, s.getTrongLuong());
			ps.setString(5, s.getNhaCungCap().getMaNCC());
			ps.setLong(6, s.getGiaNhap());
			ps.setString(7, s.getGhiChu());
			ps.setString(8, s.getDonViSanPham());
			ps.setString(9, s.getHinhAnh());
			ps.setString(10, s.getTenSach());
			ps.setString(11, s.getTacGia().getMaTacGia());
			ps.setString(12, s.getNhaXuatBan().getMaNXB());
			ps.setInt(13, s.getNamXuatBan());
			ps.setInt(14, s.getSoTrang());
			ps.setString(15, s.getTheLoaiSach().getMaLoai());
		} else {
			v = (VanPhongPham) sanPham;
			query = "INSERT [dbo].[SanPham] VALUES (?,?,?,?,?,?,?,?,?, "
					+ "null, null, null, null, null, null, ?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, v.getMaSanPham());
			ps.setString(2, v.getLoaiSanPham());
			ps.setInt(3, v.getSoLuongTon());
			ps.setDouble(4, v.getTrongLuong());
			ps.setString(5, v.getNhaCungCap().getMaNCC());
			ps.setLong(6, v.getGiaNhap());
			ps.setString(7, v.getGhiChu());
			ps.setString(8, v.getDonViSanPham());
			ps.setString(9, v.getHinhAnh());
			ps.setString(10, v.getTenVanPhongPham());
			ps.setString(11, v.getLoaiVanPhongPham().getMaLoai());
			ps.setString(12, v.getMauSac().getMaMau());
			ps.setString(13, v.getChatLieu().getMaChatLieu());
			ps.setString(14, v.getXuatXu().getMaXuatXu());
		}
		rsCheck = ps.executeUpdate();
		if (rsCheck == 0) {
			return false;
		}
		return true;
	}

	public boolean capNhatSanPham(String maSP, SanPham temp) throws Exception {
		Sach s = new Sach();
		VanPhongPham v = new VanPhongPham();
		if (temp instanceof Sach) {
			s = (Sach) temp;
			query = "update SanPham\r\n" + "set maTheLoai = ?, soLuongTon = ? , trongLuong = ? ,"
					+ " maNCC = ? , giaNhap = ? , ghiChu = ? , donViSanPham = ? , hinhAnh = ? ,"
					+ "tenSach = ? , maTacGia = ? ,maNXB = ? , namXB = ? , soTrang = ? " + "where maSanPham like ?";
			ps = con.prepareStatement(query);
			ps.setString(14, s.getMaSanPham());
			ps.setInt(2, s.getSoLuongTon());
			ps.setDouble(3, s.getTrongLuong());
			ps.setString(4, s.getNhaCungCap().getMaNCC());
			ps.setLong(5, s.getGiaNhap());
			ps.setString(6, s.getGhiChu());
			ps.setString(7, s.getDonViSanPham());
			ps.setString(8, s.getHinhAnh());
			ps.setString(9, s.getTenSach());
			ps.setString(10, s.getTacGia().getMaTacGia());
			ps.setString(11, s.getNhaXuatBan().getMaNXB());
			ps.setInt(12, s.getNamXuatBan());
			ps.setInt(13, s.getSoTrang());
			ps.setString(1, s.getTheLoaiSach().getMaLoai());
		} else {
			v = (VanPhongPham) temp;
			query = "update SanPham\r\n" + "set maLoaiVanPhongPham = ?, soLuongTon = ? , trongLuong = ? ,"
					+ " maNCC = ? , giaNhap = ? , ghiChu = ? , donViSanPham = ? , hinhAnh = ? ,"
					+ "tenVanPhongPham = ? , maMauSac = ? ,maChatLieu = ? , maXuatXu = ? " + "where maSanPham like ?";
			ps = con.prepareStatement(query);
			ps.setString(13, v.getMaSanPham());
			ps.setInt(2, v.getSoLuongTon());
			ps.setDouble(3, v.getTrongLuong());
			ps.setString(4, v.getNhaCungCap().getMaNCC());
			ps.setLong(5, v.getGiaNhap());
			ps.setString(6, v.getGhiChu());
			ps.setString(7, v.getDonViSanPham());
			ps.setString(9, v.getHinhAnh());
			ps.setString(9, v.getTenVanPhongPham());
			ps.setString(1, v.getLoaiVanPhongPham().getMaLoai());
			ps.setString(10, v.getMauSac().getMaMau());
			ps.setString(11, v.getChatLieu().getMaChatLieu());
			ps.setString(12, v.getXuatXu().getMaXuatXu());
		}
		rsCheck = ps.executeUpdate();
		if (rsCheck == 0) {
			return false;
		}
		return true;
	}

	public boolean xoaSanPham(String maSP) {

		return false;
	}

	public String getMaSPMax() throws SQLException {
		query = "select MAX(maSanPham) as maSP from SanPham";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		String s = null;
		while (rs.next()) {
			s = rs.getString("maSP");
		}
		return s;
	}

	public Sach timSanPhamTheoMaSach(String maSach) throws Exception {
		query = "SELECT SanPham.maSanPham, SanPham.soLuongTon,SanPham.loaiSanPham, NhaCungCap.maNCC, NhaCungCap.tenNCC, SanPham.giaNhap, SanPham.ghiChu, SanPham.trongLuong, SanPham.donViSanPham, SanPham.hinhAnh, SanPham.tenSach, TacGia.maTacGia, \r\n"
				+ "                  TacGia.tenTacGia, NhaXuatBan.maNXB, NhaXuatBan.tenNXB, SanPham.namXB, SanPham.soTrang, TheLoaiSach.maTheLoai, TheLoaiSach.tenTheLoai\r\n"
				+ "FROM     SanPham INNER JOIN\r\n"
				+ "                  NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC INNER JOIN\r\n"
				+ "                  NhaXuatBan ON SanPham.maNXB = NhaXuatBan.maNXB INNER JOIN\r\n"
				+ "                  TacGia ON SanPham.maTacGia = TacGia.maTacGia INNER JOIN\r\n"
				+ "                  TheLoaiSach ON SanPham.maTheLoai = TheLoaiSach.maTheLoai"
				+ " where SanPham.maSanPham like ?";
		ps = con.prepareStatement(query);
		ps.setString(1, maSach);
		rs = ps.executeQuery();
		while (rs.next()) {
			Sach s = new Sach(rs.getString("maSanPham"), rs.getString("loaiSanPham"), rs.getInt("soLuongTon"),
					rs.getDouble("trongLuong"), new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")),
					rs.getLong("giaNhap"), rs.getString("ghiChu"), rs.getString("donViSanPham"),
					rs.getString("hinhAnh"), rs.getString("tenSach"),
					new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")),
					new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB")), rs.getInt("namXB"),
					rs.getInt("soTrang"), new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai")));
			return s;
		}
		return null;
	}

	public VanPhongPham timSanPhamTheoMaVPP(String maVPP) throws Exception {
		query = "SELECT SanPham.maSanPham, SanPham.loaiSanPham, SanPham.soLuongTon, SanPham.trongLuong, "
				+ "NhaCungCap.maNCC, NhaCungCap.tenNCC, SanPham.giaNhap, SanPham.ghiChu, SanPham.donViSanPham, SanPham.hinhAnh, \r\n"
				+ "                  SanPham.tenVanPhongPham, LoaiVanPhongPham.maLoaiVanPhongPham, LoaiVanPhongPham.tenTheLoai, MauSac.maMauSac, MauSac.tenMau, ChatLieu.maChatLieu, ChatLieu.tenChatLieu, XuatXu.maXuatXu, XuatXu.tenXuatXu\r\n"
				+ "FROM     SanPham INNER JOIN\r\n"
				+ "                  LoaiVanPhongPham ON SanPham.maLoaiVanPhongPham = LoaiVanPhongPham.maLoaiVanPhongPham INNER JOIN\r\n"
				+ "                  ChatLieu ON SanPham.maChatLieu = ChatLieu.maChatLieu INNER JOIN\r\n"
				+ "                  MauSac ON SanPham.maMauSac = MauSac.maMauSac INNER JOIN\r\n"
				+ "                  XuatXu ON SanPham.maXuatXu = XuatXu.maXuatXu INNER JOIN\r\n"
				+ "                  NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC"
				+ " where SanPham.maSanPham like ?";
		ps = con.prepareStatement(query);
		ps.setString(1, maVPP);
		rs = ps.executeQuery();
		while (rs.next()) {
			VanPhongPham vanPhongPham = new VanPhongPham(rs.getString("maSanPham"), rs.getString("loaiSanPham"),
					rs.getInt("soLuongTon"), rs.getDouble("trongLuong"),
					new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")), rs.getLong("giaNhap"),
					rs.getString("ghiChu"), rs.getString("donViSanPham"), rs.getString("hinhAnh"),
					rs.getString("tenVanPhongPham"),
					new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai")),
					new MauSac(rs.getString("maMauSac"), rs.getString("tenMau")),
					new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu")),
					new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu")));
			return vanPhongPham;
		}
		return null;
	}

	public List<Sach> getAllSach() throws Exception {
		List<Sach> dsS = new ArrayList<>();
		theloaiDao = new TheLoaiDao();
		tacgiaDao = new TacGiaDao();
		try {
			String query = "Select * from SanPham where loaiSanPham=?";
			ps = con.prepareStatement(query);
			ps.setString(1, "Sách");
			rs = ps.executeQuery();
			while (rs.next()) {

				String maSanPham = rs.getString("maSanPham");
				String loaiSanPham = rs.getString("loaiSanPham");
				int soLuongTon = rs.getInt("soLuongTon");
				double trongLuong = rs.getDouble("trongLuong");

				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				long giaNhap = rs.getLong("giaNhap");
				String ghiChu = rs.getString("ghiChu");
				String donVi = rs.getString("soLuongTon");
				String hinhAnh = rs.getString("hinhAnh");
				String tenSach = rs.getString("tenSach");
				TacGia tacGia = new TacGia();
				if (rs.getString("maTacGia") != null) {
					tacGia = tacgiaDao.getTacGia(rs.getString("maTacGia")).get(0);

				} else {
					tacGia = null;
				}
				NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("maNXB"));
				int namXuatBan = rs.getInt("namXB");
				int soTrang = rs.getInt("soTrang");
				TheLoaiSach theLoaiSach = theloaiDao.getSachTheoTheLoai(rs.getString("maTheLoai")).get(0);
				Sach s = new Sach(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donVi, hinhAnh,
						tenSach, tacGia, nhaXuatBan, namXuatBan, soTrang, theLoaiSach);
				dsS.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsS;
	}

	public List<VanPhongPham> getAllVPP() {
		List<VanPhongPham> dsVPP = new ArrayList<>();
		chatLieuDao = new ChatLieuDao();
		xuatXuDao = new XuatXuDao();
		try {
			String query = "Select * from SanPham where loaiSanPham=?";
			ps = con.prepareStatement(query);
			ps.setString(1, "Văn phòng phẩm");
			rs = ps.executeQuery();
			while (rs.next()) {

				String maSanPham = rs.getString("maSanPham");
				String loaiSanPham = rs.getString("loaiSanPham");
				int soLuongTon = rs.getInt("soLuongTon");
				double trongLuong = rs.getDouble("trongLuong");

				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				long giaNhap = rs.getLong("giaNhap");
				String ghiChu = rs.getString("ghiChu");
				String donVi = rs.getString("soLuongTon");
				String hinhAnh = rs.getString("hinhAnh");
				String tenVPP = rs.getString("tenVanPhongPham");
				TheLoaiVanPhongPham theLoaiVPP = new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"));
				MauSac mauSac = new MauSac(rs.getString("maMauSac"));
				ChatLieu chatLieu = chatLieuDao.getChatLieu(rs.getString("maChatLieu")).get(0);
				XuatXu xuatXu = xuatXuDao.getXuatXu(rs.getString("maXuatXu")).get(0);
				VanPhongPham vpp = new VanPhongPham(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap,
						ghiChu, donVi, hinhAnh, tenVPP, theLoaiVPP, mauSac, chatLieu, xuatXu);

				dsVPP.add(vpp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsVPP;
	}

	public Sach getSachTheoTen(String ten) {
		List<Sach> dsS = new ArrayList<>();
		try {
			String query = "Select * from SanPham where tenSach=?";
			ps = con.prepareStatement(query);
			ps.setString(1, ten);
			rs = ps.executeQuery();
			while (rs.next()) {

				String maSanPham = rs.getString("maSanPham");
				String loaiSanPham = rs.getString("loaiSanPham");
				int soLuongTon = rs.getInt("soLuongTon");
				double trongLuong = rs.getDouble("trongLuong");

				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				long giaNhap = rs.getLong("giaNhap");
				String ghiChu = rs.getString("ghiChu");
				String donVi = rs.getString("soLuongTon");
				String hinhAnh = rs.getString("hinhAnh");
				String tenSach = rs.getString("tenSach");
				TacGia tacGia = new TacGia(rs.getString("maTacGia"));
				NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("maNXB"));
				int namXuatBan = rs.getInt("namXB");
				int soTrang = rs.getInt("soTrang");
				TheLoaiSach theLoaiSach = new TheLoaiSach(rs.getString("maTheLoai"));
				Sach s = new Sach(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donVi, hinhAnh,
						tenSach, tacGia, nhaXuatBan, namXuatBan, soTrang, theLoaiSach);
				dsS.add(s);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsS.get(0);
	}

	public VanPhongPham getVPPTheoTen(String ten) {
		List<VanPhongPham> dsV = new ArrayList<>();
		try {
			String query = "Select * from SanPham where tenVanPhongPham=?";
			ps = con.prepareStatement(query);
			ps.setString(1, ten);
			rs = ps.executeQuery();
			while (rs.next()) {

				String maSanPham = rs.getString("maSanPham");
				String loaiSanPham = rs.getString("loaiSanPham");
				int soLuongTon = rs.getInt("soLuongTon");
				double trongLuong = rs.getDouble("trongLuong");

				NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
				long giaNhap = rs.getLong("giaNhap");
				String ghiChu = rs.getString("ghiChu");
				String donVi = rs.getString("soLuongTon");
				String hinhAnh = rs.getString("hinhAnh");
				String tenVPP = rs.getString("tenVanPhongPham");
				TheLoaiVanPhongPham theLoaiVPP = new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"));
				MauSac mauSac = new MauSac(rs.getString("maMauSac"));
				ChatLieu chatLieu = chatLieuDao.getChatLieu(rs.getString("maChatLieu")).get(0);
				XuatXu xuatXu = xuatXuDao.getXuatXu(rs.getString("maXuatXu")).get(0);
				VanPhongPham vpp = new VanPhongPham(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap,
						ghiChu, donVi, hinhAnh, tenVPP, theLoaiVPP, mauSac, chatLieu, xuatXu);

				dsV.add(vpp);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsV.get(0);
	}

	public int capNhatSoLuongSanPham(SanPham sanPham) {
		String sql = "UPDATE SanPham SET soLuongTon = ? where maSanPham = ?";
		try {
			PreparedStatement stmt = con.prepareCall(sql);
			stmt.setInt(1, sanPham.getSoLuongTon());
			stmt.setString(2, sanPham.getMaSanPham());

			return stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 1;
	}
	public boolean kiemTraTonTaiSanPham(String tenSP) {
		String query = "select * from SanPham where tenSach = N'"+tenSP+"' or tenVanPhongPham = N'"+tenSP+"'";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
//	Minh Huyen-------------------------------------
	public SanPham timSanPhamTheoMa1(String maSP) throws SQLException {
		SanPham sanPham = null;
		// System.out.println(maNV);
		String query = "Select * from SanPham where maSanPham=?";
		ps = con.prepareStatement(query);
		ps.setString(1, maSP);
		rs = ps.executeQuery();

		while (rs.next()) {

			String maSanPham = rs.getString("maSanPham");
			String loaiSanPham = rs.getString("loaiSanPham");
			int soLuongTon = rs.getInt("soLuongTon");
			double trongLuong = rs.getDouble("trongLuong");

			NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
			long giaNhap = rs.getLong("giaNhap");
			String ghiChu = rs.getString("ghiChu");
			String donVi = rs.getString("donViSanPham");
			String hinhAnh = rs.getString("hinhAnh");
//			String tenVPP= rs.getString("tenVanPhongPham");
			sanPham = new SanPham(maSanPham, loaiSanPham, soLuongTon, trongLuong , ncc, giaNhap, ghiChu, donVi, hinhAnh);
			
			
		}
		return sanPham;
	}
	

}
