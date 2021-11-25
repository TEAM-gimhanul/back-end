package com.backend.gimhanul.global.utils.api.client;

import com.backend.gimhanul.global.utils.api.dto.request.FilterRequest;
import com.backend.gimhanul.global.utils.api.dto.response.FilterResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "FilterClient", url = "http://localhost:8085")
public interface FilterClient {

	@PostMapping("/text")
	FilterResponse filtering(FilterRequest request);

}
