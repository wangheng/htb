package org.nextplus.htb2.bind;

import java.beans.PropertyEditorSupport;

import org.nextplus.commons.Dao;
import org.nextplus.commons.Domain;
import org.springframework.util.StringUtils;

public class DomainEditor<T extends Domain> extends PropertyEditorSupport {

	private Dao<T> dao;

	public DomainEditor(Dao<T> dao) {
		super();
		this.dao = dao;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			Long id = Long.parseLong(text);
			T e = dao.get(id);
			setValue(e);
		} else {
			super.setValue(null);
		}
	}

	@Override
	public String getAsText() {
		Object o = getValue();
		if (o != null && Domain.class.isAssignableFrom(o.getClass())) {
			Domain e = (Domain) o;
			return e.getId().toString();
		}
		return null;
	}

}