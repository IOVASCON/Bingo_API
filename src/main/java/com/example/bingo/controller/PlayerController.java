package com.example.bingo.controller;

import com.example.bingo.model.Player;
import com.example.bingo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public Flux<Player> getAllPlayers() {
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Player> getPlayerById(@PathVariable String id) {
        return playerService.findById(id);
    }

    @PostMapping
    public Mono<Player> createPlayer(@RequestBody Player player) {
        return playerService.save(player);
    }

    @PutMapping("/{id}")
    public Mono<Player> updatePlayer(@PathVariable String id, @RequestBody Player player) {
        return playerService.update(id, player);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePlayer(@PathVariable String id) {
        return playerService.deleteById(id);
    }
}
