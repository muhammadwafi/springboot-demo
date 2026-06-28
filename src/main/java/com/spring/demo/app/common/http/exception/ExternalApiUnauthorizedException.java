package com.spring.demo.app.common.http.exception;

import org.springframework.web.client.RestClientResponseException;

/**
 * Thrown when external API return Http 401 unauthorized.
 */
public class ExternalApiUnauthorizedException extends ExternalApiException {

    public ExternalApiUnauthorizedException(RestClientResponseException ex) {
        super(ex);
    }

}
