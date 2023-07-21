package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.ChatLieu;

public class ChatLieuDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public ChatLieuDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<ChatLieu> getListChatLieu() throws Exception {
		ArrayList<ChatLieu> list = new ArrayList<>();
		query = "SELECT maChatLieu, tenChatLieu\r\n" + "FROM     ChatLieu";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			ChatLieu chatLieu = new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu"));
			list.add(chatLieu);
		}
		return list;
	}

	public boolean themChatLieu(ChatLieu l) throws Exception {
		query = "INSERT [dbo].[ChatLieu] ([maChatLieu], [tenChatLieu]) VALUES ( ? , N'" + l.getTenChatLieu() + "')";
		ps = con.prepareStatement(query);
		ps.setString(1, l.getMaChatLieu());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public ChatLieu timChatLieu(String tenChatLieu) throws SQLException {
		query = "select * from ChatLieu where tenChatLieu = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, tenChatLieu);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu"));
		}
		return null;
	}

	public List<ChatLieu> getChatLieu(String maChatLieu) {
		List<ChatLieu> dsCL = new ArrayList<ChatLieu>();
		try {
			String query = "Select * from ChatLieu where maChatLieu = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, maChatLieu);
			rs = ps.executeQuery();
			while (rs.next()) {
				String macl = rs.getString("maChatLieu");
				String tencl = rs.getString("tenChatLieu");
				ChatLieu cl = new ChatLieu(macl, tencl);
				dsCL.add(cl);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCL;
	}
	public boolean kiemTraTonTaiChatLieu(String ten) throws SQLException {
		query = "select * from ChatLieu where tenChatLieu = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
