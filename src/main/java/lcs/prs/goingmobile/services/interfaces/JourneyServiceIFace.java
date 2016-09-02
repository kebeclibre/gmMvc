package lcs.prs.goingmobile.services.interfaces;

import java.util.Set;

import lcs.prs.goingmobile.entities.Journey;

public interface JourneyServiceIFace {

	String getTrackRaw(int id);

	void saveAll(Set<Journey> newJourneys);

}