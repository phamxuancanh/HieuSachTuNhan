package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.NhanVienDao;
import dao.TaiKhoanDao;
import entity.TaiKhoan;
import service.TaiKhoanService;

public class TaiKhoanServiceImpl implements TaiKhoanService {
	TaiKhoanDao taiKhoanDao = new TaiKhoanDao();

	public TaiKhoanServiceImpl() {
		taiKhoanDao = new TaiKhoanDao();
	}

	@Override
	public int insertAccount(TaiKhoan taiKhoan) {
		// TODO Auto-generated method stub
		return taiKhoanDao.insertAccount(taiKhoan);
	}

	@Override
	public ArrayList<TaiKhoan> getList() {
		// TODO Auto-generated method stub
		return taiKhoanDao.getList();
	}

	@Override
	public int xoaTaiKhoan(String maNhanVien) {
		// TODO Auto-generated method stub
		return taiKhoanDao.xoaTaiKhoan(maNhanVien);
	}

	@Override
	public TaiKhoan getTaiKhoanTheoMaNV(String maNV) throws SQLException {
		// TODO Auto-generated method stub
		return taiKhoanDao.getTaiKhoanTheoMaNV(maNV);
	}

	@Override
	public int doiMatKhau(String passMoi, String maNV) {
		// TODO Auto-generated method stub
		return taiKhoanDao.doiMatKhau(passMoi, maNV);
	}

}
