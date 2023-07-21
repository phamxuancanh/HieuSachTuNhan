package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.data.json.impl.JSONArray;
import org.jfree.data.json.impl.JSONObject;

import entity.*;
import service.impl.*;

public class Pn_QuanLySanPham extends JPanel implements ActionListener, MouseListener {
	private final static int tenSach = 0;
	private final static int tenTacGia = 1;
	private final static int tenNhaXuatBan = 2;
	private final static int namXB = 3;
	private final static int soTrang = 4;
	private final static int tenTheLoai = 5;
	private final static int soLuongTon = 6;
	private final static int trongLuong = 7;
	private final static int tenNhaCungCap = 8;
	private final static int giaNhap = 9;
	private final static int ghiChu = 10;
	private final static int hinhAnh = 11;

	private final static int tenVanPhongPham = 0;
	private final static int tenLoaiVanPhongPham = 1;
	private final static int tenMauSac = 2;
	private final static int tenChatLieu = 3;
	private final static int tenXuatXu = 4;
	private final static int soLuongTonvpp = 5;
	private final static int trongLuongvpp = 6;
	private final static int tenNhaCungCapvpp = 7;
	private final static int giaNhapvpp = 8;
	private final static int ghiChuvpp = 9;
	private final static int hinhAnhvpp = 10;

	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelSanPham;
	private JTable tableSanPham;
	private JScrollPane scrollSanPham;
	private JTextField txtGiaTu;
	private JButton btnThemSP;
	private JButton btnImportFile;
	private JButton btnXuatFile;
	private JButton btnTImKiem;
	private JButton btnDau;
	private JButton btnTruoc;
	private JButton btnSau;
	private JButton btnCuoi;
	private JButton btnXemSachLoi;
	private JLabel lblMauTin;
	private JButton btnLamMoiDanhSach;
	private JButton btnCapNhat;
	private JRadioButton radSach;
	private JRadioButton radVPP;
	private JTextField txtGiaDen;
	private JComboBox<Object> cbbTacGiaorChatLieu;
	private JComboBox<Object> cbbNhaXBorXuatXu;
	private JComboBox<String> cbbNhaCungCap;
	private JLabel lblHetHang;
	private JPanel panel_1;
	private JLabel lblImageSP;
	private JLabel lblTenSP;
	private JLabel lblSoLuong;
	private JLabel lblGiaNhap;
	private JLabel lblGiaBan;
	private JLabel lblNhaCungCap;
	private JLabel lblTacGiaOrChatLieu;
	private JLabel lblSoLuongHienThi;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JButton btnLamMoiLoc;
	private ImageIcon imageIcon;
	private JComboBox<Object> cbbLoai;
	private JComboBox<Object> cbbGia;
	private JButton btnXemChiTiet;
	private int mauTinHienHanh;
	private int tongSoMauTin;
	private JCheckBox chkHetHang;
	SanPhamServiceImpl sanPhamServiceImpl = new SanPhamServiceImpl();
	TheLoaiServiceImpl theLoaiServiceImpl = new TheLoaiServiceImpl();
	TacGiaServiceImpl tacGiaServiceImpl = new TacGiaServiceImpl();
	NhaXuatBanServiceImpl nhaXuatBanServiceImpl = new NhaXuatBanServiceImpl();
	NhaCungCapServiceImpl nhaCungCapServiceImpl = new NhaCungCapServiceImpl();
	ChatLieuServiceImpl chatLieuServiceImpl = new ChatLieuServiceImpl();
	XuatXuServiceImpl xuatXuServiceImpl = new XuatXuServiceImpl();
	MauSacServiceImpl mauSacServiceImpl = new MauSacServiceImpl();
	private ArrayList<TheLoaiSach> theLoaiSachs;
	private ArrayList<TacGia> tacGias;
	private ArrayList<NhaXuatBan> nhaXuatBans;
	private ArrayList<NhaCungCap> nhaCungCaps;
	private ArrayList<TheLoaiVanPhongPham> theLoaiVanPhongPhams;
	private ArrayList<ChatLieu> chatLieus;
	private ArrayList<XuatXu> xuatXus;
	private JButton btnQuanLyDanhMuc;
	private HSSFWorkbook hssfWorkbook;
	private JFileChooser filechoose;
	private File file;
	private ArrayList<Sach> listSach;
	private ArrayList<VanPhongPham> listVPP;

