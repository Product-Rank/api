package com.productrank.api.config.security.custom;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {

    public CustomAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomAuthenticationException(String msg) {
        super(msg);
    }
}
