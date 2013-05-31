package org.nextplus.htb2.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "mail")
public class Mail extends Domain {

	private static final long serialVersionUID = 5702265368957158342L;

	private Member member;
	private String content;

	public Mail() {
		super();
	}

	public Mail(Member member, String content) {
		super();
		this.member = member;
		this.content = content;
	}
	@NotBlank
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
