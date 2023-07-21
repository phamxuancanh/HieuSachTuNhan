package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import com.toedter.calendar.JDayChooser;

import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.SanPhamServiceImpl;

import javax.swing.border.LineBorder;

public class Pn_ThongKeNhanVien extends JPanel implements ActionListener, MouseListener {

	/**
	 * Create the panel.
	 */
	private static DefaultCategoryDataset dataset;
	private JTabbedPane tabbedPaneThongKe;
	private JPanel panelThongKeSanPham;
	private JPanel panelTongSoLuongSach;
	private JLabel lblTongSoLuongSach;
	private JPanel panelTongSoLuongVanPhongPham;
	private JLabel lblTongSoVanPhongPham;
	private JPanel panelTongSoSachLoi;
	private JLabel lblTongSoSachLoi;
	private JPanel panelLocSPBanChay;
	private JPanel panelSachBanChayNhat;
	private JLabel lblSanPhamBanChayNhat;
	private JButton btnLocSanPham;
	private JLabel lblFromThongKeSP;
	private JDateChooser dateChooserFromThongKeSP;
	private JDateChooser dateChooserToThongKeSP;
	private JLabel lblToThongKeSP;
	private JLabel lblBoLocSPBanChay;
	private JPanel panel_ThongKeDoanhThuBanThan;
	private ChartPanel chartPanel;
	private JLabel lblTongHoaDonBanDuoc;
	private JLabel lblTongDoanhThu;
	private JLabel lblTo;
	private JDateChooser dateChooserFromDoanhThu;
	private JLabel lblFrom;
	private JComboBox comboBoxTieuChiDoanhThu;
	private JDateChooser dateChooserToDoanhThu;
	private JLabel txtTieuDe;
	private JButton btnLocDoanhThuNV;
	private JLabel lblMaSanPham;
	private JLabel lblLoaiSanPham;
	private JLabel lblTenSanPham;
	private JLabel lblGiaBan;
	private JLabel lblSoLuongDaBan;
	private JPanel panelHinhAnh;
	private JLabel lblNewLabel;
	// private DefaultCategoryDataset dataset;
	private SanPhamServiceImpl iSanPham;
	private KhachHangServiceImpl iKhachHang;
	private HoaDonServiceImpl iHoaDon;
	private List<NhanVien> dsNV;
	private List<NhanVien> dsNV2;
	private List<SanPham> dsSP;
	private List<SanPham> dsSP2;
	private List<KhachHang> dsKH;
	private List<KhachHang> dsKH2;
	private JLabel lblMaSPTop1;
	private JLabel lblLoaiSPTop1;
	private JLabel lblTenSPTop1;
	private JLabel lblGiaSPTop1;
	private JLabel lblSoLuongSPTop1;
	private JLabel lblValueSoLuongSach;
	private JLabel lblValueSoLuongSachLoi;
	private JLabel lblValueSoLuongVPP;
	private JLabel lblGiaTriDoanhThu;
	private JLabel lblGiaTriTongHoaDon;
	private NhanVienServiceImpl iNhanVien;
	private NhanVien nv;
	private String tenNV;
	private String maNV;

