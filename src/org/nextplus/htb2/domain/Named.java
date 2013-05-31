package org.nextplus.htb2.domain;

import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.NotBlank;

@MappedSuperclass
public class Named extends Domain {

	private static final long serialVersionUID = 39477085939110032L;

	private String name;
	private String description;

	public Named() {
		super();
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
