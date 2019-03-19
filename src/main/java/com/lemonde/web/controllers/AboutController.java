package com.lemonde.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lhd/About")
public class AboutController {
	@GetMapping
	public String getAbout(Model model) {
		return "about";
	}
}
