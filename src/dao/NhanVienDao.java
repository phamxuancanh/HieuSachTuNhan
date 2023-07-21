package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.KhachHang;
import entity.NhanVien;

public class NhanVienDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public NhanVienDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public int themNhanvien(NhanVien nv) throws SQLException {
		String insert = "Insert into NhanVien values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, null)";
		PreparedStatement stmt = con.prepareStatement(insert);
		stmt.setString(1, nv.getMaNhanVien());

		int day = nv.getNgaySinh().getDayOfMonth();
		int month = nv.getNgaySinh().getMonthValue();
		int year = nv.getNgaySinh().getYear();

		stmt.setString(3, year + "-" + month + "-" + day);
		stmt.setString(2, nv.getHoTenNhanVien());
		stmt.setString(4, nv.getcCCD());
		stmt.setString(5, nv.getDiaChi());
		stmt.setString(6, nv.getsDT());
		stmt.setBoolean(7, nv.isGioiTinh());
		stmt.setString(8, nv.getEmail());
		stmt.setBoolean(9, nv.isChucVu());
		stmt.setBoolean(10, nv.isCaLamViec());
		stmt.setString(11, nv.getHinhAnh());
		stmt.executeUpdate();
		return 1;
	}

	public List<NhanVien> getDSNhanVien() throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		String query = "Select * from NhanVien";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
					rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
			dsnv.add(nv);
		}

		return dsnv;
	}

	public List<NhanVien> timDanhSachNhanVienTheoMa(String maNV) throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		// System.out.println(maNV);
		String query = "Select * from NhanVien where maNhanVien LIKE CONCAT('%', ?, '%')";
		ps = con.prepareStatement(query);
		ps.setString(1, maNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
					rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
			dsnv.add(nv);

		}
		return dsnv;
	}

	public NhanVien timNhanVienTheoMa(String maNV) throws SQLException {
		NhanVien dsnv = new NhanVien();
		// System.out.println(maNV);
		String query = "Select * from NhanVien where maNhanVien=?";
		ps = con.prepareStatement(query);
		ps.setString(1, maNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
					rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
			return nv;

		}
		return null;
	}

	public List<NhanVien> timDSNhanVienTheoTen(String tenNV) throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		String query = "select * from NhanVien where hoTenNhanVien LIKE CONCAT('%', ?, '%')";
		ps = con.prepareStatement(query);
		ps.setString(1, tenNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
					rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
			dsnv.add(nv);
		}
		return dsnv;
	}

	public NhanVien timNhanVienTheoTen(String tenNV) throws SQLException {
		NhanVien nv = new NhanVien();
		String query = "select * from NhanVien where hoTenNhanVien LIKE CONCAT('%', ?, '%')";
		ps = con.prepareStatement(query);
		ps.setString(1, tenNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
					rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
			return nv;
		}
		return null;
	}

	public List<NhanVien> timNhanVienTheoSDT(String sDT) throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		String query = "select * from NhanVien where sdt LIKE CONCAT('%', ?, '%')";
		ps = con.prepareStatement(query);
		ps.setString(1, sDT);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
					rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
					rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
			dsnv.add(nv);
		}
		return dsnv;
	}

	public List<NhanVien> getListNhanVienByNameAndSDT(String tenNV, String sdt) {
		List<NhanVien> dsnv = new ArrayList<>();
		try {
			String sql = "select * from NhanVien where hoTenNhanVien LIKE CONCAT('%', ?, '%') or sdt LIKE CONCAT('%', ?, '%')";
			ps = con.prepareStatement(sql);
			ps.setString(1, tenNV);
			ps.setString(2, sdt);
			rs = ps.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
						rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
						rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
				dsnv.add(nv);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return dsnv;
	}

	public int xoaNhanVien(String maNV) {
		String query = "delete from NhanVien where maNhanVien = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, maNV);
			// rs = ps.executeQuery();
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 1;
	}

	public int suaNhanVien(NhanVien nv) {
		String sql = "UPDATE NhanVien SET hoTenNhanVien = ?, ngaySinh = ? ,cCCD = ?, diaChi =?, sdt = ?, gioiTinh =?,  email = ?, chucVu= ?, caLamViec = ?, hinhAnh=?  WHERE maNhanVien =?";
		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, nv.getHoTenNhanVien());

			int day = nv.getNgaySinh().getDayOfMonth();
			int month = nv.getNgaySinh().getMonthValue();
			int year = nv.getNgaySinh().getYear();

			ps.setString(2, year + "-" + month + "-" + day);

			ps.setString(3, nv.getcCCD());
			ps.setString(4, nv.getDiaChi());
			ps.setString(5, nv.getsDT());
			ps.setBoolean(6, nv.isGioiTinh());
			ps.setString(7, nv.getEmail());
			ps.setBoolean(8, nv.isChucVu());
			ps.setBoolean(9, nv.isCaLamViec());
			ps.setString(10, nv.getHinhAnh());
			ps.setString(11, nv.getMaNhanVien());
			// rs = ps.executeQuery();

			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public NhanVien getNhanVienByEmail(String email) {

		try {
			String query = "select *from NhanVien where email =?";

			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
						rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
						rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), rs.getString("OTP"),
						rs.getTimestamp("hetHanOTP"));
				return nv;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public int updateOTP(String email, String OTP, Timestamp hetHanOTP) {

		try {

			String query = "update NhanVien set OTP = ?, hetHanOTP = ? where email = ?";
			ps = con.prepareCall(query);
			ps.setString(1, OTP);
			ps.setTimestamp(2, hetHanOTP);
			ps.setString(3, email);

			return ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
}
