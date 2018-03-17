package mail;

import javax.mail.Message;

public class Destinataire {

	private String address;
	private Message.RecipientType type;
	
	public Destinataire(String address, Message.RecipientType type) {
		this.address = address;
		this.type = type;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Message.RecipientType getType() {
		return this.type;
	}

	public void setType(Message.RecipientType type) {
		this.type = type;
	}
}
