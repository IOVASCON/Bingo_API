package com.example.bingo.service;

import com.example.bingo.model.Player;
import com.example.bingo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Flux<Player> findAll() {
        return playerRepository.findAll();
    }

    public Mono<Player> findById(String id) {
        return playerRepository.findById(id);
    }

    public Mono<Player> save(Player player) {
        return playerRepository.save(player);
    }

    public Mono<Player> update(String id, Player player) {
        return playerRepository.findById(id)
                .flatMap(existingPlayer -> {
                    existingPlayer.setName(player.getName());
                    existingPlayer.setEmail(player.getEmail());
                    return playerRepository.save(existingPlayer);
                });
    }

    public Mono<Void> deleteById(String id) {
        return playerRepository.deleteById(id);
    }
}
