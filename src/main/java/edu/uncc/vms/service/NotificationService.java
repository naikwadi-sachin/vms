package edu.uncc.vms.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface NotificationService {

	public void sendEmail(String host, String port, final String userName,
			final String password, String toAddress, String subject,
			String message) throws AddressException, MessagingException;

	public void sendEmail(String toAddress, String subject, String message)
			throws AddressException, MessagingException;
}
