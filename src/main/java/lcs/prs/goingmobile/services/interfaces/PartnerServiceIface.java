package lcs.prs.goingmobile.services.interfaces;

import java.util.List;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.entities.Partner;

public interface PartnerServiceIface extends IServiceRepo<Partner, Integer>{

	void save(Partner element);

	Partner fetchAll(String username);
	
	List<Partner> getAllPartners();
	

}