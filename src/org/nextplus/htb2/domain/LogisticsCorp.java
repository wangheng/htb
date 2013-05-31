package org.nextplus.htb2.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "logistics_corp")
public class LogisticsCorp extends Named {

	private static final long serialVersionUID = -1728158484914288184L;
	
	@Transient
	@JsonIgnore
	public boolean isDeletable() {
		
		return false;
	}

}
