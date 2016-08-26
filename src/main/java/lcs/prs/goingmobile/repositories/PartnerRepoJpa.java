package lcs.prs.goingmobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import lcs.prs.goingmobile.entities.Partner;

@Transactional
public interface PartnerRepoJpa extends JpaRepository<Partner, Integer>,AgentRepo<Partner> {

}
