package com.lemonde.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/lhd/clientinethiopia")
public class ClientEthiopiaController {
	private ExperianceService experianceService;
	private ClientService clientService;
	private TestimonialService testomonialService;
	private OtherTextsService otherTextsService;

	@Autowired
	public ClientEthiopiaController(ExperianceService experianceService, TestimonialService testomonialService,
			ClientService clientService, OtherTextsService otherTextsService) {
		this.experianceService = experianceService;
		this.clientService = clientService;
		this.otherTextsService = otherTextsService;
		this.testomonialService = testomonialService;
	}

	@GetMapping
	public String recentTacos(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "1000") int size,
			@RequestParam(name = "type", defaultValue = "research") String type, Model model) {

		PageRequest pageRequest = PageRequest.of(page, size);
		model.addAttribute("unClients",
				this.clientService.findPageByTypeAndCountry(pageRequest, "un", "Ethiopia").getContent());
		model.addAttribute("govClients",
				this.clientService.findPageByTypeAndCountry(pageRequest, "gov", "Ethiopia").getContent());
		model.addAttribute("nongovClients",
				this.clientService.findPageByTypeAndCountry(pageRequest, "nongov", "Ethiopia").getContent());

		model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
		model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

		model.addAttribute("Clients", clientService.findImages());
		model.addAttribute("Testimonies", testomonialService.findAll());

		return "client/clientsinethiopia";
	}
}
