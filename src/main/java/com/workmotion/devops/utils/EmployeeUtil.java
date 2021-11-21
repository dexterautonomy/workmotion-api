package com.workmotion.devops.utils;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

import com.workmotion.devops.config.ConfigProcessor;
import com.workmotion.devops.enums.EntryType;
import com.workmotion.devops.repository.EmployeeRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeUtil {
	private final EmployeeRepo employeeRepo;
	private final ConfigProcessor configProcessor;
	private static AtomicReference<Long> currentTime = 
			new AtomicReference<>(System.currentTimeMillis());
	
	public static synchronized String generateEmpID() {
		log.info("--->> Generating employee number");
		
		Long prev;
		Long next = System.currentTimeMillis();
		do {
			prev = currentTime.get();
			next = next > prev ? next : prev + 1;
		} while (!currentTime.compareAndSet(prev, next));
		
		return "WM" + String.valueOf(next);
	}

	public String getEmpId() {
		log.info("--->> Commence generating employee number");
		
		String empNumber;
		
		do {
			empNumber = generateEmpID();
		}
		while(employeeRepo.existsByEmployeeNumber(empNumber).get());
		
		return empNumber;
	}

	public Optional<String> validateEntry(String entry, EntryType entryType) {
		log.info("--->> Commence validation of data entries");
		
		entry = !entry.trim().isEmpty() ? entry.trim().toUpperCase() : "EMPTY";
		
		switch (entryType) {
			case CONTRACT_TYPE:
				log.info("--->> Validating contract type entry");
				return configProcessor.getContractType().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
	
			case DEPARTMENT: {
				log.info("--->> Validating department entry");
				return configProcessor.getDepartment().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
			}
	
			case NATIONALITY: {
				log.info("--->> Validating nationality entry");
				return configProcessor.getCountries().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
			}
	
			case WORK_TYPE: {
				log.info("--->> Validating work type entry");
				return configProcessor.getWorkType().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
			}
	
			case MARITAL_STATUS: {
				log.info("--->> Validating marital status entry");
				return configProcessor.getMaritalStatus().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
			}
	
			case QUALIFICATION: {
				log.info("--->> Validating qualification entry");
				return configProcessor.getQualification().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
			}
	
			case RESIDENCE_COUNTRY: {
				log.info("--->> Validating residence country entry");
				return configProcessor.getCountries().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
			}
	
			default: 
				log.info("--->> Validating gender entry");
				return configProcessor.getGender().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
		}
	}
}
