package com.backend.gimhanul.domain.chat.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class InvalidArgumentException extends HanulException {

	public static final HanulException EXCEPTION =
			new InvalidArgumentException();

	private InvalidArgumentException() {
		super(ErrorCode.INVALID_ARGUMENT);
	}

}
