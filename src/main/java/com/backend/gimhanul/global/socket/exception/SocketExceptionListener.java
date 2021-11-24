package com.backend.gimhanul.global.socket.exception;

import java.util.List;

import com.backend.gimhanul.global.errors.ErrorResponse;
import com.backend.gimhanul.global.errors.exception.HanulException;
import com.backend.gimhanul.global.socket.SocketProperty;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListener;
import io.netty.channel.ChannelHandlerContext;

import org.springframework.stereotype.Component;

@Component
public class SocketExceptionListener implements ExceptionListener {

	@Override
	public void onEventException(Exception e, List<Object> args, SocketIOClient client) {
		runExceptionHandling(e, client);
	}

	@Override
	public void onDisconnectException(Exception e, SocketIOClient client) {
		runExceptionHandling(e, client);
	}

	@Override
	public void onConnectException(Exception e, SocketIOClient client) {
		runExceptionHandling(e, client);
		client.disconnect();
	}

	@Override
	public void onPingException(Exception e, SocketIOClient client) {
		runExceptionHandling(e, client);
	}

	@Override
	public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
		return false;
	}

	private void runExceptionHandling(Exception e, SocketIOClient client) {
		final ErrorResponse message;

		if (e instanceof HanulException) {
			HanulException hanulException = (HanulException)e;

			message = ErrorResponse.builder()
					.message(hanulException.getErrorCode().getMessage())
					.status(hanulException.getErrorCode().getStatus())
					.build();
		} else if(e.getCause() instanceof HanulException) {
			HanulException hanulException = (HanulException)e.getCause();

			message = ErrorResponse.builder()
					.message(hanulException.getErrorCode().getMessage())
					.status(hanulException.getErrorCode().getStatus())
					.build();
		} else {
			e.printStackTrace();
			message = ErrorResponse.builder()
					.status(500)
					.message("Internal Server Error")
					.build();
		}

		client.sendEvent(SocketProperty.ERROR_KEY, message);

	}

}
