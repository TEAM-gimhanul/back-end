package com.backend.gimhanul.domain.user.presentation;

import com.backend.gimhanul.domain.user.presentation.dto.request.AuthRequest;
import com.backend.gimhanul.domain.user.presentation.dto.response.TokenResponse;
import com.backend.gimhanul.domain.user.service.NaverAuthService;
import com.backend.gimhanul.domain.user.service.QueryGoogleAuthLinkService;
import com.backend.gimhanul.domain.user.service.QueryKakaoAuthLinkService;
import com.backend.gimhanul.domain.user.service.QueryNaverAuthLinkService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final QueryKakaoAuthLinkService queryKakaoAuthLinkService;
	private final QueryGoogleAuthLinkService queryGoogleAuthLinkService;
	private final QueryNaverAuthLinkService queryNaverAuthLinkService;
	private final NaverAuthService naverAuthService;

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

	@PostMapping("/naver")
	public TokenResponse naverAuth(@RequestBody AuthRequest request) {
		return naverAuthService.execute(request);
	}

}
