package gui;

import java.util.ArrayList;
import java.util.HashMap;

import entity.SanPham;

public class ShareData {
	private static HashMap<String, ArrayList<SanPham>> listHoaDonCho;
	private static ArrayList<SanPham> listSanPhamThanhToanTiep;
	private static boolean thanhToan ;
	private static boolean xoa ;
	private static String sdtThanhToan;

	public ShareData(HashMap<String, ArrayList<SanPham>> listHoaDonCho) {
		ShareData.listHoaDonCho = listHoaDonCho;
		thanhToan = false;
		xoa = false;
	}
	public static HashMap<String, ArrayList<SanPham>> getListHoaDonCho() {
		return listHoaDonCho;
	}
	public static void setListHoaDonCho(HashMap<String, ArrayList<SanPham>> listHoaDonCho) {
		ShareData.listHoaDonCho = listHoaDonCho;
	}
	public static boolean isThanhToan() {
		return thanhToan;
	}
	public static void setThanhToan(boolean thanhToan) {
		ShareData.thanhToan = thanhToan;
	}
	public static boolean isXoa() {
		return xoa;
	}
	public static void setXoa(boolean xoa) {
		ShareData.xoa = xoa;
	}
	public static String getSdtThanhToan() {
		return sdtThanhToan;
	}
	public static void setSdtThanhToan(String sdtThanhToan) {
		ShareData.sdtThanhToan = sdtThanhToan;
	}
	public static ArrayList<SanPham> getListSanPhamThanhToanTiep() {
		return listSanPhamThanhToanTiep;
	}
	public static void setListSanPhamThanhToanTiep(ArrayList<SanPham> listSanPhamThanhToanTiep) {
		ShareData.listSanPhamThanhToanTiep = listSanPhamThanhToanTiep;
	}
	
}
