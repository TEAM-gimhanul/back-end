package com.backend.gimhanul.global.socket.security;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketConnectController {

	public void onConnect(SocketIOClient client) {
		String token = client.getHandshakeData().getSingleUrlParam("Authorization");
		Authentication authentication = null;  // TODO: 2021-11-24 token provider에서 authentication가져오기 
		client.set(AuthenticationProperty.USER_KEY, authentication.getName());
	}
	
}
