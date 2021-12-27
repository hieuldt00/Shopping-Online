/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.mail.Authenticator;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author duong
 */
public class Mail {

	String fromEmail, password;
	private static final String MAIL_SERVER = "smtp";
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final int SMTP_HOST_PORT = 587;

	public Mail() {
	}

	public Mail(String fromEmail, String password) {
		this.fromEmail = fromEmail;
		this.password = password;
	}

	public void sendMail(String toMail, String subject, String body) throws AddressException, MessagingException, UnsupportedEncodingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(fromEmail));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail, false));
		msg.setSubject(subject, "UTF-8");
		msg.setText(body, "UTF-8");
		msg.setSentDate(new Date());

		Transport.send(msg);

	}

	public void sendCCMail(String[] cc, String subject, String body) throws MessagingException {

//---------------------------------------------STEP 1---------------------------------------------
		System.out.println("\n 1st ===> Setup SMTP Mail Server Properties..!");

		// Get system properties
		Properties props = System.getProperties();

		// Setup mail server        
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

		//---------------------------------------------STEP 2---------------------------------------------
		System.out.println("\n\n 2nd ===> Get Mail Session..");
		// Get the Session object.

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getInstance(props, auth);

		// Create a default MimeMessage object.
		MimeMessage message = new MimeMessage(session);

		try {

			//---------------------------------------------
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(fromEmail));

			InternetAddress[] ccAddress = new InternetAddress[cc.length];

			// To get the array of ccaddresses
			for (int i = 0; i < cc.length; i++) {
				ccAddress[i] = new InternetAddress(cc[i]);
			}

			// Set cc: header field of the header.
			for (int i = 0; i < ccAddress.length; i++) {
				message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
			}

			// Set Subject: header field
			message.setSubject(subject, "UTF-8");
			// Now set the date to actual message
			message.setSentDate(new Date());

			// Now set the actual message
			message.setContent(body, "text/html");

			//---------------------------------------------STEP 3---------------------------------------------
			System.out.println("\n\n 3rd ===> Get Session and Send Mail");
			// Send message
			Transport transport = session.getTransport(MAIL_SERVER);
			transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, fromEmail, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Sent Message Successfully....");
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	public static void main(String[] args) throws MessagingException, AddressException, UnsupportedEncodingException, IOException {
		Mail m = new Mail("duongtongmon5@gmail.com", "anhduong259");
		m.sendMail("duongnthe141567@fpt.edu.vn", "hello", "https://www.facebook.com/");
	}
}
