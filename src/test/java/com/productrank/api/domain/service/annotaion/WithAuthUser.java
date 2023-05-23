package com.productrank.api.domain.service.annotaion;

import org.junit.jupiter.api.RepeatedTest;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithAuthUserSecurityContextFactory.class)
public @interface WithAuthUser {
    String email();
    String role();
}
