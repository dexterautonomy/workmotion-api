package com.workmotion.devops.service;

import com.workmotion.devops.dto.EmployeeRequestDTO;

import java.util.List;

import com.workmotion.devops.dto.EmployeeDTO;
import com.workmotion.devops.dto.GenericResponseDTO;
import com.workmotion.devops.enums.State;

public interface EmployeeService {
	<T extends EmployeeRequestDTO> GenericResponseDTO<EmployeeDTO> createEmployee(T request);
	GenericResponseDTO<EmployeeDTO> fetchEmployee(Long id);
	GenericResponseDTO<EmployeeDTO> updateEmployee(Long id, State state);
	
	GenericResponseDTO<List<String>> contractType();
	GenericResponseDTO<List<String>> countries();
	GenericResponseDTO<List<String>> department();
	GenericResponseDTO<List<String>> gender();
	GenericResponseDTO<List<String>> maritalStatus();
	GenericResponseDTO<List<String>> qualification();
	GenericResponseDTO<List<String>> worktype();
	
}
