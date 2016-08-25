package lcs.prs.goingmobile.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AgentRepo {

	@Transactional(readOnly=false)
	@Modifying
	@Query(value="insert into roles (username,role) values (?1,?2)", nativeQuery=true )
	void insertRole(String username,String role);
}
