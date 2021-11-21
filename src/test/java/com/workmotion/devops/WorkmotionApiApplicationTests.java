package com.workmotion.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.workmotion.devops.config.ConfigProcessor;
import com.workmotion.devops.dto.BasicInfoDTO;
import com.workmotion.devops.dto.ContactInfoDTO;
import com.workmotion.devops.dto.ContractInfoDTO;
import com.workmotion.devops.dto.EmployeeDTO;
import com.workmotion.devops.dto.EmployeeRequestDTO;
import com.workmotion.devops.exception.CustomException;

import static com.workmotion.devops.enums.State.*;
import com.workmotion.devops.service.EmployeeService;
import com.workmotion.devops.utils.EmployeeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class WorkmotionApiApplicationTests {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeUtil employeeUtil;
	@Autowired
	private ConfigProcessor configProcessor;
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
		basicInfoDTO.setGender("MALE");
		basicInfoDTO.setMiddleName("Bright");
		basicInfoDTO.setMaritalStatus("SINGLE");
		basicInfoDTO.setQualification("FIRST_DEGREE");
		
		log.info("--->> BasicInfoDTO object: {}", basicInfoDTO);
		
		contactInfoDTO = new ContactInfoDTO();
		contactInfoDTO.setAddress("1995 Solid Alpine Crescent, DRY Boulevard, WORA Avenue");
		contactInfoDTO.setCountryOfResidence("GERMANY");
		contactInfoDTO.setEmail("dexterautonomy@yahoo.com");
		contactInfoDTO.setNationality("GERMANY");
		contactInfoDTO.setPhoneNumber("+23470389456789");
		
		log.info("--->> ContactInfoDTO object: {}", contactInfoDTO);
		
		contractInfoDTO = new ContractInfoDTO();
		contractInfoDTO.setContractType("PART_TIME");
		contractInfoDTO.setWorkType("HYBRID");
		contractInfoDTO.setDepartment("HR");
		
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
		assertEquals("HR", employee.getContractInfo().getDepartment());
		assertEquals("GERMANY", employee.getContactInfo().getCountryOfResidence());
	}
	
	@Test
	public void fetchEmployeeTest() {
		assertThrows(CustomException.class, () -> employeeService.fetchEmployee(0L).getData());
		EmployeeDTO employee = employeeService.fetchEmployee(1L).getData();
		assertTrue(employee.getBasicInfo() != null);
		assertEquals("Chinedu", employee.getBasicInfo().getFirstName());
		assertEquals("HR", employee.getContractInfo().getDepartment());
		assertEquals("GERMANY", employee.getContactInfo().getCountryOfResidence());
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
	
	@Test
	public void getContractTypeTest() {
		List<String> contractType = configProcessor.getContractType();
		contractType.stream().forEach(System.out::println);
		assertNotNull(contractType);
		assertTrue(contractType.size() > 0);
		assertTrue(contractType.contains("PERMANENT"));
	}
	
	@Test
	public void getCountriesTest() {
		List<String> countries = configProcessor.getCountries();
		countries.stream().forEach(System.out::println);
		assertNotNull(countries);
		assertTrue(countries.size() > 0);
		assertTrue(countries.contains("GERMANY"));
	}
	
	@Test
	public void getDepartmentTest() {
		List<String> department = configProcessor.getDepartment();
		department.stream().forEach(System.out::println);
		assertNotNull(department);
		assertTrue(department.size() > 0);
		assertTrue(department.contains("ACCOUNTING"));
	}
	
	@Test
	public void getGenderTest() {
		List<String> gender = configProcessor.getGender();
		gender.stream().forEach(System.out::println);
		assertNotNull(gender);
		assertTrue(gender.size() > 0);
		assertTrue(gender.contains("MALE"));
	}
	
	@Test
	public void getMaritalStatusTest() {
		List<String> maritalStatus = configProcessor.getMaritalStatus();
		maritalStatus.stream().forEach(System.out::println);
		assertNotNull(maritalStatus);
		assertTrue(maritalStatus.size() > 0);
		assertTrue(maritalStatus.contains("SINGLE"));
	}
	
	@Test
	public void getQualificationTest() {
		List<String> qualification = configProcessor.getQualification();
		qualification.stream().forEach(System.out::println);
		assertNotNull(qualification);
		assertTrue(qualification.size() > 0);
		assertTrue(qualification.contains("FIRST_DEGREE"));
	}
	
	@Test
	public void getWorkTypeTest() {
		List<String> workType = configProcessor.getWorkType();
		workType.stream().forEach(System.out::println);
		assertNotNull(workType);
		assertTrue(workType.size() > 0);
		assertTrue(workType.contains("ON_SITE"));
	}
}
















