package service.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.SanPhamDao;
import dao.ThongKeDao;
import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;
import service.SanPhamService;

public class SanPhamServiceImpl implements SanPhamService {
	SanPhamDao sanPhamDao = new SanPhamDao();
	ThongKeDao thongKeDao = new ThongKeDao();

	@Override
	public ArrayList<Sach> getListSach(String maSach, String tenSP, String maTheLoai, Long giaTu, Long giaDen,
			String maTacGia, String maNXB, String maNCC, boolean hetHang) throws Exception {
		// TODO Auto-generated method stub
		return sanPhamDao.getListSach(maSach, tenSP, maTheLoai, giaTu, giaDen, maTacGia, maNXB, maNCC, hetHang);
	}

	@Override
	public ArrayList<VanPhongPham> getListVanPhongPham(String maVPP, String tenVPP, String theLoaiVPP, Long giaTu,
			Long giaDen, String maChatLieu, String maXuatXu, String maNCC, boolean hetHang) throws Exception {
		// TODO Auto-generated method stub
		return sanPhamDao.getListVanPhongPham(maVPP, tenVPP, theLoaiVPP, giaTu, giaDen, maChatLieu, maXuatXu, maNCC,
				hetHang);
	}

	@Override
	public boolean themSanPham(SanPham sanPham) throws Exception {
		boolean exist = true;
		if(sanPham instanceof Sach) {
			Sach sach = (Sach) sanPham;
			exist = sanPhamDao.kiemTraTonTaiSanPham(sach.getTenSach());
		}else {
			VanPhongPham vanPhongPham = (VanPhongPham) sanPham;
			exist = sanPhamDao.kiemTraTonTaiSanPham(vanPhongPham.getTenVanPhongPham());
		}
		if(exist == true) {
			return false;
		}
		return sanPhamDao.themSanPham(sanPham);
	}

	@Override
	public boolean capNhatSanPham(String maSP, SanPham temp) throws Exception {
		// TODO Auto-generated method stub
		return sanPhamDao.capNhatSanPham(maSP, temp);
	}

	@Override
	public String getMaSPMax() throws SQLException {
		// TODO Auto-generated method stub
		return sanPhamDao.getMaSPMax();
	}

	@Override
	public int getSoLuongSachLoi() throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongSachLoi();
	}

	@Override
	public int getSoLuongVPPTon() throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongVPPTon();
	}

	@Override
	public int getSoLuongSachTon() throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongSachTon();
	}

	@Override
	public List<SanPham> getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		// TODO Auto-generated method stub
		return thongKeDao.getSanPhamBanNhieuNhatTheoNgayTuChon(ngayBatDau, ngayKetThuc);
	}

	@Override
	public SanPham timSanPhamTheoMa(String maSP) throws SQLException {
		// TODO Auto-generated method stub
		return sanPhamDao.timSanPhamTheoMa(maSP);
	}

	@Override
	public int getSoLuongBanCuaSanPhamChayNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongBanCuaSanPhamChayNhat(ngayBatDau, ngayKetThuc);
	}

	@Override
	public Sach getSachTheoMaSP(String maSP) throws SQLException {
		// TODO Auto-generated method stub
		return sanPhamDao.getSachTheoMaSP(maSP);
	}

	@Override
	public VanPhongPham getVPPTheoMaSP(String maSP) throws SQLException {
		// TODO Auto-generated method stub
		return sanPhamDao.getVPPTheoMaSP(maSP);
	}

	@Override
	public Sach timSanPhamTheoMaSach(String maSach) throws Exception {
		// TODO Auto-generated method stub
		return sanPhamDao.timSanPhamTheoMaSach(maSach);
	}

	@Override
	public VanPhongPham timSanPhamTheoMaVPP(String maVPP) throws Exception {
		// TODO Auto-generated method stub
		return sanPhamDao.timSanPhamTheoMaVPP(maVPP);
	}

	@Override
	public boolean xoaSanPham(String maSP) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Sach> getAllSach() throws Exception {
		// TODO Auto-generated method stub
		return sanPhamDao.getAllSach();
	}

	@Override
	public Sach getSachTheoTen(String ten) {
		// TODO Auto-generated method stub
		return sanPhamDao.getSachTheoTen(ten);
	}

	@Override
	public int capNhatSoLuongSanPham(SanPham sanPham) {
		// TODO Auto-generated method stub
		return sanPhamDao.capNhatSoLuongSanPham(sanPham);
	}

	@Override
	public List<VanPhongPham> getAllVPP() {
		// TODO Auto-generated method stub
		return sanPhamDao.getAllVPP();
	}

	@Override
	public VanPhongPham getVPPTheoTen(String ten) {
		// TODO Auto-generated method stub
		return sanPhamDao.getVPPTheoTen(ten);
	}

	@Override
	public SanPham getSanPhamTheoMa(String masp) throws SQLException {
		// TODO Auto-generated method stub
		return sanPhamDao.getSanPhamTheoMa(masp);
	}

	@Override
	public SanPham timSanPhamTheoMa1(String maSP) {
		// TODO Auto-generated method stub
		try {
			return sanPhamDao.timSanPhamTheoMa1(maSP);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
