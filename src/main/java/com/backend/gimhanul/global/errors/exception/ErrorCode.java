package com.backend.gimhanul.global.errors.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = Shape.OBJECT)
public enum ErrorCode {

	BAD_REQUEST(400, "HANUL-400-1", "Badreqeust"),

	OTHER_SERVER_BAD_REQUEST(400, "FEIGN-400-1", "Other server bad reqeust"),
	OTHER_SERVER_UNAUTHORIZED(401, "FEIGN-401-1", "Other server unauthorized"),
	OTHER_SERVER_FORBIDDEN(403, "FEIGN-403-1", "Other server forbidden"),
	OTHER_SERVER_EXPIRED_TOKEN(419, "FEIGN-419-1", "Other server expired token");




	private final int status;
	private final String code;
	private final String message;

}
