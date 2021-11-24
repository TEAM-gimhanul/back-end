package com.backend.gimhanul.global.config.properties;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("user")
public class UserProperties {

	private OAuth kakao;

	@Getter
	@Setter
	public static class OAuth {
		private String baseUrl;
		private String clientId;
		private String clientSecret;
		private String redirectUrl;
	}

	public String getkakaoBaseUrl() {
		return kakao.getBaseUrl();
	}

	public String getKakaoClientId() {
		return kakao.getClientId();
	}

	public String getKakaoRedirectUrl() {
		return kakao.getRedirectUrl();
	}

	public String getKakaoClientSecret() {
		return kakao.getClientSecret();
	}

}
