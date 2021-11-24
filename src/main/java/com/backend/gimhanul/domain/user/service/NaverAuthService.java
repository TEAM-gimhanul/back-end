package com.backend.gimhanul.domain.user.service;

import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.user.domain.repositories.UserRepository;
import com.backend.gimhanul.domain.user.exception.CredentialsNotFoundException;
import com.backend.gimhanul.domain.user.presentation.dto.request.AuthRequest;
import com.backend.gimhanul.domain.user.presentation.dto.response.TokenResponse;
import com.backend.gimhanul.global.config.properties.UserProperties;
import com.backend.gimhanul.global.security.jwt.JwtTokenProvider;
import com.backend.gimhanul.global.utils.api.client.NaverAuthClient;
import com.backend.gimhanul.global.utils.api.client.NaverInfoClient;
import com.backend.gimhanul.global.utils.api.dto.response.NaverInformationResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NaverAuthService {

	private static final String PREFIX = "Bearer ";

	private final UserProperties userProperties;
	private final UserRepository userRepository;
	private final NaverAuthClient naverAuthClient;
	private final NaverInfoClient naverInfoClient;
	private final JwtTokenProvider jwtTokenProvider;

	public TokenResponse execute(AuthRequest authRequest) {
		String token = naverAuthClient.naverAuth(userProperties.getNaverClientId(), userProperties.getNaverClientSecret(),
				authRequest.getCode())
				.getAccessToken();

		NaverInformationResponse response = naverInfoClient.naverInfo(PREFIX + token);

		if(userRepository.findById(response.getEmail()).isEmpty()) {
			userRepository.save(
					User.builder()
							.id(response.getEmail())
							.name(response.getName())
							.profileImage(response.getProfileImage())
							.build()
			);
		}

		return new TokenResponse(jwtTokenProvider.generateAccessToken(response.getEmail()));
	}

}
