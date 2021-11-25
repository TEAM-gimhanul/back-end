package com.backend.gimhanul.domain.chat.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class RoomNotFoundException extends HanulException {

	public static final HanulException EXCEPTION =
			new RoomNotFoundException();

	private RoomNotFoundException() {
		super(ErrorCode.ROOM_NOT_FOUND);
	}

}
