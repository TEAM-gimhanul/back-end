package com.backend.gimhanul.domain.chat.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendChatRequest {

	private String roomId;
	private String message;

}
