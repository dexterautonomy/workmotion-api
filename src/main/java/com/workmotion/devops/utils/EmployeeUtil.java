package com.workmotion.devops.utils;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

@Component
public class EmployeeUtil {
	private static AtomicReference<Long> currentTime = 
			new AtomicReference<>(System.currentTimeMillis());
	
	public static synchronized String requestId() {
		
		Long prev;
		Long next = System.currentTimeMillis();
		do {
			prev = currentTime.get();
			next = next > prev ? next : prev + 1;
		} while (!currentTime.compareAndSet(prev, next));
		
		return "WM" + String.valueOf(next);
	}

	public static void main(String[] args) {
		System.out.println(requestId());
	}
}
