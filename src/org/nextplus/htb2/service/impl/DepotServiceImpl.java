package org.nextplus.htb2.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.Depot;
import org.nextplus.htb2.hibernate.HibernateTemplate;
import org.nextplus.htb2.service.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("depotService")
public class DepotServiceImpl implements DepotService {

	@Override
	public Long createDepot(Depot depot) throws DuplicateException {
		Assert.notNull(depot);
		Assert.isTrue(depot.isNew());

		Depot p = getDepot(depot.getName());
		if (p != null) {
			throw new DuplicateException(
					String.format("[%s]已经存在", depot.getName()));
		}

		depot.setCreateDate();
		Long depotId = (Long) template.save(depot);

		return depotId;
	}

	@Override
	public void updateDepot(Depot depot) throws DuplicateException {
		Assert.notNull(depot);
		Assert.isTrue(!depot.isNew());

		Depot p = getDepot(depot.getName());
		if (p != null && !p.equals(depot)) {
			throw new DuplicateException(
					String.format("[%s]已经存在", depot.getName()));
		}

		depot.setUpdateDate();
		template.merge(depot);
	}

	@Override
	public Depot deleteDepot(Long depotId) {
		Assert.notNull(depotId);

		Depot depot = getDepot(depotId);
		if (depot != null && depot.isDeletable()) {
			template.delete(depot);
		}
		return depot;
	}

	@Override
	public Depot getDepot(Long depotId) {
		Assert.notNull(depotId);

		Depot depot = template.get(Depot.class, depotId);

		return depot;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Depot getDepot(String depotName) {
		Assert.hasText(depotName);

		DetachedCriteria criteria = DetachedCriteria.forClass(Depot.class, "c");
		criteria.add(Restrictions.eq("c.name", depotName));

		List<Depot> depots = template.findByCriteria(criteria);
		if (depots.isEmpty()) {
			return null;
		}

		return depots.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Depot> getDepots(Paginator paginator) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Depot.class, "c");
		List<Depot> depots = template.findByCriteria(criteria, paginator);

		return depots;
	}

	// DI

	private HibernateTemplate template;

	@Autowired
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

}
