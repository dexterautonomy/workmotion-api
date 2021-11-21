package com.workmotion.devops.utils;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

import com.workmotion.devops.config.ConfigProcessor;
import com.workmotion.devops.enums.EntryType;
import com.workmotion.devops.repository.EmployeeRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeUtil {
	private final EmployeeRepo employeeRepo;
	private final ConfigProcessor configProcessor;
	private static AtomicReference<Long> currentTime = 
			new AtomicReference<>(System.currentTimeMillis());
	
	public static synchronized String generateEmpID() {
		Long prev;
		Long next = System.currentTimeMillis();
		do {
			prev = currentTime.get();
			next = next > prev ? next : prev + 1;
		} while (!currentTime.compareAndSet(prev, next));
		
		return "WM" + String.valueOf(next);
	}

	public String getEmpId() {
		String empNumber;
		
		do {
			empNumber = generateEmpID();
		}
		while(employeeRepo.existsByEmployeeNumber(empNumber).get());
		
		return empNumber;
	}

	public Optional<String> validateEntry(String entry, EntryType entryType) {
		entry = !entry.trim().isEmpty() ? entry.trim().toUpperCase() : "EMPTY";
		
		switch (entryType) {
			case CONTRACT_TYPE:
				return configProcessor.getContractType().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
	
			case DEPARTMENT: {
				return configProcessor.getDepartment().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
			}
	
			case NATIONALITY: {
				return configProcessor.getCountries().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
			}
	
			case WORK_TYPE: {
				return configProcessor.getWorkType().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
			}
	
			case MARITAL_STATUS: {
				return configProcessor.getMaritalStatus().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
			}
	
			case QUALIFICATION: {
				return configProcessor.getQualification().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
			}
	
			case RESIDENCE_COUNTRY: {
				return configProcessor.getCountries().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
			}
	
			default: 
				return configProcessor.getGender().contains(entry) ? Optional.of(entry) 
						: Optional.empty();
		}
	}
}
