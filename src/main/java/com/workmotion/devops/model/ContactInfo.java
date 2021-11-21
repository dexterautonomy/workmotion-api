package com.workmotion.devops.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class ContactInfo extends CommonFields {
	private String address;
	private String phoneNumber;
	private String email;
	private String nationality;
	private String countryOfResidence;
	@OneToOne
	@JsonIgnore
	private Employee employee;
}
