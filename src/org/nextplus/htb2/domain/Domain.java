package org.nextplus.htb2.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Domain extends org.nextplus.commons.Domain {

	private static final long serialVersionUID = 3875866321231589212L;

	private Date createDate;
	private Date updateDate;

	public Domain() {
		super();
	}

	public Domain(Long id) {
		super(id);
	}

	// Domain logics.

	public void setCreateDate() {
		setCreateDate(new Date());
	}

	public void setUpdateDate() {
		setUpdateDate(new Date());
	}

//	@Transient
//	public String getCreateDateEx() {
//		return getDateEx(getCreateDate());
//	}

//	@Transient
//	public String getUpdateDateEx() {
//		return getDateEx(getUpdateDate());
//	}

//	@Transient
//	public String getDateEx(Date date) {
//		// return DateFormatUtils.formatEx_1(date);
//		long offset = System.currentTimeMillis() - getCreateDate().getTime();
//		return DateFormatUtils.explain(offset, true) + "前";
//	}

	/* 反射 */

//	@Transient
//	public Object getProp(String name) throws IllegalAccessException,
//			InvocationTargetException, NoSuchMethodException {
//		return PropertyUtils.getProperty(this, name);
//	}
//
//	@Transient
//	public void setProp(String name, Object value)
//			throws IllegalAccessException, InvocationTargetException,
//			NoSuchMethodException {
//		PropertyUtils.setProperty(this, name, value);
//	}

	// Mutators

	@Override
	public String toString() {
		return super.toString();
	}

	@Column(nullable = false, updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
