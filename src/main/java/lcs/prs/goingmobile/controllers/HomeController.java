package lcs.prs.goingmobile.controllers;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.entities.Partner;
import lcs.prs.goingmobile.interfaces.UserInterface;
import lcs.prs.goingmobile.services.interfaces.ClientServiceIFace;
import lcs.prs.goingmobile.services.interfaces.IpAddressServiceIFace;
import lcs.prs.goingmobile.services.interfaces.PartnerServiceIface;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({ "omega","user","servAddress"})
public class HomeController {
	
	@Autowired
	IpAddressServiceIFace ipAddrServ;

	@Autowired
	private ServletContext servletContext;
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public PartnerServiceIface getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerServiceIface partnerService) {
		this.partnerService = partnerService;
	}

	@Autowired
	private PartnerServiceIface partnerService;
	
	public ClientServiceIFace getClientService() {
		return clientService;
	}

	public void setClientService(ClientServiceIFace clientService) {
		this.clientService = clientService;
	}

	@Autowired
	private ClientServiceIFace clientService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest req) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("pageTitle", "GoingMobile: Traversez la ville naturellement.");

		model.addAttribute("omega", "Cycliste");
		
		model.addAttribute("servAddress",ipAddrServ.showList()+":"+req.getServerPort()+req.getContextPath());
		
		
		return "home";
	}


	@RequestMapping(value = "/connectAsPartner", method = RequestMethod.GET)
	public String connectAsPartner(Model model) {

		model.addAttribute("omega", "Commerçants");
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLogin(@ModelAttribute("nonAuthUser") UserInterface user, Model model, Principal principal) {

		
		if (null != principal) {
			User activeUser = (User) ((Authentication) principal).getPrincipal();
			
	
			GrantedAuthority roleClient = new SimpleGrantedAuthority("ROLE_CLIENT");
			GrantedAuthority rolePartner = new SimpleGrantedAuthority("ROLE_PARTNER");
			
			if (activeUser.getAuthorities().contains(rolePartner)) {
				Partner part = partnerService.fetchAll(activeUser.getUsername());
				model.addAttribute("user", part);
				model.addAttribute("omega", "Commerçant");
				//logger.info("USER ================>"+part.toString());
			//	logger.info("ADRESSES ================>"+user.getAddresseses().toString());
				logger.info("AUTH ================>"+activeUser.getAuthorities());
				logger.info("PARTNER ================>"+part.toString());
				return "redirect:/partner/offers";
			}
			
			if (activeUser.getAuthorities().contains(roleClient)) {
				user = clientService.fetchJoinAll(activeUser.getUsername());
				Client usr = (Client) user;
				TreeSet journeys = new TreeSet(usr.getJourneyses());
				usr.setJourneyses(journeys);
			
				model.addAttribute("user", usr);
				logger.info("USER ================>"+usr.toString());
				logger.info("AUTH ================>"+activeUser.getAuthorities());
			
				model.addAttribute("pageTitle", "GoingMobile : Regardez vos trajets");
			return "redirect:/user/journeys";
			
			}
			
			
		}
		return "redirect:/";
	}
	
	@ModelAttribute("nonAuthUser")
	public Client getUserObject() {
		return new Client();
	}
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}

}
