package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.TacGia;

public interface TacGiaService {
	public ArrayList<TacGia> getListTacGia() throws Exception;

	public boolean themTacGia(TacGia t) throws Exception;

	public boolean xoaTacGia(String maTacGia);

	public List<TacGia> getTacGia(String maTacGia);

	public TacGia timTacGia(String TacGia) throws SQLException;
}
