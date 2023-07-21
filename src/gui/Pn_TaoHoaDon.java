package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.JobAttributes;
import java.awt.Window;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicViewportUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import dao.ChiTietHoaDonDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.SanPhamDao;
import dao.TacGiaDao;
import dao.TheLoaiDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Sach;
import entity.SanPham;
import entity.TacGia;
import entity.TaiKhoan;
import entity.TheLoaiSach;
import entity.VanPhongPham;
import service.impl.ChiTietHoaDonServiceImpl;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.SanPhamServiceImpl;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

public class Pn_TaoHoaDon extends JPanel implements ActionListener, MouseListener {
	private JTextField txtSDT;
	private JTextField txtTenKhachHang;
	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTable tableSach;
	private JTextField txtGiaBanSach;
	private JTextField txtSoLuongSach;
	private JTextField txtTenVPP;
	private JTextField txtGiaBanVPP;
	private JComboBox<Object> cbxXuatXu;
	private JComboBox<Object> cbxChatLieu;
	private JTabbedPane tabSanPham;
	private JComboBox<Object> cbxTheLoai;
	private JComboBox<Object> cbxTacGia;
	private JButton btnThemKhachHang;
	private JButton btnRefresh;
	private JButton btnTimKhachHang;
	private JTable table;
	private JTable tableHoaDon;
	private JTextField txtMaHoaDon;
	private JTextField txtTongTienHD;
	private JTextField txtVAT;
	private JTextField txtNhanVien;
	private JTextField txtTienKhachDua;
	private JTextField txtTienThua;
	private JButton btnHuyHoaDon;
	private JButton btnThemHangCho;
	private JButton btnThanhToan;
	private JButton btnHangCho;
	private JButton btnXoa;
	private DefaultTableModel modelSach;
	private JScrollPane scrollSach;
	private JLabel lblSoLuongVPP;
	private JTextField txtSoLuongVPP;
	private JButton btnThemVPP;
	private JTable tableVPP;
	private DefaultTableModel modelVPP;
	private JScrollPane scrollVPP;
	private SanPhamDao sanPhamDao;
	private SanPhamServiceImpl iSanPham;
	private List<Sach> dsSach;
	private TheLoaiDao theLoaiSach;
	private List<VanPhongPham> dsVPP;
	private JTextField txtMaVPP;
	private DefaultTableModel modelHoaDon;
	private JScrollPane scrollHoaDon;
	private NhanVienServiceImpl iNhanvien;
	private NhanVien nv;
	private JButton btnThemSach;
	private KhachHangServiceImpl iKhachHang;
	private String sdt;
	private JButton btnLamMoiBang;
	private ChiTietHoaDonDao chiTietHoaDonDao;
	private List<ChiTietHoaDon> dscthd;
	private HoaDonServiceImpl iHoaDon;
	private ChiTietHoaDonServiceImpl iChiTietHoaDon;
	private TacGiaDao tacgiaDao;
	private List<TacGia> listTacGia;
	private static HashMap<String, ArrayList<SanPham>> listHoaDonCho = new HashMap<String, ArrayList<SanPham>>();
	Frm_HangCho frm_HangCho;
	SanPhamServiceImpl sanPhamServiceImpl = new SanPhamServiceImpl();
	private JLabel lblMaHD;
	private JTextField txtTimKiemMaSP;
	private JTextField txtTimKiemTenSP;
	private JTextField txtXoa;

	/**
	 * Create the panel.
	 */

	public Pn_TaoHoaDon() {
		setBackground(new Color(0, 206, 209));
		setFont(new Font("Dialog", Font.BOLD, 16));
		setSize(1493, 650);
		setLayout(null);

		JPanel pnlMaHD = new JPanel();
		pnlMaHD.setBounds(10, 40, 191, 30);
		add(pnlMaHD);
		pnlMaHD.setLayout(null);

		JLabel lblMa = new JLabel("MÃ HOÁ ĐƠN:");
		lblMa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMa.setHorizontalAlignment(SwingConstants.CENTER);
		lblMa.setBounds(10, 0, 92, 30);
		pnlMaHD.add(lblMa);

		lblMaHD = new JLabel("");
		lblMaHD.setText(auto_ID());
		lblMaHD.setBounds(99, 0, 92, 30);
		pnlMaHD.add(lblMaHD);
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMaHD.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaHD.setForeground(new Color(255, 0, 0));

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBounds(0, 0, 1493, 30);
		add(pnlTitle);
		pnlTitle.setLayout(null);

		JLabel lblTitle = new JLabel("LẬP HOÁ ĐƠN");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 1493, 30);
		pnlTitle.add(lblTitle);

		tableSach = new JTable(modelSach);
		tableSach.setBounds(10, 307, 780, 316);
		String[] cols = { "STT", "Mã sách", "Tên sách", "Thể loại", "Tác giả", "Giá bán", "Số lượng" };
		modelSach = new DefaultTableModel(cols, 0);
		tableSach.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableSach.setFont(new Font("Tahoma", Font.PLAIN, 12));

		scrollSach = new JScrollPane();
		scrollSach.setViewportView(tableSach = new JTable(modelSach));
		scrollSach.setBounds(10, 322, 820, 317);
		tableSach.getTableHeader().setBackground(Color.LIGHT_GRAY);
		tableSach.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableSach.setRowHeight(25);
		tableSach.setBackground(Color.WHITE);
		scrollSach.getViewport().setBackground(Color.WHITE);
		tableSach.getTableHeader().setPreferredSize(new Dimension(0, 20));
		add(scrollSach);
		tableSach.addMouseListener(this);
		String[] headers = { "STT", "Mã Văn phòng phẩm", "Tên Văn phòng phẩm", "Chất liệu", "Xuất xứ", "Giá bán",
				"Số lượng" };
		modelVPP = new DefaultTableModel(headers, 0);

		scrollVPP = new JScrollPane();
		scrollVPP.setViewportView(tableVPP = new JTable(modelVPP));
		scrollVPP.setBounds(10, 322, 820, 317);
		tableVPP.getTableHeader().setBackground(Color.LIGHT_GRAY);
		tableVPP.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableVPP.setRowHeight(25);
		tableVPP.setBackground(Color.WHITE);
		scrollVPP.getViewport().setBackground(Color.WHITE);
		tableVPP.getTableHeader().setPreferredSize(new Dimension(0, 20));
		add(scrollVPP);

		JPanel pnlHoaDon = new JPanel();
		pnlHoaDon.setBounds(840, 40, 632, 599);
		add(pnlHoaDon);
		pnlHoaDon.setLayout(null);

