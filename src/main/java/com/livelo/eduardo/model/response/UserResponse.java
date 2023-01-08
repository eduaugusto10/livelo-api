package com.livelo.eduardo.model.response;

import java.util.List;
import com.livelo.eduardo.entities.UserEntity;

import lombok.Data;

@Data
public class UserResponse {

	private String message;
	private List<UserEntity> data;
}
