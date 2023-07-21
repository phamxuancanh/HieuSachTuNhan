package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import dao.ChiTietHoaDonDao;
import dao.ChiTietHoaDonDoiTraDao;
import dao.HoaDonDao;
import dao.HoaDonDoiTraDao;
import dao.KhachHangDao;
import dao.NhanVienDao;
import db.DBConnection;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.HoaDonDoiTra;
import entity.KhachHang;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import service.impl.ChiTietHoaDonServiceImpl;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.SanPhamServiceImpl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.mail.Flags.Flag;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Pn_QuanLyHoaDon extends JPanel implements ActionListener, MouseListener {
//	public Pn_QuanLyHoaDon() {
//	}

	/**
	 * Create the panel.
	 */

	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelHoaDon;
	private JTable tableHoaDon;
	private JScrollPane scrollHoaDon;
	private JTextField txtMaHoaDon;
	private JTextField txtNgayLap;
	private JTextField txtTenNhanVien;
	private JTextField txtTenNVTim;
	private HoaDonDao hoaDon_dao;
	private List<HoaDon> dsHoaDon;
	private List<KhachHang> dsKhachHang;
	private Frm_XemChiTietHoaDon frm_XemChiTietHoaDon;

	private JButton btnRefresh;
	private JButton btnFind;
	private JButton btnXemChiTiet;
	private JButton btnIn;
	ButtonGroup G;
	JRadioButton rdHoaDonDoiTra;
	JRadioButton rdHoaDonThuong;
	private KhachHangServiceImpl iKhachHang;
	private HoaDonServiceImpl iHoaDon;
	private JTextField txtTenKHTim;
	private JLabel lblMHan_1;
	private JTextField txtMahoaDonTim;
	private JLabel lblSDT_1;
	private JTextField txtSDTTim;
//	Flag loại hóa đơn
	int flag = 1; // Mặc định là hóa đơn thường
	private ChiTietHoaDonServiceImpl iChiTietHoaDon;
	private ChiTietHoaDonDao chiTietHoaDonDao;
	private HoaDonDoiTra hddt;
	private HoaDonDao HoaDon_dao;
	private HoaDonDoiTraDao hoaDonDoiTraDao;
	private ChiTietHoaDonDoiTraDao chiTietHoaDonDoiTra;
	private SanPhamServiceImpl iSanPham;
	private List<ChiTietHoaDon> dscthd;
	private List<HoaDonDoiTra> dsHoaDonDoiTra;

//	private HoaDonDoiTraDao

	public Pn_QuanLyHoaDon() {
		setBackground(new Color(0, 206, 209));
		setFont(new Font("Dialog", Font.BOLD, 16));
		setSize(1480, 630);
		// setLocation(null);
		setLayout(null);

		JLabel lblTitle = new JLabel("QUẢN LÝ HÓA ĐƠN ");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.DARK_GRAY);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblTitle.setBounds(10, 11, 1460, 55);
		add(lblTitle);

		JPanel pnLoc = new JPanel();
		pnLoc.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnLoc.setBackground(new Color(255, 255, 255));
		pnLoc.setBounds(10, 126, 418, 428);
		add(pnLoc);
		pnLoc.setLayout(null);

		txtTenNVTim = new JTextField();
		txtTenNVTim.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenNVTim.setForeground(new Color(0, 0, 0));
		txtTenNVTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenNVTim.setColumns(10);
		txtTenNVTim.setBounds(141, 207, 267, 33);
		pnLoc.add(txtTenNVTim);

		JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
		lblTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKhachHang.setBounds(10, 341, 121, 33);
		pnLoc.add(lblTenKhachHang);

		JLabel lblSDT = new JLabel("Tên nhân viên:");
		lblSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDT.setBounds(10, 207, 121, 33);
		pnLoc.add(lblSDT);

		txtTenKHTim = new JTextField();
		txtTenKHTim.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenKHTim.setForeground(Color.BLACK);
		txtTenKHTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKHTim.setColumns(10);
		txtTenKHTim.setBounds(141, 341, 267, 33);
		pnLoc.add(txtTenKHTim);

		JPanel pnTimKiem_1 = new JPanel();
		pnTimKiem_1.setLayout(null);
		pnTimKiem_1.setBorder(null);
		pnTimKiem_1.setBackground(Color.LIGHT_GRAY);
		pnTimKiem_1.setBounds(0, 63, 418, 46);
		pnLoc.add(pnTimKiem_1);

		JLabel lblTimKiem_1 = new JLabel("Tìm kiếm thông tin hóa đơn");
		lblTimKiem_1.setBounds(0, 0, 418, 46);
		pnTimKiem_1.add(lblTimKiem_1);
		lblTimKiem_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimKiem_1.setForeground(Color.BLACK);
		lblTimKiem_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTimKiem_1.setBackground(Color.LIGHT_GRAY);

		lblMHan_1 = new JLabel("Mã hóa đơn:");
		lblMHan_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMHan_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMHan_1.setBounds(10, 139, 121, 33);
		pnLoc.add(lblMHan_1);

		txtMahoaDonTim = new JTextField();
		txtMahoaDonTim.setHorizontalAlignment(SwingConstants.CENTER);
		txtMahoaDonTim.setForeground(Color.BLACK);
		txtMahoaDonTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMahoaDonTim.setColumns(10);
		txtMahoaDonTim.setBounds(141, 139, 267, 33);
		pnLoc.add(txtMahoaDonTim);

		lblSDT_1 = new JLabel("Số điện thoại KH:");
		lblSDT_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDT_1.setBounds(10, 272, 121, 33);
		pnLoc.add(lblSDT_1);

		txtSDTTim = new JTextField();
		txtSDTTim.setHorizontalAlignment(SwingConstants.CENTER);
		txtSDTTim.setForeground(Color.BLACK);
		txtSDTTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDTTim.setColumns(10);
		txtSDTTim.setBounds(141, 272, 267, 33);
		pnLoc.add(txtSDTTim);

		rdHoaDonThuong = new JRadioButton("Hóa đơn thường");
		rdHoaDonThuong.setSelected(true);
		rdHoaDonThuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdHoaDonThuong.setBounds(41, 12, 135, 37);
//		rdHoaDonDoiTra.setSelected(true);
		pnLoc.add(rdHoaDonThuong);
//	rdHoaDonThuong

		rdHoaDonDoiTra = new JRadioButton("Hóa đơn đổi trả");
		rdHoaDonDoiTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdHoaDonDoiTra.setBounds(231, 12, 128, 37);
		pnLoc.add(rdHoaDonDoiTra);

		G = new ButtonGroup();
		G.add(rdHoaDonThuong);
		G.add(rdHoaDonDoiTra);
//		String[] cols;

//	Sự kiện với radioButton

		rdHoaDonDoiTra.setMnemonic(KeyEvent.VK_C);
		rdHoaDonThuong.setMnemonic(KeyEvent.VK_M);

		rdHoaDonThuong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					setColumnName("Mã hóa đơn", "Thành tiền");
					modelHoaDon.setRowCount(0);
					try {
						DocDuLieuTuArrayListVaoModel();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("Chọn Hóa đơn thường");
//					String[] cols = { "STT", "Mã hóa đơn", "Nhân viên lâp", "Ngày lập", "Tên khách hàng", "Thành tiền" };
					flag = 1;
				}

			}
		});
		rdHoaDonDoiTra.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					setColumnName("Mã hóa đơn đổi trả", "Tiền phải trừ");
					modelHoaDon.setRowCount(0);
					try {
						DocDuLieuHoaDonDoiTraTuArrayListVaoModel();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("Chọn Hóa đơn doi");
//					String[] cols = { "STT", "Mã hóa đơn", "Nhân viên lâp", "Ngày lập", "Tên khách hàng", "Tiền trừ" };
					flag = 0;
				}

			}
		});
		if (flag == 1) {
			String[] cols = { "STT", "Mã hóa đơn", "Nhân viên lâp", "Ngày lập", "Tên khách hàng", "Thành tiền" };
			modelHoaDon = new DefaultTableModel(cols, 0);
			tableHoaDon = new JTable(modelHoaDon);
			tableHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
			tableHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));

			scrollHoaDon = new JScrollPane(tableHoaDon);
			scrollHoaDon.setBounds(448, 76, 1022, 478);
			tableHoaDon.getTableHeader().setBackground(Color.LIGHT_GRAY);
			tableHoaDon.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 17));
			tableHoaDon.setRowHeight(25);
			tableHoaDon.setBackground(Color.WHITE);
			scrollHoaDon.getViewport().setBackground(Color.WHITE);
			tableHoaDon.getTableHeader().setPreferredSize(new Dimension(0, 40));
			add(scrollHoaDon);
			try {
				DocDuLieuTuArrayListVaoModel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			String[] cols = { "STT", "Mã hóa đơn", "Nhân viên lâp", "Ngày lập", "Tên khách hàng", "Tiền trừ" };
			modelHoaDon = new DefaultTableModel(cols, 0);
			tableHoaDon = new JTable(modelHoaDon);
			tableHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
			tableHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));

			scrollHoaDon = new JScrollPane(tableHoaDon);
			scrollHoaDon.setBounds(448, 76, 1022, 478);
			tableHoaDon.getTableHeader().setBackground(Color.LIGHT_GRAY);
			tableHoaDon.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 17));
			tableHoaDon.setRowHeight(25);
			tableHoaDon.setBackground(Color.WHITE);
			scrollHoaDon.getViewport().setBackground(Color.WHITE);
			tableHoaDon.getTableHeader().setPreferredSize(new Dimension(0, 40));
			add(scrollHoaDon);
		}

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBackground(Color.LIGHT_GRAY);
		pnTimKiem.setBorder(null);
		pnTimKiem.setBounds(10, 76, 418, 49);
		add(pnTimKiem);
		pnTimKiem.setLayout(null);
		JLabel lblTimKiem = new JLabel("Loại hóa đơn");
		lblTimKiem.setBounds(0, 0, 418, 49);
		pnTimKiem.add(lblTimKiem);
		lblTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTimKiem.setForeground(Color.BLACK);
		lblTimKiem.setBackground(Color.LIGHT_GRAY);

		btnRefresh = new JButton("Làm mới");
		btnRefresh.setBounds(859, 566, 190, 40);
		add(btnRefresh);
		btnRefresh.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/refresh-button.png")));
		btnRefresh.setForeground(Color.BLACK);
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnFind = new JButton("Tìm");
		btnFind.setBounds(104, 566, 205, 40);
		add(btnFind);
		btnFind.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/loupe.png")));
		btnFind.setForeground(Color.BLACK);
		btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setForeground(Color.BLACK);
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXemChiTiet.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/diskette.png")));
		btnXemChiTiet.setBounds(518, 566, 205, 40);
		add(btnXemChiTiet);

		btnIn = new JButton("In");
		btnIn.setForeground(Color.BLACK);
		btnIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIn.setIcon(new ImageIcon(Pn_QuanLyKhachHang.class.getResource("/gui/icon/diskette.png")));
		btnIn.setBounds(1204, 566, 205, 40);
		add(btnIn);

		TableColumnModel columnMode = tableHoaDon.getColumnModel();
		columnMode.getColumn(0).setPreferredWidth(10);

		// add action listener
		btnFind.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnXemChiTiet.addActionListener(this);
		rdHoaDonDoiTra.addMouseListener(this);
		rdHoaDonThuong.addMouseListener(this);
		tableHoaDon.addMouseListener(this);
		btnIn.addActionListener(this);

