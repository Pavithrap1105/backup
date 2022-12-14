package com.luxoft.Employeemanagement.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends RepresentationModel<Employee> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	
	@NotNull(message = "Name should not be null")
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 1,max = 30,message = "Name should contain atleast 1 character and maximum 30 characters")
	private String name;
	
	@NotNull(message = "Salary should not be null")
	@Min(value = 0,message = "Salary should be above zero")
	private Float salary;
	
	@NotNull(message = "Address should not be null")
	@OneToMany(mappedBy = "employee")
	private List<Address> address;
	
	
	
}
