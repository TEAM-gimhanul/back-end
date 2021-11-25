package com.backend.gimhanul.domain.chat.facade;

import com.backend.gimhanul.domain.chat.domain.Room;
import com.backend.gimhanul.domain.chat.domain.repository.RoomRepository;
import com.backend.gimhanul.domain.chat.exception.RoomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomFacade {
    private final RoomRepository roomRepository;

    public Room findRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> RoomNotFoundException.EXCEPTION);
    }
}
