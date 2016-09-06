package lcs.prs.goingmobile.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lcs.prs.goingmobile.entities.Partner;
import lcs.prs.goingmobile.repositories.PartnerRepoJpa;
import lcs.prs.goingmobile.services.interfaces.IServiceRepo;
import lcs.prs.goingmobile.services.interfaces.PartnerServiceIface;
@Service("partnerService")
@Transactional
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
		element.setIsActive(true);
		element.setRegistrationDate(new Date());
		repo.insertRole(element.getUsername(), "PARTNER");
		repo.save(element);
		
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
