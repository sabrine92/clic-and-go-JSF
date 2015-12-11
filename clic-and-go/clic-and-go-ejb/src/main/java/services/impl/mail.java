package services.impl;

import java.io.IOException;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import services.interfaces.mailLocal;
import services.interfaces.mailRemote;

//import javax.mail.Message;

/**
 * Session Bean implementation class mail
 */
@Stateless
public class mail implements mailRemote, mailLocal {
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;


	public void mail(String subject, String text, String destinataire) throws IOException {
			// 1 -> Cr�ation de la session
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true"); // ********
			props.put("mail.smtp.host", "smtp.gmail.com"); // serveur
			props.put("mail.smtp.port", "587"); // port

			//properties.setProperty("mail.smtp.host", SMTP_HOST1);
			//properties.setProperty("mail.smtp.user", LOGIN_SMTP1);
			//properties.setProperty("mail.from", IMAP_ACCOUNT1);
			Session session = Session.getInstance(props);
			new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication( "yosr.gouider@esprit.tn", "hhyg0110@@");// Specify the Username and the PassWord
			}
			};

			// 2 -> Cr�ation du message
			MimeMessage message = new MimeMessage(session);
			try {
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(text);



			// On regroupe les deux dans le message
			MimeMultipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);


			message.setContent(mp);
			System.out.printf(destinataire);
			message.setSubject(subject);
			message.addRecipients(Message.RecipientType.TO, destinataire);
			message.addRecipients(Message.RecipientType.CC, "yosr.gouider@esprit.tn");
			} catch (MessagingException e) {
			e.printStackTrace();
			}

			// 3 -> Envoi du message
			Transport transport = null;
			try {
			transport = session.getTransport("smtp");
			transport.connect("yosr.gouider@esprit.tn", "smc0312/*");
			transport.sendMessage(message, new Address[]{new InternetAddress(destinataire)});
			} catch (MessagingException e) {
			e.printStackTrace();
			} finally {
			try {
			if (transport != null) {
			transport.close();
			}
			} catch (MessagingException e) {
			e.printStackTrace();
			}

			}
			}

	
}
