package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entity.NhaXuatBan;

public class NhaXuatBanDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public NhaXuatBanDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<NhaXuatBan> getListNhaXuatBan() throws Exception {
		ArrayList<NhaXuatBan> list = new ArrayList<>();
		query = "SELECT maNXB, tenNXB\r\n" + "FROM     NhaXuatBan";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB"));
			list.add(nhaXuatBan);
		}
		return list;
	}

	public boolean themNhaXuatBan(NhaXuatBan t) throws Exception {
		query = "INSERT [dbo].[NhaXuatBan] ([maNXB], [tenNXB]) VALUES ( ? , N'" + t.getTenNXB() + "')";
		ps = con.prepareStatement(query);
		ps.setString(1, t.getMaNXB());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public NhaXuatBan timNhaXuatBan(String NXB) throws SQLException {
		query = "select * from NhaXuatBan where tenNXB = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, NXB);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB"));
		}
		return null;
	}
	public boolean kiemTraTonTaiNXB(String ten) throws SQLException {
		query = "select * from NhaXuatBan where tenNXB = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
