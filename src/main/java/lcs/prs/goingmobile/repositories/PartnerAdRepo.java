package lcs.prs.goingmobile.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lcs.prs.goingmobile.entities.PartnerAd;

@Repository
public interface PartnerAdRepo extends JpaRepository<PartnerAd, Integer> {

	@Query("select o from PartnerAd o "
			+ "left join fetch o.partners as p "
			+ "where p.id = ?")
	Set<PartnerAd> fetchJoinAdsByPArtnerId(int id); 
	
	@Query("select distinct o from PartnerAd o "
			+ "inner join fetch o.partners where o.isActive=1")
	List<PartnerAd> findAllJoinFetch();

	
}
