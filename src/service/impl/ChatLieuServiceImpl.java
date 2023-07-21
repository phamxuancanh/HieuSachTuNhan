package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ChatLieuDao;
import entity.ChatLieu;
import service.ChatLieuService;

public class ChatLieuServiceImpl implements ChatLieuService {
	ChatLieuDao chatLieuDao = new ChatLieuDao();

	@Override
	public ArrayList<ChatLieu> getListChatLieu() throws Exception {
		// TODO Auto-generated method stub
		return chatLieuDao.getListChatLieu();
	}

	@Override
	public boolean themChatLieu(ChatLieu l) throws Exception {
		if(chatLieuDao.kiemTraTonTaiChatLieu(l.getTenChatLieu()))
			return false;
		return chatLieuDao.themChatLieu(l);
	}

	@Override
	public boolean xoaChatLieu(String maChatLieu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ChatLieu> getChatLieu(String maChatLieu) {
		// TODO Auto-generated method stub
		return chatLieuDao.getChatLieu(maChatLieu);
	}

	@Override
	public ChatLieu timChatLieu(String tenChatLieu) throws SQLException {
		// TODO Auto-generated method stub
		return chatLieuDao.timChatLieu(tenChatLieu);
	}

}
