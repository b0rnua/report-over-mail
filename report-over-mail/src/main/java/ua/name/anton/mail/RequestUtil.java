package ua.name.anton.mail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import org.apache.log4j.Logger;

public class RequestUtil {
		
	static Logger log = Logger.getLogger(RequestUtil.class.getName());
	
	public static List<Request> checkUnseenRequest() {
		log.info("Check unseen requests");
		List<Request> result = new ArrayList<Request>();
        
		Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.port", "993");
        properties.put("mail.imaps.starttls.enable", "true");
		Session session = Session.getInstance(properties);
        Store store = null;
		try {
			store = session.getStore("imaps");
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
        	log.info("Connect to the imap server...");
			store.connect("imap.yandex.com", "noreply@***-lombard.com.ua", "********");
			Folder inbox = store.getFolder("requests");
	        inbox.open(Folder.READ_WRITE);
	        
	        Flags seen = new Flags(Flags.Flag.SEEN);
	        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
	        
	        Message messagess[] = inbox.search(unseenFlagTerm);
	        log.info("Get " + messagess.length + " request(s)");
	        for (Message msg:messagess) {
	        	Request request = new Request();
	        	Address[] in = msg.getFrom();            
	        	
	            for (Address address:in) {
	            	request.setFrom(address.toString());  	
	            }
	            
	            request.setRequestDate(msg.getSentDate());
	            getSubject(msg.getSubject(), request);
	        	result.add(request);
	        }
	        
	        inbox.setFlags(messagess, seen, true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(e.getMessage());
			log.info("End with error!");
		} finally {
			if (store.isConnected()) {
				try {
					store.close();
					log.info("Connection close.");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
        log.info("Checking completed.");
		return result;
	}
	
	public static boolean getSubject(String sbj, Request request) {
		boolean result = true;
		Integer reportType = null;
		String args[] = sbj.split(" ");
		
		if (args.length > 0) {	
			
			try {
				reportType = new Integer(args[0]);
			} catch (NumberFormatException nfe) {
				result = false;
			}
			
			if (args.length > 1) {
				args = Arrays.copyOfRange(args, 1, args.length);
			} else {
				args = new String[0];
			}
		} else {
			result = false;
		}
		
		request.setArgs(args);
		request.setReportType(reportType);
		
		return result;
	}

}
