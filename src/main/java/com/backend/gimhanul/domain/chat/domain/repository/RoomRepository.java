package com.backend.gimhanul.domain.chat.domain.repository;

import com.backend.gimhanul.domain.chat.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
