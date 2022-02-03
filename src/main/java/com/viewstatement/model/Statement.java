package com.viewstatement.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statement {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private Long accountId;
	private String datefield;
	private String amount;
	
	public Statement() {}

	public Statement(Long id, Long accountId, String dateField, String amount) {
		this.id = id;
		this.accountId = accountId;
		this.datefield = dateField;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getDateField() {
		return datefield;
	}

	public void setDateField(String dateField) {
		this.datefield = dateField;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Statement [id=" + id + ", accountId=" + accountId + ", dateField=" + datefield + ", amount=" + amount
				+ "]";
	}
	
}
