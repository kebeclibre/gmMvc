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
import lcs.prs.goingmobile.interfaces.UserInterface;
import lcs.prs.goingmobile.services.interfaces.ClientServiceIFace;
import lcs.prs.goingmobile.services.interfaces.GpxServiceIFace;
import lcs.prs.goingmobile.services.interfaces.IServiceRepo;
import lcs.prs.goingmobile.services.interfaces.PartnerServiceIface;
import lcs.prs.goingmobile.services.interfaces.TransactionServiceIFace;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "user", "omega" })
public class ClientController {

	@Autowired
	private ClientServiceIFace clientService;
	
	@Autowired
	private GpxServiceIFace gpxServ;
	
	@Autowired
	private TransactionServiceIFace transServ;
	
	@Autowired
	private PartnerServiceIface partnerService;
	


	public ClientServiceIFace getClientService() {
		return clientService;
	}


	public void setClientService(ClientServiceIFace clientService) {
		this.clientService = clientService;
	}


	public GpxServiceIFace getGpxServ() {
		return gpxServ;
	}


	public void setGpxServ(GpxServiceIFace gpxServ) {
		this.gpxServ = gpxServ;
	}


	public PartnerServiceIface getPartnerService() {
		return partnerService;
	}


	public void setPartnerService(PartnerServiceIface partnerService) {
		this.partnerService = partnerService;
	}


	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLogin(@ModelAttribute("user") UserInterface user, Model model, Principal principal) {

		
		if (null != principal) {
			User activeUser = (User) ((Authentication) principal).getPrincipal();
			
	
			GrantedAuthority roleClient = new SimpleGrantedAuthority("ROLE_CLIENT");
			GrantedAuthority rolePartner = new SimpleGrantedAuthority("ROLE_PARTNER");
			
			if (activeUser.getAuthorities().contains(rolePartner)) {
				Partner part = partnerService.fetchAll(activeUser.getUsername());
				model.addAttribute("user", part);
				model.addAttribute("omega", "Commerçant");
				//logger.info("USER ================>"+part.toString());
			//	logger.info("ADRESSES ================>"+user.getAddresseses().toString());
				logger.info("AUTH ================>"+activeUser.getAuthorities());
				logger.info("PARTNER ================>"+part.toString());
				return "redirect:/partner/offers";
			}
			
			if (activeUser.getAuthorities().contains(roleClient)) {
			user = clientService.fetchJoinAll(activeUser.getUsername());
			model.addAttribute("user", user);
			logger.info("USER ================>"+user.toString());
			//logger.info("ADRESSES ================>"+user.getAddresseses().toString());
			logger.info("AUTH ================>"+activeUser.getAuthorities());
			

			//logger.info("Journey ================>"+user.getJourneyses());
			
			model.addAttribute("pageTitle", "GoingMobile : Regardez vos trajets");
			return "redirect:/user/journeys";
			
			}
			
			
		}
		return "redirect:/";
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
	
	public TransactionServiceIFace getTransServ() {
		return transServ;
	}


	public void setTransServ(TransactionServiceIFace transServ) {
		this.transServ = transServ;
	}


	@RequestMapping("/transactions")
	public String seeTransactions(Model model, @ModelAttribute("user") Client user) {
		
		Set<Transaction> transactions = transServ.fetchJoinByClientId(user.getId());
		model.addAttribute("transactions", transactions);
		
		return "transactions";
	}
}