		JLabel lblTitleHoaDon = new JLabel("HOÁ ĐƠN BÁN HÀNG");
		lblTitleHoaDon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitleHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleHoaDon.setBounds(0, 0, 632, 35);
		pnlHoaDon.add(lblTitleHoaDon);

		JLabel lblNgayLap = new JLabel("Ngày lập");
		lblNgayLap.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgayLap.setBounds(286, 46, 90, 20);
		pnlHoaDon.add(lblNgayLap);

		JLabel lblNgayThang = new JLabel("");
		lblNgayThang.setText(auto_Date());
		lblNgayThang.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgayThang.setForeground(new Color(255, 0, 51));
		lblNgayThang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNgayThang.setBounds(386, 46, 107, 20);
		pnlHoaDon.add(lblNgayThang);
//		pnlHoaDon.add(table);

		txtSDT = new JTextField();
		txtSDT.setBounds(110, 110, 150, 20);
		pnlHoaDon.add(txtSDT);
		txtSDT.setColumns(10);

		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setBounds(10, 110, 90, 20);
		pnlHoaDon.add(lblSDT);
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setEditable(false);
		txtTenKhachHang.setBounds(120, 150, 197, 20);
		pnlHoaDon.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);

		JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
		lblTenKhachHang.setBounds(10, 150, 110, 20);
		pnlHoaDon.add(lblTenKhachHang);
		lblTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnThemKhachHang = new JButton("Thêm khách hàng");
		btnThemKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemKhachHang.setBounds(420, 105, 202, 30);
		pnlHoaDon.add(btnThemKhachHang);
		btnThemKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnThemKhachHang.setIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/add-user.png")));

		btnRefresh = new JButton("");
		btnRefresh.setBounds(270, 105, 30, 30);
		pnlHoaDon.add(btnRefresh);
		btnRefresh.setIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/refresh-button.png")));
		btnRefresh.setSelectedIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/refresh-button.png")));

		btnTimKhachHang = new JButton("Tìm");
		btnTimKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTimKhachHang.setBounds(310, 105, 100, 30);
		pnlHoaDon.add(btnTimKhachHang);
		btnTimKhachHang.setIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/loupe.png")));

		JLabel lblMaHoaDon = new JLabel("Mã hoá đơn:");
		lblMaHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMaHoaDon.setBounds(10, 75, 90, 20);
		pnlHoaDon.add(lblMaHoaDon);

		txtMaHoaDon = new JTextField();
		txtMaHoaDon.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtMaHoaDon.setText(auto_ID());
		txtMaHoaDon.setEditable(false);
		txtMaHoaDon.setForeground(Color.RED);
		txtMaHoaDon.setBounds(110, 75, 150, 20);
		pnlHoaDon.add(txtMaHoaDon);
		txtMaHoaDon.setColumns(10);

		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongTien.setBounds(10, 475, 75, 20);
		pnlHoaDon.add(lblTongTien);

		txtTongTienHD = new JTextField();
		txtTongTienHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTongTienHD.setEditable(false);
		txtTongTienHD.setBounds(130, 475, 130, 20);
		pnlHoaDon.add(txtTongTienHD);
		txtTongTienHD.setColumns(10);

		JLabel lblThanhTienHD = new JLabel("Thành tiền (10% VAT):");
		lblThanhTienHD.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThanhTienHD.setBounds(292, 475, 150, 20);
		pnlHoaDon.add(lblThanhTienHD);

		txtVAT = new JTextField();
		txtVAT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtVAT.setEditable(false);
		txtVAT.setColumns(10);
		txtVAT.setBounds(452, 475, 150, 20);
		pnlHoaDon.add(txtVAT);

		JLabel lblTienKhachDua = new JLabel("Tiền khách đưa:");
		lblTienKhachDua.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTienKhachDua.setBounds(10, 505, 110, 20);
		pnlHoaDon.add(lblTienKhachDua);

		JLabel lblNhanVien = new JLabel("Nhân viên:");
		lblNhanVien.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhanVien.setBounds(290, 75, 90, 20);
		pnlHoaDon.add(lblNhanVien);

		txtNhanVien = new JTextField();
		txtNhanVien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtNhanVien.setEditable(false);
		txtNhanVien.setForeground(Color.RED);
		txtNhanVien.setColumns(10);
		txtNhanVien.setBounds(396, 75, 226, 20);

		FrmLogin dangNhap = new FrmLogin();
		TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
		// System.out.println(taiKhoan);
		iNhanvien = new NhanVienServiceImpl();
		nv = new NhanVien();
		try {
			nv = iNhanvien.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		txtNhanVien.setText(nv.getHoTenNhanVien());
		pnlHoaDon.add(txtNhanVien);

		txtTienKhachDua = new JTextField();
		txtTienKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTienKhachDua.setColumns(10);
		txtTienKhachDua.setBounds(130, 505, 130, 20);
		pnlHoaDon.add(txtTienKhachDua);
		txtTienKhachDua.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(!(txtTienKhachDua.getText().matches("[0-9]*"))) {
					JOptionPane.showMessageDialog(null, "Không được nhập chữ");
					txtTienKhachDua.setText("");
					txtTienThua.setText("");
					txtTienKhachDua.requestFocus();
					
					return;
				}else {
					tinhTienThua();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JLabel lblTienThua = new JLabel("Tiền thừa:");
		lblTienThua.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTienThua.setBounds(292, 505, 110, 20);
		pnlHoaDon.add(lblTienThua);

		txtTienThua = new JTextField();
		txtTienThua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTienThua.setEditable(false);
		txtTienThua.setColumns(10);
		txtTienThua.setBounds(452, 506, 150, 20);
		pnlHoaDon.add(txtTienThua);

		btnHuyHoaDon = new JButton("Huỷ hoá đơn");
		btnHuyHoaDon.setIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/delete.png")));
		btnHuyHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHuyHoaDon.setBounds(10, 551, 150, 35);
		pnlHoaDon.add(btnHuyHoaDon);

		btnThemHangCho = new JButton("Thêm hàng chờ");
		btnThemHangCho.setIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/icons-add.png")));
		btnThemHangCho.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemHangCho.setBounds(235, 551, 170, 35);
		pnlHoaDon.add(btnThemHangCho);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/money.png")));
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThanhToan.setBounds(472, 551, 150, 35);
		pnlHoaDon.add(btnThanhToan);

		btnHangCho = new JButton("Hàng chờ");
		btnHangCho.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHangCho.setBounds(327, 150, 103, 30);
		pnlHoaDon.add(btnHangCho);

		btnXoa = new JButton("Xoá");
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoa.setBounds(440, 150, 89, 30);
		pnlHoaDon.add(btnXoa);

		scrollHoaDon = new JScrollPane();
		scrollHoaDon.setBounds(0, 191, 632, 264);
		pnlHoaDon.add(scrollHoaDon);
		String[] header = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Số lượng" };
		modelHoaDon = new DefaultTableModel(header, 0);
		scrollHoaDon.setViewportView(tableHoaDon = new JTable(modelHoaDon));

		txtXoa = new JTextField();
		txtXoa.setBounds(539, 150, 83, 30);
		pnlHoaDon.add(txtXoa);
		txtXoa.setColumns(10);
		tableHoaDon.getTableHeader().setBackground(Color.LIGHT_GRAY);
		tableHoaDon.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableHoaDon.setRowHeight(25);
		tableHoaDon.setBackground(Color.WHITE);
		scrollHoaDon.getViewport().setBackground(Color.WHITE);
		tableHoaDon.getTableHeader().setPreferredSize(new Dimension(0, 20));
		btnTimKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		tabSanPham = new JTabbedPane(JTabbedPane.TOP);
		tabSanPham.setBounds(10, 77, 820, 178);
		add(tabSanPham);

		JPanel pnSach = new JPanel();
		tabSanPham.addTab("Sách", null, pnSach, null);
		pnSach.setLayout(null);
		System.out.println(tabSanPham.getSelectedIndex());
		
		tabSanPham.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (tabSanPham.getSelectedIndex() == 0) {
					scrollSach.setVisible(true);
					scrollVPP.setVisible(false);
					
				} else {
					scrollSach.setVisible(false);
					scrollVPP.setVisible(true);
					txtTimKiemMaSP.addKeyListener(new KeyListener() {
						
						@Override
						public void keyTyped(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void keyReleased(KeyEvent e) {
							// TODO Auto-generated method stub
							tableDanhSachVPPWithFilter();
						}
						
						@Override
						public void keyPressed(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
					txtTimKiemTenSP.addKeyListener(new KeyListener() {
						
						@Override
						public void keyTyped(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void keyReleased(KeyEvent e) {
							// TODO Auto-generated method stub
							tableDanhSachVPPWithFilter();
						}
						
						@Override
						public void keyPressed(KeyEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			}
		});

		JLabel lblMaSach = new JLabel("Mã sách");
		lblMaSach.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaSach.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaSach.setBounds(10, 10, 75, 20);
		pnSach.add(lblMaSach);

		txtMaSach = new JTextField();
		txtMaSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaSach.setBounds(170, 10, 250, 25);
		pnSach.add(txtMaSach);
		txtMaSach.setColumns(10);

		JLabel lblTenSach = new JLabel("Tên sách");
		lblTenSach.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenSach.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenSach.setBounds(465, 10, 75, 20);
		pnSach.add(lblTenSach);

		txtTenSach = new JTextField();
		txtTenSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(550, 10, 250, 25);
		pnSach.add(txtTenSach);

		JLabel lblTacGia = new JLabel("Tác giả");
		lblTacGia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTacGia.setHorizontalAlignment(SwingConstants.LEFT);
		lblTacGia.setBounds(465, 60, 75, 20);
		pnSach.add(lblTacGia);

		cbxTacGia = new JComboBox();
		cbxTacGia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tacgiaDao = new TacGiaDao();
		try {
			listTacGia = tacgiaDao.getListTacGia();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		cbxTacGia.addItem("Tất cả");
		for (TacGia tg : listTacGia) {
			cbxTacGia.addItem(tg.getTenTacGia());
		}
		cbxTacGia.setEditable(true);
		cbxTacGia.setBounds(550, 60, 250, 25);
		pnSach.add(cbxTacGia);

		JLabel lblTheLoai = new JLabel("Thể loại");
		lblTheLoai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTheLoai.setHorizontalAlignment(SwingConstants.LEFT);
		lblTheLoai.setBounds(10, 60, 75, 20);
		pnSach.add(lblTheLoai);

		JLabel lblGiaBanSach = new JLabel("Giá bán");
		lblGiaBanSach.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaBanSach.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGiaBanSach.setBounds(10, 110, 75, 20);
		pnSach.add(lblGiaBanSach);

		txtGiaBanSach = new JTextField();
		txtGiaBanSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtGiaBanSach.setEditable(false);
		txtGiaBanSach.setColumns(10);
		txtGiaBanSach.setBounds(170, 110, 250, 25);
		pnSach.add(txtGiaBanSach);

		cbxTheLoai = new JComboBox();
		cbxTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbxTheLoai.setModel(new DefaultComboBoxModel(
				new String[] { "Tất cả", "SGK", "Truyện", "Tiểu thuyết", "Kĩ năng sống", "Kinh doanh", "Thiếu nhi" }));
		cbxTheLoai.setEditable(true);
		cbxTheLoai.setBounds(170, 60, 250, 25);
		pnSach.add(cbxTheLoai);

		btnThemSach = new JButton("Thêm");
		btnThemSach.setIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/icons-add.png")));
		btnThemSach.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemSach.setBounds(641, 108, 126, 30);
		pnSach.add(btnThemSach);

		txtSoLuongSach = new JTextField("1");
		txtSoLuongSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSoLuongSach.setBounds(550, 110, 69, 25);
		pnSach.add(txtSoLuongSach);
		txtSoLuongSach.setColumns(10);
		txtSoLuongSach.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtSoLuongSach.setText("");
			}
		});

		JLabel lblSoLuongSach = new JLabel("Số lượng");
		lblSoLuongSach.setBounds(465, 105, 77, 25);
		pnSach.add(lblSoLuongSach);
		lblSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSoLuongSach.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel pnVPP = new JPanel();
		tabSanPham.addTab("Văn phòng phẩm", null, pnVPP, null);
		pnVPP.setLayout(null);

		JLabel lblMaVPP = new JLabel("Mã văn phòng phẩm");
		lblMaVPP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaVPP.setBounds(10, 10, 150, 25);
		pnVPP.add(lblMaVPP);

		JLabel lblTenVPP = new JLabel("Tên văn phòng phẩm");
		lblTenVPP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenVPP.setBounds(10, 60, 150, 25);
		pnVPP.add(lblTenVPP);

		txtTenVPP = new JTextField();
		txtTenVPP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTenVPP.setBounds(170, 60, 250, 25);
		pnVPP.add(txtTenVPP);
		txtTenVPP.setColumns(10);

		JLabel lblChatLieu = new JLabel("Chất liệu");
		lblChatLieu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChatLieu.setBounds(465, 10, 72, 25);
		pnVPP.add(lblChatLieu);

		cbxChatLieu = new JComboBox();
		cbxChatLieu.setModel(new DefaultComboBoxModel(new String[] { "Nhựa", "Giấy", "Tẩy", "Màu", "Vải" }));
		cbxChatLieu.setEditable(true);
		cbxChatLieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxChatLieu.setBounds(550, 10, 254, 25);
		pnVPP.add(cbxChatLieu);

		JLabel lblXuatXu = new JLabel("Xuất xứ");
		lblXuatXu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblXuatXu.setBounds(465, 60, 72, 25);
		pnVPP.add(lblXuatXu);

		cbxXuatXu = new JComboBox<Object>();
		cbxXuatXu.setModel(new DefaultComboBoxModel(new String[] { "Việt Nam", "Hoa Kì", "Trung Quốc" }));
		cbxXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbxXuatXu.setEditable(true);
		cbxXuatXu.setBounds(550, 60, 254, 25);
		pnVPP.add(cbxXuatXu);

		JLabel lblGiaBanVPP = new JLabel("Giá bán");
		lblGiaBanVPP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGiaBanVPP.setBounds(10, 110, 150, 25);
		pnVPP.add(lblGiaBanVPP);

		txtGiaBanVPP = new JTextField();
		txtGiaBanVPP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtGiaBanVPP.setEditable(false);
		txtGiaBanVPP.setColumns(10);
		txtGiaBanVPP.setBounds(170, 110, 250, 25);
		pnVPP.add(txtGiaBanVPP);

		txtMaVPP = new JTextField();
		txtMaVPP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaVPP.setColumns(10);
		txtMaVPP.setBounds(170, 10, 250, 25);
		pnVPP.add(txtMaVPP);

		lblSoLuongVPP = new JLabel("Số lượng");
		lblSoLuongVPP.setBounds(465, 110, 77, 25);
		pnVPP.add(lblSoLuongVPP);
		lblSoLuongVPP.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoLuongVPP.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtSoLuongVPP = new JTextField("1");
		txtSoLuongVPP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSoLuongVPP.setBounds(550, 110, 69, 25);
		pnVPP.add(txtSoLuongVPP);
		txtSoLuongVPP.setColumns(10);
		txtSoLuongVPP.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtSoLuongVPP.setText("");
			}
		});

		btnThemVPP = new JButton("Thêm");
		btnThemVPP.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemVPP.setIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/icons-add.png")));
		btnThemVPP.setBounds(641, 108, 126, 30);
		pnVPP.add(btnThemVPP);

		tableSach.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableSach.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableSach.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableSach.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableSach.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableSach.getColumnModel().getColumn(5).setPreferredWidth(40);

		tableVPP.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableVPP.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableVPP.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableVPP.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableVPP.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableVPP.getColumnModel().getColumn(5).setPreferredWidth(40);
		tableVPP.getColumnModel().getColumn(5).setPreferredWidth(40);
		tableVPP.addMouseListener(this);

		try {
			DocDuLieuSach();
			DocDuLieuVPP();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnThemSach.addActionListener(this);
		btnHangCho.addActionListener(this);
		btnHuyHoaDon.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnThemHangCho.addActionListener(this);
		btnThemKhachHang.addActionListener(this);
		btnThemVPP.addActionListener(this);
		btnTimKhachHang.addActionListener(this);
		btnXoa.addActionListener(this);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(
				new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTimKiem.setBounds(10, 266, 820, 45);
		add(pnlTimKiem);
		pnlTimKiem.setLayout(null);

		JLabel lblTimKiemMaSP = new JLabel("Mã sản phẩm:");
		lblTimKiemMaSP.setBounds(90, 11, 115, 25);
		lblTimKiemMaSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimKiemMaSP.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlTimKiem.add(lblTimKiemMaSP);

		txtTimKiemMaSP = new JTextField();
		txtTimKiemMaSP.setBounds(210, 11, 130, 25);
		pnlTimKiem.add(txtTimKiemMaSP);
		txtTimKiemMaSP.setColumns(10);

		JLabel lblTImKiemTenSP = new JLabel("Tên sản phẩm:");
		lblTImKiemTenSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblTImKiemTenSP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTImKiemTenSP.setBounds(375, 11, 115, 25);
		pnlTimKiem.add(lblTImKiemTenSP);

		txtTimKiemTenSP = new JTextField();
		txtTimKiemTenSP.setBounds(500, 11, 130, 25);
		pnlTimKiem.add(txtTimKiemTenSP);
		txtTimKiemTenSP.setColumns(10);

		btnLamMoiBang = new JButton("Làm mới bảng");
		btnLamMoiBang.setIcon(new ImageIcon(Pn_TaoHoaDon.class.getResource("/gui/icon/refresh-button.png")));
		btnLamMoiBang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoiBang.setBounds(640, 9, 170, 30);
		pnlTimKiem.add(btnLamMoiBang);
		btnLamMoiBang.addActionListener(this);
		if(tabSanPham.getSelectedIndex()==0) {
			txtTimKiemMaSP.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					try {
						tableDanhSachSachWithFilter();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			txtTimKiemTenSP.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					try {
						tableDanhSachSachWithFilter();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
			});	
		}
		txtMaSach.setEditable(false);
		txtTenSach.setEditable(false);
		cbxTheLoai.setEditable(false);
		cbxTacGia.setEditable(false);
		txtMaVPP.setEditable(false);
		txtTenVPP.setEditable(false);
		cbxChatLieu.setEditable(false);
		cbxXuatXu.setEditable(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(tableSach)) {
			int row = tableSach.getSelectedRow();
			modelSach = (DefaultTableModel) tableSach.getModel();
			txtMaSach.setText(modelSach.getValueAt(row, 1).toString());
			txtTenSach.setText(modelSach.getValueAt(row, 2).toString());
			cbxTheLoai.setSelectedItem(modelSach.getValueAt(row, 3).toString());
			cbxTacGia.setSelectedItem(modelSach.getValueAt(row, 4).toString());
			txtGiaBanSach.setText(modelSach.getValueAt(row, 5).toString());
		} else if (obj.equals(tableVPP)) {
			int row1 = tableVPP.getSelectedRow();
			txtMaVPP.setText(modelVPP.getValueAt(row1, 1).toString());
			txtTenVPP.setText(modelVPP.getValueAt(row1, 2).toString());
			cbxChatLieu.setSelectedItem(modelVPP.getValueAt(row1, 3).toString());
			cbxXuatXu.setSelectedItem(modelVPP.getValueAt(row1, 4).toString());
			txtGiaBanVPP.setText(modelVPP.getValueAt(row1, 5).toString());
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnRefresh)) {
			clearTxtFieldsSDT();
		} else if (obj.equals(btnTimKhachHang)) {
			iKhachHang = new KhachHangServiceImpl();
			KhachHang kh = new KhachHang();
			sdt = txtSDT.getText().toString();
			try {
				kh = iKhachHang.timKhachHangBangSDT(sdt);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			if(sdt.length()<=0) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng");
				return;
			}
			if (kh != null) {
				txtTenKhachHang.setText(kh.getHoTenKhachHang());
				return;
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng, vui lòng thêm khách hàng mới");
				Frm_ThemKH frm = new Frm_ThemKH();
				frm.setVisible(true);
				return;
			}
		} else if (obj.equals(btnLamMoiBang)) {

			if (tabSanPham.getSelectedIndex() == 0) {
				try {
					clearTableSach();
					DocDuLieuSach();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				clearTableVPP();
				DocDuLieuVPP();
			}

		} else if (obj.equals(btnThemSach)) {
			int row = tableSach.getSelectedRow();
			if(txtSoLuongSach.getText().isEmpty() || !(txtSoLuongSach.getText().matches("[0-9]*"))) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng cần thêm phù hợp");
				return;
			}
			
			if(tableSach.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần mua");
				return;
			}
			int soLuongThem = Integer.parseInt(txtSoLuongSach.getText());
			if((Integer.parseInt( modelSach.getValueAt(row, 6).toString()) - soLuongThem)<0){
				JOptionPane.showMessageDialog(this, "Số lượng sách trong kho không đủ, vui lòng chọn sản phẩm khác");
				return;
			}
			else {
				try {
					themSachVaoGioHang();
					truSLSachKhiThem();
					txtSoLuongSach.setText("1");
//					DocDuLieuHoaDon();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (obj.equals(btnThemVPP)) {
			int row = tableSach.getSelectedRow();
			if(txtSoLuongVPP.getText().isEmpty() || !(txtSoLuongVPP.getText().matches("[0-9]*"))) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng cần thêm phù hợp");
				return;
			}
			if(tableVPP.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần mua");
				return;
			}
			int soLuongThem = Integer.parseInt(txtSoLuongVPP.getText());
			if((Integer.parseInt( modelVPP.getValueAt(row, 6).toString()) - soLuongThem)<0){
				JOptionPane.showMessageDialog(this, "Số lượng văn phòng phẩm trong kho không đủ, vui lòng chọn sản phẩm khác");
				return;
			}
			else {
				try {
					themHDVPP();
					truSLVPPKhiThem();
					txtSoLuongVPP.setText("1");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		} else if (obj.equals(btnThanhToan)) {
			if (txtTenKhachHang.getText().length()<=0) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng để hoàn tất thanh toán");
				return;
			}
			if(txtTienThua.getText().length()<=0) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập tiền khách đưa");
				return;
			}
			if(Integer.parseInt(txtTienThua.getText())<0) {
				JOptionPane.showMessageDialog(this, "Số tiền nhận vào chưa đủ, vui lòng kiểm tra lại");
				return;
			} 
			else {
				try {
					themHoaDon();
					themCTHD();
					JOptionPane.showMessageDialog(this, "Thanh toán hoàn tất!");
					clearTableCTHD();
					clearTxtFieldsSDT();
					lblMaHD.setText(auto_ID());
					txtMaHoaDon.setText(auto_ID());
					clearTongTien();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (obj.equals(btnThemHangCho)) {
			// kiểm tra điều kiện
			if (txtTenKhachHang.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin khách hàng", "Báo lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (modelHoaDon.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// xử lý
			if (listHoaDonCho.containsKey(txtSDT.getText().trim())) {
				// đã có người dùng trong hàng chờ
				for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
					boolean kiemTraTrung = false;
					SanPham sanPham = new SanPham(modelHoaDon.getValueAt(i, 1).toString(),
							Integer.parseInt(modelHoaDon.getValueAt(i, 4).toString()));
					for (int j = 0; j < listHoaDonCho.get(txtSDT.getText().trim()).size(); j++) {
						// trùng sản phẩm -> số lượng tăng
						if (sanPham.getMaSanPham()
								.equals(listHoaDonCho.get(txtSDT.getText().trim()).get(j).getMaSanPham())) {
							int soLuong = listHoaDonCho.get(txtSDT.getText().trim()).get(j).getSoLuongTon();
							listHoaDonCho.get(txtSDT.getText().trim()).get(j)
									.setSoLuongTon(sanPham.getSoLuongTon() + soLuong);
							kiemTraTrung = true;
							break;
						}
					}
					if (!kiemTraTrung) {
						listHoaDonCho.get(txtSDT.getText().trim()).add(sanPham);
					}
				}
			} else {
				// chưa có người dùng trong hàng chờ
				ArrayList<SanPham> listSanPhamCho = new ArrayList<>();
				for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
					SanPham sanPham = new SanPham(modelHoaDon.getValueAt(i, 1).toString(),
							Integer.parseInt(modelHoaDon.getValueAt(i, 4).toString()));
					listSanPhamCho.add(sanPham);
				}
				listHoaDonCho.put(txtSDT.getText().trim(), listSanPhamCho);
			}
			clearTxtFieldsSDT();
			setTongTienRong();
			modelHoaDon.setRowCount(0);
			JOptionPane.showMessageDialog(null, "Thêm vào hàng chờ thành công!!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (obj.equals(btnHangCho)) {
			openFrmHoaDonCho();
		} else if (obj.equals(btnThemKhachHang)) {
			Frm_ThemKH frm = new Frm_ThemKH();
			frm.setVisible(true);
		} else if (obj.equals(btnXoa)) {
			int row = tableHoaDon.getSelectedRow();
//			if (tableHoaDon.getSelectedRow() == -1) {
//				JOptionPane.showMessageDialog(this, "Phải chọn sản phẩm cần xóa");
//				return;
//			}
			if(txtXoa.getText().length()<=0) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng xoá");
				return;
			}
			int soLuongXoa = Integer.parseInt(txtXoa.getText());
			if((Integer.parseInt( modelHoaDon.getValueAt(row, 4).toString()) - soLuongXoa)<=0){
				modelHoaDon.removeRow(row);
				return;
			}
			else {
				truSoLuongTrongGioHang();
				tongTienHandler();
				tongTienVAT();
				txtXoa.setText("1");
			}
				
		
		} else if(obj.equals(btnHuyHoaDon)) {
			clearTableCTHD();
			clearTxtFieldsSDT();
			setTongTienRong();
			try {
				DocDuLieuSach();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DocDuLieuVPP();
		}
	}

	public void DocDuLieuSach() throws Exception {
		clearTableSach();
		sanPhamDao = new SanPhamDao();
		theLoaiSach = new TheLoaiDao();
		dsSach = sanPhamDao.getAllSach();
		String tacGia = "";
		int i = 1;
		for (Sach sach : dsSach) {
			if (sach.getTacGia() == null) {
				tacGia = "Không có tác giả";
			} else {
				tacGia = sach.getTacGia().getTenTacGia();
			}
			modelSach.addRow(new Object[] { i++, sach.getMaSanPham(), sach.getTenSach(),
					sach.getTheLoaiSach().getTenLoai(), tacGia, sach.tinhGiaBan(), sach.getSoLuongTon() });
		}
	}

	public void DocDuLieuVPP() {
		clearTableVPP();
		sanPhamDao = new SanPhamDao();
		dsVPP = sanPhamDao.getAllVPP();
		int i = 1;
		for (VanPhongPham vpp : dsVPP) {
			modelVPP.addRow(new Object[] { i++, vpp.getMaSanPham(), vpp.getTenVanPhongPham(),
					vpp.getChatLieu().getTenChatLieu(), vpp.getXuatXu().getTenXuatXu(), vpp.tinhGiaBan(),
					vpp.getSoLuongTon() });
		}
	}

	public void DocDuLieuHoaDon() throws Exception {
		iHoaDon = new HoaDonServiceImpl();
		iChiTietHoaDon = new ChiTietHoaDonServiceImpl();
		iSanPham = new SanPhamServiceImpl();
		if (iHoaDon.getHoaDonTheoMa(txtMaHoaDon.getText()).size() == 0) {
			return;
		} else {
			HoaDon hd = iHoaDon.getHoaDonTheoMa(txtMaHoaDon.getText()).get(0);
			dscthd = iChiTietHoaDon.getCTHoaDonTheoMaHoaDon(txtMaHoaDon.getText());
			if (dscthd.size() == 0) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn");
				return;
			} else {
				int i = 1;
				for (ChiTietHoaDon cthd : dscthd) {
					if (iSanPham.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getTenSach() != null) {
						modelHoaDon.addRow(new Object[] { i++,
								iSanPham.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getMaSanPham(),
								iSanPham.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getTenSach(),
								cthd.getSoLuong(), cthd.getDonGia() });
					} else {
						JOptionPane.showMessageDialog(this, "Hóa đơn này không có sách để đổi");
						return;
					}
				}
				JOptionPane.showMessageDialog(this, "Đã tìm thấy");
			}
		}

	}

	public void truSLSachKhiDoi() {
		iSanPham = new SanPhamServiceImpl();
		Sach s = iSanPham.getSachTheoTen(txtTenSach.getText());
		s.setSoLuongTon(s.getSoLuongTon() - Integer.parseInt(txtSoLuongSach.getText().toString()));

		try {
			iSanPham.capNhatSanPham(s.getMaSanPham(), s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean tonTaiSanPhamTrongCTHD(SanPham sp) {
		int soLuongS = 0;
		int soLuongVPP = 0;
		if (tableHoaDon.getRowCount() < 1) {
			return false;
		}
		for (int i = 0; i < tableHoaDon.getRowCount(); i++) {
			if (tableHoaDon.getValueAt(i, 1).equals(sp.getMaSanPham())) {
				if (!(txtSoLuongSach.getText().trim().isEmpty())) {
					soLuongS = Integer.parseInt(txtSoLuongSach.getText());
				} else if (!(txtSoLuongVPP.getText().trim().isEmpty())) {
					soLuongVPP = Integer.parseInt(txtSoLuongVPP.getText());
				} else
					return false;

				int tongSP = Integer.parseInt(tableHoaDon.getValueAt(i, 4).toString()) + soLuongS + soLuongVPP;
				tableHoaDon.setValueAt(tongSP, i, 4);
				return true;
			}
		}
		return false;
	}

	public void truSLVPPKhiDoi() {
		iSanPham = new SanPhamServiceImpl();
		VanPhongPham v = iSanPham.getVPPTheoTen(txtTenVPP.getText());
		v.setSoLuongTon(v.getSoLuongTon() - Integer.parseInt(txtSoLuongVPP.getText().toString()));

		try {
			iSanPham.capNhatSanPham(v.getMaSanPham(), v);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void themHoaDon() throws SQLException {
		String mahd = txtMaHoaDon.getText();
		FrmLogin dangNhap = new FrmLogin();
		TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
		iNhanvien = new NhanVienServiceImpl();
		iKhachHang = new KhachHangServiceImpl();
		nv = iNhanvien.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
		KhachHang kh = iKhachHang.timKhachHangBangSDT(txtSDT.getText());
		String sdt = txtSDT.getText();
		LocalDate ngayLapHoaDon = LocalDate.now();
		String ghiChu = "Không";
		Long tienKhachDua = Long.parseLong(txtTienKhachDua.getText().trim());
		Boolean tinhTrang = true;
		HoaDon hd = new HoaDon(mahd, nv, kh, ngayLapHoaDon, ghiChu, tienKhachDua, tinhTrang);
		iHoaDon = new HoaDonServiceImpl();
		iHoaDon.themHoaDon(hd);

	}

	public void themCTHD() throws SQLException {
		iChiTietHoaDon = new ChiTietHoaDonServiceImpl();
		iHoaDon = new HoaDonServiceImpl();
		chiTietHoaDonDao = new ChiTietHoaDonDao();
		HoaDon hd = iHoaDon.getHoaDonTheoMa(txtMaHoaDon.getText()).get(0);
		List<ChiTietHoaDon> listCTHD = new ArrayList<ChiTietHoaDon>();
		for (int i = 0; i < tableHoaDon.getRowCount(); i++) {
			iSanPham = new SanPhamServiceImpl();
			String masp = tableHoaDon.getValueAt(i, 1).toString();
			SanPham sp = iSanPham.getSanPhamTheoMa(masp);
//			VanPhongPham v = iSanPham.getVPPTheoMaSP(maV);		
			int soLuong = Integer.parseInt(modelHoaDon.getValueAt(i, 4).toString());
			long giaBan = Long.parseLong(modelHoaDon.getValueAt(i, 3).toString());
			ChiTietHoaDon cthd = new ChiTietHoaDon(hd, sp, soLuong, giaBan);
			listCTHD.add(cthd);
			if (chiTietHoaDonDao.addChiTietHoaDon(cthd) == -1)
				return;
		}

	}

	public void themSachVaoGioHang() throws SQLException {
		int soLuongS = Integer.parseInt(txtSoLuongSach.getText());
		dscthd = new ArrayList<ChiTietHoaDon>();
		modelSach = (DefaultTableModel) tableSach.getModel();
		iSanPham = new SanPhamServiceImpl();
		int row = tableSach.getSelectedRow();
		String maS = modelSach.getValueAt(row, 1).toString();
		Sach s = iSanPham.getSachTheoMaSP(maS);
		if (s != null) {
			if (!tonTaiSanPhamTrongCTHD(s)) {
				modelHoaDon = (DefaultTableModel) tableHoaDon.getModel();
				modelHoaDon.addRow(new Object[] { modelHoaDon.getRowCount() + 1, s.getMaSanPham(), s.getTenSach(),
						s.tinhGiaBan(), soLuongS });
			}
		}
		tongTienHandler();
		tongTienVAT();
	}

	public void themHDVPP() throws SQLException {
		int soLuongV = Integer.parseInt(txtSoLuongVPP.getText());
		dscthd = new ArrayList<ChiTietHoaDon>();
		modelVPP = (DefaultTableModel) tableVPP.getModel();
		iSanPham = new SanPhamServiceImpl();
		int row = tableVPP.getSelectedRow();
		String maV = modelVPP.getValueAt(row, 1).toString();
		VanPhongPham v = iSanPham.getVPPTheoMaSP(maV);
		if (v != null) {
			if (!tonTaiSanPhamTrongCTHD(v)) {
				modelHoaDon = (DefaultTableModel) tableHoaDon.getModel();
				modelHoaDon.addRow(new Object[] { modelHoaDon.getRowCount() + 1, v.getMaSanPham(),
						v.getTenVanPhongPham(), v.tinhGiaBan(), soLuongV });
			}
		}
		tongTienHandler();
		tongTienVAT();
	}

	public void tongTienHandler() {
		long tongTien = 0;

		for (int i = 0; i < tableHoaDon.getRowCount(); i++) {
			long thanhTien = Long.parseLong(tableHoaDon.getValueAt(i, 3).toString())
					* Long.parseLong(tableHoaDon.getValueAt(i, 4).toString());
			tongTien += thanhTien;
		}

		txtTongTienHD.setText(tongTien + "");
	}

	public void tongTienVAT() {
		long thanhTienVAT = 0;
		thanhTienVAT = Long.parseLong(txtTongTienHD.getText().trim())
				+ Long.parseLong(txtTongTienHD.getText().trim()) * 1 / 10;

		txtVAT.setText(thanhTienVAT + "");

	}

	public void tinhTienThua() {
		long tienThua = 0;
		tienThua = Long.parseLong(txtTienKhachDua.getText().trim()) - Long.parseLong(txtVAT.getText().trim());
		txtTienThua.setText(tienThua + "");
	}

	public void clearTxtFieldsSach() {
		txtMaSach.setText("");
		txtTenSach.setText("");
		cbxTheLoai.setSelectedIndex(0);
		cbxTacGia.setSelectedIndex(0);
		txtGiaBanSach.setText("");
		txtSoLuongSach.setText("1");
	}

	public void clearTxtFieldsVPP() {
		txtMaVPP.setText("");
		txtTenVPP.setText("");
		cbxChatLieu.setSelectedIndex(0);
		cbxXuatXu.setSelectedIndex(0);
		txtGiaBanVPP.setText("");
		txtSoLuongVPP.setText("1");
	}

	public void clearTxtFieldsSDT() {
		txtSDT.setText("");
		txtTenKhachHang.setText("");
	}

	public void clearTongTien() {
		txtTongTienHD.setText("");
		txtVAT.setText("");
		txtTienKhachDua.setText("");
		txtTienThua.setText("");
	}

	public void tableDanhSachSachWithFilter() throws Exception {
//		tableSach.clearSelection();
		clearTableSach();
		DefaultTableModel dtm = (DefaultTableModel) tableSach.getModel();
		String maSach = txtTimKiemMaSP.getText().trim();
		String tenSach = txtTimKiemTenSP.getText().trim();
		
		List<Sach> listSach = sanPhamServiceImpl.getAllSach();
		String tacGia = "";
		int i = 0;
		
		for (Sach s : listSach) {
			if (s.getTacGia() == null) {
				tacGia = "Không có tác giả";
			} else {
				tacGia = s.getTacGia().getTenTacGia();
			}
			if (removeAccent(s.getMaSanPham()).toLowerCase().contains(removeAccent(maSach).toLowerCase())
					&& removeAccent(s.getTenSach()).toLowerCase().contains(removeAccent(tenSach).toLowerCase())) {

				Object[] rowData = { i++, s.getMaSanPham(), s.getTenSach(), s.getTheLoaiSach().getTenLoai(),
						tacGia, s.tinhGiaBan(), s.getSoLuongTon() };
				dtm.addRow(rowData);
			}
		}
	}

	public void tableDanhSachVPPWithFilter() {
		tableVPP.clearSelection();
		clearTableVPP();
		DefaultTableModel dtm = (DefaultTableModel) tableVPP.getModel();
		String mavpp = txtTimKiemMaSP.getText().trim();
		String tenvpp = txtTimKiemTenSP.getText().trim();
		

		List<VanPhongPham> listVPP = sanPhamDao.getAllVPP();
		int i = 1;

		for (VanPhongPham v : listVPP) {
			if (removeAccent(v.getMaSanPham()).toLowerCase().contains(removeAccent(mavpp).toLowerCase())
					&& removeAccent(v.getTenVanPhongPham()).toLowerCase()
							.contains(removeAccent(tenvpp).toLowerCase())) {
				Object[] rowData = { i++, v.getMaSanPham(), v.getTenVanPhongPham(), v.getChatLieu().getTenChatLieu(),
						v.getXuatXu().getTenXuatXu(), v.tinhGiaBan(), v.getSoLuongTon() };
				dtm.addRow(rowData);
			}
		}
	}

	public void clearTableSach() {
		DefaultTableModel dtm = (DefaultTableModel) tableSach.getModel();
		dtm.setRowCount(0);

	}

	public void clearTableVPP() {
		DefaultTableModel dtm1 = (DefaultTableModel) tableVPP.getModel();
		dtm1.setRowCount(0);
	}

	public void clearTableCTHD() {
		DefaultTableModel dtm = (DefaultTableModel) tableHoaDon.getModel();
		dtm.setRowCount(0);
	}

	private static String removeAccent(String s) {

		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

	// Linh
	public static HashMap<String, ArrayList<SanPham>> getListHoaDonCho() {
		return listHoaDonCho;
	}

	public static void getListHoaDonCho(HashMap<String, ArrayList<SanPham>> listHD) {
		listHoaDonCho = listHD;
	}

	public void openFrmHoaDonCho() {
		new Thread(() -> {
			ShareData shareData = new ShareData(listHoaDonCho);

			synchronized (shareData) {
				try {
					frm_HangCho = new Frm_HangCho(listHoaDonCho, shareData);
					frm_HangCho.setVisible(true);
					shareData.wait();
					listHoaDonCho = shareData.getListHoaDonCho();
					if (shareData.isThanhToan()) {
						modelHoaDon.setRowCount(0);
						String sdt = shareData.getSdtThanhToan();
						KhachHangServiceImpl khachHangServiceImpl = new KhachHangServiceImpl();
						try {
							KhachHang khachHang = khachHangServiceImpl.timKhachHangBangSDT(sdt);
							txtSDT.setText(khachHang.getsDT());
							txtTenKhachHang.setText(khachHang.getHoTenKhachHang());
							ArrayList<SanPham> listSanPham = new ArrayList<>();
							listSanPham = shareData.getListSanPhamThanhToanTiep();
							for (int i = 0; i < listSanPham.size(); i++) {
								Sach sach = null;
								VanPhongPham vanPhongPham = null;
								sach = sanPhamServiceImpl.timSanPhamTheoMaSach(listSanPham.get(i).getMaSanPham());
								vanPhongPham = sanPhamServiceImpl
										.timSanPhamTheoMaVPP(listSanPham.get(i).getMaSanPham());

								if (sach != null) {
									Object[] o = { i + 1 + "", sach.getMaSanPham(), sach.getTenSach(),
											sach.tinhGiaBan() + "", listSanPham.get(i).getSoLuongTon() };
									modelHoaDon.addRow(o);
								}
								if (vanPhongPham != null) {
									Object[] o = { i + 1 + "", vanPhongPham.getMaSanPham(),
											vanPhongPham.getTenVanPhongPham(), vanPhongPham.tinhGiaBan() + "",
											listSanPham.get(i).getSoLuongTon() };
									modelHoaDon.addRow(o);
								}
							}
							tongTienHandler();
							tongTienVAT();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void setTongTienRong() {
		txtTongTienHD.setText("");
		txtVAT.setText("");
	}

	public static String auto_ID() {
		HoaDonDao hoadon_dao = new HoaDonDao();
		String idPrefix = "HD";
		LocalDate myObj = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
		String formattedString = myObj.format(formatter);
		int length = 0;
		try {
			length = hoadon_dao.getDSHoaDon().size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String finalId = idPrefix + formattedString + String.format("%05d", length + 1);
		return finalId;
	}

	public static String auto_Date() {
		LocalDate myObj = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd - LL - yyyy");
		String formattedString = myObj.format(formatter);
		return formattedString;
	}
	public void truSLSachKhiThem() {
		int soLuongThem = Integer.parseInt(txtSoLuongSach.getText());
		int row = tableSach.getSelectedRow();
		modelSach = (DefaultTableModel) tableSach.getModel();
		modelSach.setValueAt(Integer.parseInt(modelSach.getValueAt(row, 6).toString()) - soLuongThem, row, 6);
		
		
	}
	public void truSLVPPKhiThem() {
		int soLuongThem = Integer.parseInt(txtSoLuongVPP.getText());
		int row = tableVPP.getSelectedRow();
		modelVPP = (DefaultTableModel) tableVPP.getModel();
		modelVPP.setValueAt(Integer.parseInt( modelVPP.getValueAt(row, 6).toString()) - soLuongThem, row, 6);
	}
	public void truSoLuongTrongGioHang() {
		int soLuongCanXoa = Integer.parseInt(txtXoa.getText());
		
		int row = tableHoaDon.getSelectedRow();
		modelHoaDon = (DefaultTableModel) tableHoaDon.getModel();
		modelSach = (DefaultTableModel) tableSach.getModel();
		modelVPP = (DefaultTableModel) tableVPP.getModel();
		int tongSP = Integer.parseInt(tableHoaDon.getValueAt(row, 4).toString()) - soLuongCanXoa;
		tableHoaDon.setValueAt(tongSP, row, 4);
		for(int i = 0; i < modelSach.getRowCount(); i++) {
			if (modelSach.getValueAt(i, 1).toString().equals(modelHoaDon.getValueAt(row, 1).toString())) {
				modelSach.setValueAt(Integer.parseInt( modelSach.getValueAt(i, 6).toString()) + soLuongCanXoa, i, 6);
			} 
		}
		for(int j = 0; j < modelVPP.getRowCount(); j++) {
			if(modelVPP.getValueAt(j, 1).toString().equals(modelHoaDon.getValueAt(row, 1).toString())) {
				modelVPP.setValueAt(Integer.parseInt( modelVPP.getValueAt(j, 6).toString()) + soLuongCanXoa, j, 6);
			}
		}
		if (tongSP <= 0) {
			modelHoaDon.removeRow(row);
		}
	}

	public void themSoLuongSanPhamVaoKho() throws SQLException {
		int soLuongCanXoa = Integer.parseInt(txtXoa.getText());
		modelSach = (DefaultTableModel) tableSach.getModel();
		modelVPP = (DefaultTableModel) tableVPP.getModel();
		modelHoaDon = (DefaultTableModel) tableHoaDon.getModel();
		int row = tableHoaDon.getSelectedRow();
		for (int i = 0; i < modelSach.getRowCount(); i++) {
			if (modelSach.getValueAt(i, 1).toString().equals(modelHoaDon.getValueAt(row, 1).toString())) {
				SanPham sp = sanPhamServiceImpl.timSanPhamTheoMa(modelHoaDon.getValueAt(row, 1).toString());
				int soLuongBanDau = sp.getSoLuongTon();
				sp.setSoLuongTon(soLuongBanDau + soLuongCanXoa);
				sanPhamServiceImpl.capNhatSoLuongSanPham(sp);
			} if(modelVPP.getValueAt(i, 1).toString().equals(modelHoaDon.getValueAt(row, 1).toString())) {
				SanPham sp = sanPhamServiceImpl.timSanPhamTheoMa(modelHoaDon.getValueAt(row, 1).toString());
				int soLuongBanDau = sp.getSoLuongTon();
				sp.setSoLuongTon(soLuongBanDau + soLuongCanXoa);
				sanPhamServiceImpl.capNhatSoLuongSanPham(sp);
			}
		}
	}
	public void truSoLuongKhiThanhToan() throws SQLException {
		modelHoaDon = (DefaultTableModel) tableHoaDon.getModel();
		for(int i = 0;  i< modelHoaDon.getRowCount(); i++) {
				SanPham sp =  sanPhamServiceImpl.timSanPhamTheoMa(modelHoaDon.getValueAt(i, 1).toString());
				int soLuongBanDau = sp.getSoLuongTon();
				sp.setSoLuongTon(soLuongBanDau - Integer.parseInt(modelHoaDon.getValueAt(i,4).toString()));
				sanPhamServiceImpl.capNhatSoLuongSanPham(sp);
		}
		
	}
}
