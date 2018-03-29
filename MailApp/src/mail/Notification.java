package mail;

import java.util.ArrayList;

public class Notification {
	
	private String destinataire;
	private String site;
	private ArrayList<Destinataire> other_recipients;
	private final String object = "Notification - ";
	private final String msg = "Hey ! An update has been detected on this site: ";
	private String contenu;

	public Notification(String destinataire, String site, String contenu) {
		this.destinataire = destinataire;
		this.other_recipients = new ArrayList<>();
		this.site = site;
		this.contenu = contenu;
	}
	
	public void addOtherRecipent(Destinataire new_destinataine) {
		this.other_recipients.add(new_destinataine);
	}
	
	public String getDestinataire() {
		return this.destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public ArrayList<Destinataire> getOther_recipients() {
		return other_recipients;
	}

	public void setOther_recipients(ArrayList<Destinataire> other_recipients) {
		this.other_recipients = other_recipients;
	}

	public String getObject() {
		return this.object;
	}
	
	public String getContenu() {
		return this.contenu;
	}

	public String getMsg() {
		return this.msg;
	}
}
