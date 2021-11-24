package com.backend.gimhanul.global.utils.api.client;

import com.backend.gimhanul.global.utils.api.dto.response.TokenResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "NaverAuthClient", url = "https://nid.naver.com/oauth2.0/token")
public interface NaverAuthClient {

	@GetMapping("?grant_type=authorization_code&client_id={CLIENT}&client_secret={SECRET}&code={CODE}")
	TokenResponse naverAuth(@PathVariable("CLIENT") String clientId, @PathVariable("SECRET") String clientSecret,
			@PathVariable("CODE") String code);

}
