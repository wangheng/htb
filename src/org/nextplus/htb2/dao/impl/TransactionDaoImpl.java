package org.nextplus.htb2.dao.impl;

import java.util.Date;

import org.nextplus.commons.DaoImpl;
import org.nextplus.htb2.dao.TransactionDao;
import org.nextplus.htb2.domain.Transaction;
import org.springframework.util.Assert;

public class TransactionDaoImpl extends DaoImpl<Transaction>  
implements TransactionDao {

	@Override
	public Long create(Transaction transaction) {
		Assert.notNull(transaction);
		transaction.setCreateDate(new Date());
		return super.create(transaction);
	}

	@Override
	public void update(Transaction transaction) {
		Assert.notNull(transaction);
		transaction.setCreateDate(new Date());
		super.update(transaction);
	}

	@Override
	public void delete(Transaction transaction) {
		Assert.notNull(transaction);
		super.delete(transaction);	
	}

	@Override
	protected Class<Transaction> getType() {
		
		return Transaction.class;
	}

}
