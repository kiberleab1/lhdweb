package com.lemonde.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemonde.web.domains.OtherTexts;
import com.lemonde.web.services.OtherTextsService;

@Controller
@RequestMapping("/lhd/Home")
public class HomeController {
	
	private OtherTextsService otherTextsService;
	
	@Autowired
	public HomeController(OtherTextsService otherTextsService) {
		this.otherTextsService=otherTextsService;
	}
	
	@ModelAttribute(name="hometexts")
	public List<OtherTexts> homeModels(Model model){
		return otherTextsService.findByPage("home");
	}
	@GetMapping
	public String getHome() {
		return "home";
	}
}
