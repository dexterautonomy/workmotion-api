package com.workmotion.devops.model;

import static com.workmotion.devops.enums.WorkType.*;
import static com.workmotion.devops.enums.ContractType.*;
import static com.workmotion.devops.enums.Department.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workmotion.devops.enums.ContractType;
import com.workmotion.devops.enums.Department;
import com.workmotion.devops.enums.WorkType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class ContractInfo extends CommonFields {
	@Enumerated(EnumType.STRING)
	private WorkType workType = REMOTE;
	@Enumerated(EnumType.STRING)
	private ContractType contractType = PERMANENT;
	@Enumerated(EnumType.STRING)
	private Department department = IT;
	@OneToOne
	@JsonIgnore
	private Employee employee;
}
