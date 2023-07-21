package service.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.KhachHangDao;
import dao.ThongKeDao;
import entity.KhachHang;
import service.KhachHangService;

public class KhachHangServiceImpl implements KhachHangService {
	public static int errors = 0;
	public static int errorsThem = 0;
	ThongKeDao thongKeDao = new ThongKeDao();
	KhachHangDao khachhang_dao = new KhachHangDao();

	public KhachHangServiceImpl() {
		khachhang_dao = new KhachHangDao();
	}

	@Override
	public List<KhachHang> getDSKhachHang() throws SQLException {
		// TODO Auto-generated method stub
		return khachhang_dao.getDSKhachHang();
	}

	public int themKhachHang(KhachHang kh) throws SQLException {
		if (!(kh.getHoTenKhachHang()
				.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊ"
						+ "ỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]*"))) {
			errors = 1;
			return -1;
		} else if (!(kh.getsDT().matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b"))) {
			errors = 2;
			return -1;
		} else {
			return khachhang_dao.themKhachHang(kh);
		}
	}

	@Override
	public KhachHang timKhachHangTheoMa(String maKH) throws SQLException {
		// TODO Auto-generated method stub

		return khachhang_dao.timKhachHangTheoMa(maKH);
	}

	@Override
	public int capNhatKhachHang(KhachHang kh) throws SQLException {
		// TODO Auto-generated method stub
		if (!(kh.getHoTenKhachHang()
				.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊ"
						+ "ỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]*"))) {
			errorsThem = 1;
			return -1;
		} else if (!(kh.getsDT().matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b"))) {
			errorsThem = 2;
			return -1;
		} else {
			return khachhang_dao.capNhatKhachHang(kh);
		}

	}

	@Override
	public List<KhachHang> getKhachHangMuaNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		// TODO Auto-generated method stub
		return thongKeDao.getKhachHangMuaNhieuNhatTheoNgayTuChon(ngayBatDau, ngayKetThuc);
	}

	@Override
	public double getTongTienCuaKhachHangTop1(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getTongTienCuaKhachHangTop1(ngayBatDau, ngayKetThuc);
	}

	@Override
	public List<KhachHang> getTop10KHThanThiet(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		// TODO Auto-generated method stub
		return thongKeDao.getTop10KHThanThiet(ngayBatDau, ngayKetThuc);
	}

	@Override
	public int getSoLuongHoaDonCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH)
			throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongHoaDonCuaKhachHangTheoMa(ngayBatDau, ngayKetThuc, maKH);
	}

	@Override
	public double getTongTienCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH)
			throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getTongTienCuaKhachHangTheoMa(ngayBatDau, ngayKetThuc, maKH);
	}

	@Override
	public ArrayList<KhachHang> timKhachHangTheoSDT(String sDT) throws SQLException {
		// TODO Auto-generated method stub

		return khachhang_dao.timKhachHangTheoSDT(sDT);
	}

	@Override
	public ArrayList<KhachHang> timKhachHangTheoTen(String tenKH) throws SQLException {
		// TODO Auto-generated method stub
		return khachhang_dao.timKhachHangTheoTen(tenKH);
	}

	@Override
	public ArrayList<KhachHang> getListKhachHangByNameAndSDT(String tenNhanVien, String sDT) {
		// TODO Auto-generated method stub
		return khachhang_dao.getListKhachHangByNameAndSDT(tenNhanVien, sDT);
	}

	@Override
	public KhachHang timKhachHangBangSDT(String sdt) throws SQLException {
		// TODO Auto-generated method stub
		return khachhang_dao.timKhachHangBangSDT(sdt);
	}

}
