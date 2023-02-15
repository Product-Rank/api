package com.productrank.api.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON-ERR-404", "PAGE NOT FOUND"),
    INTER_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON-ERR-500", "INTER SERVER ERROR"),
    NOT_EXIST_USER(HttpStatus.NOT_FOUND, "MEMBER-ERR-404", "MEMBER IS NOT EXIST"),
    INVALID_LOGIN(HttpStatus.UNAUTHORIZED, "MEMBER-ERR-401", "LOGIN FAILED"),
    NOT_MATCH_WRITER(HttpStatus.UNAUTHORIZED, "ARTICLE-ERR-401", "IT'S NOT ALLOW TO UPDATE"),
    NOT_EXIST_ARTICLE(HttpStatus.NOT_FOUND, "ARTICLE-ERR-404", "IS NOT VALID ARTICLE ID"),
    NOT_EXIST_COMMENTS(HttpStatus.NOT_FOUND, "COMMENTS-ERR-404", "IS NOT VALID COMMENTS ID"),
    ;

    private HttpStatus status;
    private String errorCode;
    private String message;

    @Override
    public String toString() {
        return this.errorCode;
    }

    private static final Map<String, ErrorCode> descriptions =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(ErrorCode::getErrorCode, Function.identity())));

    public static ErrorCode find(String description) {
        return Optional.ofNullable(descriptions.get(description)).orElse(INTER_SERVER_ERROR);
    }

}
