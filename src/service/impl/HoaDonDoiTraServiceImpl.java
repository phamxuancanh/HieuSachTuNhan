package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.HoaDonDoiTraDao;
import entity.HoaDonDoiTra;
import service.HoaDonDoiTraService;

public class HoaDonDoiTraServiceImpl implements HoaDonDoiTraService {
	HoaDonDoiTraDao hoaDonDoiTraDao = new HoaDonDoiTraDao();

	@Override
	public int themHoaDonDoiTra(HoaDonDoiTra hddt) throws SQLException {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.themHoaDonDoiTra(hddt);
	}

	@Override
	public List<HoaDonDoiTra> getHoaDonDoiTraTheoMa(String maHDDT) {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.getHoaDonDoiTraTheoMa(maHDDT);
	}

	@Override
	public List<HoaDonDoiTra> getDSHoaDonDoiTra() throws SQLException {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.getDSHoaDonDoiTra();
	}

	@Override
	public int editTienKhachTra(HoaDonDoiTra hddt) {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.editTienKhachTra(hddt);
	}

	@Override
	public List<HoaDonDoiTra> getMaHoaDonDoiTraByMaHDCu(String maHDCu) {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.getMaHoaDonDoiTraByMaHDCu(maHDCu);
	}

	@Override
	public int editTienPhaiTru(HoaDonDoiTra hddt) {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.editTienPhaiTru(hddt);
	}

	@Override
	public List<HoaDonDoiTra> getToanBoDSHoaDonDoiTra() throws SQLException {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.getToanBoDSHoaDonDoiTra();
	}

	@Override
	public HoaDonDoiTra timHoaDonDoiTraTheoMa(String maHoaDon) {
		// TODO Auto-generated method stub
		try {
			return hoaDonDoiTraDao.timHoaDonDoiTraTheoMa(maHoaDon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTen(String tenNV) throws SQLException {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.getHoaDonDoiTraTheoTen(tenNV);
	}

	@Override
	public List<HoaDonDoiTra> getHoaDonDoiTraTheoSDT(String sdt) {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.getHoaDonDoiTraTheoSDT(sdt);
	}

	@Override
	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTenKH(String tenKH) {
		// TODO Auto-generated method stub
		return hoaDonDoiTraDao.getHoaDonDoiTraTheoTenKH(tenKH);
	}

}
