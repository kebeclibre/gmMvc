package lcs.prs.goingmobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lcs.prs.goingmobile.entities.Journey;

@Repository
public interface JourneyRepo extends JpaRepository<Journey, Integer>{

	
}
