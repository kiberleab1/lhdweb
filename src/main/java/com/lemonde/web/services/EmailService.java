package com.lemonde.web.services;

public interface EmailService {
	public void SendSimpleMessage(String to,String from,String subject,String text ) ;
	public void sendMesssageWithAttachment(String to,String from,String subject,String text,String pathToAttachemnt);
}
