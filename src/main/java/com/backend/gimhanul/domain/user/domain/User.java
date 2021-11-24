package com.backend.gimhanul.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_user")
public class User {

    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column(name = "profile_image")
    private String profileImage;

    @Column
    private Long count;

    @Builder
    public User(String name, Long count) {
        this.name = name;
        this.count = count;
    }
}
