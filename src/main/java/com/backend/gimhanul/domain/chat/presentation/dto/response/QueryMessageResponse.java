package com.backend.gimhanul.domain.chat.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QueryMessageResponse {

	private final Long roomId;
	private final Long messageId;
	private final String message;
	private final String name;
	private final String profileImage;

}
