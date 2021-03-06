package lcs.prs.goingmobile.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lcs.prs.goingmobile.entities.Journey;
import lcs.prs.goingmobile.repositories.JourneyRepo;
import lcs.prs.goingmobile.services.interfaces.JourneyServiceIFace;

@Service
@Transactional
public class JourneyService implements ServiceIFace, JourneyServiceIFace {

	@Autowired
	private JourneyRepo journeyRepo;
	
	public JourneyRepo getJourneyRepo() {
		return journeyRepo;
	}

	public void setJourneyRepo(JourneyRepo journeyRepo) {
		this.journeyRepo = journeyRepo;
	}

	@Override
	public String getTrackRaw(int id){
		return journeyRepo.findOne(id).getRawData();
	}
	
	@Override
	public void saveAll(Set<Journey> newJourneys) {
		journeyRepo.save(newJourneys);
	}
}
