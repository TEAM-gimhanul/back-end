package com.backend.gimhanul.domain.room.domain.repository;

import com.backend.gimhanul.domain.room.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
