package lcs.prs.goingmobile.services.interfaces;

import java.util.List;

import lcs.prs.goingmobile.entities.Partner;
import lcs.prs.goingmobile.entities.PartnerAd;

public interface PartnerAdServiceIface {

	void save(PartnerAd offer, Partner part);
	
	public List<PartnerAd> getAllAds();

}