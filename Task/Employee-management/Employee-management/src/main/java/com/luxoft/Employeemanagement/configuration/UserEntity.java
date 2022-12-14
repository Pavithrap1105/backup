package com.luxoft.Employeemanagement.configuration;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class UserEntity {

	@Id
	private long id;
	private String username;
	@JsonIgnore
	private String password;

}
