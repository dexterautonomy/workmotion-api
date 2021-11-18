package com.workmotion.devops.serviceImpl;

import org.springframework.stereotype.Service;

import com.workmotion.devops.dto.EmployeeDTO;
import com.workmotion.devops.dto.EmployeeRequestDTO;
import com.workmotion.devops.dto.GenericResponseDTO;
import com.workmotion.devops.enums.State;
import com.workmotion.devops.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public <T extends EmployeeRequestDTO> GenericResponseDTO<String> createEmployee(T request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericResponseDTO<EmployeeDTO> fetchEmployee(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericResponseDTO<EmployeeDTO> updateEmployee(Long id, State state) {
		// TODO Auto-generated method stub
		return null;
	}

}
