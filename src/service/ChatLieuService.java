package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.ChatLieu;

public interface ChatLieuService {
	public ArrayList<ChatLieu> getListChatLieu() throws Exception;

	public boolean themChatLieu(ChatLieu l) throws Exception;

	public boolean xoaChatLieu(String maChatLieu);

	public List<ChatLieu> getChatLieu(String maChatLieu);

	public ChatLieu timChatLieu(String tenChatLieu) throws SQLException;
}
