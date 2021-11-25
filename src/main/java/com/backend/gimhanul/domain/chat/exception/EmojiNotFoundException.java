package com.backend.gimhanul.domain.chat.exception;

import com.backend.gimhanul.global.errors.exception.ErrorCode;
import com.backend.gimhanul.global.errors.exception.HanulException;

public class EmojiNotFoundException extends HanulException {

    public static final HanulException EXCEPTION =
            new EmojiNotFoundException();

    private EmojiNotFoundException() {
        super(ErrorCode.EMOJI_NOT_FOUND);
    }
}
