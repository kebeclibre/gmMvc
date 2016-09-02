package lcs.prs.goingmobile.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lcs.prs.goingmobile.entities.Transaction;
import lcs.prs.goingmobile.repositories.TransactionRepo;

@Service
public class TransactionService implements ServiceIFace {

	@Autowired
	private TransactionRepo repo;
	
	public TransactionRepo getRepo() {
		return repo;
	}

	public void setRepo(TransactionRepo repo) {
		this.repo = repo;
	}

	public Set<Transaction> fetchJoinByClientId(int id) {
		return repo.joinFetchAllByClientId(id);
	}
	
	public Set<Transaction> fetchJoinByPartnerId(int id) {
		return repo.joinFetchAllByPartnerId(id);
	}
	
	public void save(Transaction transaction) {
		repo.save(transaction);
	}
	
	
}
