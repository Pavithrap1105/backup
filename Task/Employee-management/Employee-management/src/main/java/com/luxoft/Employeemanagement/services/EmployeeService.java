package com.luxoft.Employeemanagement.services;

import java.util.List;

import com.luxoft.Employeemanagement.entity.Employee;

public interface EmployeeService {
	
	public Employee saveEmployee(Employee emp);
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(Integer empId);
	
	public Employee updateEmployeeById(Employee emp,Integer empId);
	
	public String deleteEmployeeById(Integer empId);
	

}
