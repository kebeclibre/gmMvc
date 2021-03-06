package lcs.prs.goingmobile.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.entities.Partner;
import lcs.prs.goingmobile.entities.PartnerAd;
import lcs.prs.goingmobile.services.interfaces.ClientServiceIFace;
import lcs.prs.goingmobile.services.interfaces.JourneyServiceIFace;
import lcs.prs.goingmobile.services.interfaces.PartnerAdServiceIface;
import lcs.prs.goingmobile.services.interfaces.PartnerServiceIface;

@Controller
@RequestMapping("/generic")
@SessionAttributes({"user"})
public class GenericUserController {
	
	@Autowired
	private ClientServiceIFace serviceClient;
	
	@Autowired
	private JourneyServiceIFace journeyServ;
	
	@Autowired
	private PartnerServiceIface partnerService;
	
	@Autowired
	private PartnerAdServiceIface partnerAdServ;
	
	public ClientServiceIFace getServiceClient() {
		return serviceClient;
	}


	public void setServiceClient(ClientServiceIFace serviceClient) {
		this.serviceClient = serviceClient;
	}


	public JourneyServiceIFace getJourneyServ() {
		return journeyServ;
	}


	public void setJourneyServ(JourneyServiceIFace journeyServ) {
		this.journeyServ = journeyServ;
	}


	public PartnerServiceIface getPartnerService() {
		return partnerService;
	}


	public void setPartnerService(PartnerServiceIface partnerService) {
		this.partnerService = partnerService;
	}


	public PartnerAdServiceIface getPartnerAdServ() {
		return partnerAdServ;
	}


	public void setPartnerAdServ(PartnerAdServiceIface partnerAdServ) {
		this.partnerAdServ = partnerAdServ;
	}


	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String displayForm(Model model) {
		
		model.addAttribute("genericClient", new Client());
		
		return "signUpForm";
	}
	
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("genericClient") Client client, BindingResult bindingResult, Model model) {  //MODEL BINDIG
		if (bindingResult.hasErrors()) {
			System.out.println("======================= IL Y A EU UNE ERREUR DE VALIDATION ======");
			return "signUpForm";
		} else {
			
			Client checking = serviceClient.findByUsername(client.getUsername());
			if (checking != null) {
				model.addAttribute("saveSucceed",false);
				model.addAttribute("alreadyUsed","L'utilisateur existe déjà");
				model.addAttribute("genericClient",new Client());
				return "signUpForm";				
			}
			
			serviceClient.save(client);
			model.addAttribute("genericClient",client);
			model.addAttribute("saveSucceed",true);
			model.addAttribute("pageTitle", "GoingMobile : Votre compte a été créé");
			return "home";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getTrack")
	public String getTrack(@RequestParam("trackid") int trackid) {
		String result ="";
		System.out.println("CALL ==========> OK");
		return journeyServ.getTrackRaw(trackid);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUsernames",method = RequestMethod.GET)
	public String getUsernames(Model model, @RequestParam("partialName") String partialUname) {
		return serviceClient.getUsernamesLike(partialUname);
		
	}
	
	@RequestMapping("/viewAllOffers")
	public String viewAllOffers(Model model) {
		
		List<PartnerAd> list = partnerAdServ.getAllAds();
		model.addAttribute("allOffer", list );
		return "globalOffers";
	}
	
	@RequestMapping("/partnerSignup")
	public String partnerSignup (Model model, @ModelAttribute("genericPartner") Partner part) {
		return "partnerRegisterForm";
	}
	
	@ModelAttribute("genericPartner")
	public Partner populateGenericPartner() {
		return new Partner();
	}
	
	@RequestMapping("/registerPartner")
	public String registerPartner (Model model, @ModelAttribute("genericPartner") Partner part) {
		//if (bindingResult.hasErrors()) {
			//System.out.println("======================= IL Y A EU UNE ERREUR DE VALIDATION ======");
		//	return "signUpForm";
		//} else {
		
			Partner checking = partnerService.findByUsername(part.getUsername());
			if (checking != null) {
				model.addAttribute("saveSucceed",false);
				model.addAttribute("alreadyUsed","L'utilisateur existe déjà");
				model.addAttribute("genericClient",new Client());
				return "signUpForm";
				
			}
			partnerService.save(part);
			model.addAttribute("genericClient",part);
			model.addAttribute("saveSucceed",true);
			model.addAttribute("pageTitle", "GoingMobile : Votre compte a été créé");
			return "home";
		
	}
	
	@RequestMapping("/viewProfile")
	public String viewProfile(Model model, @RequestParam("partnerId") int partId) {
		Partner part = partnerService.findById(partId);
		model.addAttribute("otherUser",part);
		return "otherProfile";
	}
	
	
	@RequestMapping("/viewAllPartners")
	public String viewAllPartners(Model model) {
		
		List<Partner> list = partnerService.getAllPartners();
		
		model.addAttribute("allPartners", list);
		return "globalPartners";
	}
	
}