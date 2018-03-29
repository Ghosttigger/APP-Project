package mail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class MailServer {

	private String smtp_host;
	private String smtp_login;
	private String smtp_password;
	private String from;
	
	public MailServer(String host, String login, String mdp, String from) {
		this.smtp_host = host;
		this.smtp_login = login;
		this.smtp_password = mdp;
		this.from = from;
	}
	
	public String sendNotif(Notification notif) {
		
		String response = "";
		Properties properties = System.getProperties();	      
		properties.put("mail.smtps.host", this.smtp_host);
		properties.put("mail.smtps.auth","true");
		Session session = Session.getDefaultInstance(properties);
		
		MimeMessage message = new MimeMessage(session);
		try {
			message.addHeader("MIME-Version", "1.0");
			message.addHeader("Content-Type", "multipart/alternative;");
			message.setFrom(from);
			message.addRecipients(Message.RecipientType.TO, notif.getDestinataire());
			
			if(notif.getOther_recipients() != null) {
				for(Destinataire d : notif.getOther_recipients()) {
					message.addRecipients(d.getType(), d.getAddress());
				}
			}
			message.setSubject(notif.getObject());
			message.setText(notif.getMsg()+notif.getSite()+"\nContenu :\n\n"+notif.getContenu());
			
			SMTPTransport transport = (SMTPTransport) session.getTransport("smtps");
			transport.connect(this.smtp_login, this.smtp_password);
			
			response += "##### "+getDate(System.currentTimeMillis())+" #####\n\n";
			for(Address s : message.getAllRecipients()) {
				Address[] a = {s};
				transport.sendMessage(message, a);
				response += 
						"Server response: "+transport.getLastServerResponse() +
						"Return code: "+transport.getLastReturnCode() + "\n\n" +
						"Sent message successfully at "+s.toString() +
						"\n\n---------------\n\n";
			}
			
			transport.close();
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return (response.isEmpty())?"No Response":response;
	}
	
	public String getDate(long millis) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");    
		Date date = new Date(millis);
		
		return sdf.format(date);
	}
}
