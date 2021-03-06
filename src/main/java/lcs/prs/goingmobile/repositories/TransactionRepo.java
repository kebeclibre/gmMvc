package lcs.prs.goingmobile.repositories;

import java.util.Set;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lcs.prs.goingmobile.entities.Transaction;
@Repository
@Transactional
public interface TransactionRepo extends JpaRepository<Transaction, Integer>{

	@Query("select t from Transaction t "
			+ "left join fetch t.partners as p "
			+ "left join fetch t.clients as c "
			+ "where c.id=?1 "
			+ "order by t.transactionDate desc")
	Set<Transaction> joinFetchAllByClientId(int id);
	
	
	@Query("select t from Transaction t "
			+ "left join fetch t.partners as p "
			+ "left join fetch t.clients as c "
			+ "where p.id=?1 "
			+ "order by t.transactionDate desc")
	Set<Transaction> joinFetchAllByPartnerId(int id);
	
}
