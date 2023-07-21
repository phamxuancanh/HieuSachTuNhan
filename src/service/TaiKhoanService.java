package service;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.TaiKhoan;

public interface TaiKhoanService {
	public int insertAccount(TaiKhoan taiKhoan);

	public ArrayList<TaiKhoan> getList();

	public int xoaTaiKhoan(String maNhanVien);

	public TaiKhoan getTaiKhoanTheoMaNV(String maNV) throws SQLException;

	public int doiMatKhau(String passMoi, String maNV);
}
