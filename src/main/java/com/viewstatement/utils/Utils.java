package com.viewstatement.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.viewstatement.exception.AccountIdNotFoundException;
import com.viewstatement.exception.ValidationException;
import com.viewstatement.model.AccountStatementDetail;
import com.viewstatement.model.Accounts;
import com.viewstatement.model.Statement;
import com.viewstatement.model.StatementSearchCriteria;

@Service
public class Utils {
	
	private String dateFormat = "dd.MM.yyyy";

	public void checkIfInvalidParameter(StatementSearchCriteria statementSearchCriteria)
			throws ValidationException, ParseException {
		datevalidation(statementSearchCriteria);
		amountvalidation(statementSearchCriteria);

	}

	public void datevalidation(StatementSearchCriteria statementSearchCriteria)
			throws ValidationException, ParseException {
		
		if (StringUtils.isNotEmpty(statementSearchCriteria.getFromdate())) {
			try {
				  new SimpleDateFormat(dateFormat).parse(statementSearchCriteria.getFromdate());
			}

			catch (Exception e) {
				throw new ValidationException("Please enter the date in dd.MM.yyyy format");
			}
		}
		if (StringUtils.isNotEmpty(statementSearchCriteria.getTodate())) {
			try {
				 new SimpleDateFormat(dateFormat).parse(statementSearchCriteria.getTodate());
			}

			catch (Exception e) {
				throw new ValidationException("Please enter the date in dd.MM.yyyy format");
			}
		}
		if (StringUtils.isNotEmpty(statementSearchCriteria.getFromdate())
				&& !StringUtils.isNotEmpty(statementSearchCriteria.getTodate())) {
			throw new ValidationException("Please enter both From and To date ");
		}
		if (!StringUtils.isNotEmpty(statementSearchCriteria.getFromdate())
				&& StringUtils.isNotEmpty(statementSearchCriteria.getTodate())) {
			throw new ValidationException("Please enter both From and To date ");
		}
		if (StringUtils.isNotEmpty(statementSearchCriteria.getFromdate())
				&& StringUtils.isNotEmpty(statementSearchCriteria.getTodate()) && 
				convertDateToString(statementSearchCriteria.getFromdate())
					.compareTo(convertDateToString(statementSearchCriteria.getTodate())) > 0) {
				throw new ValidationException("Please  From  date should be less than to date  ");
		}
	}

	public void amountvalidation(StatementSearchCriteria statementSearchCriteria) throws ValidationException {

		if (StringUtils.isNotEmpty(statementSearchCriteria.getToAmt())
				&& Long.parseLong(statementSearchCriteria.getToAmt()) <= 0) {
			throw new ValidationException("To Amount Entered is Invalid");
		} else if (StringUtils.isNotEmpty(statementSearchCriteria.getFromAmt())
				&& StringUtils.isNotEmpty(statementSearchCriteria.getToAmt())  && 
		Long.parseLong(statementSearchCriteria.getFromAmt()) >= Long
					.parseLong(statementSearchCriteria.getToAmt())) {
				throw new ValidationException("From Amount should be less than To Amount");
		}
	}

