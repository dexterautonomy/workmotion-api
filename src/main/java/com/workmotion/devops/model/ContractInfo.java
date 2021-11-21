package com.workmotion.devops.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class ContractInfo extends CommonFields {
	private String workType;
	private String contractType;
	private String department;
	@OneToOne
	@JsonIgnore
	private Employee employee;
}
