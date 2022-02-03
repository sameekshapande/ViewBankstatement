package com.viewstatement.exception;

public class UserAccessDeniedException extends Exception{
public UserAccessDeniedException(String errorMessage)
{
	super(errorMessage);
}
}
