package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import dao.NhanVienDao;
import dao.ThongKeDao;
import entity.NhanVien;
import service.NhanVienService;

public class NhanVienServiceImpl implements NhanVienService {
	NhanVienDao nhanVienDao = new NhanVienDao();
	ThongKeDao thongKeDao = new ThongKeDao();
	public static int errorsThem = 0;
	public static int errorsSua = 0;

	/**
	 * @author dell 1 Lỗi tên 2 Lỗi sdt 3 Lỗi cccd 4
	 */
	@Override
	public int themNhanVien(NhanVien nv) throws SQLException {
		if (!(nv.getHoTenNhanVien().length() > 0 && nv.getHoTenNhanVien()
				.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊ"
						+ "ỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]*"))) {
			errorsThem = 1;
			return -1;
		} else if (!(nv.getsDT().length() > 0 && nv.getsDT().matches("^[0-9]{10}$"))) {
			errorsThem = 2;
			return -1;

		} else if (!(nv.getcCCD().length() > 0 && nv.getcCCD().matches("^[0-9]{12}$"))) {
			errorsThem = 3;
			return -1;
		} else if (!(nv.getEmail().length() > 0 && nv.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$"))) {
			errorsThem = 4;
			return -1;
		} else if (!(nv.getDiaChi().length() > 0 && nv.getDiaChi()
				.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊ"
						+ "ỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ,\\w\\s]*"))) {
			errorsThem = 5;
			return -1;
		} else {
			nhanVienDao.themNhanvien(nv);
			return 1;

		}

	}

	@Override
	public int xoaNhanVien(String maNV) throws SQLException {
		// TODO Auto-generated method stub
		return nhanVienDao.xoaNhanVien(maNV);
	}

	@Override
	public List<NhanVien> getDSNhanVien() throws SQLException {
		// TODO Auto-generated method stub
		return nhanVienDao.getDSNhanVien();
	}

	@Override
	public List<NhanVien> timDSNhanVienTheoTen(String tenNV) throws SQLException {
		// TODO Auto-generated method stub
		return nhanVienDao.timDSNhanVienTheoTen(tenNV);
	}

	@Override
	public int suaNhanVien(NhanVien nv) throws SQLException {
		// TODO Auto-generated method stub
		if (!(nv.getHoTenNhanVien().length() > 0 && nv.getHoTenNhanVien()
				.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊ"
						+ "ỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]*"))) {
			errorsSua = 1;
			return -1;
		} else if (!(nv.getsDT().length() > 0 && nv.getsDT().matches("^[0-9]{10}$"))) {
			errorsSua = 2;
			return -1;

		} else if (!(nv.getcCCD().length() > 0 && nv.getcCCD().matches("^[0-9]{12}$"))) {
			errorsSua = 3;
			return -1;
		} else if (!(nv.getEmail().length() > 0 && nv.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$"))) {
			errorsSua = 4;
			return -1;
		} else if (!(nv.getDiaChi().length() > 0 && nv.getDiaChi()
				.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊ"
						+ "ỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ,\\w\\s]*"))) {
			errorsSua = 5;
			return -1;
		} else {
			nhanVienDao.suaNhanVien(nv);
			return 1;
		}

	}

	@Override
	public List<NhanVien> getNhanVienBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		// TODO Auto-generated method stub
		return thongKeDao.getNhanVienBanNhieuNhatTheoNgayTuChon(ngayBatDau, ngayKetThuc);
	}

	@Override
	public List<NhanVien> getDoanhThuCuaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getDoanhThuCuaNhanVien(ngayBatDau, ngayKetThuc);
	}

	@Override
	public NhanVien timNhanVienTheoMa(String maNV) throws SQLException {
		// TODO Auto-generated method stub
		return nhanVienDao.timNhanVienTheoMa(maNV);
	}

	@Override
	public List<NhanVien> timDanhSachNhanVienTheoMa(String maNV) throws SQLException {
		// TODO Auto-generated method stub
		return nhanVienDao.timDanhSachNhanVienTheoMa(maNV);
	}

	@Override
	public List<NhanVien> timNhanVienTheoSDT(String sDT) throws SQLException {
		// TODO Auto-generated method stub
		return nhanVienDao.timNhanVienTheoSDT(sDT);
	}

	@Override
	public List<NhanVien> getListNhanVienByNameAndSDT(String tenNV, String sdt) {
		// TODO Auto-generated method stub
		return nhanVienDao.getListNhanVienByNameAndSDT(tenNV, sdt);
	}

	@Override
	public NhanVien timNhanVienTheoTen(String tenNV) throws SQLException {
		// TODO Auto-generated method stub
		return nhanVienDao.timNhanVienTheoTen(tenNV);
	}

	@Override
	public NhanVien getNhanVienByEmail(String email) {
		// TODO Auto-generated method stub
		return nhanVienDao.getNhanVienByEmail(email);
	}

	@Override
	public int updateOTP(String gmail, String OTP, Timestamp hetHanOTP) {
		// TODO Auto-generated method stub
		return nhanVienDao.updateOTP(gmail, OTP, hetHanOTP);
	}

	@Override
	public List<NhanVien> thongKeDoanhThu10NVBanNhieuNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		// TODO Auto-generated method stub
		return thongKeDao.thongKeDoanhThu10NVBanNhieuNhat(ngayBatDau, ngayKetThuc);
	}
}
