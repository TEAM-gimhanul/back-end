package com.backend.gimhanul.domain.user.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class CredentialsNotFoundException extends HanulException {

	public final static HanulException EXCEPTION =
			new CredentialsNotFoundException();

	private CredentialsNotFoundException() {
		super(ErrorCode.CREDENTIALS_NOT_FOUND);
	}

}
