package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import dao.TaiKhoanDao;
import db.DBConnection;
import entity.NhanVien;
import entity.TaiKhoan;
import service.impl.TaiKhoanServiceImpl;

import java.awt.Color;

public class FrmThemTaiKhoan extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtTenDangNhap;
	private JPasswordField passwordField;
	private JPasswordField passwordField_XacNhan;
	private JLabel lblTenDangNhap;
	private JLabel lblMatKhau;
	private JLabel lblXacNhanMK;
	private JButton btnTaoTaiKhoan;
	private JComboBox comboBoxQuyen;
	private JLabel lblLoaiTaiKhoan;
	private NhanVien nv;
	private TaiKhoanDao taiKhoanDao;
	private TaiKhoanServiceImpl iTaiKhoan;
	private Connection con = DBConnection.getInstance().getConnection();
	private String matKhau;
	private ArrayList<TaiKhoan> dsTK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmThemTaiKhoan frame = new FrmThemTaiKhoan(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public FrmThemTaiKhoan(NhanVien nv) {
		//
		setResizable(false);
		this.nv = nv;
		setTitle("Tạo tài khoản\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 351);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		lblTenDangNhap = new JLabel("Tên đăng nhập: ");
		lblTenDangNhap.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenDangNhap.setBounds(28, 63, 124, 19);
		contentPane.add(lblTenDangNhap);

		lblMatKhau = new JLabel("Mật khẩu: ");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatKhau.setBounds(28, 125, 91, 14);
		contentPane.add(lblMatKhau);

		lblXacNhanMK = new JLabel("Xác nhận mật khẩu: ");
		lblXacNhanMK.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblXacNhanMK.setBounds(28, 191, 158, 19);
		contentPane.add(lblXacNhanMK);

		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setBounds(245, 64, 210, 20);
		contentPane.add(txtTenDangNhap);
		txtTenDangNhap.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(245, 124, 210, 20);
		contentPane.add(passwordField);

		passwordField_XacNhan = new JPasswordField();
		passwordField_XacNhan.setBounds(245, 192, 210, 20);
		contentPane.add(passwordField_XacNhan);

		btnTaoTaiKhoan = new JButton("Tạo");
		btnTaoTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTaoTaiKhoan.setBounds(170, 266, 158, 35);
		contentPane.add(btnTaoTaiKhoan);

		comboBoxQuyen = new JComboBox();
		comboBoxQuyen.setBounds(245, 11, 210, 22);
		comboBoxQuyen.addItem("Quản lý");
		comboBoxQuyen.addItem("Nhân viên");
		comboBoxQuyen.setSelectedItem(nv.isChucVu() == true ? "Quản lý" : "Nhân viên");
		comboBoxQuyen.setEditable(false);
		contentPane.add(comboBoxQuyen);

		lblLoaiTaiKhoan = new JLabel("Loại tài khoản: ");
		lblLoaiTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoaiTaiKhoan.setBounds(28, 11, 124, 22);
		contentPane.add(lblLoaiTaiKhoan);

		btnTaoTaiKhoan.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnTaoTaiKhoan)) {
			iTaiKhoan = new TaiKhoanServiceImpl();
			try {
				if (iTaiKhoan.insertAccount(revertTaiKhoanFromTextfields()) > 0) 
					this.setVisible(false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Tên đăng nhập có dạng: TK001, không được trùng");
			}
		}

	}

	public TaiKhoan revertTaiKhoanFromTextfields() throws Exception {
		iTaiKhoan = new TaiKhoanServiceImpl();
		if (iTaiKhoan.getList() != null) {
			dsTK = iTaiKhoan.getList();
			String tenDN = txtTenDangNhap.getText();
			if (tenDN.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Tên đăng nhập không được rỗng");
			} else {
				char[] pf1 = passwordField.getPassword();
				String valuePass1 = new String(pf1);
				char[] pf2 = passwordField_XacNhan.getPassword();
				String valuePass2 = new String(pf2);
				if (valuePass1.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Mật khẩu không được rỗng");
				} else {
					if (valuePass1.equals(valuePass2)) {
						matKhau = valuePass2;
						boolean quyen = comboBoxQuyen.getSelectedItem().toString() == "Quản lý" ? true : false;
						TaiKhoan tk = new TaiKhoan(tenDN, matKhau, nv, quyen);
						return tk;
					} else {
						JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không đúng");
						passwordField.setText("");
						passwordField_XacNhan.setText("");
						passwordField.requestFocus();
						return null;
					}
				}

			}

		}
		return null;

	}
}
