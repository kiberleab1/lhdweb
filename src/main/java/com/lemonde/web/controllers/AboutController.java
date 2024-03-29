package com.lemonde.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemonde.web.domains.OtherTexts;
import com.lemonde.web.domains.TeamMembers;
import com.lemonde.web.domains.Testimonies;
import com.lemonde.web.services.ClientService;
import com.lemonde.web.services.ExperianceService;
import com.lemonde.web.services.OtherTextsService;
import com.lemonde.web.services.ServicesService;
import com.lemonde.web.services.TeamMembersService;
import com.lemonde.web.services.TestimonialService;

@Controller
@RequestMapping("/lhd/about")
public class AboutController {

	private ClientService clientService;
	private TestimonialService testomonialService;
	private OtherTextsService otherTextsService;
	private ServicesService servicesService;
	private TeamMembersService teamMembersService;

	public AboutController(ExperianceService experianceService, TestimonialService testomonialService,
			ClientService clientService, OtherTextsService otherTextsService, ServicesService servicesService,
			TeamMembersService teamMembersService) {
		this.clientService = clientService;
		this.otherTextsService = otherTextsService;
		this.testomonialService = testomonialService;
		this.servicesService = servicesService;
		this.teamMembersService = teamMembersService;
	}

	@GetMapping
	public String getAbout(Model model) {
		try {

			model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
			model.addAttribute("Service", otherTextsService.findSingleByPage("Service"));
			model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));
			model.addAttribute("OperationalCapacity", otherTextsService.findSingleByPage("operational"));

			model.addAttribute("VisionText", otherTextsService.findSingleByPage("vision"));
			model.addAttribute("MisionText", otherTextsService.findSingleByPage("mision"));
			model.addAttribute("FirmText", otherTextsService.findSingleByPage("firm"));
			model.addAttribute("TeamText", otherTextsService.findSingleByPage("teamText"));

			model.addAttribute("Objectives", otherTextsService.findByType("aboutPoint"));

			model.addAttribute("Clients", clientService.findImages());
			model.addAttribute("Testimonies", testomonialService.findAll());
			model.addAttribute("services", servicesService.findAll());
			model.addAttribute("ourTeam", teamMembersService.findAll());

			model.addAttribute("newTeamMember", new TeamMembers());
			model.addAttribute("newTestemony", new Testimonies());
			model.addAttribute("newObjectives", new OtherTexts());
			return "client/about";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/error";
		}
	}
}
