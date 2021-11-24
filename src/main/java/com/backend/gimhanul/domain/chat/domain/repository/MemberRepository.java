package com.backend.gimhanul.domain.chat.domain.repository;

import com.backend.gimhanul.domain.chat.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
}
