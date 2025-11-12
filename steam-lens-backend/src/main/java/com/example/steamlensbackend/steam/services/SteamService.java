package com.example.steamlensbackend.steam.services;

import com.example.steamlensbackend.steam.dto.options.GetOwnedGamesOptions;
import com.example.steamlensbackend.steam.dto.response.OwnedGamesResponse;
import com.example.steamlensbackend.steam.dto.response.SteamBaseResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SteamService {

    private final WebClient webClient;
    private final String apiKey;

    public SteamService(@Qualifier("steamWebClient") WebClient webClient, @Value("${steam.api.key}") String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    public Mono<SteamBaseResponse<OwnedGamesResponse>> getUserOwnedGames(
            String steamId,
            GetOwnedGamesOptions options
    )
    {
        GetOwnedGamesOptions defaultOptions = GetOwnedGamesOptions.defaultOptions();
        GetOwnedGamesOptions finalOptions = options == null ? defaultOptions : options.mergeWithDefaults(defaultOptions);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("IPlayerService/GetOwnedGames/v1/")
                        .queryParam("key", apiKey)
                        .queryParam("steamid", steamId)
                        .queryParam("include_appinfo", finalOptions.includeAppInfo())
                        .queryParam("include_played_free_games", finalOptions.includePlayedFreeGames())
                        .queryParam("language", finalOptions.language())
                        .queryParam("include_free_sub", finalOptions.includeFreeSub())
                        .queryParam("skip_unvetted_apps", finalOptions.skipUnvettedApps())
                        .queryParam("include_extended_appinfo", finalOptions.includeExtendedAppInfo())
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SteamBaseResponse<OwnedGamesResponse>>() {})
                .onErrorResume(e -> {
                    System.err.println("Error getting playtime for " + steamId + e.getMessage() + this.apiKey);
                    return Mono.empty();
                });

    }
}
