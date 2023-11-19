package com.lemonde.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemonde.web.services.OtherTextsService;

@Controller
@RequestMapping("/Admin/Home")
public class EditHome {
	@Autowired
	private OtherTextsService otherTextsService;

	@GetMapping
	public String getEditHome(Model model) {
		return "/admin/editHome";
	}

}
