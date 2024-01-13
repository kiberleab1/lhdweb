package com.lemonde.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String onRoot() {
		return "redirect:/lhd/Home";
	}

	@GetMapping("/admin/login")
	public String getLogin(Model model) {
		return "admin/login";
	}
}
