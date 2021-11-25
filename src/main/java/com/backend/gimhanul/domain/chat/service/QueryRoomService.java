package com.backend.gimhanul.domain.chat.service;

import java.util.List;
import java.util.stream.Collectors;

import com.backend.gimhanul.domain.chat.domain.repository.RoomRepository;
import com.backend.gimhanul.domain.chat.presentation.dto.response.QueryRoomResponse;
import com.backend.gimhanul.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryRoomService {

	private final RoomRepository roomRepository;

	public List<QueryRoomResponse> execute(User user) {
		return roomRepository.findRoomById(user.getId())
				.stream().map(room ->
				new QueryRoomResponse(room.getId(), room.getUsername(user.getId()))
		).collect(Collectors.toList());
	}

}
