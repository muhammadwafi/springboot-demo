package com.spring.demo.app.common.http.client;

import com.spring.demo.app.common.http.model.ApiRequest;

public interface ExternalApiClient {

    <T> T get(
        ApiRequest<Void> request,
        Class<T> responseType
    );

    <T, B> T post(
        ApiRequest<Void> request,
        Class<T> responseType
    );

    <T, B> T put(
        ApiRequest<Void> request,
        Class<T> responseType
    );

    <T> T delete(
        ApiRequest<Void> request,
        Class<T> responseType
    );

    <T, B> T patch(
        ApiRequest<Void> request,
        Class<T> responseType
    );

}
