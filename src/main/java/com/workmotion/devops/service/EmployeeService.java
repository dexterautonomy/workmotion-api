package com.workmotion.devops.service;

import com.workmotion.devops.dto.EmployeeRequestDTO;
import com.workmotion.devops.dto.EmployeeDTO;
import com.workmotion.devops.dto.GenericResponseDTO;
import com.workmotion.devops.enums.State;

public interface EmployeeService {
	<T extends EmployeeRequestDTO> GenericResponseDTO<EmployeeDTO> createEmployee(T request);
	GenericResponseDTO<EmployeeDTO> fetchEmployee(Long id);
	GenericResponseDTO<EmployeeDTO> updateEmployee(Long id, State state);
}
