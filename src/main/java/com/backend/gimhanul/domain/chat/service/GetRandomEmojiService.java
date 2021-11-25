package com.backend.gimhanul.domain.chat.service;

import com.backend.gimhanul.domain.chat.domain.Emoji;
import com.backend.gimhanul.domain.chat.facade.EmojiFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class GetRandomEmojiService {

    private final EmojiFacade emojiFacade;

    private static final int DATA_MAX_ID = 130;

    public Emoji getRandomEmoji () {
        return emojiFacade.findEmojiById(
                (long) ThreadLocalRandom.current().nextInt(1, DATA_MAX_ID + 1)
        );
    }
}
