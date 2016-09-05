package lcs.prs.goingmobile.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import lcs.prs.goingmobile.entities.Client;
import lcs.prs.goingmobile.entities.Partner;

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
	
	@Query("select c from Client c where c.username=?1 and c.password=?2 and isActive=1")
	Client checkClientCredentials(String username, String password);
	
	@Query("select c from Client c where c.username LIKE %:uname%")
	List<Client> getClientsLike(@Param("uname") String uname);
	
	
	@Query("select c from Client c "
			+ "left join fetch c.addresseses as addresse "
			+ "left join fetch c.journeyses as journey "
			+ "where c.id=?1")
	public Client joinFetchAllById(int id);

}
