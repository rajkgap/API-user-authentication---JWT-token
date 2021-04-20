 package com.SDEadda247.app.client.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SDEadda247.app.client.DTO.DTOservuce;
import com.SDEadda247.app.client.Userentity.userentity;
import com.SDEadda247.app.client.Userentity.userrepositry;
@Service
public class serviceimpl implements userService {
	userrepositry userrepositry;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public serviceimpl(userrepositry userrepositry,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userrepositry = userrepositry;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}

	@Override
	public DTOservuce createuser(DTOservuce details) {
		details.setUserid(UUID.randomUUID().toString()); 
		details.setEncryptedpassword(bCryptPasswordEncoder.encode(details.getPassword()));
		ModelMapper modelmapper=new ModelMapper(); 
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); 
		userentity userentity=modelmapper.map(details,userentity.class);
		userrepositry.save(userentity);
		// TODO Auto-generated method stub
		return null;
	}

	@Override  
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		userentity entity = userrepositry.findByemail(username);
		if(entity==null)throw new UsernameNotFoundException(username);
		
		// TODO Auto-generated method stub
		return new User(entity.getEmail(),entity.getEncryptedpassword(),true,true,true,true,new ArrayList<>());
	}

	@Override
	public DTOservuce getUserDetailsByemail(String email) {
		userentity entity = userrepositry.findByemail(email);
		if(entity==null)throw new UsernameNotFoundException(email);
		// TODO Auto-generated method stub
		return new ModelMapper().map(entity, DTOservuce.class);
	}
	

}
