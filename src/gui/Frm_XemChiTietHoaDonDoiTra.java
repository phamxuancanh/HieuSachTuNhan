package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHoaDonDao;
import dao.SanPhamDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
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

public class Frm_XemChiTietHoaDonDoiTra extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel txtNhanVienLapHD;
	private JLabel txtTenKhachHang;
	private JLabel txtNgayLapHoaDon;
	private JLabel txtMaHoaDonDT;
	private JLabel lblTenKH;
	private JLabel lblNgayLapHoaDon;
	private JLabel txtTenKhachHang_1 = new JLabel();

	private JLabel lblNhanVien;
	JLabel lblTienKhachDua;

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
	String maHDDT;
	String maHD;
	String ngayLap;
	String tenNhanVien;
	String tenKhachHang;
	String tienKhachDua;
	String ghiChu;
	String tienPhaiTru;
	private JLabel lblTienPhaiTru;
	private JLabel txtTienPhaiTru;
	private JTextArea txtAreaGhiChu;
	private JLabel txtTienKhachDua;
	JScrollPane sp_ChiTietHD;
	private JTable table_ChiTietHD;
	private DefaultTableModel tableModel_chiTietHoaDonDao;
	private SanPham sanPham;
	private SanPhamDao sanPham_dao;
	

	@SuppressWarnings("deprecation")
	public Frm_XemChiTietHoaDonDoiTra(String maHDDT, String maHD, String tenNV, String tenKH, String ngayLapHD,
			String ghiChu, String tienKhachDua, String tienPhaiTru) {
		System.out.println("Ma hd cu "+maHD );
		this.maHDDT = maHDDT;
		this.maHD = maHD;
		this.tenNhanVien = tenNV;
		this.ngayLap = ngayLapHD;
		this.tenKhachHang = tenKH;
		this.ghiChu = ghiChu;
		this.tienKhachDua = tienKhachDua;
		this.tienPhaiTru = tienPhaiTru;
		setTitle("Chi tiết hóa đơn đổi trả");
		setResizable(false);
		setSize(785, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblMaHDDT = new JLabel("Mã hóa đơn đổi trả:");
		lblMaHDDT.setForeground(new Color(72, 61, 139));
		lblMaHDDT.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaHDDT.setBounds(10, 94, 160, 38);
		getContentPane().add(lblMaHDDT);

		txtNhanVienLapHD = new JLabel();
		txtNhanVienLapHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNhanVienLapHD.setBackground(new Color(255, 255, 255));
		txtNhanVienLapHD.setBounds(236, 195, 273, 38);
		getContentPane().add(txtNhanVienLapHD);

		txtTenKhachHang = new JLabel();
		txtTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKhachHang.setBackground(Color.WHITE);
		txtTenKhachHang.setBounds(236, 232, 240, 38);
		getContentPane().add(txtTenKhachHang);

		txtNgayLapHoaDon = new JLabel();
		txtNgayLapHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgayLapHoaDon.setBackground(Color.WHITE);
		txtNgayLapHoaDon.setBounds(236, 291, 240, 38);
		getContentPane().add(txtNgayLapHoaDon);

		JPanel panel = new JPanel();
		panel.setBounds(10, 38, 285, 46);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("CHI TIẾT HÓA ĐƠN ĐỔI TRẢ");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 10, 751, 39);
		getContentPane().add(lblNewLabel);

		txtMaHoaDonDT = new JLabel("maHoaDon");
		txtMaHoaDonDT.setBorder(BorderFactory.createLineBorder(Color.cyan));
		txtMaHoaDonDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaHoaDonDT.setBounds(236, 94, 240, 38);
		getContentPane().add(txtMaHoaDonDT);

		lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setForeground(new Color(72, 61, 139));
		lblTenKH.setFont(new Font("Arial", Font.BOLD, 16));
		lblTenKH.setBounds(10, 243, 170, 38);
		getContentPane().add(lblTenKH);

		lblNgayLapHoaDon = new JLabel("Ngày lập hóa đơn:");
		lblNgayLapHoaDon.setForeground(new Color(72, 61, 139));
		lblNgayLapHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		lblNgayLapHoaDon.setBounds(10, 291, 196, 38);
		getContentPane().add(lblNgayLapHoaDon);

		lblNhanVien = new JLabel("Nhân viên lập hóa đơn:");
		lblNhanVien.setForeground(new Color(72, 61, 139));
		lblNhanVien.setFont(new Font("Arial", Font.BOLD, 16));
		lblNhanVien.setBounds(10, 193, 204, 40);
		getContentPane().add(lblNhanVien);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 599, 1096, 54);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(330, 10, 132, 39);
		panel_1.add(btnThoat);

		txtAreaGhiChu = new JTextArea();
		txtAreaGhiChu.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtAreaGhiChu.setBounds(176, 480, 485, 109);
		txtAreaGhiChu.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(txtAreaGhiChu);

		JLabel lblNewLabel_6_1 = new JLabel("Ghi chú(Mô tả):");
		lblNewLabel_6_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_6_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_6_1.setBounds(325, 447, 160, 23);
		getContentPane().add(lblNewLabel_6_1);

		lblTienPhaiTru = new JLabel("Tiền phải trừ:");
		lblTienPhaiTru.setForeground(new Color(72, 61, 139));
		lblTienPhaiTru.setFont(new Font("Arial", Font.BOLD, 16));
		lblTienPhaiTru.setBounds(10, 387, 184, 38);
		getContentPane().add(lblTienPhaiTru);

		txtTienPhaiTru = new JLabel();
		txtTienPhaiTru.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTienPhaiTru.setBackground(Color.WHITE);
		txtTienPhaiTru.setBounds(236, 392, 240, 33);
		getContentPane().add(txtTienPhaiTru);

		lblTienKhachDua = new JLabel("Tiền khách đã đưa:");
		lblTienKhachDua.setForeground(new Color(72, 61, 139));
		lblTienKhachDua.setFont(new Font("Arial", Font.BOLD, 16));
		lblTienKhachDua.setBounds(10, 339, 196, 38);
		getContentPane().add(lblTienKhachDua);

		txtTienKhachDua = new JLabel();
