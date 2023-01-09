package com.livelo.eduardo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.livelo.eduardo.entities.StateEntity;
import com.livelo.eduardo.repositories.StateRepository;

@RestController
@RequestMapping(path = "/api")
public class StateController {

	@Autowired
	private StateRepository stateRepository; 
	
	@GetMapping(path = "/state")
	public List<StateEntity> getAllStates() {
		try {
			List<StateEntity> response = (List<StateEntity>) stateRepository.findAll();
			 return response;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
