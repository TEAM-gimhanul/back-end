package com.backend.gimhanul.global.security.jwt.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class TokenBadRequestException  extends HanulException {

    public static final HanulException EXCEPTION =
            new TokenBadRequestException();

    private TokenBadRequestException() {
        super(ErrorCode.TOKEN_BAD_REQUEST);
    }

}

