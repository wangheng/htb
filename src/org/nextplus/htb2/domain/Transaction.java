package org.nextplus.htb2.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "transaction")
public class Transaction extends Named {

	private static final long serialVersionUID = 3114283807680659084L;

	private Member member;
	private String location;
	private State state;
	// 管理员确认的时候填写
	private Depot depot;
	private BigDecimal expense;
	private Date cfmDate;
	private String cfmNotes;
	// 物流
	private LogisticsCorp logisticsCorp;
	private String logisticsNotes;
	private Date logisticsDate;

	public Transaction() {
		super();
	}

	@ManyToOne
	@NotNull
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@NotBlank
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@ManyToOne
	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	@Min(0)
	public BigDecimal getExpense() {
		return expense;
	}

	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}

	public Date getCfmDate() {
		return cfmDate;
	}

	public void setCfmDate(Date cfmDate) {
		this.cfmDate = cfmDate;
	}

	public String getCfmNotes() {
		return cfmNotes;
	}

	public void setCfmNotes(String cfmNotes) {
		this.cfmNotes = cfmNotes;
	}

	@ManyToOne
	public LogisticsCorp getLogisticsCorp() {
		return logisticsCorp;
	}

	public void setLogisticsCorp(LogisticsCorp logisticsCorp) {
		this.logisticsCorp = logisticsCorp;
	}

	public String getLogisticsNotes() {
		return logisticsNotes;
	}

	public void setLogisticsNotes(String logisticsNotes) {
		this.logisticsNotes = logisticsNotes;
	}

	public Date getLogisticsDate() {
		return logisticsDate;
	}

	public void setLogisticsDate(Date logisticsDate) {
		this.logisticsDate = logisticsDate;
	}

	/**
	 * 状态
	 *
	 * @author Harris
	 *
	 */
	public enum State {

		PENDING("待处理"),
		CHECKED_IN("已入仓"),
		RECEIPT("已发货"),
		SHIPPED("已收货");

		private String name;

		private State(String name) {
			this.name = name;
		}

		public String getEx() {
			return name;
		}

	}
	
	@Transient
	@JsonIgnore
	public boolean isUpdatable() {
		return true;
	}
	
	@Transient
	@JsonIgnore
	public boolean isDeletable() {
		if(getState().equals(State.PENDING)) {
			return true;
		}
		return false;
	}

}
