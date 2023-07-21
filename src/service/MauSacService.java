package service;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.MauSac;

public interface MauSacService {
	public ArrayList<MauSac> getListMauSac() throws Exception;

	public boolean themMauSac(MauSac mauSac) throws Exception;

	public MauSac timMauSac(String Mau) throws SQLException;
}
