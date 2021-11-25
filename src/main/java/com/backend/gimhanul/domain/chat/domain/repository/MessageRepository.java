package com.backend.gimhanul.domain.chat.domain.repository;

import java.util.List;
import java.util.Optional;

import com.backend.gimhanul.domain.chat.domain.Message;
import com.backend.gimhanul.domain.chat.domain.Room;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

	@Query(value = "SELECT * FROM tbl_message m WHERE m.fk_room_id = :roomId ORDER BY id DESC LIMIT 1", nativeQuery = true)
	Optional<Message> findTopByRoom(Long roomId);

	List<Message> findByRoom(Room room);
}