	public Date convertDateToString(String date) throws ParseException {
		Date isodate = null;
		isodate = 	new SimpleDateFormat(dateFormat).parse(date);
		return isodate;
	}



	
	public List<AccountStatementDetail> compareDateAndAmountToViewStatementData(Statement statement,Date fromdate,Date toDate,Long fromAmt,Long toAmt,List<AccountStatementDetail> accountStatementDetail,Accounts accounts,Statement statement1) throws ParseException, NoSuchAlgorithmException {
		AccountStatementDetail accountDetail = new AccountStatementDetail();
		Date isodate = new SimpleDateFormat(dateFormat).parse(statement.getDateField());
		
		Double amountDouble = Double.parseDouble(statement.getAmount());
		Long amountLong = Math.round(amountDouble);
		
		if(isodate.compareTo(fromdate)>=0 && isodate.compareTo(toDate)<=0 && amountLong >= fromAmt && amountLong <= toAmt) 
		{
			String accNumber= accountNumberHashing(accounts);
			accountDetail.setAccountNumber(accNumber);
			accountDetail.setAccountType(accounts.getAccountType());
			accountDetail.setDatefield(statement1.getDateField());
			accountDetail.setAmount(statement1.getAmount());
			accountStatementDetail.add(accountDetail);
		}
		return accountStatementDetail;

	}
	
	
	public List<AccountStatementDetail> compareAmtToViewStatementData(String amount, Long fromAmt, Long toAmt,
			List<AccountStatementDetail> accountStatementDetail, Accounts accounts, Statement statement1)
			throws  NoSuchAlgorithmException {
		AccountStatementDetail accountDetail = new AccountStatementDetail();
		Double amountDouble = Double.parseDouble(amount);
		Long amountLong = Math.round(amountDouble);
		if (amountLong >= fromAmt && amountLong <= toAmt) {
			String accNumber= accountNumberHashing(accounts);
			accountDetail.setAccountNumber(accNumber);
			accountDetail.setAccountType(accounts.getAccountType());
			accountDetail.setDatefield(statement1.getDateField());
			accountDetail.setAmount(statement1.getAmount());
			accountStatementDetail.add(accountDetail);
		}

		return accountStatementDetail;

	}
	
	public List<AccountStatementDetail> compareDateToViewStatementData(Statement statement,Date fromdate,Date toDate,List<AccountStatementDetail> accountStatementDetail,Accounts accounts,Statement statement1) throws ParseException, NoSuchAlgorithmException {
		AccountStatementDetail accountDetail = new AccountStatementDetail();
		Date isodate = new SimpleDateFormat(dateFormat).parse(statement.getDateField());

		if(isodate.compareTo(fromdate)>=0 && isodate.compareTo(toDate)<=0 ) 
		{
			String accNumber= accountNumberHashing(accounts);
			accountDetail.setAccountNumber(accNumber);
			accountDetail.setAccountType(accounts.getAccountType());
			accountDetail.setDatefield(statement1.getDateField());
			accountDetail.setAmount(statement1.getAmount());
			accountStatementDetail.add(accountDetail);
		}
		return accountStatementDetail;

	}
	
	public List<AccountStatementDetail>  compareUserViewStatementData(Statement statement,List<AccountStatementDetail> accountStatementDetail,Accounts accounts,Statement statement1) throws ParseException, NoSuchAlgorithmException {
		AccountStatementDetail accountDetail = new AccountStatementDetail();
		Date accntdate = new SimpleDateFormat(dateFormat).parse(statement.getDateField());
	
		Date today = new Date();
		SimpleDateFormat todaydate = new SimpleDateFormat(dateFormat);
		todaydate.format(today);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(today); 
		c.add(Calendar.MONTH, -30);
		Date afterThreeMonths = c.getTime();
		
		if(accntdate.compareTo(today)<=0 && accntdate.compareTo(afterThreeMonths)>=0 ) 
		{
			String accNumber= accountNumberHashing(accounts);
			accountDetail.setAccountNumber(accNumber);
			accountDetail.setAccountType(accounts.getAccountType());
			accountDetail.setDatefield(statement1.getDateField());
			accountDetail.setAmount(statement1.getAmount());
			accountStatementDetail.add(accountDetail);
		}
		
		return accountStatementDetail;
		
	}

	private String accountNumberHashing(Accounts accounts) throws NoSuchAlgorithmException {
		String hashedAccountNumber = null ;
		final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
		final byte[] hashbytes = digest.digest(
				accounts.getAccountNumber().getBytes(StandardCharsets.UTF_8));
		 hashedAccountNumber = bytesToHex(hashbytes);
		return hashedAccountNumber;
	}

	private static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}


	public void isAccountPresent(StatementSearchCriteria statementSearchCriteria, Optional<Accounts> accounts)
			throws AccountIdNotFoundException {
		if(!accounts.isPresent()) {
			throw new AccountIdNotFoundException("Account ID "+statementSearchCriteria.getAccountId() +" not found in the system ");
		}
	}

}
