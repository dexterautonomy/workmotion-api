package com.workmotion.devops.dto;

import static com.workmotion.devops.enums.Qualification.*;

import com.workmotion.devops.enums.Gender;
import com.workmotion.devops.enums.MaritalStatus;
import com.workmotion.devops.enums.Qualification;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BasicInfoDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private int age;
	private Qualification qualification = TECHNICAL;
	private Gender gender;
	private MaritalStatus maritalStatus;
}
