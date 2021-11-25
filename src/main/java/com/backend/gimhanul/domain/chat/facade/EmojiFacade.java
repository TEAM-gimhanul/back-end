package com.backend.gimhanul.domain.chat.facade;

import com.backend.gimhanul.domain.chat.domain.Emoji;
import com.backend.gimhanul.domain.chat.domain.repository.EmojiRepository;
import com.backend.gimhanul.domain.chat.exception.EmojiNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmojiFacade {

    private final EmojiRepository emojiRepository;

    public Emoji findEmojiById(Long id) {
        return emojiRepository.findById(id)
                .orElseThrow(() -> EmojiNotFoundException.EXCEPTION);
    }
}
