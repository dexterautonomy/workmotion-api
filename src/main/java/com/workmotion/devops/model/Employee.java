package com.workmotion.devops.model;

import static com.workmotion.devops.enums.State.ADDED;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.workmotion.devops.enums.State;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Employee extends CommonFields {
	@OneToOne(cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER, mappedBy = "employee")
	private BasicInfo basicInfo;
	@OneToOne(cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER, mappedBy = "employee")
	private ContactInfo contactInfo;
	@OneToOne(cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER, mappedBy = "employee")
	private ContractInfo contractInfo;
	@Enumerated(EnumType.STRING)
	private State state = ADDED;
	private String employeeNumber;
}
