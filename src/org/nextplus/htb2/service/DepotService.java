package org.nextplus.htb2.service;

import java.util.List;

import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.Depot;
import org.springframework.transaction.annotation.Transactional;

public interface DepotService {

	@Transactional(rollbackFor = Exception.class)
	Long createDepot(Depot depot) throws DuplicateException;

	@Transactional(rollbackFor = Exception.class)
	void updateDepot(Depot depot) throws DuplicateException;

	@Transactional
	Depot deleteDepot(Long depotId);

	@Transactional(readOnly = true)
	Depot getDepot(Long depotId);

	@Transactional(readOnly = true)
	Depot getDepot(String depotName);

	@Transactional(readOnly = true)
	List<Depot> getDepots(Paginator paginator);

}
