package com.backend.gimhanul.domain.chat.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.backend.gimhanul.domain.chat.domain.Member;
import com.backend.gimhanul.domain.chat.domain.Room;
import com.backend.gimhanul.domain.chat.domain.repository.MemberRepository;
import com.backend.gimhanul.domain.chat.domain.repository.RoomRepository;
import com.backend.gimhanul.domain.chat.presentation.dto.response.JoinResponse;
import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.user.domain.repositories.UserRepository;
import com.backend.gimhanul.domain.user.facade.UserFacade;
import com.backend.gimhanul.global.socket.SocketProperty;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinRoomService {

	private final RoomRepository roomRepository;
	private final MemberRepository memberRepository;
	private final UserRepository userRepository;
	private final UserFacade userFacade;

	@Transactional
	public void execute(SocketIOClient client) {
		User currentUser = userFacade.findUserByClient(client);
		User randomUser = userRepository.getUserRamdom(currentUser.getId());

		Optional<Long> roomId = memberRepository.findRoomIdByUsers(currentUser.getId(), randomUser.getId());

		if(roomId.isPresent()) {
			client.leaveRoom(roomId.get().toString());
			client.joinRoom(roomId.get().toString());
			client.sendEvent(SocketProperty.ROOM_KEY, new JoinResponse(randomUser.getName(), roomId.get(), randomUser.getProfileImage()));
			return;
		}

		Room room = roomRepository.save(
				Room.builder()
				.name(randomUser.getName())
				.build()
		);

		memberRepository.save(
				Member.builder()
				.room(room)
				.user(currentUser)
				.build()
		);
		memberRepository.save(
				Member.builder()
						.room(room)
						.user(randomUser)
						.build()
		);

		client.joinRoom(room.getId().toString());
		client.sendEvent(SocketProperty.ROOM_KEY, new JoinResponse(randomUser.getName(), room.getId(), randomUser.getProfileImage()));
	}

}
