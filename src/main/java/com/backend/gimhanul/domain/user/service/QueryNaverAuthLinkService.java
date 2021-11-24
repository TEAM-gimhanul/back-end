package com.backend.gimhanul.domain.user.service;

import com.backend.gimhanul.global.config.properties.UserProperties;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryNaverAuthLinkService {

	private static final String QUERY_STRING = "?response_type=code&client_id=%s&redirect_uri=%s";

	private final UserProperties userProperties;

	public String execute() {
		return userProperties.getNaverBaseUrl() +
				String.format(QUERY_STRING, userProperties.getNaverClientId(), userProperties.getNaverRedirectUrl());
	}

}
