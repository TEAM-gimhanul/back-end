package com.backend.gimhanul.domain.chat.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinResponse {

	private final String name;
	private final Long roomId;

}
