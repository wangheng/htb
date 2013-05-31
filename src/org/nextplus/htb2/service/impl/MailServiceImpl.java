package org.nextplus.htb2.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.nextplus.commons.DuplicateException;
import org.nextplus.commons.Paginator;
import org.nextplus.htb2.domain.Mail;
import org.nextplus.htb2.hibernate.HibernateTemplate;
import org.nextplus.htb2.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Override
	public Long createMail(Mail mail) throws DuplicateException {
		Assert.notNull(mail);
		Assert.isTrue(mail.isNew());


		mail.setCreateDate();
		Long mailId = (Long) template.save(mail);

		return mailId;
	}

	@Override
	public void updateMail(Mail mail) throws DuplicateException {
		Assert.notNull(mail);
		Assert.isTrue(!mail.isNew());

		Mail p = getMail(mail.getId());
		if (p != null && !p.equals(mail)) {
			throw new DuplicateException(
					String.format("[%s]已经存在", mail.getContent()));
		}

		mail.setUpdateDate();
		template.merge(mail);
	}

	@Override
	public Mail deleteMail(Long mailId) {
		Assert.notNull(mailId);

		Mail mail = getMail(mailId);
		if (mail != null && mail.isDeletable()) {
			template.delete(mail);
		}
		return mail;
	}

	@Override
	public Mail getMail(Long mailId) {
		Assert.notNull(mailId);

		Mail mail = template.get(Mail.class, mailId);

		return mail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mail> getMails(Paginator paginator) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Mail.class, "m");
		List<Mail> mails = template.findByCriteria(criteria, paginator);

		return mails;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mail> getMails(Long memberId, Paginator paginator) {
		Assert.notNull(memberId);
		DetachedCriteria criteria = DetachedCriteria.forClass(Mail.class, "m");
		criteria.add(Restrictions.eq("m.member.id", memberId));
		
		List<Mail> mails = null;
		if(paginator == null) {
			mails = template.findByCriteria(criteria);
		} else {
			mails = template.findByCriteria(criteria, paginator);
		}
		return mails;
	}

	// DI

	private HibernateTemplate template;

	@Autowired
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

}
