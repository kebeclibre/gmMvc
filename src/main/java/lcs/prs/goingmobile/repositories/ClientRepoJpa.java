package lcs.prs.goingmobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import lcs.prs.goingmobile.entities.Client;

@Transactional
public interface ClientRepoJpa extends JpaRepository<Client, Integer>,AgentRepo {

}
