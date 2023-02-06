package com.productrank.api.sns.kakao;

import com.productrank.api.sns.kakao.client.KakaoApiClient;
import com.productrank.api.sns.kakao.client.KakaoClient;
import com.productrank.api.sns.kakao.client.KakaoData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@RestController
@RequiredArgsConstructor
@Slf4j
public class KakaoLogin {

    @Value("${client.key}")
    private String client_id;
    @Value("${client.grant_type}")
    private String grant_type;
    @Value("${client.redirect_uri}")
    private String redirect_uri;

    private final KakaoClient client;
    private final KakaoApiClient loginClient;
    private final HttpServletResponse response;
    @GetMapping("/login")
    public ResponseEntity<?> kakaoLogin(){

        return ResponseEntity.ok(null);
    }

    @GetMapping("/info")
    public ResponseEntity<KakaoData> kakaoInfo(@RequestParam String code){

        log.info("{} {} {} {} ", grant_type, client_id, redirect_uri, code);
//https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=58812c2af9f02f10145c2b08f3c2e141&redirect_uri=http://localhost:8080/kakao/info
        KakaoClient.Request req = new KakaoClient.Request(grant_type, client_id, redirect_uri, code);
        KakaoClient.TokenDto response = client.getToken(req.getModel());

        KakaoData contents = loginClient.getUserData(response.bearerTokenInfo());

        return ResponseEntity.ok(contents);
    }


}
