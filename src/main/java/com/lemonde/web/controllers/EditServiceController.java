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
import com.lemonde.web.domains.Services;
import com.lemonde.web.services.ClientService;
import com.lemonde.web.services.ExperianceService;
import com.lemonde.web.services.OtherTextsService;
import com.lemonde.web.services.ServicesService;
import com.lemonde.web.services.TestimonialService;


@Controller
@RequestMapping("Admin/editService")
public class EditServiceController {

	private OtherTextsService otherTextsService;
	private ClientService clientService;
	private TestimonialService testomonialService;
	private ServicesService servicesService;

	@Autowired
	public EditServiceController(OtherTextsService otherTextsService, ClientService clientService,
			TestimonialService testomonialService, ServicesService servicesService) {
		this.otherTextsService = otherTextsService;
		this.clientService = clientService;
		this.testomonialService = testomonialService;
		this.servicesService = servicesService;
	}

	@GetMapping
	public String recentTacos(Model model) {
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
		model.addAttribute("newService", new Services());

		return "editServices";

	}

	@PostMapping
	public String addService(@Valid @ModelAttribute("newService") Services service, BindingResult bindingResult,
			@RequestParam("img") MultipartFile file, Errors errors, Model model) {
		if (errors.hasErrors()) {
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
			return "editServices";
		}

		if (!file.getOriginalFilename().isEmpty()) {

			try {
				int i = 1;
				String filename = file.getOriginalFilename();
				int pos = filename.lastIndexOf(".");
				String dir = "src/main/resources/static/img";
				File directory = new File(dir);
				if (!directory.exists()) {
					directory.mkdir();
				}
				String name = "";
				if (pos > 0) {
					name += filename.substring(0, pos);
				}
				String absPath = directory.getAbsolutePath();
				// absPath.replaceAll("\\","/");
				
				String filePath = absPath + "/" + name + "/";
				File destFolder = new File(filePath);

				while (destFolder.exists()) {
					filePath = directory.getAbsolutePath() + "/" + name + "(" + i + ")/";
					destFolder = new File(filePath);
					i++;
				}

				destFolder.mkdir();

				filePath += filename;
				
				File dest = new File(filePath);

				file.transferTo(dest);

				service.setSvgImgPath("/img/" + name + "/" + name + ".svg");

			} catch (FileNotFoundException e) {
				
			} catch (IOException e) {
				
			}
		}
		this.servicesService.save(service);
		return "redirect:/Admin/editService";
	}

	@PostMapping("/delete")
	public String deleteClient(String serviceId) {
		int id = Integer.parseInt(serviceId);
		this.servicesService.deleteById(id);
		return "redirect:/Admin/editService";
	}

	@PostMapping("/edit")
	public String editClient(String serviceId, Model model) {

		int id = Integer.parseInt(serviceId);
		
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
		model.addAttribute("newService", this.servicesService.findById(id));

		return "editServices";

	}

}
