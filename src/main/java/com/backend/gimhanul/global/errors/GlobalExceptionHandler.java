package com.backend.gimhanul.global.errors;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(HanulException.class)
	public ResponseEntity<ErrorResponse> hanulExceptionHandler(HanulException e) {
		ErrorCode code = e.getErrorCode();
		return new ResponseEntity<>(new ErrorResponse(code.getStatus(), code.getCode(), code.getMessage()),
				HttpStatus.valueOf(code.getStatus()));
	}

}
