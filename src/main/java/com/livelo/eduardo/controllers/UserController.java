package com.livelo.eduardo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livelo.eduardo.entities.UserCreateEntity;
import com.livelo.eduardo.model.response.UserResponse;
import com.livelo.eduardo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/user")
	public ResponseEntity<UserResponse> store(@RequestBody UserCreateEntity userEntity) {
		UserResponse response = new UserResponse();

		try {
			response = userService.store(userEntity);
			if (response.getStatusCode() == 409) {
				return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<UserResponse>(response, HttpStatus.CREATED);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<UserResponse>(response, HttpStatus.CONFLICT);
		}

	}

	/*
	 * Type = 1 search by name and type = 2 search by cpf
	 */
	@GetMapping(path = "/user")
	public ResponseEntity<UserResponse> getByName(@RequestParam Integer type,
			@RequestParam(required = false) String name, @RequestParam(required = false) String cpf) {
		UserResponse response = new UserResponse();

		try {
			response = (type == 1) ? userService.getByName(name) : userService.getByCPF(cpf);
			return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<UserResponse>(response, HttpStatus.CONFLICT);
		}
	}

	@PutMapping(path = "/user")
	public ResponseEntity<UserResponse> update(@RequestBody UserCreateEntity userEntity, @RequestParam Integer id) {
		UserResponse response = new UserResponse();

		try {
			response = userService.update(userEntity,id);
			return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<UserResponse>(response, HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping(path = "/user")
	public ResponseEntity<UserResponse> delete(@RequestParam Integer id) {
		UserResponse response = new UserResponse();

		try {
			response = userService.delete(id);
			return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<UserResponse>(response, HttpStatus.CONFLICT);
		}
	}

}
