package com.backend.gimhanul.domain.user.service;

import com.backend.gimhanul.global.config.properties.UserProperties;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryGoogleAuthLinkService {

	private static final String QUERY_STRING = "?client_id=%s&redirect_uri=%s&response_type=code&"
			+ "scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";

	private final UserProperties userProperties;

	public String execute() {
		return userProperties.getGoogleBaseUrl() +
				String.format(QUERY_STRING, userProperties.getGoogleClientId(), userProperties.getGoogleRedirectUrl());
	}

}
