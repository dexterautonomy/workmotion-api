package com.workmotion.devops.dto;

import com.workmotion.devops.enums.Country;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ContactInfoDTO {
	private Long id;
	private String address;
	private String phoneNumber;
	private String email;
	private Country nationality;
	private Country countryOfResidence;
}
