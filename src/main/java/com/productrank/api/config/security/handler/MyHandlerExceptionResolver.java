package com.productrank.api.config.security.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Component
@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // Set the response status code
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        // Set the response content type to JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Create a new error response object
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());

        // Convert the error response object to JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(errorResponse);
        } catch (JsonProcessingException e) {
            log.error("{}", e);
        }

        // Write the JSON response to the output stream
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            log.error("{}", e);
        }

        // Return null to indicate that we have handled the exception
        return null;
    }

    private static class ErrorResponse {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
