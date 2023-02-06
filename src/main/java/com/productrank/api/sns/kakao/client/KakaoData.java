package com.productrank.api.sns.kakao.client;

public record KakaoData(String id, KakaoAccount kakao_account) {

    public record KakaoAccount(String nickname, String email, Profile profile){

    }

    public record Profile(String nickname, String thumbnail_image_url){

    }
}
