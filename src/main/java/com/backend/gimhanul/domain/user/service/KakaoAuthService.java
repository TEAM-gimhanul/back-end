package com.backend.gimhanul.domain.user.service;

import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.user.domain.repositories.UserRepository;
import com.backend.gimhanul.domain.user.exception.CredentialsNotFoundException;
import com.backend.gimhanul.domain.user.presentation.dto.request.AuthRequest;
import com.backend.gimhanul.domain.user.presentation.dto.response.TokenResponse;
import com.backend.gimhanul.global.config.properties.UserProperties;
import com.backend.gimhanul.global.utils.api.client.KakaoAuthClient;
import com.backend.gimhanul.global.utils.api.client.KakaoInfoClient;
import com.backend.gimhanul.global.utils.api.dto.response.KakaoInformationResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

	private static final String PREFIX = "Bearer ";

	private final UserProperties userProperties;
	private final KakaoAuthClient kakaoAuthClient;
	private final KakaoInfoClient kakaoInfoClient;
	private final UserRepository userRepository;

	public TokenResponse execute(AuthRequest request) {

		String accessToken = kakaoAuthClient.kakaoAuth(userProperties.getKakaoClientId(),
				userProperties.getKakaoRedirectUrl(), request.getCode())
				.getAccessToken();

		KakaoInformationResponse response = kakaoInfoClient.kakaoInfo(PREFIX + accessToken);

		if(userRepository.findById(response.getEmail()).isEmpty()) {
			userRepository.save(
					User.builder()
					.id(response.getEmail())
					.name(response.getName())
					.profileImage(response.getProfileImage())
					.build()
			);
		}

		User user = userRepository.findById(response.getEmail())
				.orElseThrow(() -> CredentialsNotFoundException.EXCEPTION);

		// TODO: 2021-11-25 토큰 발급

		return null;
	}

}
