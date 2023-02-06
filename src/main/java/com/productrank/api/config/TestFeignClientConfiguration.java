package com.productrank.api.config;

import feign.Client;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class TestFeignClientConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/x-www-form-urlencoded");
            requestTemplate.header("Accept", "application/json");
        };
    }

    private SSLSocketFactory sslContextFactory() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext ssl_ctx = SSLContext.getInstance("TLS");
        TrustManager[] certs = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String t) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String t) {
                    }
                }};
        ssl_ctx.init(null, certs, new SecureRandom());
        return ssl_ctx.getSocketFactory();
    }

    @Bean
    public Client client() throws KeyManagementException, NoSuchAlgorithmException {
        return new Client.Default(sslContextFactory(), (hostname, session) -> true);
    }
}
