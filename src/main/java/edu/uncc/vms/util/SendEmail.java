package edu.uncc.vms.util;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import edu.uncc.vms.web.form.ControllerCodes;

public class SendEmail {

	private static String host = "smtp.gmail.com";
	private static String port = "587";

	public static void main(String[] args) {
		try {
			sendEmail(host, port, ControllerCodes.userName, ControllerCodes.pass,
					"sachya39@gmail.com", "Thank You!!",
					"Welcome, thank you for joining..");
		} catch (Exception e) {

		}
	}

	public static void sendEmail(String host, String port,
			final String userName, final String password, String toAddress,
			String subject, String message) throws AddressException,
			MessagingException {

		// sets SMTP server properties
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.fallback", "false");
//		properties.put("mail.smtp.starttls.enable", "true");

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
		msg.setContent(message,"text/html");
		msg.setSubject(subject);
		msg.setSentDate(new Date());
//		msg.setText(message);

		// sends the e-mail
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.gmail.com", userName, password);
		transport.sendMessage(msg,msg.getAllRecipients());

	}

	private void old() {
		// Recipient's email ID needs to be mentioned.
		String to = "sachya39@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "sachin.naikwadi.1@gmail.com";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Now set the actual message
			message.setText("This is actual message");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
