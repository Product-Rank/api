package com.productrank.api.sns.kakao.client;

import com.productrank.api.config.TestFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@FeignClient(name = "KakaoAuth", url = "https://kauth.kakao.com", configuration = TestFeignClientConfiguration.class)
public interface KakaoClient {
    @PostMapping("/oauth/token")
    TokenDto getToken(@RequestParam Map<String, String> request);
    record Request(String grant_type, String client_id, String redirect_uri, String code){

        public Map<String, String> getModel(){
            Map<String, String> result = new HashMap<>();
            result.put("grant_type", grant_type);
            result.put("client_id", client_id);
            result.put("redirect_uri", redirect_uri);
            result.put("code", code);
            return result;
        }
    }

    record TokenDto(String access_token, String token_type, String refresh_token, String id_token){
        public String bearerTokenInfo(){
            return token_type + " " + access_token;
        }
    }

}
