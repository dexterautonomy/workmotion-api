package com.workmotion.devops.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@Data
@RequiredArgsConstructor
public class BasicInfoDTO {
	private Long id;
	@NotBlank(message = "Must not be blank")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String firstName;
	@NotBlank(message = "Must not be blank")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String lastName;
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String middleName;
	@Min(value = 17L, message = "Must be greater than 17")
	@Max(value = 45L, message = "Must be less than 45")	
	private int age;
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String qualification = "HIGH_SCHOOL";
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String gender = "MALE";
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String maritalStatus = "SINGLE";
}
