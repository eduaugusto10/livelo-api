package com.livelo.eduardo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livelo.eduardo.entities.CityCreateEntity;
import com.livelo.eduardo.entities.UserCreateEntity;
import com.livelo.eduardo.entities.UserEntity;
import com.livelo.eduardo.model.response.CityResponse;
import com.livelo.eduardo.model.response.UserResponse;
import com.livelo.eduardo.repositories.UserCreateRepository;
import com.livelo.eduardo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/user")
	public ResponseEntity<UserCreateEntity> store(@RequestBody UserCreateEntity userEntity) {
		try {
			userService.store(userEntity);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	/*
	 * Type = 1 search by name Type = 2 search by cpf
	 */
	@GetMapping(path = "/user")
	public UserResponse getByName(@RequestParam Integer type, @RequestParam(required = false) String name,
			@RequestParam(required = false) String cpf) {
		UserResponse response = new UserResponse();
		try {
			List<UserEntity> entity = (type == 1) ? userService.getByName(name) : userService.getByCPF(cpf);
			System.out.println(entity);
			response.setData(entity);
			response.setMessage("Sucesso");
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@PutMapping(path = "/user")
	public UserResponse update(@RequestBody UserCreateEntity userEntity) {
		try {
			UserResponse response = new UserResponse();
			userService.update(userEntity);
			response.setMessage("Usuário alterado com sucesso");
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@DeleteMapping(path = "/user")
	public UserResponse delete(@RequestParam Integer id) {
		System.out.println(id);
		try {
			
			UserResponse response = new UserResponse();
			userService.delete(id);
			response.setMessage("Usuário deletado com sucesso");
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
