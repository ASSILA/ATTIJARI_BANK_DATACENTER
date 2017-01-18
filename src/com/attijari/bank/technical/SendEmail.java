package com.attijari.bank.technical;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendEmail extends Authenticator{


    public static void sendEMail(String recepteur, String sujet,String msg) throws MessagingException{


		final String username = "assilaali@gmail.com";
		final String password = "ALI11";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });



			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("assilaali@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(recepteur));
			message.setSubject(sujet);
			message.setText(msg);

			Transport.send(message);

			System.out.println("Done");



	}

}
