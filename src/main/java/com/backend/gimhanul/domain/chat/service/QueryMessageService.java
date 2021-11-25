package com.backend.gimhanul.domain.chat.service;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.gimhanul.domain.chat.domain.Room;
import com.backend.gimhanul.domain.chat.domain.facade.RoomFacade;
import com.backend.gimhanul.domain.chat.domain.repository.MessageRepository;
import com.backend.gimhanul.domain.chat.presentation.dto.response.QueryMessageResponse;
import com.backend.gimhanul.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryMessageService {

	private final MessageRepository messageRepository;
	private final RoomFacade roomFacade;

	public List<QueryMessageResponse> execute(User user, String roomId) {
		Room room = roomFacade.findRoomById(Long.valueOf(roomId));
		return messageRepository.findByRoom(room)
				.stream().map(message ->
					new QueryMessageResponse(room.getId(), message.getId(),
							message.getMessage(), message.getMember().getUser().getName()
							, message.getMember().getUser().getProfileImage())
		).collect(Collectors.toList());
	}

}
