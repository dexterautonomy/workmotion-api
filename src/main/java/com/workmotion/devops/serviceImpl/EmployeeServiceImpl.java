package com.workmotion.devops.serviceImpl;

import org.springframework.stereotype.Service;

import com.workmotion.devops.dto.EmployeeDTO;
import com.workmotion.devops.dto.EmployeeRequestDTO;
import com.workmotion.devops.dto.GenericResponseDTO;
import com.workmotion.devops.enums.State;
import com.workmotion.devops.exception.CustomException;
import com.workmotion.devops.model.BasicInfo;
import com.workmotion.devops.model.ContactInfo;
import com.workmotion.devops.model.ContractInfo;
import com.workmotion.devops.model.Employee;
import com.workmotion.devops.repository.BasicInfoRepo;
import com.workmotion.devops.repository.ContactInfoRepo;
import com.workmotion.devops.repository.ContractInfoRepo;
import com.workmotion.devops.repository.EmployeeRepo;
import com.workmotion.devops.service.EmployeeService;
import com.workmotion.devops.utils.Transformer;
import static com.workmotion.devops.enums.Status.*;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepo employeeRepo;
	private final Transformer transformer;
	private final BasicInfoRepo basicInfoRepo;
	private final ContactInfoRepo contactInfoRepo;
	private final ContractInfoRepo contractInfoRepo;

	@Override
	@Transactional
	public <T extends EmployeeRequestDTO> GenericResponseDTO<EmployeeDTO> createEmployee(T request) {
		log.info("---->>> Commence adding new employee with info: {}", request.toString());
		
		Employee employee = transformer.create(request)
				.orElseThrow(() -> new CustomException(EMPLOYEE_CREATION.getMessage()));
		employee = employeeRepo.save(employee);
		
		if(employee.getId() == null) {
			throw new CustomException(SAVE_EMPLOYEE_ERROR.getMessage());
		}
		
		updateChildren(employee);
		
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				transformer.createEmployeeDTO(employee).get());
	}

	@Override
	public GenericResponseDTO<EmployeeDTO> fetchEmployee(Long id) {
		log.info("---->>> Commence retieving employee details with id: {}", id);
		
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new CustomException(EMPLOYEE_RETRIEVAL_ERROR.getMessage()));
		
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				transformer.createEmployeeDTO(employee).get());
	}

	@Override
	@Transactional
	public GenericResponseDTO<EmployeeDTO> updateEmployee(Long id, State state) {
		log.info("---->>> Commence updating employee with id: {} and status: {}", id, state);
		
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


	@Transactional
	private void updateChildren(Employee employee) {
		BasicInfo basicInfo = employee.getBasicInfo();
		basicInfo.setEmployee(employee);
		basicInfoRepo.save(basicInfo);
		
		ContactInfo contactInfo = employee.getContactInfo();
		contactInfo.setEmployee(employee);
		contactInfoRepo.save(contactInfo);
		
		ContractInfo contractInfo = employee.getContractInfo();
		contractInfo.setEmployee(employee);
		contractInfoRepo.save(contractInfo);
	}
}
