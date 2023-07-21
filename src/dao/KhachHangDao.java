package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.KhachHang;
import entity.NhanVien;

public class KhachHangDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private ArrayList<KhachHang> khachhang = new ArrayList<KhachHang>();

	public KhachHangDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public int themKhachHang(KhachHang kh) throws SQLException {
		String insert = "Insert into KhachHang values (?, ?, ?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(insert);
		stmt.setString(1, kh.getMaKhachHang());
		stmt.setString(2, kh.getHoTenKhachHang());
		stmt.setBoolean(3, kh.isGioiTinh());
		stmt.setString(4, kh.getsDT());
		stmt.setString(5, kh.getDiaChi());
		stmt.executeUpdate();
		return 1;
	}

	public List<KhachHang> getDSKhachHang() throws SQLException {
		List<KhachHang> dskh = new ArrayList<>();
		String query = "Select * from KhachHang";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			KhachHang kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"),
					rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
			dskh.add(kh);
		}

		return dskh;
	}

	public KhachHang timKhachHangTheoMa(String maKH) throws SQLException {
		KhachHang kh = new KhachHang();
		String query = "Select * from KhachHang where maKhachHang=?";
		ps = con.prepareStatement(query);
		ps.setString(1, maKH);
		rs = ps.executeQuery();
		while (rs.next()) {
			kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"), rs.getString("sdt"),
					rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
			return kh;
		}
		return null;
	}

	public ArrayList<KhachHang> timKhachHangTheoTen(String tenKH) throws SQLException {
		KhachHang kh = new KhachHang();
		khachhang = new ArrayList<KhachHang>();
		System.out.println(tenKH);
		String query = "Select * from KhachHang where hotenKhachHang LIKE CONCAT('%', ?, '%')";
		ps = con.prepareStatement(query);
		ps.setString(1, tenKH);
		rs = ps.executeQuery();
		while (rs.next()) {
			kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"), rs.getString("sdt"),
					rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
			System.out.println(kh);
			khachhang.add(kh);
			// return khachhang;
		}
		return khachhang;
	}

	public int capNhatKhachHang(KhachHang kh) {
		String sql = "UPDATE KhachHang SET hotenKhachHang = ?, gioiTinh = ?, sdt = ?, diaChi = ? WHERE maKhachHang = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, kh.getHoTenKhachHang());
			ps.setBoolean(2, kh.isGioiTinh());
			ps.setString(3, kh.getsDT());
			ps.setString(4, kh.getDiaChi());
			ps.setString(5, kh.getMaKhachHang());

			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	// ---------

	public ArrayList<KhachHang> timKhachHangTheoSDT(String sDT) throws SQLException {
		KhachHang kh = new KhachHang();
		String query = "Select * from KhachHang where sdt LIKE CONCAT('%', ?, '%')";
		PreparedStatement stmt = con.prepareCall(query);
		stmt.setString(1, sDT);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"), rs.getString("sdt"),
					rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
			khachhang.add(kh);
		}
		return khachhang;
	}

	public ArrayList<KhachHang> getListKhachHangByNameAndSDT(String tenKH, String sdt) {
		KhachHang kh = new KhachHang();
		try {
			String sql = "select * from KhachHang where hotenKhachHang LIKE CONCAT('%', ?, '%') or sdt LIKE CONCAT('%', ?, '%')";
			PreparedStatement stmt = con.prepareCall(sql);
			stmt.setString(1, tenKH);
			stmt.setString(2, sdt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"), rs.getString("sdt"),
						rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
				khachhang.add(kh);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return khachhang;
	}

	public KhachHang timKhachHangBangSDT(String sdt) throws SQLException {
		KhachHang kh = new KhachHang();
		String query = "Select * from KhachHang where sdt=?";
		ps = con.prepareStatement(query);
		ps.setString(1, sdt);
		rs = ps.executeQuery();
		while (rs.next()) {
			kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"), rs.getString("sdt"),
					rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
			return kh;
		}
		return null;
	}

}
