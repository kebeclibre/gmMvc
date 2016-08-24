package lcs.prs.goingmobile.controllers;

import java.security.Principal;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

	
	@RequestMapping(value = "/journeys", method = RequestMethod.POST)
	public @ResponseBody String userLogin(Locale locale, Model model) {
		
		
		Client genericClient = new Client();
		genericClient.setUsername(getPrincipal());
		
		model.addAttribute("user", genericClient);
		model.addAttribute("pageTitle", "GoingMobile : Regardez vos trajets");

		return "journeys";
	}
	
	@RequestMapping(value = "/journeys", method = RequestMethod.GET)
	public String userLoginGet(@ModelAttribute Client genericClient, Locale locale, Model model) {

		model.addAttribute("user", genericClient);
		model.addAttribute("pageTitle", "GoingMobile : Regardez vos trajets");

		return "journeys";
	}

	// @RequestMapping(value="/registerUser", method = RequestMethod.POST)
	// public String registerUser(@ModelAttribute Client client,Model model) {
	//
	// service.save(client);
	// model.addAttribute("client",client);
	// return "journeys";
	// }
	
	private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
	

}
