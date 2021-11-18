package com.workmotion.devops.dto;

import static com.workmotion.devops.enums.State.ADDED;

import javax.validation.Valid;

import com.workmotion.devops.enums.State;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EmployeeRequestDTO {
	private Long id;
	private @Valid BasicInfoDTO basicInfo;
	private @Valid ContactInfoDTO contactInfo;
	private @Valid ContractInfoDTO contractInfo;
	private State state = ADDED;
}
