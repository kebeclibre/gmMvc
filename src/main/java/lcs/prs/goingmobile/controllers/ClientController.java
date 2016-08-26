package lcs.prs.goingmobile.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.services.ClientService;
import lcs.prs.goingmobile.services.IServiceRepo;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "user" })
public class ClientController {

	@Autowired
	private ClientService clientService;

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	public IServiceRepo<Client, Integer> getService() {
		return clientService;
	}

	public void setService(ClientService service) {
		this.clientService = service;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLogin(@ModelAttribute("user") Client user, Model model, Principal principal) {

		
		if (null != principal) {
			User activeUser = (User) ((Authentication) principal).getPrincipal();
			user = clientService.fetchJoinAll(activeUser.getUsername());
			model.addAttribute("user", user);
			logger.info("USER ================>"+user.toString());
			logger.info("ADRESSES ================>"+user.getAddresseses().toString());
		}
		// httpSess.setAttribute("user", user);

		model.addAttribute("pageTitle", "GoingMobile : Regardez vos trajets");
		

		return "journeys";
	}
	

	@ModelAttribute("user")
	public Client getUserObject() {
		return new Client();
	}

	@RequestMapping("/profile")
	public String showClientProfile(Model model) {
		
		model.addAttribute("pageTitle", "GoingMobile : Gérez votre profil");
		return "profile";
	}

	@RequestMapping("/journeys")
	public String showJourneys(Model model) {

		model.addAttribute("pageTitle", "GoingMobile : Regardez vos trajets");
		return "journeys";
	}
	
	@RequestMapping("/addJourney")
	public String addJourneyForm(Model model) {
		return "addJourneyForm";
	}
	
	@RequestMapping("/processUpload")
	public String processUpload(@RequestParam("file") MultipartFile multipartFile, 
			@ModelAttribute("journeyName") String journeyName, 
			Model model,
			@ModelAttribute("user") Client client) {
		
			
			BufferedReader bf = null;
			StringBuffer result = new StringBuffer();
			try {
				bf = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
				
				String st = "";
				while ((st = bf.readLine()) != null) {
					result.append(st);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					bf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			model.addAttribute("uploaded", result.toString());
		
		return "journeys";
	}

}
