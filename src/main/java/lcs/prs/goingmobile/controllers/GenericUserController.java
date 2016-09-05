package lcs.prs.goingmobile.controllers;

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
import lcs.prs.goingmobile.services.interfaces.ClientServiceIFace;
import lcs.prs.goingmobile.services.interfaces.IServiceRepo;
import lcs.prs.goingmobile.services.interfaces.JourneyServiceIFace;

@Controller
@RequestMapping("/generic")
@SessionAttributes({"user"})
public class GenericUserController {
	
	@Autowired
	private ClientServiceIFace serviceClient;
	
	@Autowired
	private JourneyServiceIFace journeyServ;
	
	@Autowired
	private ClientServiceIFace clientService;
	
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
		System.out.println("CALL LIKE OK");
		return serviceClient.getUsernamesLike(partialUname);
		
	}
	


}
