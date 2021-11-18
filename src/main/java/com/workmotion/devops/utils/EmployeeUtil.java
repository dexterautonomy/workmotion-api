package com.workmotion.devops.utils;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

import com.workmotion.devops.repository.EmployeeRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeUtil {
	private final EmployeeRepo employeeRepo;
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

//	public static void main(String[] args) {
//		System.out.println(getEmpId());
//	}
	
	public String getEmpId() {
		String empNumber;
		
		do {
			empNumber = generateEmpID();
		}
		while(employeeRepo.existsByEmployeeNumber(empNumber).get());
		
		return empNumber;
	}
}
