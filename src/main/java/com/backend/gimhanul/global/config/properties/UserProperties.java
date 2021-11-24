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
	private OAuth google;
	private OAuth naver;

	@Getter
	@Setter
	public static class OAuth {
		private String baseUrl;
		private String clientId;
		private String clientSecret;
		private String redirectUrl;
	}

	public String getKakaoBaseUrl() {
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

	public String getGoogleBaseUrl() {
		return google.getBaseUrl();
	}

	public String getGoogleClientId() {
		return google.getClientId();
	}

	public String getGoogleRedirectUrl() {
		return google.getRedirectUrl();
	}

	public String getGoogleClientSecret() {
		return google.getClientSecret();
	}

	public String getNaverBaseUrl() {
		return naver.getBaseUrl();
	}

	public String getNaverClientId() {
		return naver.getClientId();
	}

	public String getNaverRedirectUrl() {
		return naver.getRedirectUrl();
	}

	public String getNaverClientSecret() {
		return naver.getClientSecret();
	}

}
