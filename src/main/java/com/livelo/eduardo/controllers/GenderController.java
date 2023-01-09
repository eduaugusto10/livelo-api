package com.livelo.eduardo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livelo.eduardo.entities.GenderEntity;
import com.livelo.eduardo.repositories.GenderRepository;

@RestController
@RequestMapping(path = "/api")
public class GenderController {

	@Autowired
	private GenderRepository genderRepository;

	@GetMapping(path = "/gender")
	public List<GenderEntity> getAllGenders() {
		try {
			List<GenderEntity> response = (List<GenderEntity>) genderRepository.findAll();
			return response;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
