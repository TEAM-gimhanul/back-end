package com.backend.gimhanul.domain.message.domain;

import com.backend.gimhanul.domain.member.domain.Member;
import com.backend.gimhanul.domain.room.domain.Room;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_room_id")
    private Room room;

    @Builder
    public Message (String message, Member member, Room room) {
        this.message = message;
        this.member = member;
        this.room = room;
    }
}
