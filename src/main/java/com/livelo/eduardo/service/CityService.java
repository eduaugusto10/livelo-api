package com.livelo.eduardo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.livelo.eduardo.entities.CityCreateEntity;
import com.livelo.eduardo.entities.CityEntity;
import com.livelo.eduardo.entities.UserEntity;
import com.livelo.eduardo.model.response.CityResponse;
import com.livelo.eduardo.repositories.CityCreateRepository;
import com.livelo.eduardo.repositories.CityRepository;
import com.livelo.eduardo.repositories.UserRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CityCreateRepository cityCreateRepository;

	public CityResponse getCityByName(String city) {
		CityResponse response = new CityResponse();
		try {
			List<CityEntity> entities = cityRepository.getByName(city);
			response.setData(entities);
			response.setStatusCode(200);
			response.setMessage("Cidade encontrada");
			return response;

		} catch (Exception e) {
			// TODO: handle exception
			response.setMessage("Erro geral no sistema");
			response.setStatusCode(500);
			return response;
		}
	}

	public CityResponse getCityByState(Integer stateId) {
		CityResponse response = new CityResponse();
		try {
			List<CityEntity> entities = cityRepository.getByState(stateId);
			response.setData(entities);
			response.setStatusCode(200);
			response.setMessage("Cidade encontrada");
			return response;

		} catch (Exception e) {
			// TODO: handle exception
			response.setMessage("Erro geral no sistema");
			response.setStatusCode(500);
			return response;
		}
	}

	public CityResponse store(CityCreateEntity cityEntity) {
		CityResponse response = new CityResponse();

		try {
			List<CityEntity> cityExists = cityRepository.getAllCityByName(cityEntity.getCity());
			if (cityExists.size() <= 0) {
				CityCreateEntity entities = new CityCreateEntity();
				entities.setCity(cityEntity.getCity());
				entities.setStateId(cityEntity.getStateId());
				cityCreateRepository.save(entities);
				response.setStatusCode(201);
				response.setMessage("Cidade cadastrada com sucesso");
				return response;
			} else {
				response.setMessage("Cidade já cadastrada");
				response.setStatusCode(409);
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setMessage("Erro geral no sistema");
			response.setStatusCode(500);
			return response;
		}
		return response;
	}

	public CityResponse update(CityCreateEntity cityEntity, Integer id) {
		CityResponse response = new CityResponse();
		
		try {
			Optional<CityCreateEntity> optionalEntities = cityCreateRepository.findById(id);
			List<CityEntity> cityExists = cityRepository.getAllCityByName(cityEntity.getCity());
			
			if (cityExists.size() <= 0) {
				CityCreateEntity entities = optionalEntities.get();
				entities.setCity(cityEntity.getCity());
				cityCreateRepository.save(entities);
				response.setMessage("Cidade atualizada com sucesso");
				response.setStatusCode(200);
				return response;
			} else {
				response.setMessage("Cidade já cadastrada");
				response.setStatusCode(409);
				return response;
			}

		} catch (Exception e) {
			// TODO: handle exception
			response.setMessage("Erro geral no sistema");
			response.setStatusCode(500);
			return response;
		}
	}

	public CityResponse delete(Integer id) {
		CityResponse response = new CityResponse();
		try {
			List<UserEntity> cityUsed = userRepository.getUsedCity(id);
			System.out.println(cityUsed);
			if (cityUsed.size() <= 0) {
				cityCreateRepository.deleteById(id);
				response.setMessage("Cidade deletada com sucesso");
				response.setStatusCode(200);
				return response;
			}else {
				response.setMessage("Erro cidade vinculada com usuários");
				response.setStatusCode(409);
				return response;
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setMessage("Erro geral no sistema");
			response.setStatusCode(500);
			return response;
		}
	}

}
