package com.lemonde.web.controllers;

import javax.validation.Valid;

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

import com.lemonde.web.domains.Services;
import com.lemonde.web.services.ClientService;
import com.lemonde.web.services.FileUpload;
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
	private FileUpload fileUpload;

	public EditServiceController(OtherTextsService otherTextsService, ClientService clientService,
			TestimonialService testomonialService, ServicesService servicesService, FileUpload fileUpload) {
		this.otherTextsService = otherTextsService;
		this.clientService = clientService;
		this.testomonialService = testomonialService;
		this.servicesService = servicesService;
		this.fileUpload = fileUpload;
	}

	@GetMapping
	public String recentTacos(Model model) {
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
			model.addAttribute("newService", new Services());

			return "admin/editServices";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping
	public String addService(@Valid @ModelAttribute("newService") Services service, BindingResult bindingResult,
			@RequestParam("img") MultipartFile file, Errors errors, Model model) {
		try {
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
				return "admin/editServices";
			}

			if (!file.getOriginalFilename().isEmpty()) {

				try {

					String name = this.fileUpload.uploadFile(file);
					service.setSvgImgPath(name);

				} catch (Exception e) {
					System.out.println("error" + e.getMessage());
					throw new Exception(e.getMessage());
				}
			}
			this.servicesService.save(service);
			return "redirect:/Admin/editService";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/delete")
	public String deleteClient(String serviceId) {
		try {
			int id = Integer.parseInt(serviceId);
			this.servicesService.deleteById(id);
			return "redirect:/Admin/editService";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/edit")
	public String editClient(String serviceId, Model model) {
		try {
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

			return "admin/editServices";
		} catch (Exception e) {
			return "redirect:/error";
		}

	}

}
