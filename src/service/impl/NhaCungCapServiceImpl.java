package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.NhaCungCapDao;
import entity.NhaCungCap;
import service.NhaCungCapService;

public class NhaCungCapServiceImpl implements NhaCungCapService {
	NhaCungCapDao nhaCungCapDao = new NhaCungCapDao();

	@Override
	public ArrayList<NhaCungCap> getListNhaCungCap(String loaiSanPham) throws Exception {
		// TODO Auto-generated method stub
		return nhaCungCapDao.getListNhaCungCap(loaiSanPham);
	}

	@Override
	public boolean themNhaCungCap(NhaCungCap t) throws Exception {
		if(nhaCungCapDao.kiemTraTonTaiNCC(t.getTenNCC()))
			return false;
		return nhaCungCapDao.themNhaCungCap(t);
	}

	@Override
	public ArrayList<NhaCungCap> getAllListNhaCungCap() throws Exception {
		// TODO Auto-generated method stub
		return nhaCungCapDao.getAllListNhaCungCap();
	}

	@Override
	public NhaCungCap timNhaCungCap(String NCC) throws SQLException {
		// TODO Auto-generated method stub
		return nhaCungCapDao.timNhaCungCap(NCC);
	}

}