	public static JFreeChart createChart() {
		JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU", "Tháng", "Doanh thu", createDataset(),
				PlotOrientation.VERTICAL, false, false, false);
		return barChart;
	}

	private static CategoryDataset createDataset() {
		dataset = new DefaultCategoryDataset();
		dataset.addValue(0, "Doanh thu", "1");
		dataset.addValue(0, "Doanh thu", "2");
		dataset.addValue(0, "Doanh thu", "3");
		dataset.addValue(0, "Doanh thu", "4");
		dataset.addValue(0, "Doanh thu", "5");
		dataset.addValue(0, "Doanh thu", "6");
		dataset.addValue(0, "Doanh thu", "7");
		dataset.addValue(0, "Doanh thu", "8");
		dataset.addValue(0, "Doanh thu", "9");
		dataset.addValue(0, "Doanh thu", "10");
		dataset.addValue(0, "Doanh thu", "11");
		dataset.addValue(0, "Doanh thu", "12");
		return dataset;
	}

	public Pn_ThongKeNhanVien() throws SQLException {

		setLayout(null);
		setSize(1500, 700);
		tabbedPaneThongKe = new JTabbedPane(JTabbedPane.TOP);
		// tabbedPaneThongKe.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPaneThongKe.setBounds(27, 79, 1450, 610);
		add(tabbedPaneThongKe);

		panelThongKeSanPham = new JPanel();
		panelThongKeSanPham.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.addTab("Thống kê sản phẩm", null, panelThongKeSanPham, null);
		panelThongKeSanPham.setLayout(null);

		panelTongSoLuongSach = new JPanel();
		panelTongSoLuongSach.setBounds(925, 11, 263, 133);
		panelThongKeSanPham.add(panelTongSoLuongSach);
		panelTongSoLuongSach.setLayout(null);

		lblTongSoLuongSach = new JLabel("    Tổng số lượng sách: \r\n");
		lblTongSoLuongSach.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/books.png")));
		lblTongSoLuongSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongSoLuongSach.setBounds(0, 0, 263, 36);
		panelTongSoLuongSach.add(lblTongSoLuongSach);

		lblValueSoLuongSach = new JLabel("New label");
		lblValueSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblValueSoLuongSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueSoLuongSach.setBounds(0, 47, 263, 86);
		panelTongSoLuongSach.add(lblValueSoLuongSach);

		panelTongSoLuongVanPhongPham = new JPanel();
		panelTongSoLuongVanPhongPham.setBounds(925, 372, 263, 133);
		panelThongKeSanPham.add(panelTongSoLuongVanPhongPham);
		panelTongSoLuongVanPhongPham.setLayout(null);

		lblTongSoVanPhongPham = new JLabel("    Tổng số văn phòng phẩm: ");
		lblTongSoVanPhongPham.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/stationery.png")));
		lblTongSoVanPhongPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSoVanPhongPham.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongSoVanPhongPham.setBounds(0, 0, 263, 37);
		panelTongSoLuongVanPhongPham.add(lblTongSoVanPhongPham);

		lblValueSoLuongVPP = new JLabel("New label");
		lblValueSoLuongVPP.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueSoLuongVPP.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblValueSoLuongVPP.setBounds(0, 48, 263, 86);
		panelTongSoLuongVanPhongPham.add(lblValueSoLuongVPP);

		panelTongSoSachLoi = new JPanel();
		panelTongSoSachLoi.setBounds(925, 187, 263, 133);
		panelThongKeSanPham.add(panelTongSoSachLoi);
		panelTongSoSachLoi.setLayout(null);

		lblTongSoSachLoi = new JLabel("    Tổng số sách lỗi: ");
		lblTongSoSachLoi.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/sachloi.png")));
		lblTongSoSachLoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSoSachLoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongSoSachLoi.setBounds(0, 0, 263, 40);
		panelTongSoSachLoi.add(lblTongSoSachLoi);

		lblValueSoLuongSachLoi = new JLabel("New label");
		lblValueSoLuongSachLoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueSoLuongSachLoi.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblValueSoLuongSachLoi.setBounds(0, 47, 263, 86);
		panelTongSoSachLoi.add(lblValueSoLuongSachLoi);

		panelLocSPBanChay = new JPanel();
		panelLocSPBanChay.setBackground(new Color(173, 255, 47));
		panelLocSPBanChay.setBounds(155, 23, 551, 523);
		panelThongKeSanPham.add(panelLocSPBanChay);
		panelLocSPBanChay.setLayout(null);

		panelSachBanChayNhat = new JPanel();
		panelSachBanChayNhat.setBounds(28, 297, 489, 201);
		panelLocSPBanChay.add(panelSachBanChayNhat);
		panelSachBanChayNhat.setLayout(null);

		lblSanPhamBanChayNhat = new JLabel("    Sản phẩm bán chạy nhất: ");
		lblSanPhamBanChayNhat.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/sachchay.png")));
		lblSanPhamBanChayNhat.setHorizontalAlignment(SwingConstants.CENTER);
		lblSanPhamBanChayNhat.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSanPhamBanChayNhat.setBounds(0, 0, 489, 42);
		panelSachBanChayNhat.add(lblSanPhamBanChayNhat);

		lblMaSanPham = new JLabel("Mã sản phẩm:");
		lblMaSanPham.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaSanPham.setBounds(204, 53, 87, 14);
		panelSachBanChayNhat.add(lblMaSanPham);

		lblLoaiSanPham = new JLabel("Loại sản phẩm: ");
		lblLoaiSanPham.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLoaiSanPham.setBounds(204, 78, 87, 14);
		panelSachBanChayNhat.add(lblLoaiSanPham);

		lblTenSanPham = new JLabel("Tên sản phẩm: ");
		lblTenSanPham.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenSanPham.setBounds(204, 103, 87, 14);
		panelSachBanChayNhat.add(lblTenSanPham);

		lblGiaBan = new JLabel("Giá bán: ");
		lblGiaBan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGiaBan.setBounds(203, 128, 72, 14);
		panelSachBanChayNhat.add(lblGiaBan);

		lblSoLuongDaBan = new JLabel("Số lượng đã bán: ");
		lblSoLuongDaBan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSoLuongDaBan.setBounds(204, 153, 104, 14);
		panelSachBanChayNhat.add(lblSoLuongDaBan);

		panelHinhAnh = new JPanel();
		panelHinhAnh.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelHinhAnh.setBounds(10, 44, 159, 146);
		panelSachBanChayNhat.add(panelHinhAnh);
		panelHinhAnh.setLayout(null);

		lblNewLabel = new JLabel("Hình ảnh");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 159, 146);
		panelHinhAnh.add(lblNewLabel);

		lblMaSPTop1 = new JLabel("...");
		lblMaSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaSPTop1.setBounds(311, 53, 124, 14);
		panelSachBanChayNhat.add(lblMaSPTop1);

		lblLoaiSPTop1 = new JLabel("...");
		lblLoaiSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLoaiSPTop1.setBounds(311, 78, 124, 14);
		panelSachBanChayNhat.add(lblLoaiSPTop1);

		lblTenSPTop1 = new JLabel("...");
		lblTenSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenSPTop1.setBounds(311, 103, 168, 14);
		panelSachBanChayNhat.add(lblTenSPTop1);

		lblGiaSPTop1 = new JLabel("...");
		lblGiaSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGiaSPTop1.setBounds(311, 128, 124, 14);
		panelSachBanChayNhat.add(lblGiaSPTop1);

		lblSoLuongSPTop1 = new JLabel("...");
		lblSoLuongSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSoLuongSPTop1.setBounds(311, 153, 124, 14);
		panelSachBanChayNhat.add(lblSoLuongSPTop1);

		btnLocSanPham = new JButton("Lọc");
		btnLocSanPham.setBounds(157, 237, 92, 35);
		panelLocSPBanChay.add(btnLocSanPham);
		btnLocSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		btnLocSanPham.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/filter.png")));
		btnLocSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblFromThongKeSP = new JLabel("Từ: ");
		lblFromThongKeSP.setBounds(41, 104, 35, 14);
		panelLocSPBanChay.add(lblFromThongKeSP);
		lblFromThongKeSP.setFont(new Font("Tahoma", Font.BOLD, 13));

		dateChooserFromThongKeSP = new JDateChooser();
		dateChooserFromThongKeSP.setBounds(157, 98, 164, 33);
		panelLocSPBanChay.add(dateChooserFromThongKeSP);

		dateChooserToThongKeSP = new JDateChooser();
		dateChooserToThongKeSP.setBounds(157, 171, 164, 33);
		panelLocSPBanChay.add(dateChooserToThongKeSP);

		lblToThongKeSP = new JLabel("Đến: ");
		lblToThongKeSP.setBounds(42, 180, 46, 14);
		panelLocSPBanChay.add(lblToThongKeSP);
		lblToThongKeSP.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblBoLocSPBanChay = new JLabel("Sản phẩm bán chạy nhất");
		lblBoLocSPBanChay.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoLocSPBanChay.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBoLocSPBanChay.setForeground(new Color(255, 0, 0));
		lblBoLocSPBanChay.setBackground(new Color(255, 0, 0));
		lblBoLocSPBanChay.setBounds(0, 11, 551, 35);
		panelLocSPBanChay.add(lblBoLocSPBanChay);

		panel_ThongKeDoanhThuBanThan = new JPanel();
		panel_ThongKeDoanhThuBanThan.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.addTab("Thống kê doanh thu bản thân", null, panel_ThongKeDoanhThuBanThan, null);
		panel_ThongKeDoanhThuBanThan.setLayout(null);

		chartPanel = new ChartPanel(createChart());
		chartPanel.setBackground(new Color(0, 206, 209));
		chartPanel.setBounds(359, 33, 1065, 490);
		panel_ThongKeDoanhThuBanThan.add(chartPanel);
		lblTongDoanhThu = new JLabel("Tổng doanh thu: ");
		lblTongDoanhThu.setIcon(new ImageIcon(Pn_ThongKeNhanVien.class.getResource("/gui/icon/count.png")));
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTongDoanhThu.setBounds(44, 316, 209, 28);
		panel_ThongKeDoanhThuBanThan.add(lblTongDoanhThu);

		lblTongHoaDonBanDuoc = new JLabel("Tổng hóa đơn bán được: ");
		lblTongHoaDonBanDuoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongHoaDonBanDuoc.setIcon(new ImageIcon(Pn_ThongKeNhanVien.class.getResource("/gui/icon/money.png")));
		lblTongHoaDonBanDuoc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTongHoaDonBanDuoc.setBounds(32, 423, 254, 28);
		panel_ThongKeDoanhThuBanThan.add(lblTongHoaDonBanDuoc);

		lblTo = new JLabel("Đến:  ");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTo.setBounds(32, 127, 72, 28);
		panel_ThongKeDoanhThuBanThan.add(lblTo);

		lblFrom = new JLabel("Từ:  ");
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFrom.setBounds(32, 44, 64, 28);
		panel_ThongKeDoanhThuBanThan.add(lblFrom);

		dateChooserFromDoanhThu = new JDateChooser();
		dateChooserFromDoanhThu.setBounds(106, 56, 169, 20);
		panel_ThongKeDoanhThuBanThan.add(dateChooserFromDoanhThu);

		dateChooserToDoanhThu = new JDateChooser();
		dateChooserToDoanhThu.setBounds(106, 135, 169, 20);
		panel_ThongKeDoanhThuBanThan.add(dateChooserToDoanhThu);

		comboBoxTieuChiDoanhThu = new JComboBox();
		comboBoxTieuChiDoanhThu.setBounds(32, 208, 154, 35);
		comboBoxTieuChiDoanhThu.addItem("3 tháng gần nhất");
		comboBoxTieuChiDoanhThu.addItem("6 tháng gần nhất");
		comboBoxTieuChiDoanhThu.addItem("9 tháng gần nhất");
		panel_ThongKeDoanhThuBanThan.add(comboBoxTieuChiDoanhThu);

		btnLocDoanhThuNV = new JButton("Lọc");
		btnLocDoanhThuNV.setIcon(new ImageIcon(Pn_ThongKeNhanVien.class.getResource("/gui/icon/filter.png")));
		btnLocDoanhThuNV.setBounds(210, 208, 89, 35);
		panel_ThongKeDoanhThuBanThan.add(btnLocDoanhThuNV);

		lblGiaTriDoanhThu = new JLabel("0");
		lblGiaTriDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaTriDoanhThu.setBounds(44, 373, 231, 28);
		panel_ThongKeDoanhThuBanThan.add(lblGiaTriDoanhThu);

		lblGiaTriTongHoaDon = new JLabel("0");
		lblGiaTriTongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaTriTongHoaDon.setBounds(44, 487, 209, 28);
		panel_ThongKeDoanhThuBanThan.add(lblGiaTriTongHoaDon);

		txtTieuDe = new JLabel("THỐNG KÊ");
		txtTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		txtTieuDe.setForeground(new Color(0, 191, 255));
		txtTieuDe.setFont(new Font("Tahoma", Font.BOLD, 26));
		txtTieuDe.setBounds(0, 11, 1500, 51);
		add(txtTieuDe);

		btnLocDoanhThuNV.addActionListener(this);
		btnLocSanPham.addActionListener(this);

		FrmLogin dangNhap = new FrmLogin();
		TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
		// System.out.println(taiKhoan);
		iNhanVien = new NhanVienServiceImpl();
		nv = new NhanVien();
		nv = iNhanVien.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
		tenNV = nv.getHoTenNhanVien();

		maNV = (iNhanVien.timNhanVienTheoTen(tenNV)).getMaNhanVien();
		setChart();
		comboBoxTieuChiDoanhThu.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				setBieuDoVe0();
				setChart();
			}
		});
		// System.out.println(maNV);
		iSanPham = new SanPhamServiceImpl();
		try {
			if (iSanPham.getSoLuongSachLoi() > 0) {
				lblValueSoLuongSachLoi.setText(String.valueOf(iSanPham.getSoLuongSachLoi()));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (iSanPham.getSoLuongSachTon() > 0) {
				lblValueSoLuongSach.setText(String.valueOf(iSanPham.getSoLuongSachTon()));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (iSanPham.getSoLuongVPPTon() > 0) {
				lblValueSoLuongVPP.setText(String.valueOf(iSanPham.getSoLuongVPPTon()));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
		if (obj.equals(btnLocSanPham)) {
			iSanPham = new SanPhamServiceImpl();
			if (getNgayFromJDateChooser(dateChooserFromThongKeSP) != null
					&& getNgayFromJDateChooser(dateChooserToThongKeSP) != null) {
				if (iSanPham.getSanPhamBanNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromThongKeSP),
						getNgayFromJDateChooser(dateChooserToThongKeSP)) != null) {
					dsSP = iSanPham.getSanPhamBanNhieuNhatTheoNgayTuChon(
							getNgayFromJDateChooser(dateChooserFromThongKeSP),
							getNgayFromJDateChooser(dateChooserToThongKeSP));
					for (SanPham sp : dsSP) {
						iSanPham = new SanPhamServiceImpl();
						try {
							lblMaSPTop1.setText(iSanPham.timSanPhamTheoMa(sp.getMaSanPham()).getMaSanPham());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						try {
							lblLoaiSPTop1.setText(iSanPham.timSanPhamTheoMa(sp.getMaSanPham()).getLoaiSanPham());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							lblGiaSPTop1.setText(String
									.valueOf(iSanPham.timSanPhamTheoMa(sp.getMaSanPham()).getGiaNhap()
											+ iSanPham.timSanPhamTheoMa(sp.getMaSanPham()).getGiaNhap() * 10 / 100)
									+ "đ");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						try {
							lblSoLuongSPTop1.setText(String.valueOf(iSanPham.getSoLuongBanCuaSanPhamChayNhat(
									getNgayFromJDateChooser(dateChooserFromThongKeSP),
									getNgayFromJDateChooser(dateChooserToThongKeSP))));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						try {
							if (iSanPham.timSanPhamTheoMa(sp.getMaSanPham()).getLoaiSanPham().equals("Sách")) {
								// System.out.println("dc");
								lblTenSPTop1.setText(iSanPham.getSachTheoMaSP(sp.getMaSanPham()).getTenSach());
							} else if (iSanPham.timSanPhamTheoMa(sp.getMaSanPham()).getLoaiSanPham()
									.equals("Văn phòng phẩm")) {
								// System.out.println("dc");
								lblTenSPTop1.setText(iSanPham.getVPPTheoMaSP(sp.getMaSanPham()).getTenVanPhongPham());
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		}
		if (obj.equals(btnLocDoanhThuNV)) {
			iHoaDon = new HoaDonServiceImpl();
			iNhanVien = new NhanVienServiceImpl();
			if (getNgayFromJDateChooser(dateChooserFromDoanhThu) != null
					&& getNgayFromJDateChooser(dateChooserToDoanhThu) != null) {

				try {
					if (iHoaDon.getDoanhThuTheoMaNhanVien(getNgayFromJDateChooser(dateChooserFromDoanhThu),
							getNgayFromJDateChooser(dateChooserToDoanhThu),
							((NhanVien) iNhanVien.timNhanVienTheoTen(tenNV)).getMaNhanVien()) > 0) {
						lblGiaTriDoanhThu.setText(String.valueOf(
								iHoaDon.getDoanhThuTheoMaNhanVien(getNgayFromJDateChooser(dateChooserFromDoanhThu),
										getNgayFromJDateChooser(dateChooserToDoanhThu),
										((NhanVien) iNhanVien.timNhanVienTheoTen(tenNV)).getMaNhanVien()))
								+ "đ");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if (iHoaDon.getSoLuongHoaDonTheoMaNV(getNgayFromJDateChooser(dateChooserFromDoanhThu),
							getNgayFromJDateChooser(dateChooserToDoanhThu),
							((NhanVien) iNhanVien.timNhanVienTheoTen(tenNV)).getMaNhanVien()) > 0) {
						lblGiaTriTongHoaDon.setText(String.valueOf(
								iHoaDon.getSoLuongHoaDonTheoMaNV(getNgayFromJDateChooser(dateChooserFromDoanhThu),
										getNgayFromJDateChooser(dateChooserToDoanhThu),
										((NhanVien) iNhanVien.timNhanVienTheoTen(tenNV)).getMaNhanVien())));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public LocalDate getNgayFromJDateChooser(JDateChooser ngay) {
		if (ngay.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày");
			return null;
		}
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String dayKT = sdf2.format(ngay.getDate());
		String dateKT[] = dayKT.split("-");
		int namKT = Integer.parseInt(dateKT[0]);
		int thangKT = Integer.parseInt(dateKT[1]);
		int ngayKT = Integer.parseInt(dateKT[2]);
		LocalDate lcDateKT = LocalDate.of(namKT, thangKT, ngayKT);
		return lcDateKT;
	}

	public LocalDate getNgayHienTai() {
		LocalDate lt = LocalDate.now();
		return lt;
	}

	public void setChart() {
		int count = 0;
		iHoaDon = new HoaDonServiceImpl();
		LocalDate nowMinus = null;
		LocalDate now = getNgayHienTai();
		if (comboBoxTieuChiDoanhThu.getSelectedIndex() == 0) {
			count = 3;
			while (count > 0) {
				nowMinus = now.minusMonths(1);
				try {
					dataset.setValue(iHoaDon.getDoanhThuTheoMaNhanVien(nowMinus, now, maNV), "Doanh thu",
							String.valueOf(nowMinus.getMonthValue()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				now = nowMinus;
				count--;
			}
		}

		else if (comboBoxTieuChiDoanhThu.getSelectedIndex() == 1) {
			count = 6;
			while (count > 0) {
				nowMinus = now.minusMonths(1);
				try {
					dataset.setValue(iHoaDon.getDoanhThuTheoMaNhanVien(nowMinus, now, maNV), "Doanh thu",
							String.valueOf(nowMinus.getMonthValue()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				now = nowMinus;
				count--;
			}
		}

		else {
			count = 9;
			while (count > 0) {
				nowMinus = now.minusMonths(1);
				try {
					dataset.setValue(iHoaDon.getDoanhThuTheoMaNhanVien(nowMinus, now, maNV), "Doanh thu",
							String.valueOf(nowMinus.getMonthValue()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				now = nowMinus;
				count--;
			}
		}

	}

	public void setBieuDoVe0() {
		dataset.setValue(0, "Doanh thu", "1");
		dataset.setValue(0, "Doanh thu", "2");
		dataset.setValue(0, "Doanh thu", "3");
		dataset.setValue(0, "Doanh thu", "4");
		dataset.setValue(0, "Doanh thu", "5");
		dataset.setValue(0, "Doanh thu", "6");
		dataset.setValue(0, "Doanh thu", "7");
		dataset.setValue(0, "Doanh thu", "8");
		dataset.setValue(0, "Doanh thu", "9");
		dataset.setValue(0, "Doanh thu", "10");
		dataset.setValue(0, "Doanh thu", "11");
		dataset.setValue(0, "Doanh thu", "12");
	}
}
