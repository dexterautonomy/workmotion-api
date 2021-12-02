package com.workmotion.devops.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workmotion.devops.dto.EmployeeDTO;
import com.workmotion.devops.dto.EmployeeRequestDTO;
import com.workmotion.devops.dto.GenericResponseDTO;
import com.workmotion.devops.enums.State;
import com.workmotion.devops.service.EmployeeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@CrossOrigin(maxAge = 3600, origins = "*")
@SecurityRequirement(name = "Channel-Code") 
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
	
	@PutMapping("update/{id}/{state}")
	public ResponseEntity<GenericResponseDTO<EmployeeDTO>> updateEmployee(@PathVariable Long id, @PathVariable State state){
		log.info("---->>> Updating employee");
		return new ResponseEntity<>(employeeService.updateEmployee(id, state), HttpStatus.OK);
	}
	
	@GetMapping("get-contract-types")
	public ResponseEntity<GenericResponseDTO<List<String>>> contractType(){
		log.info("---->>> Fetching approved contract types");
		return new ResponseEntity<>(employeeService.contractType(), HttpStatus.OK);
	}
	
	@GetMapping("get-approved-countries")
	public ResponseEntity<GenericResponseDTO<List<String>>> approvedCountries(){
		log.info("---->>> Fetching approved contract types");
		return new ResponseEntity<>(employeeService.countries(), HttpStatus.OK);
	}
	
	@GetMapping("get-departments")
	public ResponseEntity<GenericResponseDTO<List<String>>> department(){
		log.info("---->>> Fetching approved contract types");
		return new ResponseEntity<>(employeeService.department(), HttpStatus.OK);
	}
	
	@GetMapping("get-genders")
	public ResponseEntity<GenericResponseDTO<List<String>>> gender(){
		log.info("---->>> Fetching approved contract types");
		return new ResponseEntity<>(employeeService.gender(), HttpStatus.OK);
	}
	
	@GetMapping("get-marital-statuses")
	public ResponseEntity<GenericResponseDTO<List<String>>> maritalStatus(){
		log.info("---->>> Fetching approved contract types");
		return new ResponseEntity<>(employeeService.maritalStatus(), HttpStatus.OK);
	}
	
	@GetMapping("get-qualifications")
	public ResponseEntity<GenericResponseDTO<List<String>>> qualification(){
		log.info("---->>> Fetching approved contract types");
		return new ResponseEntity<>(employeeService.qualification(), HttpStatus.OK);
	}
	
	@GetMapping("get-work-types")
	public ResponseEntity<GenericResponseDTO<List<String>>> workType(){
		log.info("---->>> Fetching approved contract types");
		return new ResponseEntity<>(employeeService.worktype(), HttpStatus.OK);
	}
}
