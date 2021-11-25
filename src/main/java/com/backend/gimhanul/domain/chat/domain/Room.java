package com.backend.gimhanul.domain.chat.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Builder
    public Room(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
	private Set<Member> members = new HashSet<>();

    public String getUsername(String id) {
    	return members.stream().filter(member -> !member.getUser().getId().equals(id))
				.collect(Collectors.toList()).get(0).getUser().getName();
	}

}
