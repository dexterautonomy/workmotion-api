package com.workmotion.devops.dto;

import lombok.Getter;

@Getter
public class GenericResponseDTO<T> {
	private final int code;
	private final String message;
	private final T data;
	
	private GenericResponseDTO(int rCode, String message, T data) {
		this.code=rCode;
		this.message=message;
		this.data=data;
	}
	
	public static <T> GenericResponseDTO<T> newInstance(int rCode, String message, 
			T data){
		return new GenericResponseDTO<T>(rCode, message, data);
	}
}