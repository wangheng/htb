package org.nextplus.htb2.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.Transaction;
import org.nextplus.htb2.domain.Transaction.State;
import org.nextplus.htb2.hibernate.HibernateTemplate;
import org.nextplus.htb2.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

	@Override
	public Long createTransaction(Transaction transaction) throws DuplicateException {
		Assert.notNull(transaction);
		Assert.isTrue(transaction.isNew());

		Transaction p = getTransaction(transaction.getName());
		if (p != null) {
			throw new DuplicateException(
					String.format("[%s]已经存在", transaction.getName()));
		}

		transaction.setState(State.PENDING);
		transaction.setCreateDate();
		Long transactionId = (Long) template.save(transaction);

		return transactionId;
	}

	@Override
	public void updateTransaction(Transaction transaction) throws DuplicateException {
		Assert.notNull(transaction);
		Assert.isTrue(!transaction.isNew());

		Transaction p = getTransaction(transaction.getName());
		if (p != null && !p.equals(transaction)) {
			throw new DuplicateException(
					String.format("[%s]已经存在", transaction.getName()));
		}

		transaction.setUpdateDate();
		template.merge(transaction);
	}

	@Override
	public Transaction deleteTransaction(Long transactionId) {
		Assert.notNull(transactionId);

		Transaction transaction = getTransaction(transactionId);
		if (transaction != null && transaction.isDeletable()) {
			template.delete(transaction);
		}
		return transaction;
	}

	@Override
	public Transaction getTransaction(Long transactionId) {
		Assert.notNull(transactionId);

		Transaction transaction = template.get(Transaction.class, transactionId);

		return transaction;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Transaction getTransaction(String transactionName) {
		Assert.hasText(transactionName);

		DetachedCriteria criteria = DetachedCriteria.forClass(Transaction.class, "c");
		criteria.add(Restrictions.eq("c.name", transactionName));

		List<Transaction> transactions = template.findByCriteria(criteria);
		if (transactions.isEmpty()) {
			return null;
		}

		return transactions.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> getTransactions(Paginator paginator) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Transaction.class, "tx");
		criteria.addOrder(Order.desc("tx.id"));
		List<Transaction> transactions = template.findByCriteria(criteria, paginator);

		return transactions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> getTransactions(Long memberId, Date from, Date to, State state,
			Long depotId, Paginator paginator) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Transaction.class, "tx");
		if (memberId != null) {
			criteria.add(Restrictions.eq("tx.member.id", memberId));
		}
		if (from != null) {
			criteria.add(Restrictions.ge("tx.createDate", from));
		}
		if (to != null) {
			criteria.add(Restrictions.le("tx.createDate", to));
		}
		if (state != null) {
			criteria.add(Restrictions.eq("tx.state", state));
		}
		if (depotId != null) {
			criteria.add(Restrictions.eq("tx.depot.id", depotId));
		}

		criteria.addOrder(Order.desc("tx.id"));
		List<Transaction> transactions = template.findByCriteria(criteria, paginator);

		return transactions;
	}

	// DI

	private HibernateTemplate template;

	@Autowired
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

}
