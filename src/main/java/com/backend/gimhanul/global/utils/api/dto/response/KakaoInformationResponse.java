package com.backend.gimhanul.global.utils.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoInformationResponse {

	private Properties properties;

	@JsonProperty("kakao_account")
	private KakaoAccount kakaoAccount;

	@Getter
	@NoArgsConstructor
	public static class Properties {
		private String nickname;
	}

	@Getter
	@NoArgsConstructor
	public static class KakaoAccount {

		private Profile profile;

		private String email;

		@Getter
		@NoArgsConstructor
		public static class Profile {
			@JsonProperty("profile_image_url")
			private String profileImageUrl;
		}

		public String getProfileImageUrl() {
			return profile.getProfileImageUrl();
		}

	}

	public String getEmail() {
		return kakaoAccount.getEmail();
	}

	public String getName() {
		return properties.getNickname();
	}

	public String getProfileImage() {
		return kakaoAccount.getProfileImageUrl();
	}

}
