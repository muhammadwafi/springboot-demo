package com.spring.demo.app.common.http.exception;

import org.springframework.web.client.RestClientResponseException;

/**
 * Thrown when external API returns an HTTP 5xx Server Error.
 */
public class ExternalApiServerException extends ExternalApiException {

    public ExternalApiServerException(RestClientResponseException ex) {
        super(ex);
    }

}