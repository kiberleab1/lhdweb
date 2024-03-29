package com.lemonde.web.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lemonde.web.services.ClientService;
import com.lemonde.web.services.ExperianceService;
import com.lemonde.web.services.OtherTextsService;
import com.lemonde.web.services.TestimonialService;

@Controller
@RequestMapping("/lhd/otherclients")
public class ClientOtherController {
	private ClientService clientService;
	private TestimonialService testomonialService;
	private OtherTextsService otherTextsService;

	public ClientOtherController(ExperianceService experianceService, TestimonialService testomonialService,
			ClientService clientService, OtherTextsService otherTextsService) {
		this.clientService = clientService;
		this.otherTextsService = otherTextsService;
		this.testomonialService = testomonialService;
	}

	@GetMapping
	public String recentTacos(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "type", defaultValue = "research") String type, Model model) {
		try {

			PageRequest pageRequest = PageRequest.of(page, size);
			model.addAttribute("unClients",
					this.clientService.findPageByTypeAndCountry(pageRequest, "un", "other").getContent());
			model.addAttribute("govClients",
					this.clientService.findPageByTypeAndCountry(pageRequest, "gov", "other").getContent());
			model.addAttribute("nongovClients",
					this.clientService.findPageByTypeAndCountry(pageRequest, "nongov", "other").getContent());

			model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
			model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

			model.addAttribute("Clients", clientService.findImages());
			model.addAttribute("Testimonies", testomonialService.findAll());

			return "client/clientsinothers";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}
}
