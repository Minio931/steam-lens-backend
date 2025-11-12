package com.example.steamlensbackend.steam.controllers;

import com.example.steamlensbackend.steam.dto.response.OwnedGamesResponse;
import com.example.steamlensbackend.steam.dto.response.SteamBaseResponse;
import com.example.steamlensbackend.steam.services.SteamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/steam")
public class SteamController {
    private final SteamService steamService;

    public SteamController(SteamService steamService) {
        this.steamService = steamService;
    }

    @GetMapping("/user/games/{steamId}")
    public Mono<SteamBaseResponse<OwnedGamesResponse>> getUserOwnedGames(@PathVariable String steamId) {
        return steamService.getUserOwnedGames(steamId, null);
    }
}
