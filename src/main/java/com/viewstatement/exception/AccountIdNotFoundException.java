package com.viewstatement.exception;

public class AccountIdNotFoundException extends Exception {
	public AccountIdNotFoundException(String errorMessage)
	{
		super(errorMessage);
	}
	}