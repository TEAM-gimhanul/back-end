package com.backend.gimhanul.domain.message.domain.repository;

import com.backend.gimhanul.domain.member.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Member, Long> {
}
