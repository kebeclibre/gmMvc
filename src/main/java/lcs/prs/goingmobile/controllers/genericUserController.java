package lcs.prs.goingmobile.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lcs.prs.goingmobile.entities.Client;

@Controller
@RequestMapping("/generic")
public class genericUserController {
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String displayForm(Model model) {
		
		model.addAttribute("client", new Client());
		
		return "signUpForm";
	}

}
