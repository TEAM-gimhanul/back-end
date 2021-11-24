package com.backend.gimhanul.domain.user.presentation;

import com.backend.gimhanul.domain.user.service.QueryGoogleAuthLinkService;
import com.backend.gimhanul.domain.user.service.QueryKakaoAuthLinkService;
import com.backend.gimhanul.domain.user.service.QueryNaverAuthLinkService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final QueryKakaoAuthLinkService queryKakaoAuthLinkService;
	private final QueryGoogleAuthLinkService queryGoogleAuthLinkService;
	private final QueryNaverAuthLinkService queryNaverAuthLinkService;

	@GetMapping("/kakao")
	public String queryKakaoAuthLink() {
		return queryKakaoAuthLinkService.execute();
	}

	@GetMapping("/google")
	public String queryGoogleAuthLink() {
		return queryGoogleAuthLinkService.execute();
	}

	@GetMapping("/naver")
	public String queryNaverAuthLink() {
		return queryNaverAuthLinkService.execute();
	}

}
