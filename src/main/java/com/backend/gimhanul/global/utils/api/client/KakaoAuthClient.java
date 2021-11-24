package com.backend.gimhanul.global.utils.api.client;

import com.backend.gimhanul.global.utils.api.dto.response.TokenResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "KakaoAuthClient", url = "https://kauth.kakao.com")
public interface KakaoAuthClient {

	@PostMapping("/oauth/token?grant_type=authorization_code&client_id={CLIENT_ID}&redirect_uri={REDIRECT_URI}&code={CODE}")
	TokenResponse kakaoAuth(@PathVariable("CLIENT_ID") String clientId,
			@PathVariable("REDIRECT_URI") String redirectUri,
			@PathVariable("CODE") String code);

}
