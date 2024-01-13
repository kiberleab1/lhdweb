package com.lemonde.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemonde.web.services.UserService;

@Controller
@RequestMapping("/default")
public class DefaultController {

	public DefaultController(UserService userService) {
	}

	@GetMapping
	public String getDfault(@AuthenticationPrincipal UserDetails userDetails) {
		try {

			if (userDetails.getAuthorities().toString().equals("[ADMIN]")) {
				return "redirect:/Admin/Home";
			}
			return "redirect:/admin/login";

		} catch (Exception e) {
			return "redirect:/admin/login";
		}
	}
}
