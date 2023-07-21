package service;

import java.sql.SQLException;
import java.util.List;

import entity.ChiTietHoaDonDoiTra;

public interface ChiTietHoaDonDoiTraService {
	public List<ChiTietHoaDonDoiTra> getCTHoaDonDoiTraTheoMaHoaDonDoiTra(String maHD);

	public int themChiTietHoaDonDoiTra(ChiTietHoaDonDoiTra cthddt) throws SQLException;
}
