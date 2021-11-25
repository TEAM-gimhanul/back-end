package com.backend.gimhanul.domain.chat.domain.facade;

import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.chat.domain.Member;
import com.backend.gimhanul.domain.chat.domain.Room;
import com.backend.gimhanul.domain.chat.domain.repository.MemberRepository;
import com.backend.gimhanul.domain.chat.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberRepository memberRepository;


    public Member findMemberByUserAndRoom(User user, Room room) {
        return memberRepository.findByUserAndRoom(user, room)
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);
    }
}
