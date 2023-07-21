package gui;

import java.awt.Color;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import dao.NhanVienDao;
import dao.TaiKhoanDao;
import db.DBConnection;
import entity.NhanVien;
import entity.TaiKhoan;
import service.impl.HoaDonServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.TaiKhoanServiceImpl;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class Pn_QuanLyNhanVien extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaNhanVien;
	private JTextField txtTenNV;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JScrollPane sp_tableNhanVien;
	private JTable table_NhanVien;
	private DefaultTableModel tableModel_NhanVien;
	private JPanel panel_Right;
	private JPanel panel_TitleBoLoc;
	private JLabel lblNewLabel;
	private JLabel lblTenNhanVien;
	private JLabel lblMaNhanVien;
	private JLabel lblSDT;
	private JLabel lblGioiTinh;
	private JLabel lblCaLam;
	private JLabel lblChucVu;
	private JLabel lblDiaChi;
	private JComboBox comboBoxGioiTinh;
	private JComboBox comboBoxCaLam;
	private JComboBox comboBoxChucVu;
	private JButton btnThemNV;
	private JButton btnSuaNV;
	private JButton btnXoaNV;
	private JButton btnTimKiem;
	private JButton btnLamMoi;
	private JPanel panel_Bot;
	private JButton btnLamMoiBang;
	private JPanel panelTop;
	private JLabel lblTitle;
	private JButton btnLuu;
	private List<NhanVien> dsNhanVien;
	private JLabel lblCCCD;
	private JTextField txtCCCD;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblNgaySinh;
	private JButton btnChonAnh;
	private JDateChooser dateChooserNgaySinh;

	private Connection con = DBConnection.getInstance().getConnection();
	private NhanVienServiceImpl iNhanvien;
	private TaiKhoanServiceImpl iTaiKhoan;
	private HoaDonServiceImpl iHoaDon;
	private JPanel pnlHinhAnh;
	private File file = null;
	private JPanel panelTim;
	private JLabel lblMaNhanVien_1;
	private JTextField txtSDTTim;
	private JTextField txtTenTim;
	private JFileChooser filechoose;
	private ArrayList<NhanVien> listNV;
	private JLabel lblHinhAnh;

	/**
	 * Create the panel.
	 */
	public Pn_QuanLyNhanVien() {
		setBackground(new Color(0, 206, 209));
		setFont(new Font("Dialog", Font.BOLD, 16));
		setSize(1400, 682);
		setLayout(null);

		panel_Right = new JPanel();
		panel_Right.setBounds(22, 11, 544, 647);
		add(panel_Right);
		panel_Right.setLayout(null);

		panel_TitleBoLoc = new JPanel();
		panel_TitleBoLoc.setBackground(new Color(128, 128, 128));
		panel_TitleBoLoc.setBounds(0, 0, 544, 40);
		panel_Right.add(panel_TitleBoLoc);
		panel_TitleBoLoc.setLayout(null);

		lblNewLabel = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblNewLabel.setBackground(new Color(128, 128, 128));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 0, 544, 40);
		panel_TitleBoLoc.add(lblNewLabel);

		lblTenNhanVien = new JLabel("Tên nhân viên: ");
		lblTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenNhanVien.setBounds(10, 205, 93, 14);
		panel_Right.add(lblTenNhanVien);

		lblMaNhanVien = new JLabel("Mã nhân viên: ");
		lblMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaNhanVien.setBounds(10, 170, 93, 14);
		panel_Right.add(lblMaNhanVien);

		lblSDT = new JLabel("Số điện thoại: ");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSDT.setBounds(10, 249, 104, 14);
		panel_Right.add(lblSDT);

		lblGioiTinh = new JLabel("Giới tính: ");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGioiTinh.setBounds(10, 294, 93, 14);
		panel_Right.add(lblGioiTinh);

		lblCaLam = new JLabel("Ca làm: ");
		lblCaLam.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCaLam.setBounds(204, 327, 46, 14);
		panel_Right.add(lblCaLam);

		lblChucVu = new JLabel("Chức vụ: ");
		lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChucVu.setBounds(10, 327, 76, 14);
		panel_Right.add(lblChucVu);

		lblDiaChi = new JLabel("Địa chỉ: ");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDiaChi.setBounds(10, 364, 71, 14);
		panel_Right.add(lblDiaChi);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setBounds(102, 167, 147, 20);
		panel_Right.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		txtTenNV = new JTextField();
		txtTenNV.setBounds(102, 202, 147, 20);
		panel_Right.add(txtTenNV);
		txtTenNV.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setBounds(102, 246, 147, 20);
		panel_Right.add(txtSDT);
		txtSDT.setColumns(10);

		comboBoxGioiTinh = new JComboBox();
		comboBoxGioiTinh.setBounds(102, 290, 86, 22);
		comboBoxGioiTinh.addItem("Nam");
		comboBoxGioiTinh.addItem("Nữ");
		panel_Right.add(comboBoxGioiTinh);

		comboBoxCaLam = new JComboBox();
		comboBoxCaLam.setBounds(260, 323, 86, 22);
		comboBoxCaLam.addItem("Sáng");
		comboBoxCaLam.addItem("Chiều");
		panel_Right.add(comboBoxCaLam);

		comboBoxChucVu = new JComboBox();
		comboBoxChucVu.setBounds(102, 323, 86, 22);
		comboBoxChucVu.addItem("Quản lý");
		comboBoxChucVu.addItem("Nhân viên");
		panel_Right.add(comboBoxChucVu);

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(91, 360, 433, 23);
		panel_Right.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		btnThemNV = new JButton("   Thêm ");
		btnThemNV.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/add-user.png")));
		btnThemNV.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnThemNV.setBounds(212, 532, 117, 32);
		panel_Right.add(btnThemNV);

		btnSuaNV = new JButton("   Sửa");
		btnSuaNV.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/contract.png")));
		btnSuaNV.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSuaNV.setBounds(44, 532, 117, 32);
		panel_Right.add(btnSuaNV);

		btnXoaNV = new JButton("   Xóa");
		btnXoaNV.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/delete.png")));
		btnXoaNV.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnXoaNV.setBounds(44, 593, 117, 32);
		panel_Right.add(btnXoaNV);

		btnTimKiem = new JButton("   Tìm");
		btnTimKiem.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/loupe.png")));
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTimKiem.setBounds(376, 532, 117, 32);
		panel_Right.add(btnTimKiem);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/refresh-button.png")));
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLamMoi.setBounds(212, 593, 117, 32);
		panel_Right.add(btnLamMoi);

		btnLuu = new JButton("   Lưu");
		btnLuu.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/diskette.png")));
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLuu.setBounds(376, 593, 117, 32);
		panel_Right.add(btnLuu);

		lblCCCD = new JLabel("Căn cước công dân: ");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCCCD.setBounds(266, 170, 111, 14);
		panel_Right.add(lblCCCD);

		txtCCCD = new JTextField();
		txtCCCD.setBounds(387, 167, 147, 20);
		panel_Right.add(txtCCCD);
		txtCCCD.setColumns(10);

		lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(266, 205, 46, 14);
		panel_Right.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(387, 202, 147, 20);
		panel_Right.add(txtEmail);
		txtEmail.setColumns(10);

		lblNgaySinh = new JLabel("Ngày sinh: ");
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNgaySinh.setBounds(266, 249, 76, 14);
		panel_Right.add(lblNgaySinh);

		dateChooserNgaySinh = new JDateChooser();
		dateChooserNgaySinh.setBounds(387, 249, 147, 20);
		panel_Right.add(dateChooserNgaySinh);

		btnChonAnh = new JButton("Chọn ảnh");
		btnChonAnh.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnChonAnh.setBounds(213, 87, 89, 32);
		panel_Right.add(btnChonAnh);

		pnlHinhAnh = new JPanel();
		pnlHinhAnh.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlHinhAnh.setBounds(32, 51, 153, 101);
		panel_Right.add(pnlHinhAnh);
		pnlHinhAnh.setLayout(null);
		
		lblHinhAnh = new JLabel("Hình ảnh\r\n");
		lblHinhAnh.setIcon(null);
		lblHinhAnh.setBounds(0, 0, 153, 101);
		pnlHinhAnh.add(lblHinhAnh);

		panelTim = new JPanel();
		panelTim.setBorder(
				new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTim.setBounds(44, 401, 449, 120);
		panel_Right.add(panelTim);
		panelTim.setLayout(null);

		lblMaNhanVien_1 = new JLabel("Số điện thoại: ");
		lblMaNhanVien_1.setBounds(44, 34, 81, 14);
		lblMaNhanVien_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelTim.add(lblMaNhanVien_1);

		txtSDTTim = new JTextField();
		txtSDTTim.setColumns(10);
		txtSDTTim.setBounds(206, 31, 147, 20);
		panelTim.add(txtSDTTim);

		txtTenTim = new JTextField();
		txtTenTim.setColumns(10);
		txtTenTim.setBounds(206, 74, 147, 20);
		panelTim.add(txtTenTim);

		JLabel lblTenNhanVien_1 = new JLabel("Tên nhân viên: ");
		lblTenNhanVien_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenNhanVien_1.setBounds(44, 77, 93, 14);
		panelTim.add(lblTenNhanVien_1);

		panel_Bot = new JPanel();
		panel_Bot.setBounds(589, 574, 811, 71);
		add(panel_Bot);
		panel_Bot.setLayout(null);

		btnLamMoiBang = new JButton("   Làm mới bảng");
		btnLamMoiBang.setBounds(395, 21, 188, 39);
		panel_Bot.add(btnLamMoiBang);
		btnLamMoiBang.setIcon(new ImageIcon(Pn_QuanLyNhanVien.class.getResource("/gui/icon/refresh-button.png")));
		btnLamMoiBang.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLamMoiBang.addActionListener(this);

		panelTop = new JPanel();
		panelTop.setBounds(589, 42, 811, 512);
		add(panelTop);

		lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitle.setBounds(0, 0, 1400, 31);
		add(lblTitle);

		panelTop.setLayout(null);
		String header_NhanVien[] = { "STT", "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Giới tính",
				"Ca làm", "Chức vụ", "Địa chỉ" };
		tableModel_NhanVien = new DefaultTableModel(header_NhanVien, 0);
		table_NhanVien = new JTable(tableModel_NhanVien);
		sp_tableNhanVien = new JScrollPane(table_NhanVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_tableNhanVien.setBounds(10, 34, 791, 436);
		panelTop.add(sp_tableNhanVien);

		table_NhanVien.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_NhanVien.getColumnModel().getColumn(1).setPreferredWidth(70);
		table_NhanVien.getColumnModel().getColumn(2).setPreferredWidth(120);
		table_NhanVien.getColumnModel().getColumn(3).setPreferredWidth(70);
		table_NhanVien.getColumnModel().getColumn(4).setPreferredWidth(70);
		table_NhanVien.getColumnModel().getColumn(5).setPreferredWidth(40);
		table_NhanVien.getColumnModel().getColumn(6).setPreferredWidth(40);
		table_NhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
		table_NhanVien.getColumnModel().getColumn(8).setPreferredWidth(220);

		table_NhanVien.addMouseListener(this);
		try {
			DocDuLieuTuArrayListVaoModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		moKhoaTextfields(false);
		btnLuu.setEnabled(false);
		txtSDTTim.setEditable(true);
		txtTenTim.setEditable(true);
//				lblHinhAnh.setIcon(setSizeImageIconString("..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg",
//						lblHinhAnh.getWidth(), lblHinhAnh.getHeight()));
		btnThemNV.addActionListener(this);
		btnChonAnh.addActionListener(this);
		btnSuaNV.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXoaNV.addActionListener(this);

	}

//-------------------------------------------------//
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(table_NhanVien)) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int row = table_NhanVien.getSelectedRow();
		try {
			DefaultTableModel model = (DefaultTableModel) table_NhanVien.getModel();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(row, 3).toString());

			dateChooserNgaySinh.setDate(date);

			dateChooserNgaySinh.setDateFormatString("yyyy-MM-dd");
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println("sai");
		}
		txtMaNhanVien.setText(tableModel_NhanVien.getValueAt(row, 1).toString());
		NhanVien nv;
		File file = new File("");
		try {
			nv = iNhanvien.timNhanVienTheoMa(txtMaNhanVien.getText());
			txtCCCD.setText(nv.getcCCD());
			txtEmail.setText(nv.getEmail());
			String hinhAnh = file.getAbsolutePath() +"\\hinhAnhHieuSach\\" + nv.getHinhAnh();
			//String hinhAnh = nv.getHinhAnh();
			System.out.println(hinhAnh);
			lblHinhAnh.setIcon(setSizeImageIconString(hinhAnh, lblHinhAnh.getWidth(), lblHinhAnh.getHeight()));
			
			//lblHinhAnh.setText("111111111");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		txtTenNV.setText(tableModel_NhanVien.getValueAt(row, 2).toString());
		txtSDT.setText(tableModel_NhanVien.getValueAt(row, 4).toString());
		comboBoxGioiTinh
				.setSelectedIndex(tableModel_NhanVien.getValueAt(row, 5).toString().equalsIgnoreCase("Nam") ? 0 : 1);
		comboBoxCaLam
				.setSelectedIndex(tableModel_NhanVien.getValueAt(row, 6).toString().equalsIgnoreCase("Sáng") ? 0 : 1);
		comboBoxChucVu.setSelectedIndex(
				tableModel_NhanVien.getValueAt(row, 7).toString().equalsIgnoreCase("Quản lý") ? 0 : 1);
		txtDiaChi.setText(tableModel_NhanVien.getValueAt(row, 8).toString());
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
		if (obj.equals(btnThemNV)) {
			if (btnThemNV.getText().equalsIgnoreCase("   Thêm ")) {
				table_NhanVien.removeMouseListener(this);
				btnLuu.setEnabled(true);
				comboBoxChucVu.setSelectedItem(null);
				comboBoxChucVu.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						if (e.getStateChange() == ItemEvent.SELECTED) {

							try {
								dsNhanVien = iNhanvien.getDSNhanVien();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							String maNVMoi = "";
							if (comboBoxChucVu.getSelectedItem().toString().equals("Quản lý")) {
								maNVMoi = "QL";
							} else {
								maNVMoi = "NV";
							}
							LocalDate myObj = LocalDate.now();
							String ngayMaNV = String.valueOf(myObj.getDayOfMonth());
							String thangMaNV = String.valueOf(myObj.getMonthValue());
							// String namMaNV = String.valueOf(myObj.getYear());
							String soLuong = "";
							if (dsNhanVien.size() < 10) {
								soLuong = "0" + (dsNhanVien.size() + 1);
							} else if (dsNhanVien.size() < 100) {
								soLuong = "" + (dsNhanVien.size() + 1);
							}
							maNVMoi = maNVMoi + ngayMaNV + thangMaNV + soLuong;
							txtMaNhanVien.setText(maNVMoi);
						}
					}
				});
				moKhoaTextfields(true);
				moKhoaControls(false);
				txtSDTTim.setEditable(false);
				txtTenTim.setEditable(false);
				btnLuu.setEnabled(true);
				btnThemNV.setEnabled(true);
				clearTxtfields();
				btnThemNV.setText("Hủy");
				txtMaNhanVien.setEditable(false);

			} else if (btnThemNV.getText().equalsIgnoreCase("Hủy")) {
				table_NhanVien.addMouseListener(this);
				btnLuu.setEnabled(false);
				moKhoaTextfields(false);
				moKhoaControls(true);
				clearTxtfields();
				btnLuu.setEnabled(false);
				btnThemNV.setText("   Thêm ");
			}
		} else if (obj.equals(btnLuu) && btnThemNV.getText().equalsIgnoreCase("Hủy")) {
			// if (validData()) {
			NhanVien nv = revertNhanVienFromTextfields();

			iNhanvien = new NhanVienServiceImpl();
			try {

				if (iNhanvien.themNhanVien(nv) > 0) {
					updateTableData(nv);
					// bentaotaikhoanbaothanhcong
					JOptionPane.showMessageDialog(this, "Thêm thành công 1 nhân viên");
					btnLuu.setEnabled(false);
					table_NhanVien.addMouseListener(this);
					moKhoaControls(true);
					moKhoaTextfields(false);
					btnThemNV.setText("   Thêm ");
					FrmThemTaiKhoan frmThemTK = new FrmThemTaiKhoan(nv);
					frmThemTK.setVisible(true);
				}
				else {
					System.out.println("Thme k dc");
					int erThem = NhanVienServiceImpl.errorsThem;
					if(erThem==1) {
						JOptionPane.showMessageDialog(this, "Tên nhân viên phải theo mẫu : Nguyen Van Anh");
						txtTenNV.setText("");
						txtTenNV.requestFocus();
					}
					else if(erThem==2) {
						JOptionPane.showMessageDialog(this, "Số điện thoại cần có 10 chữ số ví dụ: 0384600357");
						txtSDT.setText("");
						txtSDT.requestFocus();
					}
					else if(erThem == 3) {
						JOptionPane.showMessageDialog(this, "Căn cước cần có 12 chữ số ví dụ: 038460035712");
						txtCCCD.setText("");
						txtCCCD.requestFocus();
					}
					else if (erThem==4) {
						JOptionPane.showMessageDialog(this, "Email cần dạng canh@gmail.com");
						txtEmail.setText("");
						txtEmail.requestFocus();
					}
					else if (erThem ==5) {
						JOptionPane.showMessageDialog(this, "Địa chỉ bao gồm số và chữ : 12 Nguyen Van Bao");
						txtDiaChi.setText("");
						txtDiaChi.requestFocus();
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Trùng mã");
			}
		}
//		} 
		else if (obj.equals(btnLuu) && btnSuaNV.getText().equalsIgnoreCase("Hủy")) {
			
			NhanVien nv = revertNhanVienFromTextfields();
			iNhanvien = new NhanVienServiceImpl();
			try {
				if (iNhanvien.suaNhanVien(nv) > 0) {
					JOptionPane.showMessageDialog(this, "Sửa thành công 1 nhân viên");
					btnLuu.setEnabled(false);
					btnSuaNV.setText("   Sửa");
					table_NhanVien.addMouseListener(this);
					moKhoaControls(true);
					moKhoaTextfields(false);
					editOnRow();
				}
				else {
					int erSua = NhanVienServiceImpl.errorsSua;
					if(erSua==1) {
						JOptionPane.showMessageDialog(this, "Tên nhân viên phải theo mẫu : Nguyen Van Anh");
						txtTenNV.setText("");
						txtTenNV.requestFocus();
					}
					else if(erSua==2) {
						JOptionPane.showMessageDialog(this, "Số điện thoại cần có 10 chữ số ví dụ: 0384600357");
						txtSDT.setText("");
						txtSDT.requestFocus();
					}
					else if(erSua == 3) {
						JOptionPane.showMessageDialog(this, "Căn cước cần có 12 chữ số ví dụ: 038460035712");
						txtCCCD.setText("");
						txtCCCD.requestFocus();
					}
					else if (erSua==4) {
						JOptionPane.showMessageDialog(this, "Email cần dạng canh@gmail.com");
						txtEmail.setText("");
						txtEmail.requestFocus();
					}
					else if (erSua ==5) {
						JOptionPane.showMessageDialog(this, "Địa chỉ bao gồm số và chữ : 12 Nguyen Van Bao");
						txtDiaChi.setText("");
						txtDiaChi.requestFocus();
					}
				}
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (obj.equals(btnXoaNV)) {
			// System.out.println("ok");
			if (table_NhanVien.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Phải chọn dòng trước khi xóa");
			} else {

				String maXoa = txtMaNhanVien.getText();
				// String tenXoa = txtTenNV.getText();
				try {
					iHoaDon = new HoaDonServiceImpl();
					iHoaDon.setNullChoMaNhanVienTrongHoaDon(maXoa);
					iTaiKhoan = new TaiKhoanServiceImpl();
					iTaiKhoan.xoaTaiKhoan(maXoa);

					int resq = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa nhân viên không ?",
							"Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (resq == JOptionPane.NO_OPTION)
						System.exit(0);

					else {
						if (iNhanvien.xoaNhanVien(maXoa) > 0) {

							JOptionPane.showMessageDialog(this,
									"Xóa thành công 1 nhân viên và tài khoản của nhân viên đó");
							tableModel_NhanVien.removeRow(table_NhanVien.getSelectedRow());
						}
					}

				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else if (obj.equals(btnSuaNV)) {
			if (table_NhanVien.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Phải chọn dòng trước khi sửa");
			} else {
				if (btnSuaNV.getText().equalsIgnoreCase("   Sửa")) {
					btnLuu.setEnabled(true);
					table_NhanVien.removeMouseListener(this);
					moKhoaTextfields(true);
					txtSDTTim.setEditable(false);
					txtTenTim.setEditable(false);
					txtMaNhanVien.setEditable(false);
					moKhoaControls(false);
					btnLuu.setEnabled(true);
					btnSuaNV.setEnabled(true);
					btnSuaNV.setText("Hủy");
				} else if (btnSuaNV.getText().equalsIgnoreCase("Hủy")) {
					table_NhanVien.addMouseListener(this);
					moKhoaTextfields(false);
					moKhoaControls(true);
					clearTxtfields();
					btnLuu.setEnabled(false);
					btnSuaNV.setText("   Sửa");

				}
			}
		} else if (obj.equals(btnTimKiem) && btnTimKiem.getText().equalsIgnoreCase("   Tìm")) {
			txtSDTTim.setEditable(true);
			txtTenTim.setEditable(true);
			moKhoaControls(false);
			btnTimKiem.setEnabled(true);
			NhanVien nv = new NhanVien();
			String sdtTim = txtSDTTim.getText();
			String tenTim = txtTenTim.getText();
			iTaiKhoan = new TaiKhoanServiceImpl();

			if (!sdtTim.isEmpty() && tenTim.isEmpty()) {
				try {
					dsNhanVien = iNhanvien.timNhanVienTheoSDT(sdtTim);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (dsNhanVien.size() != 0) {

					xoaHetDuLieu();
					for (NhanVien nhanVien : dsNhanVien) {
						try {
							updateTableData(nhanVien);
							moKhoaControls(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
				}
			} else if (sdtTim.isEmpty() && !tenTim.isEmpty()) {
				try {
					dsNhanVien = iNhanvien.timDSNhanVienTheoTen(tenTim);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (dsNhanVien.size() != 0) {
					xoaHetDuLieu();
					for (NhanVien nhanVien : dsNhanVien) {
						try {
							updateTableData(nhanVien);
							moKhoaControls(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
				}
			} else if (!sdtTim.isEmpty() && !tenTim.isEmpty()) {
				dsNhanVien = iNhanvien.getListNhanVienByNameAndSDT(tenTim, sdtTim);
				if (dsNhanVien.size() != 0) {

					xoaHetDuLieu();
					for (NhanVien nhanVien : dsNhanVien) {
						try {
							updateTableData(nhanVien);
							moKhoaControls(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
				}
			}
			else if (sdtTim.isEmpty() && tenTim.isEmpty()) {
				xoaHetDuLieu();
				try {
					DocDuLieuTuArrayListVaoModel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm ");
			}

		} else if (obj.equals(btnLamMoi)) {
			clearTxtfields();
		} else if (obj.equals(btnChonAnh)) {
//			System.err.println("aaa");
			filechoose = new JFileChooser("D:\\hinhAnhHieuSach");
			FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hinh anh", "jpg", "png");
			filechoose.setFileFilter(imageFilter);
			filechoose.setMultiSelectionEnabled(false);

			int x = filechoose.showDialog(this, "Chọn Ảnh");
			if (x == JFileChooser.APPROVE_OPTION) {
				file = filechoose.getSelectedFile();
				
				String name = file.getName();
				System.out.println(name);
				
				lblHinhAnh.setText("");
				lblHinhAnh.setIcon(ResizeImage(file.getAbsolutePath()));
				// System.out.println(file.getAbsolutePath());
			}
		} else if (obj.equals(btnLamMoiBang)) {
			xoaHetDuLieu();
			try {
				DocDuLieuTuArrayListVaoModel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public NhanVien revertNhanVienFromTextfields() {
		String maNV = txtMaNhanVien.getText();
		String tenNV = txtTenNV.getText();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String day = sdf.format(dateChooserNgaySinh.getDate());
		String date[] = day.split("-");
		int nam = Integer.parseInt(date[0]);
		int thang = Integer.parseInt(date[1]);
		int ngay = Integer.parseInt(date[2]);

		LocalDate lcDate = LocalDate.of(nam, thang, ngay);
		String cccd = txtCCCD.getText();
		String diaChi = txtDiaChi.getText();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
		boolean gioiTinh = comboBoxGioiTinh.getSelectedItem().toString() == "Nam" ? true : false;
		boolean chucVu = comboBoxChucVu.getSelectedItem().toString() == "Quản lý" ? true : false;
		boolean caLam = comboBoxCaLam.getSelectedItem().toString() == "Sáng" ? true : false;

		String hinhAnh = "";
		if (file != null) {
			hinhAnh = file.getName();
		}

		NhanVien nv = new NhanVien(maNV, tenNV, lcDate, cccd, diaChi, sdt, gioiTinh, email, chucVu, caLam, hinhAnh,
				null, null);
		return nv;
	}

	public void xoaHetDuLieu() {
		DefaultTableModel dtm = (DefaultTableModel) table_NhanVien.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void DocDuLieuTuArrayListVaoModel() throws Exception {
		iNhanvien = new NhanVienServiceImpl();
		dsNhanVien = iNhanvien.getDSNhanVien();
		int i = 1;
		for (NhanVien nv : dsNhanVien) {

			tableModel_NhanVien.addRow(new Object[] { i++, nv.getMaNhanVien(), nv.getHoTenNhanVien(), nv.getNgaySinh(),
					nv.getsDT(), nv.isGioiTinh() == true ? "Nam" : "Nữ", nv.isCaLamViec() == true ? "Sáng" : "Chiều",
					nv.isChucVu() == true ? "Quản lý" : "Nhân viên", nv.getDiaChi() });
			// System.out.println(nv.isGioiTinh());
		}
	}

	private void updateTableData(NhanVien nv) throws SQLException {
		iNhanvien = new NhanVienServiceImpl();
		dsNhanVien = iNhanvien.getDSNhanVien();
		int i = dsNhanVien.size();
		tableModel_NhanVien.addRow(new Object[] { i++, nv.getMaNhanVien(), nv.getHoTenNhanVien(), nv.getNgaySinh(),
				nv.getsDT(), nv.isGioiTinh() == true ? "Nam" : "Nữ", nv.isCaLamViec() == true ? "Sáng" : "Chiều",
				nv.isChucVu() == true ? "Quản lý" : "Nhân viên", nv.getDiaChi() });
	}

	private void moKhoaControls(boolean b) {
		btnThemNV.setEnabled(b);
		btnXoaNV.setEnabled(b);
		btnSuaNV.setEnabled(b);
		btnTimKiem.setEnabled(b);
	}

	private void moKhoaTextfields(boolean b) {
		txtMaNhanVien.setEditable(b);
		txtTenNV.setEditable(b);
		txtDiaChi.setEditable(b);
		txtSDT.setEditable(b);
		comboBoxChucVu.setEditable(b);
		comboBoxCaLam.setEditable(b);
		txtSDTTim.setEditable(b);
		txtTenTim.setEditable(b);
		txtCCCD.setEditable(b);
		txtEmail.setEditable(b);

	}

	private void clearTxtfields() {
		txtMaNhanVien.setText("");
		txtTenNV.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		comboBoxCaLam.setSelectedIndex(0);
		comboBoxChucVu.setSelectedIndex(0);
		txtSDTTim.setText("");
		txtTenTim.setText("");
		txtCCCD.setText("");
		txtEmail.setText("");
	}

	public ImageIcon ResizeImage(String imgPath) {
		ImageIcon myImage = new ImageIcon(imgPath);
		Image img = myImage.getImage();
		Image newImg = img.getScaledInstance(pnlHinhAnh.getWidth(), pnlHinhAnh.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}

//	private static String removeAccent(String s) {
//		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
//		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
//		return pattern.matcher(temp).replaceAll("");
//	}

	public void editOnRow() {
		int row = table_NhanVien.getSelectedRow();
		NhanVien nv = revertNhanVienFromTextfields();

		table_NhanVien.setValueAt(nv.getMaNhanVien(), row, 1);
		table_NhanVien.setValueAt(nv.getHoTenNhanVien(), row, 2);
		table_NhanVien.setValueAt(nv.getNgaySinh(), row, 3);
		table_NhanVien.setValueAt(nv.getsDT(), row, 4);
		table_NhanVien.setValueAt(nv.isGioiTinh() == true ? "Nam" : "Nữ", row, 5);
		table_NhanVien.setValueAt(nv.isCaLamViec() == true ? "Sáng" : "Chiều", row, 6);
		table_NhanVien.setValueAt(nv.isChucVu() == true ? "Quản lí" : "Nhân viên", row, 7);
		table_NhanVien.setValueAt(nv.getDiaChi(), row, 8);

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

}
