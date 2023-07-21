package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.MauSacDao;
import entity.MauSac;
import service.MauSacService;

public class MauSacServiceImpl implements MauSacService {
	MauSacDao mauSacDao = new MauSacDao();

	@Override
	public ArrayList<MauSac> getListMauSac() throws Exception {
		// TODO Auto-generated method stub
		return mauSacDao.getListMauSac();
	}

	@Override
	public boolean themMauSac(MauSac mauSac) throws Exception {
		if(mauSacDao.kiemTraTonTaiMauSac(mauSac.getTenMau()))
			return false;
		return mauSacDao.themMauSac(mauSac);
	}

	@Override
	public MauSac timMauSac(String Mau) throws SQLException {
		// TODO Auto-generated method stub
		return mauSacDao.timMauSac(Mau);
	}

}
