package com.livelo.eduardo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.livelo.eduardo.entities.UserCreateEntity;

public interface UserCreateRepository extends CrudRepository<UserCreateEntity, Integer> {

}
