package com.backend.gimhanul.domain.chat.service;

import com.backend.gimhanul.domain.chat.domain.Member;
import com.backend.gimhanul.domain.chat.domain.Message;
import com.backend.gimhanul.domain.chat.domain.Room;
import com.backend.gimhanul.domain.chat.domain.Swear;
import com.backend.gimhanul.domain.chat.domain.repository.MessageRepository;
import com.backend.gimhanul.domain.chat.domain.repository.SwearRepository;
import com.backend.gimhanul.domain.chat.exception.SwearNotFoundException;
import com.backend.gimhanul.domain.chat.exception.InvalidArgumentException;
import com.backend.gimhanul.domain.chat.facade.MemberFacade;
import com.backend.gimhanul.domain.chat.facade.RoomFacade;
import com.backend.gimhanul.domain.chat.presentation.dto.request.SendChatRequest;
import com.backend.gimhanul.domain.chat.presentation.dto.response.MessageDto;
import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.user.facade.UserFacade;
import com.backend.gimhanul.global.socket.SocketProperty;
import com.backend.gimhanul.global.utils.CheckExist;
import com.backend.gimhanul.global.utils.api.client.FilterClient;
import com.backend.gimhanul.global.utils.api.dto.request.FilterRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SendChatService {

    private final MessageRepository messageRepository;
    private final GetRandomEmojiService getRandomEmojiService;
    private final UserFacade userFacade;
    private final RoomFacade roomFacade;
    private final MemberFacade memberFacade;
    private final FilterClient filterClient;
    private final SwearRepository swearRepository;

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

        List<String> result = checkMessage(Arrays.asList(request.getMessage().split(" ")), user);

        String content = StringUtils.join(result, ' ');

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

    private List<String> checkMessage(List<String> messageList, User user) {
        List<String> result = new ArrayList<>();

        for (String message : messageList) {

            if (swearRepository.findById(message).isPresent()) {
                addMessage(result, message, user);
                continue;
            }

            if (CheckExist.checkIsOnlyEng(message)) {
                swearRepository.save(swearBuilder(message, false));
                addMessage(result, message, user);
                continue;
            }

            for (boolean value : filterClient.filtering(new FilterRequest(List.of(message))).getData()) {
                swearRepository.save(swearBuilder(message, value));
                addMessage(result, message, user);
            }
        }

        return result;
    }

    private Swear swearBuilder(String message, boolean value) {
        return Swear.builder()
                .id(message)
                .isSwear(value)
                .count(1)
                .build();
    }

	private void addMessage(List<String> result, String message, User user) {
		Swear swear = swearRepository.findById(message)
				.orElseThrow(() -> SwearNotFoundException.EXCEPTION);
		if (swear.isSwear()) {
			swear.increaseCount();
			if(swear.getReplaceMessage() != null)
				result.add(swear.getReplaceMessage());
			else result.add(changeSwear(message));
			user.increaseCount();
		}
		else result.add(message);
	}

    private String changeSwear(String swear) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < swear.length(); j++)
            builder.append(getRandomEmojiService.getRandomEmoji().getEmoticon());
        return builder.toString();
    }

}
