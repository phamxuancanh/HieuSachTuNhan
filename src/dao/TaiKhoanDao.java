package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoanDao {

	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public TaiKhoanDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<TaiKhoan> getList() {
		try {
			String query = "SELECT * FROM TaiKhoan";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			NhanVienDao nhanVienDao = new NhanVienDao();
			ArrayList<TaiKhoan> listAcc = new ArrayList<>();
			while (rs.next()) {
				TaiKhoan a = new TaiKhoan(rs.getString(1), rs.getString(2),
						nhanVienDao.timNhanVienTheoMa(rs.getString(3)), rs.getBoolean(4));
				listAcc.add(a);
				// System.out.println(a);
			}
			return listAcc;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public int insertAccount(TaiKhoan taiKhoan) {
		try {
			String query = "INSERT INTO TaiKhoan VALUES(?,?,?,?)";

			ps = con.prepareStatement(query);
			ps.setString(1, taiKhoan.getTenDangNhap());
			ps.setString(2, taiKhoan.getMatKhau());
			ps.setString(3, taiKhoan.getNhanVien().getMaNhanVien());
			ps.setBoolean(4, taiKhoan.isQuyen());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}

	public int xoaTaiKhoan(String maNhanVien) {
		String query = "delete from TaiKhoan where maNhanVien = ?";
		try {
//			System.out.println(maNhanVien);
			ps = con.prepareStatement(query);
			ps.setString(1, maNhanVien);
			// rs = ps.executeQuery();
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public TaiKhoan getTaiKhoanTheoMaNV(String maNV) throws SQLException {
		TaiKhoan tk = new TaiKhoan();
		NhanVienDao nhanVienDao = new NhanVienDao();
		String query = "SELECT *FROM TaiKhoan where maNhanVien =?";
		ps = con.prepareStatement(query);
		ps.setString(1, maNV);
		rs = ps.executeQuery();
		while (rs.next()) {
			tk = new TaiKhoan(rs.getString(1), rs.getString(2), nhanVienDao.timNhanVienTheoMa(rs.getString(3)),
					rs.getBoolean(4));

		}
		return tk;
	}

	public int doiMatKhau(String passMoi, String maNV) {
		try {
			String query = "update TaiKhoan set matKhau =? where maNhanVien=?";
			ps = con.prepareStatement(query);
			ps.setString(1, passMoi);

			ps.setString(2, maNV);

			return ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}

}