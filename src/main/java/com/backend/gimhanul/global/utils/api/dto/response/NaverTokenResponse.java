package com.backend.gimhanul.global.utils.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NaverTokenResponse {

	@JsonProperty("access_token")
	private String accessToken;

}
