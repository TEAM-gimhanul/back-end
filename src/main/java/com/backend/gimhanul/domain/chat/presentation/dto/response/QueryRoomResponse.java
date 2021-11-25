package com.backend.gimhanul.domain.chat.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QueryRoomResponse {

	private final long roomId;
	private final String name;

}
