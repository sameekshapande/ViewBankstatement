package com.viewstatement.service;

import java.text.ParseException;

import com.viewstatement.exception.AccountIdNotFoundException;
import com.viewstatement.exception.ValidationException;
import com.viewstatement.model.ResponseStatus;
import com.viewstatement.model.StatementSearchCriteria;

public interface StatementService {

	public ResponseStatus getStatement(StatementSearchCriteria statementSearchCriteria) throws 
	ParseException,ValidationException,AccountIdNotFoundException;

	public ResponseStatus getStatementByUser(StatementSearchCriteria statementSearchCriteria) throws 
	ParseException,ValidationException,AccountIdNotFoundException;
}
