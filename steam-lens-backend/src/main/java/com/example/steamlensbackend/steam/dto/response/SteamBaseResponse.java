package com.example.steamlensbackend.steam.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SteamBaseResponse<T>(
        @JsonProperty("response") T response
) {
}
