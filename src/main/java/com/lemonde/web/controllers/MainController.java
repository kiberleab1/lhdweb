package com.lemonde.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemonde.web.services.EmailService;

@Controller
public class MainController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/")
	public String onRoot() {
		return "redirect:/lhd/Home";
	}

	@GetMapping("/admin/login")
	public String getLogin(Model model) {
		return "admin/login";
	}
}
