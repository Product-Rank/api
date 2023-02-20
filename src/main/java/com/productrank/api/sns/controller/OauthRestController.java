package com.productrank.api.sns.controller;

import com.productrank.api.config.security.JwtTokenProvider;
import com.productrank.api.domain.service.UserService;
import com.productrank.api.sns.common.Constant;
import com.productrank.api.sns.common.SNSUser;
import com.productrank.api.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class OauthRestController {
    private final OAuthService oAuthService;
    private final JwtTokenProvider provider;
    private final UserService userService;

    @GetMapping("/{socialLoginType}") //GOOGLE이 들어올 것이다.
    public void socialLoginRedirect(@PathVariable(name="socialLoginType") String SocialLoginPath) throws IOException {
        Constant.SocialLoginType socialLoginType= Constant.SocialLoginType.valueOf(SocialLoginPath.toUpperCase());
        oAuthService.request(socialLoginType);
    }
    @GetMapping(value = "/{socialLoginType}/callback")
    public ResponseEntity<SNSUser> callback (
            @PathVariable(name = "socialLoginType") String socialLoginPath,
            @RequestParam(name = "code") String code)throws IOException{
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :"+ code);
        Constant.SocialLoginType socialLoginType= Constant.SocialLoginType.valueOf(socialLoginPath.toUpperCase());
        SNSUser oAuthUser=oAuthService.oAuthLogin(socialLoginType,code);

        userService.saveUser(UserDto.builder()
                        .userName(oAuthUser.getName())
                        .email(oAuthUser.getEmail())
                        .nickName(oAuthUser.getName())
                        .picture(oAuthUser.getPicture())
                        .snsType(oAuthUser.getSnsType())
                        .accessToken(provider.createToken(oAuthUser.getEmail(), oAuthUser.getSnsType()))
                        .build());

        return ResponseEntity.ok(oAuthUser);
    }
}
