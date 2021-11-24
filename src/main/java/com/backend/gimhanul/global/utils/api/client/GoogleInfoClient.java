package com.backend.gimhanul.global.utils.api.client;

import com.backend.gimhanul.global.utils.api.dto.response.GoogleInformationResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GoogleInfoClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
public interface GoogleInfoClient {

	@GetMapping("?alt=json&access_token={TOKEN}")
	GoogleInformationResponse googleInfo(@PathVariable("TOKEN") String accessToken);

}
