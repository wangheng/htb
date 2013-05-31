package org.nextplus.htb2.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "depot")
public class Depot extends Named {

	private static final long serialVersionUID = -5803217075976984838L;

	private String location;
	private List<Transaction> transactions;
	
	public Depot() {
		super();
	}

	@NotBlank
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@OneToMany(mappedBy="depot")
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	@Transient
	@JsonIgnore
	public boolean isDeletable() {
		if(getTransactions().isEmpty()) {
			return true;
		}
		return false;
	}

}
