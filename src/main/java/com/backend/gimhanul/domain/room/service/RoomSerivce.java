package com.backend.gimhanul.domain.room.service;

import com.backend.gimhanul.domain.room.domain.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomSerivce {

  private final RoomRepository roomRepository;
}