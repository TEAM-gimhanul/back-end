package com.backend.gimhanul.global.utils.api.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class OtherServerBadRequestException extends HanulException {

	public static final HanulException EXCEPTION =
			new OtherServerBadRequestException();

	private OtherServerBadRequestException() {
		super(ErrorCode.)
	}

}
