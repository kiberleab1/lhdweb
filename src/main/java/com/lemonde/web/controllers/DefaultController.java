package com.lemonde.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemonde.web.domains.User;
import com.lemonde.web.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/default")
public class DefaultController {

	// private HotelService hotelService;
	private UserService userService;
	private User user;
	// private Hotel hotel;

	@Autowired
	public DefaultController(UserService userService) {
		// this.hotelService=hotelService;
		this.userService = userService;
	}

	@GetMapping
	public String getDfault(@AuthenticationPrincipal UserDetails userDetails) {

		if (userDetails.getAuthorities().toString().equals("[ADMIN]")) {

			user = (User) userDetails;
			user = this.userService.findByEmail(userDetails.getUsername());

			return "redirect:/Admin/Home";
		} else if (userDetails.getAuthorities().toString().equalsIgnoreCase("EMPLOYEERS")) {
			user = (User) userDetails;
			user = this.userService.findByEmail(userDetails.getUsername());
			return "redirect:/EMPLOYEERS/Home";
		} else {
			return "redirect:/AccessDenied";
		}
	}
}
