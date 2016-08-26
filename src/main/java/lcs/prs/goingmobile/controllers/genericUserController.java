package lcs.prs.goingmobile.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.services.IServiceRepo;

@Controller
@RequestMapping("/generic")
@SessionAttributes({"user"})
public class GenericUserController {
	
	@Autowired
	private IServiceRepo<Client, Integer> serviceClient;
	
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
			serviceClient.save(client);
			model.addAttribute("genericClient",client);
			model.addAttribute("saveSucceed",true);
			model.addAttribute("pageTitle", "GoingMobile : Votre compte a été créé");
			return "home";
		}
	}

	
}
