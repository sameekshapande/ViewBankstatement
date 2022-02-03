package com.viewstatement.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viewstatement.model.ResponseStatus;
import com.viewstatement.model.StatementSearchCriteria;
import com.viewstatement.service.StatementService;

@TestInstance(Lifecycle.PER_CLASS)
 class AccountStatementControllerTest {
    @InjectMocks
	private AccountStatementController accountStatementController;
    
    @Mock
    private StatementService statementService;
	
    private MockMvc mockMvc;
    
    private static final String statementByAccId = "/api/rest/accountstatement/statementByAccId";
    
    @BeforeAll
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    	this.mockMvc = MockMvcBuilders.standaloneSetup(accountStatementController).build();
    }
    
    @Test
	 void shouldViewStatementByAccID() throws Exception {
    	ObjectMapper objMapper = new ObjectMapper();
		ResponseStatus responseStatus = new ResponseStatus();
		Long l =3L;
		StatementSearchCriteria statementSearchCriteria = new StatementSearchCriteria("02.02.2020", "04.02.2020", "",
				"", "", l);
		Mockito.doReturn(responseStatus).when(statementService).getStatement(statementSearchCriteria);
		this.mockMvc.perform(MockMvcRequestBuilders.post(statementByAccId).
				contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(objMapper.writeValueAsBytes(statementSearchCriteria)));
	}
    
    @Test
	 void shouldViewStatementByUser() throws Exception {
    	ObjectMapper objMapper = new ObjectMapper();
		ResponseStatus responseStatus = new ResponseStatus();
		Long l =3L;
		StatementSearchCriteria statementSearchCriteria = new StatementSearchCriteria("", "", "",
				"", "", l);
		Mockito.doReturn(responseStatus).when(statementService).getStatementByUser(statementSearchCriteria);
		this.mockMvc.perform(MockMvcRequestBuilders.post(statementByAccId).
				contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(objMapper.writeValueAsBytes(statementSearchCriteria)));
	}
    
    @Test
   	 void shouldViewStatementBy() throws Exception {
       	ObjectMapper objMapper = new ObjectMapper();
   		ResponseStatus responseStatus = new ResponseStatus();
   		Long l =3L;
   		StatementSearchCriteria statementSearchCriteria = new StatementSearchCriteria("05.02.2020", "04.02.2020", "",
   				"", "", l);
   		Mockito.doReturn(responseStatus).when(statementService).getStatement(statementSearchCriteria);
   		this.mockMvc.perform(MockMvcRequestBuilders.post(statementByAccId).
   				contentType(MediaType.APPLICATION_JSON_VALUE)
   		.content(objMapper.writeValueAsBytes(statementSearchCriteria)));
   	}
    
    @Test
   	 void shouldViewStatementByID() throws Exception {
       	ObjectMapper objMapper = new ObjectMapper();
   		ResponseStatus responseStatus = new ResponseStatus();
   		Long l =3L;
   		StatementSearchCriteria statementSearchCriteria = new StatementSearchCriteria("", "", "90",
   				"0", "", l);
   		Mockito.doReturn(responseStatus).when(statementService).getStatement(statementSearchCriteria);
   		this.mockMvc.perform(MockMvcRequestBuilders.post(statementByAccId).
   				contentType(MediaType.APPLICATION_JSON_VALUE)
   		.content(objMapper.writeValueAsBytes(statementSearchCriteria)));
   	}
    
    @Test
   	 void shouldViewStatementAmt() throws Exception {
       	ObjectMapper objMapper = new ObjectMapper();
   		ResponseStatus responseStatus = new ResponseStatus();
   		Long l =3L;
   		StatementSearchCriteria statementSearchCriteria = new StatementSearchCriteria("03.02.2020", "05.02.2020", "0",
   				"90", "", l);
   		Mockito.doReturn(responseStatus).when(statementService).getStatement(statementSearchCriteria);
   		this.mockMvc.perform(MockMvcRequestBuilders.post(statementByAccId).
   				contentType(MediaType.APPLICATION_JSON_VALUE)
   		.content(objMapper.writeValueAsBytes(statementSearchCriteria)));
   	}
    
    @Test
   	void shouldViewStatementAccountIdNull() throws Exception {
       	ObjectMapper objMapper = new ObjectMapper();
   		ResponseStatus responseStatus = new ResponseStatus();
   		Long l =3L;
   		StatementSearchCriteria statementSearchCriteria = new StatementSearchCriteria("", "", "0",
   				"", "", 0L);
   		Mockito.doReturn(responseStatus).when(statementService).getStatement(statementSearchCriteria);
   		this.mockMvc.perform(MockMvcRequestBuilders.post(statementByAccId).
   				contentType(MediaType.APPLICATION_JSON_VALUE)
   		.content(objMapper.writeValueAsBytes(statementSearchCriteria)));
   	}
    
}
