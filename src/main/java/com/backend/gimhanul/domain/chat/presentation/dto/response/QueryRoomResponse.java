package com.backend.gimhanul.domain.chat.presentation.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QueryRoomResponse {

	private final long roomId;
	private final String name;
	private final String profileImage;
	private final String lastMessage;
	private final LocalDateTime sentAt;

}