//		SET FORCUS
		txtMahoaDonTim.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				txtSDTTim.setEditable(false);
				txtTenKHTim.setEditable(false);
				txtTenNVTim.setEditable(false);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtSDTTim.setEditable(true);
				txtTenKHTim.setEditable(true);
				txtTenNVTim.setEditable(true);
			}
		});

		txtSDTTim.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMahoaDonTim.setEditable(false);
				txtTenKHTim.setEditable(false);
				txtTenNVTim.setEditable(false);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtMahoaDonTim.setEditable(true);
				txtTenKHTim.setEditable(true);
				txtTenNVTim.setEditable(true);
			}
		});
		txtTenKHTim.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMahoaDonTim.setEditable(false);
				txtSDTTim.setEditable(false);
				txtTenNVTim.setEditable(false);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtMahoaDonTim.setEditable(true);
				txtSDTTim.setEditable(true);
				txtTenNVTim.setEditable(true);
			}
		});
		txtTenNVTim.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMahoaDonTim.setEditable(false);
				txtSDTTim.setEditable(false);
				txtTenKHTim.setEditable(false);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txtMahoaDonTim.setEditable(true);
				txtSDTTim.setEditable(true);
				txtTenKHTim.setEditable(true);
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(rdHoaDonDoiTra)) {
			setColumnName("Mã hóa đơn đổi trả", "Tiền phải trừ");
			modelHoaDon.setRowCount(0);
			try {
				DocDuLieuHoaDonDoiTraTuArrayListVaoModel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (o.equals(rdHoaDonThuong)) {
			setColumnName("Mã hóa đơn", "Thành tiền");
			modelHoaDon.setRowCount(0);
			try {
				DocDuLieuTuArrayListVaoModel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (flag == 1) {
			if (obj.equals(btnXemChiTiet)) {
				int row = tableHoaDon.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào!!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String maHoaDon = modelHoaDon.getValueAt(row, 1).toString();
					System.out.println(maHoaDon);
					String tenNhanVien = modelHoaDon.getValueAt(row, 2).toString();
					String ngayLap = modelHoaDon.getValueAt(row, 3).toString();
					String tenKhachHang = modelHoaDon.getValueAt(row, 4).toString();
					String tienKhachDua = "";
					String ghiChu = "";
					String tongTienHoaDon = "";
//					HoaDon hd;
					HoaDon_dao = new HoaDonDao();
					try {
						HoaDon hd = HoaDon_dao.timHoaDonTheoMa(maHoaDon);
//						System.out.println(hd.getTienKhachDua());
						tienKhachDua = hd.getTienKhachDua() + "";
						tongTienHoaDon = tongTienHoaDon(maHoaDon) + "";
						ghiChu = hd.getGhiChu();
//						if(hd.isTinhTrang())
//							tinhTrang = "";
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					new Frm_XemChiTietHoaDon(maHoaDon, tenNhanVien, ngayLap, tenKhachHang, tienKhachDua, tongTienHoaDon,
							ghiChu).setVisible(true);
				}
			} else if (obj.equals(btnFind)) {

				if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
						&& txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm");
				} else if (!txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
						&& txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
					String maTim = txtMahoaDonTim.getText().trim();
					try {
						DocDuLieuTimKiemTuArrayListVaoModelTheoMa(maTim);
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
						&& txtSDTTim.getText().equals("") && !txtTenNVTim.getText().equals("")) {
					String tenTim = txtTenNVTim.getText().trim();

//					xoaHetDuLieu()
					try {
						DocDuLieuTimKiemTuArrayListVaoModelTheoTen(tenTim);
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
						&& !txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
					String sdtTim = txtSDTTim.getText().trim();

//					xoaHetDuLieu()
					try {
						DocDuLieuTimKiemTuArrayListVaoModelTheoSDT(sdtTim);
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (txtMahoaDonTim.getText().equals("") && !txtTenKHTim.getText().equals("")
						&& txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
					String tenKHTim = txtTenKHTim.getText().trim();

//					xoaHetDuLieu()
					try {
						DocDuLieuTimKiemTuArrayListVaoModelTheoTenKH(tenKHTim);
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			} else if (obj.equals(btnRefresh)) {
				xoaHetDuLieu();
				try {
					DocDuLieuTuArrayListVaoModel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(obj.equals(btnIn)) {
				if (tableHoaDon.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(this, "Phải chọn hóa đơn cần in");
				} else {
					xuatHoaDon(tableHoaDon.getValueAt(tableHoaDon.getSelectedRow(), 1).toString());
				}
			}
			
		} else {
		 if (obj.equals(btnFind)) {
				if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
						&& txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm");
				} else if (!txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
						&& txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
					String maTim = txtMahoaDonTim.getText().trim();
					try {
						DocDuLieuHoaDonDoiTraVaoModelTheoMa(maTim);
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
						&& txtSDTTim.getText().equals("") && !txtTenNVTim.getText().equals("")) {
					String tenTim = txtTenNVTim.getText().trim();

//					xoaHetDuLieu()
					try {
						DocDuLieuHoaDonDoiTraVaoModelTheoTenNV(tenTim);
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
						&& !txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
					String sdtTim = txtSDTTim.getText().trim();

//					xoaHetDuLieu()
					try {
						DocDuLieuHoaDonDoiTraVaoModelTheoSDT(sdtTim);
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (txtMahoaDonTim.getText().equals("") && !txtTenKHTim.getText().equals("")
						&& txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
					String tenKHTim = txtTenKHTim.getText().trim();

//					xoaHetDuLieu()
					try {
						DocDuLieuHoaDonDoiTraVaoModelTheoTenKH(tenKHTim);
						xoaTrang();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			else if (obj.equals(btnXemChiTiet)) {
//				System.out.println("Xem chi tiết hóa đơn đổi trả");
				int row = tableHoaDon.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào!!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String maHoaDonDoiTra = modelHoaDon.getValueAt(row, 1).toString();
					hoaDonDoiTraDao = new HoaDonDoiTraDao();
					HoaDonDoiTra hoaDonDT = new HoaDonDoiTra();
					try {
						hoaDonDT = hoaDonDoiTraDao.timHoaDonDoiTraTheoMa(maHoaDonDoiTra);
						String maHoaDonDT = hoaDonDT.getMaHoaDonDoiTra();
						String maHDCu = hoaDonDT.getHoaDon().getMaHoaDon();
						String tenNV = hoaDonDT.getNhanVien().getHoTenNhanVien();
						String tenKH = hoaDonDT.getKhachHang().getHoTenKhachHang();
						String ngayLapHD = hoaDonDT.getNgayLapHoaDon().toString();
						String tienKhachDua = hoaDonDT.getTienKhachDua() + "";
						String tienPhaiTru = hoaDonDT.getTienPhaiTru() + "";
						String ghiChu = hoaDonDT.getGhiChu();
						System.out.println("Ma hd cu: " + maHDCu);
						new Frm_XemChiTietHoaDonDoiTra(maHoaDonDT, maHDCu, tenNV, tenKH, ngayLapHD, ghiChu,
								tienKhachDua, tienPhaiTru).setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			} else if (obj.equals(btnRefresh)) {
				xoaHetDuLieu();
				try {
					DocDuLieuHoaDonDoiTraTuArrayListVaoModel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (obj.equals(btnIn)) {
				System.out.println("ok"); 
					if (tableHoaDon.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(this, "Phải chọn hóa đơn cần in");
					} else {
						xuatHoaDonDoiTra(tableHoaDon.getValueAt(tableHoaDon.getSelectedRow(), 1).toString());
					}
				}

			
		}

	}

	public void setColumnName(String st1, String st2) {
		JTableHeader HEADER = tableHoaDon.getTableHeader();
		TableColumnModel TMC = HEADER.getColumnModel();
		TableColumn TC = TMC.getColumn(1);
		TC.setHeaderValue(st1);

		TableColumn TC2 = TMC.getColumn(5);
		TC2.setHeaderValue(st2);

		HEADER.repaint();
		tableHoaDon.getTableHeader().repaint();
	}

	public long tongTienHoaDon(String maHoaDon) {
		chiTietHoaDonDao = new ChiTietHoaDonDao();
		long tongTien = chiTietHoaDonDao.getTien(maHoaDon);

//		iChiTietHoaDon = new ChiTietHoaDonServiceImpl();
//		tongTien = iChiTietHoaDon.getTien(maHoaDon);
		return tongTien;

	}

	public void DocDuLieuTuArrayListVaoModel() throws Exception {
		hoaDon_dao = new HoaDonDao();
		dsHoaDon = hoaDon_dao.getHoaDonThuong();
		int i = 1;
		for (HoaDon hoaDon : dsHoaDon) {
			if(hoaDon.getNhanVien()==null) {
				modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(),"Đã nghỉ việc",
						hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
						tongTienHoaDon(hoaDon.getMaHoaDon()) });
			}
			else {
			modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
					hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
					tongTienHoaDon(hoaDon.getMaHoaDon()) });
			}
		}

	}

	public void DocDuLieuTimKiemTuArrayListVaoModelTheoMa(String maHoaDon) throws Exception {
		hoaDon_dao = new HoaDonDao();
		dsHoaDon = hoaDon_dao.getHoaDonTheoMa(maHoaDon);
		if (dsHoaDon.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		} else {
			xoaHetDuLieu();
			int i = 1;
			for (HoaDon hoaDon : dsHoaDon) {
				System.out.println(hoaDon);
				modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
						hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
						tongTienHoaDon(hoaDon.getMaHoaDon()) });
			}
		}
	}

//timHoaDonTheoSDT
	public void DocDuLieuTimKiemTuArrayListVaoModelTheoTen(String tenNV) throws Exception {
		hoaDon_dao = new HoaDonDao();
		dsHoaDon = hoaDon_dao.getHoaDonTheoTen(tenNV);
		if (dsHoaDon.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		} else {
			xoaHetDuLieu();
			int i = 1;
			for (HoaDon hoaDon : dsHoaDon) {
				modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
						hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
						tongTienHoaDon(hoaDon.getMaHoaDon()) });
			}
		}

	}

	public void DocDuLieuTimKiemTuArrayListVaoModelTheoTenKH(String tenKH) throws Exception {
		hoaDon_dao = new HoaDonDao();
		dsHoaDon = hoaDon_dao.timHoaDonTheoTenKH(tenKH);
		if (dsHoaDon.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		} else {
			xoaHetDuLieu();
			int i = 1;
			for (HoaDon hoaDon : dsHoaDon) {

				System.out.println(hoaDon);
				modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
						hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
						tongTienHoaDon(hoaDon.getMaHoaDon()) });
			}
		}

	}

	public void DocDuLieuTimKiemTuArrayListVaoModelTheoSDT(String sdt) throws Exception {
		hoaDon_dao = new HoaDonDao();
		dsHoaDon = hoaDon_dao.timHoaDonTheoSDT(sdt);
		if (dsHoaDon.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		} else {
			xoaHetDuLieu();
			int i = 1;
			for (HoaDon hoaDon : dsHoaDon) {
//				System.out.println(hoaDon.getKhachHang().getHoTenKhachHang());
				modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
						hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
						tongTienHoaDon(hoaDon.getMaHoaDon()) });
			}
		}

	}

	public void DocDuLieuHoaDonDoiTraTuArrayListVaoModel() throws Exception {
		hoaDonDoiTraDao = new HoaDonDoiTraDao();

		dsHoaDonDoiTra = hoaDonDoiTraDao.getToanBoDSHoaDonDoiTra();
//		JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		int i = 1;
		for (HoaDonDoiTra hd : dsHoaDonDoiTra) {
			System.out.println(hd);
//			String hoten = new NhanVienDao().timDanhSachNhanVienTheoMa(hd.getNhanVien().getMaNhanVien());
			modelHoaDon.addRow(new Object[] { i++, hd.getMaHoaDonDoiTra(), hd.getNhanVien().getHoTenNhanVien(),
					hd.getNgayLapHoaDon(), hd.getKhachHang().getHoTenKhachHang(), hd.getTienPhaiTru() });
		}

	}

// Hoa don doi tra
	public void DocDuLieuHoaDonDoiTraVaoModelTheoMa(String maHoaDon) throws Exception {
		hoaDonDoiTraDao = new HoaDonDoiTraDao();
		hddt = hoaDonDoiTraDao.timHoaDonDoiTraTheoMa(maHoaDon);
		if (hddt == null) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		} else {
			xoaHetDuLieu();
			int i = 1;

			System.out.println(hddt);
			modelHoaDon.addRow(new Object[] { i++, hddt.getMaHoaDonDoiTra(), hddt.getNhanVien().getHoTenNhanVien(),
					hddt.getNgayLapHoaDon(), hddt.getKhachHang().getHoTenKhachHang(), hddt.getTienPhaiTru() });

		}
	}

	public void DocDuLieuHoaDonDoiTraVaoModelTheoTenNV(String ten) throws Exception {
		hoaDonDoiTraDao = new HoaDonDoiTraDao();
		dsHoaDonDoiTra = hoaDonDoiTraDao.getHoaDonDoiTraTheoTen(ten);
		if (dsHoaDonDoiTra.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		} else {
			xoaHetDuLieu();
			int i = 1;
			for (HoaDonDoiTra hddt : dsHoaDonDoiTra) {
				modelHoaDon.addRow(new Object[] { i++, hddt.getMaHoaDonDoiTra(), hddt.getNhanVien().getHoTenNhanVien(),
						hddt.getNgayLapHoaDon(), hddt.getKhachHang().getHoTenKhachHang(), hddt.getTienPhaiTru() });
			}
		}

	}

	public void DocDuLieuHoaDonDoiTraVaoModelTheoSDT(String sdt) throws Exception {
		hoaDonDoiTraDao = new HoaDonDoiTraDao();
		dsHoaDonDoiTra = hoaDonDoiTraDao.getHoaDonDoiTraTheoSDT(sdt);
		if (dsHoaDonDoiTra.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		} else {
			xoaHetDuLieu();
			int i = 1;
			for (HoaDonDoiTra hddt : dsHoaDonDoiTra) {
				modelHoaDon.addRow(new Object[] { i++, hddt.getMaHoaDonDoiTra(), hddt.getNhanVien().getHoTenNhanVien(),
						hddt.getNgayLapHoaDon(), hddt.getKhachHang().getHoTenKhachHang(), hddt.getTienPhaiTru() });
			}
		}

	}

	public void DocDuLieuHoaDonDoiTraVaoModelTheoTenKH(String tenKH) throws Exception {
		hoaDonDoiTraDao = new HoaDonDoiTraDao();
		dsHoaDonDoiTra = hoaDonDoiTraDao.getHoaDonDoiTraTheoTenKH(tenKH);
		if (dsHoaDonDoiTra.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
		} else {
			xoaHetDuLieu();
			int i = 1;
			for (HoaDonDoiTra hddt : dsHoaDonDoiTra) {
				modelHoaDon.addRow(new Object[] { i++, hddt.getMaHoaDonDoiTra(), hddt.getNhanVien().getHoTenNhanVien(),
						hddt.getNgayLapHoaDon(), hddt.getKhachHang().getHoTenKhachHang(), hddt.getTienPhaiTru() });
			}
		}

	}

	public void xoaHetDuLieu() {
		DefaultTableModel dtm = (DefaultTableModel) tableHoaDon.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void xoaTrang() {
		txtMahoaDonTim.setText("");
		txtTenKHTim.setText("");
		txtSDTTim.setText("");
		txtTenNVTim.setText("");
	}

	public void xuatHoaDonDoiTra(String maHDDT) {
		try {
			Hashtable map = new Hashtable();
			JasperReport report = JasperCompileManager.compileReport("src/gui/HoaDonDoiTra.jrxml");
			// System.out.println(report);
			map.put("maHDDT", maHDDT);
			JasperPrint p = JasperFillManager.fillReport(report, map, DBConnection.getInstance().getConnection());

			JasperViewer.viewReport(p, false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void xuatHoaDon(String maHD) {
		try {
			Hashtable map = new Hashtable();
			JasperReport report = JasperCompileManager.compileReport("src/gui/HoaDonBanHang.jrxml");
			// System.out.println(report);
			map.put("maHD", maHD);
			JasperPrint p = JasperFillManager.fillReport(report, map, DBConnection.getInstance().getConnection());

			JasperViewer.viewReport(p, false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