//		txtTienKhachDua_1.setText("<dynamic>");
		txtTienKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTienKhachDua.setBackground(Color.WHITE);
		txtTienKhachDua.setBounds(236, 343, 240, 39);
		getContentPane().add(txtTienKhachDua);
		setValue();

		JLabel lblMaHDCu = new JLabel();
		lblMaHDCu.setText("Mã hóa đơn cũ");
		lblMaHDCu.setForeground(new Color(72, 61, 139));
		lblMaHDCu.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaHDCu.setBounds(10, 143, 189, 40);
		getContentPane().add(lblMaHDCu);
		
		txtTenKhachHang_1 = new JLabel();
		txtTenKhachHang_1.setText(maHD);
		txtTenKhachHang_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKhachHang_1.setBackground(Color.WHITE);
		txtTenKhachHang_1.setBounds(236, 147, 240, 38);
		getContentPane().add(txtTenKhachHang_1);

		btnThoat.addActionListener(this);
	}

	public void setValue() {
//		System.out.println("Ma hd cu: ", this.maHD);
		txtMaHoaDonDT.setText(maHDDT);
		txtTenKhachHang_1.setText(maHD);
		txtNhanVienLapHD.setText(tenNhanVien);
		txtTenKhachHang.setText(tenKhachHang);
		txtNgayLapHoaDon.setText(ngayLap);
		txtTienKhachDua.setText(tienKhachDua);
		txtTienPhaiTru.setText(tienPhaiTru);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			if (JOptionPane.showConfirmDialog(null, "Có chắc bạn muốn thoát", "Cảnh báo", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
				this.setVisible(false);
		}
	}
}
