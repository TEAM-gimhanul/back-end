package com.backend.gimhanul.domain.chat.presentation;

import com.backend.gimhanul.domain.chat.presentation.dto.request.SendChatRequest;
import com.backend.gimhanul.domain.chat.service.SendChatService;
import com.backend.gimhanul.global.socket.annotation.SocketController;
import com.backend.gimhanul.global.socket.annotation.SocketMapping;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;

@SocketController
@RequiredArgsConstructor
public class ChatController {

	private final SendChatService sendChatService;


	@SocketMapping(endpoint = "message", requestCls = SendChatRequest.class)
	public void sendChat(SocketIOClient client, SocketIOServer server, SendChatRequest request) {
		sendChatService.execute(client, server, request);
	}

}
