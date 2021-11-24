package com.backend.gimhanul.global.utils.api.client;

import com.backend.gimhanul.global.utils.api.dto.response.NaverInformationResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "NaverInfoClient", url = "https://openapi.naver.com/v1/nid/me")
public interface NaverInfoClient {

	@GetMapping
	NaverInformationResponse naverInfo(@RequestHeader("Authorization") String token);

}
