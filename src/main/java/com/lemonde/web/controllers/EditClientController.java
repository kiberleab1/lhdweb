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
import com.lemonde.web.domains.OtherTexts;
import com.lemonde.web.services.ClientService;
import com.lemonde.web.services.ExperianceService;
import com.lemonde.web.services.OtherTextsService;
import com.lemonde.web.services.TestimonialService;

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
		model.addAttribute("govClients", this.clientService.findByPage(pageRequest, "gov").getContent());
		model.addAttribute("nongovClients", this.clientService.findByPage(pageRequest, "nongov").getContent());
		model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));

		model.addAttribute("Clients", clientService.findImages());
		model.addAttribute("newClient", new Clients());
		return "editClient";
	}
	@PostMapping
	public String addClient(@Valid @ModelAttribute("newClient") Clients client, BindingResult bindingResult,
			@RequestParam("img") MultipartFile file, Errors errors, Model model) {
		if (errors.hasErrors()) {
			PageRequest pageRequest = PageRequest.of(0, 10);
			model.addAttribute("unClients", this.clientService.findByPage(pageRequest, "un").getContent());
			model.addAttribute("govClients", this.clientService.findByPage(pageRequest, "gov").getContent());
			model.addAttribute("nongovClients", this.clientService.findByPage(pageRequest, "nongov").getContent());
			model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
			model.addAttribute("Testimonial", otherTextsService.findSingleByPage("Testimonial"));
			model.addAttribute("Clients", clientService.findImages());
			return "editClient";
		}
		
		if (!file.getOriginalFilename().isEmpty()) {

			
			try {
				int i=1;
				String filename=file.getOriginalFilename();
				int pos = filename.lastIndexOf(".");
	            String dir="src/main/resources/static/img";
	            File directory = new File(dir);
	            if (! directory.exists()){
	                directory.mkdir();
	            }
				String name="";
				if (pos > 0) {
				    name += filename.substring(0, pos);
				}
				String absPath=directory.getAbsolutePath();
			
				String filePath = absPath + "/" + name+"/";
				File destFolder = new File(filePath);
				
				while(destFolder.exists()) {
					filePath=directory.getAbsolutePath()+"/"+ name+"("+i+")/";
					destFolder=new File(filePath);
					i++;
				}
				
				destFolder.mkdir();
				
				
				
				filePath+=filename;
				
				File dest=new File(filePath);
			
				file.transferTo(dest);
				
				client.setImgPath("/img/"+name+"/"+name+".jpg");
				
			}
			catch(FileNotFoundException e) {
				
			}
			catch(IOException e) {
			
			}
		}
		this.clientService.save(client);
		return "redirect:/Admin/editClient";
	}
	@PostMapping("/delete")
	public String deleteClient(String clientId) {
		int id = Integer.parseInt(clientId);
		this.clientService.delete(id);
		return "redirect:/Admin/editClient";
	}

	@PostMapping("/edit")
	public String editClient(String clientId, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "type", defaultValue = "un") String type, Model model) {
		
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
		return "editClient";

	}

	@PostMapping("/saveClientText")
	public String saveClientText(@Valid @ModelAttribute("clientText") OtherTexts clientText, BindingResult bindingResult,
			Errors errors, Model model) {
		if (errors.hasErrors()) {
			PageRequest pageRequest = PageRequest.of(0, 10);
			model.addAttribute("unClients", this.clientService.findByPage(pageRequest, "un").getContent());
			model.addAttribute("govClients", this.clientService.findByPage(pageRequest, "gov").getContent());
			model.addAttribute("nongovClients", this.clientService.findByPage(pageRequest, "nongov").getContent());
			model.addAttribute("clientText", otherTextsService.findSingleByPage("clients"));
			

			model.addAttribute("Clients", clientService.findImages());

			return "editClient";
		}

		this.otherTextsService.save(clientText);
		return "redirect:/Admin/editClient";
	}

	

}
