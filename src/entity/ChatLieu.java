package entity;

public class ChatLieu {
	private String maChatLieu;
	private String tenChatLieu;

	public ChatLieu() {
		super();

	}

	public ChatLieu(String maChatLieu) {
		super();
		this.maChatLieu = maChatLieu;
	}

	public ChatLieu(String maChatLieu, String tenChatLieu) {
		super();
		this.maChatLieu = maChatLieu;
		this.tenChatLieu = tenChatLieu;
	}

	public String getMaChatLieu() {
		return maChatLieu;
	}

	public void setMaChatLieu(String maChatLieu) {
		this.maChatLieu = maChatLieu;
	}

	public String getTenChatLieu() {
		return tenChatLieu;
	}

	public void setTenChatLieu(String tenChatLieu) {
		this.tenChatLieu = tenChatLieu;
	}

	@Override
	public String toString() {
		return "ChatLieu [maChatLieu=" + maChatLieu + ", tenChatLieu=" + tenChatLieu + "]";
	}

}
