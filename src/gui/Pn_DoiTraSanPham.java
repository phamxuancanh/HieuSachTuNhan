package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;

import db.DBConnection;
import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonDoiTra;
import entity.HoaDon;
import entity.HoaDonDoiTra;
import entity.KhachHang;
import entity.NhanVien;
import entity.Sach;
import entity.SachLoi;
import entity.SanPham;
import entity.TaiKhoan;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import service.impl.ChiTietHoaDonDoiTraServiceImpl;
import service.impl.ChiTietHoaDonServiceImpl;
import service.impl.HoaDonDoiTraServiceImpl;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.SachLoiServiceImpl;
import service.impl.SanPhamServiceImpl;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.ImageIcon;

public class Pn_DoiTraSanPham extends JPanel implements ActionListener, MouseListener {
	private JTextField txtTongTienSP;
	private JTextField txtTienKhachTra;
	private JTextField txtVAT;
	private JTextField txtMaHoaDonCu;
	private DefaultTableModel modelChiTietHoaDon;
	private JTable tableChiTietHoaDon;
	private JScrollPane scrollChiTietHoaDon;
	private JLabel lblTitle;
	private JPanel panelTool;
	private JLabel lblTongTienMoi;
	private JLabel lblVAT;
	private JLabel lblTienKhachTra;
	private JLabel lblTienThua;
	private JLabel lblGiaTriTienThua;
	private JPanel panelTool2;
	private JButton btnHuy;
	private JButton btnIn;
	private JButton btnThanhToan;
	private JComponent lblNgayHienTai;
	private JLabel lblGiaTriNgayHienTai;
	private JLabel lblMaHoaDonCu;
	private JButton btnKiemTra;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblGia;
	private JComboBox comboBoxSP;
	private JLabel lblGiaTriGiaSP;
	private JButton btnDoi2;
	private JComponent lblTiTleHoaDonCu;
	private JPanel panel_HoaDonCu;
	private JLabel lblNgayHoaDonCu;
	private JLabel lblMaNhanVien;
	private JComponent lblMaKhachHang;
	private JLabel lblTongTien;
	private JLabel lblGiaTriNgayHoaDonCu;
	private JLabel lblGiaTriMaNV;
	private JLabel lblGiaTriMaKH;
	private JLabel lblGiaTriTongTien;
	private JLabel lblChitiet;
	private JPanel panelDoi;
	private JButton btnDoi;
	private NhanVienServiceImpl iNhanVien;
	private ChiTietHoaDonServiceImpl iChiTietHoaDon;
	private HoaDonServiceImpl iHoaDon;
	private SanPhamServiceImpl iSanPham;
	private List<ChiTietHoaDon> dsCTHD;
	private List<SanPham> dsSP;
	private List<Sach> dsS;
	private JLabel lblNhapLoi;
	private JTextField txtLoi;
	private JTextField txtSoLuongSPLoi;
	private SachLoiServiceImpl iSachLoi;
	private KhachHangServiceImpl iKhachHang;
	private JLabel lblNewLabel_2;
	private JTextField txtMaSPDoi;
	private SachLoi sl;
	private JLabel txtMaHDDoiTra;
	private JLabel txtGiaTriMaHDDT;
	private NhanVien nv;
	private KhachHang kh;
	private HoaDonDoiTraServiceImpl iHoaDonDoiTra;
	private JButton btnXong;
	private SanPham sp;
	private ChiTietHoaDonDoiTraServiceImpl iChiTietHoaDonDoiTra;
	private JButton btnLmMi;
	private JButton btnTaoHDDoiTra;
	private JButton btnLmMiHD;
	private List<ChiTietHoaDonDoiTra> dsCTHDDT;
	private List<SachLoi> dsSL;
	private HoaDon hd;
	private double tienPhaiTru = 0;

	/**
	 * Create the panel.
	 */
	public Pn_DoiTraSanPham() {
		setBackground(new Color(0, 206, 209));
		setFont(new Font("Dialog", Font.BOLD, 16));
		setSize(1400, 682);
		setLayout(null);

		lblTitle = new JLabel("ĐỔI TRẢ SẢN PHẨM");
		lblTitle.setIcon(new ImageIcon(Pn_DoiTraSanPham.class.getResource("/gui/icon/contract.png")));
		lblTitle.setForeground(new Color(255, 69, 0));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 1400, 63);
		add(lblTitle);

