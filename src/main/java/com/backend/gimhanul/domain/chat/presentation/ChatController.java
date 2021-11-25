package com.backend.gimhanul.domain.chat.presentation;

import java.util.List;

import com.backend.gimhanul.domain.chat.exception.InvalidArgumentException;
import com.backend.gimhanul.domain.chat.presentation.dto.request.SendChatRequest;
import com.backend.gimhanul.domain.chat.presentation.dto.response.QueryRoomResponse;
import com.backend.gimhanul.domain.chat.service.JoinRoomService;
import com.backend.gimhanul.domain.chat.service.QueryRoomService;
import com.backend.gimhanul.domain.chat.service.SendChatService;
import com.backend.gimhanul.domain.chat.service.SubscribeAllService;
import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.global.annotation.UserLoginToken;
import com.backend.gimhanul.global.socket.annotation.SocketController;
import com.backend.gimhanul.global.socket.annotation.SocketMapping;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@SocketController
@RequiredArgsConstructor
public class ChatController {

	private final SendChatService sendChatService;
	private final SubscribeAllService subscribeAllService;
	private final JoinRoomService joinRoomService;
	private final QueryRoomService queryRoomService;


	@SocketMapping(endpoint = "message", requestCls = SendChatRequest.class)
	public void sendChat(SocketIOClient client, SocketIOServer server, SendChatRequest request) {
		sendChatService.execute(client, server, request);
	}

	@SocketMapping(endpoint = "subscribe", requestCls = String.class)
	public void subscribeRoom(SocketIOClient client, String roomId) {
		try {
			Long.valueOf(roomId);
			client.joinRoom(roomId);
		} catch (Exception e) {
			throw InvalidArgumentException.EXCEPTION;
		}
	}

	@SocketMapping(endpoint = "subscribe-all")
	public void subscribeAll(SocketIOClient client) {
		subscribeAllService.execute(client);
	}

	@SocketMapping(endpoint = "join")
	public void joinRoom(SocketIOClient client) {
		joinRoomService.execute(client);
	}

	@UserLoginToken
	@GetMapping("/rooms")
	public List<QueryRoomResponse> queryRoom(@RequestAttribute("user") User user) {
		return queryRoomService.execute(user);
	}

}
