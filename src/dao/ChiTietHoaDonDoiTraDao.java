package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonDoiTra;
import entity.HoaDon;
import entity.HoaDonDoiTra;
import entity.KhachHang;
import entity.NhanVien;

public class ChiTietHoaDonDoiTraDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;
	private HoaDonDoiTraDao hoaDonDoiTraDao = new HoaDonDoiTraDao();
	private SanPhamDao sanPhamDao = new SanPhamDao();

	public ChiTietHoaDonDoiTraDao() {
		// TODO Auto-generated constructor stub
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public List<ChiTietHoaDonDoiTra> getCTHoaDonDoiTraTheoMaHoaDonDoiTra(String maHD) {
		List<ChiTietHoaDonDoiTra> dscthddt = new ArrayList<>();
		// System.out.println(maNV);
		try {
			String query = "Select * from ChiTietHoaDonDoiTra where maHoaDonDoiTra =?";
			ps = con.prepareStatement(query);
			ps.setString(1, maHD);
			rs = ps.executeQuery();
			while (rs.next()) {
				ChiTietHoaDonDoiTra cthddt = new ChiTietHoaDonDoiTra(
						hoaDonDoiTraDao.getHoaDonDoiTraTheoMa(rs.getString(1)).get(0),
						sanPhamDao.timSanPhamTheoMa(rs.getString(2)), rs.getInt(3), rs.getLong(4));
				dscthddt.add(cthddt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dscthddt;
	}

	public int themChiTietHoaDonDoiTra(ChiTietHoaDonDoiTra cthddt) throws SQLException {
		String insert = "Insert into ChiTietHoaDonDoiTra values (?, ?, ?, ?)";
		PreparedStatement stmt = con.prepareStatement(insert);
		stmt.setString(1, cthddt.getHoaDonDoiTra().getMaHoaDonDoiTra());
		stmt.setString(2, cthddt.getSanPham().getMaSanPham());
		stmt.setInt(3, cthddt.getSoLuong());
		stmt.setLong(4, cthddt.getDonGia());
		stmt.executeUpdate();
		return 1;
	}

}
