package com.backend.gimhanul.domain.chat.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "tbl_swear")
public class Swear {

	@Id
	private String id;

	private int count;

	private boolean isSwear;

	private String replaceMessage;

	@Builder
	public Swear(String id, int count, boolean isSwear, String replaceMessage) {
		this.id = id;
		this.count = count;
		this.isSwear = isSwear;
		this.replaceMessage = replaceMessage;
	}

	public void increaseCount() {
		this.count ++;
	}

}
