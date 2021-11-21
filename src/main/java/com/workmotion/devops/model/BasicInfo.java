package com.workmotion.devops.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String qualification;
	@OneToOne
	@JsonIgnore
	private Employee employee;
	private String gender;
	private String maritalStatus;
}
