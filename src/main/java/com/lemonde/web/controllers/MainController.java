package com.lemonde.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemonde.web.services.EmailService;

@Controller
public class MainController {
	private final String contactEmail="contact@lhdconsult.org";
	@Autowired
	private EmailService emailService;
	@GetMapping("/")
	public String onRoot() {
		emailService.sendMesssageWithAttachment(contactEmail,"kiberleabdemassie@gmail.com", "Testing", "test","src\\main\\resources\\static\\image\\WIN_20180823_06_35_44_Pro\\WIN_20180823_06_35_44_Pro.jpg");
		return "redirect:/lhd/Home";
	}
	@GetMapping("/login")
	public String getLogin(Model model) {
		return "login";
	}
}
