package com.springcloud.example.department.service;

import com.springcloud.example.department.entity.Department;
import com.springcloud.example.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;


    public Department saveDepartment(Department department) {
        log.info("Inside saveDepartment of DepartmentService");
        return departmentRepo.save(department);
    }

    public Department findDepartmentById(Long departmentId){
        log.info("Inside findDepartmentById of DepartmentService");
        return departmentRepo.findByDepartmentId(departmentId);
    }
}
