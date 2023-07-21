package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ChiTietHoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import service.ChiTietHoaDonService;

public class ChiTietHoaDonServiceImpl implements ChiTietHoaDonService {
	ChiTietHoaDonDao chiTietHoaDonDao = new ChiTietHoaDonDao();

	@Override
	public List<ChiTietHoaDon> getCTHoaDonTheoMaHoaDon(String maHD) {
		// TODO Auto-generated method stub
		return chiTietHoaDonDao.getCTHoaDonTheoMaHoaDon(maHD);
	}

	@Override
	public ArrayList<ChiTietHoaDon> getCTHDTheoHoaDon(HoaDon hoaDon) {
		// TODO Auto-generated method stub
		return chiTietHoaDonDao.getCTHDTheoHoaDon(hoaDon);
	}

	@Override
	public List<ChiTietHoaDon> getAllCTHD() throws SQLException {
		// TODO Auto-generated method stub
		return chiTietHoaDonDao.getAllCTHD();
	}

	@Override
	public int addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		// TODO Auto-generated method stub
		return chiTietHoaDonDao.addChiTietHoaDon(chiTietHoaDon);
	}

	@Override
	public long getTien(String maHD) {
		// TODO Auto-generated method stub
		return chiTietHoaDonDao.getTien(maHD);
	}

}
