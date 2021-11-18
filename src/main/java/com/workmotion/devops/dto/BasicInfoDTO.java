package com.workmotion.devops.dto;

import static com.workmotion.devops.enums.Qualification.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import com.workmotion.devops.enums.Gender;
import com.workmotion.devops.enums.MaritalStatus;
import com.workmotion.devops.enums.Qualification;

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
	@Min(value = 18L, message = "Must be greater than 18")
	@Max(value = 40L, message = "Must be less than 40")	
	private int age;
	private Qualification qualification = TECHNICAL;
	private Gender gender;
	private MaritalStatus maritalStatus;
}
