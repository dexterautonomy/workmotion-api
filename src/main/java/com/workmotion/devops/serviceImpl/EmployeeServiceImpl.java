package com.workmotion.devops.serviceImpl;

import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import static com.workmotion.devops.enums.State.*;
import org.springframework.stereotype.Service;

import com.workmotion.devops.config.ConfigProcessor;
import com.workmotion.devops.controller.EmployeeController;
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
	private final ConfigProcessor configProcessor;

	@Override
	@Transactional
	public <T extends EmployeeRequestDTO> GenericResponseDTO<EmployeeDTO> createEmployee(T request) {
		log.info("---->>> Commence adding new employee with info: {}", request.toString());
		EmployeeDTO empDTO = null;
		Employee employee = transformer.create(request)
				.orElseThrow(() -> new CustomException(EMPLOYEE_CREATION.getMessage()));
		employee = employeeRepo.save(employee);
		
		if(employee.getId() == null) {
			throw new CustomException(SAVE_EMPLOYEE_ERROR.getMessage());
		}
		
		updateChildren(employee);
		
		Link addEmp = linkTo(methodOn(EmployeeController.class).addEmployee(request))
				.withSelfRel();
		Link fetchEmployee = linkTo(methodOn(EmployeeController.class)
				.fetchEmployee(request.getId())).withRel("fetchEmployee");
		Link updateEmployeeState = linkTo(methodOn(EmployeeController.class)
				.updateEmployee(request.getId(), request.getState())).withRel("updateEmployeeState");
		empDTO = transformer.createEmployeeDTO(employee).get();
		empDTO.add(addEmp).add(fetchEmployee).add(updateEmployeeState);
		
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), empDTO);
	}

	@Override
	public GenericResponseDTO<EmployeeDTO> fetchEmployee(Long id) {
		log.info("---->>> Commence retieving employee details with id: {}", id);
		EmployeeDTO empDTO = null;
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new CustomException(EMPLOYEE_RETRIEVAL_ERROR.getMessage()));
		
		Link addEmp = linkTo(methodOn(EmployeeController.class).addEmployee(new EmployeeRequestDTO()))
				.withRel("addNewEmployee");
		Link fetchEmployee = linkTo(methodOn(EmployeeController.class)
				.fetchEmployee(id)).withSelfRel();
		Link updateEmployeeState = linkTo(methodOn(EmployeeController.class)
				.updateEmployee(id, ADDED)).withRel("updateEmployeeState");
		empDTO = transformer.createEmployeeDTO(employee).get();
		empDTO.add(addEmp).add(fetchEmployee).add(updateEmployeeState);
		
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), empDTO);
	}

	@Override
	@Transactional
	public GenericResponseDTO<EmployeeDTO> updateEmployee(Long id, State state) {
		log.info("---->>> Commence updating employee with id: {} and status: {}", id, state);
		EmployeeDTO empDTO = null;
		Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new CustomException(EMPLOYEE_RETRIEVAL_ERROR.getMessage()));
		employee.setState(state);
		employee = employeeRepo.save(employee);
		
		if(employee.getId() == null) {
			throw new CustomException(UPDATE_EMPLOYEE_ERROR.getMessage());
		}
		
		Link addEmp = linkTo(methodOn(EmployeeController.class).addEmployee(new EmployeeRequestDTO()))
				.withRel("addNewEmployee");
		Link fetchEmployee = linkTo(methodOn(EmployeeController.class)
				.fetchEmployee(id)).withRel("fetchEmployee");
		Link updateEmployeeState = linkTo(methodOn(EmployeeController.class)
				.updateEmployee(id, state)).withSelfRel();
		empDTO = transformer.createEmployeeDTO(employee).get();
		empDTO.add(addEmp).add(fetchEmployee).add(updateEmployeeState);
		
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), empDTO);
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
	
	@Override
	public GenericResponseDTO<List<String>> contractType() {
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				configProcessor.getContractType());
	}
	
	@Override
	public GenericResponseDTO<List<String>> countries() {
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				configProcessor.getCountries());
	}
	
	@Override
	public GenericResponseDTO<List<String>> department() {
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				configProcessor.getDepartment());
	}
	
	@Override
	public GenericResponseDTO<List<String>> gender() {
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				configProcessor.getGender());
	}
	
	@Override
	public GenericResponseDTO<List<String>> maritalStatus() {
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				configProcessor.getMaritalStatus());
	}
	
	@Override
	public GenericResponseDTO<List<String>> qualification() {
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				configProcessor.getQualification());
	}
	
	@Override
	public GenericResponseDTO<List<String>> worktype() {
		return GenericResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				configProcessor.getWorkType());
	}
}
