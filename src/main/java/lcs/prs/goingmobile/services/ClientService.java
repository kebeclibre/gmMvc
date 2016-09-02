package lcs.prs.goingmobile.services;

import java.util.Date;
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

@Service("clientService")
public class ClientService implements IServiceRepo<Client, Integer> {

	@Autowired
	private ClientRepoJpa repo;
	
	@Autowired
	private JourneyService journeyService;
	
	@Autowired
	private TransactionService transactionService;
	
	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public JourneyService getJourneyService() {
		return journeyService;
	}

	public void setJourneyService(JourneyService journeyService) {
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
	public void findById(Integer key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client findByUsername(String username) {
		return repo.findByUsername(username);
		
	}
	
	public Client fetchJoinAddresses(String username) {
		return repo.joinFetchAddresses(username);
	}
	
	public Client fetchJoinAll(String username) {
		return repo.joinFetchAll(username);
	}
	
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
	
	public Client getClientWithCredentials(String username, String password) {
		return repo.checkClientCredentials(username, password);
	}
	
	@Transactional(rollbackFor=InsufficientFundsException.class)
	public void proceedTransaction(Client client, Partner part, TransactionWrapper transactionWrapper) {//throws InsufficientFundsException {
		if (client.getGmPointsHistoryCumul() < transactionWrapper.getTransaction().getGmPointsEngaged()) {
			
			//throw new InsufficientFundsException();
		} else {
			client.setGmPointsHistoryCumul(client.getGmPointsHistoryCumul()-(float)transactionWrapper.getTransaction().getGmPointsEngaged());
			
			transactionWrapper.getTransaction().setClients(client);
			transactionWrapper.getTransaction().setPartners(part);
			repo.save(client);
			transactionService.save(transactionWrapper.getTransaction());
		}
	}


	
}
