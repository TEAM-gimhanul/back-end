package com.backend.gimhanul.global.utils.api.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class OtherServerForbiddenException extends HanulException {

	public static final HanulException EXCEPTION =
			new OtherServerForbiddenException();

	private OtherServerForbiddenException() {
		super(ErrorCode.OTHER_SERVER_FORBIDDEN);
	}

}
