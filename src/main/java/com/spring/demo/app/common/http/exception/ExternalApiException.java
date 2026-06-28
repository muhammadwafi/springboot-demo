package com.spring.demo.app.common.http.exception;

import lombok.Getter;
import org.springframework.web.client.RestClientResponseException;

/**
 * Base exception for external API errors.
 */
@Getter
public class ExternalApiException extends RuntimeException {

    private final int statusCode;
    private final String responseBody;

    public ExternalApiException(RestClientResponseException ex) {
        super(ex.getMessage(), ex);

        this.statusCode = ex.getStatusCode().value();
        this.responseBody = ex.getResponseBodyAsString();
    }

}
