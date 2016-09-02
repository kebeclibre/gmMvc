package lcs.prs.goingmobile.controllers;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.entities.Partner;
import lcs.prs.goingmobile.entities.Transaction;
import lcs.prs.goingmobile.exceptions.InsufficientFundsException;
import lcs.prs.goingmobile.helperclasses.TransactionWrapper;
import lcs.prs.goingmobile.services.interfaces.ClientServiceIFace;
import lcs.prs.goingmobile.services.interfaces.PartnerServiceIface;
import lcs.prs.goingmobile.services.interfaces.TransactionServiceIFace;

@Controller
@RequestMapping("/partner")
@SessionAttributes({ "user" })
public class PartnerController {

	@Autowired
	private ClientServiceIFace clientService;
	
	@Autowired
	private PartnerServiceIface partnerService;
	
	@Autowired
	private TransactionServiceIFace transServ;

	public PartnerServiceIface getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerServiceIface partnerService) {
		this.partnerService = partnerService;
	}

	@RequestMapping("/offers")
	public String loginPartner(Model model, Principal principal) {
		model.addAttribute("pageTitle", "GoingMobile : Regardez vos offres");
		return "offers";
	}

	@RequestMapping("/engageTransaction")
	public String engageTransaction(Model model, @ModelAttribute("transactionWrapper") TransactionWrapper transWrapper) {
		return "newTransaction";
	}

	@RequestMapping("/doTransaction")
	public String doTransaction(
			Model model,
			@ModelAttribute("transactionWrapper") TransactionWrapper transWrapper,
			@ModelAttribute("user") Partner part
			) {
			
		String username = transWrapper.getUsername();
		String password = transWrapper.getPassword();

		Client client = null;
		
		try {
			client = clientService.getClientWithCredentials(username, password);
			clientService.proceedTransaction(client,part , transWrapper);
		} catch (InsufficientFundsException e) {
			model.addAttribute("transactionStatus", "La transaction a échoué : fonds insuffisants");
			return "transactions";
		} catch (RuntimeException ex) {
			model.addAttribute("transactionStatus", "La transaction a échoué : Utilisateur non reconnu");
			ex.printStackTrace();
			return "transactions";
		}
		
		
		Set<Transaction> transactions = transServ.fetchJoinByPartnerId(part.getId());
		
		model.addAttribute("transactions", transactions);
		model.addAttribute("transactionStatus", "Transaction réussie entre "+part.getUsername()+" et "+client.getUsername()+" pour "+transWrapper.getTransaction().getGmPointsEngaged());
		
		return "transactions";
	}
	
	public ClientServiceIFace getClientService() {
		return clientService;
	}

	public void setClientService(ClientServiceIFace clientService) {
		this.clientService = clientService;
	}

	@ModelAttribute("transactionWrapper")
	public TransactionWrapper providerTransactionWrapper() {
		return new TransactionWrapper();
	}
	
	@RequestMapping("/transactions")
	public String seeTransactions(Model model, @ModelAttribute("user") Partner part) {
		Set<Transaction> transactions = transServ.fetchJoinByPartnerId(part.getId());
		model.addAttribute("transactions", transactions);
		return "transactions";
	}
}
