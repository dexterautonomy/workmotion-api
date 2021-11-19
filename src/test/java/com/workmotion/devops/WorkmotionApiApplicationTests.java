package com.workmotion.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.workmotion.devops.dto.BasicInfoDTO;
import com.workmotion.devops.dto.ContactInfoDTO;
import com.workmotion.devops.dto.ContractInfoDTO;
import com.workmotion.devops.dto.EmployeeDTO;
import com.workmotion.devops.dto.EmployeeRequestDTO;
import com.workmotion.devops.exception.CustomException;

import static com.workmotion.devops.enums.Gender.*;
import static com.workmotion.devops.enums.Country.*;
import static com.workmotion.devops.enums.ContractType.*;
import static com.workmotion.devops.enums.Department.*;
import static com.workmotion.devops.enums.State.*;
import static com.workmotion.devops.enums.Qualification.*;
import static com.workmotion.devops.enums.MaritalStatus.*;
import static com.workmotion.devops.enums.WorkType.*;
import com.workmotion.devops.service.EmployeeService;
import com.workmotion.devops.utils.EmployeeUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
@ExtendWith(SpringExtension.class)
class WorkmotionApiApplicationTests {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeUtil employeeUtil;
	private EmployeeRequestDTO employeeRequestDTO = null;
	private ContactInfoDTO contactInfoDTO = null;
	private BasicInfoDTO basicInfoDTO = null;
	private ContractInfoDTO contractInfoDTO = null;
	
	@BeforeEach
	public void init() {
		log.info("--->> Initializing basic model objects for test");
		
		basicInfoDTO = new BasicInfoDTO();
		basicInfoDTO.setAge(30);
		basicInfoDTO.setFirstName("Chinedu");
		basicInfoDTO.setLastName("Ezeigbo");
		basicInfoDTO.setGender(MALE);
		basicInfoDTO.setMiddleName("Bright");
		basicInfoDTO.setMaritalStatus(SINGLE);
		basicInfoDTO.setQualification(FIRST_DEGREE);
		
		log.info("--->> BasicInfoDTO object: {}", basicInfoDTO);
		
		contactInfoDTO = new ContactInfoDTO();
		contactInfoDTO.setAddress("47 Alpine Crescent, DRY Boulevard");
		contactInfoDTO.setCountryOfResidence(GER);
		contactInfoDTO.setEmail("dexterautonomy@yahoo.com");
		contactInfoDTO.setNationality(USA);
		contactInfoDTO.setPhoneNumber("+23470389456789");
		
		log.info("--->> ContactInfoDTO object: {}", contactInfoDTO);
		
		contractInfoDTO = new ContractInfoDTO();
		contractInfoDTO.setContractType(PART_TIME);
		contractInfoDTO.setWorkType(HYBRID);
		contractInfoDTO.setDepartment(HR);
		
		log.info("--->> ContractInfoDTO object: {}", contractInfoDTO);
		
		employeeRequestDTO = new EmployeeRequestDTO();
		employeeRequestDTO.setBasicInfo(basicInfoDTO);
		employeeRequestDTO.setContactInfo(contactInfoDTO);
		employeeRequestDTO.setContractInfo(contractInfoDTO);
		employeeRequestDTO.setState(ADDED);
		employeeService.createEmployee(employeeRequestDTO).getData();
		
		log.info("--->> EmployeeRequestDTO object: {}", employeeRequestDTO);
	}
	
	@Test
	public void createEmployeeTest() {
		EmployeeDTO employee = employeeService.createEmployee(employeeRequestDTO).getData();
		assertTrue(employee.getId() != null);
		assertTrue(employee.getBasicInfo() != null);
		assertEquals("Chinedu", employee.getBasicInfo().getFirstName());
		assertEquals(HR, employee.getContractInfo().getDepartment());
		assertEquals(GER, employee.getContactInfo().getCountryOfResidence());
	}
	
	@Test
	public void fetchEmployeeTest() {
		assertThrows(CustomException.class, () -> employeeService.fetchEmployee(0L).getData());
		EmployeeDTO employee = employeeService.fetchEmployee(1L).getData();
		assertTrue(employee.getBasicInfo() != null);
		assertEquals("Chinedu", employee.getBasicInfo().getFirstName());
		assertEquals(HR, employee.getContractInfo().getDepartment());
		assertEquals(GER, employee.getContactInfo().getCountryOfResidence());
	}
	
	@Test
	public void updateEmployeeTest() {
		EmployeeDTO employee = employeeService.updateEmployee(1L, IN_CHECK).getData();
		assertTrue(employee.getId() != null);
		assertEquals(IN_CHECK, employee.getState());
	}
	
	@Test
	public void getEmpIdTest() {
		String empNumber = employeeUtil.getEmpId();
		assertNotNull(empNumber);
	}
}
















