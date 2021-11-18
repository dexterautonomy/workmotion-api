package com.workmotion.devops.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum State {
	ADDED,
	IN_CHECK,
	APPROVED,
	ACTIVE;
}