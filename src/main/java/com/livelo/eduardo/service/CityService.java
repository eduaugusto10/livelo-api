package com.livelo.eduardo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.livelo.eduardo.entities.CityCreateEntity;
import com.livelo.eduardo.entities.CityEntity;
import com.livelo.eduardo.exception.LiveloException;
import com.livelo.eduardo.repositories.CityCreateRepository;
import com.livelo.eduardo.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CityCreateRepository cityCreateRepository;

	public List<CityEntity> GetCityByName(String city) {

		try {
			List<CityEntity> entities = cityRepository.getByName(city);

			return entities;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public List<CityEntity> GetCityByState(Integer stateId) {

		try {
			List<CityEntity> entities = cityRepository.getByState(stateId);

			return entities;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public String Store(CityCreateEntity cityEntity) throws LiveloException {
		List<CityEntity> cityExists = cityRepository.getAllCityByName(cityEntity.getCity());
		if(cityExists.size()>0) {
			LiveloException liveloException = new LiveloException("Cidade já cadastrada");
			throw liveloException;
		}
		if(cityExists.size()>0) {
			return "Cidade já cadastrada"; 
		}
		try {
			CityCreateEntity entities = new CityCreateEntity();
			entities.setCity(cityEntity.getCity());
			entities.setStateId(cityEntity.getStateId());
			cityCreateRepository.save(entities);
			return "Cidade cadastrada com sucesso";
		} catch (Exception e) {
			// TODO: handle exception
			return "Erro ao cadastrar cidade";
		}
	}

	public List<CityCreateEntity> Update(CityCreateEntity cityEntity) {
		
		try {
			cityCreateRepository.save(cityEntity);
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public CityEntity Delete(Integer id) {
		try {
			System.out.println(id);
			cityCreateRepository.deleteById(id);
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
