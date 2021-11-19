package com.workmotion.devops.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workmotion.devops.dto.EmployeeDTO;
import com.workmotion.devops.dto.EmployeeRequestDTO;
import com.workmotion.devops.dto.GenericResponseDTO;
import com.workmotion.devops.enums.State;
import com.workmotion.devops.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@CrossOrigin(maxAge = 3600, origins = "*")
public class EmployeeController {
	private final EmployeeService employeeService;
	
	@PostMapping("add")
	public ResponseEntity<GenericResponseDTO<EmployeeDTO>> addEmployee(@RequestBody @Valid EmployeeRequestDTO employeeDTO){
		log.info("---->>> Add new employee");
		return new ResponseEntity<>(employeeService.createEmployee(employeeDTO), HttpStatus.OK);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<GenericResponseDTO<EmployeeDTO>> fetchEmployee(@PathVariable Long id){
		log.info("---->>> Fetching employee");
		return new ResponseEntity<>(employeeService.fetchEmployee(id), HttpStatus.OK);
	}
	
	@PostMapping("update/{id}/{state}")
	public ResponseEntity<GenericResponseDTO<EmployeeDTO>> addEmployee(@PathVariable Long id, @PathVariable State state){
		log.info("---->>> Updating employee");
		return new ResponseEntity<>(employeeService.updateEmployee(id, state), HttpStatus.OK);
	}
}
