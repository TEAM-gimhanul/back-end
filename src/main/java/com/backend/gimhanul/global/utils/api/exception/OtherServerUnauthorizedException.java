package com.backend.gimhanul.global.utils.api.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class OtherServerUnauthorizedException extends HanulException {

	public static final HanulException EXCEPTION =
			new OtherServerUnauthorizedException();

	private OtherServerUnauthorizedException() {
		super(ErrorCode.OTHER_SERVER_UNAUTHORIZED);
	}

}
