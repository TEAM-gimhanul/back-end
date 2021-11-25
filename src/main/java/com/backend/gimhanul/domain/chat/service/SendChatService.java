package com.backend.gimhanul.domain.chat.service;

import com.backend.gimhanul.domain.chat.domain.Member;
import com.backend.gimhanul.domain.chat.domain.Message;
import com.backend.gimhanul.domain.chat.domain.Room;
import com.backend.gimhanul.domain.chat.domain.repository.MemberRepository;
import com.backend.gimhanul.domain.chat.domain.repository.MessageRepository;
import com.backend.gimhanul.domain.chat.domain.repository.RoomRepository;
import com.backend.gimhanul.domain.chat.exception.MemberNotFoundException;
import com.backend.gimhanul.domain.chat.exception.RoomNotFoundException;
import com.backend.gimhanul.domain.chat.presentation.dto.request.SendChatRequest;
import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.user.facade.UserFacade;
import com.backend.gimhanul.global.socket.SocketProperty;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendChatService {

	private final RoomRepository roomRepository;
	private final MessageRepository messageRepository;
	private final MemberRepository memberRepository;
	private final UserFacade userFacade;

	public void execute(SocketIOClient client, SocketIOServer server, SendChatRequest request) {
		Room room = roomRepository.findById(request.getRoomId())
				.orElseThrow(() -> RoomNotFoundException.EXCEPTION);

		User user = userFacade.findUserByClient(client);

		Member member = memberRepository.findByUserAndRoom(user, room)
				.orElseThrow(() -> MemberNotFoundException.EXCEPTION);

		// TODO: 2021-11-25 비속어 감지

		Message message = messageRepository.save(
				Message.builder()
				.room(room)
				.message(request.getMessage())
				.member(member)
				.build()
		);

		server.getRoomOperations(room.getId().toString())
				.sendEvent(SocketProperty.MESSAGE_KEY, message);

	}

}
