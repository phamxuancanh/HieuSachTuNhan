package service;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.NhaCungCap;

public interface NhaCungCapService {
	public ArrayList<NhaCungCap> getListNhaCungCap(String loaiSanPham) throws Exception;

	public ArrayList<NhaCungCap> getAllListNhaCungCap() throws Exception;

	public boolean themNhaCungCap(NhaCungCap t) throws Exception;

	public NhaCungCap timNhaCungCap(String NCC) throws SQLException;
}
