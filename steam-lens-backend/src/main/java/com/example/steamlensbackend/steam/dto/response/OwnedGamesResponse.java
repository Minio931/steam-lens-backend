package com.example.steamlensbackend.steam.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OwnedGamesResponse(
        @JsonProperty("game_count") int gameCount,
        List<GameResponse> games
) {
}
