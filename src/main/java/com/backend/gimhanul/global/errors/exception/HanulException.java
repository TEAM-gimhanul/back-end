package com.backend.gimhanul.global.errors.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HanulException extends RuntimeException {

	private final ErrorCode errorCode;

}
