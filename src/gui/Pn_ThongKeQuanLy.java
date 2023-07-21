package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.SanPhamServiceImpl;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class Pn_ThongKeQuanLy extends JPanel implements MouseListener, ActionListener {
	private static DefaultCategoryDataset dataset;
	/**
	 * Create the panel.
	 * 
	 */

	private JScrollPane sp_top10KH;
	private JTable table_top10KH;
	private DefaultTableModel tableModel_top10KH;
	private JTabbedPane tabbedPaneThongKe;
	private JPanel panelThongKeDoanhThu;
	private JLabel lblFrom;
	private JLabel lblTo;
	private JLabel lblLocTheo;
	private JDateChooser dateChooserFromDoanhThu;
	private JDateChooser dateChooserToDoanhThu;
	private JComboBox comboBoxTieuChiDoanhThu;
	private JButton btnLocDT;
	private JLabel lblTongSoHoaDon;
	private JLabel lblDoanhThu;
	private ChartPanel chartPanel;
	private JLabel lblconCount;
	private JLabel lblIconMoney;
	private JLabel lblGiaTriTongHoaDon;
	private JLabel lblGiaTriDoanhThu;
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
	private JButton btnLocSP;
	private JLabel lblFromThongKeSP;
	private JDateChooser dateChooserFromThongKeSP;
	private JDateChooser dateChooserToThongKeSP;
	private JLabel lblToThongKeSP;
	private JLabel lblBoLocSPBanChay;
	private JPanel panelThongKeKhachHang;
	private JPanel panel_top10;
	private JLabel lblTitleDSKHThanThiet;
	private JPanel panel_loc;
	private JLabel lblFromThongKeKH;
	private JLabel lblToThongKeKH;
	private JDateChooser dateChooserFromKH;
	private JDateChooser dateChooserToKH;
	private JLabel lblTenKHMuaNhieuNhat;
	private JButton btnLocKH;
	private JLabel lblSoTienKhachDaMua;
	private JLabel txtTieuDe;
	private JLabel titleLocKH;
	private NhanVienServiceImpl iNhanVien;
	private SanPhamServiceImpl iSanPham;
	private HoaDonServiceImpl iHoaDon;
	private KhachHangServiceImpl iKhachHang;
	private JLabel lblValueSoLuongSach;
	private JLabel lblValueSoLuongVPP;
	private JLabel lblValueSoLuongSachLoi;
	private List<NhanVien> dsNV;
	private List<NhanVien> dsNV2;
	private List<SanPham> dsSP;
	private List<SanPham> dsSP2;
	private List<KhachHang> dsKH;
	private List<KhachHang> dsKH2;
	private NhanVien nv2;
	private JLabel valueTop1NV;
	private JLabel lblTop1NV;
	private JLabel lblTenKHValue;
	private JLabel lblSoTienDaMuaValue;
	private JLabel lblmaSPTop1;
	private JLabel lblloaiSpTop1;
	private JLabel lblGiaBanTop1;
	private JLabel lblSoLuongBanTop1;
	private JPanel panelSanPhamTop1;
	private JLabel lblHinhAnhTop1;
	private JLabel lblMaSP;
	private JLabel lblTenSPTop1;
	private JLabel lblTenSanPham;
	private JLabel lblTenSP;
	private JLabel lblGiaBan;
	private JLabel lblSoLuongDaBan;
	private JPanel panelThongKeNhanVien;
	private JButton btnLocSP_1;
	private JLabel lblFromThongKeSP_1;
	private JDateChooser dateChooserFromThongKeNV;
	private JDateChooser dateChooserToThongKeNV;
	private JLabel lblToThongKeSP_1;
	private DefaultTableModel tableModel_NV;
	private JTable table_NV;
	private Component sp_tableNV;
	private JButton btnLocNV;

	public static void main(String[] args) {
		ChartPanel chartPanel = new ChartPanel(createChart());
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));

		Pn_ThongKeQuanLy frame = new Pn_ThongKeQuanLy();
		frame.add(chartPanel);

		frame.setVisible(true);
	}

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
		// dataset.setValue(5000, "Doanh thu", "9");
		return dataset;
	}

	public Pn_ThongKeQuanLy() {

		setLayout(null);
		setSize(1500, 700);
		tabbedPaneThongKe = new JTabbedPane(JTabbedPane.TOP);
		// tabbedPaneThongKe.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPaneThongKe.setBounds(27, 79, 1450, 610);
		add(tabbedPaneThongKe);

		panelThongKeDoanhThu = new JPanel();
		panelThongKeDoanhThu.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.addTab("Thống kê doanh thu", null, panelThongKeDoanhThu, null);
		panelThongKeDoanhThu.setLayout(null);
		panelThongKeDoanhThu.setSize(900, 500);

		lblFrom = new JLabel("Từ ngày: ");
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFrom.setBounds(10, 52, 77, 32);
		panelThongKeDoanhThu.add(lblFrom);

		lblTo = new JLabel("Đến ngày: ");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTo.setBounds(10, 133, 92, 32);
		panelThongKeDoanhThu.add(lblTo);

		lblLocTheo = new JLabel("Lọc theo: ");
		lblLocTheo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLocTheo.setBounds(10, 207, 77, 32);
		panelThongKeDoanhThu.add(lblLocTheo);

		dateChooserFromDoanhThu = new JDateChooser();
		dateChooserFromDoanhThu.setBounds(97, 58, 154, 26);
		panelThongKeDoanhThu.add(dateChooserFromDoanhThu);

		dateChooserToDoanhThu = new JDateChooser();
		dateChooserToDoanhThu.setBounds(97, 133, 154, 26);
		panelThongKeDoanhThu.add(dateChooserToDoanhThu);

		comboBoxTieuChiDoanhThu = new JComboBox();
		comboBoxTieuChiDoanhThu.setBounds(97, 208, 154, 32);
		comboBoxTieuChiDoanhThu.addItem("3 tháng gần nhất");
		comboBoxTieuChiDoanhThu.addItem("6 tháng gần nhất");
		comboBoxTieuChiDoanhThu.addItem("9 tháng gần nhất");
		panelThongKeDoanhThu.add(comboBoxTieuChiDoanhThu);

		btnLocDT = new JButton("Lọc");
		btnLocDT.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/filter.png")));
		// btnLocDT.setBackground(new Color(192, 192, 192));
		btnLocDT.setBounds(261, 208, 88, 32);
		panelThongKeDoanhThu.add(btnLocDT);

		lblTongSoHoaDon = new JLabel("Tổng số hóa đơn: ");
		lblTongSoHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTongSoHoaDon.setBounds(10, 389, 115, 14);
		panelThongKeDoanhThu.add(lblTongSoHoaDon);

		lblDoanhThu = new JLabel("Doanh thu: ");
		lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDoanhThu.setBounds(10, 470, 77, 14);
		panelThongKeDoanhThu.add(lblDoanhThu);

		chartPanel = new ChartPanel(createChart());
		chartPanel.setBackground(new Color(0, 206, 209));
		chartPanel.setBounds(359, 33, 1065, 490);
		panelThongKeDoanhThu.add(chartPanel);

		lblconCount = new JLabel("");
		lblconCount.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/count.png")));
		lblconCount.setBounds(10, 414, 48, 45);
		panelThongKeDoanhThu.add(lblconCount);

		lblIconMoney = new JLabel("");
		lblIconMoney.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/money.png")));
		lblIconMoney.setBounds(10, 495, 36, 34);
		panelThongKeDoanhThu.add(lblIconMoney);

		lblGiaTriTongHoaDon = new JLabel("0");
		lblGiaTriTongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaTriTongHoaDon.setBounds(56, 427, 46, 14);
		panelThongKeDoanhThu.add(lblGiaTriTongHoaDon);

		lblGiaTriDoanhThu = new JLabel("0");
		lblGiaTriDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaTriDoanhThu.setBounds(56, 497, 257, 14);
		panelThongKeDoanhThu.add(lblGiaTriDoanhThu);

		lblTop1NV = new JLabel("Nhân viên bán được nhiều nhất: ");
		lblTop1NV.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTop1NV.setBounds(10, 282, 218, 14);
		panelThongKeDoanhThu.add(lblTop1NV);

		valueTop1NV = new JLabel("");
		valueTop1NV.setFont(new Font("Tahoma", Font.BOLD, 13));
		valueTop1NV.setBounds(10, 319, 218, 14);
		panelThongKeDoanhThu.add(valueTop1NV);

		// ----------------------------------------------------
		panelThongKeKhachHang = new JPanel();
		panelThongKeKhachHang.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.addTab("Thống kê khách hàng", null, panelThongKeKhachHang, null);
		panelThongKeKhachHang.setLayout(null);

		panel_top10 = new JPanel();
		panel_top10.setBounds(798, 57, 547, 477);
		panelThongKeKhachHang.add(panel_top10);
		panel_top10.setLayout(null);

		lblTitleDSKHThanThiet = new JLabel("Danh sách top 10 khách hàng thân thiết:");
		lblTitleDSKHThanThiet.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleDSKHThanThiet.setBounds(0, 0, 547, 34);
		panel_top10.add(lblTitleDSKHThanThiet);
		lblTitleDSKHThanThiet.setFont(new Font("Tahoma", Font.BOLD, 13));

		String header_top10KH[] = { "STT", "Mã khách hàng", "Tên khách hàng", "Số tiền đã mua", "Số hóa đơn đã mua" };
		tableModel_top10KH = new DefaultTableModel(header_top10KH, 0);

		table_top10KH = new JTable(tableModel_top10KH);
		sp_top10KH = new JScrollPane(table_top10KH, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_top10KH.setBounds(0, 45, 547, 421);
		panel_top10.add(sp_top10KH);
		table_top10KH.getColumnModel().getColumn(0).setPreferredWidth(20);

		table_top10KH.getColumnModel().getColumn(0).setPreferredWidth(20);
		panel_loc = new JPanel();
		panel_loc.setBounds(54, 57, 673, 477);
		panelThongKeKhachHang.add(panel_loc);
		panel_loc.setLayout(null);

		titleLocKH = new JLabel("Lọc khách hàng mua nhiều nhất theo thời gian");
		titleLocKH.setFont(new Font("Tahoma", Font.BOLD, 13));
		titleLocKH.setHorizontalAlignment(SwingConstants.CENTER);
		titleLocKH.setBounds(0, 25, 673, 32);
		panel_loc.add(titleLocKH);

		lblFromThongKeKH = new JLabel("Từ: ");
		lblFromThongKeKH.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFromThongKeKH.setBounds(41, 84, 46, 14);
		panel_loc.add(lblFromThongKeKH);

		lblToThongKeKH = new JLabel("Đến: ");
		lblToThongKeKH.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblToThongKeKH.setBounds(41, 146, 46, 14);
		panel_loc.add(lblToThongKeKH);

		dateChooserFromKH = new JDateChooser();
		dateChooserFromKH.setBounds(196, 84, 151, 32);
		panel_loc.add(dateChooserFromKH);

		dateChooserToKH = new JDateChooser();
		dateChooserToKH.setBounds(195, 146, 152, 32);
		panel_loc.add(dateChooserToKH);

		lblTenKHMuaNhieuNhat = new JLabel("Tên Khách Hàng: ");
		lblTenKHMuaNhieuNhat.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTenKHMuaNhieuNhat.setBounds(41, 270, 125, 32);
		panel_loc.add(lblTenKHMuaNhieuNhat);

		btnLocKH = new JButton("Lọc");
		btnLocKH.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLocKH.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/filter.png")));
		btnLocKH.setBounds(389, 146, 89, 32);
		panel_loc.add(btnLocKH);

		lblSoTienKhachDaMua = new JLabel("Số tiền đã mua: ");
		lblSoTienKhachDaMua.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSoTienKhachDaMua.setBounds(41, 348, 125, 14);
		panel_loc.add(lblSoTienKhachDaMua);

		lblTenKHValue = new JLabel("...");
		lblTenKHValue.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTenKHValue.setBounds(196, 275, 236, 23);
		panel_loc.add(lblTenKHValue);

		lblSoTienDaMuaValue = new JLabel("0");
		lblSoTienDaMuaValue.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSoTienDaMuaValue.setBounds(196, 348, 243, 14);
		panel_loc.add(lblSoTienDaMuaValue);

		// -----------------------------------------------

		panelThongKeSanPham = new JPanel();
		panelThongKeSanPham.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.addTab("Thống kê sản phẩm", null, panelThongKeSanPham, null);
		panelThongKeSanPham.setLayout(null);

		panelTongSoLuongSach = new JPanel();
		panelTongSoLuongSach.setBounds(925, 23, 253, 152);
		panelThongKeSanPham.add(panelTongSoLuongSach);
		panelTongSoLuongSach.setLayout(null);

		lblTongSoLuongSach = new JLabel("    Tổng số lượng sách: \r\n");
		lblTongSoLuongSach.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/books.png")));
		lblTongSoLuongSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongSoLuongSach.setBounds(0, 0, 253, 36);
		panelTongSoLuongSach.add(lblTongSoLuongSach);

		lblValueSoLuongSach = new JLabel("New label");
		lblValueSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValueSoLuongSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueSoLuongSach.setBounds(0, 46, 253, 83);
		panelTongSoLuongSach.add(lblValueSoLuongSach);

		panelTongSoLuongVanPhongPham = new JPanel();
		panelTongSoLuongVanPhongPham.setBounds(925, 376, 253, 152);
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
		lblValueSoLuongVPP.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValueSoLuongVPP.setBounds(0, 48, 253, 83);
		panelTongSoLuongVanPhongPham.add(lblValueSoLuongVPP);

		panelTongSoSachLoi = new JPanel();
		panelTongSoSachLoi.setBounds(925, 196, 253, 152);
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
		lblValueSoLuongSachLoi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValueSoLuongSachLoi.setBounds(0, 44, 253, 83);
		panelTongSoSachLoi.add(lblValueSoLuongSachLoi);

		panelLocSPBanChay = new JPanel();
		panelLocSPBanChay.setBackground(new Color(173, 255, 47));
		panelLocSPBanChay.setBounds(144, 23, 551, 523);
		panelThongKeSanPham.add(panelLocSPBanChay);
		panelLocSPBanChay.setLayout(null);

		panelSachBanChayNhat = new JPanel();
		panelSachBanChayNhat.setBounds(38, 261, 472, 219);
		panelLocSPBanChay.add(panelSachBanChayNhat);
		panelSachBanChayNhat.setLayout(null);

		lblSanPhamBanChayNhat = new JLabel("    Sản phẩm bán chạy nhất: ");
		lblSanPhamBanChayNhat.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/sachchay.png")));
		lblSanPhamBanChayNhat.setHorizontalAlignment(SwingConstants.CENTER);
		lblSanPhamBanChayNhat.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSanPhamBanChayNhat.setBounds(0, 0, 472, 42);
		panelSachBanChayNhat.add(lblSanPhamBanChayNhat);

		lblmaSPTop1 = new JLabel("...");
		lblmaSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblmaSPTop1.setBounds(343, 42, 71, 24);
		panelSachBanChayNhat.add(lblmaSPTop1);

		lblloaiSpTop1 = new JLabel("...");
		lblloaiSpTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblloaiSpTop1.setBounds(343, 67, 111, 24);
		panelSachBanChayNhat.add(lblloaiSpTop1);

		lblGiaBanTop1 = new JLabel("...");
		lblGiaBanTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGiaBanTop1.setBounds(343, 132, 129, 14);
		panelSachBanChayNhat.add(lblGiaBanTop1);

		lblSoLuongBanTop1 = new JLabel("...");
		lblSoLuongBanTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSoLuongBanTop1.setBounds(343, 168, 121, 14);
		panelSachBanChayNhat.add(lblSoLuongBanTop1);

		panelSanPhamTop1 = new JPanel();
		panelSanPhamTop1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSanPhamTop1.setBounds(10, 42, 159, 150);
		panelSachBanChayNhat.add(panelSanPhamTop1);
		panelSanPhamTop1.setLayout(null);

		lblHinhAnhTop1 = new JLabel("Hình ảnh");
		lblHinhAnhTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHinhAnhTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnhTop1.setBounds(0, 0, 159, 150);
		panelSanPhamTop1.add(lblHinhAnhTop1);

		lblMaSP = new JLabel("Mã sản phẩm: ");
		lblMaSP.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaSP.setBounds(205, 47, 89, 14);
		panelSachBanChayNhat.add(lblMaSP);

		lblTenSPTop1 = new JLabel("...");
		lblTenSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenSPTop1.setBounds(343, 105, 129, 19);
		panelSachBanChayNhat.add(lblTenSPTop1);

		lblTenSanPham = new JLabel("Loại sản phẩm: ");
		lblTenSanPham.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenSanPham.setBounds(205, 72, 89, 14);
		panelSachBanChayNhat.add(lblTenSanPham);

		lblTenSP = new JLabel("Tên sản phẩm: ");
		lblTenSP.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenSP.setBounds(205, 107, 89, 14);
		panelSachBanChayNhat.add(lblTenSP);

		lblGiaBan = new JLabel("Giá bán: ");
		lblGiaBan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGiaBan.setBounds(205, 132, 89, 14);
		panelSachBanChayNhat.add(lblGiaBan);

		lblSoLuongDaBan = new JLabel("Số lượng đã bán: ");
		lblSoLuongDaBan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSoLuongDaBan.setBounds(205, 168, 98, 14);
		panelSachBanChayNhat.add(lblSoLuongDaBan);

		btnLocSP = new JButton("Lọc");
		btnLocSP.setBounds(364, 138, 92, 35);
		panelLocSPBanChay.add(btnLocSP);
		btnLocSP.setHorizontalAlignment(SwingConstants.LEFT);
		btnLocSP.setIcon(new ImageIcon(Pn_ThongKeQuanLy.class.getResource("/gui/icon/filter.png")));
		btnLocSP.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblFromThongKeSP = new JLabel("Từ: ");
		lblFromThongKeSP.setBounds(36, 89, 35, 14);
		panelLocSPBanChay.add(lblFromThongKeSP);
		lblFromThongKeSP.setFont(new Font("Tahoma", Font.BOLD, 13));

		dateChooserFromThongKeSP = new JDateChooser();
		dateChooserFromThongKeSP.setBounds(157, 82, 164, 33);
		panelLocSPBanChay.add(dateChooserFromThongKeSP);

		dateChooserToThongKeSP = new JDateChooser();
		dateChooserToThongKeSP.setBounds(157, 140, 164, 33);
		panelLocSPBanChay.add(dateChooserToThongKeSP);

		lblToThongKeSP = new JLabel("Đến: ");
		lblToThongKeSP.setBounds(37, 152, 46, 14);
		panelLocSPBanChay.add(lblToThongKeSP);
		lblToThongKeSP.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblBoLocSPBanChay = new JLabel("Sản phẩm bán chạy nhất");
		lblBoLocSPBanChay.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoLocSPBanChay.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBoLocSPBanChay.setForeground(new Color(255, 0, 0));
		lblBoLocSPBanChay.setBackground(new Color(255, 0, 0));
		lblBoLocSPBanChay.setBounds(0, 33, 537, 26);
		panelLocSPBanChay.add(lblBoLocSPBanChay);

		panelThongKeNhanVien = new JPanel();
		panelThongKeNhanVien.setBackground(new Color(0, 206, 209));
		tabbedPaneThongKe.addTab("Thống kê nhân viên", null, panelThongKeNhanVien, null);
		panelThongKeNhanVien.setLayout(null);

		dateChooserToThongKeNV = new JDateChooser();
		dateChooserToThongKeNV.setBounds(138, 201, 164, 33);
		panelThongKeNhanVien.add(dateChooserToThongKeNV);

		String header_NhanVien[] = { "STT", "Mã nhân viên", "Tên nhân viên", "Số tiền đã bán", "Số hóa đơn đã bán" };
		tableModel_NV = new DefaultTableModel(header_NhanVien, 0);

		table_NV = new JTable(tableModel_NV);
		sp_tableNV = new JScrollPane(table_NV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_tableNV.setBounds(397, 92, 746, 394);
		panelThongKeNhanVien.add(sp_tableNV);

		btnLocNV = new JButton("Lọc");
		btnLocNV.setBounds(52, 294, 92, 35);
		panelThongKeNhanVien.add(btnLocNV);
		btnLocNV.setFont(new Font("Tahoma", Font.BOLD, 13));

		dateChooserFromThongKeNV = new JDateChooser();
		dateChooserFromThongKeNV.setBounds(138, 115, 164, 33);
		panelThongKeNhanVien.add(dateChooserFromThongKeNV);

		lblFromThongKeSP_1 = new JLabel("Từ: ");
		lblFromThongKeSP_1.setBounds(52, 127, 35, 14);
		panelThongKeNhanVien.add(lblFromThongKeSP_1);
		lblFromThongKeSP_1.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblToThongKeSP_1 = new JLabel("Đến: ");
		lblToThongKeSP_1.setBounds(52, 206, 46, 14);
		panelThongKeNhanVien.add(lblToThongKeSP_1);
		lblToThongKeSP_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel = new JLabel("TOP 10 NHÂN VIÊN BÁN ĐƯỢC NHIỀU NHẤT");
		lblNewLabel.setForeground(new Color(255, 69, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(397, 44, 746, 37);
		panelThongKeNhanVien.add(lblNewLabel);
		btnLocSP.addActionListener(this);

		txtTieuDe = new JLabel("THỐNG KÊ");
		txtTieuDe.setForeground(new Color(0, 191, 255));
		txtTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		txtTieuDe.setFont(new Font("Tahoma", Font.BOLD, 26));
		txtTieuDe.setBounds(0, 11, 1490, 51);
		add(txtTieuDe);

		btnLocDT.addActionListener(this);
		btnLocKH.addActionListener(this);
		btnLocSP.addActionListener(this);
		btnLocNV.addActionListener(this);

		setChart();

		comboBoxTieuChiDoanhThu.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				setBieuDoVe0();
				setChart();
			}
		});

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
		if (obj.equals(btnLocSP)) {
			iSanPham = new SanPhamServiceImpl();
			if (getNgayFromJDateChooser(dateChooserFromThongKeSP) != null
					&& getNgayFromJDateChooser(dateChooserToThongKeSP) != null) {
				if (iSanPham.getSanPhamBanNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromThongKeSP),
						getNgayFromJDateChooser(dateChooserToThongKeSP)) != null) {
					dsSP = iSanPham.getSanPhamBanNhieuNhatTheoNgayTuChon(
							getNgayFromJDateChooser(dateChooserFromThongKeSP),
							getNgayFromJDateChooser(dateChooserToThongKeSP));
					for (SanPham sp : dsSP) {
						File file = new File("");
						iSanPham = new SanPhamServiceImpl();
						try {
							lblmaSPTop1.setText(iSanPham.timSanPhamTheoMa(sp.getMaSanPham()).getMaSanPham());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						try {
							lblloaiSpTop1.setText(iSanPham.timSanPhamTheoMa(sp.getMaSanPham()).getLoaiSanPham());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							lblGiaBanTop1.setText(String
									.valueOf(iSanPham.timSanPhamTheoMa(sp.getMaSanPham()).getGiaNhap()
											+ iSanPham.timSanPhamTheoMa(sp.getMaSanPham()).getGiaNhap() * 10 / 100)
									+ "đ");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						try {
							lblSoLuongBanTop1.setText(String.valueOf(iSanPham.getSoLuongBanCuaSanPhamChayNhat(
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
//						try {
//							System.out.println(iSanPham.getSachTheoMaSP(sp.getMaSanPham()).getHinhAnh());
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
						System.out.println(sp.getHinhAnh());
						String hinhAnh;
						try {
							hinhAnh = file.getAbsolutePath() + "\\hinhAnhHieuSach\\" + iSanPham.getSachTheoMaSP(sp.getMaSanPham()).getHinhAnh();
							lblHinhAnhTop1.setIcon(setSizeImageIconString(hinhAnh, lblHinhAnhTop1.getWidth(),
									lblHinhAnhTop1.getHeight()));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}



					}
				}
			}
		}
		if (obj.equals(btnLocDT)) {
			iNhanVien = new NhanVienServiceImpl();
			iHoaDon = new HoaDonServiceImpl();
			if (getNgayFromJDateChooser(dateChooserFromDoanhThu) != null
					&& getNgayFromJDateChooser(dateChooserToDoanhThu) != null) {
				if (iNhanVien.getNhanVienBanNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromDoanhThu),
						getNgayFromJDateChooser(dateChooserToDoanhThu)) != null) {
					dsNV = iNhanVien.getNhanVienBanNhieuNhatTheoNgayTuChon(
							getNgayFromJDateChooser(dateChooserFromDoanhThu),
							getNgayFromJDateChooser(dateChooserToDoanhThu));
					for (NhanVien nv : dsNV) {
						iNhanVien = new NhanVienServiceImpl();
						try {
							valueTop1NV.setText(iNhanVien.timNhanVienTheoMa(nv.getMaNhanVien()).getHoTenNhanVien());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
				try {
					if (iHoaDon.getDoanhThu(getNgayFromJDateChooser(dateChooserFromDoanhThu),
							getNgayFromJDateChooser(dateChooserToDoanhThu)) > 0) {
						lblGiaTriDoanhThu.setText(
								String.valueOf(iHoaDon.getDoanhThu(getNgayFromJDateChooser(dateChooserFromDoanhThu),
										getNgayFromJDateChooser(dateChooserToDoanhThu))) + "đ");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if (iHoaDon.getSoLuongHoaDon(getNgayFromJDateChooser(dateChooserFromDoanhThu),
							getNgayFromJDateChooser(dateChooserToDoanhThu)) > 0) {
						lblGiaTriTongHoaDon.setText(String
								.valueOf(iHoaDon.getSoLuongHoaDon(getNgayFromJDateChooser(dateChooserFromDoanhThu),
										getNgayFromJDateChooser(dateChooserToDoanhThu))));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (obj.equals(btnLocKH)) {
			if (getNgayFromJDateChooser(dateChooserFromKH) != null
					&& getNgayFromJDateChooser(dateChooserToKH) != null) {
				iKhachHang = new KhachHangServiceImpl();
				if (iKhachHang.getKhachHangMuaNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromKH),
						getNgayFromJDateChooser(dateChooserToKH)) != null) {
					dsKH = iKhachHang.getKhachHangMuaNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromKH),
							getNgayFromJDateChooser(dateChooserToKH));
					for (KhachHang kh : dsKH) {
						iKhachHang = new KhachHangServiceImpl();
						try {
							lblTenKHValue
									.setText(iKhachHang.timKhachHangTheoMa(kh.getMaKhachHang()).getHoTenKhachHang());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							lblSoTienDaMuaValue.setText(String.valueOf(
									iKhachHang.getTongTienCuaKhachHangTop1(getNgayFromJDateChooser(dateChooserFromKH),
											getNgayFromJDateChooser(dateChooserToKH)))
									+ "đ");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
				xoaHetDuLieuTableTop10KH();
				try {
					DocDuLieuTuArrayListTop10VaoModel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// updateTableDataTop10VaoModel(KhachHang kh);
			}
		}

		if (obj.equals(btnLocNV)) {
			xoaHetDuLieuTableTop10NV();
			if (getNgayFromJDateChooser(dateChooserFromThongKeNV) != null
					&& getNgayFromJDateChooser(dateChooserToThongKeNV) != null) {
				iNhanVien = new NhanVienServiceImpl();
				iHoaDon = new HoaDonServiceImpl();
				// if
				// (iNhanVien.getDoanhThuCuaNhanVien(getNgayFromJDateChooser(dateChooserFromThongKeNV),
//							getNgayFromJDateChooser(dateChooserToThongKeNV)) != null) {
//						dsNV = iNhanVien.getDoanhThuCuaNhanVien(getNgayFromJDateChooser(dateChooserFromThongKeNV),
//								getNgayFromJDateChooser(dateChooserToThongKeNV));
//
//						int i = 1;
//						for (NhanVien nv : dsNV) {
//							// System.out.println(kh.getHoTenKhachHang());
//							tableModel_NV.addRow(new Object[] { i++, nv.getMaNhanVien(), nv.getHoTenNhanVien(),
//									iHoaDon.getDoanhThuTheoMaNhanVien(getNgayFromJDateChooser(dateChooserFromThongKeNV),
//											getNgayFromJDateChooser(dateChooserToThongKeNV), nv.getMaNhanVien()),
//									iHoaDon.getSoLuongHoaDonTheoMaNV(getNgayFromJDateChooser(dateChooserFromThongKeNV),
//											getNgayFromJDateChooser(dateChooserToThongKeNV), nv.getMaNhanVien()) });
//						}
//					}
				DocDuLieuTuArrayListTop10NVVaoModel();
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

	public void xoaHetDuLieuTableTop10KH() {
		DefaultTableModel dtm = (DefaultTableModel) table_top10KH.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void DocDuLieuTuArrayListTop10VaoModel() throws Exception {
		iKhachHang = new KhachHangServiceImpl();
		// if (getNgayFromJDateChooser(dateChooserFromKH) != null &&
		// getNgayFromJDateChooser(dateChooserToKH) != null) {
		dsKH = iKhachHang.getTop10KHThanThiet(getNgayFromJDateChooser(dateChooserFromKH),
				getNgayFromJDateChooser(dateChooserToKH));
		int i = 1;
		for (KhachHang kh : dsKH) {
			// System.out.println(kh.getHoTenKhachHang());
			tableModel_top10KH.addRow(new Object[] { i++, kh.getMaKhachHang(),
					iKhachHang.timKhachHangTheoMa(kh.getMaKhachHang()).getHoTenKhachHang(),
					iKhachHang.getTongTienCuaKhachHangTheoMa(getNgayFromJDateChooser(dateChooserFromKH),
							getNgayFromJDateChooser(dateChooserToKH), kh.getMaKhachHang()),
					iKhachHang.getSoLuongHoaDonCuaKhachHangTheoMa(getNgayFromJDateChooser(dateChooserFromKH),
							getNgayFromJDateChooser(dateChooserToKH), kh.getMaKhachHang()) });
		}
		// }
	}

	// Thong ke top 10 nv
	public void DocDuLieuTuArrayListTop10NVVaoModel() {
		iNhanVien = new NhanVienServiceImpl();
		dsNV = iNhanVien.thongKeDoanhThu10NVBanNhieuNhat(getNgayFromJDateChooser(dateChooserFromThongKeNV),
				getNgayFromJDateChooser(dateChooserToThongKeNV));
		int i = 1;
		for (NhanVien nv : dsNV) {
			try {
				//System.out.println(nv.getHoTenNhanVien());
				tableModel_NV.addRow(new Object[] { i++, nv.getMaNhanVien(), iNhanVien.timNhanVienTheoMa(nv.getMaNhanVien()).getHoTenNhanVien(),
						iHoaDon.getDoanhThuTheoMaNhanVien(getNgayFromJDateChooser(dateChooserFromThongKeNV),
								getNgayFromJDateChooser(dateChooserToThongKeNV), nv.getMaNhanVien()),
						iHoaDon.getSoLuongHoaDonTheoMaNV(getNgayFromJDateChooser(dateChooserFromThongKeNV),
								getNgayFromJDateChooser(dateChooserToThongKeNV), nv.getMaNhanVien()) });
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void xoaHetDuLieuTableTop10NV() {
		DefaultTableModel dtm = (DefaultTableModel) table_NV.getModel();
		dtm.getDataVector().removeAllElements();
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
					dataset.setValue(iHoaDon.getDoanhThu(nowMinus, now), "Doanh thu",
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
					dataset.setValue(iHoaDon.getDoanhThu(nowMinus, now), "Doanh thu",
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
					dataset.setValue(iHoaDon.getDoanhThu(nowMinus, now), "Doanh thu",
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
	public ImageIcon setSizeImageIconString(String s, int width, int height) {
		ImageIcon image = new ImageIcon(s);
		Image imageSet = image.getImage();
		imageSet = imageSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		image = new ImageIcon(imageSet);
		return image;
	}
}
