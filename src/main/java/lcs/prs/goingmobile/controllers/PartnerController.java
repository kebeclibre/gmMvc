package lcs.prs.goingmobile.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/partner")
@SessionAttributes({ "user" })
public class PartnerController {
	
	@RequestMapping("/offers")
	public String loginPartner(Model model, Principal principal) {
		
		
		return "offers";
	}
		
}
