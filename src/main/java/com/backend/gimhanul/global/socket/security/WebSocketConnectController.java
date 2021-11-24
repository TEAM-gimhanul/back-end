package com.backend.gimhanul.global.socket.security;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketConnectController {

	public void onConnect(SocketIOClient client) {
//			TODO: 2021-11-24 client에 USER_KEY로 유저 고유값 넣기
	}
	
}
