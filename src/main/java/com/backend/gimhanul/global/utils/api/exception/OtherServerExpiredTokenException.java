package com.backend.gimhanul.global.utils.api.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class OtherServerExpiredTokenException extends HanulException {

	public static final HanulException EXCEPTION =
			new OtherServerExpiredTokenException();

	private OtherServerExpiredTokenException() {
		super(ErrorCode.OTHER_SERVER_EXPIRED_TOKEN);
	}

}
