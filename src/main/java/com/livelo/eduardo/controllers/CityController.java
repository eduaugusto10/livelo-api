package com.livelo.eduardo.controllers;

import java.util.List;

import javax.transaction.Transactional;

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
import com.livelo.eduardo.entities.CityEntity;
import com.livelo.eduardo.model.response.CityResponse;
import com.livelo.eduardo.service.CityService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class CityController {

	@Autowired
	private CityService cityService;

	@PostMapping(path = "/city")
	@ResponseStatus(code=HttpStatus.CREATED)
	public CityResponse store(@RequestBody CityCreateEntity cityEntity) {

		try {
			CityResponse response = new CityResponse();
			String message = cityService.Store(cityEntity);
			response.setMessage("Cidade cadastrada com sucesso");
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/*
	 * Type = 1 get city by city name,
	 * Type = 2 get all city by state,
	 */
	@GetMapping(path = "/city")
	public CityResponse get(@RequestParam Integer type, @RequestParam(required=false) String name, @RequestParam(required=false) Integer stateid) {
		try {
			CityResponse response = new CityResponse();
			List<CityEntity> entity = null;
			if(type==1) entity = cityService.GetCityByName(name);
			else entity = cityService.GetCityByState(stateid);
			
			response.setData(entity);

			if(entity.size()>0) response.setMessage("Sucesso");
			else response.setMessage("Nenhuma cidade cadastrada");
			
			return response;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@PutMapping(path = "/city")
	public CityResponse update(@RequestBody CityCreateEntity cityEntity) {
		try {
			CityResponse response = new CityResponse();
			List<CityCreateEntity> entity = cityService.Update(cityEntity);
			response.setMessage("Cidade alterada com sucesso");
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	@DeleteMapping(path = "/city")
	public CityResponse delete(@RequestParam Integer id) {
		System.out.println(id);
		try {
			
			CityResponse response = new CityResponse();
			cityService.Delete(id);
			response.setMessage("Cidade deletada com sucesso");
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
