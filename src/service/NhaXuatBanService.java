package service;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.NhaXuatBan;

public interface NhaXuatBanService {
	public ArrayList<NhaXuatBan> getListNhaXuatBan() throws Exception;

	public boolean themNhaXuatBan(NhaXuatBan t) throws Exception;

	public NhaXuatBan timNhaXuatBan(String NXB) throws SQLException;
}
