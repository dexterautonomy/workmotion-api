package com.workmotion.devops.dto;

import static com.workmotion.devops.enums.ContractType.PERMANENT;
import static com.workmotion.devops.enums.Department.IT;
import static com.workmotion.devops.enums.WorkType.REMOTE;

import com.workmotion.devops.enums.ContractType;
import com.workmotion.devops.enums.Department;
import com.workmotion.devops.enums.WorkType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ContractInfoDTO {
	private Long id;
	private WorkType workType = REMOTE;
	private ContractType contractType = PERMANENT;
	private Department department = IT;
}
