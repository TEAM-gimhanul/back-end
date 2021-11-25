package com.backend.gimhanul.domain.chat.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class SwearNotFoundException extends HanulException {

	public static final HanulException EXCEPTION =
			new SwearNotFoundException();

	private SwearNotFoundException() {
		super(ErrorCode.SWEAR_NOT_FOUND);
	}

}
