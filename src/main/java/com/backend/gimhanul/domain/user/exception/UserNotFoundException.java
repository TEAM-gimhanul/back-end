package com.backend.gimhanul.domain.user.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class UserNotFoundException extends HanulException {

    public final static HanulException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
