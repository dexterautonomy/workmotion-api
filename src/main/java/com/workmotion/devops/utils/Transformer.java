package com.workmotion.devops.utils;

import java.util.Optional;

import org.springframework.stereotype.Component;
import static com.workmotion.devops.enums.Status.*;

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
		basicInfo.setGender(basicInfoDTO.getGender());
		basicInfo.setLastName(basicInfoDTO.getLastName());
		basicInfo.setMiddleName(basicInfoDTO.getMiddleName());
		basicInfo.setMaritalStatus(basicInfoDTO.getMaritalStatus());
		basicInfo.setQualification(basicInfoDTO.getQualification());
		
		return Optional.of(basicInfo);
	}
	
	private Optional<ContactInfo> createContactInfo(ContactInfoDTO contactInfoDTO) {
		ContactInfo contactInfo = new ContactInfo();
		
		contactInfo.setAddress(contactInfoDTO.getAddress());
		contactInfo.setCountryOfResidence(contactInfoDTO.getCountryOfResidence());
		contactInfo.setEmail(contactInfoDTO.getEmail());
		contactInfo.setNationality(contactInfoDTO.getNationality());
		contactInfo.setPhoneNumber(contactInfoDTO.getPhoneNumber());
		
		return Optional.of(contactInfo);
	}

	private Optional<ContractInfo> createContractInfo(ContractInfoDTO contractInfoDTO) {
		ContractInfo contractInfo = new ContractInfo();
		
		contractInfo.setContractType(contractInfoDTO.getContractType());
		contractInfo.setDepartment(contractInfoDTO.getDepartment());
		contractInfo.setWorkType(contractInfoDTO.getWorkType());

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
