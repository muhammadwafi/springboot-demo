package com.spring.demo.app.common.http.exception;

import org.springframework.web.client.RestClientResponseException;

/**
 * Thrown when external API returns HTTP 404 Not Found.
 */
public class ExternalApiNotFoundException extends ExternalApiException {

    public ExternalApiNotFoundException(RestClientResponseException ex) {
        super(ex);
    }

}
