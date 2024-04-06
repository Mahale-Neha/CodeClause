package com.learncode.email_sending;



import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App 
{
    private static Session session;
	public static void main( String[] args )
    {
        System.out.println("preparing to send message");
        String message = "Hello , Dear this is message for security check . ";
        String subject = "CodersArea : Confirmation";
        String to = "leansoftwithKavita@gmail.com";
        String from = "shruti23@gmail.gmailcom";
        
     //   sendEmail(message,subject,to,from);
        sendAttach(message,subject,to,from);
    }
	// this is responsible to send the message with attachment
    private static void sendAttach(String message, String subject, String to, String from) {
	
    	//Variable for email
    			String host="smtp.gmail.com";
    			
    			//get the system properties
    			Properties Properties = System.getProperties();
    			System.out.println("PROPERTIES "+Properties);
    			
    			//setting important information to properties object
    			
    			
    			//host set
    			Properties.put("mail.smpt.host", host);
    			Properties.put("mail.smpt.port", "25");
    			Properties.put("mail.smpt.ssl.enable", "true");
    			Properties.put("mail.smpt.auth", "true");
    			
    			//step 1: to get the session object..
    			 Session.getInstance(Properties , new Authenticator() {

    				@Override
    				protected PasswordAuthentication getPasswordAuthentication() {
    					
    					return new PasswordAuthentication("leansoftwithKavita@gmail.com", "12345");
    				}
    				
    			});
    			
    			
    			
    			 //step 2 : Compose the message [text,multimedia]
    			 MimeMessage m = new MimeMessage(session);
    			 
    			 try {
    			 // from email
    			 m.setFrom(from);
    			 
    			 //adding recipient to message
    			 m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    			 
    			 //adding subject to message 
    			 m.setSubject(subject);
    			
    			
    			 //attachment..
    			 
    			 //file path
    			 String path="\"C:\\Users\\DELL\\Desktop\\IP\"";
    			 
    			 MimeMultipart mimeMultipart = new MimeMultipart();
    				//text
    				//file
    				
              	 MimeBodyPart textMime	= new MimeBodyPart();
    				
    			 MimeBodyPart fileMime  =new MimeBodyPart();
    			 
    			 
    			 try {
    				 textMime.setText(message);
    				 
    				 File file=new File(path);
    				 fileMime.attachFile(file);
    				 
    				 mimeMultipart.addBodyPart(textMime);
    				 mimeMultipart.addBodyPart(fileMime);
    				 
    				 
    			 } catch (Exception e) {
    				 e.printStackTrace();
    			 }
    			 
    			 
    			 m.setContent(mimeMultipart);
    			 
    			 
    			 //step 3: send the message using Transport class
    			 Transport.send(m);
    			 
    			 System.out.println("Sent success..................");
    			 
    			 }catch (Exception e) {
    				 e.printStackTrace();
    			 }
    		}
    		
    	
    	
    	
    	
    	
    	
    	
		
	
	//this is responsible to send email..
	private static void sendEmail(String message, String subject, String to, String from) {
		
		
		//Variable for email
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties Properties = System.getProperties();
		System.out.println("PROPERTIES "+Properties);
		
		//setting important information to properties object
		
		
		//host set
		Properties.put("mail.smpt.host", host);
		Properties.put("mail.smpt.port", "25");
		Properties.put("mail.smpt.ssl.enable", "true");
		Properties.put("mail.smpt.auth", "true");
		
		//step 1: to get the session object..
		 Session.getInstance(Properties , new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("leansoftwithKavita@gmail.com", "12345");
			}
			
		});
		
		
		
		 //step 2 : Compose the message [text,multimedia]
		 MimeMessage m = new MimeMessage(session);
		 
		 try {
		 // from email
		 m.setFrom(from);
		 
		 //adding recipient to message
		 m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		 
		 //adding subject to message 
		 m.setSubject(subject);
		
		 //adding text to message
		  m.setText(message);
		 
		 //step 3: send the message using Transport class
		 Transport.send(m);
		 
		 System.out.println("Sent success..................");
		 
		 }catch (Exception e) {
			 e.printStackTrace();
		 }
	}
	
}
