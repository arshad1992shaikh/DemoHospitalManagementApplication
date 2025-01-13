package com.hospital_management.demo_hospital_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String to, String subject, String templete) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(templete, true); // Use 'true' for HTML content

			mailSender.send(message);
			System.out.println("Email sent successfully.");
		} catch (MessagingException e) {
		
			System.err.println("Error while sending email: " + e.getMessage());
		}
	}

}
