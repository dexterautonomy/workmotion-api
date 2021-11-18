package com.workmotion.devops.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import com.workmotion.devops.enums.Country;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@SuppressWarnings("deprecation")
@RequiredArgsConstructor
public class ContactInfoDTO {
	private Long id;
	@NotBlank(message = "Must not be blank")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String address;
	@NotBlank(message = "Must not be blank")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String phoneNumber;
	@Email(message = "Must be a valid email")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String email;
	private Country nationality;
	private Country countryOfResidence;
}
