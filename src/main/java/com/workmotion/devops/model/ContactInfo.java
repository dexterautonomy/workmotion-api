package com.workmotion.devops.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workmotion.devops.enums.Country;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class ContactInfo extends CommonFields {
	private String address;
	private String phoneNumber;
	private String email;
	@Enumerated(EnumType.STRING)
	private Country nationality;
	@Enumerated(EnumType.STRING)
	private Country countryOfResidence;
	@OneToOne
	@JsonIgnore
	private Employee employee;
}
