package com.livelo.eduardo.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.livelo.eduardo.entities.UserCreateEntity;
import com.livelo.eduardo.entities.UserEntity;
import com.livelo.eduardo.exception.LiveloException;
import com.livelo.eduardo.model.response.UserResponse;
import com.livelo.eduardo.repositories.UserCreateRepository;
import com.livelo.eduardo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserCreateRepository userCreateRepository;

	public UserResponse store(UserCreateEntity userEntity) throws LiveloException {

		UserResponse response = new UserResponse();

		try {

			UserResponse userCPFExists = getByCPF(userEntity.getCpf());
			UserResponse userEmailExits = getByEmail(userEntity.getEmail());

			if (userCPFExists.getData().size() <= 0 && userEmailExits.getData().size() <= 0) {
				UserCreateEntity entities = new UserCreateEntity();
				entities.setName(userEntity.getName());
				entities.setCityId(userEntity.getCityId());
				entities.setEmail(userEntity.getEmail());
				entities.setGenderId(userEntity.getGenderId());
				entities.setCpf(userEntity.getCpf());
				entities.setBirth(userEntity.getBirth());
				userCreateRepository.save(entities);
				response.setMessage("Usuário cadastrado com sucesso");
				response.setStatusCode(201);
				return response;
			} else {
				response.setMessage("CPF/E-mail já cadastrado");
				response.setStatusCode(409);
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setMessage("Erro geral no sistema");
			response.setStatusCode(500);
		}
		return response;
	}

	public UserResponse getByCPF(String cpf) {
		UserResponse response = new UserResponse();

		try {
			List<UserEntity> entities = userRepository.getUserByCpf(cpf);
			response.setData(entities);
			response.setStatusCode(200);
			if (entities.size() <= 0)
				response.setMessage("Nenhum usuário encontrado");
			else
				response.setMessage("Usuário(s) encontrado(s)");
			return response;
		} catch (Exception e) {
			// TODO: handle exception
			response.setMessage("Erro geral no sistema");
			response.setStatusCode(500);
			return response;
		}
	}

	public UserResponse getByEmail(String email) {
		UserResponse response = new UserResponse();

		try {
			List<UserEntity> entities = userRepository.getUserByEmail(email);
			response.setData(entities);
			response.setStatusCode(200);
			if (entities.size() <= 0)
				response.setMessage("Nenhum usuário encontrado");
			else
				response.setMessage("Usuário(s) encontrado(s)");
			return response;
		} catch (Exception e) {
			// TODO: handle exception
			response.setMessage("Erro geral no sistema");
			response.setStatusCode(500);
			return response;
		}

	}

	public UserResponse getByName(String name) {
		UserResponse response = new UserResponse();

		try {
			List<UserEntity> entities = userRepository.getUserByName(name);
			response.setData(entities);
			response.setStatusCode(200);
			if (entities.size() <= 0)
				response.setMessage("Nenhum usuário encontrado");
			else
				response.setMessage("Usuário(s) encontrado(s)");
			return response;
		} catch (Exception e) {
			// TODO: handle exception
			response.setMessage("Erro geral no sistema");
			response.setStatusCode(500);
			return response;
		}
	}

	public UserResponse delete(Integer id) {

		UserResponse response = new UserResponse();

		try {
			System.out.println(id);
			userCreateRepository.deleteById(id);
			response.setStatusCode(200);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
			response.setMessage("Erro geral no sistema");
			response.setStatusCode(500);
			return response;
		}
	}

	public UserResponse update(UserCreateEntity userEntity, Integer id) {

		UserResponse response = new UserResponse();

		try {
			Optional<UserCreateEntity> optionalEntities = userCreateRepository.findById(id);

			UserCreateEntity entities = optionalEntities.get();
			entities.setName(userEntity.getName());

			userCreateRepository.save(entities);
			response.setMessage("Usuário atualizado com sucesso");
			response.setStatusCode(200);
			return response;

		} catch (Exception e) {
			// TODO: handle exception
			response.setMessage("Erro geral no sistema");
			response.setStatusCode(500);
			return response;
		}
	}
}
