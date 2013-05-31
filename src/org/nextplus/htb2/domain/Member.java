package org.nextplus.htb2.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "member")
public class Member extends User {

	private static final long serialVersionUID = 3972166046566440526L;

	private String mail;
	private String cellphone;
	private List<Transaction> transactions;
	private List<Mail> mails;

	public Member() {
		super();
	}

	// Domain logics

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	@Transient
	public boolean isDeletable() {
		if(getTransactions().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transient
	public List<Role> getRoles() {
		return Arrays.asList(Role.ROLE_MEMBER);
	}

	// Mutators

	@Email
	@NotBlank
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@NotBlank
	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@OneToMany(mappedBy="member")
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@OneToMany(mappedBy="member")
	@LazyCollection(LazyCollectionOption.EXTRA)
	public List<Mail> getMails() {
		return mails;
	}

	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}

}
