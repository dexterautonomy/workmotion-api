package com.workmotion.devops.dto;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@SuppressWarnings("deprecation")
public class ContractInfoDTO {
	private Long id;
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String workType = "REMOTE";
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String contractType = "PERMANENT";
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String department = "SALES";
}
