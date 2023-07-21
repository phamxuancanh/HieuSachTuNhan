package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.XuatXuDao;
import entity.XuatXu;
import service.XuatXuService;

public class XuatXuServiceImpl implements XuatXuService {
	XuatXuDao xuatXuDao = new XuatXuDao();

	@Override
	public ArrayList<XuatXu> getListXuatXu() throws Exception {
		// TODO Auto-generated method stub
		return xuatXuDao.getListXuatXu();
	}

	@Override
	public boolean themXuatXu(XuatXu x) throws Exception {
		if(xuatXuDao.kiemTraTonTaiXuatXu(x.getTenXuatXu()))
			return false;
		return xuatXuDao.themXuatXu(x);
	}

	@Override
	public boolean xoaXuatXu(String maXuatXu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<XuatXu> getXuatXu(String maXuatXu) {
		// TODO Auto-generated method stub
		return xuatXuDao.getXuatXu(maXuatXu);
	}

	@Override
	public XuatXu timXuatXu(String XuatXu) throws SQLException {
		// TODO Auto-generated method stub
		return xuatXuDao.timXuatXu(XuatXu);
	}

}
