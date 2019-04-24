package com.lemonde.web.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lemonde.web.domains.Contact;
import com.lemonde.web.domains.Vaccancy;
import com.lemonde.web.services.EmailService;
import com.lemonde.web.services.VaccancyService;

@Controller
@RequestMapping("/lhd/Vaccancy")
public class VaccancyContoller {
	private final String contactEmail = "contact@lhdconsult.org";
	@Autowired
	private EmailService emailService;
	private int vaccId;
	@Autowired
	private VaccancyService vaccancyService;

	@GetMapping
	public String getEditVaccancy(Model model) {
		model.addAttribute("vaccancys", this.vaccancyService.findAll());
		return "vaccancy";
	}

	@GetMapping("apply")
	public String getApplyVaccancy(String appId, Model model) {
		vaccId = Integer.parseInt(appId);

		model.addAttribute("newContact", new Contact());
		return "applyVaccancy";
	}

	@PostMapping
	public String saveContact(@Valid @ModelAttribute("newContact") Contact contact,
			@RequestParam("img") MultipartFile file, Model Model, Errors error) {
		if (error.hasErrors()) {
			return "contact";
		}
		String filePath="";
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

				filePath = absPath + "/" + name + "/";
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

			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}
		}
		Vaccancy vac = vaccancyService.findById(vaccId);
		emailService.sendMesssageWithAttachment(contactEmail, contactEmail, contact.getEmail() + " Applyed on" + vac.getTitle(), contact.getMessage(), filePath);
		emailService.SendSimpleMessage(contact.getEmail(), contactEmail, "Thanks", "We will contact u");
		return "redirect:/lhd/Home";
	}

}
