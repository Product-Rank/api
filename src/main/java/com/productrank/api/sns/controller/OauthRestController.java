package com.productrank.api.sns.controller;

import com.productrank.api.sns.common.Constant;
import com.productrank.api.sns.common.SNSUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/accounts")
@Slf4j
public class OauthRestController {
    private final OAuthService oAuthService;
    @GetMapping("/auth/{socialLoginType}") //GOOGLE이 들어올 것이다.
    public void socialLoginRedirect(@PathVariable(name="socialLoginType") String SocialLoginPath) throws IOException {
        Constant.SocialLoginType socialLoginType= Constant.SocialLoginType.valueOf(SocialLoginPath.toUpperCase());
        oAuthService.request(socialLoginType);
    }
    @GetMapping(value = "/auth/{socialLoginType}/callback")
    public ResponseEntity<SNSUser> callback (
            @PathVariable(name = "socialLoginType") String socialLoginPath,
            @RequestParam(name = "code") String code)throws IOException{
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :"+ code);
        Constant.SocialLoginType socialLoginType= Constant.SocialLoginType.valueOf(socialLoginPath.toUpperCase());
        SNSUser getSocialOAuthRes=oAuthService.oAuthLogin(socialLoginType,code);
        return ResponseEntity.ok(getSocialOAuthRes);
    }
}
