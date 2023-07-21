package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.NhaXuatBanDao;
import entity.NhaXuatBan;
import service.NhaXuatBanService;

public class NhaXuatBanServiceImpl implements NhaXuatBanService {
	NhaXuatBanDao nhaXuatBanDao = new NhaXuatBanDao();

	@Override
	public ArrayList<NhaXuatBan> getListNhaXuatBan() throws Exception {
		// TODO Auto-generated method stub
		return nhaXuatBanDao.getListNhaXuatBan();
	}

	@Override
	public boolean themNhaXuatBan(NhaXuatBan t) throws Exception {
		if(nhaXuatBanDao.kiemTraTonTaiNXB(t.getTenNXB()))
			return false;
		return nhaXuatBanDao.themNhaXuatBan(t);
	}

	@Override
	public NhaXuatBan timNhaXuatBan(String NXB) throws SQLException {
		// TODO Auto-generated method stub
		return nhaXuatBanDao.timNhaXuatBan(NXB);
	}

}
