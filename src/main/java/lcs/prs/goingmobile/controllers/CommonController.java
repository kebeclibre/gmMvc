package lcs.prs.goingmobile.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.entities.Partner;
import lcs.prs.goingmobile.services.interfaces.ClientServiceIFace;
import lcs.prs.goingmobile.services.interfaces.PartnerServiceIface;

@Controller
@RequestMapping("/common")
@SessionAttributes({"user"})
public class CommonController {
	
	public ClientServiceIFace getClientService() {
		return clientService;
	}

	public void setClientService(ClientServiceIFace clientService) {
		this.clientService = clientService;
	}

	public PartnerServiceIface getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerServiceIface partnerService) {
		this.partnerService = partnerService;
	}

	@Autowired
	private ClientServiceIFace clientService;

	@Autowired
	private PartnerServiceIface partnerService;
	
	@RequestMapping("currentProfile")
	public String viewCurrentProfile(Model model) {
		return "profile";
	}
	
	@RequestMapping("/viewProfile")
	public String viewOtherProfile(Model model, @RequestParam("cat") String cat,@RequestParam("id") int id) {
		
		if (cat.equals("client")) {
			Client client = clientService.findById(id);
			model.addAttribute("otherUser", client);
		}
		
		if (cat.equals("partner")) {
			Partner part = partnerService.findById(id);
			model.addAttribute("otherUser", part);
		
		}
		return "otherProfile";
	}
	
	
	
}
