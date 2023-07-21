package service.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.HoaDonDao;
import dao.SanPhamDao;
import dao.ThongKeDao;
import entity.HoaDon;
import service.HoaDonService;

public class HoaDonServiceImpl implements HoaDonService {
	HoaDonDao hoaDonDao = new HoaDonDao();
	ThongKeDao thongKeDao = new ThongKeDao();

	@Override
	public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongHoaDon(ngayBatDau, ngayKetThuc);
	}

	@Override
	public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getDoanhThu(ngayBatDau, ngayKetThuc);
	}

	@Override
	public double getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV)
			throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getDoanhThuTheoMaNhanVien(ngayBatDau, ngayKetThuc, maNV);
	}

	@Override
	public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongHoaDonTheoMaNV(ngayBatDau, ngayKetThuc, maNV);
	}

	@Override
	public int setNullChoMaNhanVienTrongHoaDon(String maNV) {
		// TODO Auto-generated method stub
		return hoaDonDao.setNullChoMaNhanVienTrongHoaDon(maNV);
	}

	@Override
	public List<HoaDon> getHoaDonTheoMa(String maHD) {
		// TODO Auto-generated method stub
		return hoaDonDao.getHoaDonTheoMa(maHD);
	}

	@Override
	public List<HoaDon> getDSHoaDon() throws SQLException {
		// TODO Auto-generated method stub
		return hoaDonDao.getDSHoaDon();
	}

	@Override
	public int doiThongTinHoaDonSauKhiXoa(String tenNV) {
		// TODO Auto-generated method stub
		return hoaDonDao.doiThongTinHoaDonSauKhiXoa(tenNV);
	}

	@Override
	public int themHoaDon(HoaDon hd) throws SQLException {
		// TODO Auto-generated method stub
		return hoaDonDao.themHoaDon(hd);
	}

	@Override
	public List<HoaDon> getHoaDonThuong() {
		// TODO Auto-generated method stub
		return hoaDonDao.getHoaDonThuong();
	}

	@Override
	public HoaDon timHoaDonTheoMa(String maHoaDon) {
		// TODO Auto-generated method stub
		try {
			return hoaDonDao.timHoaDonTheoMa(maHoaDon);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HoaDon> getHoaDonTheoTen(String tenNV) {
		// TODO Auto-generated method stub
		try {
			return hoaDonDao.getHoaDonTheoTen(tenNV);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HoaDon> timHoaDonTheoSDT(String sdt) {
		// TODO Auto-generated method stub
		try {
			return hoaDonDao.timHoaDonTheoSDT(sdt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HoaDon> timHoaDonTheoTenKH(String ten) {
		// TODO Auto-generated method stub
		try {
			return hoaDonDao.timHoaDonTheoTenKH(ten);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
