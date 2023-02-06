package com.productrank.api.sns.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productrank.api.sns.common.SocialOauth;
import com.productrank.api.sns.kakao.client.KakaoApiClient;
import com.productrank.api.sns.kakao.client.KakaoClient;
import com.productrank.api.sns.kakao.client.KakaoData;
import com.productrank.api.sns.kakao.dto.KakaoUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoOauth implements SocialOauth {

    @Value("${spring.OAuth2.kakao.client_id}")
    private String KAKAO_CLIENT_ID;
    @Value("${spring.OAuth2.kakao.grant_type}")
    private String KAKAO_GRANT_TYPE;
    @Value("${spring.OAuth2.kakao.redirect_uri}")
    private String KAKAO_REDIRECT_URI;
    @Value("${spring.OAuth2.kakao.url}")
    private String KAKAO_SNS_LOGIN_URL;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final KakaoClient kakaoClient;
    private final KakaoApiClient loginClient;
    @Override
    public String getOauthRedirectURL() {
        Map<String,Object> params=new HashMap<>();
        params.put("client_id",KAKAO_CLIENT_ID);
        params.put("redirect_uri",KAKAO_REDIRECT_URI);
        params.put("response_type","code");

        //parameter를 형식에 맞춰 구성해주는 함수
        String parameterString=params.entrySet().stream()
                .map(x->x.getKey()+"="+x.getValue())
                .collect(Collectors.joining("&"));
        String redirectURL=KAKAO_SNS_LOGIN_URL+"?"+parameterString;
        log.info("redirectURL = " + redirectURL);

        return redirectURL;
    }

    public ResponseEntity<String> requestAccessToken(String code) throws JsonProcessingException {
        KakaoClient.Request req = new KakaoClient.Request(KAKAO_GRANT_TYPE, KAKAO_CLIENT_ID, KAKAO_REDIRECT_URI, code);
        KakaoClient.TokenDto response = kakaoClient.getToken(req.getModel());



        return ResponseEntity.ok(response.bearerTokenInfo());
    }
    public KakaoUser getUserInfo(ResponseEntity<String> userInfoRes) throws JsonProcessingException{
        KakaoData contents = loginClient.getUserData(userInfoRes.getBody());
        KakaoData.KakaoAccount accounts = contents.kakao_account();
        KakaoData.Profile profile = accounts.profile();
        return KakaoUser.builder()
                .thumbnail_image_url(profile.thumbnail_image_url())
                .email(accounts.email())
                .nickname(profile.nickname())
                .build();
    }

}
