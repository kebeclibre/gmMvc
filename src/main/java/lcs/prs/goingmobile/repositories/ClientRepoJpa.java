package lcs.prs.goingmobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import lcs.prs.goingmobile.entities.Client;

@Transactional
public interface ClientRepoJpa extends JpaRepository<Client, Integer>,AgentRepo<Client> {
	
	@Query("select c from Client c left join fetch c.addresseses as addresse where username=?1")
	Client joinFetchAddresses(String username);
	
	@Query("select c from Client c "
			+ "left join fetch c.addresseses as addresse "
			+ "left join fetch c.journeyses as journey "
			+ "where username=?1")
	Client joinFetchAll(String username);
	
	@Query("select c from Client c left join fetch c.journeyses as journey where username=?1")
	Client joinFetchJourneys(String username);
}
