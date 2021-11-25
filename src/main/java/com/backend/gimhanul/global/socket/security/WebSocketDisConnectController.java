package com.backend.gimhanul.global.socket.security;

import javax.transaction.Transactional;

import com.backend.gimhanul.domain.user.facade.UserFacade;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketDisConnectController {

	private final UserFacade userFacade;

	@Transactional
	public void onDisConnect(SocketIOClient client) {
		userFacade.findUserByClient(client)
				.setOnlineFalse();
	}

}
