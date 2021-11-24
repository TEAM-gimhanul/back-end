package com.backend.gimhanul.domain.user.service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.user.domain.repositories.UserRepository;
import com.backend.gimhanul.domain.user.exception.CredentialsNotFoundException;
import com.backend.gimhanul.domain.user.presentation.dto.request.AuthRequest;
import com.backend.gimhanul.domain.user.presentation.dto.response.TokenResponse;
import com.backend.gimhanul.global.config.properties.UserProperties;
import com.backend.gimhanul.global.utils.api.client.GoogleAuthClient;
import com.backend.gimhanul.global.utils.api.client.GoogleInfoClient;
import com.backend.gimhanul.global.utils.api.dto.request.GoogleCodeRequest;
import com.backend.gimhanul.global.utils.api.dto.response.GoogleInformationResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

	private final UserProperties userProperties;
	private final GoogleAuthClient googleAuthClient;
	private final GoogleInfoClient googleInfoClient;
	private final UserRepository userRepository;

	public TokenResponse execute(AuthRequest request) {
		GoogleCodeRequest codeRequest = GoogleCodeRequest.builder()
				.code(URLDecoder.decode(request.getCode(), StandardCharsets.UTF_8))
				.clientId(userProperties.getGoogleClientId())
				.clientSecret(userProperties.getGoogleClientSecret())
				.redirectUri(userProperties.getGoogleRedirectUrl())
				.build();

		String accessToken = googleAuthClient.googleAuth(codeRequest)
				.getAccessToken();

		GoogleInformationResponse response = googleInfoClient.googleInfo(accessToken);

		if(userRepository.findById(response.getEmail()).isEmpty()) {
			userRepository.save(
					User.builder()
					.id(response.getEmail())
					.name(response.getName())
					.profileImage(response.getPicture())
					.build()
			);
		}

		User user = userRepository.findById(response.getEmail())
				.orElseThrow(() -> CredentialsNotFoundException.EXCEPTION);

		// TODO: 2021-11-25 토큰 발급 

		return null;
	}

}
