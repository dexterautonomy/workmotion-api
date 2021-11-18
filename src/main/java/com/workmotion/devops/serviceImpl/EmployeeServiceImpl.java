package com.workmotion.devops.serviceImpl;

import org.springframework.stereotype.Service;

import com.workmotion.devops.dto.EmployeeDTO;
import com.workmotion.devops.dto.EmployeeRequestDTO;
import com.workmotion.devops.dto.GenericResponseDTO;
import com.workmotion.devops.enums.State;
import com.workmotion.devops.exception.CustomException;
import com.workmotion.devops.model.Employee;
import com.workmotion.devops.repository.EmployeeRepo;
import com.workmotion.devops.service.EmployeeService;
import com.workmotion.devops.utils.Transformer;
import static com.workmotion.devops.enums.Status.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepo employeeRepo;
	private final Transformer transformer;

	@Override
	public <T extends EmployeeRequestDTO> GenericResponseDTO<EmployeeDTO> createEmployee(T request) {
		log.info("Commence adding new employee with info: {}", request.toString());
		
		Employee employee = transformer.create(request)
				.orElseThrow(() -> new CustomException(EMPLOYEE_CREATION.getMessage()));
		employee = employeeRepo.save(employee);
		
		if(employee.getId() == null) {
			throw new CustomException(SAVE_EMPLOYEE_ERROR.getMessage());
		}
		
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				transformer.createEmployeeDTO(employee).get());
	}

	@Override
	public GenericResponseDTO<EmployeeDTO> fetchEmployee(Long id) {
		log.info("Commence retieving employee details with id: {}", id);
		
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new CustomException(EMPLOYEE_RETRIEVAL_ERROR.getMessage()));
		
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				transformer.createEmployeeDTO(employee).get());
	}

	@Override
	public GenericResponseDTO<EmployeeDTO> updateEmployee(Long id, State state) {
		log.info("Commence updating employee with id: {} and status: {}", id, state);
		
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new CustomException(EMPLOYEE_RETRIEVAL_ERROR.getMessage()));
		employee.setState(state);
		employee = employeeRepo.save(employee);
		
		if(employee.getId() == null) {
			throw new CustomException(UPDATE_EMPLOYEE_ERROR.getMessage());
		}
		
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				transformer.createEmployeeDTO(employee).get());
	}

}
