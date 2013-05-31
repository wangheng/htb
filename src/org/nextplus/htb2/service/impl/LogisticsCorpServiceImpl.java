package org.nextplus.htb2.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.LogisticsCorp;
import org.nextplus.htb2.hibernate.HibernateTemplate;
import org.nextplus.htb2.service.LogisticsCorpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("logisticsCorpService")
public class LogisticsCorpServiceImpl implements LogisticsCorpService {

	@Override
	public Long createLogisticsCorp(LogisticsCorp logisticsCorp) throws DuplicateException {
		Assert.notNull(logisticsCorp);
		Assert.isTrue(logisticsCorp.isNew());

		LogisticsCorp p = getLogisticsCorp(logisticsCorp.getName());
		if (p != null) {
			throw new DuplicateException(
					String.format("[%s]已经存在", logisticsCorp.getName()));
		}

		logisticsCorp.setCreateDate();
		Long logisticsCorpId = (Long) template.save(logisticsCorp);

		return logisticsCorpId;
	}

	@Override
	public void updateLogisticsCorp(LogisticsCorp logisticsCorp) throws DuplicateException {
		Assert.notNull(logisticsCorp);
		Assert.isTrue(!logisticsCorp.isNew());

		LogisticsCorp p = getLogisticsCorp(logisticsCorp.getName());
		if (p != null && !p.equals(logisticsCorp)) {
			throw new DuplicateException(
					String.format("[%s]已经存在", logisticsCorp.getName()));
		}

		logisticsCorp.setUpdateDate();
		template.merge(logisticsCorp);
	}

	@Override
	public LogisticsCorp deleteLogisticsCorp(Long logisticsCorpId) {
		Assert.notNull(logisticsCorpId);

		LogisticsCorp logisticsCorp = getLogisticsCorp(logisticsCorpId);
		if (logisticsCorp != null && logisticsCorp.isDeletable()) {
			template.delete(logisticsCorp);
		}
		return logisticsCorp;
	}

	@Override
	public LogisticsCorp getLogisticsCorp(Long logisticsCorpId) {
		Assert.notNull(logisticsCorpId);

		LogisticsCorp logisticsCorp = template.get(LogisticsCorp.class, logisticsCorpId);

		return logisticsCorp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LogisticsCorp getLogisticsCorp(String logisticsCorpName) {
		Assert.hasText(logisticsCorpName);

		DetachedCriteria criteria = DetachedCriteria.forClass(LogisticsCorp.class, "c");
		criteria.add(Restrictions.eq("c.name", logisticsCorpName));

		List<LogisticsCorp> logisticsCorps = template.findByCriteria(criteria);
		if (logisticsCorps.isEmpty()) {
			return null;
		}

		return logisticsCorps.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LogisticsCorp> getLogisticsCorps(Paginator paginator) {
		DetachedCriteria criteria = DetachedCriteria.forClass(LogisticsCorp.class, "c");
		List<LogisticsCorp> logisticsCorps = template.findByCriteria(criteria, paginator);

		return logisticsCorps;
	}

	// DI

	private HibernateTemplate template;

	@Autowired
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

}
