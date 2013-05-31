package org.nextplus.htb2.service;

import java.util.Date;
import java.util.List;

import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.Transaction;
import org.nextplus.htb2.domain.Transaction.State;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionService {

	@Transactional(rollbackFor = Exception.class)
	Long createTransaction(Transaction transaction) throws DuplicateException;

	@Transactional(rollbackFor = Exception.class)
	void updateTransaction(Transaction transaction) throws DuplicateException;

	@Transactional
	Transaction deleteTransaction(Long transactionId);

	@Transactional(readOnly = true)
	Transaction getTransaction(Long transactionId);

	@Transactional(readOnly = true)
	Transaction getTransaction(String transactionName);

	@Transactional(readOnly = true)
	List<Transaction> getTransactions(Paginator paginator);

	@Transactional(readOnly = true)
	List<Transaction> getTransactions(Long memberId, Date from, Date to, State state, Long depotId,
			Paginator paginator);

}
