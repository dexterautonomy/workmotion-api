package com.workmotion.devops.utils;

import java.util.Optional;

import org.springframework.stereotype.Component;
import static com.workmotion.devops.enums.Status.*;
import static com.workmotion.devops.enums.EntryType.*;

import com.workmotion.devops.dto.BasicInfoDTO;
import com.workmotion.devops.dto.ContactInfoDTO;
import com.workmotion.devops.dto.ContractInfoDTO;
import com.workmotion.devops.dto.EmployeeDTO;
import com.workmotion.devops.dto.EmployeeRequestDTO;
import com.workmotion.devops.exception.CustomException;
import com.workmotion.devops.model.BasicInfo;
import com.workmotion.devops.model.ContactInfo;
import com.workmotion.devops.model.ContractInfo;
import com.workmotion.devops.model.Employee;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Transformer {
	private final EmployeeUtil employeeUtil;
	
	public Optional<Employee> create(EmployeeRequestDTO requestDTO) {
		Employee employee = new Employee();
		employee.setEmployeeNumber(employeeUtil.getEmpId());
		employee.setBasicInfo(createBasicInfo(requestDTO.getBasicInfo())
				.orElseThrow(() -> new CustomException(BASIC_INFO_ERROR.getMessage())));
		employee.setContactInfo(createContactInfo(requestDTO.getContactInfo())
				.orElseThrow(() -> new CustomException(CONTACT_INFO_ERROR.getMessage())));
		employee.setContractInfo(createContractInfo(requestDTO.getContractInfo())
				.orElseThrow(() -> new CustomException(CONTRACT_INFO_ERROR.getMessage())));
		
		return Optional.of(employee);
	}
	
	private Optional<BasicInfo> createBasicInfo(BasicInfoDTO basicInfoDTO) {
		BasicInfo basicInfo = new BasicInfo();
		
		basicInfo.setAge(basicInfoDTO.getAge());
		basicInfo.setFirstName(basicInfoDTO.getFirstName());
		basicInfo.setLastName(basicInfoDTO.getLastName());
		basicInfo.setMiddleName(basicInfoDTO.getMiddleName());
		basicInfo.setGender(employeeUtil.validateEntry(basicInfoDTO.getGender(), GENDER)
				.orElseThrow(() -> new CustomException("No such gender. Get available list "
						+ "of gender types from 'get-genders' API")));
		basicInfo.setMaritalStatus(employeeUtil.validateEntry(basicInfoDTO.getMaritalStatus(), 
				MARITAL_STATUS)
				.orElseThrow(() -> new CustomException("No such marital status. Get available "
						+ "list of marital status from 'get-marital-statuses' API")));
		basicInfo.setQualification(employeeUtil.validateEntry(basicInfoDTO.getQualification(),
				QUALIFICATION)
				.orElseThrow(() -> new CustomException("No such qualification. Get available "
						+ "list of work qualifications from 'get-qualifications' API")));
		
		return Optional.of(basicInfo);
	}
	
	private Optional<ContactInfo> createContactInfo(ContactInfoDTO contactInfoDTO) {
		ContactInfo contactInfo = new ContactInfo();
		
		contactInfo.setAddress(contactInfoDTO.getAddress());
		contactInfo.setEmail(contactInfoDTO.getEmail());
		contactInfo.setCountryOfResidence(employeeUtil.validateEntry(contactInfoDTO.getCountryOfResidence(),
				RESIDENCE_COUNTRY)
				.orElseThrow(() -> new CustomException("No such country of residence. Get available list of countries "
						+ "from 'get-approved-countries' API")));
		contactInfo.setNationality(employeeUtil.validateEntry(contactInfoDTO.getNationality(),
				NATIONALITY)
				.orElseThrow(() -> new CustomException("No such nationality. Get available list of "
						+ "nationality from 'get-approved-countries' API")));
		contactInfo.setPhoneNumber(contactInfoDTO.getPhoneNumber());
		
		return Optional.of(contactInfo);
	}

	private Optional<ContractInfo> createContractInfo(ContractInfoDTO contractInfoDTO) {
		ContractInfo contractInfo = new ContractInfo();
		
		contractInfo.setContractType(employeeUtil.validateEntry(contractInfoDTO.getContractType(),
				CONTRACT_TYPE)
				.orElseThrow(() -> new CustomException("No such contract type. Get available list of "
						+ "contract types from 'get-contract-types' API")));
		contractInfo.setDepartment(employeeUtil.validateEntry(contractInfoDTO.getDepartment(),
				DEPARTMENT)
				.orElseThrow(() -> new CustomException("No such department. Get available list of "
						+ "departments from 'get-departments' API")));
		contractInfo.setWorkType(employeeUtil.validateEntry(contractInfoDTO.getWorkType(), 
				WORK_TYPE)
				.orElseThrow(() -> new CustomException("No such work type. Get available list of "
						+ "work types from 'get-work-types' API")));

		return Optional.of(contractInfo);
	}
	
	public Optional<EmployeeDTO> createEmployeeDTO(Employee employee_) {
		EmployeeDTO employee = EmployeeDTO.builder().basicInfo(createBasicInfoDTO(employee_.getBasicInfo())
				.orElseThrow(() -> new CustomException(BASIC_INFO_ERROR.getMessage())))
				.contactInfo(createContactInfoDTO(employee_.getContactInfo())
						.orElseThrow(() -> new CustomException(CONTACT_INFO_ERROR.getMessage())))
				.contractInfo(createContractInfoDTO(employee_.getContractInfo())
						.orElseThrow(() -> new CustomException(CONTRACT_INFO_ERROR.getMessage())))
				.id(employee_.getId())
				.state(employee_.getState())
				.build();
		
		return Optional.of(employee);
	}
	
	private Optional<BasicInfoDTO> createBasicInfoDTO(BasicInfo basicInfo) {
		BasicInfoDTO basicInfoDTO = new BasicInfoDTO();
		
		basicInfoDTO.setId(basicInfo.getId());
		basicInfoDTO.setAge(basicInfo.getAge());
		basicInfoDTO.setFirstName(basicInfo.getFirstName());
		basicInfoDTO.setGender(basicInfo.getGender());
		basicInfoDTO.setLastName(basicInfo.getLastName());
		basicInfoDTO.setMiddleName(basicInfo.getMiddleName());
		basicInfoDTO.setMaritalStatus(basicInfo.getMaritalStatus());
		basicInfoDTO.setQualification(basicInfo.getQualification());
		
		return Optional.of(basicInfoDTO);
	}
	
	private Optional<ContactInfoDTO> createContactInfoDTO(ContactInfo contactInfo) {
		ContactInfoDTO contactInfoDTO = new ContactInfoDTO();
		
		contactInfoDTO.setId(contactInfo.getId());
		contactInfoDTO.setAddress(contactInfo.getAddress());
		contactInfoDTO.setCountryOfResidence(contactInfo.getCountryOfResidence());
		contactInfoDTO.setEmail(contactInfo.getEmail());
		contactInfoDTO.setNationality(contactInfo.getNationality());
		contactInfoDTO.setPhoneNumber(contactInfo.getPhoneNumber());
		
		return Optional.of(contactInfoDTO);
	}

	private Optional<ContractInfoDTO> createContractInfoDTO(ContractInfo contractInfo) {
		ContractInfoDTO contractInfoDTO = new ContractInfoDTO();
		
		contractInfoDTO.setId(contractInfo.getId());
		contractInfoDTO.setContractType(contractInfo.getContractType());
		contractInfoDTO.setDepartment(contractInfo.getDepartment());
		contractInfoDTO.setWorkType(contractInfo.getWorkType());

		return Optional.of(contractInfoDTO);
	}
}
