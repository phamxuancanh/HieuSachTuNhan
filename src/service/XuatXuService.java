package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.XuatXu;

public interface XuatXuService {
	public ArrayList<XuatXu> getListXuatXu() throws Exception;

	public boolean themXuatXu(XuatXu x) throws Exception;

	public boolean xoaXuatXu(String maXuatXu);

	public List<XuatXu> getXuatXu(String maXuatXu);

	public XuatXu timXuatXu(String XuatXu) throws SQLException;
}
