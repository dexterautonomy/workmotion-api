package com.workmotion.devops.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workmotion.devops.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	public Optional<Boolean> existsByEmployeeNumber(String employeeNumber);
}
