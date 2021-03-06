package com.workmotion.devops.dto;

import org.springframework.hateoas.RepresentationModel;

import com.workmotion.devops.enums.State;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeDTO extends RepresentationModel<EmployeeDTO> {
	private Long id;
	private BasicInfoDTO basicInfo;
	private ContactInfoDTO contactInfo;
	private ContractInfoDTO contractInfo;
	private State state;
	
	public static EmployeeDTOBuilder builder() {
		return new EmployeeDTOBuilder();
	}
}
