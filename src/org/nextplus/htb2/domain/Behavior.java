package org.nextplus.htb2.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "behavior")
@Inheritance(strategy = InheritanceType.JOINED)
public class Behavior extends Domain {

	private static final long serialVersionUID = -7062576007928812309L;

	private User user;

	public Behavior() {
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
		return super.isDeletable();
	}

	// Mutators

	@ManyToOne
	@NotNull
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
