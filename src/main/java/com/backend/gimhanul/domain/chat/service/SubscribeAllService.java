package com.backend.gimhanul.domain.chat.service;

import com.backend.gimhanul.domain.chat.domain.repository.RoomRepository;
import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.user.facade.UserFacade;
import com.backend.gimhanul.global.socket.SocketProperty;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeAllService {

	private final RoomRepository roomRepository;
	private final UserFacade userFacade;

	public void execute(SocketIOClient client) {
		User user = userFacade.findUserByClient(client);

		roomRepository.findIdEmail(user.getId())
				.forEach(client::joinRoom);

		client.sendEvent(SocketProperty.SUBSCRIBE_KEY, "Subscribe all success");
	}

}
