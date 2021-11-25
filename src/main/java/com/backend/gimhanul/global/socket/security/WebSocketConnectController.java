package com.backend.gimhanul.global.socket.security;

import javax.transaction.Transactional;

import com.backend.gimhanul.domain.user.facade.UserFacade;
import com.backend.gimhanul.global.security.jwt.JwtTokenProvider;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketConnectController {

	private final JwtTokenProvider jwtTokenProvider;
	private final UserFacade userFacade;

	@Transactional
	public void onConnect(SocketIOClient client) {
		String token = client.getHandshakeData().getSingleUrlParam("authorization");
		client.set(AuthenticationProperty.USER_KEY, jwtTokenProvider.validate(token).getId());
		userFacade.findUserByClient(client)
				.setOnlineTrue();
	}
	
}
