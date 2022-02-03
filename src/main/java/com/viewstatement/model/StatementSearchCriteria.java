package com.viewstatement.model;

public class StatementSearchCriteria {
//	date from date to id amt to and amt from and role
	private String fromdate;
	private String todate;
	private String fromAmt;
	private String toAmt;
	private String role;
	private Long accountId;
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getFromAmt() {
		return fromAmt;
	}
	public void setFromAmt(String fromAmt) {
		this.fromAmt = fromAmt;
	}
	public String getToAmt() {
		return toAmt;
	}
	public void setToAmt(String toAmt) {
		this.toAmt = toAmt;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public StatementSearchCriteria(String fromdate, String todate, String fromAmt, String toAmt, String role,
			Long accountId) {
		super();
		this.fromdate = fromdate;
		this.todate = todate;
		this.fromAmt = fromAmt;
		this.toAmt = toAmt;
		this.role = role;
		this.accountId = accountId;
	}
	
	public StatementSearchCriteria() {
		
	}
}
