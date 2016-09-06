package lcs.prs.goingmobile.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lcs.prs.goingmobile.entities.Partner;
import lcs.prs.goingmobile.entities.PartnerAd;
import lcs.prs.goingmobile.repositories.PartnerAdRepo;
import lcs.prs.goingmobile.services.interfaces.PartnerAdServiceIface;
import lcs.prs.goingmobile.services.interfaces.PartnerServiceIface;

@Transactional
@Service
public class PartnerAdService implements PartnerAdServiceIface {
	
	@Autowired
	private PartnerAdRepo AdRepo;
	
	@Autowired
	private PartnerServiceIface partnerServ;
	
	
	public PartnerAdRepo getAdRepo() {
		return AdRepo;
	}


	public void setAdRepo(PartnerAdRepo adRepo) {
		AdRepo = adRepo;
	}


	public PartnerServiceIface getPartnerServ() {
		return partnerServ;
	}


	public void setPartnerServ(PartnerServiceIface partnerServ) {
		this.partnerServ = partnerServ;
	}


	@Override
	public void save(PartnerAd offer, Partner part) {
		offer.setAddedDate(new Date());
		offer.setPartners(part);
		offer.setIsActive(true);
		AdRepo.save(offer);
	}


	@Override
	public List<PartnerAd> getAllAds() {
		return AdRepo.findAllJoinFetch();
//		return AdRepo.findAllJoinFetch();
	}
	
}
