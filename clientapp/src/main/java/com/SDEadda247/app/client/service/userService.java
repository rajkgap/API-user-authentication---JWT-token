package com.SDEadda247.app.client.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.SDEadda247.app.client.DTO.DTOservuce;

public interface userService extends UserDetailsService{
	DTOservuce createuser(DTOservuce details);
	DTOservuce getUserDetailsByemail(String email);

}
