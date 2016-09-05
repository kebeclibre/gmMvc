package lcs.prs.goingmobile.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import lcs.prs.goingmobile.services.interfaces.ClientServiceIFace;

@Controller
@RequestMapping("/common")
@SessionAttributes({"user"})
public class CommonController {
	
	@Autowired
	ClientServiceIFace clientService;


	
	@RequestMapping("currentProfile")
	public String viewCurrentProfile(Model model) {
		return "profile";
	}
	
	
	
}
