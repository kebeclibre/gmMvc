package lcs.prs.goingmobile.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcs.prs.goingmobile.entities.Partner;
import lcs.prs.goingmobile.repositories.PartnerRepoJpa;
import lcs.prs.goingmobile.services.interfaces.IServiceRepo;
import lcs.prs.goingmobile.services.interfaces.PartnerServiceIface;
@Service("partnerService")
public class PartnerService implements IServiceRepo<Partner, Integer>, PartnerServiceIface{

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
	public Partner findById(Integer key) {
		return repo.joinFetchAllById(key);
		
	}
	
	public Partner findByUsername(String username) {
		return repo.findByUsername(username);
		
	}
	
	@Override
	public Partner fetchAll(String username) {
		return repo.joinFetchAll(username);
	}
	
	public List<Partner> getAllPartners() {
		return repo.findAll();
	}

}
