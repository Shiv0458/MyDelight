package com.MyDelight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendSimpleEmail(String toEmail, String userName) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("puredelightmilk@gmail.com");
		message.setTo(toEmail);
		message.setText("Thanks "+userName+"  for Registering to our Delight App... we are here to provide quality milk at your door step.");
		message.setSubject("You have successfully registerd to Delight App");
		mailSender.send(message);
		System.out.println("Mail sent...");
	}


}
