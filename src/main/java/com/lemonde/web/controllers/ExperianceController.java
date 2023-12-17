package com.lemonde.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lemonde.web.domains.Expirence;
import com.lemonde.web.services.ClientService;
import com.lemonde.web.services.ExperianceService;
import com.lemonde.web.services.OtherTextsService;
import com.lemonde.web.services.ResearchService;
import com.lemonde.web.services.ServicesService;
import com.lemonde.web.services.TestimonialService;

@Controller
@RequestMapping(path = "/lhd/experiance")
public class ExperianceController {
	private ExperianceService experianceService;
	private ClientService clientService;
	private TestimonialService testomonialService;
	private OtherTextsService otherTextsService;
	private ResearchService researchService;

	@Autowired
	public ExperianceController(ExperianceService experianceService, TestimonialService testomonialService,
			ClientService clientService, OtherTextsService otherTextsService, ResearchService researchService) {
		this.experianceService = experianceService;
		this.clientService = clientService;
		this.otherTextsService = otherTextsService;
		this.testomonialService = testomonialService;
		this.researchService = researchService;
	}

	@GetMapping
	public String recentTacos(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "type", defaultValue = "health") String type, Model model) {

		PageRequest pageRequest = PageRequest.of(page, size);

		model.addAttribute("pageCounter", (this.researchService.count() + size) / size);
		model.addAttribute("health", this.researchService.findByType(pageRequest, "health"));
		model.addAttribute("water", this.researchService.findByType(pageRequest, "water"));
		model.addAttribute("hiv", this.researchService.findByType(pageRequest, "hiv"));
		model.addAttribute("food", this.researchService.findByType(pageRequest, "food"));

		model.addAttribute("stratExperiance", this.experianceService.findByPage(pageRequest, "strat").getContent());
		model.addAttribute("trainExperiance", this.experianceService.findByPage(pageRequest, "train").getContent());
		model.addAttribute("assistExperiance", this.experianceService.findByPage(pageRequest, "assist").getContent());
		model.addAttribute("proposalExperiance",
				this.experianceService.findByPage(pageRequest, "propsal").getContent());

		model.addAttribute("expText", otherTextsService.findSingleByPage("experiance"));

		model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

		model.addAttribute("Clients", clientService.findImages());
		model.addAttribute("Testimonies", testomonialService.findAll());

		return "client/experiance";

	}
}
