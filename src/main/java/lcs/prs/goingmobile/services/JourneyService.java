package lcs.prs.goingmobile.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcs.prs.goingmobile.entities.Journey;
import lcs.prs.goingmobile.repositories.JourneyRepo;

@Service
public class JourneyService {

	@Autowired
	private JourneyRepo journeyRepo;
	
	public JourneyRepo getJourneyRepo() {
		return journeyRepo;
	}

	public void setJourneyRepo(JourneyRepo journeyRepo) {
		this.journeyRepo = journeyRepo;
	}

	public String getTrackRaw(int id){
		return journeyRepo.findOne(id).getRawData();
	}
	
	public void saveAll(Set<Journey> newJourneys) {
		journeyRepo.save(newJourneys);
	}
}
