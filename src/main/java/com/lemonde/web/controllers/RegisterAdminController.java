package com.lemonde.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemonde.web.domains.User;
import com.lemonde.web.services.UserService;

@Controller
@RequestMapping("/Admin/Register")
public class RegisterAdminController {

	private UserService userService;

	@Autowired
	public RegisterAdminController(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute(name = "registerAdmin")
	public User registerManager(Model model) {
		return new User();
	}

	@GetMapping
	public String getRegister() {

		return "admin/registerAdmin";
	}

	@PostMapping
	public String processRegisterHotel(@Valid @ModelAttribute("registerAdmin") User Admin, Errors errors) {

		if (errors.hasErrors()) {

			return "admin/registerAdmin";
		}
		try {
			this.userService.saveAdminUser(Admin);
		} catch (Exception e) {
			// add custom error message
			errors.rejectValue("", "Something Went wrong");
			return "redirect:/Admin/Register?error";
		}
		// User registedManager=userRepository.save(manager);
		return "redirect:/Admin/Home";

	}

}
