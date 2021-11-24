package com.backend.gimhanul.global.utils.api.client;

import com.backend.gimhanul.global.utils.api.dto.request.GoogleCodeRequest;
import com.backend.gimhanul.global.utils.api.dto.response.TokenResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleAuthClient {

	@PostMapping
	TokenResponse googleAuth(GoogleCodeRequest request);

}
