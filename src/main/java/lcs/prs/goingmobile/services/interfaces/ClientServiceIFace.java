package lcs.prs.goingmobile.services.interfaces;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.entities.Journey;
import lcs.prs.goingmobile.entities.Partner;
import lcs.prs.goingmobile.exceptions.InsufficientFundsException;
import lcs.prs.goingmobile.helperclasses.TransactionWrapper;

public interface ClientServiceIFace extends IServiceRepo<Client, Integer>{

	Client fetchJoinAddresses(String username);

	Client fetchJoinAll(String username);

	void update(Client client);

	Client update(Client client, Set<Journey> journeySet);

	Client getClientWithCredentials(String username, String password);

	void proceedTransaction(Client client, Partner part, TransactionWrapper transactionWrapper) throws InsufficientFundsException;
	
	String getUsernamesLike(String like);

}