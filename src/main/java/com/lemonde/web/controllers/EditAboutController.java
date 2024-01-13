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

import com.lemonde.web.domains.OtherTexts;
import com.lemonde.web.domains.TeamMembers;
import com.lemonde.web.domains.Testimonies;
import com.lemonde.web.services.ClientService;
import com.lemonde.web.services.ExperianceService;
import com.lemonde.web.services.FileUpload;
import com.lemonde.web.services.OtherTextsService;
import com.lemonde.web.services.ServicesService;
import com.lemonde.web.services.TeamMembersService;
import com.lemonde.web.services.TestimonialService;

@Controller
@RequestMapping("/Admin/editAbout")
public class EditAboutController {

	private ClientService clientService;
	private TestimonialService testomonialService;
	private OtherTextsService otherTextsService;
	private ServicesService servicesService;
	private TeamMembersService teamMembersService;
	private FileUpload fileUpload;

	public EditAboutController(ExperianceService experianceService, TestimonialService testomonialService,
			ClientService clientService, OtherTextsService otherTextsService, ServicesService servicesService,
			TeamMembersService teamMembersService,
			FileUpload fileUploadService) {
		this.clientService = clientService;
		this.otherTextsService = otherTextsService;
		this.testomonialService = testomonialService;
		this.servicesService = servicesService;
		this.teamMembersService = teamMembersService;
		this.fileUpload = fileUploadService;
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
			model.addAttribute("FirmText", otherTextsService.findSingleByPage("teamText"));
			model.addAttribute("TeamText", otherTextsService.findSingleByPage("teamText"));

			model.addAttribute("Objectives", otherTextsService.findByType("aboutPoint"));

			model.addAttribute("Clients", clientService.findImages());
			model.addAttribute("Testimonies", testomonialService.findAll());
			model.addAttribute("services", servicesService.findAll());
			model.addAttribute("ourTeam", teamMembersService.findAll());

			model.addAttribute("newTeamMember", new TeamMembers());
			model.addAttribute("newTestemony", new Testimonies());
			model.addAttribute("newObjectives", new OtherTexts());
			System.out.println(model.toString());
			return "admin/editAbout";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/deleteObjective")
	public String deleteObjective(String objId) {
		try {

			int id = Integer.parseInt(objId);
			this.otherTextsService.deleteById((long) id);
			return "redirect:/Admin/editAbout";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/deleteTestmony")
	public String deleteTestimony(String testomonyId) {
		try {

			int id = Integer.parseInt(testomonyId);
			this.testomonialService.deleteById((long) id);
			return "redirect:/Admin/editAbout";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/editTestmony")
	public String editTestimony(String testomonyId, Model model) {
		try {

			int id = Integer.parseInt(testomonyId);
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

			model.addAttribute("newObjectives", new OtherTexts());

			model.addAttribute("newTestemony", this.testomonialService.findById((long) id));
			return "admin/editAbout";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/saveTestimony")
	public String saveTestimony(@Valid @ModelAttribute("newTestemony") Testimonies testimonies,
			BindingResult bindingResult,
			Errors errors, Model model) {
		try {

			if (errors.hasErrors()) {
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

				model.addAttribute("newObjectives", new OtherTexts());

				model.addAttribute("newTestemony", new Testimonies());

				return "admin/editAbout";
			}

			this.testomonialService.save(testimonies);
			return "redirect:/admin/editABout";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/addObjectives")
	public String saveObjectivesText(@Valid @ModelAttribute("newObjectives") OtherTexts clientText,
			BindingResult bindingResult,
			Errors errors, Model model) {
		try {

			if (errors.hasErrors()) {

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
				// model.addAttribute("newObjectives", new OtherTexts());

				return "admin/editAbout";

			}
			this.otherTextsService.save(clientText);
			return "redirect:/Admin/editAbout";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/saveTestmonialText")
	public String saveClientText(@Valid @ModelAttribute("clientText") OtherTexts clientText,
			BindingResult bindingResult,
			Errors errors, Model model) {
		try {

			if (errors.hasErrors()) {

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

				return "admin/editAbout";

			}

			this.otherTextsService.save(clientText);
			return "redirect:/Admin/editAbout";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/saveOthersText")
	public String saveOperationaCapacity(@Valid @ModelAttribute("OperationalCapacity") OtherTexts clientText,
			BindingResult bindingResult,
			Errors errors, Model model) {
		try {

			if (errors.hasErrors()) {

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
				return "admin/editAbout";

			}

			this.otherTextsService.save(clientText);
			return "redirect:/Admin/editAbout";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/saveTeamMember")
	public String addClient(@Valid @ModelAttribute("newTeamMember") TeamMembers teamMembers,
			BindingResult bindingResult,
			@RequestParam("img") MultipartFile file, Errors errors, Model model) {
		try {

			if (errors.hasErrors()) {

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

				// model.addAttribute("newTeamMember",new TeamMembers());
				model.addAttribute("newTestemony", new Testimonies());
				model.addAttribute("newObjectives", new OtherTexts());
				return "admin/editAbout";
			}

			if (!file.getOriginalFilename().isEmpty()) {
				try {
					String imgPath = this.fileUpload.uploadFile(file);

					teamMembers.setImgPath(imgPath);

				} catch (Exception e) {

				}
			}

			this.teamMembersService.save(teamMembers);
		} catch (Exception e) {
			return "redirect:/error";
		}
		return "redirect:/Admin/editAbout";
	}

	@PostMapping("/deleteTeam")
	public String deleteTeam(String teamMemberId) {
		try {
			int id = Integer.parseInt(teamMemberId);
			this.teamMembersService.deleteById((long) id);
			return "redirect:/Admin/editAbout";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

	@PostMapping("/editTeam")
	public String editTeam(String teamMemberId, Model model) {
		try {
			int id = Integer.parseInt(teamMemberId);

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

			model.addAttribute("newTeamMember", this.teamMembersService.findById(id));
			model.addAttribute("newTestemony", new Testimonies());
			model.addAttribute("newObjectives", new OtherTexts());
			return "admin/editAbout";
		} catch (Exception e) {
			return "redirect:/error";
		}
	}

}
