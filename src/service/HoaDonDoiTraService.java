package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.HoaDonDoiTra;

public interface HoaDonDoiTraService {
	public int themHoaDonDoiTra(HoaDonDoiTra hddt) throws SQLException;

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoMa(String maHDDT);

	public List<HoaDonDoiTra> getDSHoaDonDoiTra() throws SQLException;

	public int editTienKhachTra(HoaDonDoiTra hddt);

	public List<HoaDonDoiTra> getMaHoaDonDoiTraByMaHDCu(String maHDCu);

	public int editTienPhaiTru(HoaDonDoiTra hddt);
	
//	MINH HUYEN
	public List<HoaDonDoiTra> getToanBoDSHoaDonDoiTra() throws SQLException ;

	// Tim hoa don doi tra theo ma
	public HoaDonDoiTra timHoaDonDoiTraTheoMa(String maHoaDon);
	

// Tim hóa đơn đổi trả theo tên nhân viên
	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTen(String tenNV) throws SQLException ;
	

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoSDT(String sdt);

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTenKH(String tenKH);
}
