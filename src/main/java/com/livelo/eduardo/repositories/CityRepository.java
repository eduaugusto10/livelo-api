package com.livelo.eduardo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.livelo.eduardo.entities.CityEntity;

public interface CityRepository extends CrudRepository<CityEntity, String> {

	@Query(value = "SELECT tbcity.id, tbcity.stateId, tbcity.city,tbstate.state "
			+ "FROM tbcity JOIN tbstate ON tbcity.stateId=tbstate.id WHERE city LIKE %?1%", nativeQuery = true)
	List<CityEntity> getByName(String name);
	
	@Query(value = "SELECT tbcity.id, tbcity.stateId, tbcity.city,tbstate.state"
			+ " FROM tbcity JOIN tbstate ON tbcity.stateId=tbstate.id WHERE city=?1", nativeQuery=true)
	List<CityEntity> getAllCityByName(String name);

	@Query(value = "SELECT tbcity.id, tbcity.stateId, tbcity.city, tbstate.state "
			+ "FROM tbcity JOIN tbstate ON tbcity.stateId=tbstate.id WHERE stateId=?1", nativeQuery = true)
	List<CityEntity> getByState(Integer stateId);

}
