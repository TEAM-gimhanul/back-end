package com.backend.gimhanul.global.security.jwt.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class TokenForgedException extends HanulException {

    public static final HanulException EXCEPTION =
            new TokenForgedException();

    private TokenForgedException() {
        super(ErrorCode.TOKEN_FORGED_ERROR);
    }

}