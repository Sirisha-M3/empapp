package com.imaginnovate.empapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imaginnovate.empapp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
