package com.backend.gimhanul.global.errors.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = Shape.OBJECT)
public enum ErrorCode {

	BAD_REQUEST(400, "HANUL-400-1", "Badreqeust");

	private final int status;
	private final String code;
	private final String message;

}
