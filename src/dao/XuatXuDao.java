package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

import entity.XuatXu;

public class XuatXuDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public XuatXuDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<XuatXu> getListXuatXu() throws Exception {
		ArrayList<XuatXu> list = new ArrayList<>();
		query = "SELECT maXuatXu, tenXuatXu\r\n" + "FROM     XuatXu";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			XuatXu xuatXu = new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu"));
			list.add(xuatXu);
		}
		return list;
	}

	public boolean themXuatXu(XuatXu x) throws Exception {
		query = "INSERT [dbo].[XuatXu] ([maXuatXu], [tenXuatXu]) VALUES ( ? , N'" + x.getTenXuatXu() + "')";
		ps = con.prepareStatement(query);
		ps.setString(1, x.getMaXuatXu());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public List<XuatXu> getXuatXu(String maXuatXu) {
		List<XuatXu> dsXX = new ArrayList<XuatXu>();
		try {
			String query = "Select * from XuatXu where maXuatXu = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, maXuatXu);
			rs = ps.executeQuery();
			while (rs.next()) {
				String maxx = rs.getString("maXuatXu");
				String tenxx = rs.getString("tenXuatXu");
				XuatXu xx = new XuatXu(maxx, tenxx);
				dsXX.add(xx);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsXX;
	}

	public boolean xoaXuatXu(String maXuatXu) {

		return false;
	}

	public XuatXu timXuatXu(String XuatXu) throws SQLException {
		query = "select * from XuatXu where tenXuatXu = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, XuatXu);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu"));
		}
		return null;
	}
	public boolean kiemTraTonTaiXuatXu(String ten) throws SQLException {
		query = "select * from XuatXu where tenXuatXu = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
