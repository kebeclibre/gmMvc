package lcs.prs.goingmobile.controllers;

import java.security.Principal;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import lcs.prs.goingmobile.entities.Journey;
import lcs.prs.goingmobile.entities.Partner;
import lcs.prs.goingmobile.entities.Transaction;
import lcs.prs.goingmobile.services.ClientService;
import lcs.prs.goingmobile.services.GpxService;
import lcs.prs.goingmobile.services.IServiceRepo;
import lcs.prs.goingmobile.services.PartnerService;
import lcs.prs.goingmobile.services.TransactionService;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "user", "omega" })
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private GpxService gpxServ;
	
	@Autowired
	private TransactionService transServ;
	
	@Autowired
	private PartnerService partnerService;
	


	public ClientService getClientService() {
		return clientService;
	}


	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}


	public GpxService getGpxServ() {
		return gpxServ;
	}


	public void setGpxServ(GpxService gpxServ) {
		this.gpxServ = gpxServ;
	}


	public PartnerService getPartnerService() {
		return partnerService;
	}


	public void setPartnerService(PartnerService partnerService) {
		this.partnerService = partnerService;
	}


	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLogin(@ModelAttribute("user") Client user, Model model, Principal principal) {

		
		if (null != principal) {
			User activeUser = (User) ((Authentication) principal).getPrincipal();
			user = clientService.fetchJoinAll(activeUser.getUsername());
	
			GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_CLIENT");
			
			if (!activeUser.getAuthorities().contains(ga)) {
				Partner part = partnerService.fetchAll(activeUser.getUsername());
				if (null == part) { // Just Debugging if DB doesn't contain a partner
					part = new Partner();
				}
				model.addAttribute("user", part);
				model.addAttribute("omega", "Commerçant");
				//logger.info("USER ================>"+part.toString());
			//	logger.info("ADRESSES ================>"+user.getAddresseses().toString());
				logger.info("AUTH ================>"+activeUser.getAuthorities());
				return "redirect:/partner/offers";
			}
			
			if (null == user) {user = new Client();} // again, just debugging
			model.addAttribute("user", user);
			logger.info("USER ================>"+user.toString());
			logger.info("ADRESSES ================>"+user.getAddresseses().toString());
			logger.info("AUTH ================>"+activeUser.getAuthorities());
			

			logger.info("Journey ================>"+user.getJourneyses());
		}
		// httpSess.setAttribute("user", user);

		model.addAttribute("pageTitle", "GoingMobile : Regardez vos trajets");
		

		return "redirect:/user/journeys";
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
		
			Set<Journey> toAddList = gpxServ.processGpx(multipartFile, journeyName);
	
			clientService.update(client,toAddList);
			
			Client updated = clientService.fetchJoinAll(client.getUsername());
			model.addAttribute("user", updated);
			model.addAttribute("uploaded", "Votre GPX et ses trajets ont été ajoutés avec succès");
			
		return "journeys";
	}
	
	public TransactionService getTransServ() {
		return transServ;
	}


	public void setTransServ(TransactionService transServ) {
		this.transServ = transServ;
	}


	@RequestMapping("/transactions")
	public String seeTransactions(Model model, @ModelAttribute("user") Client user) {
		
		Set<Transaction> transactions = transServ.fetchJoinByClientId(user.getId());
		model.addAttribute("transactions", transactions);
		
		return "transactions";
	}
}
