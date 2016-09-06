package lcs.prs.goingmobile.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lcs.prs.goingmobile.entities.Transaction;
import lcs.prs.goingmobile.repositories.TransactionRepo;
import lcs.prs.goingmobile.services.interfaces.TransactionServiceIFace;

@Service
@Transactional
public class TransactionService implements ServiceIFace, TransactionServiceIFace {

	@Autowired
	private TransactionRepo repo;
	
	public TransactionRepo getRepo() {
		return repo;
	}

	public void setRepo(TransactionRepo repo) {
		this.repo = repo;
	}

	@Override
	public Set<Transaction> fetchJoinByClientId(int id) {
		return repo.joinFetchAllByClientId(id);
	}
	
	@Override
	public Set<Transaction> fetchJoinByPartnerId(int id) {
		return repo.joinFetchAllByPartnerId(id);
	}
	
	@Override
	public void save(Transaction transaction) {
		repo.save(transaction);
	}
	
	
}
