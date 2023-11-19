package com.lemonde.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LemondeApplication extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LemondeApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(LemondeApplication.class, args);
	}

	/*
	 * @Bean
	 * public JavaMailSender getJavaMailSender() {
	 * JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	 * mailSender.setHost("mail.lhdconsult.org");
	 * mailSender.setPort(587);
	 * 
	 * mailSender.setUsername("contact@lhdconsult.org");
	 * mailSender.setPassword("711380Dm");
	 * 
	 * Properties props = mailSender.getJavaMailProperties();f
	 * props.put("mail.transport. protocol", "smtp");
	 * props.put("mail.smtp.auth", "true");
	 * props.put("mail.smtp.starttls.enable", "true");
	 * props.put("mail.debug", "true");
	 * 
	 * return mailSender;
	 * }
	 */
	@Bean
	CommandLineRunner commandLineRunner() {
		return (args) -> {
			System.err.println("Hello World from Spring Boot 2!");
			// System.out.println("Hello World from Spring Boot 2!");
		};
	}
}
