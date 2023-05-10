package com.filiaiev.authservice.repository;

import com.filiaiev.authservice.exception.AuthenticationException;
import io.netty.handler.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.util.function.Function;

import static reactor.netty.http.client.HttpClient.*;

public abstract class ApiRepository {

    protected final WebClient webClient;

    public ApiRepository(String baseUrl, LogLevel logLevel) {
        HttpClient httpClient = create()
                .wiretap("reactor.netty.http.client.HttpClient",
                        logLevel, AdvancedByteBufFormat.TEXTUAL);

        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    protected Function<ClientResponse, Mono<? extends Throwable>> handleError(String message) {
        return clientResponse -> Mono.error(
                new AuthenticationException(message, HttpStatus.resolve(clientResponse.statusCode().value()))
        );
    }
}