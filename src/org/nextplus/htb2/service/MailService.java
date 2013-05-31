package org.nextplus.htb2.service;

import java.util.List;

import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.Mail;
import org.springframework.transaction.annotation.Transactional;

public interface MailService {

	@Transactional(rollbackFor = Exception.class)
	Long createMail(Mail mail) throws DuplicateException;

	@Transactional(rollbackFor = Exception.class)
	void updateMail(Mail mail) throws DuplicateException;

	@Transactional
	Mail deleteMail(Long mailId);

	@Transactional(readOnly = true)
	Mail getMail(Long mailId);

	@Transactional(readOnly = true)
	List<Mail> getMails(Paginator paginator);
	
	@Transactional(readOnly = true)
	List<Mail> getMails(Long memberId, Paginator paginator);

}
