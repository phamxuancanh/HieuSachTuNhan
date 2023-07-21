package service;

import java.sql.SQLException;
import java.util.List;

import entity.SachLoi;

public interface SachLoiService {
	public int themSachLoi(SachLoi sl) throws SQLException;

	public int capNhatSoLuong(SachLoi sl);

	public List<SachLoi> getAllSachLoi() throws SQLException;
	public void xoaSachLoi(String maSach, String loi);
}
