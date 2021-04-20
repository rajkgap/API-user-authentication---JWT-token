package com.SDEadda247.app.client.UI.Controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SDEadda247.app.client.DTO.DTOservuce;
import com.SDEadda247.app.client.create.request.model.createUserRequest;
import com.SDEadda247.app.client.service.serviceimpl;

@RestController
@RequestMapping("/users")
public class ClientUIcontroller {
	@Autowired
	serviceimpl service;
	
	@GetMapping("/status/check")
	public String status()
	{
		return "working...";
	}
	@PostMapping
	public ResponseEntity CreatNewUser(@Valid @RequestBody createUserRequest val)
	{
		
		ModelMapper modelmapper=new ModelMapper(); 
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); 
		DTOservuce dto=modelmapper.map(val,DTOservuce.class);
		service.createuser(dto);
		
		
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
