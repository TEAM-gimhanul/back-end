package com.backend.gimhanul.domain.message.service;

import com.backend.gimhanul.domain.message.domain.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
}
