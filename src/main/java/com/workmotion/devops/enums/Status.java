package com.workmotion.devops.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Status {
	ERROR(99, "Sorry an error occurred"), 
	SUCCESS(100, "Successful");

	private int code;
	private String message;
}