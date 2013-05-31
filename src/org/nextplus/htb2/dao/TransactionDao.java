package org.nextplus.htb2.dao;

import org.nextplus.commons.Dao;
import org.nextplus.htb2.domain.Transaction;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionDao extends Dao<Transaction>{
	
	@Override
	@Transactional
	Long create(Transaction transaction);
	
	@Override
	@Transactional
	void update(Transaction transaction);
	
	@Override
	@Transactional
	void delete(Transaction transaction);
}
