package mail;

public class MainMail {

	public static void main(String[] args) {
		
		MailServer ms = new MailServer("smtp.iut.univ-paris8.fr", "jprochard@iut.univ-paris8.fr", "F4ZKa6R6e", "no-reply@iut.univ-paris8.fr");
		Site site = new Site("lemonde.fr", "AAAAA", 10000);
		Notification notif = new Notification("jfouquet@iut.univ-paris8.fr", site.getUrl());
		ms.sendNotif(notif);
		
		
		
	}

}
