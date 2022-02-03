package com.viewstatement.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.viewstatement.exception.ValidationException;
import com.viewstatement.model.ResponseStatus;
import com.viewstatement.model.StatementSearchCriteria;
import com.viewstatement.repo.AccountRepository;
import com.viewstatement.repo.ViewstatementRepository;
import com.viewstatement.utils.Utils;

public class StatementServiceImplTest {

	@InjectMocks
	StatementServiceImpl statementServiceImpl = new StatementServiceImpl();
	
	@Mock
	private ViewstatementRepository  viewstatementRepository ;
	
	@Mock
	private AccountRepository accountRepository ;
	
	@Mock
	Utils utils;
	
	 @BeforeAll
	    public void setUp() {
	    	MockitoAnnotations.initMocks(this);
	    	viewstatementRepository = Mockito.mock(ViewstatementRepository.class);
	    }
	 
	 @Test(expected = ValidationException.class)
	 public void shouldValidateForAmt () throws Exception
	 {
		 ResponseStatus responseStatus = new  ResponseStatus();
		 StatementSearchCriteria statementSearchCriteria = new StatementSearchCriteria("04.02.2020", "01.02.2020", "",
					"", "", 1l);
		 Mockito.doReturn(responseStatus).when(utils).checkIfInvalidParameter(statementSearchCriteria);
		 Assert.assertNull(responseStatus);
		 }
}
