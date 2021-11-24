package com.backend.gimhanul.global.utils.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GoogleCodeRequest {

	private final String code;
	private final String clientId;
	private final String clientSecret;
	private final String redirectUri;
	private final String grantType = "authorization_code";

}
