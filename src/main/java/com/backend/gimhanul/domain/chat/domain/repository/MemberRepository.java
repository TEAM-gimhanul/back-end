package com.backend.gimhanul.domain.chat.domain.repository;

import java.util.Optional;

import com.backend.gimhanul.domain.chat.domain.Member;
import com.backend.gimhanul.domain.chat.domain.Room;
import com.backend.gimhanul.domain.user.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
	Optional<Member> findByUserAndRoom(User user, Room room);

	@Query("select distinct(m.room.id) from tbl_member m where m.user.id = :currentUser or m.user.id = :userId group by m.room.id having count(m.id) > 1")
	Optional<String> findRoomIdByUsers(String currentUser, String userId);
}
