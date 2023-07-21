package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.ChiTietHoaDonDoiTraDao;
import entity.ChiTietHoaDonDoiTra;
import service.ChiTietHoaDonDoiTraService;

public class ChiTietHoaDonDoiTraServiceImpl implements ChiTietHoaDonDoiTraService {
	ChiTietHoaDonDoiTraDao chiTietHoaDonDoiTraDao = new ChiTietHoaDonDoiTraDao();

	@Override
	public List<ChiTietHoaDonDoiTra> getCTHoaDonDoiTraTheoMaHoaDonDoiTra(String maHD) {
		// TODO Auto-generated method stub
		return chiTietHoaDonDoiTraDao.getCTHoaDonDoiTraTheoMaHoaDonDoiTra(maHD);
	}

	@Override
	public int themChiTietHoaDonDoiTra(ChiTietHoaDonDoiTra cthddt) throws SQLException {
		// TODO Auto-generated method stub
		return chiTietHoaDonDoiTraDao.themChiTietHoaDonDoiTra(cthddt);
	}

}
