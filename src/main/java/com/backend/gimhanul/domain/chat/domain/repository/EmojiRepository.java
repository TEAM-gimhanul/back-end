package com.backend.gimhanul.domain.chat.domain.repository;

import com.backend.gimhanul.domain.chat.domain.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmojiRepository extends JpaRepository<Emoji, Long> {

    Optional<Emoji> findById(Long id);
}
