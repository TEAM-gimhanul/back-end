package com.backend.gimhanul.global.security.jwt.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class TokenExpireException extends HanulException {

    public static final HanulException EXCEPTION =
            new TokenExpireException();

    private TokenExpireException() {
        super(ErrorCode.TOKEN_IS_EXPIRE);
    }

}
