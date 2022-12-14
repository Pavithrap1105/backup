package com.luxoft.Employeemanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.Employeemanagement.dao.EmployeeDao;
import com.luxoft.Employeemanagement.entity.Employee;
import com.luxoft.Employeemanagement.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee saveEmployee(Employee emp) {
		Employee saved = employeeDao.save(emp);
		return saved;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> listOfEmployees = employeeDao.findAll();
		return listOfEmployees;
	}

	@Override
	public Employee getEmployeeById(Integer empId) {
		Employee employee = employeeDao.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with the employee ID" + empId));
		return employee;
	}

	@Override
	public Employee updateEmployeeById(Employee emp, Integer empId) {
		Employee employee = employeeDao.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with the employee ID" + empId));
		employee.setName(emp.getName());
		employee.setSalary(emp.getSalary());
		employee.setAddress(emp.getAddress());
		Employee saved = employeeDao.save(employee);

		return saved;
	}

	@Override
	public String deleteEmployeeById(Integer empId) {
		Employee employee = employeeDao.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with the employee ID" + empId));
		employeeDao.delete(employee);
		
		return "Employee with the Id "+empId+" Deleted succesfully";
	}

}
