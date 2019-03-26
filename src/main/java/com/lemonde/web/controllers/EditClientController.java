package com.lemonde.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/Admin/editClient")
public class EditClientController {
	private ExperianceService experianceService;
	private ClientService clientService;
	private TestimonialService testomonialService;
	private OtherTextsService otherTextsService;

	@Autowired
	public EditClientController(ExperianceService experianceService, TestimonialService testomonialService,
			ClientService clientService, OtherTextsService otherTextsService) {
		this.experianceService = experianceService;
		this.clientService = clientService;
		this.otherTextsService = otherTextsService;
		this.testomonialService = testomonialService;
	}

	@GetMapping
	public String getClient(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "type", defaultValue = "un") String type, Model model) {
		PageRequest pageRequest = PageRequest.of(page, size);
		model.addAttribute("unClients", this.clientService.findByPage(pageRequest, type).getContent());
		model.addAttribute("govClients", this.clientService.findByPage(pageRequest, "train").getContent());
		model.addAttribute("nongovClients", this.clientService.findByPage(pageRequest, "assist").getContent());
		model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
		model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

		model.addAttribute("Clients", clientService.findAll());
		model.addAttribute("Testimonies", testomonialService.findAll());
		model.addAttribute("newClient", new Clients());
		return "editClient";
	}

	@PostMapping("/delete")
	public String deleteClient(String clientId) {
		int id = Integer.parseInt(clientId);
		this.clientService.delete(id);
		return "redirect: /Admin/editClient";
	}

	@PostMapping("/edit")
	public String editClient(String clientId) {
		log.info("Hello" + clientId);
		return "redirect:/Hotel/Requests";
	}

	@PostMapping
	public String addClient(@Valid @ModelAttribute("newClient") Clients client, @RequestParam("img") MultipartFile file,
			Errors errors, Model model) {
		if (errors.hasErrors()) {
			PageRequest pageRequest = PageRequest.of(0, 10);
			model.addAttribute("researchExperiance",
					this.experianceService.findByPage(pageRequest, "research").getContent());
			model.addAttribute("trainExperiance", this.experianceService.findByPage(pageRequest, "train").getContent());
			model.addAttribute("assistExperiance",
					this.experianceService.findByPage(pageRequest, "assist").getContent());
			model.addAttribute("proposalExperiance",
					this.experianceService.findByPage(pageRequest, "propsal").getContent());

			model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
			model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

			model.addAttribute("Clients", clientService.findAll());
			model.addAttribute("Testimonies", testomonialService.findAll());
			return "editClient";
		}
		log.info("ad" + file.getOriginalFilename() + "ff");
		if (!file.getOriginalFilename().isEmpty()) {
			log.info("sdsdsdsd");
		}
		this.clientService.save(client);
		return "redirect:/Hotel/sss";
	}

}
