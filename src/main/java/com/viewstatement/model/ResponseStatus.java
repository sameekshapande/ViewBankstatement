package com.viewstatement.model;

import java.util.List;

public class ResponseStatus {
private String status;
private String responseMessage;
private List<AccountStatementDetail> accountStatementDetailList ;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getResponseMessage() {
	return responseMessage;
}
public void setResponseMessage(String responseMessage) {
	this.responseMessage = responseMessage;
}
public List<AccountStatementDetail> getAccountStatementDetailList() {
	return accountStatementDetailList;
}
public void setAccountStatementDetailList(List<AccountStatementDetail> accountStatementDetailList) {
	this.accountStatementDetailList = accountStatementDetailList;
}

}
