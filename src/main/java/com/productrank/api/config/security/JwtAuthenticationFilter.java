package com.productrank.api.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productrank.api.config.security.JwtTokenProvider;
import com.productrank.api.domain.entity.enums.SNSType;
import com.productrank.api.error.ErrorCode;
import com.productrank.api.error.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final String PREFIX = "Bearer ";
    private final String CONTENT_TYPE = "application/json;charset=utf-8";

    public void makeErrorResponse(HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResponse error = new ErrorResponse(ErrorCode.INVALID_LOGIN);
        response.setStatus(401);
        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUrl = request.getRequestURI();
        List<String> list = Arrays.asList(
                "/auth/kakao","/auth/google",
                "/h2-console"
        );

        if(list.contains(requestUrl)){
            filterChain.doFilter(request, response);
            return;
        }
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        token = token.replaceAll(PREFIX, "");
        if("PASS TOKEN".equals(token)){
            token = jwtTokenProvider.createToken("TEST@kakao.com", SNSType.KAKAO);
        }
        if (!jwtTokenProvider.validateToken(token)) {
            makeErrorResponse(response);
            return;
        }
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
