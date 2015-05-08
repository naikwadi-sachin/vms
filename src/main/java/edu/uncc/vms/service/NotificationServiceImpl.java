package edu.uncc.vms.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

	private String host = "smtp.gmail.com";
	private String port = "587";
	private String userName = "info.vmsusa@gmail.com";
	private String password = "admin@vms";
	
	private boolean sendNotifications = false;

	@Override
	public void sendEmail(String host, String port, String userName,
			String password, String toAddress, String subject, String message)
			throws AddressException, MessagingException {
		// sets SMTP server properties
		
		if(!sendNotifications)
			return;
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(properties, null);
		session.setDebug(true);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setContent(message, "text/html");
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// sends the e-mail
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.gmail.com", userName, password);
		transport.sendMessage(msg, msg.getAllRecipients());

	}

	@Override
	public void sendEmail(String toAddress, String subject, String message)
			throws AddressException, MessagingException {

		System.out.println("sendEmail toAddress="+toAddress + ";subject="+ subject + ";message=" + message);
		if(!sendNotifications)
			return;
		
		// sets SMTP server properties
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		// properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {

			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getDefaultInstance(properties, null);
		session.setDebug(true);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setContent(message, "text/html");
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		// msg.setText(message);

		// sends the e-mail
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.gmail.com", userName, password);
		transport.sendMessage(msg, msg.getAllRecipients());

	}
	/*
	public static void main(String args[])
	{
		NotificationServiceImpl s = new NotificationServiceImpl();
		try {
			s.sendEmail("vramcha1@uncc.edu", "test email", "test mail message");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
*/
}
