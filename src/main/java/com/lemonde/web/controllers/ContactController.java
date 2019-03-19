package com.lemonde.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lhd/Contact")
public class ContactController {
	@GetMapping
	public String getContact(Model model) {
		return "contact";
	}
}