		panelTool = new JPanel();
		panelTool.setBackground(new Color(144, 238, 144));
		panelTool.setBounds(0, 574, 1400, 97);
		add(panelTool);
		panelTool.setLayout(null);

		lblTongTienMoi = new JLabel("Tổng tiền sản phẩm :");
		lblTongTienMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTongTienMoi.setBounds(34, 11, 164, 27);
		panelTool.add(lblTongTienMoi);

		lblVAT = new JLabel("VAT: ");
		lblVAT.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVAT.setBounds(34, 49, 46, 24);
		panelTool.add(lblVAT);

		txtTongTienSP = new JTextField();
		txtTongTienSP.setBounds(220, 15, 175, 20);
		panelTool.add(txtTongTienSP);
		txtTongTienSP.setColumns(10);

		lblTienKhachTra = new JLabel("Tiền khách trả: ");
		lblTienKhachTra.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTienKhachTra.setBounds(463, 15, 106, 23);
		panelTool.add(lblTienKhachTra);

		txtTienKhachTra = new JTextField();
		txtTienKhachTra.setBounds(598, 15, 175, 20);
		panelTool.add(txtTienKhachTra);
		txtTienKhachTra.setColumns(10);

		lblTienThua = new JLabel("Tiền thừa:");
		lblTienThua.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTienThua.setBounds(463, 50, 95, 23);
		panelTool.add(lblTienThua);

		txtVAT = new JTextField();
		txtVAT.setBounds(220, 52, 175, 20);
		panelTool.add(txtVAT);
		txtVAT.setColumns(10);

		lblGiaTriTienThua = new JLabel(".......");
		lblGiaTriTienThua.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaTriTienThua.setBounds(598, 49, 175, 24);
		panelTool.add(lblGiaTriTienThua);

		panelTool2 = new JPanel();
		panelTool2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTool2.setBounds(842, 11, 524, 75);
		panelTool.add(panelTool2);
		panelTool2.setLayout(null);

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHuy.setBounds(27, 11, 126, 53);
		panelTool2.add(btnHuy);

		btnIn = new JButton("In");
		btnIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIn.setBounds(204, 11, 126, 53);
		panelTool2.add(btnIn);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThanhToan.setBounds(366, 11, 133, 53);
		panelTool2.add(btnThanhToan);

		lblNgayHienTai = new JLabel("Ngày: ");
		lblNgayHienTai.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNgayHienTai.setBounds(27, 64, 57, 22);
		add(lblNgayHienTai);

		lblGiaTriNgayHienTai = new JLabel("26-10-2022");
		lblGiaTriNgayHienTai.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaTriNgayHienTai.setBounds(94, 67, 86, 17);
		add(lblGiaTriNgayHienTai);

		lblMaHoaDonCu = new JLabel("Nhập mã hóa đơn cũ: ");
		lblMaHoaDonCu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaHoaDonCu.setBounds(27, 131, 153, 30);
		add(lblMaHoaDonCu);

		txtMaHoaDonCu = new JTextField();
		txtMaHoaDonCu.setBounds(178, 137, 152, 20);
		add(txtMaHoaDonCu);
		txtMaHoaDonCu.setColumns(10);

		btnKiemTra = new JButton("Kiểm tra");
		btnKiemTra.setBounds(340, 136, 89, 23);
		add(btnKiemTra);

		panel = new JPanel();
		panel.setBounds(27, 172, 402, 377);
		add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Chọn sản phẩm thay thế: ");
		lblNewLabel.setBounds(10, 182, 236, 22);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		lblGia = new JLabel("Giá: ");
		lblGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGia.setBounds(10, 215, 97, 22);
		panel.add(lblGia);

		comboBoxSP = new JComboBox();
		comboBoxSP.setBounds(235, 184, 145, 22);
		panel.add(comboBoxSP);

		lblGiaTriGiaSP = new JLabel(".............");
		lblGiaTriGiaSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiaTriGiaSP.setBounds(235, 215, 157, 22);
		panel.add(lblGiaTriGiaSP);

