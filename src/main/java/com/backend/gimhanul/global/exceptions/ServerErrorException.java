package com.backend.gimhanul.global.exceptions;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;
import com.backend.gimhanul.global.security.jwt.exception.TokenBadRequestException;

public class ServerErrorException extends HanulException {

    public static final HanulException EXCEPTION =
            new ServerErrorException();

    private ServerErrorException() {
        super(ErrorCode.SERVER_ERROR);
    }
}