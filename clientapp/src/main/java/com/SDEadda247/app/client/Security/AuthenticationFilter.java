package com.SDEadda247.app.client.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.SDEadda247.app.client.DTO.DTOservuce;
import com.SDEadda247.app.client.create.request.model.Loginrequest;
import com.SDEadda247.app.client.service.userService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private userService usersservice; 
	private Environment environment;
	
	

	public AuthenticationFilter(userService usersservice, Environment environment,
			AuthenticationManager authenticationManager) {
		this.environment=environment;
		this.usersservice=usersservice;
		super.setAuthenticationManager(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public org.springframework.security.core.Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		try {
			Loginrequest cred=new ObjectMapper().readValue(request.getInputStream(),Loginrequest.class);
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword(),new ArrayList<>()));
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
			
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			org.springframework.security.core.Authentication authResult) throws IOException, ServletException {
		String userName=((User) authResult.getPrincipal()).getUsername();
		DTOservuce details=usersservice.getUserDetailsByemail(userName);
		
		String token = Jwts.builder()
				.setSubject(details.getUserid())
				.setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(environment.getProperty("token.expiration_time"))))
				.signWith(SignatureAlgorithm.HS512,environment.getProperty("token.secret"))
				.compact();
		
		response.addHeader("token", token);
		response.addHeader("userId",details.getUserid());
		

	}

}
