package com.backend.gimhanul.domain.chat.domain.repository;

import java.util.List;

import com.backend.gimhanul.domain.chat.domain.Room;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
	@Query("select r.id from tbl_room r join tbl_member m on m.user.id = :id where r.id = m.room.id")
	List<String> findIdEmail(String id);
}
