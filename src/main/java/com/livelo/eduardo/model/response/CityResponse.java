package com.livelo.eduardo.model.response;

import java.util.List;

import com.livelo.eduardo.entities.CityEntity;

import lombok.Data;

@Data
public class CityResponse {

	private String message;
	private List<CityEntity> data;
}
