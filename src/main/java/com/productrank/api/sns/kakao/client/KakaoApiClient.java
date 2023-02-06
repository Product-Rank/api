package com.productrank.api.sns.kakao.client;

import com.productrank.api.config.TestFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "KakaoLogin", url = "https://kapi.kakao.com/v2", configuration = TestFeignClientConfiguration.class)
public interface KakaoApiClient {
    @PostMapping("/user/me")
    KakaoData getUserData(@RequestHeader("Authorization") String token);

    @PostMapping("/user/me")
    Map<String, ?> getUserData2(@RequestHeader("Authorization") String token);
}
