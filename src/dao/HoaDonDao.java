package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDonDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;
	private NhanVienDao nhanVienDao = new NhanVienDao();
	private KhachHangDao khachHangDao = new KhachHangDao();

	public HoaDonDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public int setNullChoMaNhanVienTrongHoaDon(String maNV) {
		try {
			String query = "update HoaDon set maNhanVien =NULL where maNhanVien=?";
			ps = con.prepareStatement(query);
			// ps.setString(1, maNVMoi);

			ps.setString(1, maNV);

			return ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	public List<HoaDon> getDSHoaDon() throws SQLException {
		List<HoaDon> dshd = new ArrayList<>();
		String query = "Select * from HoaDon";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			HoaDon hd = new HoaDon(rs.getString("maHoaDon"), new NhanVien(rs.getString("maNhanVien")),
					new KhachHang(rs.getString("maKhachHang")), rs.getDate("ngayLapHoaDon").toLocalDate(),
					rs.getString("ghiChu"), rs.getFloat("tienKhachDua"), rs.getBoolean("tinhTrang"));
			dshd.add(hd);
		}

		return dshd;
	}

	public int doiThongTinHoaDonSauKhiXoa(String tenNV) {
		try {
			String query = "update HoaDon set maNhanVien =NULL where maNhanVien=NULL";
			ps = con.prepareStatement(query);
			// ps.setString(1, maNVMoi);

			ps.setString(1, tenNV);

			return ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	public int themHoaDon(HoaDon hd) throws SQLException {
		String sql = "Insert into HoaDon values (?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, hd.getMaHoaDon());
		stmt.setString(2, hd.getNhanVien().getMaNhanVien());
		stmt.setString(3, hd.getKhachHang().getMaKhachHang());
		int day = hd.getNgayLapHoaDon().getDayOfMonth();
		int month = hd.getNgayLapHoaDon().getMonthValue();
		int year = hd.getNgayLapHoaDon().getYear();
		stmt.setString(4, year + "-" + month + "-" + day);
		stmt.setString(5, hd.getGhiChu());
		stmt.setDouble(6, hd.getTienKhachDua());
		stmt.setBoolean(7, hd.isTinhTrang());
		stmt.executeUpdate();
		return 1;
	}

	public List<HoaDon> getHoaDonTheoMa(String maHD) {
		List<HoaDon> dshd = new ArrayList<HoaDon>();
		// System.out.println(maNV);
		try {
			String query = "Select * from HoaDon where maHoaDon =?";
			ps = con.prepareStatement(query);
			ps.setString(1, maHD);
			rs = ps.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString(1), nhanVienDao.timNhanVienTheoMa(rs.getString(2)),
						khachHangDao.timKhachHangTheoMa(rs.getString(3)), rs.getDate(4).toLocalDate(), rs.getString(5),
						rs.getDouble(6), rs.getBoolean(7));

				dshd.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshd;

	}
	//Huyen--------------------------------
	public List<HoaDon> getHoaDonThuong() {
		List<HoaDon> danhSachHoaDon = new ArrayList<HoaDon>();
		// System.out.println(maNV);
		try {
			String query = "select * from HoaDon";
			ps = con.prepareStatement(query);
//			ps.setString(1, maHD);
			rs = ps.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString(1), nhanVienDao.timNhanVienTheoMa(rs.getString(2)),
						khachHangDao.timKhachHangTheoMa(rs.getString(3)), rs.getDate(4).toLocalDate(), rs.getString(5)
						, rs.getDouble(6), rs.getBoolean(7));

				danhSachHoaDon.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSachHoaDon;

	}
	//DSHD theo mã
	public HoaDon timHoaDonTheoMa(String maHoaDon) throws SQLException {
		HoaDon hoaDon = null;
		// System.out.println(maNV);
		String query = "Select * from HoaDon where maHoaDon=?";
		ps = con.prepareStatement(query);
		ps.setString(1, maHoaDon);
		rs = ps.executeQuery();
		while (rs.next()) {
			hoaDon = new HoaDon(rs.getString("maHoaDon"), new NhanVien(rs.getString("maNhanVien")),
					new KhachHang(rs.getString("maKhachHang")), rs.getDate("ngayLapHoaDon").toLocalDate(),
					rs.getString("ghiChu"), rs.getFloat("tienKhachDua"),
					rs.getBoolean("tinhTrang"));
			
			
		}
		return hoaDon;
	}
	public List<HoaDon> getHoaDonTheoTen(String tenNV) throws SQLException{
		List<HoaDon> dshd = new ArrayList<HoaDon>();
		// System.out.println(maNV);
		try {
			String query = "select * from NhanVien\r\n"
					+ "\r\n"
					+ "inner join HoaDon\r\n"
					+ "on NhanVien.maNhanVien = HoaDon.maNhanVien\r\n"
					+ "where NhanVien.hotenNhanVien like N'%" + tenNV + "%'";
					
			PreparedStatement ps = con.prepareStatement(query);
//			stmt.setString(1, tenNV);
			ResultSet rs = ps.executeQuery();;
			while (rs.next()) {
				String maHoaDon = rs.getString("maHoaDon");
				String maNV = rs.getString("maNhanVien");
				String maKH = rs.getString("maKhachHang");
//				Date ngayLap =rs.getDate("ngayLapHoaDon");
				
				HoaDon hd = new HoaDon(maHoaDon, nhanVienDao.timNhanVienTheoMa(maNV),
						khachHangDao.timKhachHangTheoMa(maKH), rs.getDate("ngayLapHoaDon").toLocalDate(), rs.getString("ghiChu"), rs.getFloat("tienKhachDua"),
						rs.getBoolean("tinhTrang"));

				dshd.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dshd;

	}
//	Tim kiem hoa don theo sdt khach hang
	
	public List<HoaDon> timHoaDonTheoSDT(String sdt) throws SQLException {
		List<HoaDon> dshd = new ArrayList<HoaDon>();
		
		// System.out.println(maNV);
		String query = "  select * from HoaDon \r\n"
				+ "  inner join KhachHang\r\n"
				+ "  on HoaDon.maKhachHang = KhachHang.maKhachHang\r\n"
				+ "  inner join NhanVien \r\n"
				+ "  on HoaDon.maNhanVien = nhanVien.maNhanVien\r\n"
				+ "  where khachhang.sdt = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, sdt);
		rs = ps.executeQuery();
		while (rs.next()) {
			HoaDon hd = new HoaDon(rs.getString("maHoaDon"), nhanVienDao.timNhanVienTheoMa(rs.getString("maNhanVien")),
					khachHangDao.timKhachHangTheoMa(rs.getString("maKhachHang")), rs.getDate("ngayLapHoaDon").toLocalDate(),
					rs.getString("ghiChu"), rs.getFloat("tienKhachDua"),
					rs.getBoolean("tinhTrang"));
			dshd.add(hd);
			
		}
		return dshd;
	}
	public List<HoaDon> timHoaDonTheoTenKH(String ten) throws SQLException {
		List<HoaDon> dshd = new ArrayList<HoaDon>();
		
		
		// System.out.println(maNV);
		String query = "select * from HoaDon \r\n"
				+"inner join NhanVien \r\n"
				+"on HoaDon.maNhanVien = NhanVien.maNhanVien \r\n"
				+"inner join KhachHang \r\n"
				+"on HoaDon.maKhachHang = KhachHang.maKhachHang \r\n"
				+ "where KhachHang.hotenKhachHang like N'%"+ ten +"%'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			HoaDon hd = new HoaDon(rs.getString("maHoaDon"), nhanVienDao.timNhanVienTheoMa(rs.getString("maNhanVien")),
					khachHangDao.timKhachHangTheoMa(rs.getString("maKhachHang")), rs.getDate("ngayLapHoaDon").toLocalDate(),
					rs.getString("ghiChu"), rs.getFloat("tienKhachDua"),
					rs.getBoolean("tinhTrang"));
			dshd.add(hd);
			
		}
		return dshd;
	}

}
