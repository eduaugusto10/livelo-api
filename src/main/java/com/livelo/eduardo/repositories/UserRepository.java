package com.livelo.eduardo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.livelo.eduardo.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	@Query(value = "SELECT tbuser.id, tbuser.email, tbuser.birth, tbuser.cpf,tbstate.state, tbuser.name, tbgender.gender, tbcity.city FROM tbuser\r\n"
			+ " INNER JOIN tbgender ON tbuser.genderId=tbgender.id\r\n"
			+ " INNER JOIN tbcity ON tbuser.cityId=tbcity.id " + " INNER JOIN tbstate ON tbcity.stateId=tbstate.id"
			+ " WHERE tbuser.name LIKE %?1%", nativeQuery = true)
	List<UserEntity> getUserByName(String name);

	@Query(value = "SELECT tbuser.id, tbuser.email, tbuser.birth, tbuser.cpf,tbstate.state, tbuser.name, tbgender.gender, tbcity.city FROM tbuser\r\n"
			+ " INNER JOIN tbgender ON tbuser.genderId=tbgender.id\r\n"
			+ " INNER JOIN tbcity ON tbuser.cityId=tbcity.id" + " INNER JOIN tbstate ON tbcity.stateId=tbstate.id"
			+ " WHERE tbuser.cpf=?1", nativeQuery = true)
	List<UserEntity> getUserByCpf(String cpf);

	@Query(value = "SELECT tbuser.id, tbuser.email, tbuser.birth, tbuser.cpf,tbstate.state, tbuser.name, tbgender.gender, tbcity.city FROM tbuser\r\n"
			+ " INNER JOIN tbgender ON tbuser.genderId=tbgender.id\r\n"
			+ " INNER JOIN tbcity ON tbuser.cityId=tbcity.id" + " INNER JOIN tbstate ON tbcity.stateId=tbstate.id"
			+ " WHERE tbuser.email=?1", nativeQuery = true)
	List<UserEntity> getUserByEmail(String email);

	@Query(value = "SELECT tbuser.id, tbuser.email, tbuser.birth, tbuser.cpf,tbstate.state, tbuser.name, tbgender.gender, tbcity.city FROM tbuser\r\n"
			+ " INNER JOIN tbgender ON tbuser.genderId=tbgender.id\r\n"
			+ " INNER JOIN tbcity ON tbuser.cityId=tbcity.id " + " INNER JOIN tbstate ON tbcity.stateId=tbstate.id"
			+ " WHERE tbuser.cityId=?1", nativeQuery = true)
	List<UserEntity> getUsedCity(Integer id);
}
