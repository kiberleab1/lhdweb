package com.lemonde.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class LemondeApplication extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LemondeApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(LemondeApplication.class, args);
	}
	/*@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("mail.lhdconsult.org");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername("contact@lhdconsult.org");
	    mailSender.setPassword("711380Dm");
	     
	    Properties props = mailSender.getJavaMailProperties();f
	    props.put("mail.transport. protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
	    return mailSender;
	}*/
}
