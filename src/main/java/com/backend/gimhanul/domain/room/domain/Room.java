package com.backend.gimhanul.domain.room.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_room")
public class Room {

  @Id
  @Column
  private Long idx;

  @Column
  private String name;
}
