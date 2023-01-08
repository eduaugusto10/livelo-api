package com.livelo.eduardo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.livelo.eduardo.entities.CityCreateEntity;
import com.livelo.eduardo.entities.UserCreateEntity;
import com.livelo.eduardo.entities.UserEntity;
import com.livelo.eduardo.repositories.UserCreateRepository;
import com.livelo.eduardo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserCreateRepository userCreateRepository;

	public void store(UserCreateEntity userEntity) {
		try {
			UserCreateEntity entities = new UserCreateEntity();
			entities.setName(userEntity.getName());
			entities.setCityId(userEntity.getCityId());
			entities.setEmail(userEntity.getEmail());
			entities.setGenderId(userEntity.getGenderId());
			entities.setCpf(userEntity.getCpf());
			entities.setBirth(userEntity.getBirth());
			userCreateRepository.save(entities);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public List<UserEntity> getByCPF(String cpf) {
		List<UserEntity> entities = userRepository.getUserByCpf(cpf);
		System.out.println(entities);
		return entities;
	}

	public List<UserEntity> getByName(String name) {
		List<UserEntity> entities = userRepository.getUserByName(name);
		System.out.println(entities);
		return entities;
	}

	public UserEntity delete(Integer id) {
		try {
			System.out.println(id);
			userCreateRepository.deleteById(id);
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public void update(UserCreateEntity userEntity) {
		try {
			UserCreateEntity entities = new UserCreateEntity();
			entities.setName(userEntity.getName());
			entities.setCityId(userEntity.getCityId());
			entities.setEmail(userEntity.getEmail());
			entities.setGenderId(userEntity.getGenderId());
			entities.setCpf(userEntity.getCpf());
			entities.setBirth(userEntity.getBirth());
			entities.setId(userEntity.getId());
			userCreateRepository.save(entities);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
