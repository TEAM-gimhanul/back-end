package com.backend.gimhanul.domain.member.domain;

import com.backend.gimhanul.domain.room.domain.Room;
import com.backend.gimhanul.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_member")
public class Member {

  @Id
  @Column
  private Long idx;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_room_idx")
  private Room room;
}
