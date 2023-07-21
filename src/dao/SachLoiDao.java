package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.NhanVien;
import entity.Sach;
import entity.SachLoi;

public class SachLoiDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public SachLoiDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public int themSachLoi(SachLoi sl) throws SQLException {
		String insert = "Insert into SachLoi values (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(insert);
		stmt.setString(1, sl.getSach().getMaSanPham());
		stmt.setString(2, sl.getLoiSanPham());
		stmt.setInt(3, sl.getSoLuong());
		stmt.executeUpdate();
		return 1;
	}

	public int capNhatSoLuong(SachLoi sl) {
		String sql = "update SachLoi set soLuongLoi = ? where maSanPham =? and loiSanPham =?";
		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, sl.getSoLuong());
			ps.setString(2, sl.getSach().getMaSanPham());
			ps.setString(3, sl.getLoiSanPham());

			// rs = ps.executeQuery();

			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public List<SachLoi> getAllSachLoi() throws SQLException {
		List<SachLoi> dssl = new ArrayList<>();
		String query = "Select * from SachLoi";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			SachLoi sl = new SachLoi(new Sach(rs.getString("maSanPham")), rs.getString("loiSanPham"),
					rs.getInt("soLuongLoi"));
			dssl.add(sl);
		}

		return dssl;
	}

	public void xoaSachLoi(String maSach, String loi) {
		String query = "delete from SachLoi" + " where maSanPham = '" + maSach + "' and loiSanPham = N'" + loi + "'";
		try {
			System.out.println(query);
			ps = con.prepareStatement(query);
		    ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
