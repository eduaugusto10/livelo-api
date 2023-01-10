package com.livelo.eduardo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.livelo.eduardo.entities.CityCreateEntity;
import com.livelo.eduardo.model.response.CityResponse;
import com.livelo.eduardo.service.CityService;

@RestController
@RequestMapping(path = "/api")
public class CityController {

	@Autowired
	private CityService cityService;

	@PostMapping(path = "/city")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<CityResponse> store(@RequestBody CityCreateEntity cityEntity) {
		CityResponse response = new CityResponse();
		try {

			response = cityService.store(cityEntity);
			if (response.getStatusCode() == 200) {
				return new ResponseEntity<CityResponse>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<CityResponse>(response, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatusCode(500);
			response.setMessage("Erro geral no sistema");
			return new ResponseEntity<CityResponse>(response, HttpStatus.CONFLICT);
		}
	}

	/*
	 * Type = 1 get city by city name, Type = 2 get all city by state,
	 */
	@GetMapping(path = "/city")
	public ResponseEntity<CityResponse> get(@RequestParam Integer type, @RequestParam(required = false) String name,
			@RequestParam(required = false) Integer stateid) {
		CityResponse response = new CityResponse();
		try {

			if (type == 1)
				response = cityService.getCityByName(name);
			else
				response = cityService.getCityByState(stateid);
			return new ResponseEntity<CityResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatusCode(500);
			response.setMessage("Erro geral no sistema");
			return new ResponseEntity<CityResponse>(response, HttpStatus.CONFLICT);
		}
	}

	@PutMapping(path = "/city")
	public ResponseEntity<CityResponse> update(@RequestBody CityCreateEntity cityEntity,@RequestParam Integer id) {
		CityResponse response = new CityResponse();

		try {
			response = cityService.update(cityEntity,id);
			return new ResponseEntity<CityResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatusCode(500);
			response.setMessage("Erro geral no sistema");
			return new ResponseEntity<CityResponse>(response, HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping(path = "/city")
	public ResponseEntity<CityResponse> delete(@RequestParam Integer id) {
		CityResponse response = new CityResponse();

		try {
			response = cityService.delete(id);
			return new ResponseEntity<CityResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatusCode(500);
			response.setMessage("Erro geral no sistema");
			return new ResponseEntity<CityResponse>(response, HttpStatus.CONFLICT);
		}
	}
}
