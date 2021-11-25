package com.backend.gimhanul.domain.chat.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import com.backend.gimhanul.domain.chat.domain.Member;
import com.backend.gimhanul.domain.chat.domain.Message;
import com.backend.gimhanul.domain.chat.domain.Room;
import com.backend.gimhanul.domain.chat.facade.MemberFacade;
import com.backend.gimhanul.domain.chat.domain.repository.MessageRepository;
import com.backend.gimhanul.domain.chat.exception.InvalidArgumentException;
import com.backend.gimhanul.domain.chat.presentation.dto.request.SendChatRequest;
import com.backend.gimhanul.domain.chat.presentation.dto.response.MessageDto;
import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.chat.facade.RoomFacade;
import com.backend.gimhanul.domain.user.facade.UserFacade;
import com.backend.gimhanul.global.socket.SocketProperty;
import com.backend.gimhanul.global.utils.api.client.FilterClient;
import com.backend.gimhanul.global.utils.api.dto.request.FilterRequest;
import com.backend.gimhanul.global.utils.api.dto.response.FilterResponse;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendChatService {

	private final MessageRepository messageRepository;
	private final GetRandomEmojiService getRandomEmojiService;
	private final UserFacade userFacade;
	private final RoomFacade roomFacade;
	private final MemberFacade memberFacade;
	private final FilterClient filterClient;

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

		List<String> messageList = Arrays.asList(request.getMessage().split(" "));
		List<Boolean> response = filterClient.filtering(new FilterRequest(messageList)).getData();

		int count = Collections.frequency(response, true);
		user.appendCount(count);


		for(int i = 0; i < messageList.size(); i++) {
			if(response.get(i).equals(Boolean.TRUE)){
				StringBuilder builder = new StringBuilder();
				for(int j = 0; j < messageList.get(i).length(); j++)
					builder.append(getRandomEmojiService.getRandomEmoji().getEmoticon());
				messageList.set(i, builder.toString());
			}
		}

		String content = StringUtils.join(messageList, ' ');

		messageRepository.save(
				Message.builder()
				.room(room)
				.message(content)
				.member(member)
				.build()
		);

		MessageDto messageDto = new MessageDto(content, user.getName(),
				user.getProfileImage(), room.getId());

		server.getRoomOperations(room.getId().toString())
				.sendEvent(SocketProperty.MESSAGE_KEY, messageDto);

	}

}
