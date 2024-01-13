package com.lemonde.web.controllers;

import javax.validation.Valid;

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
import com.lemonde.web.domains.OtherTexts;
import com.lemonde.web.services.ClientService;
import com.lemonde.web.services.ExperianceService;
import com.lemonde.web.services.FileUpload;
import com.lemonde.web.services.OtherTextsService;
import com.lemonde.web.services.TestimonialService;

@Controller
@RequestMapping("/Admin/editClient")
public class EditClientController {
	private ClientService clientService;
	private TestimonialService testomonialService;
	private OtherTextsService otherTextsService;
	private FileUpload fileUpload;

	public EditClientController(ExperianceService experianceService, TestimonialService testomonialService,
			ClientService clientService, OtherTextsService otherTextsService, FileUpload fileUploadService) {
		this.clientService = clientService;
		this.otherTextsService = otherTextsService;
		this.testomonialService = testomonialService;
		this.fileUpload = fileUploadService;
	}

	@GetMapping
	public String getClient(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "1000") int size,
			@RequestParam(name = "type", defaultValue = "un") String type, Model model) {
		try {
			PageRequest pageRequest = PageRequest.of(page, size);
			model.addAttribute("unClients", this.clientService.findByPage(pageRequest, type).getContent());
			model.addAttribute("govClients", this.clientService.findByPage(pageRequest, "gov").getContent());
			model.addAttribute("nongovClients", this.clientService.findByPage(pageRequest, "nongov").getContent());
			model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));

			model.addAttribute("Clients", clientService.findImages());
			model.addAttribute("newClient", new Clients());
			// ArrayList<Clients> listClients = new ArrayList<>();

			return "admin/editClient";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping
	public String addClient(@Valid @ModelAttribute("newClient") Clients client, BindingResult bindingResult,
			@RequestParam("img") MultipartFile file, Errors errors, Model model) {
		try {
			if (errors.hasErrors()) {
				PageRequest pageRequest = PageRequest.of(0, 1000);
				model.addAttribute("unClients", this.clientService.findByPage(pageRequest, "un").getContent());
				model.addAttribute("govClients", this.clientService.findByPage(pageRequest, "gov").getContent());
				model.addAttribute("nongovClients", this.clientService.findByPage(pageRequest, "nongov").getContent());
				model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
				model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));
				model.addAttribute("Clients", clientService.findImages());
				return "admin/editClient";
			}

			if (!file.getOriginalFilename().isEmpty()) {

				try {

					String imageURL = this.fileUpload.uploadFile(file);
					client.setImgPath(imageURL);

				} catch (Exception e) {
					System.out.println("error" + e.getMessage());
				}
			}
			this.clientService.save(client);
			return "redirect:/Admin/editClient";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/delete")
	public String deleteClient(String clientId) {
		try {
			int id = Integer.parseInt(clientId);
			this.clientService.delete(id);
			return "redirect:/Admin/editClient";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/edit")
	public String editClient(String clientId, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "1000") int size,
			@RequestParam(name = "type", defaultValue = "un") String type, Model model) {
		try {
			PageRequest pageRequest = PageRequest.of(page, size);
			model.addAttribute("unClients", this.clientService.findByPage(pageRequest, type).getContent());
			model.addAttribute("govClients", this.clientService.findByPage(pageRequest, "gov").getContent());
			model.addAttribute("nongovClients", this.clientService.findByPage(pageRequest, "nongov").getContent());
			model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
			model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));

			model.addAttribute("Clients", clientService.findImages());
			model.addAttribute("Testimonies", testomonialService.findAll());
			int id = Integer.parseInt(clientId);

			model.addAttribute("newClient", this.clientService.findById((long) id));
			return "admin/editClient";
		} catch (Exception e) {
			return "redirect:/error";
		}

	}

	@PostMapping("/saveClientText")
	public String saveClientText(@Valid @ModelAttribute("clientText") OtherTexts clientText,
			BindingResult bindingResult,
			Errors errors, Model model) {
		try {
			if (errors.hasErrors()) {
				PageRequest pageRequest = PageRequest.of(0, 1000);
				model.addAttribute("unClients", this.clientService.findByPage(pageRequest, "un").getContent());
				model.addAttribute("govClients", this.clientService.findByPage(pageRequest, "gov").getContent());
				model.addAttribute("nongovClients", this.clientService.findByPage(pageRequest, "nongov").getContent());
				model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));

				model.addAttribute("Clients", clientService.findImages());

				return "admin/editClient";
			}

			this.otherTextsService.save(clientText);
			return "redirect:/Admin/editClient";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

}
