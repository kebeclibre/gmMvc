package lcs.prs.goingmobile.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.helperclasses.TransactionWrapper;
import lcs.prs.goingmobile.services.ClientService;

@Controller
@RequestMapping("/partner")
@SessionAttributes({ "user" })
public class PartnerController {

	@Autowired
	private ClientService clientService;

	@RequestMapping("/offers")
	public String loginPartner(Model model, Principal principal) {

		return "offers";
	}

	@RequestMapping("/engageTransaction")
	public String engageTransaction(Model model, @ModelAttribute("transactionWrapper") TransactionWrapper transWrapper) {
		return "newTransaction";
	}

	@RequestMapping("/doTransaction")
	public String doTransaction(
			Model model,
			@ModelAttribute("transactionWrapper") TransactionWrapper transWrapper
//			@RequestParam("clientUser") String username,
//			@RequestParam("clientPassword") String password,
//			@RequestParam("pointsEngaged") String points
			) {
			
		String username = transWrapper.getUsername();
		String password = transWrapper.getPassword();

		Client client = clientService.getClientWithCredentials(username, password);
		
		
		
		
		
		
		return "transactions";
	}
	
	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	@ModelAttribute("transactionWrapper")
	public TransactionWrapper provideransactionWrapper() {
		return new TransactionWrapper();
	}
}
