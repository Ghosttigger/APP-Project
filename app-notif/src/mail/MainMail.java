package mail;

public class MainMail {

	public static void main(String[] args) {
		
        String login = "";
        String passwd = "";
		MailServer ms = new MailServer("smtp.iut.univ-paris8.fr", login, passwd, "no-reply@iut.univ-paris8.fr");
		Site site = new Site("lemonde.fr", "AAAAA", 10000);
		Notification notif = new Notification("jfouquet@iut.univ-paris8.fr", site.getUrl());
		ms.sendNotif(notif);
		
		
		
	}

}
