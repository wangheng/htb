package org.nextplus.htb2.service;

import java.util.List;

import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.LogisticsCorp;
import org.springframework.transaction.annotation.Transactional;

public interface LogisticsCorpService {

	@Transactional(rollbackFor = Exception.class)
	Long createLogisticsCorp(LogisticsCorp logisticsCorp) throws DuplicateException;

	@Transactional(rollbackFor = Exception.class)
	void updateLogisticsCorp(LogisticsCorp logisticsCorp) throws DuplicateException;

	@Transactional
	LogisticsCorp deleteLogisticsCorp(Long logisticsCorpId);

	@Transactional(readOnly = true)
	LogisticsCorp getLogisticsCorp(Long logisticsCorpId);

	@Transactional(readOnly = true)
	LogisticsCorp getLogisticsCorp(String logisticsCorpName);

	@Transactional(readOnly = true)
	List<LogisticsCorp> getLogisticsCorps(Paginator paginator);

}
