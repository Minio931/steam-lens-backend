package com.example.steamlensbackend.steam.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SteamConfig {

    @Value("${steam.api.baseUrl}")
    private String steamBaseUrl;

    @Bean
    public WebClient steamWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(steamBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
