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

import com.lemonde.web.domains.Contact;
import com.lemonde.web.services.EmailService;

@Controller
@RequestMapping("/lhd/contact")
public class ContactController {
	private final String contactEmail = "contact@lhdconsult.org";
	@Autowired
	private EmailService emailService;

	@GetMapping
	public String getContact(Model model) {
		model.addAttribute("newContact", new Contact());
		return "client/contact";
	}

	@PostMapping
	public String saveContact(@Valid @ModelAttribute("newContact") Contact contact, Model Model, Errors error) {
		if (error.hasErrors()) {
			return "contact";
		}
		emailService.SendSimpleMessage("kiberleabdemassie@gmail.com", contactEmail,
				contact.getName() + "@ " + contact.getEmail(), contact.getMessage());
		emailService.SendSimpleMessage(contact.getEmail(), contactEmail, "Contact @ LHD",
				"Thank you for contacting us and will respond to your quires.  Please\r\n" +
						"do reach out to the following emails and we will be able to respond to\r\n" +
						"you as soon as possible.\r\n" +
						"antenanie35@gmail.com or anduye2@gmail.com");
		return "redirect:/lhd/contact";
	}
}
