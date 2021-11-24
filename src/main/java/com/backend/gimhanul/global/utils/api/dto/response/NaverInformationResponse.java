package com.backend.gimhanul.global.utils.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NaverInformationResponse {

	private Response response;

	@Getter
	@NoArgsConstructor
	public static class Response {

		@JsonProperty("profile_image")
		private String profileImage;

		private String email;

		private String name;

	}

	public String getProfileImage() {
		return response.getProfileImage();
	}

	public String getEmail() {
		return response.getEmail();
	}

	public String getName() {
		return response.getName();
	}

}
