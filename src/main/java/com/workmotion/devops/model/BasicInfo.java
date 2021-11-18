package com.workmotion.devops.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workmotion.devops.enums.Gender;
import com.workmotion.devops.enums.MaritalStatus;
import com.workmotion.devops.enums.Qualification;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BasicInfo extends CommonFields {
	private String firstName;
	private String lastName;
	private String middleName;
	private int age;
	@Enumerated(EnumType.STRING)
	private Qualification qualification;
	@OneToOne
	@JsonIgnore
	private Employee employee;
	private Gender gender;
	private MaritalStatus maritalStatus;
}
