package com.spring.demo.app.common.http.exception;

import org.springframework.web.client.RestClientResponseException;

/**
 * Thrown when external API returns Http 403 forbidden.
 */
public class ExternalApiForbiddenException extends ExternalApiException {

    public ExternalApiForbiddenException(RestClientResponseException ex) {
        super(ex);
    }

}