	public Pn_QuanLySanPham() throws Exception {
		setSize(1493, 650);
		setBackground(new Color(0, 206, 209));
		setLayout(null);
//		JSlider slider = new JSlider(50,500);
//		slider.setPaintLabels(true);
//		slider.setPaintLabels(true);
//		slider.setLabelTable(slider.createStandardLabels(50));
		JLabel lblTitle = new JLabel("QUẢN LÝ SẢN PHẨM");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
//		lblTitle.setForeground(new Color(0, 206, 209));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTitle.setBounds(418, 10, 601, 44);
		add(lblTitle);

		JPanel pnLoc = new JPanel();
		pnLoc.setBackground(new Color(255, 255, 255));
		pnLoc.setBorder(new LineBorder(new Color(58, 176, 242), 2));
		pnLoc.setBounds(10, 64, 260, 549);
		add(pnLoc);
		pnLoc.setLayout(null);

		JLabel lblSach = new JLabel("Sách: ");
		lblSach.setForeground(new Color(182, 9, 242));
		lblSach.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSach.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSach.setBounds(10, 10, 51, 33);
		pnLoc.add(lblSach);
		txtGiaTu = new JTextField();
		txtGiaTu.setText("Từ 0đ");
		txtGiaTu.setHorizontalAlignment(SwingConstants.CENTER);
		txtGiaTu.setForeground(Color.GRAY);
		txtGiaTu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGiaTu.setColumns(10);
		txtGiaTu.setBounds(10, 259, 115, 33);
		pnLoc.add(txtGiaTu);
		txtGiaTu.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtGiaTu.getText().equals("Từ 0đ")) {
					txtGiaTu.setText("");
					txtGiaTu.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtGiaTu.getText().isEmpty()) {
					txtGiaTu.setForeground(Color.GRAY);
					txtGiaTu.setText("Từ 0đ");
				}
			}
		});
		txtGiaDen = new JTextField();
		txtGiaDen.setText("Đến 10,000,000đ");
		txtGiaDen.setHorizontalAlignment(SwingConstants.CENTER);
		txtGiaDen.setForeground(Color.GRAY);
		txtGiaDen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtGiaDen.setColumns(10);
		txtGiaDen.setBounds(135, 259, 115, 33);
		pnLoc.add(txtGiaDen);
		txtGiaDen.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtGiaDen.getText().equals("Đến 10,000,000đ")) {
					txtGiaDen.setText("");
					txtGiaDen.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtGiaDen.getText().isEmpty()) {
					txtGiaDen.setForeground(Color.GRAY);
					txtGiaDen.setText("Đến 10,000,000đ");
				}
			}
		});
		txtMaSP = new JTextField();
		txtMaSP.setForeground(Color.GRAY);
		txtMaSP.setText("Mã sản phẩm");
		txtMaSP.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaSP.setBounds(10, 53, 240, 37);
		pnLoc.add(txtMaSP);
		txtMaSP.setColumns(10);
		txtMaSP.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtMaSP.getText().equals("Mã sản phẩm")) {
					txtMaSP.setText("");
					txtMaSP.setForeground(Color.BLACK);
					txtMaSP.setHorizontalAlignment(SwingConstants.LEFT);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtMaSP.getText().isEmpty()) {
					txtMaSP.setForeground(Color.GRAY);
					txtMaSP.setText("Mã sản phẩm");
					txtMaSP.setHorizontalAlignment(SwingConstants.CENTER);
				}
			}
		});

		txtTenSP = new JTextField();
		txtTenSP.setForeground(Color.GRAY);
		txtTenSP.setText("Tên sản phẩm");
		txtTenSP.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(10, 101, 240, 37);
		pnLoc.add(txtTenSP);
		txtTenSP.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTenSP.getText().equals("Tên sản phẩm")) {
					txtTenSP.setText("");
					txtTenSP.setForeground(Color.BLACK);
					txtTenSP.setHorizontalAlignment(SwingConstants.LEFT);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTenSP.getText().isEmpty()) {
					txtTenSP.setForeground(Color.GRAY);
					txtTenSP.setText("Tên sản phẩm");
					txtTenSP.setHorizontalAlignment(SwingConstants.CENTER);
				}
			}
		});

		btnLamMoiLoc = new JButton("Làm mới");
		btnLamMoiLoc.setHorizontalAlignment(SwingConstants.LEFT);
		imageIcon = setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/refresh-button.png"), 13, 13);
		btnLamMoiLoc.setIcon(imageIcon);
		btnLamMoiLoc.setBackground(new Color(88, 86, 214));
		btnLamMoiLoc.setForeground(new Color(255, 255, 255));
		btnLamMoiLoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLamMoiLoc.setBounds(10, 506, 109, 33);
		pnLoc.add(btnLamMoiLoc);

		btnTImKiem = new JButton("Tìm kiếm");
		btnTImKiem.setHorizontalAlignment(SwingConstants.LEFT);
		btnTImKiem.setForeground(Color.BLACK);
		btnTImKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnTImKiem.setBounds(135, 506, 115, 33);
		imageIcon = setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/loupe.png"), 13, 13);
		btnTImKiem.setIcon(imageIcon);
		pnLoc.add(btnTImKiem);

		radSach = new JRadioButton("");
		radSach.setSelected(true);
		radSach.setBackground(new Color(255, 255, 255));
		radSach.setForeground(new Color(255, 255, 255));
		radSach.setBounds(59, 16, 21, 21);
		pnLoc.add(radSach);

		JLabel lblVPP = new JLabel("Văn phòng phẩm:");
		lblVPP.setHorizontalAlignment(SwingConstants.LEFT);
		lblVPP.setForeground(new Color(182, 9, 242));
		lblVPP.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblVPP.setBounds(86, 10, 143, 33);
		pnLoc.add(lblVPP);

		radVPP = new JRadioButton("");
		radVPP.setForeground(Color.WHITE);
		radVPP.setBackground(Color.WHITE);
		radVPP.setBounds(233, 16, 21, 21);
		pnLoc.add(radVPP);

		ButtonGroup G = new ButtonGroup();
		G.add(radSach);
		G.add(radVPP);

		cbbLoai = new JComboBox<Object>();
		cbbLoai.setBackground(new Color(255, 255, 255));
		cbbLoai.setBounds(10, 148, 240, 33);
		pnLoc.add(cbbLoai);

		String[] gia = { "Tất cả giá", "Dưới 50000", "50000 đến 120000", "120000 đến 250000", "Trên 250000" };
		cbbGia = new JComboBox<Object>(gia);
		cbbGia.setBackground(Color.WHITE);
		cbbGia.setBounds(10, 202, 240, 33);
		pnLoc.add(cbbGia);

		JLabel lblHocNhpGi = new JLabel("Hoặc nhập giá nhập ở ô dưới đây:");
		lblHocNhpGi.setHorizontalAlignment(SwingConstants.LEFT);
		lblHocNhpGi.setForeground(new Color(182, 9, 242));
		lblHocNhpGi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHocNhpGi.setBounds(10, 233, 240, 27);
		pnLoc.add(lblHocNhpGi);

		cbbTacGiaorChatLieu = new JComboBox<Object>();
		cbbTacGiaorChatLieu.setBackground(Color.WHITE);
		cbbTacGiaorChatLieu.setBounds(10, 311, 240, 33);
		pnLoc.add(cbbTacGiaorChatLieu);

		cbbNhaXBorXuatXu = new JComboBox<Object>();
		cbbNhaXBorXuatXu.setBackground(Color.WHITE);
		cbbNhaXBorXuatXu.setBounds(10, 365, 240, 33);
		pnLoc.add(cbbNhaXBorXuatXu);

		cbbNhaCungCap = new JComboBox<String>();
		cbbNhaCungCap.setBackground(Color.WHITE);
		cbbNhaCungCap.setBounds(10, 420, 240, 33);
		pnLoc.add(cbbNhaCungCap);

		lblHetHang = new JLabel("Hết hàng: ");
		lblHetHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblHetHang.setForeground(new Color(182, 9, 242));
		lblHetHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHetHang.setBounds(10, 463, 76, 33);
		pnLoc.add(lblHetHang);

		chkHetHang = new JCheckBox("");
		chkHetHang.setHorizontalAlignment(SwingConstants.CENTER);
		chkHetHang.setBounds(86, 469, 15, 21);
		pnLoc.add(chkHetHang);

		String[] cols = { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá nhập", "Giá bán", "Nhà cung cấp", "Số trang",
				"Tác giả", "Nhà xuất bản" };
		modelSanPham = new DefaultTableModel(cols, 0);
		tableSanPham = new JTable(modelSanPham);
		tableSanPham.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));

		scrollSanPham = new JScrollPane(tableSanPham);
		scrollSanPham.setBounds(280, 223, 1203, 348);

		tableSanPham.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableSanPham.setAutoCreateRowSorter(true);
		tableSanPham.setRowHeight(25);
		tableSanPham.setBackground(Color.decode("#BEFFC0"));
		scrollSanPham.getViewport().setBackground(Color.WHITE);
		tableSanPham.getTableHeader().setPreferredSize(new Dimension(0, 40));
		add(scrollSanPham);

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBackground(new Color(58, 176, 242));
		pnTimKiem.setBorder(new LineBorder(new Color(58, 176, 242), 2));
		pnTimKiem.setBounds(10, 10, 260, 64);
		add(pnTimKiem);
		pnTimKiem.setLayout(null);
		JLabel lblTimKiem = new JLabel("Bộ lọc");
		lblTimKiem.setBounds(47, 0, 146, 54);
		pnTimKiem.add(lblTimKiem);
		lblTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTimKiem.setForeground(new Color(255, 255, 255));
		lblTimKiem.setBackground(new Color(58, 176, 242));

		JPanel panel = new JPanel();
		panel.setBounds(280, 576, 1203, 37);
		panel.setBackground(Color.decode("#F6B85A"));
		add(panel);
		panel.setLayout(null);

		btnDau = new JButton();
		btnDau.setIcon(new ImageIcon(Pn_QuanLySanPham.class.getResource("/gui/icon/go-first.png")));
		btnDau.setBounds(436, 0, 35, 35);
		btnDau.setBackground(Color.decode("#F6B85A"));
		btnDau.setBorder(BorderFactory.createLineBorder(Color.decode("#F6B85A")));
		panel.add(btnDau);

		btnTruoc = new JButton();
		btnTruoc.setIcon(new ImageIcon(Pn_QuanLySanPham.class.getResource("/gui/icon/go-previous.png")));
		btnTruoc.setBounds(481, 0, 35, 35);
		btnTruoc.setBackground(Color.decode("#F6B85A"));
		btnTruoc.setBorder(BorderFactory.createLineBorder(Color.decode("#F6B85A")));
		panel.add(btnTruoc);

		btnSau = new JButton();
		btnSau.setIcon(new ImageIcon(Pn_QuanLySanPham.class.getResource("/gui/icon/go-next.png")));
		btnSau.setBounds(581, 0, 35, 35);
		btnSau.setBackground(Color.decode("#F6B85A"));
		btnSau.setBorder(BorderFactory.createLineBorder(Color.decode("#F6B85A")));
		panel.add(btnSau);

		btnCuoi = new JButton();
		btnCuoi.setIcon(new ImageIcon(Pn_QuanLySanPham.class.getResource("/gui/icon/go-last.png")));
		btnCuoi.setBounds(624, 0, 35, 35);
		btnCuoi.setBackground(Color.decode("#F6B85A"));
		btnCuoi.setBorder(BorderFactory.createLineBorder(Color.decode("#F6B85A")));
		panel.add(btnCuoi);

		lblMauTin = new JLabel("1/2");
		lblMauTin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMauTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMauTin.setBounds(509, 10, 77, 13);
		panel.add(lblMauTin);

		lblSoLuongHienThi = new JLabel("Hiển thị 0 sản phẩm");
		lblSoLuongHienThi.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblSoLuongHienThi.setBounds(669, 0, 294, 35);
		panel.add(lblSoLuongHienThi);

		btnThemSP = new JButton("Thêm sản phẩm");
		btnThemSP.setHorizontalAlignment(SwingConstants.LEFT);
		btnThemSP.setIcon(setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/add.png"), 16, 16));
		btnThemSP.setBounds(876, 173, 176, 40);
		add(btnThemSP);
		btnThemSP.setBackground(new Color(66, 178, 26));
		btnThemSP.setForeground(new Color(255, 255, 255));
		btnThemSP.setFont(new Font("Dialog", Font.BOLD, 16));

		btnImportFile = new JButton("Import File");
		btnImportFile
				.setIcon(setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/download.png"), 25, 25));
		btnImportFile.setHorizontalAlignment(SwingConstants.LEFT);
		btnImportFile.setBounds(1060, 173, 176, 40);
		add(btnImportFile);
		btnImportFile.setForeground(Color.WHITE);
		btnImportFile.setBackground(Color.decode("#E761EA"));
		btnImportFile.setFont(new Font("Dialog", Font.BOLD, 16));

		btnXuatFile = new JButton("Export File");
		btnXuatFile.setIcon(setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/share.png"), 25, 25));
		btnXuatFile.setHorizontalAlignment(SwingConstants.LEFT);
		btnXuatFile.setBounds(1246, 173, 176, 40);
		btnXuatFile.setBackground(Color.decode("#E761EA"));
		add(btnXuatFile);
		btnXuatFile.setForeground(Color.WHITE);
		btnXuatFile.setFont(new Font("Dialog", Font.BOLD, 16));

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat
				.setIcon(setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/maintenance.png"), 25, 25));
		btnCapNhat.setHorizontalAlignment(SwingConstants.LEFT);
		btnCapNhat.setBounds(1062, 123, 176, 40);
		add(btnCapNhat);
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Dialog", Font.BOLD, 16));
		btnCapNhat.setBackground(Color.decode("#EDD927"));

		btnQuanLyDanhMuc = new JButton("Quản lý danh mục");
		btnQuanLyDanhMuc.setHorizontalAlignment(SwingConstants.LEFT);
		btnQuanLyDanhMuc.setBounds(877, 72, 176, 40);
		add(btnQuanLyDanhMuc);
		btnQuanLyDanhMuc.setFont(new Font("Dialog", Font.BOLD, 16));

		btnLamMoiDanhSach = new JButton("Làm mới bảng");
		btnLamMoiDanhSach.setHorizontalAlignment(SwingConstants.LEFT);
		btnLamMoiDanhSach.setIcon(
				setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/refresh-button.png"), 25, 25));
		btnLamMoiDanhSach.setBounds(876, 123, 176, 40);
		add(btnLamMoiDanhSach);
		btnLamMoiDanhSach.setForeground(Color.WHITE);
		btnLamMoiDanhSach.setFont(new Font("Dialog", Font.BOLD, 16));
		btnLamMoiDanhSach.setBackground(Color.decode("#1959BA"));

		btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setHorizontalAlignment(SwingConstants.LEFT);
		btnXemChiTiet.setForeground(Color.WHITE);
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXemChiTiet.setBackground(new Color(88, 86, 214));
		btnXemChiTiet.setBounds(1248, 123, 176, 40);
		btnXemChiTiet
				.setIcon(setSizeImageIconURL(Pn_QuanLySanPham.class.getResource("/gui/icon/DetailBook.png"), 25, 25));
		add(btnXemChiTiet);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(419, 54, 426, 159);
		add(panel_1);
		panel_1.setLayout(null);

		lblTenSP = new JLabel("Tên sản phẩm: ");
		lblTenSP.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTenSP.setBounds(0, 11, 386, 13);
		panel_1.add(lblTenSP);

		lblSoLuong = new JLabel("Số Lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSoLuong.setBounds(0, 35, 386, 13);
		panel_1.add(lblSoLuong);

		lblGiaNhap = new JLabel("Giá nhập");
		lblGiaNhap.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaNhap.setBounds(0, 59, 386, 13);
		panel_1.add(lblGiaNhap);

		lblGiaBan = new JLabel("Giá bán: ");
		lblGiaBan.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaBan.setBounds(0, 83, 386, 13);
		panel_1.add(lblGiaBan);

		lblNhaCungCap = new JLabel("Nhà cung cấp: ");
		lblNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNhaCungCap.setBounds(0, 107, 386, 13);
		panel_1.add(lblNhaCungCap);

		lblTacGiaOrChatLieu = new JLabel("Tác giả: ");
		lblTacGiaOrChatLieu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTacGiaOrChatLieu.setBounds(0, 131, 386, 13);
		panel_1.add(lblTacGiaOrChatLieu);

		lblImageSP = new JLabel("");
		lblImageSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageSP.setBounds(280, 54, 140, 163);
		lblImageSP.setIcon(setSizeImageIconString("..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg",
				lblImageSP.getWidth(), lblImageSP.getHeight()));
		add(lblImageSP);
		
		btnXemSachLoi = new JButton("Xem sản phẩm lỗi");
		btnXemSachLoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnXemSachLoi.setForeground(Color.WHITE);
		btnXemSachLoi.setFont(new Font("Dialog", Font.BOLD, 16));
		btnXemSachLoi.setBackground(new Color(25, 89, 186));
		btnXemSachLoi.setBounds(1060, 72, 176, 40);
		add(btnXemSachLoi);

		// load du lieu
		setRadSach();
		tongSoMauTin = tableSanPham.getRowCount();
		capNhatThongTinMauTin(-1);

		// Đăng ký lắng nghe
		btnLamMoiDanhSach.addActionListener(this);
		btnQuanLyDanhMuc.addActionListener(this);
		btnLamMoiLoc.addActionListener(this);
		btnXemChiTiet.addActionListener(this);
		btnThemSP.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnXuatFile.addActionListener(this);
		btnImportFile.addActionListener(this);
		btnTImKiem.addActionListener(this);
		btnDau.addActionListener(this);
		btnCuoi.addActionListener(this);
		btnSau.addActionListener(this);
		btnTruoc.addActionListener(this);
		radVPP.addMouseListener(this);
		radSach.addMouseListener(this);
		tableSanPham.addMouseListener(this);
		btnXemSachLoi.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (tongSoMauTin > 0) { // Cho các nút duyệt
			if (o.equals(btnDau))
				mauTinHienHanh = 0;
			else if (o.equals(btnCuoi))
				mauTinHienHanh = tongSoMauTin - 1;
			else if (o.equals(btnSau) && mauTinHienHanh < tongSoMauTin - 1)
				mauTinHienHanh++;
			else if (o.equals(btnTruoc) && mauTinHienHanh > 0)
				mauTinHienHanh--;
			capNhatThongTinMauTin(mauTinHienHanh);
		}
		if(o.equals(btnXemSachLoi)) {
			try {
				new Frm_XemChiTietHoaDon2().setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (o.equals(btnLamMoiDanhSach)) {
			try {
				if (radSach.isSelected())
					setRadSach();
				else
					setRadVPP();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cbbGia.setSelectedIndex(0);
			chkHetHang.setSelected(false);
			txtGiaTu.setText("Từ 0đ");
			txtGiaDen.setText("Đến 10,000,000đ");
			txtMaSP.setText("Mã sản phẩm");
			txtTenSP.setText("Tên sản phẩm");
			modelSanPham.setRowCount(0);
			capNhatThongTinMauTin(-1);
			setHienThiRong();
		}

		if (o.equals(btnLamMoiLoc)) {

			try {
				if (radSach.isSelected())
					setRadSach();
				else
					setRadVPP();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			cbbGia.setSelectedIndex(0);
			chkHetHang.setSelected(false);
			txtGiaTu.setText("Từ 0đ");
			txtGiaDen.setText("Đến 10,000,000đ");
			txtMaSP.setText("Mã sản phẩm");
			txtTenSP.setText("Tên sản phẩm");
		}

		if (o.equals(btnThemSP)) {
			new Frm_ThemSP().setVisible(true);
		}

		if (o.equals(btnCapNhat)) {
			int row = tableSanPham.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào!!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				String maSP = modelSanPham.getValueAt(row, 0).toString();
				String loai = "Sách";
				if (radVPP.isSelected()) {
					loai = "Văn phòng phẩm";
				}
				new Frm_CapNhatSP(maSP, loai).setVisible(true);

			}
		}

		if (o.equals(btnTImKiem)) {
			try {
				if (radSach.isSelected())
					loadDuLieuSach();
				else
					loadDuLieuVPP();
				int row = -1;
				if (tableSanPham.getRowCount() != 0) {
					row = tableSanPham.getSelectedRow();
					tableSanPham.setRowSelectionInterval(0, 0);
					mauTinHienHanh = tableSanPham.getSelectedRow();
				} else {
					mauTinHienHanh = -1;
				}
				tongSoMauTin = tableSanPham.getRowCount();
				capNhatThongTinMauTin(mauTinHienHanh);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (o.equals(btnXemChiTiet)) {
			int row = tableSanPham.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào!!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				String maSP = modelSanPham.getValueAt(row, 0).toString();
				String loai = "Sách";
				if (radVPP.isSelected()) {
					loai = "Văn phòng phẩm";
				}
				new Frm_XemChiTiet(maSP, loai).setVisible(true);
			}
		}
		if (o.equals(btnQuanLyDanhMuc)) {
			if (radSach.isSelected())
				new Frm_QuanLyDanhMuc("Sách").setVisible(true);
			else
				new Frm_QuanLyDanhMuc("Văn phòng phẩm").setVisible(true);
		}
		if (o.equals(btnImportFile)) {
			filechoose = new JFileChooser("../HieuSachTuNhan/Script");
			filechoose.setMultiSelectionEnabled(false);
			String filePath = "";
			int x = filechoose.showDialog(this, "Chọn File");
			if (x == JFileChooser.APPROVE_OPTION) {
				file = filechoose.getSelectedFile();
				filePath = file.getAbsolutePath();
			}
			if (!filePath.endsWith(".xlsx")) {
				return;
			}
			if (radSach.isSelected()) {
				int count = 0;
				try {
					count = importSach(filePath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String s = "Import thành công " + count + " cuốn sách!!";
				JOptionPane.showMessageDialog(null, s, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			} else {
				int count = 0;
				try {
					count = importVPP(filePath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String s = "Import thành công " + count + " văn phòng phẩm!!";
				JOptionPane.showMessageDialog(null, s, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (o.equals(btnXuatFile)) {
			filechoose = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.xlsx", "xlsx");
			filechoose.setFileFilter(filter);
			filechoose.setMultiSelectionEnabled(false);

			String filePath = "";
			int x = filechoose.showDialog(this, "Chọn File");
			if (x == JFileChooser.APPROVE_OPTION) {
				file = filechoose.getSelectedFile();
				filePath = file.getAbsolutePath();
				if (!filePath.endsWith(".xlsx")) {
					filePath += ".xlsx";
				}
			}
			if (filePath.equals("")) {
				return;
			}
			if (radSach.isSelected()) {
				ArrayList<Sach> listSach = new ArrayList<>();
				int row = tableSanPham.getRowCount();
				for (int i = 0; i < row; i++) {
					try {
						Sach s = sanPhamServiceImpl.timSanPhamTheoMaSach(modelSanPham.getValueAt(i, 0).toString());
						listSach.add(s);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				try {
					exportSach(listSach, filePath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				ArrayList<VanPhongPham> listVPP = new ArrayList<>();
				int row = tableSanPham.getRowCount();
				for (int i = 0; i < row; i++) {
					try {
						VanPhongPham vpp = sanPhamServiceImpl
								.timSanPhamTheoMaVPP(modelSanPham.getValueAt(i, 0).toString());
						listVPP.add(vpp);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				try {
					exportVPP(listVPP, filePath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (JOptionPane.showConfirmDialog(null, "Export thành công!!\nBạn có muốn mở file?", "Thông báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				Desktop desktop = Desktop.getDesktop();
				File file = new File(filePath);
				if (file.exists())
					try {
						desktop.open(file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(radSach)) {
			setRadSach();
			setColumnName("Số trang", "Tác giả", "Nhà xuất bản");
			modelSanPham.setRowCount(0);
			tongSoMauTin = tableSanPham.getRowCount();
			capNhatThongTinMauTin(-1);
			lblSoLuongHienThi.setText("Hiển thị 0 sản phẩm");
			setHienThiRong();
		}
		if (o.equals(radVPP)) {
			setRadVPP();
			setColumnName("Màu sắc", "Chất liệu", "Xuất xứ");
			modelSanPham.setRowCount(0);
			tongSoMauTin = tableSanPham.getRowCount();
			capNhatThongTinMauTin(-1);
			setHienThiRong();
		}
		if (o.equals(tableSanPham)) {
			int row = tableSanPham.getSelectedRow();
			tongSoMauTin = tableSanPham.getRowCount();
			if (row >= 0) {
				mauTinHienHanh = row;
				capNhatThongTinMauTin(mauTinHienHanh);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void loadDuLieuSach() throws Exception {
		listSach = new ArrayList<Sach>();
		String maSach = txtMaSP.getText().trim();
		if (maSach.equals("Mã sản phẩm")) {
			maSach = "";
		}
		String tenSP = txtTenSP.getText().trim();
		if (tenSP.equals("Tên sản phẩm")) {
			tenSP = "";
		}

		String maTheLoai = "";
		if (cbbLoai.getSelectedIndex() != 0)
			maTheLoai = theLoaiSachs.get(cbbLoai.getSelectedIndex() - 1).getMaLoai();

		Long giaTu = (long) 0;
		Long giaDen = (long) 100000000;
		if (cbbGia.getSelectedIndex() != 0) {
			int key = cbbGia.getSelectedIndex();
			switch (key) {
			case 1:
				giaTu = (long) 0;
				giaDen = (long) 50001;
				break;
			case 2:
				giaTu = (long) 50001;
				giaDen = (long) 120001;
				break;
			case 3:
				giaTu = (long) 120001;
				giaDen = (long) 250001;
				break;
			case 4:
				giaTu = (long) 250001;
				giaDen = (long) 100000000;
				break;
			default:
				break;
			}
		}
		try {
			if (!txtGiaTu.getText().trim().equals("Từ 0đ") && !txtGiaDen.getText().trim().equals("Đến 10,000,000đ")) {
				giaTu = Long.parseLong(txtGiaTu.getText().trim());
				giaDen = Long.parseLong(txtGiaDen.getText().trim());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Giá từ bao nhiêu đến bao nhiêu phải nhập số!!!");
			txtGiaTu.requestFocus();
			return;
		}

		String maTacGia = "";
		if (cbbTacGiaorChatLieu.getSelectedIndex() != 0)
			maTacGia = tacGias.get(cbbTacGiaorChatLieu.getSelectedIndex() - 1).getMaTacGia();
		String maNXB = "";
		if (cbbNhaXBorXuatXu.getSelectedIndex() != 0)
			maNXB = nhaXuatBans.get(cbbNhaXBorXuatXu.getSelectedIndex() - 1).getMaNXB();
		String maNCC = "";
		if (cbbNhaCungCap.getSelectedIndex() != 0)
			maNCC = nhaCungCaps.get(cbbNhaCungCap.getSelectedIndex() - 1).getMaNCC();
		try {
			listSach = sanPhamServiceImpl.getListSach(maSach, tenSP, maTheLoai, giaTu, giaDen, maTacGia, maNXB, maNCC,
					chkHetHang.isSelected());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		modelSanPham.setRowCount(0);
		if (listSach == null) {
			JOptionPane.showMessageDialog(null, "Giá từ bao nhiêu phải bé hơn giá nhập!!", "Báo lỗi",
					JOptionPane.ERROR_MESSAGE);
			txtGiaTu.requestFocus();
			txtGiaTu.selectAll();
			return;
		}
		for (int i = 0; i < listSach.size(); i++) {
			Sach s = listSach.get(i);
			Object[] o = { s.getMaSanPham(), s.getTenSach(), s.getSoLuongTon(), s.getGiaNhap(), s.tinhGiaBan(),
					s.getNhaCungCap().getTenNCC(), s.getSoTrang(), s.getTacGia().getTenTacGia(),
					s.getNhaXuatBan().getTenNXB() };

			modelSanPham.addRow(o);
		}
		//
		lblSoLuongHienThi.setText("Hiển thị " + listSach.size() + " sản phẩm");
		TableColumnModel columnMode = tableSanPham.getColumnModel();
		columnMode.getColumn(0).setPreferredWidth(40);
		columnMode.getColumn(1).setPreferredWidth(100);
		columnMode.getColumn(2).setPreferredWidth(5);
		columnMode.getColumn(3).setPreferredWidth(10);
		columnMode.getColumn(4).setPreferredWidth(10);
		columnMode.getColumn(6).setPreferredWidth(10);
		columnMode.getColumn(7).setPreferredWidth(100);

//			
	}

	public void loadDuLieuVPP() {
		listVPP = new ArrayList<VanPhongPham>();
		String maVPP = txtMaSP.getText().trim();
		if (maVPP.equals("Mã sản phẩm")) {
			maVPP = "";
		}
		String tenSP = txtTenSP.getText().trim();
		if (tenSP.equals("Tên sản phẩm")) {
			tenSP = "";
		}

		String maTheLoai = "";
		if (cbbLoai.getSelectedIndex() != 0)
			maTheLoai = theLoaiSachs.get(cbbLoai.getSelectedIndex() - 1).getMaLoai();
		Long giaTu = (long) 0;
		Long giaDen = (long) 100000000;
		if (cbbGia.getSelectedIndex() != 0) {
			int key = cbbGia.getSelectedIndex();
			switch (key) {
			case 1:
				giaTu = (long) 0;
				giaDen = (long) 50001;
				break;
			case 2:
				giaTu = (long) 50001;
				giaDen = (long) 120001;
				break;
			case 3:
				giaTu = (long) 120001;
				giaDen = (long) 250001;
				break;
			case 4:
				giaTu = (long) 250001;
				giaDen = (long) 100000000;
				break;
			default:
				break;
			}
		}
		try {
			if (!txtGiaTu.getText().trim().equals("Từ 0đ") && !txtGiaDen.getText().trim().equals("Đến 10,000,000đ")) {
				giaTu = Long.parseLong(txtGiaTu.getText().trim());
				giaDen = Long.parseLong(txtGiaDen.getText().trim());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Giá từ bao nhiêu đến bao nhiêu phải nhập số!!!");
			txtGiaTu.requestFocus();
			return;
		}
		String maChatLieu = "";
		if (cbbTacGiaorChatLieu.getSelectedIndex() != 0)
			maChatLieu = chatLieus.get(cbbTacGiaorChatLieu.getSelectedIndex() - 1).getMaChatLieu();
		String maXuatXu = "";
		if (cbbNhaXBorXuatXu.getSelectedIndex() != 0)
			maXuatXu = xuatXus.get(cbbNhaXBorXuatXu.getSelectedIndex() - 1).getMaXuatXu();
		String maNCC = "";
		if (cbbNhaCungCap.getSelectedIndex() != 0)
			maNCC = nhaCungCaps.get(cbbNhaCungCap.getSelectedIndex() - 1).getMaNCC();
		try {
			listVPP = sanPhamServiceImpl.getListVanPhongPham(maVPP, tenSP, maTheLoai, giaTu, giaDen, maChatLieu,
					maXuatXu, maNCC, chkHetHang.isSelected());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelSanPham.setRowCount(0);
		if (listVPP == null) {
			JOptionPane.showMessageDialog(null, "Giá từ bao nhiêu phải bé hơn giá nhập!!", "Báo lỗi",
					JOptionPane.ERROR_MESSAGE);
			txtGiaTu.requestFocus();
			txtGiaTu.selectAll();
			return;
		}
		for (int i = 0; i < listVPP.size(); i++) {
			VanPhongPham s = listVPP.get(i);
			Object[] o = { s.getMaSanPham(), s.getTenVanPhongPham(), s.getSoLuongTon(), s.getGiaNhap(), s.tinhGiaBan(),
					s.getNhaCungCap().getTenNCC(), s.getMauSac().getTenMau(), s.getChatLieu().getTenChatLieu(),
					s.getXuatXu().getTenXuatXu() };
			modelSanPham.addRow(o);
		}
		lblSoLuongHienThi.setText("Hiển thị " + listVPP.size() + " sản phẩm");
		TableColumnModel columnMode = tableSanPham.getColumnModel();
		columnMode.getColumn(0).setPreferredWidth(40);
		columnMode.getColumn(1).setPreferredWidth(100);
		columnMode.getColumn(2).setPreferredWidth(5);
		columnMode.getColumn(3).setPreferredWidth(10);
		columnMode.getColumn(4).setPreferredWidth(10);
		columnMode.getColumn(6).setPreferredWidth(10);
		columnMode.getColumn(7).setPreferredWidth(100);
	}

	private void capNhatThongTinMauTin(int n) {
		if (n >= 0) {
			tableSanPham.setRowSelectionInterval(n, n);
			setHienThi(n);
		}
		tongSoMauTin = tableSanPham.getRowCount();
		mauTinHienHanh = n;
		if (tongSoMauTin == 0) {
			mauTinHienHanh = -1;
		}
		lblMauTin.setText(mauTinHienHanh + 1 + "/" + tongSoMauTin);
	}

	public ImageIcon setSizeImageIconURL(URL url, int width, int height) {
		ImageIcon image = new ImageIcon(url);
		Image imageSet = image.getImage();
		imageSet = imageSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		image = new ImageIcon(imageSet);
		return image;
	}

	public ImageIcon setSizeImageIconString(String s, int width, int height) {
		ImageIcon image = new ImageIcon(s);
		Image imageSet = image.getImage();
		imageSet = imageSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		image = new ImageIcon(imageSet);
		return image;
	}

	public void setRadSach() {
		// the loai
		theLoaiSachs = new ArrayList<TheLoaiSach>();
		try {
			theLoaiSachs = theLoaiServiceImpl.getListTheLoaiSach();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbLoai.removeAllItems();
		;
		cbbLoai.addItem("Tất cả thể loại");
		for (TheLoaiSach theLoaiSach : theLoaiSachs) {
			cbbLoai.addItem(theLoaiSach.getTenLoai());
		}
		// tac gia
		tacGias = new ArrayList<TacGia>();
		try {
			tacGias = tacGiaServiceImpl.getListTacGia();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbTacGiaorChatLieu.removeAllItems();
		cbbTacGiaorChatLieu.addItem("Tất cả tác giả");
		for (TacGia tacGia : tacGias) {
			cbbTacGiaorChatLieu.addItem(tacGia.getTenTacGia());
		}
		// nxb
		nhaXuatBans = new ArrayList<NhaXuatBan>();
		try {
			nhaXuatBans = nhaXuatBanServiceImpl.getListNhaXuatBan();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbNhaXBorXuatXu.removeAllItems();
		cbbNhaXBorXuatXu.addItem("Tất cả Nhà xuất bản");
		for (NhaXuatBan nhaXuatBan : nhaXuatBans) {
			cbbNhaXBorXuatXu.addItem(nhaXuatBan.getTenNXB());
		}
		// ncc
		nhaCungCaps = new ArrayList<NhaCungCap>();
		try {
			nhaCungCaps = nhaCungCapServiceImpl.getListNhaCungCap("Sách");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbNhaCungCap.removeAllItems();
		cbbNhaCungCap.addItem("Tất cả nhà cung cấp");
		for (NhaCungCap nhaCungCap : nhaCungCaps) {
			cbbNhaCungCap.addItem(nhaCungCap.getTenNCC());
		}
	}

	public void setRadVPP() {
		// the loai
		theLoaiVanPhongPhams = new ArrayList<TheLoaiVanPhongPham>();
		try {
			theLoaiVanPhongPhams = theLoaiServiceImpl.getListTheLoaiVanPhongPham();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbLoai.removeAllItems();
		cbbLoai.addItem("Tất cả thể loại");
		for (TheLoaiVanPhongPham theLoaiVanPhongPham : theLoaiVanPhongPhams) {
			cbbLoai.addItem(theLoaiVanPhongPham.getTenLoai());
		}
		// chat lieu
		chatLieus = new ArrayList<ChatLieu>();
		try {
			chatLieus = chatLieuServiceImpl.getListChatLieu();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbTacGiaorChatLieu.removeAllItems();
		cbbTacGiaorChatLieu.addItem("Tất cả chất liệu");
		for (ChatLieu chatLieu : chatLieus) {
			cbbTacGiaorChatLieu.addItem(chatLieu.getTenChatLieu());
		}
		// Xuat xu
		xuatXus = new ArrayList<XuatXu>();
		try {
			xuatXus = xuatXuServiceImpl.getListXuatXu();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbNhaXBorXuatXu.removeAllItems();
		cbbNhaXBorXuatXu.addItem("Tất cả xuất xứ");
		for (XuatXu xuatXu : xuatXus) {
			cbbNhaXBorXuatXu.addItem(xuatXu.getTenXuatXu());
		}
		// ncc
		nhaCungCaps = new ArrayList<NhaCungCap>();
		try {
			nhaCungCaps = nhaCungCapServiceImpl.getListNhaCungCap("Văn phòng phẩm");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbNhaCungCap.removeAllItems();
		cbbNhaCungCap.addItem("Tất cả nhà cung cấp");
		for (NhaCungCap nhaCungCap : nhaCungCaps) {
			cbbNhaCungCap.addItem(nhaCungCap.getTenNCC());
		}
	}

	public void setColumnName(String soTrangMauSac, String tacGiaOrChatLieu, String nXBOrXuatXu) {
		JTableHeader HEADER = tableSanPham.getTableHeader();
		TableColumnModel TMC = HEADER.getColumnModel();
		TableColumn TC = TMC.getColumn(6);
		TC.setHeaderValue(soTrangMauSac);
		TableColumn TC1 = TMC.getColumn(7);
		TC1.setHeaderValue(tacGiaOrChatLieu);
		TableColumn TC2 = TMC.getColumn(8);
		TC2.setHeaderValue(nXBOrXuatXu);
		HEADER.repaint();
		tableSanPham.getTableHeader().repaint();
	}

	public void setHienThi(int row) {
		lblTenSP.setText("Tên sản phẩm: " + tableSanPham.getValueAt(row, 1).toString());
		lblSoLuong.setText("Số lượng: " + tableSanPham.getValueAt(row, 2).toString());
		lblGiaNhap.setText("Giá nhập: " + tableSanPham.getValueAt(row, 3).toString() + " VNĐ");
		lblGiaBan.setText("Giá bán: " + tableSanPham.getValueAt(row, 4).toString() + " VNĐ");
		lblNhaCungCap.setText("Nhà cung cấp: " + tableSanPham.getValueAt(row, 5).toString());
		String hinhAnh = "..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg";
		if (radSach.isSelected()) {
			hinhAnh = "..\\HieuSachTuNhan\\hinhAnhHieuSach\\" + listSach.get(row).getHinhAnh();
		} else {
			hinhAnh = "..\\HieuSachTuNhan\\hinhAnhHieuSach\\" + listVPP.get(row).getHinhAnh();
		}
		if (!new File(hinhAnh).exists()) {
			hinhAnh = "..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg";
		}
		lblImageSP.setIcon(setSizeImageIconString(hinhAnh, lblImageSP.getWidth(), lblImageSP.getHeight()));
		if (radSach.isSelected())
			lblTacGiaOrChatLieu.setText("Tác giả: " + tableSanPham.getValueAt(row, 7).toString());
		else
			lblTacGiaOrChatLieu.setText("Chất liệu: " + tableSanPham.getValueAt(row, 7).toString());

	}

	public void setHienThiRong() {
		lblSoLuongHienThi.setText("Hiển thị 0 sản phẩm");
		lblTenSP.setText("Tên sản phẩm: ");
		lblSoLuong.setText("Số lượng: ");
		lblGiaNhap.setText("Giá nhập: ");
		lblGiaBan.setText("Giá bán: ");
		lblNhaCungCap.setText("Nhà cung cấp: ");
		lblImageSP.setIcon(setSizeImageIconString("..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg",
				lblImageSP.getWidth(), lblImageSP.getHeight()));
		if (radSach.isSelected())
			lblTacGiaOrChatLieu.setText("Tác giả: ");
		else
			lblTacGiaOrChatLieu.setText("Chất liệu: ");
	}

	public int importSach(String excelFilePath) throws IOException, SQLException {
		int i = 0;
		FileInputStream fis = new FileInputStream(excelFilePath);
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(fis);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(fis);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row row = iterator.next();
			if (row.getRowNum() == 0)
				continue;

			Iterator<Cell> cellIterator = row.iterator();
			Sach sach = new Sach();
			
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				Object cellValue = getCellValue(cell);
				String ma = null;
				String ten = null;
				if (cellValue == null || cellValue.toString().isEmpty())
					continue;
				
				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case soLuongTon:
					sach.setSoLuongTon(Integer.parseInt(getCellValue(cell).toString().split("\\.")[0]));
					break;
				case trongLuong:
					sach.setTrongLuong((double) getCellValue(cell));
					;
					break;
				case tenNhaCungCap:
					ten = getCellValue(cell).toString().trim();
					NhaCungCap n = nhaCungCapServiceImpl.timNhaCungCap(ten);
					if (n == null) {
						try {
							ArrayList<NhaCungCap> nhaCungCaps = new ArrayList<>();
							nhaCungCaps = nhaCungCapServiceImpl.getAllListNhaCungCap();
							ma = tangMa(nhaCungCaps.get(nhaCungCaps.size() - 1).getMaNCC(), 3);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							nhaCungCapServiceImpl.themNhaCungCap(new NhaCungCap(ma, ten));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						ma = n.getMaNCC();
					}
					sach.setNhaCungCap(new NhaCungCap(ma, ten));
					break;
				case giaNhap:
					sach.setGiaNhap(Long.parseLong(getCellValue(cell).toString().split("\\.")[0]));
					break;
				case ghiChu:
					sach.setGhiChu(getCellValue(cell).toString());
					break;
				case hinhAnh:
					if (getCellValue(cell).equals(null))
						sach.setHinhAnh("");
					else
						sach.setHinhAnh(getCellValue(cell).toString());
					break;
				case tenSach:
					sach.setTenSach(getCellValue(cell).toString());
					break;
				case tenTacGia:
					ten = getCellValue(cell).toString().trim();
					TacGia t = tacGiaServiceImpl.timTacGia(ten);
					if (t == null) {
						ma = tangMa(tacGias.get(tacGias.size() - 1).getMaTacGia(), 2);
						try {
							tacGiaServiceImpl.themTacGia(new TacGia(ma, ten));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						ma = t.getMaTacGia();
					}
					sach.setTacGia(new TacGia(ma, ten));
					break;
				case tenNhaXuatBan:
					ten = getCellValue(cell).toString().trim();
					NhaXuatBan nxb = nhaXuatBanServiceImpl.timNhaXuatBan(ten);
					if (nxb == null) {
						ma = tangMa(nhaXuatBans.get(nhaXuatBans.size() - 1).getMaNXB(), 3);
						try {
							nhaXuatBanServiceImpl.themNhaXuatBan(new NhaXuatBan(ma, ten));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						ma = nxb.getMaNXB();
					}
					sach.setNhaXuatBan(new NhaXuatBan(ma, ten));
					break;
				case namXB:
					sach.setNamXuatBan(Integer.parseInt(getCellValue(cell).toString().substring(0, 4)));
					break;
				case soTrang:
					sach.setSoTrang(Integer.parseInt(getCellValue(cell).toString().split("\\.")[0]));
					break;
				case tenTheLoai:
					ten = getCellValue(cell).toString().trim();
					TheLoaiSach tsach = theLoaiServiceImpl.timTheLoaiSach(ten);
					if (tsach == null) {
						ma = tangMa(theLoaiSachs.get(theLoaiSachs.size() - 1).getMaLoai(), 1);

						try {
							theLoaiServiceImpl.themTheLoaiSach(new TheLoaiSach(ma, ten));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						ma = tsach.getMaLoai();
					}
					sach.setTheLoaiSach(new TheLoaiSach(ma, ten));
					break;
				default:
					break;
				}
				sach.setMaSanPham(tangMaSP());
				sach.setLoaiSanPham("Sách");
				sach.setDonViSanPham("cuốn");
			}
			try {
				if (sanPhamServiceImpl.themSanPham(sach)==true) {
					i++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fis.close();

		return i;
	}

	public int importVPP(String excelFilePath) throws IOException, SQLException {
		int i = 0;
		FileInputStream fis = new FileInputStream(excelFilePath);
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(fis);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(fis);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			Row row = iterator.next();
			if (row.getRowNum() == 0)
				continue;

			Iterator<Cell> cellIterator = row.iterator();
			VanPhongPham vpp = new VanPhongPham();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				Object cellValue = getCellValue(cell);
				String ma = null;
				String ten = null;
				if (cellValue == null || cellValue.toString().isEmpty())
					continue;

				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case soLuongTonvpp:
					vpp.setSoLuongTon(Integer.parseInt(getCellValue(cell).toString().split("\\.")[0]));
					break;
				case trongLuongvpp:
					vpp.setTrongLuong((double) getCellValue(cell));
					;
					break;
				case tenNhaCungCapvpp:
					ten = getCellValue(cell).toString().trim();
					NhaCungCap n = nhaCungCapServiceImpl.timNhaCungCap(ten);
					if (n == null) {
						try {
							ArrayList<NhaCungCap> nhaCungCaps = new ArrayList<>();
							nhaCungCaps = nhaCungCapServiceImpl.getAllListNhaCungCap();
							ma = tangMa(nhaCungCaps.get(nhaCungCaps.size() - 1).getMaNCC(), 3);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							nhaCungCapServiceImpl.themNhaCungCap(new NhaCungCap(ma, ten));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						ma = n.getMaNCC();
					}
					vpp.setNhaCungCap(new NhaCungCap(ma, ten));
					break;
				case giaNhapvpp:
					vpp.setGiaNhap(Long.parseLong(getCellValue(cell).toString().split("\\.")[0]));
					break;
				case ghiChuvpp:
					vpp.setGhiChu(getCellValue(cell).toString());
					break;
				case hinhAnhvpp:
					if (getCellValue(cell).equals(null))
						vpp.setHinhAnh("");
					else
						vpp.setHinhAnh(getCellValue(cell).toString());
					break;
				case tenVanPhongPham:
					vpp.setTenVanPhongPham(getCellValue(cell).toString());
					break;
				case tenMauSac:
					ten = getCellValue(cell).toString().trim();
					MauSac mauSac = mauSacServiceImpl.timMauSac(ten);
					if (mauSac == null) {
						ArrayList<MauSac> mauSacs = null;
						try {
							mauSacs = mauSacServiceImpl.getListMauSac();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ma = tangMa(mauSacs.get(mauSacs.size() - 1).getMaMau(), 2);
						try {
							mauSacServiceImpl.themMauSac(new MauSac(ma, ten));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						ma = mauSac.getMaMau();
					}
					vpp.setMauSac(new MauSac(ma, ten));
					break;
				case tenChatLieu:
					ten = getCellValue(cell).toString().trim();
					ChatLieu chatLieu = chatLieuServiceImpl.timChatLieu(ten);
					if (chatLieu == null) {
						ma = tangMa(chatLieus.get(chatLieus.size() - 1).getMaChatLieu(), 2);
						try {
							chatLieuServiceImpl.themChatLieu(new ChatLieu(ma, ten));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						ma = chatLieu.getMaChatLieu();
					}
					vpp.setChatLieu(new ChatLieu(ma, ten));
					break;
				case tenLoaiVanPhongPham:
					ten = getCellValue(cell).toString().trim();
					TheLoaiVanPhongPham tvpp = theLoaiServiceImpl.timTheLoaiVanPhongPham(ten);
					if (tvpp == null) {
						ma = tangMa(theLoaiVanPhongPhams.get(theLoaiVanPhongPhams.size() - 1).getMaLoai(), 2);

						try {
							theLoaiServiceImpl.themTheLoaiVanPhongPham(new TheLoaiVanPhongPham(ma, ten));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						ma = tvpp.getMaLoai();
					}
					vpp.setLoaiVanPhongPham(new TheLoaiVanPhongPham(ma, ten));
					break;
				case tenXuatXu:
					ten = getCellValue(cell).toString().trim();
					XuatXu xuatXu = xuatXuServiceImpl.timXuatXu(ten);
					if (xuatXu == null) {
						ma = tangMa(xuatXus.get(xuatXus.size() - 1).getMaXuatXu(), 2);

						try {
							xuatXuServiceImpl.themXuatXu(new XuatXu(ma, ten));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						ma = xuatXu.getMaXuatXu();
					}
					vpp.setXuatXu(new XuatXu(ma, ten));
					break;
				default:
					break;
				}
				vpp.setMaSanPham(tangMaSP());
				vpp.setLoaiSanPham("Văn phòng phẩm");
				vpp.setDonViSanPham("cái");
			}
			try {
				if (sanPhamServiceImpl.themSanPham(vpp)==true) {
					i++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fis.close();

		return i;
	}

	public static void exportSach(List<Sach> listSach, String excelFilePath) throws IOException {
		// Create Workbook
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		// Create sheet
		Sheet sheet = workbook.createSheet("Books"); // Create sheet with sheet name

		int rowIndex = 0;

		// Write header
		Row row = sheet.createRow(rowIndex);
		// Create cells
		Cell cell = row.createCell(0);
		cell.setCellValue("mã sách");

		cell = row.createCell(tenSach + 1);
		cell.setCellValue("tên sách");

		cell = row.createCell(tenTacGia + 1);
		cell.setCellValue("tên tác giả");

		cell = row.createCell(tenNhaXuatBan + 1);
		cell.setCellValue("tên nhà xuất bản");

		cell = row.createCell(namXB + 1);
		cell.setCellValue("năm xuất bản");

		cell = row.createCell(soTrang + 1);
		cell.setCellValue("số trang");

		cell = row.createCell(tenTheLoai + 1);
		cell.setCellValue("tên thể loại");

		cell = row.createCell(soLuongTon + 1);
		cell.setCellValue("số lượng");

		cell = row.createCell(trongLuong + 1);
		cell.setCellValue("trọng lượng");

		cell = row.createCell(tenNhaCungCap + 1);
		cell.setCellValue("tên nhà cung cấp");

		cell = row.createCell(giaNhap + 1);
		cell.setCellValue("giá nhập");

		cell = row.createCell(ghiChu + 1);
		cell.setCellValue("ghi chú");

		cell = row.createCell(hinhAnh + 1);
		cell.setCellValue("hình ảnh");

		// Write data
		rowIndex++;
		JSONArray jsonarray = new JSONArray();
		for (Sach sach : listSach) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("item_id", sach.getMaSanPham());
			jsonObject.put("book_name", sach.getTenSach());
			jsonObject.put("author", sach.getTacGia().getTenTacGia());
			jsonObject.put("pages", sach.getSoTrang());
			jsonObject.put("price", sach.getGiaNhap());
			jsonObject.put("inventory", sach.getSoLuongTon());
			jsonarray.add(jsonObject);
			// Create row
			row = sheet.createRow(rowIndex);
			// Write data on row
			cell = row.createCell(0);
			cell.setCellValue(sach.getMaSanPham());

			cell = row.createCell(tenSach + 1);
			cell.setCellValue(sach.getTenSach());

			cell = row.createCell(tenTacGia + 1);
			cell.setCellValue(sach.getTacGia().getTenTacGia());

			cell = row.createCell(tenNhaXuatBan + 1);
			cell.setCellValue(sach.getNhaXuatBan().getTenNXB());

			cell = row.createCell(namXB + 1);
			cell.setCellValue(sach.getNamXuatBan());

			cell = row.createCell(soTrang + 1);
			cell.setCellValue(sach.getSoTrang());

			cell = row.createCell(tenTheLoai + 1);
			cell.setCellValue(sach.getTheLoaiSach().getTenLoai());

			cell = row.createCell(soLuongTon + 1);
			cell.setCellValue(sach.getSoLuongTon());

			cell = row.createCell(trongLuong + 1);
			cell.setCellValue(sach.getTrongLuong());

			cell = row.createCell(tenNhaCungCap + 1);
			cell.setCellValue(sach.getNhaCungCap().getTenNCC());

			cell = row.createCell(giaNhap + 1);
			cell.setCellValue(sach.getGiaNhap());

			cell = row.createCell(ghiChu + 1);
			cell.setCellValue(sach.getGhiChu());

			cell = row.createCell(hinhAnh + 1);
			cell.setCellValue(sach.getHinhAnh());

			rowIndex++;
		}
		// Auto resize column witdth
		int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
		autosizeColumn(sheet, numberOfColumn);

		// Create file excel
		try (OutputStream os = new FileOutputStream(excelFilePath)) {
			workbook.write(os);
		}
	}

	public static void exportVPP(List<VanPhongPham> listVpp, String excelFilePath) throws IOException {
		// Create Workbook
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		// Create sheet
		Sheet sheet = workbook.createSheet("VanPhongPham"); // Create sheet with sheet name

		int rowIndex = 0;

		// Write header
		Row row = sheet.createRow(rowIndex);
		// Create cells
		Cell cell = row.createCell(0);
		cell.setCellValue("Mã văn phòng phẩm");

		cell = row.createCell(tenVanPhongPham + 1);
		cell.setCellValue("Tên văn phòng phẩm");

		cell = row.createCell(tenLoaiVanPhongPham + 1);
		cell.setCellValue("Tên loại văn phòng phẩm");

		cell = row.createCell(tenMauSac + 1);
		cell.setCellValue("Màu sắc");

		cell = row.createCell(tenChatLieu + 1);
		cell.setCellValue("Chất liệu");

		cell = row.createCell(tenXuatXu + 1);
		cell.setCellValue("Xuất xứ");

		cell = row.createCell(soLuongTonvpp + 1);
		cell.setCellValue("Số lượng");

		cell = row.createCell(trongLuongvpp + 1);
		cell.setCellValue("Trọng lượng");

		cell = row.createCell(tenNhaCungCapvpp + 1);
		cell.setCellValue("Nhà cung cấp");

		cell = row.createCell(giaNhapvpp + 1);
		cell.setCellValue("Giá nhập");

		cell = row.createCell(ghiChuvpp + 1);
		cell.setCellValue("Ghi chú");

		cell = row.createCell(hinhAnhvpp + 1);
		cell.setCellValue("Hình ảnh");

		// Write data
		rowIndex++;
		for (VanPhongPham vanPhongPham : listVpp) {
			// Create row
			row = sheet.createRow(rowIndex);
			// Write data on row
			cell = row.createCell(0);
			cell.setCellValue(vanPhongPham.getMaSanPham());

			cell = row.createCell(tenVanPhongPham + 1);
			cell.setCellValue(vanPhongPham.getTenVanPhongPham());

			cell = row.createCell(tenLoaiVanPhongPham + 1);
			cell.setCellValue(vanPhongPham.getLoaiVanPhongPham().getTenLoai());

			cell = row.createCell(tenMauSac + 1);
			cell.setCellValue(vanPhongPham.getMauSac().getTenMau());

			cell = row.createCell(tenChatLieu + 1);
			cell.setCellValue(vanPhongPham.getChatLieu().getTenChatLieu());

			cell = row.createCell(tenXuatXu + 1);
			cell.setCellValue(vanPhongPham.getXuatXu().getTenXuatXu());

			cell = row.createCell(soLuongTonvpp + 1);
			cell.setCellValue(vanPhongPham.getSoLuongTon());

			cell = row.createCell(trongLuongvpp + 1);
			cell.setCellValue(vanPhongPham.getTrongLuong());

			cell = row.createCell(tenNhaCungCapvpp + 1);
			cell.setCellValue(vanPhongPham.getNhaCungCap().getTenNCC());

			cell = row.createCell(giaNhapvpp + 1);
			cell.setCellValue(vanPhongPham.getGiaNhap());

			cell = row.createCell(ghiChuvpp + 1);
			cell.setCellValue(vanPhongPham.getGhiChu());

			cell = row.createCell(hinhAnhvpp + 1);
			cell.setCellValue(vanPhongPham.getHinhAnh());

			rowIndex++;
		}

		// Auto resize column witdth
		int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
		autosizeColumn(sheet, numberOfColumn);

		// Create file excel
		try (OutputStream os = new FileOutputStream(excelFilePath)) {
			workbook.write(os);
		}
	}

	private static void autosizeColumn(Sheet sheet, int lastColumn) {
		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}

	private static Object getCellValue(Cell cell) {
		CellType cellType = cell.getCellType();
		Object cellValue = null;
		switch (cellType) {
		case NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		default:
			cellValue = null;
			break;
		}

		return cellValue;
	}

	public String tangMa(String maHT, int indexLastAlphabet) {
		String s = maHT;
		int n = Integer.parseInt(s.substring(indexLastAlphabet));
		if ((n + 1) % 10 == 0) {
			s = s.replaceAll("0" + n + "", n + 1 + "");
		} else
			s = s.replaceAll(n + "", n + 1 + "");
		return s;
	}

	public String tangMaSP() throws SQLException {
		String s = sanPhamServiceImpl.getMaSPMax();
		int n = Integer.parseInt(s.substring(2));
		if ((n + 1) % 10 == 0) {
			s = s.replaceAll("0" + n + "", n + 1 + "");
		} else
			s = s.replaceAll(n + "", n + 1 + "");
		return s;
	}
}
