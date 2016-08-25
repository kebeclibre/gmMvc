package lcs.prs.goingmobile.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.services.IServiceRepo;

@Controller
@RequestMapping("/user")
@SessionAttributes({"user"})
public class ClientController {

	@Autowired
	private IServiceRepo<Client, Integer> service;

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	public IServiceRepo<Client, Integer> getService() {
		return service;
	}

	public void setService(IServiceRepo<Client, Integer> service) {
		this.service = service;
	}

	
	@RequestMapping(value = "/journeys", method = RequestMethod.GET)
	public String userLogin(@ModelAttribute("user") Client user,Model model, Principal principal) {
		
			if (null != principal) {
				user.setUsername(principal.getName());
				model.addAttribute("user", user);
		}
		//httpSess.setAttribute("user", user);
		
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
	
	
}
