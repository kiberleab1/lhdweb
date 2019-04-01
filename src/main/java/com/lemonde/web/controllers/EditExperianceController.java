package com.lemonde.web.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lemonde.web.domains.Clients;
import com.lemonde.web.domains.Expirence;
import com.lemonde.web.services.ClientService;
import com.lemonde.web.services.ExperianceService;
import com.lemonde.web.services.OtherTextsService;
import com.lemonde.web.services.TestimonialService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Admin/editExperiance")
public class EditExperianceController {

	private ExperianceService experianceService;
	private ClientService clientService;
	private TestimonialService testomonialService;
	private OtherTextsService otherTextsService;

	@Autowired
	public EditExperianceController(ExperianceService experianceService, TestimonialService testomonialService,
			ClientService clientService, OtherTextsService otherTextsService) {
		this.experianceService = experianceService;
		this.clientService = clientService;
		this.otherTextsService = otherTextsService;
		this.testomonialService = testomonialService;
	}

	@GetMapping
	public String recentTacos(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "type", defaultValue = "research") String type, Model model) {

		PageRequest pageRequest = PageRequest.of(page, size);
		model.addAttribute("researchExperiance", this.experianceService.findByPage(pageRequest, type).getContent());
		model.addAttribute("trainExperiance", this.experianceService.findByPage(pageRequest, "train").getContent());
		model.addAttribute("assistExperiance", this.experianceService.findByPage(pageRequest, "assist").getContent());
		model.addAttribute("proposalExperiance",
				this.experianceService.findByPage(pageRequest, "propsal").getContent());

		model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
		model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

		model.addAttribute("Clients", clientService.findImages());
		model.addAttribute("Testimonies", testomonialService.findAll());
		model.addAttribute("newExperiance", new Expirence());

		return "editExperiance";

	}
	@PostMapping
	public String addExperiance(@Valid @ModelAttribute("newExperiance") Expirence experiance, BindingResult bindingResult,
			 Errors errors, Model model,@RequestParam(name = "page", defaultValue = "0") int page,
				@RequestParam(name = "size", defaultValue = "10") int size,
				@RequestParam(name = "type", defaultValue = "research") String type) {
		if (errors.hasErrors()) {
			PageRequest pageRequest = PageRequest.of(page, size);
			model.addAttribute("researchExperiance", this.experianceService.findByPage(pageRequest, type).getContent());
			model.addAttribute("trainExperiance", this.experianceService.findByPage(pageRequest, "train").getContent());
			model.addAttribute("assistExperiance", this.experianceService.findByPage(pageRequest, "assist").getContent());
			model.addAttribute("proposalExperiance",
					this.experianceService.findByPage(pageRequest, "propsal").getContent());

			model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
			model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

			model.addAttribute("Clients", clientService.findImages());
			model.addAttribute("Testimonies", testomonialService.findAll());
			return "editExperiance";
		}
		
		this.experianceService.save(experiance);
		return "redirect:/Admin/editExperiance";
	}
	@PostMapping("/delete")
	public String deleteClient(String experianceId) {
		int id = Integer.parseInt(experianceId);
		this.experianceService.delete((long)id);
		return "redirect:/Admin/editExperiance";
	}


	@PostMapping("/edit")
	public String editClient(String experianceId, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "type", defaultValue = "research") String type, Model model) {
		
		int id=Integer.parseInt(experianceId);
		
		PageRequest pageRequest = PageRequest.of(page, size);
		
		model.addAttribute("researchExperiance", this.experianceService.findByPage(pageRequest, type).getContent());
		model.addAttribute("trainExperiance", this.experianceService.findByPage(pageRequest, "train").getContent());
		model.addAttribute("assistExperiance", this.experianceService.findByPage(pageRequest, "assist").getContent());
		model.addAttribute("proposalExperiance",
				this.experianceService.findByPage(pageRequest, "propsal").getContent());

		model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
		model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

		model.addAttribute("Clients", clientService.findImages());
		model.addAttribute("Testimonies", testomonialService.findAll());
		model.addAttribute("newExperiance", this.experianceService.findById((long)id));
		return  "editExperiance";

	}
}
