package lcs.prs.goingmobile.services.interfaces;

import java.util.Set;

import lcs.prs.goingmobile.entities.Transaction;

public interface TransactionServiceIFace {

	Set<Transaction> fetchJoinByClientId(int id);

	Set<Transaction> fetchJoinByPartnerId(int id);

	void save(Transaction transaction);

}