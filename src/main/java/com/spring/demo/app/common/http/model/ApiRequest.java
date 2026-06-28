package com.spring.demo.app.common.http.model;

import org.springframework.http.HttpHeaders;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents an HTTP request to an external API.
 *
 * @param url          Request URL.
 * @param headers      HTTP headers.
 * @param uriVariables URI template variables.
 * @param queryParams  Query parameters.
 * @param body         Request body.
 * @param <T>          Request body type.
 */
public record ApiRequest<T>(
    String url,
    HttpHeaders headers,
    Map<String, ?> uriVariables,
    Map<String, ?> queryParams,
    T body
) {

    /**
     * Creates a GET request.
     */
    public static ApiRequest<Void> get(String url) {
        return new ApiRequest<>(
            url,
            new HttpHeaders(),
            Map.of(),
            Map.of(),
            null
        );
    }

    /**
     * Creates a POST request.
     */
    public static <T> ApiRequest<T> post(String url, T body) {
        return new ApiRequest<>(
            url,
            new HttpHeaders(),
            Map.of(),
            Map.of(),
            body
        );
    }

    /**
     * Creates a PUT request.
     */
    public static <T> ApiRequest<T> put(String url, T body) {
        return new ApiRequest<>(
            url,
            new HttpHeaders(),
            Map.of(),
            Map.of(),
            body
        );
    }

    /**
     * Creates a PATCH request.
     */
    public static <T> ApiRequest<T> patch(String url, T body) {
        return new ApiRequest<>(
            url,
            new HttpHeaders(),
            Map.of(),
            Map.of(),
            body
        );
    }

    /**
     * Creates a DELETE request.
     */
    public static ApiRequest<Void> delete(String url) {
        return new ApiRequest<>(
            url,
            new HttpHeaders(),
            Map.of(),
            Map.of(),
            null
        );
    }

    /**
     * Returns a copy with additional headers.
     */
    public ApiRequest<T> withHeaders(HttpHeaders headers) {
        return new ApiRequest<>(
            url,
            headers,
            uriVariables,
            queryParams,
            body
        );
    }

    /**
     * Returns a copy with additional URI variables.
     */
    public ApiRequest<T> withUriVariables(Map<String, ?> uriVariables) {
        return new ApiRequest<>(
            url,
            headers,
            uriVariables,
            queryParams,
            body
        );
    }

    /**
     * Returns a copy with additional query parameters.
     */
    public ApiRequest<T> withQueryParams(Map<String, ?> queryParams) {
        return new ApiRequest<>(
            url,
            headers,
            uriVariables,
            queryParams,
            body
        );
    }

    /**
     * Adds a single query parameter.
     */
    public ApiRequest<T> withQueryParam(String key, Object value) {
        Map<String, Object> params = new LinkedHashMap<>(queryParams);
        params.put(key, value);

        return new ApiRequest<>(
            url,
            headers,
            uriVariables,
            params,
            body
        );
    }

    /**
     * Adds a Bearer token.
     */
    public ApiRequest<T> withBearerToken(String token) {
        HttpHeaders newHeaders = new HttpHeaders();
        newHeaders.addAll(headers);
        newHeaders.setBearerAuth(token);

        return new ApiRequest<>(
            url,
            newHeaders,
            uriVariables,
            queryParams,
            body
        );
    }

}