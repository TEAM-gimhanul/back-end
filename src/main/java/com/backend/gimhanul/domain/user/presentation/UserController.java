package com.backend.gimhanul.domain.user.presentation;

import com.backend.gimhanul.domain.user.domain.User;
import com.backend.gimhanul.domain.user.presentation.dto.request.AuthRequest;
import com.backend.gimhanul.domain.user.presentation.dto.response.TokenResponse;
import com.backend.gimhanul.domain.user.service.*;
import com.backend.gimhanul.global.annotation.UserLoginToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final QueryKakaoAuthLinkService queryKakaoAuthLinkService;
    private final QueryGoogleAuthLinkService queryGoogleAuthLinkService;
    private final QueryNaverAuthLinkService queryNaverAuthLinkService;
    private final KakaoAuthService kakaoAuthService;
    private final GoogleAuthService googleAuthService;
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

    @PostMapping("/kakao")
    public TokenResponse kakaoAuth(@RequestBody AuthRequest request) {
        return kakaoAuthService.execute(request);
    }

    @PostMapping("/google")
    public TokenResponse googleAuth(@RequestBody AuthRequest request) {
        return googleAuthService.execute(request);
    }

    @PostMapping("/naver")
    public TokenResponse naverAuth(@RequestBody AuthRequest request) {
        return naverAuthService.execute(request);
    }

    @UserLoginToken
    @GetMapping("/my")
    public User getMyInfo (
            final @RequestAttribute("user") User user
    ) {
        return user;
    }
}
