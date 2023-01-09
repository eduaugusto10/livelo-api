package com.livelo.eduardo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="tbstate")
public class StateEntity {

	@Id
	private Integer id;
	private String state;
}
