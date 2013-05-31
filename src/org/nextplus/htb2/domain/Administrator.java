package org.nextplus.htb2.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "administrator")
public class Administrator extends User {

	private static final long serialVersionUID = 8492108715368180865L;

	public Administrator() {
		super();
	}

	public Administrator(Long id, String username, String password,
			String fullname) {
		super(id, username, password, fullname);
	}

	// Domain logics;

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	@Transient
	public boolean isDeletable() {
		if (getUsername() != null && getUsername().equals("admin")) {
			return false;
		}
		return super.isDeletable();
	}

	@Override
	@Transient
	public List<Role> getRoles() {
		return Arrays.asList(Role.ROLE_ADMINISTRATOR);
	}

}
