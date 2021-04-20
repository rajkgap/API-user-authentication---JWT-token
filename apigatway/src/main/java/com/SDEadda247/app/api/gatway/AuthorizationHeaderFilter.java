package com.SDEadda247.app.api.gatway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.net.HttpHeaders;

import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>{
	@Autowired
	Environment env;
	
	
	public  AuthorizationHeaderFilter ()
	{
		super(Config.class);
	}
	public static class Config
	{
		
	}

	@Override
	public GatewayFilter apply(Config config) {
		return  (exchange,chain) ->{
			ServerHttpRequest request=exchange.getRequest();
			
			if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				return onError(exchange,"No Authorization Header",HttpStatus.UNAUTHORIZED);
			}
			
			String authorizationheader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			String jwt=authorizationheader.replace("bearer", "");
			
			if(isJwtTokenValid(jwt)==false) {
				return onError(exchange,"JWT TOKEN NOT VALID",HttpStatus.UNAUTHORIZED);
			}
			
			
			return chain.filter(exchange);
		};
	}
	
	
	private Mono<Void> onError(ServerWebExchange exchange,String err,HttpStatus httpstatus){
		ServerHttpResponse response=exchange.getResponse();
		response.setStatusCode(httpstatus);
		
		return response.setComplete();
	}
	
	private boolean isJwtTokenValid(String jwt)
	{
		boolean returnvalue=true;
		String subject=null;
		try {
		subject=Jwts.parser().setSigningKey(env.getProperty("token.secret"))
		.parseClaimsJws(jwt)
		.getBody()
		.getSubject();
		}catch(Exception ex) {
			returnvalue= false;
		}
		
		if(subject==null||subject.isEmpty())
		{
			returnvalue=false;
		}
		
		return returnvalue;
		
	}

}
