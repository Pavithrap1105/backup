package com.luxoft.Employeemanagement.configuration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	@Query("from UserEntity where username=:username")
	public UserEntity findByUsername(@Param("username") String username);

}
