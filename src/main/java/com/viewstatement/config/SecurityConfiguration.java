package com.viewstatement.config;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	private static final String ADMIN ="ADMIN";
	private static final String CONSOLE  = "/console/**";
	private static final String CONTENT = "/content/**";
	private static final String HEALTH = "/actuator/**"; 
	private static final String HTML_JS = "/**/*.{js,html,css}"; 
	private static final String HTML_JS_APP = "/app/**/*.{js,html,css}"; 
	private static final String MANAGEMENT = "/management/**"; 
	private static final String SWAGGER_UI = "/swagger-ui/**";
	private static final String SWAGGER_UI_INDEX = "/swagger-ui/index.html";
	
	@Bean(name = "ignoreUrlList")
	public List<String> getIgnoreUrlList(){
		List<String> urlList = new ArrayList<>();
		urlList.add(SWAGGER_UI);
		urlList.add(CONSOLE);
		urlList.add(HEALTH);
		urlList.add(HTML_JS);
		urlList.add(HTML_JS_APP);
		urlList.add(MANAGEMENT);
		return urlList;
	}
	
	@Override
	public void configure (WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers(HTML_JS)
		.antMatchers(SWAGGER_UI_INDEX)
		.antMatchers(CONTENT)
		.antMatchers(HTML_JS_APP)
		.antMatchers(MANAGEMENT);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER")
		.and().withUser("admin").password("{noop}admin").roles(ADMIN)
		;
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception
	{
		http .httpBasic().and().csrf().disable().authorizeRequests()
		.antMatchers("/api/rest/accountstatement/statementByAccId").hasRole(ADMIN)
		.antMatchers("/api/rest/accountstatement/statementByUser").hasAnyRole(ADMIN,"USER")
		.and().authorizeRequests().antMatchers(SWAGGER_UI_INDEX).permitAll()
		.and().authorizeRequests().antMatchers(HTML_JS).permitAll()
		.and().authorizeRequests().antMatchers(HTML_JS_APP).permitAll()
		.and().authorizeRequests().antMatchers(CONTENT).permitAll()
		.and().authorizeRequests().antMatchers(MANAGEMENT).permitAll().and().formLogin();
	}
	
	

}
