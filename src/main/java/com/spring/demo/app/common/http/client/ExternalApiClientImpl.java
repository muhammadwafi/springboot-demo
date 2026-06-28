package com.spring.demo.app.common.http.client;

import com.spring.demo.app.common.http.exception.*;
import com.spring.demo.app.common.http.model.ApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class ExternalApiClientImpl implements ExternalApiClient {

    private final RestClient restClient;

    @Override
    public <T> T get(
        ApiRequest<Void> request,
        Class<T> responseType
    ) {
        return execute(() ->
            restClient
                .get()
                .uri(buildUri(request))
                .headers(httpHeaders -> httpHeaders.addAll(defaultHeaders(request.headers())))
                .retrieve()
                .body(responseType)
        );
    }

    @Override
    public <T, B> T post(
        ApiRequest<Void> request,
        Class<T> responseType
    ) {

        return execute(() ->
            restClient
                .post()
                .uri(buildUri(request))
                .headers(httpHeaders -> httpHeaders.addAll(defaultHeaders(request.headers())))
                .body(request.body())
                .retrieve()
                .body(responseType)
        );
    }

    @Override
    public <T, B> T put(
        ApiRequest<Void> request,
        Class<T> responseType
    ) {

        return execute(() ->
            restClient
                .put()
                .uri(request.url())
                .headers(httpHeaders -> httpHeaders.addAll(defaultHeaders(request.headers())))
                .body(request.body())
                .retrieve()
                .body(responseType)
        );
    }

    @Override
    public <T, B> T patch(
        ApiRequest<Void> request,
        Class<T> responseType
    ) {

        return execute(() ->
            restClient
                .patch()
                .uri(buildUri(request))
                .headers(httpHeaders -> httpHeaders.addAll(defaultHeaders(request.headers())))
                .body(request.body())
                .retrieve()
                .body(responseType)
        );
    }

    @Override
    public <T> T delete(
        ApiRequest<Void> request,
        Class<T> responseType
    ) {

        return execute(() ->
            restClient
                .delete()
                .uri(buildUri(request))
                .headers(httpHeaders -> httpHeaders.addAll(defaultHeaders(request.headers())))
                .retrieve()
                .body(responseType)
        );
    }

    private <T> T execute(ApiAction<T> action) {
        try {
            return action.execute();
        } catch (RestClientResponseException ex) {
            HttpStatusCode status = ex.getStatusCode();

            if (status.value() == 401) {
                throw new ExternalApiUnauthorizedException(ex);
            }

            if (status.value() == 403) {
                throw new ExternalApiForbiddenException(ex);
            }

            if (status.value() == 404) {
                throw new ExternalApiNotFoundException(ex);
            }

            if (status.is5xxServerError()) {
                throw new ExternalApiServerException(ex);
            }

            throw new ExternalApiException(ex);
        }
    }

    /**
     * Build request headers
     *
     * @param headers Custom headers.
     * @return Merged headers
     */
    private HttpHeaders defaultHeaders(HttpHeaders headers) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(MediaType.parseMediaTypes(MediaType.APPLICATION_JSON_VALUE));

        if (headers != null) {
            httpHeaders.addAll(headers);
        }

        return httpHeaders;
    }

    @FunctionalInterface
    private interface ApiAction<T> {
        T execute();
    }

    private URI buildUri(ApiRequest<?> request) {
        UriComponentsBuilder builder = UriComponentsBuilder
            .fromUriString(request.url());

        if (request.queryParams() != null) {
            request.queryParams().forEach(builder::queryParam);
        }

        return builder.build(request.uriVariables());
    }
}
