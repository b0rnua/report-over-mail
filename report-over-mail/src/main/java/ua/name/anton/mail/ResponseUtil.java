package ua.name.anton.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;


public class ResponseUtil {
	
	static Logger log = Logger.getLogger(Response.class.getName());
	
	public static void doResponse(Response response) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.yandex.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		log.info("Connect to smtp server...");
		Session se = Session.getDefaultInstance(
				props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("noreply@***-lombard.com.ua", "*******");
					}
				}
				);
		
		try {
			Message msg = new MimeMessage(se);
			msg.setFrom(new InternetAddress("noreply@ucs-lombard.com.ua"));
			
			Multipart multipart = new MimeMultipart();
			MimeBodyPart msgBodyPart = new MimeBodyPart();
			DataSource source = new ByteArrayDataSource(response.getAttache(), response.getAttMimeType());
			msgBodyPart.setDataHandler(new DataHandler(source));
			msgBodyPart.setFileName(MimeUtility.encodeText(response.getAttName()));
			multipart.addBodyPart(msgBodyPart);
			msg.setContent(multipart);

			
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(response.getRecipient()));
			msg.setSubject(response.getSubject());
			
			log.info("Sending message...");
			Transport.send(msg);
			
		} catch(MessagingException e) {
			log.info(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(e.getMessage());
		}
		log.info("Sending completed.");
	}

}
