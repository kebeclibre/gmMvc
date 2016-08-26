package lcs.prs.goingmobile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.entities.Partner;
import lcs.prs.goingmobile.repositories.PartnerRepoJpa;
@Service("partnerService")
public class PartnerService implements IServiceRepo<Partner, Integer>{

	@Autowired
	private PartnerRepoJpa repo;
	
	public  PartnerRepoJpa getRepo() {
		return repo;
	}

	public void setRepo( PartnerRepoJpa repo) {
		this.repo = repo;
	}
	
	@Override
	public void save(Partner element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findById(Integer key) {
		// TODO Auto-generated method stub
		
	}
	
	public Partner findByUsername(String username) {
		return repo.findByUsername(username);
		
	}

}
