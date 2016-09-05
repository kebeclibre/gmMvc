package lcs.prs.goingmobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.entities.Partner;

@Transactional
public interface PartnerRepoJpa extends JpaRepository<Partner, Integer>,AgentRepo<Partner> {
	
	@Query("select p from Partner p "
			+ "left join fetch p.addresseses as a "
			+ "left join fetch p.partneradses as o "
			+ "where p.username=?1")
	public Partner joinFetchAll(String username);

	@Query("select p from Partner p "
			+ "left join fetch p.addresseses as a "
			+ "left join fetch p.partneradses as o "
			+ "where p.id=?1")
	public Partner joinFetchAllById(int id);

}