package com.example.bingo.repository;

import com.example.bingo.model.BingoCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface BingoCardRepository extends ReactiveMongoRepository<BingoCard, String> {
    Flux<BingoCard> findAllByPlayerId(String playerId); // Adicionando o método para buscar por playerId
}
