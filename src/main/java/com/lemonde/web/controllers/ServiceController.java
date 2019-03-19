package com.lemonde.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lhd/Service")
public class ServiceController {
	@GetMapping
	public String getService(Model model) {
		return "services";
	}
}
