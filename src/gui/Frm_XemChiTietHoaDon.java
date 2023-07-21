package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHoaDonDao;
import dao.HoaDonDao;
import dao.SanPhamDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhaCungCap;
import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;

import service.impl.ChatLieuServiceImpl;
import service.impl.MauSacServiceImpl;
import service.impl.NhaCungCapServiceImpl;
import service.impl.NhaXuatBanServiceImpl;
import service.impl.SanPhamServiceImpl;
import service.impl.TacGiaServiceImpl;
import service.impl.TheLoaiServiceImpl;
import service.impl.XuatXuServiceImpl;
import java.awt.SystemColor;

public class Frm_XemChiTietHoaDon extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel txtNhanVienLapHoaDon;
	private JLabel txtTenKhachHang;
	private JLabel txtNgayLapHoaDon;
	private JLabel txtMaHoaDon;
	private JLabel lblTenKH;
	private JLabel lblNgayLapHoaDon;
	private JLabel txtSoTrang;
	private JLabel lblNewLabel_9;
	private JLabel txtTenSp;
	JLabel lblDanhSachSanPham;

	private JButton btnThoat;
	SanPhamServiceImpl sanPhamServiceImpl = new SanPhamServiceImpl();
	TheLoaiServiceImpl theLoaiServiceImpl = new TheLoaiServiceImpl();
	TacGiaServiceImpl tacGiaServiceImpl = new TacGiaServiceImpl();
	NhaXuatBanServiceImpl nhaXuatBanServiceImpl = new NhaXuatBanServiceImpl();
	NhaCungCapServiceImpl nhaCungCapServiceImpl = new NhaCungCapServiceImpl();
	ChatLieuServiceImpl chatLieuServiceImpl = new ChatLieuServiceImpl();
	XuatXuServiceImpl xuatXuServiceImpl = new XuatXuServiceImpl();
	MauSacServiceImpl mauSacServiceImpl = new MauSacServiceImpl();
	private ChiTietHoaDonDao chiTietHoaDonDao;
	private List<ChiTietHoaDon> dsHoaDon;

	String loaiSanPham;
	Sach sach;
	HoaDon hoaDon;
	VanPhongPham vanPhongPham;
	String maHoaDon;
	String tenNhanVien;
	String ngayLap;
	String tenKhachHang;
	String tienKhachDua;
	String ghiChu;
	String tongTienHoaDon;
	private JLabel lblTongTienKhachDua;
	private JLabel txtTongTienHoaDon;
	private JTextArea txtAreaGhiChu;
	private JLabel txtTienKhachDua;
	JScrollPane sp_ChiTietHD;
	private JTable table_ChiTietHD;
	private DefaultTableModel tableModel_chiTietHoaDonDao;
	private SanPham sanPham;
	private SanPhamDao sanPham_dao;
	
	@SuppressWarnings("deprecation")
	public Frm_XemChiTietHoaDon(String maHoaDon, String tenNhanVien, String ngayLap, String tenKhachHang, String tienKhachDua, String tongTienHoaDon, String ghiChu){
	
		this.maHoaDon = maHoaDon;
		this.tenNhanVien = tenNhanVien;
		this.ngayLap = ngayLap;
		this.tenKhachHang = tenKhachHang;
		this.tienKhachDua = tienKhachDua;
		this.ghiChu = ghiChu;
		this.tongTienHoaDon = tongTienHoaDon;
		setTitle("Chi tiết hóa đơn");
		setResizable(false);
		setSize(1130, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblMaHD = new JLabel("Mã hóa đơn:");
		lblMaHD.setForeground(new Color(72, 61, 139));
		lblMaHD.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaHD.setBounds(10, 94, 116, 38);
		getContentPane().add(lblMaHD);

		txtNhanVienLapHoaDon = new JLabel();
		txtNhanVienLapHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNhanVienLapHoaDon.setBackground(new Color(255, 255, 255));
		txtNhanVienLapHoaDon.setBounds(236, 162, 273, 38);
		getContentPane().add(txtNhanVienLapHoaDon);

		txtTenKhachHang = new JLabel();
		txtTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKhachHang.setBackground(Color.WHITE);
		txtTenKhachHang.setBounds(236, 210, 240, 38);
		getContentPane().add(txtTenKhachHang);

		txtNgayLapHoaDon = new JLabel();
		txtNgayLapHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgayLapHoaDon.setBackground(Color.WHITE);
		txtNgayLapHoaDon.setBounds(236, 275, 240, 38);
		getContentPane().add(txtNgayLapHoaDon);

		JPanel panel = new JPanel();
		panel.setBounds(10, 38, 285, 46);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(201, 10, 766, 39);
		getContentPane().add(lblNewLabel);

		txtMaHoaDon = new JLabel(maHoaDon);
		txtMaHoaDon.setBorder(BorderFactory.createLineBorder(Color.cyan));
		txtMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaHoaDon.setBounds(236, 94, 240, 38);
		getContentPane().add(txtMaHoaDon);

		lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setForeground(new Color(72, 61, 139));
		lblTenKH.setFont(new Font("Arial", Font.BOLD, 16));
		lblTenKH.setBounds(10, 210, 170, 38);
		getContentPane().add(lblTenKH);

		lblNgayLapHoaDon = new JLabel("Ngày lập hóa đơn:");
		lblNgayLapHoaDon.setForeground(new Color(72, 61, 139));
		lblNgayLapHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		lblNgayLapHoaDon.setBounds(10, 275, 196, 38);
		getContentPane().add(lblNgayLapHoaDon);

		txtSoTrang = new JLabel();
		txtSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtSoTrang.setBounds(125, 290, 170, 23);
		getContentPane().add(txtSoTrang);

		lblNewLabel_9 = new JLabel("Nhân viên lập hóa đơn:");
		lblNewLabel_9.setForeground(new Color(72, 61, 139));
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_9.setBounds(10, 160, 196, 40);
		getContentPane().add(lblNewLabel_9);

		txtTenSp = new JLabel();
		txtTenSp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtTenSp.setBounds(297, 122, 328, 23);
		getContentPane().add(txtTenSp);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 599, 1096, 54);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(405, 10, 132, 39);
		panel_1.add(btnThoat);

		txtAreaGhiChu = new JTextArea();
		txtAreaGhiChu.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtAreaGhiChu.setBounds(10, 480, 485, 109);
		txtAreaGhiChu.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(txtAreaGhiChu);

		JLabel lblNewLabel_6_1 = new JLabel("Ghi chú(Mô tả):");
		lblNewLabel_6_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_6_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_6_1.setBounds(158, 447, 160, 23);
		getContentPane().add(lblNewLabel_6_1);
		
		lblTongTienKhachDua = new JLabel("Tổng tiền hóa đơn:");
		lblTongTienKhachDua.setForeground(new Color(72, 61, 139));
		lblTongTienKhachDua.setFont(new Font("Arial", Font.BOLD, 16));
		lblTongTienKhachDua.setBounds(10, 399, 184, 38);
		getContentPane().add(lblTongTienKhachDua);
		
		txtTongTienHoaDon = new JLabel();
		txtTongTienHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTongTienHoaDon.setBackground(Color.WHITE);
		txtTongTienHoaDon.setBounds(236, 399, 240, 33);
		getContentPane().add(txtTongTienHoaDon);
		
		lblDanhSachSanPham = new JLabel("Tiền khách đã đưa:");
		lblDanhSachSanPham.setForeground(new Color(72, 61, 139));
		lblDanhSachSanPham.setFont(new Font("Arial", Font.BOLD, 16));
		lblDanhSachSanPham.setBounds(10, 337, 196, 38);
		getContentPane().add(lblDanhSachSanPham);
		
		txtTienKhachDua = new JLabel();
//		txtTienKhachDua_1.setText("<dynamic>");
		txtTienKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTienKhachDua.setBackground(Color.WHITE);
		txtTienKhachDua.setBounds(236, 336, 240, 39);
		getContentPane().add(txtTienKhachDua);
		
		JPanel panel_right = new JPanel();
		panel_right.setBounds(519, 94, 587, 433);
		getContentPane().add(panel_right);
		setValue();
		
		
		panel_right.setLayout(null);
		String header_ChiTietHD[] = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Giá tiền","Số lượng"};
		tableModel_chiTietHoaDonDao = new DefaultTableModel(header_ChiTietHD, 0);
		table_ChiTietHD = new JTable(tableModel_chiTietHoaDonDao);
		sp_ChiTietHD = new JScrollPane(table_ChiTietHD, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_ChiTietHD.setBounds(10, 45, 552, 367);
		table_ChiTietHD.setAutoCreateRowSorter(true);
		panel_right.add(sp_ChiTietHD);
		
		JLabel lblNewLabel_1 = new JLabel("DANH SÁCH SẢN PHẨM");
		lblNewLabel_1.setBounds(128, 2, 360, 33);
		panel_right.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			docDLVaoTableModel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		txtDonVi = new JLabel();
//		txtDonVi.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		txtDonVi.setBackground(Color.WHITE);
//		txtDonVi.setBounds(125, 438, 240, 33);
//		getContentPane().add(txtDonVi);
//		txtAreaGhiChu.setEditable(false);
		
//		if(loaiSanPham.equals("Sách")) {
//			try {
//				setRadSach();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else
//		{
//			try {
//				setRadVPP();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
		btnThoat.addActionListener(this);
	}

	public void setValue() {
		
		txtMaHoaDon.setText(maHoaDon);
		txtNhanVienLapHoaDon.setText(tenNhanVien);
		txtTenKhachHang.setText(tenKhachHang);
		txtNgayLapHoaDon.setText(ngayLap);
		txtTienKhachDua.setText(tienKhachDua);
		txtTongTienHoaDon.setText(tongTienHoaDon);
	}
	
	private void docDLVaoTableModel() throws SQLException {
		chiTietHoaDonDao = new ChiTietHoaDonDao();
		dsHoaDon = chiTietHoaDonDao.getCTHoaDonTheoMaHoaDon(maHoaDon);
		int i = 1;
		
		for (ChiTietHoaDon chiTietHoaDon: dsHoaDon) {
			String masanPham = chiTietHoaDon.getSanPham().getMaSanPham();
			sanPham_dao = new SanPhamDao();
			sanPham = sanPham_dao.timSanPhamTheoMa1(masanPham);
			if(sanPham.getLoaiSanPham().equals("Sách")){
				Sach s = sanPham_dao.getSachTheoMaSP(masanPham);
				tableModel_chiTietHoaDonDao.addRow(new Object[] { i++, s.getMaSanPham() , s.getTenSach(),
						chiTietHoaDon.getDonGia(), chiTietHoaDon.getSoLuong() });
			}else {
				VanPhongPham vvp = sanPham_dao.getVPPTheoMaSP(masanPham);
				tableModel_chiTietHoaDonDao.addRow(new Object[] { i++, vvp.getMaSanPham() , vvp.getTenVanPhongPham(),
						chiTietHoaDon.getDonGia(), chiTietHoaDon.getSoLuong() });
			}
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThoat)) {
			if(JOptionPane.showConfirmDialog(null,"Có chắc bạn muốn thoát","Cảnh báo",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION)
				this.setVisible(false);
		}
	}
}
