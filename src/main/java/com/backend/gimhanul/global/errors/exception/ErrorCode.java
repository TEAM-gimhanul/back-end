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
	CREDENTIALS_NOT_FOUND(401, "USER-401-1", "Credentials not found."),

	USER_NOT_FOUND(404, "USER-404-1", "User not found."),

	TOKEN_BAD_REQUEST(400, "TOKEN-400-1", "Token is not served"),
	TOKEN_FORGED_ERROR(403, "TOKEN-403-1", "Token is forged"),
	TOKEN_IS_EXPIRE(410, "TOKEN-410-1", "Token is expire"),

	ROOM_NOT_FOUND(404, "CHAT-404-1", "Room not found"),
	MEMBER_NOT_FOUND(404, "CHAT-404-2", "Member not found"),

	OTHER_SERVER_BAD_REQUEST(400, "FEIGN-400-1", "Other server bad reqeust"),
	OTHER_SERVER_UNAUTHORIZED(401, "FEIGN-401-1", "Other server unauthorized"),
	OTHER_SERVER_FORBIDDEN(403, "FEIGN-403-1", "Other server forbidden"),
	OTHER_SERVER_EXPIRED_TOKEN(419, "FEIGN-419-1", "Other server expired token"),

	SERVER_ERROR(500, "HANUL-500-1", "Sever error occured");




	private final int status;
	private final String code;
	private final String message;

}
