package com.backend.gimhanul.domain.chat.service;

import javax.transaction.Transactional;

import com.backend.gimhanul.domain.chat.domain.Member;
import com.backend.gimhanul.domain.chat.domain.Message;
import com.backend.gimhanul.domain.chat.domain.Room;
import com.backend.gimhanul.domain.chat.domain.facade.MemberFacade;
import com.backend.gimhanul.domain.chat.domain.repository.MemberRepository;
import com.backend.gimhanul.domain.chat.domain.repository.MessageRepository;
import com.backend.gimhanul.domain.chat.domain.repository.RoomRepository;
import com.backend.gimhanul.domain.chat.exception.InvalidArgumentException;
import com.backend.gimhanul.domain.chat.presentation.dto.request.SendChatRequest;
import com.backend.gimhanul.domain.chat.presentation.dto.response.MessageDto;
import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.chat.domain.facade.RoomFacade;
import com.backend.gimhanul.domain.user.facade.UserFacade;
import com.backend.gimhanul.global.socket.SocketProperty;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendChatService {

	private final MessageRepository messageRepository;
	private final UserFacade userFacade;
	private final RoomFacade roomFacade;
	private final MemberFacade memberFacade;

	@Transactional
	public void execute(SocketIOClient client, SocketIOServer server, SendChatRequest request) {
		Room room;
		try {
			room = roomFacade.findRoomById(Long.valueOf(request.getRoomId()));
		} catch (Exception e) {
			throw InvalidArgumentException.EXCEPTION;
		}

		User user = userFacade.findUserByClient(client);
		Member member = memberFacade.findMemberByUserAndRoom(user, room);

		// TODO: 2021-11-25 비속어 감지

		Message message = messageRepository.save(
				Message.builder()
				.room(room)
				.message(request.getMessage())
				.member(member)
				.build()
		);

		MessageDto messageDto = new MessageDto(message.getMessage(), room.getUsername(user.getId()),
				room.getProfileImage(user.getId()), room.getId());

		client.sendEvent(SocketProperty.MESSAGE_KEY, messageDto);

		server.getRoomOperations(room.getId().toString())
				.sendEvent(SocketProperty.MESSAGE_KEY, messageDto);



	}

}
