package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entity.NhaCungCap;

public class NhaCungCapDao {

	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public NhaCungCapDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<NhaCungCap> getListNhaCungCap(String loaiSanPham) throws Exception {
		ArrayList<NhaCungCap> list = new ArrayList<>();
		query = "SELECT distinct NhaCungCap.maNCC, NhaCungCap.tenNCC\r\n"
				+ "FROM NhaCungCap INNER JOIN SanPham ON NhaCungCap.maNCC = SanPham.maNCC\r\n"
				+ "where SanPham.loaiSanPham like ?";
		ps = con.prepareStatement(query);
		ps.setString(1, loaiSanPham);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhaCungCap nhaCungCap = new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC"));
			list.add(nhaCungCap);
		}
		return list;
	}

	public boolean themNhaCungCap(NhaCungCap t) throws Exception {
		query = "INSERT [dbo].[NhaCungCap] ([maNCC], [tenNCC], [diaChi], [email], [sdt]) VALUES (?, ?, ?, ?, ?)";
		ps = con.prepareStatement(query);
		ps.setString(1, t.getMaNCC());
		ps.setString(2, t.getTenNCC());
		ps.setString(3, t.getDiaChi());
		ps.setString(4, t.getEmail());
		ps.setString(5, t.getsDT());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public ArrayList<NhaCungCap> getAllListNhaCungCap() throws Exception {
		ArrayList<NhaCungCap> list = new ArrayList<>();
		query = "select maNCC, tenNCC, diaChi, email, sdt from NhaCungCap \r\n";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhaCungCap nhaCungCap = new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC"),
					rs.getString("diaChi"), rs.getString("email"), rs.getString("sdt"));
			list.add(nhaCungCap);
		}
		return list;
	}

	public NhaCungCap timNhaCungCap(String NCC) throws SQLException {
		query = "select * from NhaCungCap where tenNCC = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, NCC);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC"));
		}
		return null;
	}
	public boolean kiemTraTonTaiNCC(String ten) throws SQLException {
		query = "select * from NhaCungCap where tenNCC = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
