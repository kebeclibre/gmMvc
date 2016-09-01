package lcs.prs.goingmobile.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/common")
@SessionAttributes({"user"})
public class CommonController {

	@RequestMapping("currentProfile")
	public String viewCurrentProfile(Model model) {
		return "profile";
	}
	
}
