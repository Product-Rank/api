package com.productrank.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final AccessDeniedHandler jwtAccessDeniedHandler;
    private AuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private String[] NOT_FILTER_URL = {
            "/h2-console/**", "/auth/**", "/templates"};
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, AccessDeniedHandler jwtAccessDeniedHandler, AuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests((authorization) ->
                        authorization
                                .requestMatchers(NOT_FILTER_URL).permitAll()
//                        .requestMatchers("/swagger-resources/**",
//                                "/swagger-ui.html",
//                                "/v2/api-docs",
//                                "/webjars/**").permitAll()
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/app/**").authenticated()
                                .anyRequest().authenticated()
                                )
             .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
             .formLogin()
                .disable()
             .headers()
                .frameOptions().disable().and()
             .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
             ;

        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/images/**", "/js/**", "/webjars/**", "/templates/kakaoCI/**", "/static/images/**");
    }

}
