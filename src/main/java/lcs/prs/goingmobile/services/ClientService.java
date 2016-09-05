package lcs.prs.goingmobile.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.entities.Journey;
import lcs.prs.goingmobile.entities.Partner;
import lcs.prs.goingmobile.exceptions.InsufficientFundsException;
import lcs.prs.goingmobile.helperclasses.TransactionWrapper;
import lcs.prs.goingmobile.repositories.ClientRepoJpa;
import lcs.prs.goingmobile.services.interfaces.ClientServiceIFace;
import lcs.prs.goingmobile.services.interfaces.JourneyServiceIFace;
import lcs.prs.goingmobile.services.interfaces.TransactionServiceIFace;

@Service("clientService")
public class ClientService implements ClientServiceIFace {

	@Autowired
	private ClientRepoJpa repo;
	
	@Autowired
	private JourneyServiceIFace journeyService;
	
	@Autowired
	private TransactionServiceIFace transactionService;
	
	public TransactionServiceIFace getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionServiceIFace transactionService) {
		this.transactionService = transactionService;
	}

	public JourneyServiceIFace getJourneyService() {
		return journeyService;
	}

	public void setJourneyService(JourneyServiceIFace journeyService) {
		this.journeyService = journeyService;
	}

	public ClientRepoJpa getRepo() {
		return repo;
	}

	public void setRepo(ClientRepoJpa repo) {
		this.repo = repo;
	}

	@Override
	public void save(Client element) {
		element.setRegistrationDate(new Date());
		element.setKmsHistoryCumul(0);
		element.setKmsTotal(0);
		element.setGmPointsHistoryCumul(0);
		element.setGmPointsTotal(0);
		element.setIsActive(true);
		repo.save(element);
		repo.insertRole(element.getUsername(), "CLIENT");
		
	}

	@Override
	public Client findById(Integer key) {
		return repo.joinFetchAllById(key);
		
	}

	@Override
	public Client findByUsername(String username) {
		return repo.findByUsername(username);
		
	}
	
	@Override
	public Client fetchJoinAddresses(String username) {
		return repo.joinFetchAddresses(username);
	}
	
	@Override
	public Client fetchJoinAll(String username) {
		return repo.joinFetchAll(username);
	}
	
	@Override
	public void update(Client client) {
		Client managed =repo.findOne(client.getId());
		Set<Journey> journeyList = client.getJourneyses();
		
		for (Journey j : journeyList) {
			client.setGmPointsHistoryCumul(client.getGmPointsHistoryCumul()+j.getGmPoints());
			client.setGmPointsTotal(client.getGmPointsTotal()+j.getGmPoints());
			client.setKmsHistoryCumul(client.getKmsHistoryCumul()+j.getKilometers());
			client.setKmsTotal(client.getKmsTotal()+j.getKilometers());
			
		}
		
		managed.setJourneyses(client.getJourneyses());
		
		repo.saveAndFlush(managed);
		
	}
	
	@Override
	@Transactional
	public Client update(Client client,Set<Journey> journeySet) {
		Client managed =repo.joinFetchJourneys(client.getUsername());
		
		
		for (Journey j : journeySet) {
			managed.setGmPointsHistoryCumul(managed.getGmPointsHistoryCumul()+j.getGmPoints());
			managed.setGmPointsTotal(managed.getGmPointsTotal()+j.getGmPoints());
			managed.setKmsHistoryCumul(managed.getKmsHistoryCumul()+j.getKilometers());
			managed.setKmsTotal(managed.getKmsTotal()+j.getKilometers());
			j.setAddedDate(new Date());
			j.setIsActive(true);
			j.setClients(managed);
			
		}
		
		
		journeyService.saveAll(journeySet);
		Set<Journey> global = client.getJourneyses();
		
		global.addAll(journeySet);
		
		managed.setJourneyses(global);
		
		repo.save(managed);
		
		return managed;
		
	}
	
	@Override
	@Transactional
	public Client getClientWithCredentials(String username, String password) {
		//Client result = 
		
		return repo.checkClientCredentials(username, password);
	}
	
	@Override
	@Transactional(rollbackFor=InsufficientFundsException.class)
	public void proceedTransaction(Client client, Partner part, TransactionWrapper transactionWrapper) throws InsufficientFundsException {
		if (client.getGmPointsHistoryCumul() < transactionWrapper.getTransaction().getGmPointsEngaged()) {
			
			throw new InsufficientFundsException();
		} else {
			client.setGmPointsHistoryCumul(client.getGmPointsHistoryCumul()-(float)transactionWrapper.getTransaction().getGmPointsEngaged());
			transactionWrapper.getTransaction().setTransactionDate(new Date());
			transactionWrapper.getTransaction().setClients(client);
			transactionWrapper.getTransaction().setPartners(part);
			repo.save(client);
			transactionService.save(transactionWrapper.getTransaction());
		}
	}

	@Override
	public String getUsernamesLike(String like) {
		List<Client> listMatches = repo.getClientsLike(like);
		StringBuilder sb = new StringBuilder();
		for (Client cli : listMatches) {
			sb.append(cli.getUsername());
			sb.append("===");
		}
		
		if (sb.length() > 3 ) {
		sb.delete(sb.length()-3, sb.length()); }
		
		return sb.toString();
	}


	
}
