package com.luxoft.Employeemanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxoft.Employeemanagement.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

}
