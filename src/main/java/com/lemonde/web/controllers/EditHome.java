package com.lemonde.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin/Home")
public class EditHome {

	@GetMapping
	public String getEditHome(Model model) {
		try {
			return "/admin/editHome";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

}
