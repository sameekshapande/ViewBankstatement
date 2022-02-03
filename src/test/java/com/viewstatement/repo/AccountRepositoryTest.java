package com.viewstatement.repo;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.viewstatement.model.Accounts;
import com.viewstatement.model.Statement;
import com.viewstatement.service.StatementServiceImpl;

import junit.framework.Assert;

/*@RunWith(SpringRunner.class)
@DataJpaTest*/
@TestInstance(Lifecycle.PER_CLASS)
 class AccountRepositoryTest {
	
	@Autowired
	StatementServiceImpl  statementServiceImpl;
	
	@Mock
	AccountRepository accountRepository;
	
	 @BeforeAll
	    public void setUp() {
	    	MockitoAnnotations.initMocks(this);
	    	accountRepository = Mockito.mock(AccountRepository.class);
	    }
	
@Test
 void shouldSearchByThreeMonthList()
{
	
	
	Statement statement =  new Statement (4L,3L,"03.02.2020","330.455004587924");
	
	Optional<Accounts>  s = accountRepository.findById(3L);
	//Assert.assertNotNull(s);
}
}