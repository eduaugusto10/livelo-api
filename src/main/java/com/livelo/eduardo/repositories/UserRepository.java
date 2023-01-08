package com.livelo.eduardo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.livelo.eduardo.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	@Query(value="SELECT tbuser.id, tbuser.email, tbuser.birth, tbuser.cpf, tbuser.name, tbgender.gender, tbcity.city FROM tbuser\r\n"
			+ " INNER JOIN tbgender ON tbuser.genderId=tbgender.id\r\n"
			+ " INNER JOIN tbcity ON tbuser.cityId=tbcity.id WHERE tbuser.name LIKE %?1%",nativeQuery=true)
	List<UserEntity> getUserByName(String name);
	
	@Query(value="SELECT tbuser.id, tbuser.email, tbuser.birth, tbuser.cpf, tbuser.name, tbgender.gender, tbcity.city FROM tbuser\r\n"
			+ " INNER JOIN tbgender ON tbuser.genderId=tbgender.id\r\n"
			+ " INNER JOIN tbcity ON tbuser.cityId=tbcity.id WHERE tbuser.cpf=?1",nativeQuery=true)
	List<UserEntity> getUserByCpf(String cpf);
}
