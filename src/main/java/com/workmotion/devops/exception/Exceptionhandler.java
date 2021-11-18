package com.workmotion.devops.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.UnexpectedTypeException;
import static com.workmotion.devops.enums.Status.*;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.workmotion.devops.dto.GenericResponseDTO;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Exceptionhandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public GenericResponseDTO<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return GenericResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), errors);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public GenericResponseDTO<Map<String, String>> handleHttpExceptions(HttpClientErrorException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("error2", ex.getMessage());

		return GenericResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), errors);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public GenericResponseDTO<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		
		return GenericResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), error);
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public GenericResponseDTO<String> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append("method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		
		return GenericResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), builder.toString());
	}

	@ExceptionHandler(UnexpectedTypeException.class)
	public GenericResponseDTO<String> handleUnexpectedTypeException(UnexpectedTypeException ex) {
		return GenericResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage());
	}
	
	@ExceptionHandler(CustomException.class)
	public GenericResponseDTO<String> handleCustomException(CustomException ex) {
		return GenericResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage());
	}
}