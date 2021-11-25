package com.backend.gimhanul.domain.user.facade;

import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.user.domain.repositories.UserRepository;
import com.backend.gimhanul.domain.user.exception.UserNotFoundException;
import com.backend.gimhanul.global.socket.security.AuthenticationProperty;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User findUserByClient(SocketIOClient client) {
    	return userRepository.findById(client.get(AuthenticationProperty.USER_KEY))
				.orElseThrow(() -> UserNotFoundException.EXCEPTION);
	}

}
