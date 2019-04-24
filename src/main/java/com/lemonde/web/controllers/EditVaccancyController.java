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

import com.lemonde.web.domains.Vaccancy;
import com.lemonde.web.services.VaccancyService;

@Controller
@RequestMapping("/Admin/editVaccancy")
public class EditVaccancyController {
	@Autowired
	private VaccancyService vaccancyService;

	@GetMapping
	public String getEditVaccancy(Model model) {
		model.addAttribute("vaccancys", this.vaccancyService.findAll());
		model.addAttribute("newVaccancy", new Vaccancy());
		return "editVaccancy";
	}

	@PostMapping
	public String saveVaccancy(@Valid @ModelAttribute("newVaccancy") Vaccancy vaccancy, Model model, Errors errors) {
		if (errors.hasErrors()) {
			model.addAttribute("vaccancys", this.vaccancyService.findAll());
			return "editVaccancy";
		}
		this.vaccancyService.save(vaccancy);
		return "redirect:/Admin/editVaccancy";
	}

	@PostMapping("delete")
	public String deleteVaccancy(String clientId) {
		long Id = (long) Integer.parseInt(clientId);
		this.vaccancyService.delete(Id);
		return "redirect:/Admin/editVaccancy";
	}

	@PostMapping("edit")
	public String editVaccancy(String clientId,Model model) {
		long Id=(long)Integer.parseInt(clientId);
		model.addAttribute("vaccancys", this.vaccancyService.findAll());
		model.addAttribute("newVaccancy",this.vaccancyService.findById(Id));
		return "editVaccancy";
	}

}
