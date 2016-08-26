package lcs.prs.goingmobile.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AgentRepo<T> {

	@Transactional(readOnly=false)
	@Modifying
	@Query(value="insert into roles (username,role) values (?1,?2)", nativeQuery=true )
	void insertRole(String username,String role);
	
	@Transactional(readOnly=false)
	@Modifying
	@Query(value="delete from roles where username=?1", nativeQuery=true )
	void deleteRole(String username);
	
	 T findByUsername(String username);
}
