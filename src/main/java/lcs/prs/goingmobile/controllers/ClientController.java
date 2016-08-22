package lcs.prs.goingmobile.controllers;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lcs.prs.goingmobile.entities.Client;

@Controller
@RequestMapping("/user")
public class ClientController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@RequestMapping(value = "/journeys", method = RequestMethod.POST)
	public String userLogin(@ModelAttribute Client client, Locale locale, Model model) {
		
		model.addAttribute("client",client);
		model.addAttribute("pageTitle", "GoingMobile : Regardez vos trajets");
		
		return "journeys";
	}
	
	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute Client client,Model model) {
		
		
		
		return "journeys";
	}
	
}
