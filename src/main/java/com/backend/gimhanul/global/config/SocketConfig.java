package com.backend.gimhanul.global.config;

import com.backend.gimhanul.global.socket.WebSocketAddMappingSupporter;
import com.backend.gimhanul.global.socket.exception.SocketExceptionListener;
import com.backend.gimhanul.global.socket.security.WebSocketConnectController;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocketConfig {

	private final WebSocketAddMappingSupporter mappingSupporter;
	private final WebSocketConnectController connectController;
	private final SocketExceptionListener exceptionListener;

	@Value("${socket.port}")
	private Integer port;

	@Bean
	public SocketIOServer socketIOServer() {
		Configuration config = new Configuration();
		config.setPort(port);
		config.setOrigin("*");
		config.setExceptionListener(exceptionListener);
		SocketIOServer server = new SocketIOServer(config);
		mappingSupporter.addListeners(server);
		server.addConnectListener(connectController::onConnect);
		return server;
	}

}
