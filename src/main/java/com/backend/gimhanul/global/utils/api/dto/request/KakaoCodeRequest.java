package com.backend.gimhanul.global.utils.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class KakaoCodeRequest {

	@JsonProperty("client_id")
	private final String clientId;

	@JsonProperty("client_secret")
	private final String clientSecret;

	@JsonProperty("redirect_uri")
	private final String redirectUri;

	private final String code;

	@JsonProperty("grant_type")
	private final String grantType = "authorization_code";

}
