package com.backend.gimhanul.domain.user.service;

import com.backend.gimhanul.global.config.properties.UserProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryKakaoAuthLinkService {

	private static final String QUERY_STRING =
			"/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code";

	private final UserProperties userProperties;

	public String execute() {
		return userProperties.getKakaoBaseUrl() + String.format(QUERY_STRING,
				userProperties.getKakaoClientId(), userProperties.getKakaoRedirectUrl());
	}

}
