package com.workmotion.devops.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Status {
	ERROR(99, "Sorry an error occurred"), 
	SUCCESS(100, "Successful"),
	BASIC_INFO_ERROR(13, "Basic infor error"),
	CONTACT_INFO_ERROR(14, "Contact info error"),
	CONTRACT_INFO_ERROR(15, "Contract info error"),
	EMPLOYEE_CREATION(16, "Error creating employee object"),
	SAVE_EMPLOYEE_ERROR(17, "Error persisting employee object"),
	EMPLOYEE_RETRIEVAL_ERROR(18, "Error retrieving employee"),
	UPDATE_EMPLOYEE_ERROR(17, "Error updating employee object");

	private int code;
	private String message;
}