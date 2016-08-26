package lcs.prs.goingmobile.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.repositories.ClientRepoJpa;

@Service("clientService")

public class ClientService implements IServiceRepo<Client, Integer> {

	@Autowired
	private ClientRepoJpa repo;
	
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

	
}
