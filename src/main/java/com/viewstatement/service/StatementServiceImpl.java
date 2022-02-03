package com.viewstatement.service;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.viewstatement.exception.AccountIdNotFoundException;
import com.viewstatement.exception.ValidationException;
import com.viewstatement.model.AccountStatementDetail;
import com.viewstatement.model.Accounts;
import com.viewstatement.model.ResponseStatus;
import com.viewstatement.model.Statement;
import com.viewstatement.model.StatementSearchCriteria;
import com.viewstatement.repo.AccountRepository;
import com.viewstatement.repo.ViewstatementRepository;
import com.viewstatement.utils.Utils;

@Service
public class StatementServiceImpl implements StatementService {
	
	@Autowired
	private JdbcTemplate template;
	
	
	@Autowired
	ViewstatementRepository viewStatementRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	Utils utils;
	ResponseStatus responseStatus = new ResponseStatus();
	

	@Override
	public ResponseStatus getStatement(StatementSearchCriteria statementSearchCriteria) throws ParseException, ValidationException, AccountIdNotFoundException  {
		 Date fromDate = null ;
		 Date toDate = null ;
		 Long fromAmt = null ;
		 Long toAmt = null ;
		List<AccountStatementDetail> accountStatementDetail = new ArrayList<>();
		utils.checkIfInvalidParameter(statementSearchCriteria);
		
		List<Statement> statement = viewStatementRepository.findByAccountId(statementSearchCriteria.getAccountId());
		
		Optional<Accounts> accounts = accountRepository.findById(statementSearchCriteria.getAccountId());
		
		utils.isAccountPresent(statementSearchCriteria, accounts);
		
		if (StringUtils.isNotEmpty(statementSearchCriteria.getFromdate())) {
			fromDate = utils.convertDateToString(statementSearchCriteria.getFromdate());
		}
		if (StringUtils.isNotEmpty(statementSearchCriteria.getTodate())) {
			toDate = utils.convertDateToString(statementSearchCriteria.getTodate());
		}
		if (StringUtils.isNotEmpty(statementSearchCriteria.getFromAmt())) {
			fromAmt = Long.parseLong(statementSearchCriteria.getFromAmt());
		}
		if (StringUtils.isNotEmpty(statementSearchCriteria.getToAmt())) {
			toAmt = Long.parseLong(statementSearchCriteria.getToAmt());
		}
		
		
		
		datefilterForViewStatement(statement,fromDate,toDate,fromAmt,toAmt,accountStatementDetail,accounts.isPresent() ? accounts.get() : new Accounts());
		
		
		responseStatus.setAccountStatementDetailList(accountStatementDetail);
		responseStatus.setStatus("Success");
		responseStatus.setResponseMessage("Retrieved Statement for given Criteria");
		
		return responseStatus;
	}



	private List<AccountStatementDetail> datefilterForViewStatement(List<Statement> statement,Date fromDate,Date toDate,Long fromAmt,Long toAmt, List<AccountStatementDetail> accountStatementDetail,Accounts accounts) {
		statement.stream().forEach(statement1 -> {
			try {
				
				if ((fromDate!=null || toDate!=null) && (fromAmt!=null || toAmt!=null)){
					 utils.compareDateAndAmountToViewStatementData(statement1, fromDate,toDate,fromAmt,toAmt, accountStatementDetail,accounts, statement1);
				}
				else if(StringUtils.isNotEmpty(statement1.getDateField()) && (fromDate!=null || toDate!=null) && (fromAmt==null || toAmt==null))
				{
					utils.compareDateToViewStatementData(statement1, fromDate,toDate, accountStatementDetail,accounts, statement1);
				}else if( (fromDate==null || toDate==null) && (fromAmt!=null || toAmt!=null))
				{
					utils.compareAmtToViewStatementData(statement1.getAmount(),fromAmt,toAmt, accountStatementDetail,accounts, statement1);
				}
				else
				{
					 utils.compareUserViewStatementData(statement1, accountStatementDetail,accounts, statement1);
						
				}
			} catch (ParseException | NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		});
		return accountStatementDetail;
	}


	@Override
	public ResponseStatus getStatementByUser(StatementSearchCriteria statementSearchCriteria)
			throws ParseException, ValidationException, AccountIdNotFoundException {
		List<AccountStatementDetail> accountStatementDetail = new ArrayList<>();
		
		List<Statement> statement = viewStatementRepository.findByAccountId(statementSearchCriteria.getAccountId());
		
		Optional<Accounts> accounts = accountRepository.findById(statementSearchCriteria.getAccountId());
		
		utils.isAccountPresent(statementSearchCriteria, accounts);
		
		datefilterForViewStatementByUser(statement,accountStatementDetail,accounts.isPresent() ? accounts.get() : new Accounts());
		 
		responseStatus.setAccountStatementDetailList(accountStatementDetail);
		responseStatus.setStatus("Success");
		responseStatus.setResponseMessage("Retrieved Statement for given Criteria");
		
		return responseStatus;
	}
	

	private List<AccountStatementDetail> datefilterForViewStatementByUser(List<Statement> statement,List<AccountStatementDetail> accountStatementDetail,Accounts accounts) {
		statement.stream().forEach(statement1 -> {
			try {
					 utils.compareUserViewStatementData(statement1, accountStatementDetail,accounts, statement1);
				
			} catch (ParseException | NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		});
		return accountStatementDetail;
	}

	
	
	

}
