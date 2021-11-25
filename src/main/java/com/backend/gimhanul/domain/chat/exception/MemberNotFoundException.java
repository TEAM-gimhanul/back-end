package com.backend.gimhanul.domain.chat.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class MemberNotFoundException extends HanulException {

	public static final HanulException EXCEPTION =
			new MemberNotFoundException();

	private MemberNotFoundException() {
		super(ErrorCode.MEMBER_NOT_FOUND);
	}

}
