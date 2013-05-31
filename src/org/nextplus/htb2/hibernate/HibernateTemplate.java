package org.nextplus.htb2.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.nextplus.commons.Paginator;

public class HibernateTemplate extends org.nextplus.commons.HibernateTemplate {

	@SuppressWarnings("rawtypes")
	public List find(String queryString,  Paginator paginator, Object... values) {
		if (paginator != null) {
			return super.find(queryString, values, paginator);
		}
		return super.find(queryString, values);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findByCriteria(DetachedCriteria criteria, Paginator paginator) {
		if (paginator != null) {
			return super.findByCriteria(criteria, paginator);
		}
		return super.findByCriteria(criteria);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString, Object[] values, Paginator paginator) {
		if (paginator != null) {
			return super.find(queryString, values, paginator);
		}
		return super.find(queryString, values);
	}

}
