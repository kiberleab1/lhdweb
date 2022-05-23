package com.lemonde.web.controllers;

import javax.validation.Valid;

import com.lemonde.web.domains.Expirence;
import com.lemonde.web.domains.Research;
import com.lemonde.web.services.ClientService;
import com.lemonde.web.services.ExperianceService;
import com.lemonde.web.services.OtherTextsService;
import com.lemonde.web.services.ResearchService;
import com.lemonde.web.services.TestimonialService;

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

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/Admin/editExperiance")
public class EditExperianceController {

	private ExperianceService experianceService;
	private ClientService clientService;
	private TestimonialService testomonialService;
	private OtherTextsService otherTextsService;
	private ResearchService researchService;

	@Autowired
	public EditExperianceController(ExperianceService experianceService, TestimonialService testomonialService,
			ClientService clientService, OtherTextsService otherTextsService,ResearchService researchService) {
		this.experianceService = experianceService;
		this.clientService = clientService;
		this.otherTextsService = otherTextsService;
		this.testomonialService = testomonialService;
		this.researchService=researchService;
	}

	@GetMapping
	public String recentTacos(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "1000") int size,
			@RequestParam(name = "type", defaultValue = "research") String type, Model model) {

		PageRequest pageRequest = PageRequest.of(page, size);
		model.addAttribute("stratExperiance", this.experianceService.findByPage(pageRequest, "strat").getContent());
		model.addAttribute("trainExperiance", this.experianceService.findByPage(pageRequest, "train").getContent());
		model.addAttribute("assistExperiance", this.experianceService.findByPage(pageRequest, "assist").getContent());
		model.addAttribute("proposalExperiance",
				this.experianceService.findByPage(pageRequest, "propsal").getContent());
	
		model.addAttribute("health", this.researchService.findByType(pageRequest,"health"));
		model.addAttribute("water", this.researchService.findByType(pageRequest,"water"));
		model.addAttribute("hiv", this.researchService.findByType(pageRequest,"hiv"));
		model.addAttribute("food", this.researchService.findByType(pageRequest,"food"));
		
		model.addAttribute("expText", otherTextsService.findSingleByPage("experiance"));
		model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));
		
		model.addAttribute("Clients", clientService.findImages());
		model.addAttribute("Testimonies", testomonialService.findAll());
		
		model.addAttribute("newExperiance", new Expirence());
		model.addAttribute("newResearch", new Research());

		return "editExperiance";

	}
	@PostMapping("/research")
	public String addRsearch(@Valid @ModelAttribute("newResearch") Research experiance, BindingResult bindingResult,
			 Errors errors, Model model,@RequestParam(name = "page", defaultValue = "0") int page,
				@RequestParam(name = "size", defaultValue = "1000") int size,
				@RequestParam(name = "type", defaultValue = "research") String type) {
		if (errors.hasErrors()) {
			PageRequest pageRequest = PageRequest.of(page, size);
			model.addAttribute("stratExperiance", this.experianceService.findByPage(pageRequest, "strat").getContent());
			
			model.addAttribute("trainExperiance", this.experianceService.findByPage(pageRequest, "train").getContent());
			model.addAttribute("assistExperiance", this.experianceService.findByPage(pageRequest, "assist").getContent());
			model.addAttribute("proposalExperiance",
					this.experianceService.findByPage(pageRequest, "propsal").getContent());
		
			model.addAttribute("expText", otherTextsService.findSingleByPage("experiance"));
			model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

			model.addAttribute("Clients", clientService.findImages());
			model.addAttribute("Testimonies", testomonialService.findAll());
			model.addAttribute("newExperiance", new Expirence());
			return "editExperiance";
		}
		
		this.researchService.insert(experiance);
		return "redirect:/Admin/editExperiance";
	}
	@PostMapping
	public String addExperiance(@Valid @ModelAttribute("newExperiance") Expirence experiance, BindingResult bindingResult,
			 Errors errors, Model model,@RequestParam(name = "page", defaultValue = "0") int page,
				@RequestParam(name = "size", defaultValue = "1000") int size,
				@RequestParam(name = "type", defaultValue = "research") String type) {
		experiance.setFirm("lhd");
		if (errors.hasErrors() && errors.getErrorCount()!=1) {
			log.debug("In the Error Section");
			log.debug(experiance.getClient()+" "+experiance.getDetailText()+" "+experiance.getFirm()+" "+experiance.getType());
			
			PageRequest pageRequest = PageRequest.of(page, size);
			model.addAttribute("stratExperiance", this.experianceService.findByPage(pageRequest, "strat").getContent());
			model.addAttribute("trainExperiance", this.experianceService.findByPage(pageRequest, "train").getContent());
			model.addAttribute("assistExperiance", this.experianceService.findByPage(pageRequest, "assist").getContent());
			model.addAttribute("proposalExperiance",
					this.experianceService.findByPage(pageRequest, "propsal").getContent());

			model.addAttribute("expText", otherTextsService.findSingleByPage("experiance"));
			model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

			model.addAttribute("Clients", clientService.findImages());
			model.addAttribute("Testimonies", testomonialService.findAll());
			model.addAttribute("newResearch", new Research());
			return "editExperiance";
		}
		log.debug(experiance.getClient()+" "+experiance.getDetailText()+" "+experiance.getFirm()+" "+experiance.getType());
		this.experianceService.save(experiance);
		return "redirect:/Admin/editExperiance";
	}
	@PostMapping("/delete")
	public String deleteClient(String experianceId) {
		int id = Integer.parseInt(experianceId);
		this.experianceService.delete((long)id);
		return "redirect:/Admin/editExperiance";
	}
	@PostMapping("/deleteResearch")
	public String deleteResearch(String experianceId) {
		int id = Integer.parseInt(experianceId);
		this.researchService.deleteById((long)id);
		return "redirect:/Admin/editExperiance";
	}

	@PostMapping("/editResearch")
	public String editResearch(String experianceId, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "1000") int size,
			@RequestParam(name = "type", defaultValue = "research") String type, Model model) {
		
		int id=Integer.parseInt(experianceId);
		
		PageRequest pageRequest = PageRequest.of(page, size);
		model.addAttribute("stratExperiance", this.experianceService.findByPage(pageRequest, "strat").getContent());
		
		model.addAttribute("trainExperiance", this.experianceService.findByPage(pageRequest, "train").getContent());
		model.addAttribute("assistExperiance", this.experianceService.findByPage(pageRequest, "assist").getContent());
		model.addAttribute("proposalExperiance",
				this.experianceService.findByPage(pageRequest, "propsal").getContent());

		model.addAttribute("expText", otherTextsService.findSingleByPage("experiance"));
		model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

		model.addAttribute("Clients", clientService.findImages());
		model.addAttribute("Testimonies", testomonialService.findAll());
		model.addAttribute("newExperiance", new Expirence());
		model.addAttribute("newResearch", this.researchService.findById((long)id));
		return  "editExperiance";

	}
	@PostMapping("/edit")
	public String editClient(String experianceId, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "1000") int size,
			@RequestParam(name = "type", defaultValue = "research") String type, Model model) {
		
		int id=Integer.parseInt(experianceId);
		
		PageRequest pageRequest = PageRequest.of(page, size);
		model.addAttribute("stratExperiance", this.experianceService.findByPage(pageRequest, "strat").getContent());
		
		model.addAttribute("trainExperiance", this.experianceService.findByPage(pageRequest, "train").getContent());
		model.addAttribute("assistExperiance", this.experianceService.findByPage(pageRequest, "assist").getContent());
		model.addAttribute("proposalExperiance",
				this.experianceService.findByPage(pageRequest, "propsal").getContent());

		model.addAttribute("expText", otherTextsService.findSingleByPage("experiance"));
		model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

		model.addAttribute("Clients", clientService.findImages());
		model.addAttribute("Testimonies", testomonialService.findAll());
		model.addAttribute("newExperiance", this.experianceService.findById((long)id));
		model.addAttribute("newResearch", new Research());
		return  "editExperiance";

	}
}
