package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.TheLoaiSach;
import entity.TheLoaiVanPhongPham;

public class TheLoaiDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public TheLoaiDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<TheLoaiSach> getListTheLoaiSach() throws Exception {
		ArrayList<TheLoaiSach> list = new ArrayList<>();
		query = "SELECT maTheLoai, tenTheLoai\r\n" + "FROM     TheLoaiSach";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			TheLoaiSach s = new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai"));
			list.add(s);
		}
		return list;
	}

	public ArrayList<TheLoaiVanPhongPham> getListTheLoaiVanPhongPham() throws Exception {
		ArrayList<TheLoaiVanPhongPham> list = new ArrayList<>();
		query = "SELECT maLoaiVanPhongPham, tenTheLoai\r\n" + "FROM     LoaiVanPhongPham";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			TheLoaiVanPhongPham s = new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"),
					rs.getString("tenTheLoai"));
			list.add(s);
		}
		return list;
	}

	public boolean themTheLoaiSach(TheLoaiSach t) throws Exception {
		query = "INSERT [dbo].[TheLoaiSach] ([maTheLoai], [tenTheLoai]) VALUES (?, N'" + t.getTenLoai() + "')";
		ps = con.prepareStatement(query);
		ps.setString(1, t.getMaLoai());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public boolean themTheLoaiVanPhongPham(TheLoaiVanPhongPham t) throws Exception {
		query = "INSERT [dbo].[LoaiVanPhongPham] ([maLoaiVanPhongPham], [tenTheLoai]) VALUES ( ? , N'" + t.getTenLoai()
				+ "')";
		ps = con.prepareStatement(query);
		ps.setString(1, t.getMaLoai());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public TheLoaiSach timTheLoaiSach(String TheLoai) throws SQLException {
		query = "select * from TheLoaiSach where tenTheLoai = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, TheLoai);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai"));
		}
		return null;
	}

	public TheLoaiVanPhongPham timTheLoaiVanPhongPham(String TheLoai) throws SQLException {
		query = "select * from LoaiVanPhongPham where tenTheLoai = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, TheLoai);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai"));
		}
		return null;
	}

	public List<TheLoaiSach> getSachTheoTheLoai(String maTL) {
		List<TheLoaiSach> dsTLS = new ArrayList<>();
		try {
			String query = "Select * from TheLoaiSach where maTheLoai=?";
			ps = con.prepareStatement(query);
			ps.setString(1, maTL);
			rs = ps.executeQuery();
			while (rs.next()) {
				String maTheLoai = rs.getString("maTheLoai");
				String tenTheLoai = rs.getString("tenTheLoai");
				TheLoaiSach tls = new TheLoaiSach(maTheLoai, tenTheLoai);
				dsTLS.add(tls);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsTLS;
	}
	public boolean kiemTraTonTaiTheLoaiVPP(String ten) throws SQLException {
		query = "select * from LoaiVanPhongPham where tenTheLoai = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
	public boolean kiemTraTonTaiTheLoaiSach(String ten) throws SQLException {
		query = "select * from TheLoaiSach where tenTheLoai = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}

}
