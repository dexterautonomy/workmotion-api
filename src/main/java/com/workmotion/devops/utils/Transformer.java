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

@Component
public class Transformer {
	public Employee create(EmployeeRequestDTO requestDTO) {
		Employee employee = new Employee();
		employee.setEmployeeNumber(employeeNumber);
		employee.setBasicInfo(createBasicInfo(requestDTO.getBasicInfo()));
		employee.setContactInfo(createContactInfo(requestDTO.getContactInfo()));
		employee.setContractInfo(createContractInfo(requestDTO.getContractInfo()));
		
		return null;
	}
	
	public EmployeeDTO create(Employee employee) {
		return null;
	}
	
	private BasicInfo createBasicInfo(BasicInfoDTO basicInfoDTO_) {
		BasicInfo basicInfo = new BasicInfo();
		
		basicInfo.setAge(basicInfo.getAge());
		basicInfo.setFirstName(basicInfoDTO_.getFirstName());
		basicInfo.setGender(basicInfoDTO_.getGender());
		basicInfo.setLastName(basicInfoDTO_.getLastName());
		basicInfo.setMaritalStatus(basicInfoDTO_.getMaritalStatus());
		basicInfo.setQualification(basicInfoDTO_.getQualification());
		
		return basicInfo;
	}
	
	private ContactInfo createContactInfo(ContactInfoDTO contactInfo_) {
		ContactInfo contactInfo = new ContactInfo();
		
		contactInfo.setAddress(contactInfo_.getAddress());
		contactInfo.setCountryOfResidence(contactInfo_.getCountryOfResidence());
		contactInfo.setEmail(contactInfo_.getEmail());
		contactInfo.setNationality(contactInfo_.getNationality());
		contactInfo.setPhoneNumber(contactInfo_.getPhoneNumber());
		
		return contactInfo;
	}

	private ContractInfo createContractInfo(ContractInfoDTO contractInfo_) {
		ContractInfo contractInfo = new ContractInfo();
		
		contractInfo.setContractType(contractInfo_.getContractType());
		contractInfo.setDepartment(contractInfo_.getDepartment());
		contractInfo.setWorkType(contractInfo_.getWorkType());

		return contractInfo;
	}
}
