package com.lemonde.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemonde.web.services.ClientService;
import com.lemonde.web.services.OtherTextsService;
import com.lemonde.web.services.ServicesService;
import com.lemonde.web.services.TestimonialService;
import com.lemonde.web.services.VaccancyService;

@Controller
@RequestMapping("/lhd/Home")
public class HomeController {

	private OtherTextsService otherTextsService;
	private ClientService clientService;
	private TestimonialService testomonialService;
	private ServicesService servicesService;
	@Autowired
	private VaccancyService vaccancyService;

	public HomeController(OtherTextsService otherTextsService, ClientService clientService,
			TestimonialService testomonialService, ServicesService servicesService) {
		this.otherTextsService = otherTextsService;
		this.clientService = clientService;
		this.testomonialService = testomonialService;
		this.servicesService = servicesService;
	}

	@GetMapping
	public String getHome(Model model) {
		try {
			model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
			model.addAttribute("Service", otherTextsService.findSingleByPage("Service"));
			model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

			model.addAttribute("VisionText", otherTextsService.findSingleByPage("vision"));
			model.addAttribute("MisionText", otherTextsService.findSingleByPage("mision"));
			model.addAttribute("Objectives", otherTextsService.findByType("aboutPoint"));

			model.addAttribute("AboutPoints", otherTextsService.findByType("aboutPoint"));
			model.addAttribute("Clients", clientService.findImages());
			model.addAttribute("Testimonies", testomonialService.findAll());
			model.addAttribute("services", servicesService.findAll());
			model.addAttribute("vacc", this.vaccancyService.count());
			return "client/home";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}
}
