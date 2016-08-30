package lcs.prs.goingmobile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcs.prs.goingmobile.repositories.JourneyRepo;

@Service
public class JourneyService {

	@Autowired
	JourneyRepo journeyrepo;
	
	public String getTrackRaw(int id){
		return journeyrepo.findOne(id).getRawData();
	}
}