		btnDoi2 = new JButton("Đổi sản phẩm");
		btnDoi2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDoi2.setBounds(10, 262, 145, 35);
		panel.add(btnDoi2);

		lblNhapLoi = new JLabel("Nhập lỗi sản phẩm:");
		lblNhapLoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhapLoi.setBounds(10, 64, 183, 22);
		panel.add(lblNhapLoi);

		txtLoi = new JTextField();
		txtLoi.setBounds(235, 64, 145, 22);
		panel.add(txtLoi);
		txtLoi.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nhập số lượng sản phẩm lỗi cần thay thế:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 99, 351, 45);
		panel.add(lblNewLabel_1);

		txtSoLuongSPLoi = new JTextField();
		txtSoLuongSPLoi.setBounds(10, 155, 145, 22);
		panel.add(txtSoLuongSPLoi);
		txtSoLuongSPLoi.setColumns(10);

		lblNewLabel_2 = new JLabel("Mã sản phẩm lỗi:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 19, 145, 22);
		panel.add(lblNewLabel_2);

		txtMaSPDoi = new JTextField();
		txtMaSPDoi.setBounds(235, 22, 145, 20);
		panel.add(txtMaSPDoi);
		txtMaSPDoi.setColumns(10);

		lblTiTleHoaDonCu = new JLabel("Hóa đơn cũ");
		lblTiTleHoaDonCu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTiTleHoaDonCu.setBounds(561, 138, 96, 17);
		add(lblTiTleHoaDonCu);

		panel_HoaDonCu = new JPanel();
		panel_HoaDonCu.setBounds(555, 197, 332, 352);
		add(panel_HoaDonCu);
		panel_HoaDonCu.setLayout(null);

		lblNgayHoaDonCu = new JLabel("Ngày lập: ");
		lblNgayHoaDonCu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayHoaDonCu.setBounds(10, 34, 78, 30);
		panel_HoaDonCu.add(lblNgayHoaDonCu);

		lblMaNhanVien = new JLabel("Mã nhân viên: ");
		lblMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaNhanVien.setBounds(10, 120, 107, 22);
		panel_HoaDonCu.add(lblMaNhanVien);

		lblMaKhachHang = new JLabel("Mã khách hàng: ");
		lblMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaKhachHang.setBounds(10, 194, 149, 30);
		panel_HoaDonCu.add(lblMaKhachHang);

		lblTongTien = new JLabel("Tổng tiền: ");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongTien.setBounds(10, 278, 78, 22);
		panel_HoaDonCu.add(lblTongTien);

		lblGiaTriNgayHoaDonCu = new JLabel(".............");
		lblGiaTriNgayHoaDonCu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiaTriNgayHoaDonCu.setBounds(169, 34, 106, 30);
		panel_HoaDonCu.add(lblGiaTriNgayHoaDonCu);

		lblGiaTriMaNV = new JLabel(".............");
		lblGiaTriMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiaTriMaNV.setBounds(169, 120, 106, 22);
		panel_HoaDonCu.add(lblGiaTriMaNV);

		lblGiaTriMaKH = new JLabel(".............");
		lblGiaTriMaKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiaTriMaKH.setBounds(169, 194, 106, 30);
		panel_HoaDonCu.add(lblGiaTriMaKH);

		lblGiaTriTongTien = new JLabel("0");
		lblGiaTriTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiaTriTongTien.setBounds(169, 278, 106, 22);
		panel_HoaDonCu.add(lblGiaTriTongTien);

		btnLmMiHD = new JButton("Làm mới");
		btnLmMiHD.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLmMiHD.setBounds(86, 306, 106, 35);
		panel_HoaDonCu.add(btnLmMiHD);

		lblChitiet = new JLabel("Chi tiết hóa đơn cũ");
		lblChitiet.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChitiet.setBounds(922, 138, 167, 17);
		add(lblChitiet);

		String header_ChiTiet[] = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá" };
		modelChiTietHoaDon = new DefaultTableModel(header_ChiTiet, 0);
		tableChiTietHoaDon = new JTable(modelChiTietHoaDon);
		scrollChiTietHoaDon = new JScrollPane(tableChiTietHoaDon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollChiTietHoaDon.setBounds(922, 197, 429, 306);
		//tableChiTietHoaDon.setAutoCreateRowSorter(true);
		add(scrollChiTietHoaDon);

		panelDoi = new JPanel();
		panelDoi.setBounds(922, 514, 429, 35);
		add(panelDoi);

		btnDoi = new JButton("Đổi sản phẩm này");
		btnDoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelDoi.add(btnDoi);

		lblGiaTriNgayHienTai.setText(LocalDate.now().toString());
		txtMaSPDoi.setEditable(false);

		btnLmMi = new JButton("Làm mới");
		btnLmMi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLmMi.setBounds(10, 331, 145, 35);
		panel.add(btnLmMi);

		btnXong = new JButton("Xong");
		btnXong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXong.setBounds(223, 331, 138, 35);
		panel.add(btnXong);

		btnTaoHDDoiTra = new JButton("Tạo");
		btnTaoHDDoiTra.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTaoHDDoiTra.setBounds(223, 262, 138, 35);
		panel.add(btnTaoHDDoiTra);
		btnThanhToan.setEnabled(false);
		btnDoi.addActionListener(this);
		btnDoi2.addActionListener(this);
		btnIn.addActionListener(this);
		btnKiemTra.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLmMi.addActionListener(this);
		btnXong.addActionListener(this);
		btnTaoHDDoiTra.addActionListener(this);
		btnLmMiHD.addActionListener(this);
		tableChiTietHoaDon.addMouseListener(this);

		txtMaHDDoiTra = new JLabel("Mã hóa đơn đổi trả: ");
		txtMaHDDoiTra.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtMaHDDoiTra.setBounds(221, 64, 153, 22);
		add(txtMaHDDoiTra);

		txtGiaTriMaHDDT = new JLabel("...");
		txtGiaTriMaHDDT.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtGiaTriMaHDDT.setBounds(384, 64, 142, 22);
		add(txtGiaTriMaHDDT);

		txtMaSPDoi.setEditable(false);
		txtLoi.setEditable(false);
		txtSoLuongSPLoi.setEditable(false);
		btnDoi2.setEnabled(false);
		btnDoi.setEnabled(false);
		btnXong.setEnabled(false);
		btnTaoHDDoiTra.setEnabled(false);
		btnIn.setEnabled(false);

		comboBoxSP.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				iSanPham = new SanPhamServiceImpl();
				if (comboBoxSP.getItemCount() > 0) {
					lblGiaTriGiaSP.setText(
							iSanPham.getSachTheoTen(String.valueOf(comboBoxSP.getSelectedItem())).getGiaNhap() + "");
				} else {
					return;
				}
				// System.out.println();
			}
		});

		txtTienKhachTra.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				tinhTienThua();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void xoaHetDuLieuChiTietHoaDon() {
		DefaultTableModel dtm = (DefaultTableModel) tableChiTietHoaDon.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void xoaHetDuLieuHoaDon() {
		lblGiaTriNgayHoaDonCu.setText("");
		lblGiaTriMaNV.setText("");
		lblGiaTriMaKH.setText("");
		lblGiaTriTongTien.setText("");
	}

	public void DocDuLieuTuArrayListVaoModel() throws Exception {
		iHoaDon = new HoaDonServiceImpl();
		iChiTietHoaDon = new ChiTietHoaDonServiceImpl();
		iSanPham = new SanPhamServiceImpl();

		if (iHoaDon.getHoaDonTheoMa(txtMaHoaDonCu.getText()).size() == 0) {
			return;
		} else {

			HoaDon hd = iHoaDon.getHoaDonTheoMa(txtMaHoaDonCu.getText()).get(0);

			// Period period = Period.between( hd.getNgayLapHoaDon(),LocalDate.now());
			long dayGap = ChronoUnit.DAYS.between(hd.getNgayLapHoaDon(), LocalDate.now());
			// System.out.println(dayGap);
			// System.out.println("Period between start and end " + "date is : " + period);
			if (dayGap - 2 > 3) {
				// JOptionPane.showMessageDialog(this, "Hóa đơn này đã quá hạn đổi trả");
				return;
			}

			else {
				dsCTHD = iChiTietHoaDon.getCTHoaDonTheoMaHoaDon(txtMaHoaDonCu.getText());
				if (dsCTHD.size() == 0) {
					// JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn");
					return;
				} else {
					int count = 0;
					int i = 1;
					for (ChiTietHoaDon cthd : dsCTHD) {
						if (iSanPham.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getTenSach() == null) {
							count++;
						}
					}
					if (count < dsCTHD.size()) {
						for (ChiTietHoaDon cthd : dsCTHD) {
							if (iSanPham.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getTenSach() != null) {
								modelChiTietHoaDon.addRow(new Object[] { i++,
										iSanPham.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getMaSanPham(),
										iSanPham.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getTenSach(),
										cthd.getSoLuong(), cthd.getDonGia() });
							}
						}
					} else {
						JOptionPane.showMessageDialog(this, "Hóa đơn này không có sách để đổi");
						return;
					}
					JOptionPane.showMessageDialog(this, "Đã tìm thấy");
					btnTaoHDDoiTra.setEnabled(true);
				}
			}
		}
	}

	private void DocDuLieuHoaDon() throws SQLException {
		try {
			iHoaDon = new HoaDonServiceImpl();
			iNhanVien = new NhanVienServiceImpl();
			if (iHoaDon.getHoaDonTheoMa(txtMaHoaDonCu.getText()).size() == 0) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn");
				return;
			} else {

				HoaDon hd = iHoaDon.getHoaDonTheoMa(txtMaHoaDonCu.getText()).get(0);

				// Period period = Period.between( hd.getNgayLapHoaDon(),LocalDate.now());
				long dayGap = ChronoUnit.DAYS.between(hd.getNgayLapHoaDon(), LocalDate.now());
				// System.out.println(dayGap);
				// System.out.println("Period between start and end " + "date is : " + period);
				if (dayGap - 2 > 3) {
					JOptionPane.showMessageDialog(this, "Hóa đơn này đã quá hạn đổi trả");
					return;
				}

				else {
					lblGiaTriNgayHoaDonCu.setText(hd.getNgayLapHoaDon().toString());
					if (hd.getNhanVien() == null) {
						lblGiaTriMaNV.setText("Đã nghỉ việc");
					} else {
						lblGiaTriMaNV.setText(hd.getNhanVien().getMaNhanVien().toString());
					}

					lblGiaTriMaKH.setText(hd.getKhachHang().getMaKhachHang().toString());
					iChiTietHoaDon = new ChiTietHoaDonServiceImpl();
					long tongTien = 0;
					dsCTHD = iChiTietHoaDon.getCTHoaDonTheoMaHoaDon(txtMaHoaDonCu.getText());

					for (ChiTietHoaDon cthd : dsCTHD) {
						tongTien += cthd.tinhThanhTien();
					}
					lblGiaTriTongTien.setText(String.valueOf(tongTien * 1.1));
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void DocDuLieuVaoCombobox() {
		try {
			// System.out.println(".");
			iSanPham = new SanPhamServiceImpl();
			dsS = iSanPham.getAllSach();
			int row = 0;
			row = tableChiTietHoaDon.getSelectedRow();
			// comboBoxSP.removeAllItems();

			DefaultComboBoxModel model = (DefaultComboBoxModel) comboBoxSP.getModel();
			model.removeAllElements();
			// System.out.println(row);
			// System.out.println(comboBoxSP.getItemCount());
			// System.out.println(comboBoxSP.countComponents());
			for (Sach sach : dsS) {
				// System.out.println(sach.getGiaNhap()*1.1);
				if (sach.getSoLuongTon() > 0 && (sach.getGiaNhap() * 1.2) >= Double
						.parseDouble(tableChiTietHoaDon.getValueAt(row, 4).toString())) {
					comboBoxSP.addItem(sach.getTenSach());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void truSLSachKhiDoi() {
		iSanPham = new SanPhamServiceImpl();
		Sach s = iSanPham.getSachTheoTen(comboBoxSP.getSelectedItem().toString());
		s.setSoLuongTon(s.getSoLuongTon() - Integer.valueOf(txtSoLuongSPLoi.getText().toString()).intValue());

		try {
			iSanPham.capNhatSanPham(s.getMaSanPham(), s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addVaoSachLoi() {
		iSachLoi = new SachLoiServiceImpl();
		Sach sach = new Sach();

		try {
			dsSL = iSachLoi.getAllSachLoi();
			for (SachLoi sachLoi : dsSL) {
				if (txtMaSPDoi.getText().equals(sachLoi.getSach().getMaSanPham())
						&& txtLoi.getText().equals(sachLoi.getLoiSanPham())) {
					sachLoi.setSoLuong(sachLoi.getSoLuong() + Integer.parseInt(txtSoLuongSPLoi.getText().toString()));
					iSachLoi.capNhatSoLuong(sachLoi);
					return;
				}
			}

			try {
				sach = iSanPham.timSanPhamTheoMaSach(txtMaSPDoi.getText());
				sl = new SachLoi(sach, txtLoi.getText().toString(),
						Integer.parseInt(txtSoLuongSPLoi.getText().toString()));
				iSachLoi.themSachLoi(sl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void addHoaDonDoiTraMoi() throws SQLException {
		// HD13 00001

		String maHDDT = "HDDT";
		LocalDate ngayLapHoaDon = LocalDate.now();
		String ngayHD = String.valueOf(ngayLapHoaDon.getDayOfMonth());
		int length = 0;
		iHoaDonDoiTra = new HoaDonDoiTraServiceImpl();

		if (iHoaDonDoiTra.getMaHoaDonDoiTraByMaHDCu(txtMaHoaDonCu.getText().toString()).size() > 0) {
			// System.out.println(iHoaDonDoiTra.getMaHoaDonDoiTraByMaHDCu(txtMaHoaDonCu.getText().toString()));
			JOptionPane.showMessageDialog(this, "Hóa đơn này đã đổi trả rồi");
			btnDoi.setEnabled(true);
			return;
		} else {
			JOptionPane.showMessageDialog(this, "Nhập thông tin hóa đơn đổi trả");
			try {
				length = iHoaDonDoiTra.getDSHoaDonDoiTra().size();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String finalId = maHDDT + ngayHD + String.format("%05d", length + 1);
			txtGiaTriMaHDDT.setText(finalId);

			FrmLogin dangNhap = new FrmLogin();
			TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
			// System.out.println(taiKhoan);
			iNhanVien = new NhanVienServiceImpl();
			iKhachHang = new KhachHangServiceImpl();
			// nv = new NhanVien();
			nv = iNhanVien.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
			kh = iKhachHang.timKhachHangTheoMa(lblGiaTriMaKH.getText());
//			Double tienKhachDua = Double.parseDouble(txtTienKhachTra.getText().toString());
			Double tienKhachDua = (double) 0;
			tienPhaiTru = (double) 0;
			String ghiChu = "Không";
			hd = iHoaDon.getHoaDonTheoMa(txtMaHoaDonCu.getText().toString()).get(0);
			HoaDonDoiTra hdDoiTra = new HoaDonDoiTra(finalId, nv, kh, ngayLapHoaDon, ghiChu, tienKhachDua, hd,
					tienPhaiTru);
			iHoaDonDoiTra = new HoaDonDoiTraServiceImpl();
			iHoaDonDoiTra.themHoaDonDoiTra(hdDoiTra);
		}

	}

	public void addCTHDDoiTraMoi() throws SQLException {
		try {
		iChiTietHoaDonDoiTra = new ChiTietHoaDonDoiTraServiceImpl();
		iHoaDonDoiTra = new HoaDonDoiTraServiceImpl();
		HoaDonDoiTra hddt = iHoaDonDoiTra.getHoaDonDoiTraTheoMa(txtGiaTriMaHDDT.getText()).get(0);

		iSanPham = new SanPhamServiceImpl();
		sp = iSanPham.getSachTheoTen(comboBoxSP.getSelectedItem().toString());

		int soLuong = Integer.parseInt(txtSoLuongSPLoi.getText().toString().trim());
		long donGia = Long.parseLong(lblGiaTriGiaSP.getText().toString().trim());
		ChiTietHoaDonDoiTra cthddt = new ChiTietHoaDonDoiTra(hddt, sp, soLuong, donGia);
		iChiTietHoaDonDoiTra.themChiTietHoaDonDoiTra(cthddt);
		int row = 0;
		row = tableChiTietHoaDon.getSelectedRow();
		row = 0;
		row = tableChiTietHoaDon.getSelectedRow();
		tienPhaiTru += Integer.parseInt(txtSoLuongSPLoi.getText().toString())
				* Double.parseDouble(tableChiTietHoaDon.getValueAt(row, 4).toString());
		JOptionPane.showMessageDialog(this, "Đã đổi thành công sản phẩm này");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Sản phẩm này đã đổi rồi");
			return;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnDoi)) {
			if (tableChiTietHoaDon.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Phải chọn sản phẩm cần đổi");
			} else {
				DocDuLieuVaoCombobox();
				btnDoi.setEnabled(false);
				btnDoi2.setEnabled(true);

				txtLoi.setEditable(true);
				txtSoLuongSPLoi.setEditable(true);
				txtSoLuongSPLoi.setEditable(true);
				// btnDoi.setEnabled(true);
			}
		}

		if (o.equals(btnKiemTra)) {
			xoaHetDuLieu();
			try {
				DocDuLieuTuArrayListVaoModel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				DocDuLieuHoaDon();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			// btnDoi.setEnabled(true);

		}
		if (o.equals(btnTaoHDDoiTra)) {
			// taoHDMoi

			btnDoi.setEnabled(true);
			btnTaoHDDoiTra.setEnabled(false);

			try {
				addHoaDonDoiTraMoi();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (o.equals(btnDoi2)) {
			int row = 0;
			row = tableChiTietHoaDon.getSelectedRow();
			btnXong.setEnabled(true);
			btnDoi.setEnabled(true);
			txtSoLuongSPLoi.setEditable(true);
			if (txtSoLuongSPLoi.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(this, "Phải nhập thông tin sản phẩm lỗi và sản phẩm muốn đổi");
			} else {
				try {
					if (Integer.parseInt(txtSoLuongSPLoi.getText()) <= 0
							|| Integer.parseInt(txtSoLuongSPLoi.getText()) <= 0) {
						JOptionPane.showMessageDialog(this,
								"Số lượng sản phẩm đổi và sản phẩm thay thế phải lớn hơn 0");
						btnXong.setEnabled(false);
					} else if (Integer.parseInt(txtSoLuongSPLoi.getText()) > Integer
							.parseInt(tableChiTietHoaDon.getValueAt(row, 3).toString())
							|| Integer.parseInt(txtSoLuongSPLoi.getText()) > Integer
									.parseInt(tableChiTietHoaDon.getValueAt(row, 3).toString())) {
						JOptionPane.showMessageDialog(this,
								"Số lượng sản phẩm đổi và sản phẩm thay thế nhỏ hơn hoặc bằng số lượng sản phẩm trong hóa đơn cũ");
						btnXong.setEnabled(false);
					} else {
						addVaoSachLoi();
//						row = 0;
//						row = tableChiTietHoaDon.getSelectedRow();
//						tienPhaiTru += Integer.parseInt(txtSoLuongSPLoi.getText().toString())
//								* Double.parseDouble(tableChiTietHoaDon.getValueAt(row, 4).toString());
						// taocacCTHDMoi
						btnXong.setEnabled(true);
						try {
							addCTHDDoiTraMoi();
							truSLSachKhiDoi();
							clearAll();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(this, "Số lượng nhập vào phải là số");
				}
			}
		}
		if (o.equals(btnLmMi)) {
			clearAll();
		}
		if (o.equals(btnLmMiHD)) {
			clearHD();
		}
		if (o.equals(btnThanhToan)) {

			txtMaSPDoi.setEditable(false);
			txtLoi.setEditable(false);
			txtSoLuongSPLoi.setEditable(false);
			txtSoLuongSPLoi.setEditable(false);
			btnDoi2.setEnabled(false);
			btnDoi.setEnabled(false);
			btnXong.setEnabled(false);
			btnTaoHDDoiTra.setEnabled(true);
			if (Double.parseDouble(lblGiaTriTienThua.getText().toString()) > 0
					&& !txtTienKhachTra.getText().isEmpty()) {
				if (Double.parseDouble(txtTienKhachTra.getText().toString()) < 0) {
					JOptionPane.showMessageDialog(this, "Tiền khách đưa phải lớn hơn 0");
				} else {
					try {
						iHoaDonDoiTra = new HoaDonDoiTraServiceImpl();

						HoaDonDoiTra hoaDonDoiTra = iHoaDonDoiTra
								.getHoaDonDoiTraTheoMa(txtGiaTriMaHDDT.getText().toString()).get(0);
						hoaDonDoiTra.setTienKhachDua(Double.parseDouble(txtTienKhachTra.getText().toString()));
						System.out.println(tienPhaiTru);
						hoaDonDoiTra.setTienPhaiTru(tienPhaiTru);
						iHoaDonDoiTra.editTienPhaiTru(hoaDonDoiTra);
						iHoaDonDoiTra.editTienKhachTra(hoaDonDoiTra);
						JOptionPane.showMessageDialog(this, "Thanh toán thành công");
						btnThanhToan.setEnabled(false);
						txtMaHoaDonCu.setText("");
						txtTienKhachTra.setText("");
						txtTongTienSP.setText("");
						txtVAT.setText("");
						lblGiaTriTienThua.setText("....");
						clearAll();
						clearHD();
						xoaHetDuLieu();
						tienPhaiTru = 0;
						btnIn.setEnabled(true);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(this, "Tiền nhập khách đưa phải là số");
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ");
			}

		}
		if (o.equals(btnHuy)) {
			txtMaHoaDonCu.setText("");
			txtTienKhachTra.setText("");
			txtTongTienSP.setText("");
			txtVAT.setText("");
			lblGiaTriTienThua.setText("....");
			clearAll();
			clearHD();
			xoaHetDuLieu();
			tienPhaiTru = 0;
		}
		if (o.equals(btnIn)) {
			// System.out.println("in");
			xuatHoaDon(txtGiaTriMaHDDT.getText().toString());
		}
		if (o.equals(btnXong)) {

			iChiTietHoaDonDoiTra = new ChiTietHoaDonDoiTraServiceImpl();
			dsCTHDDT = iChiTietHoaDonDoiTra.getCTHoaDonDoiTraTheoMaHoaDonDoiTra(txtGiaTriMaHDDT.getText().toString());
			double tongTien = 0;
			for (ChiTietHoaDonDoiTra chiTietHoaDonDoiTra : dsCTHDDT) {
				tongTien += (chiTietHoaDonDoiTra.getDonGia() * 1.2 * chiTietHoaDonDoiTra.getSoLuong());
			}

			tongTien = tongTien - tienPhaiTru;

			double vat = 0;

			if (tongTien == 0) {
				vat = 5000;
			} else {
				vat = 5000 + tongTien * 0.1 * 1.1;
			}

			txtTongTienSP.setText(String.valueOf(tongTien * 1.1 + vat));
			txtVAT.setText(String.valueOf(vat));
			JOptionPane.showMessageDialog(this, "Vui lòng thanh toán");
			txtTienKhachTra.requestFocus();
			btnThanhToan.setEnabled(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableChiTietHoaDon.getSelectedRow();
		txtMaSPDoi.setText(modelChiTietHoaDon.getValueAt(row, 1).toString());
		DocDuLieuVaoCombobox();
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

	public void clearAll() {
		txtMaSPDoi.setText("");
		txtLoi.setText("");
		lblGiaTriGiaSP.setText("..........");
		txtSoLuongSPLoi.setText("");

	}

	public void clearHD() {
		lblGiaTriNgayHienTai.setText("...");
		lblGiaTriMaNV.setText("..........");
		lblGiaTriMaKH.setText("..........");
		lblGiaTriTongTien.setText("0");
	}

	public void tinhTienThua() {
		try {
			double tienThua = 0;
			Double tienKhachTra = Double.parseDouble(txtTienKhachTra.getText().toString());
			Double tongTien = Double.parseDouble(txtTongTienSP.getText().toString());
			if (tongTien != 0) {
				tienThua = tienKhachTra - tongTien;
			} else {
				tienThua = tienKhachTra - Double.parseDouble(txtVAT.getText().toString());
			}
			lblGiaTriTienThua.setText(String.valueOf(tienThua));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Tiền nhập vào phải là số");
		}

	}

	public void xoaHetDuLieu() {
		DefaultTableModel dtm = (DefaultTableModel) tableChiTietHoaDon.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void xuatHoaDon(String maHDDT) {
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

}
