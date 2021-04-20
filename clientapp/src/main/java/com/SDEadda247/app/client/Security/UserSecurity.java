package com.SDEadda247.app.client.Security;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.SDEadda247.app.client.service.userService;


@Configuration
@EnableWebSecurity
public class UserSecurity extends WebSecurityConfigurerAdapter {
	private userService usersservice;                             
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private Environment environment;
	
	public UserSecurity(userService usersservice,BCryptPasswordEncoder bCryptPasswordEncoder,Environment environment)
	{
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		this.usersservice=usersservice;
		this.environment=environment;
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/users/**").permitAll()
		.and()
		.addFilter(getAuthenticationfilter());
		
		http.headers().frameOptions().disable();
	}
	private AuthenticationFilter getAuthenticationfilter() throws Exception  
	{
		AuthenticationFilter authentiactionfilter=new AuthenticationFilter(usersservice,environment,authenticationManager());
//		authentiactionfilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
//		authentiactionfilter.setAuthenticationManager(authenticationManager());
		return authentiactionfilter;
	}                                                                
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersservice).passwordEncoder(bCryptPasswordEncoder);
	}

}
