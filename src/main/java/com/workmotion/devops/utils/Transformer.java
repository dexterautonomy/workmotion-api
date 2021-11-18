package com.workmotion.devops.utils;

import org.springframework.stereotype.Component;

import com.workmotion.devops.dto.BasicInfoDTO;
import com.workmotion.devops.dto.ContactInfoDTO;
import com.workmotion.devops.dto.ContractInfoDTO;
import com.workmotion.devops.dto.EmployeeDTO;
import com.workmotion.devops.dto.EmployeeRequestDTO;
import com.workmotion.devops.model.BasicInfo;
import com.workmotion.devops.model.ContactInfo;
import com.workmotion.devops.model.ContractInfo;
import com.workmotion.devops.model.Employee;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Transformer {
	private final EmployeeUtil employeeUtil;
	
	public Employee create(EmployeeRequestDTO requestDTO) {
		Employee employee = new Employee();
		employee.setEmployeeNumber(employeeUtil.getEmpId());
		employee.setBasicInfo(createBasicInfo(requestDTO.getBasicInfo()));
		employee.setContactInfo(createContactInfo(requestDTO.getContactInfo()));
		employee.setContractInfo(createContractInfo(requestDTO.getContractInfo()));
		
		return employee;
	}
	
	private BasicInfo createBasicInfo(BasicInfoDTO basicInfoDTO) {
		BasicInfo basicInfo = new BasicInfo();
		
		basicInfo.setAge(basicInfo.getAge());
		basicInfo.setFirstName(basicInfoDTO.getFirstName());
		basicInfo.setGender(basicInfoDTO.getGender());
		basicInfo.setLastName(basicInfoDTO.getLastName());
		basicInfo.setMaritalStatus(basicInfoDTO.getMaritalStatus());
		basicInfo.setQualification(basicInfoDTO.getQualification());
		
		return basicInfo;
	}
	
	private ContactInfo createContactInfo(ContactInfoDTO contactInfoDTO) {
		ContactInfo contactInfo = new ContactInfo();
		
		contactInfo.setAddress(contactInfoDTO.getAddress());
		contactInfo.setCountryOfResidence(contactInfoDTO.getCountryOfResidence());
		contactInfo.setEmail(contactInfoDTO.getEmail());
		contactInfo.setNationality(contactInfoDTO.getNationality());
		contactInfo.setPhoneNumber(contactInfoDTO.getPhoneNumber());
		
		return contactInfo;
	}

	private ContractInfo createContractInfo(ContractInfoDTO contractInfoDTO) {
		ContractInfo contractInfo = new ContractInfo();
		
		contractInfo.setContractType(contractInfoDTO.getContractType());
		contractInfo.setDepartment(contractInfoDTO.getDepartment());
		contractInfo.setWorkType(contractInfoDTO.getWorkType());

		return contractInfo;
	}
	
	public EmployeeDTO createEmployeeDTO(Employee employee_) {
		EmployeeDTO employee = EmployeeDTO.builder().basicInfo(createBasicInfoDTO(employee_.getBasicInfo()))
				.contactInfo(createContactInfoDTO(employee_.getContactInfo()))
				.contractInfo(createContractInfoDTO(employee_.getContractInfo()))
				.id(employee_.getId())
				.state(employee_.getState())
				.build();
		
		return employee;
	}
	
	
	private BasicInfoDTO createBasicInfoDTO(BasicInfo basicInfo) {
		BasicInfoDTO basicInfoDTO = new BasicInfoDTO();
		
		basicInfoDTO.setAge(basicInfo.getAge());
		basicInfoDTO.setFirstName(basicInfo.getFirstName());
		basicInfoDTO.setGender(basicInfo.getGender());
		basicInfoDTO.setLastName(basicInfo.getLastName());
		basicInfoDTO.setMaritalStatus(basicInfo.getMaritalStatus());
		basicInfoDTO.setQualification(basicInfo.getQualification());
		
		return basicInfoDTO;
	}
	
	private ContactInfoDTO createContactInfoDTO(ContactInfo contactInfo) {
		ContactInfoDTO contactInfoDTO = new ContactInfoDTO();
		
		contactInfoDTO.setAddress(contactInfo.getAddress());
		contactInfoDTO.setCountryOfResidence(contactInfo.getCountryOfResidence());
		contactInfoDTO.setEmail(contactInfo.getEmail());
		contactInfoDTO.setNationality(contactInfo.getNationality());
		contactInfoDTO.setPhoneNumber(contactInfo.getPhoneNumber());
		
		return contactInfoDTO;
	}

	private ContractInfoDTO createContractInfoDTO(ContractInfo contractInfo) {
		ContractInfoDTO contractInfoDTO = new ContractInfoDTO();
		
		contractInfoDTO.setContractType(contractInfo.getContractType());
		contractInfoDTO.setDepartment(contractInfo.getDepartment());
		contractInfoDTO.setWorkType(contractInfo.getWorkType());

		return contractInfoDTO;
	}
}
