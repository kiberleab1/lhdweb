package com.lemonde.web.services;

import java.io.File;
import java.nio.file.Path;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
			@Autowired
			public JavaMailSender eMailSender;

			@Override
			public void SendSimpleMessage(String to, String from,String subject, String text) {
					SimpleMailMessage message=new SimpleMailMessage();
					message.setFrom(from);
					message.setTo(to);
					message.setSubject(subject);
					message.setText(text);
					eMailSender.send(message);
				
			}

			@Override
			public void sendMesssageWithAttachment(String to, String from,String subject, String text, String pathToAttachemnt) {
				Mime	Message message=eMailSender.createMimeMessage();
				try {
					MimeMessageHelper helper=new MimeMessageHelper(message, true);
					helper.setFrom(from);
					helper.setTo(to);
					helper.setSubject(subject);
					helper.setText(text);
					FileSystemResource file=new FileSystemResource(new File(pathToAttachemnt));
					helper.addAttachment("Invoice", file);
					eMailSender.send(message);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		
}
