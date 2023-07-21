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
import dao.HoaDonDoiTraDao;
import dao.SachLoiDao;
import dao.SanPhamDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.HoaDonDoiTra;
import entity.NhaCungCap;
import entity.Sach;
import entity.SachLoi;
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

public class Frm_XemChiTietHoaDon2 extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel txtSoTrang;

	private JButton btnThoat;
	SanPhamServiceImpl sanPhamServiceImpl = new SanPhamServiceImpl();
	TheLoaiServiceImpl theLoaiServiceImpl = new TheLoaiServiceImpl();
	TacGiaServiceImpl tacGiaServiceImpl = new TacGiaServiceImpl();
	NhaXuatBanServiceImpl nhaXuatBanServiceImpl = new NhaXuatBanServiceImpl();
	NhaCungCapServiceImpl nhaCungCapServiceImpl = new NhaCungCapServiceImpl();
	ChatLieuServiceImpl chatLieuServiceImpl = new ChatLieuServiceImpl();
	XuatXuServiceImpl xuatXuServiceImpl = new XuatXuServiceImpl();
	MauSacServiceImpl mauSacServiceImpl = new MauSacServiceImpl();

	private List<SachLoi> dsSachLoi;

	private JTable table_ChiTietHD;
	private DefaultTableModel tableModel_chiTietHoaDonDao;

	private SachLoiDao sachLoi_dao;
	private JScrollPane sp_ChiTietHD;
	private SanPhamDao sanPhamDao;
	private JButton btnDoi;

//	@SuppressWarnings("deprecation")
	public Frm_XemChiTietHoaDon2() throws Exception {

		setTitle("Chi tiết hóa đơn");
		setResizable(false);
		setSize(1130, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("CÁC SÁCH LỖI");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(201, 10, 766, 39);
		getContentPane().add(lblNewLabel);

		txtSoTrang = new JLabel();
		txtSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtSoTrang.setBounds(125, 290, 170, 23);
		getContentPane().add(txtSoTrang);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(28, 599, 1078, 54);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(569, 10, 132, 39);
		panel_1.add(btnThoat);

		btnDoi = new JButton("Đổi");
		btnDoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDoi.setBounds(363, 10, 132, 39);
		panel_1.add(btnDoi);

		JPanel panel_right = new JPanel();
		panel_right.setBounds(28, 59, 1078, 511);
		getContentPane().add(panel_right);
		setValue();

		panel_right.setLayout(null);
		String header_ChiTietHD[] = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Lỗi sản phẩm", "Số lượng" };
		tableModel_chiTietHoaDonDao = new DefaultTableModel(header_ChiTietHD, 0);
		table_ChiTietHD = new JTable(tableModel_chiTietHoaDonDao);
		sp_ChiTietHD = new JScrollPane(table_ChiTietHD, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_ChiTietHD.setBounds(10, 45, 1058, 434);
		table_ChiTietHD.setAutoCreateRowSorter(true);
		panel_right.add(sp_ChiTietHD);

		JLabel lblNewLabel_1 = new JLabel("DANH SÁCH SẢN PHẨM");
		lblNewLabel_1.setBounds(274, 2, 360, 33);
		panel_right.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			docDuLieuSachLoi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnThoat.addActionListener(this);
		btnDoi.addActionListener(this);
	}

	public void setValue() {
	}

	// Doc du lieu sach loi
	public void docDuLieuSachLoi() throws Exception {
		sachLoi_dao = new SachLoiDao();
		sanPhamDao = new SanPhamDao();
//		try {
		dsSachLoi = sachLoi_dao.getAllSachLoi();
//		} catch (SQLException e) {
		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		if (dsSachLoi.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy sách lỗi");
		} else {
			int i = 1;
			for (SachLoi sachLoi : dsSachLoi) {
				Sach sach = sanPhamDao.timSanPhamTheoMaSach(sachLoi.getSach().getMaSanPham());
				System.out.println(new Sach(sachLoi.getSach().getMaSanPham()).getTenSach());
				tableModel_chiTietHoaDonDao.addRow(new Object[] { i++, sachLoi.getSach().getMaSanPham(),
						sach.getTenSach(), sachLoi.getLoiSanPham(), sachLoi.getSoLuong() });
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			if (JOptionPane.showConfirmDialog(null, "Có chắc bạn muốn thoát", "Cảnh báo", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
				this.setVisible(false);
		}
		if (o.equals(btnDoi)) {
			try {
				int row = table_ChiTietHD.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm cần đổi");
				} else {
					SanPham sanPham = sanPhamServiceImpl.timSanPhamTheoMa(
							table_ChiTietHD.getValueAt(table_ChiTietHD.getSelectedRow(), 1).toString());
					sanPham.setSoLuongTon(sanPham.getSoLuongTon() + Integer
							.parseInt(table_ChiTietHD.getValueAt(table_ChiTietHD.getSelectedRow(), 4).toString()));
					sanPhamServiceImpl.capNhatSoLuongSanPham(sanPham);
					// Hàm xóa sản phẩm lỗi theo mã
					sachLoi_dao.xoaSachLoi(sanPham.getMaSanPham(), table_ChiTietHD.getValueAt(row, 3).toString());
					JOptionPane.showMessageDialog(this, "Sản phẩm đã được đổi");
					// refresh lại bảng
//					table_ChiTietHD.removeRowSelectionInterval(row, row);
					try {
						tableModel_chiTietHoaDonDao.setRowCount(0);
						docDuLieuSachLoi();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}
}
